package com.project.one.pojo;

import java.util.ArrayList;
import java.util.List;

public class RoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRuleNameIsNull() {
            addCriterion("rule_name is null");
            return (Criteria) this;
        }

        public Criteria andRuleNameIsNotNull() {
            addCriterion("rule_name is not null");
            return (Criteria) this;
        }

        public Criteria andRuleNameEqualTo(String value) {
            addCriterion("rule_name =", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotEqualTo(String value) {
            addCriterion("rule_name <>", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThan(String value) {
            addCriterion("rule_name >", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("rule_name >=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThan(String value) {
            addCriterion("rule_name <", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThanOrEqualTo(String value) {
            addCriterion("rule_name <=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLike(String value) {
            addCriterion("rule_name like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotLike(String value) {
            addCriterion("rule_name not like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameIn(List<String> values) {
            addCriterion("rule_name in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotIn(List<String> values) {
            addCriterion("rule_name not in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameBetween(String value1, String value2) {
            addCriterion("rule_name between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotBetween(String value1, String value2) {
            addCriterion("rule_name not between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhIsNull() {
            addCriterion("role_name_zh is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhIsNotNull() {
            addCriterion("role_name_zh is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhEqualTo(String value) {
            addCriterion("role_name_zh =", value, "roleNameZh");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhNotEqualTo(String value) {
            addCriterion("role_name_zh <>", value, "roleNameZh");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhGreaterThan(String value) {
            addCriterion("role_name_zh >", value, "roleNameZh");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhGreaterThanOrEqualTo(String value) {
            addCriterion("role_name_zh >=", value, "roleNameZh");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhLessThan(String value) {
            addCriterion("role_name_zh <", value, "roleNameZh");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhLessThanOrEqualTo(String value) {
            addCriterion("role_name_zh <=", value, "roleNameZh");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhLike(String value) {
            addCriterion("role_name_zh like", value, "roleNameZh");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhNotLike(String value) {
            addCriterion("role_name_zh not like", value, "roleNameZh");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhIn(List<String> values) {
            addCriterion("role_name_zh in", values, "roleNameZh");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhNotIn(List<String> values) {
            addCriterion("role_name_zh not in", values, "roleNameZh");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhBetween(String value1, String value2) {
            addCriterion("role_name_zh between", value1, value2, "roleNameZh");
            return (Criteria) this;
        }

        public Criteria andRoleNameZhNotBetween(String value1, String value2) {
            addCriterion("role_name_zh not between", value1, value2, "roleNameZh");
            return (Criteria) this;
        }

        public Criteria andRoleWeightIsNull() {
            addCriterion("role_weight is null");
            return (Criteria) this;
        }

        public Criteria andRoleWeightIsNotNull() {
            addCriterion("role_weight is not null");
            return (Criteria) this;
        }

        public Criteria andRoleWeightEqualTo(Integer value) {
            addCriterion("role_weight =", value, "roleWeight");
            return (Criteria) this;
        }

        public Criteria andRoleWeightNotEqualTo(Integer value) {
            addCriterion("role_weight <>", value, "roleWeight");
            return (Criteria) this;
        }

        public Criteria andRoleWeightGreaterThan(Integer value) {
            addCriterion("role_weight >", value, "roleWeight");
            return (Criteria) this;
        }

        public Criteria andRoleWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_weight >=", value, "roleWeight");
            return (Criteria) this;
        }

        public Criteria andRoleWeightLessThan(Integer value) {
            addCriterion("role_weight <", value, "roleWeight");
            return (Criteria) this;
        }

        public Criteria andRoleWeightLessThanOrEqualTo(Integer value) {
            addCriterion("role_weight <=", value, "roleWeight");
            return (Criteria) this;
        }

        public Criteria andRoleWeightIn(List<Integer> values) {
            addCriterion("role_weight in", values, "roleWeight");
            return (Criteria) this;
        }

        public Criteria andRoleWeightNotIn(List<Integer> values) {
            addCriterion("role_weight not in", values, "roleWeight");
            return (Criteria) this;
        }

        public Criteria andRoleWeightBetween(Integer value1, Integer value2) {
            addCriterion("role_weight between", value1, value2, "roleWeight");
            return (Criteria) this;
        }

        public Criteria andRoleWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("role_weight not between", value1, value2, "roleWeight");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}