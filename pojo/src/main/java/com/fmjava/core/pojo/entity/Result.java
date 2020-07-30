package com.fmjava.core.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Result implements Serializable {
    private String message;
    private Boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Result(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
