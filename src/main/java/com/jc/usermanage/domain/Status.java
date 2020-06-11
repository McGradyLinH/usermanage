package com.jc.usermanage.domain;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author LinXing
 * @version 1.0
 * @desc TODO
 * @date 2020/6/11 9:50
 */
public class Status implements Serializable {

    private static final long serialVersionUID = -5196723866033322663L;
    private Integer status_id;
    @NotBlank
    private String status;
    /**
     * 开始时间必须是现在或之前的某个时间
     */
    @PastOrPresent
    private LocalDateTime start_time;
    /**
     * 结束时间必须是现在或以后的某个时间
     */
    @FutureOrPresent
    private LocalDateTime end_time;

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "Status{" +
                "status_id=" + status_id +
                ", status='" + status + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }
}
