package com.bell.kst.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "role_seq")
    private Role role;

    @Column(name = "user_type")
    private Integer type;

    @Column(name = "user_status")
    private Integer status;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_nm")
    private String name;

    @Column(name = "user_pwd")
    private String password;

    @Column(name = "phone_no")
    private String phone;

    @Column(name = "create_dt")
    private Long createDate;

    @Column(name = "update_dt")
    private Long updateDate;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
