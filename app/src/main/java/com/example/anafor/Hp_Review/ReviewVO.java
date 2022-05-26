package com.example.anafor.Hp_Review;

import java.io.Serializable;

public class ReviewVO implements Serializable {
    int rev_num,rev_text1,rev_text2,rev_text3;
    double rev_grade;

    String user_id, rev_date, hp_code, rev_text4, hp_name,user_name;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getHp_name() {
        return hp_name;
    }

    public void setHp_name(String hp_name) {
        this.hp_name = hp_name;
    }

    public void setRev_text4(String rev_text4) {
        this.rev_text4 = rev_text4;
    }

    public String getRev_text4() {
        return rev_text4;
    }

    public String getHp_code() {
        return hp_code;
    }

    public void setHp_code(String hp_code) {
        this.hp_code = hp_code;
    }

    public int getRev_num() {
        return rev_num;
    }
    public void setRev_num(int rev_num) {
        this.rev_num = rev_num;
    }
    public int getRev_text1() {
        return rev_text1;
    }
    public void setRev_text1(int rev_text1) {
        this.rev_text1 = rev_text1;
    }
    public int getRev_text2() {
        return rev_text2;
    }
    public void setRev_text2(int rev_text2) {
        this.rev_text2 = rev_text2;
    }
    public int getRev_text3() {
        return rev_text3;
    }
    public void setRev_text3(int rev_text3) {
        this.rev_text3 = rev_text3;
    }
    public double getRev_grade() {
        return rev_grade;
    }
    public void setRev_grade(double rev_grade) {
        this.rev_grade = rev_grade;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getRev_date() {
        return rev_date;
    }
    public void setRev_date(String rev_date) {
        this.rev_date = rev_date;
    }
}
