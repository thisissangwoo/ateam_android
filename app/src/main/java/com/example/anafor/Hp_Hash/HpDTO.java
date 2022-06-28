package com.example.anafor.Hp_Hash;

public class HpDTO {

    private String text, name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HpDTO(String text, String name) {
        this.text = text;
        this.name = name;
    }
}
