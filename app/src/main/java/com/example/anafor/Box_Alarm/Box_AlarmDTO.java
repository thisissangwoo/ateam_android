package com.example.anafor.Box_Alarm;

public class Box_AlarmDTO {

    private String user_id, memo;
    private String case_date1, case_date2, case_date3, case_date4;
    private int box_id, case_num1, case_num2, case_num3, case_num4;

    public Box_AlarmDTO(){

    }

    public Box_AlarmDTO(String user_id, String memo, String case_date1, String case_date2, String case_date3, String case_date4, int box_id, int case_num1, int case_num2, int case_num3, int case_num4) {
        this.user_id = user_id;
        this.memo = memo;
        this.case_date1 = case_date1;
        this.case_date2 = case_date2;
        this.case_date3 = case_date3;
        this.case_date4 = case_date4;
        this.box_id = box_id;
        this.case_num1 = case_num1;
        this.case_num2 = case_num2;
        this.case_num3 = case_num3;
        this.case_num4 = case_num4;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCase_date1() {
        return case_date1;
    }

    public void setCase_date1(String case_date1) {
        this.case_date1 = case_date1;
    }

    public String getCase_date2() {
        return case_date2;
    }

    public void setCase_date2(String case_date2) {
        this.case_date2 = case_date2;
    }

    public String getCase_date3() {
        return case_date3;
    }

    public void setCase_date3(String case_date3) {
        this.case_date3 = case_date3;
    }

    public String getCase_date4() {
        return case_date4;
    }

    public void setCase_date4(String case_date4) {
        this.case_date4 = case_date4;
    }

    public int getBox_id() {
        return box_id;
    }

    public void setBox_id(int box_id) {
        this.box_id = box_id;
    }

    public int getCase_num1() {
        return case_num1;
    }

    public void setCase_num1(int case_num1) {
        this.case_num1 = case_num1;
    }

    public int getCase_num2() {
        return case_num2;
    }

    public void setCase_num2(int case_num2) {
        this.case_num2 = case_num2;
    }

    public int getCase_num3() {
        return case_num3;
    }

    public void setCase_num3(int case_num3) {
        this.case_num3 = case_num3;
    }

    public int getCase_num4() {
        return case_num4;
    }

    public void setCase_num4(int case_num4) {
        this.case_num4 = case_num4;
    }
}
