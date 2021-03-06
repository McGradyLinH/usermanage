package com.jc.usermanage.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/11 15:05
 */
public class Account implements Serializable {
    private static final long serialVersionUID = -5799039131156124290L;

    private Integer user_id;
    private UserInfo info;
    private Dept dept;
    private Status status;
    private TbCompany tbCompany;

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public TbCompany getTbCompany() {
        return tbCompany;
    }

    public void setTbCompany(TbCompany tbCompany) {
        this.tbCompany = tbCompany;
    }

    @Override
    public String toString() {
        return "Account{" +
                "user_id=" + user_id +
                ", info=" + info +
                ", dept=" + dept +
                ", status=" + status +
                ", tbCompany=" + tbCompany +
                '}';
    }
}
