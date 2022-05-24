package com.example.anafor.Nav_Vaccine;

public class VaccineDTO {
    private String vacc_title, vacc_eng, vacc_content;

    public VaccineDTO(String vacc_title, String vacc_eng, String vacc_content) {
        this.vacc_title = vacc_title;
        this.vacc_eng = vacc_eng;
        this.vacc_content = vacc_content;
    }

    public String getVacc_title() {
        return vacc_title;
    }

    public void setVacc_title(String vacc_title) {
        this.vacc_title = vacc_title;
    }

    public String getVacc_eng() {
        return vacc_eng;
    }

    public void setVacc_eng(String vacc_eng) {
        this.vacc_eng = vacc_eng;
    }

    public String getVacc_content() {
        return vacc_content;
    }

    public void setVacc_content(String vacc_content) {
        this.vacc_content = vacc_content;
    }
}
