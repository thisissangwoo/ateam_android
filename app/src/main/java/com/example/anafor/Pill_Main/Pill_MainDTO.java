package com.example.anafor.Pill_Main;

public class Pill_MainDTO {

    private int pii_code, pill_code1, pill_code2, pill_code3;
    private String user_id, hp_code, pill_date;

    public int getPii_code() {
        return pii_code;
    }

    public void setPii_code(int pii_code) {
        this.pii_code = pii_code;
    }

    public int getPill_code1() {
        return pill_code1;
    }

    public void setPill_code1(int pill_code1) {
        this.pill_code1 = pill_code1;
    }

    public int getPill_code2() {
        return pill_code2;
    }

    public void setPill_code2(int pill_code2) {
        this.pill_code2 = pill_code2;
    }

    public int getPill_code3() {
        return pill_code3;
    }

    public void setPill_code3(int pill_code3) {
        this.pill_code3 = pill_code3;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHp_code() {
        return hp_code;
    }

    public void setHp_code(String hp_code) {
        this.hp_code = hp_code;
    }

    public String getPill_date() {
        return pill_date;
    }

    public void setPill_date(String pill_date) {
        this.pill_date = pill_date;
    }

    public Pill_MainDTO(int pii_code, int pill_code1, int pill_code2, int pill_code3, String user_id, String hp_code, String pill_date) {
        this.pii_code = pii_code;
        this.pill_code1 = pill_code1;
        this.pill_code2 = pill_code2;
        this.pill_code3 = pill_code3;
        this.user_id = user_id;
        this.hp_code = hp_code;
        this.pill_date = pill_date;



    }
}

