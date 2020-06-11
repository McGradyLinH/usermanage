package com.jc.usermanage.domain;

import java.io.Serializable;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/11 9:51
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = 3694071961987443284L;

    private Integer role_id;
    private Integer user_id;
    private String role_name;

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
