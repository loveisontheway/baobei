package com.yaozhitech.baobei.child.dto;

import com.yaozhitech.baobei.child.domain.LeaderFeedback;
import com.yaozhitech.baobei.child.domain.LeaderFeedbackComplaint;

import java.util.List;

public class LeaderFeedbackDTO extends LeaderFeedback {

	private String title;	// 活动标题
	private String sname;	// 场次名
	private Integer num;	// 事故投诉
	private String cities;	// 城市
	private List<LeaderFeedbackComplaint> complaintList;	// 事故投诉列表

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getCities() {
		return cities;
	}

	public void setCities(String cities) {
		this.cities = cities;
	}

	public List<LeaderFeedbackComplaint> getComplaintList() {
		return complaintList;
	}

	public void setComplaintList(List<LeaderFeedbackComplaint> complaintList) {
		this.complaintList = complaintList;
	}
}
