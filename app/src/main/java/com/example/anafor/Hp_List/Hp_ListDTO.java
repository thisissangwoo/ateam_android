package com.example.anafor.Hp_List;

public class Hp_ListDTO {

    private int imgv_url;
    private String HP_CODE, HP_NAME, TYPE_CODE, SIDO_CODE, SIDO_NAME, SIGUNGU_CODE, SIGUNGU_NAME, HP_ADDR, HP_TEL, HP_URL, HP_X, HP_Y;

    public Hp_ListDTO(int imgv_url, String HP_CODE, String HP_NAME, String TYPE_CODE, String SIDO_CODE, String SIDO_NAME, String SIGUNGU_CODE, String SIGUNGU_NAME, String HP_ADDR, String HP_TEL, String HP_URL, String HP_X, String HP_Y) {
        this.imgv_url = imgv_url;
        this.HP_CODE = HP_CODE;
        this.HP_NAME = HP_NAME;
        this.TYPE_CODE = TYPE_CODE;
        this.SIDO_CODE = SIDO_CODE;
        this.SIDO_NAME = SIDO_NAME;
        this.SIGUNGU_CODE = SIGUNGU_CODE;
        this.SIGUNGU_NAME = SIGUNGU_NAME;
        this.HP_ADDR = HP_ADDR;
        this.HP_TEL = HP_TEL;
        this.HP_URL = HP_URL;
        this.HP_X = HP_X;
        this.HP_Y = HP_Y;
    }

    public int getImgv_url() {
        return imgv_url;
    }

    public void setImgv_url(int imgv_url) {
        this.imgv_url = imgv_url;
    }

    public String getHP_CODE() {
        return HP_CODE;
    }

    public void setHP_CODE(String HP_CODE) {
        this.HP_CODE = HP_CODE;
    }

    public String getHP_NAME() {
        return HP_NAME;
    }

    public void setHP_NAME(String HP_NAME) {
        this.HP_NAME = HP_NAME;
    }

    public String getTYPE_CODE() {
        return TYPE_CODE;
    }

    public void setTYPE_CODE(String TYPE_CODE) {
        this.TYPE_CODE = TYPE_CODE;
    }

    public String getSIDO_CODE() {
        return SIDO_CODE;
    }

    public void setSIDO_CODE(String SIDO_CODE) {
        this.SIDO_CODE = SIDO_CODE;
    }

    public String getSIDO_NAME() {
        return SIDO_NAME;
    }

    public void setSIDO_NAME(String SIDO_NAME) {
        this.SIDO_NAME = SIDO_NAME;
    }

    public String getSIGUNGU_CODE() {
        return SIGUNGU_CODE;
    }

    public void setSIGUNGU_CODE(String SIGUNGU_CODE) {
        this.SIGUNGU_CODE = SIGUNGU_CODE;
    }

    public String getSIGUNGU_NAME() {
        return SIGUNGU_NAME;
    }

    public void setSIGUNGU_NAME(String SIGUNGU_NAME) {
        this.SIGUNGU_NAME = SIGUNGU_NAME;
    }

    public String getHP_ADDR() {
        return HP_ADDR;
    }

    public void setHP_ADDR(String HP_ADDR) {
        this.HP_ADDR = HP_ADDR;
    }

    public String getHP_TEL() {
        return HP_TEL;
    }

    public void setHP_TEL(String HP_TEL) {
        this.HP_TEL = HP_TEL;
    }

    public String getHP_URL() {
        return HP_URL;
    }

    public void setHP_URL(String HP_URL) {
        this.HP_URL = HP_URL;
    }

    public String getHP_X() {
        return HP_X;
    }

    public void setHP_X(String HP_X) {
        this.HP_X = HP_X;
    }

    public String getHP_Y() {
        return HP_Y;
    }

    public void setHP_Y(String HP_Y) {
        this.HP_Y = HP_Y;
    }
}
