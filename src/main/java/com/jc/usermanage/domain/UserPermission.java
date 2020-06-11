package com.jc.usermanage.domain;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/11 9:52
 */
public class UserPermission {

    private Integer permission_id;
    private String role_name;
    private String permission;

    public Integer getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Integer permission_id) {
        this.permission_id = permission_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
