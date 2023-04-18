package com.bell.kst.dto;

public class AuthDTO {
    private Integer seq;
    private String menu;
    private boolean accessState;
    private boolean readState;
    private boolean writeState;
    private boolean updateState;
    private boolean deleteState;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
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
}
