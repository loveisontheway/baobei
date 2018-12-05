package com.yaozhitech.baobei.child.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserArea implements Serializable{
	
	private static final long serialVersionUID = -6565455041950453045L;
	
	private String uid;
	private int areaId;
	private String title;
	private Timestamp crtTime;
	private Timestamp updTime;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public Timestamp getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(Timestamp crtTime) {
		this.crtTime = crtTime;
	}
	public Timestamp getUpdTime() {
		return updTime;
	}
	public void setUpdTime(Timestamp updTime) {
		this.updTime = updTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return areaId+"::"+title;
	}
}
