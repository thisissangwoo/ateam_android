package com.example.anafor.Nav_Schedule;

import java.io.Serializable;

public class ScheduleDTO implements Serializable {

    private int sc_code;
    private String user_id, sc_date, sc_title, sc_memo;

    public int getSc_code() {
        return sc_code;
    }

    public void setSc_code(int sc_code) {
        this.sc_code = sc_code;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSc_date() {
        return sc_date;
    }

    public void setSc_date(String sc_date) {
        this.sc_date = sc_date;
    }

    public String getSc_title() {
        return sc_title;
    }

    public void setSc_title(String sc_title) {
        this.sc_title = sc_title;
    }

    public String getSc_memo() {
        return sc_memo;
    }

    public void setSc_memo(String sc_memo) {
        this.sc_memo = sc_memo;
    }

}
