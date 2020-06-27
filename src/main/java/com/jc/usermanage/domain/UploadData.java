package com.jc.usermanage.domain;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/18 15:40
 */
public class UploadData implements Serializable {
    private static final long serialVersionUID = 3606617836153877607L;

    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("电话")
    private String phone;
    @ExcelProperty("是否在职")
    private String status;
    @ExcelProperty("公司")
    private String company1;
    @ExcelProperty("所属公司")
    private String company2;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompany1() {
        return company1;
    }

    public void setCompany1(String company1) {
        this.company1 = company1;
    }

    public String getCompany2() {
        return company2;
    }

    public void setCompany2(String company2) {
        this.company2 = company2;
    }

    @Override
    public String toString() {
        return "UploadData{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", company1='" + company1 + '\'' +
                ", company2='" + company2 + '\'' +
                '}';
    }
}
