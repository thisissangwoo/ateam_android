package com.example.anafor.Box_Recode;

public class Box_RecordDTO {

    private String title, content, date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Box_RecordDTO(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
