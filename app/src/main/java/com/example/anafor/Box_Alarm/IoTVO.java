package com.example.anafor.Box_Alarm;

import java.io.Serializable;

public class IoTVO implements Serializable {
	
	private int box_id;
	private String user_id, memo, case_num, case_time;
	
	public int getBox_id() {
		return box_id;
	}
	public void setBox_id(int box_id) {
		this.box_id = box_id;
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
	public String getCase_num() {
		return case_num;
	}
	public void setCase_num(String case_num) {
		this.case_num = case_num;
	}
	public String getCase_time() {
		return case_time;
	}
	public void setCase_time(String case_time) {
		this.case_time = case_time;
	}

}
