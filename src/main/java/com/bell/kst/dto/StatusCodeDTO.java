package com.bell.kst.dto;

public class StatusCodeDTO {
    private Integer code;
    private String message;

    public StatusCodeDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
