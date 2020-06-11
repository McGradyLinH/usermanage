package com.jc.usermanage.domain;


import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/11 9:47
 */
public class UserInfo {
    private Integer user_info_id;
    private String name;
    @NotNull(message = "电话号码不能为空！")
    @Size(max=11,message="电话号码长度不能超过{max}位")
    private String phone;
    @Email
    private String email;
    private Integer age;
    private Boolean sex;
    private LocalDateTime birth_time;
    private String user_pwd;
    private BigDecimal balance;

    public Integer getUser_info_id() {
        return user_info_id;
    }

    public void setUser_info_id(Integer user_info_id) {
        this.user_info_id = user_info_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean isSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public LocalDateTime getBirth_time() {
        return birth_time;
    }

    public void setBirth_time(LocalDateTime birth_time) {
        this.birth_time = birth_time;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_info_id=" + user_info_id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", birth_time=" + birth_time +
                ", user_pwd='" + user_pwd + '\'' +
                ", balance=" + balance +
                '}';
    }
}
