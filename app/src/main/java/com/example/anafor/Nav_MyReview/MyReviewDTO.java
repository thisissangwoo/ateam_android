package com.example.anafor.Nav_MyReview;

public class MyReviewDTO {

    public String name, date, category, content;
    public String explanation, diagnosis, kindness;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getKindness() {
        return kindness;
    }

    public void setKindness(String kindness) {
        this.kindness = kindness;
    }

    public MyReviewDTO(String name, String date, String category, String content, String explanation, String diagnosis, String kindness) {
        this.name = name;
        this.date = date;
        this.category = category;
        this.content = content;
        this.explanation = explanation;
        this.diagnosis = diagnosis;
        this.kindness = kindness;
    }
}
