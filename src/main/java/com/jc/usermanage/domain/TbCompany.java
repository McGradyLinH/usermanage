package com.jc.usermanage.domain;

import java.io.Serializable;

/**
 * (TbCompany)实体类
 *
 * @author makejava
 * @since 2020-06-16 09:22:02
 */
public class TbCompany implements Serializable {
    private static final long serialVersionUID = 778375149159430969L;
    
    private Integer companyId;
    /**
    * 公司名称
    */
    private String companyname;
    /**
    * 公司地址
    */
    private String companylocation;


    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanylocation() {
        return companylocation;
    }

    public void setCompanylocation(String companylocation) {
        this.companylocation = companylocation;
    }

    @Override
    public String toString() {
        return "TbCompany{" +
                "companyId=" + companyId +
                ", companyname='" + companyname + '\'' +
                ", companylocation='" + companylocation + '\'' +
                '}';
    }
}