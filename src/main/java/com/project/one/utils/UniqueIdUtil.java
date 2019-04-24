package com.project.one.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhanghenan on 17/9/27.
 */
public class UniqueIdUtil {
    private static final Logger logger = LoggerFactory.getLogger(UniqueIdUtil.class);

    // 取IP地址最后一段作为多机分隔段
    private static long SCF_SERVER_ID = 255L;

    static {
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String[] nums = hostAddress.split("\\.");
            SCF_SERVER_ID = Long.parseLong(nums[3]);
        } catch (Exception ex) {
            logger.error("UniqueIDUtils init Exception, Default SCF_SERVER_ID = " + SCF_SERVER_ID, ex);
        }

    }

    private static final long UniqueID_BEGIN_TIME = 1506787200567L;

    // unique id 提前生成后保存在Queue里
    private static Queue<Long> uniqueIdQueue = new ConcurrentLinkedQueue<Long>();

    private static final int SCALE_CEILING = 6;
    private static final int SCALE_FLOOR = 1;
    private static final int DEFAULT_SIZE_DIVIDE = 10;
    private static final int DEFAULT_SIZE_CEILING = 12;

    private static int sizeDivide = DEFAULT_SIZE_DIVIDE;
    private static int sizeCeiling = DEFAULT_SIZE_CEILING + 5;

    private static Queue<Integer> scaleQueue = new ConcurrentLinkedQueue<Integer>();
    private static int totalScale = 0;

    private static int scale = SCALE_FLOOR;

    private static long CURRENT_MILLIS = 0L;
    private static int lastSize = 0;
    private static long lastStamp = 0L;

    private static ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

    // unique id generate task
    private static Callable<Integer> uidGenerateTask = new Callable<Integer>() {

        @Override public Integer call() {

            if (UniqueIdUtil.uniqueIdQueue.size() < UniqueIdUtil.sizeDivide) {

                if (UniqueIdUtil.lastStamp > 0) {
                    //非第一轮执行时，根据近期生成速率确定指标：scale、sizeDivide、sizeCeiling
                    reDetermineIndicator();
                }

                // 将uid存量补充到>=sizeCeiling
                while (UniqueIdUtil.uniqueIdQueue.size() < UniqueIdUtil.sizeCeiling) {
                    UniqueIdUtil.generateNewId();
                }

                // 记录快照，用于计算每毫秒生成的uid容量
                UniqueIdUtil.lastStamp = System.currentTimeMillis();
                UniqueIdUtil.lastSize = UniqueIdUtil.uniqueIdQueue.size();

            }

            // 目前没有用call的执行结果，暂时返回此轮id生成前的存量
            return UniqueIdUtil.uniqueIdQueue.size();
        }

        /**
         * 根据近期生成速率确定指标：scale、sizeDivide、sizeCeiling
         */
        private void reDetermineIndicator() {

            long velocity = ((UniqueIdUtil.lastSize - UniqueIdUtil.uniqueIdQueue.size()) << 2) / (
                    System.currentTimeMillis() - UniqueIdUtil.lastStamp);

            int tempScale = UniqueIdUtil.scale;
            if (velocity > 1) {
                while (tempScale > UniqueIdUtil.SCALE_FLOOR && velocity < (1 << tempScale)) {
                    tempScale--;
                }
                while (tempScale < UniqueIdUtil.SCALE_CEILING && velocity > (1 << tempScale)) {
                    tempScale++;
                }
            } else {
                tempScale = UniqueIdUtil.SCALE_FLOOR;
            }

            UniqueIdUtil.scaleQueue.add(tempScale);
            UniqueIdUtil.totalScale += tempScale;
            if (UniqueIdUtil.scaleQueue.size() > 3) {
                UniqueIdUtil.totalScale -= UniqueIdUtil.scaleQueue.poll();
            }

            if (UniqueIdUtil.scale != UniqueIdUtil.totalScale / UniqueIdUtil.scaleQueue.size()) {
                UniqueIdUtil.scale = UniqueIdUtil.totalScale / UniqueIdUtil.scaleQueue.size();
                UniqueIdUtil.sizeDivide = UniqueIdUtil.DEFAULT_SIZE_DIVIDE << UniqueIdUtil.scale;
                UniqueIdUtil.sizeCeiling = (UniqueIdUtil.DEFAULT_SIZE_CEILING << UniqueIdUtil.scale) + 5;
                //					UniqueIDUtils.logger.info("UniqueIDUtils.scale={}, UniqueIDUtils.sizeDivide={}, UniqueIDUtils.sizeCeiling={}, uniqueIdQueue.size={}", new Object[]{UniqueIDUtils.scale, UniqueIDUtils.sizeDivide, UniqueIDUtils.sizeCeiling, UniqueIDUtils.uniqueIdQueue.size()});
            }
        }
    };

    static {
        exec.schedule(uidGenerateTask, 0L, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取主键ID
     *
     * @return
     * @throws Exception
     */
    public static long getUniqueID() {

        if (uniqueIdQueue.size() < sizeDivide) {
            exec.schedule(uidGenerateTask, 0L, TimeUnit.MILLISECONDS);
        }

        Long uid = uniqueIdQueue.poll();
        while (uid == null) {
            logger.info("The uniqueIdQueue is empty! scale={},sizeCeiling={}", scale, sizeCeiling);
            generateNewId();
            uid = uniqueIdQueue.poll();
        }

        return uid.longValue();
    }

    private static synchronized void generateNewId() {

        if (scale > SCALE_CEILING) {
            scale = SCALE_CEILING;
        }
        long destID = System.currentTimeMillis() - UniqueID_BEGIN_TIME;
        if (destID <= CURRENT_MILLIS) {
            destID = ++CURRENT_MILLIS;
        }
        else {
            CURRENT_MILLIS = destID;
        }

        destID = (destID << 8) | SCF_SERVER_ID;
        destID = destID << SCALE_CEILING;
        for (int i = 1 << scale; i > 0; i--) {
            uniqueIdQueue.add(destID++);
        }
    }

    public static void main(String[] args){

        for(int i = 0;  i < 108; i++){
            System.out.println(getUniqueID());
        }
        System.exit(0);
    }
}
