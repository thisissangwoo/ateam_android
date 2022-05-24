package com.example.anafor.Box_Alarm;

public class Box_AlarmDTO {

    private int imgv_url;
    private String title, location, date;

    public int getImgv_url() {
        return imgv_url;
    }

    public void setImgv_url(int imgv_url) {
        this.imgv_url = imgv_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Box_AlarmDTO(int imgv_url, String title, String location, String date) {
        this.imgv_url = imgv_url;
        this.title = title;
        this.location = location;
        this.date = date;
    }
}
