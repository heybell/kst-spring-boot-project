package com.bell.kst.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_auth_info")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_seq")
    private Integer seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_seq", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_seq", nullable = false)
    private Menu menu;

    @Column(name = "access_yn", nullable = false)
    private boolean accessState;

    @Column(name = "read_yn", nullable = false)
    private boolean readState;

    @Column(name = "write_yn", nullable = false)
    private boolean writeState;

    @Column(name = "update_yn", nullable = false)
    private boolean updateState;

    @Column(name = "delete_yn", nullable = false)
    private boolean deleteState;

    @Column(name = "create_dt")
    private Long createDate;

    @Column(name = "update_dt")
    private Long updateDate;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public boolean isAccessState() {
        return accessState;
    }

    public void setAccessState(boolean accessState) {
        this.accessState = accessState;
    }

    public boolean isReadState() {
        return readState;
    }

    public void setReadState(boolean readState) {
        this.readState = readState;
    }

    public boolean isWriteState() {
        return writeState;
    }

    public void setWriteState(boolean writeState) {
        this.writeState = writeState;
    }

    public boolean isUpdateState() {
        return updateState;
    }

    public void setUpdateState(boolean updateState) {
        this.updateState = updateState;
    }

    public boolean isDeleteState() {
        return deleteState;
    }

    public void setDeleteState(boolean deleteState) {
        this.deleteState = deleteState;
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
