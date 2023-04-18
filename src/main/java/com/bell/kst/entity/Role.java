package com.bell.kst.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_role_info")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_seq")
    private Integer seq;

    @Column(name = "role_nm", nullable = false)
    private String role;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
