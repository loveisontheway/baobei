package com.yaozhitech.baobei.child.domain;

import java.io.Serializable;

/**
 * 领队反馈投诉情况
 * @author cong
 *
 */
public class LeaderFeedbackComplaint implements Serializable{
	private static final long serialVersionUID = 1136791112993973759L;
	/* 主键  */
	private Integer id;
	/* 反馈表id  */
	private Integer fid;
	/* 儿童证件姓名  */
	private String name;
	/* 投诉情况描述  */
	private String descr;
	/* 相关图片，多张逗号隔开  */
	private String pic;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
