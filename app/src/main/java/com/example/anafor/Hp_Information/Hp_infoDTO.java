package com.example.anafor.Hp_Information;

import java.io.Serializable;

public class Hp_infoDTO implements Serializable {
    // 병원정보
    String hp_code, hp_name, type_code, hp_addr , hp_tel, hp_url, hp_x, hp_y,filename,filepath;
    //진료시간정보
    String start_m, end_m, start_t, end_t, start_w, end_w, start_th, end_th, start_f, end_f, start_s, end_s, start_su, end_su,  lunch_d, lunch_w, close_su, close_ho;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getHp_code() {
        return hp_code;
    }

    public void setHp_code(String hp_code) {
        this.hp_code = hp_code;
    }

    public String getHp_name() {
        return hp_name;
    }

    public void setHp_name(String hp_name) {
        this.hp_name = hp_name;
    }

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getHp_addr() {
        return hp_addr;
    }

    public void setHp_addr(String hp_addr) {
        this.hp_addr = hp_addr;
    }

    public String getHp_tel() {
        return hp_tel;
    }

    public void setHp_tel(String hp_tel) {
        this.hp_tel = hp_tel;
    }

    public String getHp_url() {
        return hp_url;
    }

    public void setHp_url(String hp_url) {
        this.hp_url = hp_url;
    }

    public String getHp_x() {
        return hp_x;
    }

    public void setHp_x(String hp_x) {
        this.hp_x = hp_x;
    }

    public String getHp_y() {
        return hp_y;
    }

    public void setHp_y(String hp_y) {
        this.hp_y = hp_y;
    }

    public String getStart_m() {
        return start_m;
    }

    public void setStart_m(String start_m) {
        this.start_m = start_m;
    }

    public String getEnd_m() {
        return end_m;
    }

    public void setEnd_m(String end_m) {
        this.end_m = end_m;
    }

    public String getStart_t() {
        return start_t;
    }

    public void setStart_t(String start_t) {
        this.start_t = start_t;
    }

    public String getEnd_t() {
        return end_t;
    }

    public void setEnd_t(String end_t) {
        this.end_t = end_t;
    }

    public String getStart_w() {
        return start_w;
    }

    public void setStart_w(String start_w) {
        this.start_w = start_w;
    }

    public String getEnd_w() {
        return end_w;
    }

    public void setEnd_w(String end_w) {
        this.end_w = end_w;
    }

    public String getStart_th() {
        return start_th;
    }

    public void setStart_th(String start_th) {
        this.start_th = start_th;
    }

    public String getEnd_th() {
        return end_th;
    }

    public void setEnd_th(String end_th) {
        this.end_th = end_th;
    }

    public String getStart_f() {
        return start_f;
    }

    public void setStart_f(String start_f) {
        this.start_f = start_f;
    }

    public String getEnd_f() {
        return end_f;
    }

    public void setEnd_f(String end_f) {
        this.end_f = end_f;
    }

    public String getStart_s() {
        return start_s;
    }

    public void setStart_s(String start_s) {
        this.start_s = start_s;
    }

    public String getEnd_s() {
        return end_s;
    }

    public void setEnd_s(String end_s) {
        this.end_s = end_s;
    }

    public String getStart_su() {
        return start_su;
    }

    public void setStart_su(String start_su) {
        this.start_su = start_su;
    }

    public String getEnd_su() {
        return end_su;
    }

    public void setEnd_su(String end_su) {
        this.end_su = end_su;
    }

    public String getLunch_d() {
        return lunch_d;
    }

    public void setLunch_d(String lunch_d) {
        this.lunch_d = lunch_d;
    }

    public String getLunch_w() {
        return lunch_w;
    }

    public void setLunch_w(String lunch_w) {
        this.lunch_w = lunch_w;
    }

    public String getClose_su() {
        return close_su;
    }

    public void setClose_su(String close_su) {
        this.close_su = close_su;
    }

    public String getClose_ho() {
        return close_ho;
    }

    public void setClose_ho(String close_ho) {
        this.close_ho = close_ho;
    }
}
