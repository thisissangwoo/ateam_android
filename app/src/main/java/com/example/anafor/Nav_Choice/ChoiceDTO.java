package com.example.anafor.Nav_Choice;

public class ChoiceDTO {

    private int img_url;
    private String name, addr, category, date;

    public int getImg_url() {
        return img_url;
    }

    public void setImg_url(int img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ChoiceDTO(int img_url, String name, String addr, String category, String date) {
        this.img_url = img_url;
        this.name = name;
        this.addr = addr;
        this.category = category;
        this.date = date;
    }
}
