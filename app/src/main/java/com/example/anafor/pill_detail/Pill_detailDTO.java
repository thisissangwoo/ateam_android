package com.example.anafor.pill_detail;

import android.net.Uri;

public class Pill_detailDTO {

    private int drug_id;
    private String drug_name, drug_cp, drug_code, drug_efcy, drug_use, drug_se, drug_atpn, drug_img;


    public Pill_detailDTO(int drug_id, String drug_name, String drug_cp, String drug_code, String drug_efcy, String drug_use, String drug_se, String drug_atpn, String drug_img) {
        this.drug_id = drug_id;
        this.drug_name = drug_name;
        this.drug_cp = drug_cp;
        this.drug_code = drug_code;
        this.drug_efcy = drug_efcy;
        this.drug_use = drug_use;
        this.drug_se = drug_se;
        this.drug_atpn = drug_atpn;
        this.drug_img = drug_img;
    }

    public int getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(int drug_id) {
        this.drug_id = drug_id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getDrug_cp() {
        return drug_cp;
    }

    public void setDrug_cp(String drug_cp) {
        this.drug_cp = drug_cp;
    }

    public String getDrug_code() {
        return drug_code;
    }

    public void setDrug_code(String drug_code) {
        this.drug_code = drug_code;
    }

    public String getDrug_efcy() {
        return drug_efcy;
    }

    public void setDrug_efcy(String drug_efcy) {
        this.drug_efcy = drug_efcy;
    }

    public String getDrug_use() {
        return drug_use;
    }

    public void setDrug_use(String drug_use) {
        this.drug_use = drug_use;
    }

    public String getDrug_se() {
        return drug_se;
    }

    public void setDrug_se(String drug_se) {
        this.drug_se = drug_se;
    }

    public String getDrug_atpn() {
        return drug_atpn;
    }

    public void setDrug_atpn(String drug_atpn) {
        this.drug_atpn = drug_atpn;
    }

    public String getDrug_img() {
        return drug_img;
    }

    public void setDrug_img(String drug_img) {
        this.drug_img = drug_img;
    }
}
