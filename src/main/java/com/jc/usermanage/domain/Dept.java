package com.jc.usermanage.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/11 9:50
 */
public class Dept implements Serializable {
    private static final long serialVersionUID = 5341763463453113672L;

    private Integer dept_id;
    @NotNull
    private String dept_name;
    @NotNull
    private String dept_address;

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_address() {
        return dept_address;
    }

    public void setDept_address(String dept_address) {
        this.dept_address = dept_address;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dept_id=" + dept_id +
                ", dept_name='" + dept_name + '\'' +
                ", dept_address='" + dept_address + '\'' +
                '}';
    }
}
