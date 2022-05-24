package com.example.anafor.User;

public class EmailNumberVO {
    private int checkNum;

    public EmailNumberVO(int checkNum) {
        super();
        this.checkNum = checkNum;
    }

    public EmailNumberVO() {
        super();
    }

    public int getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }
}
