package com.bell.kst.dto;

public class JwtUserInfoDTO {
    private Long seq;
    private String name;
    private Integer role;

    public JwtUserInfoDTO(Long seq, String name, Integer role) {
        this.seq = seq;
        this.name = name;
        this.role = role;
    }

    public Long getSeq() {
        return seq;
    }

    public String getName() {
        return name;
    }

    public Integer getRole() {
        return role;
    }
}
