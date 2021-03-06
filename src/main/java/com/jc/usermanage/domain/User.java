package com.jc.usermanage.domain;


import java.io.Serializable;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/11 9:32
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1036671338779680044L;
    /**
     * 用户id
     */
    private Integer user_id;
    /**
     * 用户部门id
     */
    private Integer user_dept_id;
    /**
     * 用户信息id
     */
    private Integer user_info_id;
    /**
     * 用户状态id
     */
    private Integer user_status_id;

    /**
     * 用户公司id
     */
    private Integer user_company_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_dept_id() {
        return user_dept_id;
    }

    public void setUser_dept_id(Integer user_dept_id) {
        this.user_dept_id = user_dept_id;
    }

    public Integer getUser_info_id() {
        return user_info_id;
    }

    public void setUser_info_id(Integer user_info_id) {
        this.user_info_id = user_info_id;
    }

    public Integer getUser_status_id() {
        return user_status_id;
    }

    public void setUser_status_id(Integer user_status_id) {
        this.user_status_id = user_status_id;
    }

    public Integer getUser_company_id() {
        return user_company_id;
    }

    public void setUser_company_id(Integer user_company_id) {
        this.user_company_id = user_company_id;
    }
}
