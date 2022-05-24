package com.example.anafor.Hp_Main;

public class Hp_MainDTO {

    private int url;
    private String text;

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Hp_MainDTO(int url, String text) {
        this.url = url;
        this.text = text;
    }
}
