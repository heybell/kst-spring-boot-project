package com.bell.kst.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_menu_info")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_seq")
    private Long seq;

    @Column(name = "menu_nm", nullable = false)
    private String menu;

    @Column(name = "menu_path", nullable = false)
    private String path;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
