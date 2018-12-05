package com.yaozhitech.baobei.child.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class LeaderFeedback implements Serializable {
	private static final long serialVersionUID = -554549876265519953L;

	/**
     * 主键
     */
    private Integer id;
    /**
     * 活动id
     */
    private String aid;
    /**
     * 场次id
     */
    private Integer sid;
    /**
     * 活动签到表-图片（单张）
     */
    private String signinImg;
    
    /**
     * 领队名称
     * */
    private String leaderName;

    /**
     * 领队大合照-图片（单张）
     */
    private String leaderImg;

    /**
     * 儿童及领队保单-图片（单张）
     */
    private String childLeaderInsuranceImg;

    /**
     * 活动白皮书-图片（最多四张）
     */
    private String whiteBookImg;

    /**
     * 喔图直播：0-否  1-是
     */
    private Integer alltuu;

    /**
     * 海报发送到直播群： 0-否  1-是
     */
    private Integer poster;

    /**
     * 是否已上传大巴车资质到网盘（无、91大巴、其它）
     */
    private String remark;

    /**
     * 大巴车资质上传网盘的截图-图片（单张）
     */
    private String busImg;

    /**
     * 状态： 0正常  1删除
     */
    private Integer status;

    /**
     * 操作人id（用户id）
     */
    private String uid;

    /**
     * 操作人名称（用户名）
     */
    private String nickName;

    /**
     * 创建时间
     */
    private Timestamp crtTime;

    /**
     * 更新时间
     */
    private Timestamp updTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取活动id
     *
     * @return aid - 活动id
     */
    public String getAid() {
        return aid;
    }

    /**
     * 设置活动id
     *
     * @param aid 活动id
     */
    public void setAid(String aid) {
        this.aid = aid;
    }

    /**
     * 获取场次id
     *
     * @return sid - 场次id
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * 设置场次id
     *
     * @param sid 场次id
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 获取活动签到表-图片（单张）
     *
     * @return signin_img - 活动签到表-图片（单张）
     */
    public String getSigninImg() {
        return signinImg;
    }

    /**
     * 设置活动签到表-图片（单张）
     *
     * @param signinImg 活动签到表-图片（单张）
     */
    public void setSigninImg(String signinImg) {
        this.signinImg = signinImg;
    }

    /**
     * 获取领队大合照-图片（单张）
     *
     * @return leader_img - 领队大合照-图片（单张）
     */
    public String getLeaderImg() {
        return leaderImg;
    }

    /**
     * 设置领队大合照-图片（单张）
     *
     * @param leaderImg 领队大合照-图片（单张）
     */
    public void setLeaderImg(String leaderImg) {
        this.leaderImg = leaderImg;
    }

    /**
     * 获取儿童及领队保单-图片（单张）
     *
     * @return child_leader_insurance_img - 儿童及领队保单-图片（单张）
     */
    public String getChildLeaderInsuranceImg() {
        return childLeaderInsuranceImg;
    }

    /**
     * 设置儿童及领队保单-图片（单张）
     *
     * @param childLeaderInsuranceImg 儿童及领队保单-图片（单张）
     */
    public void setChildLeaderInsuranceImg(String childLeaderInsuranceImg) {
        this.childLeaderInsuranceImg = childLeaderInsuranceImg;
    }

    /**
     * 获取活动白皮书-图片（最多四张）
     *
     * @return white_book_img - 活动白皮书-图片（最多四张）
     */
    public String getWhiteBookImg() {
        return whiteBookImg;
    }

    /**
     * 设置活动白皮书-图片（最多四张）
     *
     * @param whiteBookImg 活动白皮书-图片（最多四张）
     */
    public void setWhiteBookImg(String whiteBookImg) {
        this.whiteBookImg = whiteBookImg;
    }

    /**
     * 获取喔图直播：0是  1否
     *
     * @return alltuu - 喔图直播：0是  1否
     */
    public Integer getAlltuu() {
        return alltuu;
    }

    /**
     * 设置喔图直播：0是  1否
     *
     * @param alltuu 喔图直播：0是  1否
     */
    public void setAlltuu(Integer alltuu) {
        this.alltuu = alltuu;
    }

    /**
     * 获取海报发送到直播群： 0是  1否
     *
     * @return poster - 海报发送到直播群： 0是  1否
     */
    public Integer getPoster() {
        return poster;
    }

    /**
     * 设置海报发送到直播群： 0是  1否
     *
     * @param poster 海报发送到直播群： 0是  1否
     */
    public void setPoster(Integer poster) {
        this.poster = poster;
    }

    /**
     * 获取是否已上传大巴车资质到网盘（无、91大巴、其它）
     *
     * @return remark - 是否已上传大巴车资质到网盘（无、91大巴、其它）
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置是否已上传大巴车资质到网盘（无、91大巴、其它）
     *
     * @param remark 是否已上传大巴车资质到网盘（无、91大巴、其它）
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取大巴车资质上传网盘的截图-图片（单张）
     *
     * @return bus_img - 大巴车资质上传网盘的截图-图片（单张）
     */
    public String getBusImg() {
        return busImg;
    }

    /**
     * 设置大巴车资质上传网盘的截图-图片（单张）
     *
     * @param busImg 大巴车资质上传网盘的截图-图片（单张）
     */
    public void setBusImg(String busImg) {
        this.busImg = busImg;
    }

    /**
     * 获取状态： 0正常  1删除
     *
     * @return status - 状态： 0正常  1删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态： 0正常  1删除
     *
     * @param status 状态： 0正常  1删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取操作人id（用户id）
     *
     * @return uid - 操作人id（用户id）
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置操作人id（用户id）
     *
     * @param uid 操作人id（用户id）
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取操作人名称（用户名）
     *
     * @return nick_name - 操作人名称（用户名）
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置操作人名称（用户名）
     *
     * @param nickName 操作人名称（用户名）
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取创建时间
     *
     * @return crt_time - 创建时间
     */
    public Timestamp getCrtTime() {
        return crtTime;
    }

    /**
     * 设置创建时间
     *
     * @param crtTime 创建时间
     */
    public void setCrtTime(Timestamp crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * 获取更新时间
     *
     * @return upd_time - 更新时间
     */
    public Timestamp getUpdTime() {
        return updTime;
    }

    /**
     * 设置更新时间
     *
     * @param updTime 更新时间
     */
    public void setUpdTime(Timestamp updTime) {
        this.updTime = updTime;
    }

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
}