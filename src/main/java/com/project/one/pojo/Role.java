package com.project.one.pojo;

public class Role {
    private Long id;

    private String ruleName;

    private String roleNameZh;

    private Integer roleWeight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRoleNameZh() {
        return roleNameZh;
    }

    public void setRoleNameZh(String roleNameZh) {
        this.roleNameZh = roleNameZh;
    }

    public Integer getRoleWeight() {
        return roleWeight;
    }

    public void setRoleWeight(Integer roleWeight) {
        this.roleWeight = roleWeight;
    }
}