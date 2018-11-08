package com.yaozhitech.baobei.child.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "child_archives")
public class ChildArchives implements Serializable {
    /**
     * 儿童档案表唯一性id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别 0-女，1-男
     */
    private Integer sex;

    /**
     * 身高
     */
    private Integer height;

    /**
     * 证件类型   0(默认)-身份证，1-护照，2-军官证，3-港澳通行证，4台胞证 , 5其他
     */
    @Column(name = "card_type")
    private Integer cardType;

    /**
     * 证件号
     */
    private String idcard;

    /**
     * 出生年月日
     */
    private Date birthday;

    /**
     * 护照号
     */
    @Column(name = "passport_no")
    private String passportNo;

    /**
     * 勋章
     */
    private Integer medal;

    /**
     * 印章,每次购买活动成功+1,退款成功-1
     */
    private Integer seal;

    /**
     * 黄金印章
     */
    @Column(name = "gold_seal")
    private Integer goldSeal;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 性格
     */
    private String nature;

    /**
     * 性格其他补充
     */
    @Column(name = "nature_remark")
    private String natureRemark;

    /**
     * 兴趣
     */
    private String interest;

    /**
     * 兴趣其他补充
     */
    @Column(name = "interest_remark")
    private String interestRemark;

    /**
     * 家境
     */
    private String family;

    /**
     * 家境其他补充
     */
    @Column(name = "family_remark")
    private String familyRemark;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 学校
     */
    private String school;

    /**
     * 年级
     */
    private String grade;

    /**
     * 家长的手机号 多个用","隔开
     */
    private String phone;

    /**
     * 家长信息jsonArr,eg:[{uid:"",name:"",phone:"",idcard:""},{...}]
     */
    private String parents;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态：0(默认)-正常，1-删除
     */
    private Integer status;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "upd_time")
    private Date updTime;

    /**
     * 儿童头像
     */
    private String avatar;

    /**
     * 证件资料图片,多张用逗号隔开
     */
    private String pics;

    /**
     * 培训机构
     */
    private String organ;

    /**
     * 上次生日短信发送日期
     */
    @Column(name = "birthday_sms")
    private Date birthdaySms;

    /**
     * 体重/kg
     */
    private BigDecimal weight;

    /**
     * 是否是刚粉  0(默认)-不是   1-是
     */
    private Integer fans;

    /**
     * 护照姓名
     */
    @Column(name = "passport_name")
    private String passportName;

    /**
     * 行前信息补充，json对象
     */
    private String otherinfo;

    /**
     * 子证件：合并到本记录的儿童（格式：证件号-证件类型，多个儿童时以逗号隔开）
     */
    @Column(name = "child_cardinfo")
    private String childCardinfo;

    /**
     * 体型：0 偏瘦  1 正常  2 偏胖
     */
    @Column(name = "body_type")
    private Integer bodyType;

    /**
     * 消费金额
     */
    private BigDecimal price;

    /**
     * 是否黑名单 0(默认)-不是   1-是
     */
    private Integer disabled;

    private static final long serialVersionUID = 1L;

    /**
     * 获取儿童档案表唯一性id
     *
     * @return id - 儿童档案表唯一性id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置儿童档案表唯一性id
     *
     * @param id 儿童档案表唯一性id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别 0-女，1-男
     *
     * @return sex - 性别 0-女，1-男
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别 0-女，1-男
     *
     * @param sex 性别 0-女，1-男
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取身高
     *
     * @return height - 身高
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 设置身高
     *
     * @param height 身高
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 获取证件类型   0(默认)-身份证，1-护照，2-军官证，3-港澳通行证，4台胞证 , 5其他
     *
     * @return card_type - 证件类型   0(默认)-身份证，1-护照，2-军官证，3-港澳通行证，4台胞证 , 5其他
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * 设置证件类型   0(默认)-身份证，1-护照，2-军官证，3-港澳通行证，4台胞证 , 5其他
     *
     * @param cardType 证件类型   0(默认)-身份证，1-护照，2-军官证，3-港澳通行证，4台胞证 , 5其他
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * 获取证件号
     *
     * @return idcard - 证件号
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 设置证件号
     *
     * @param idcard 证件号
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * 获取出生年月日
     *
     * @return birthday - 出生年月日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生年月日
     *
     * @param birthday 出生年月日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取护照号
     *
     * @return passport_no - 护照号
     */
    public String getPassportNo() {
        return passportNo;
    }

    /**
     * 设置护照号
     *
     * @param passportNo 护照号
     */
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    /**
     * 获取勋章
     *
     * @return medal - 勋章
     */
    public Integer getMedal() {
        return medal;
    }

    /**
     * 设置勋章
     *
     * @param medal 勋章
     */
    public void setMedal(Integer medal) {
        this.medal = medal;
    }

    /**
     * 获取印章,每次购买活动成功+1,退款成功-1
     *
     * @return seal - 印章,每次购买活动成功+1,退款成功-1
     */
    public Integer getSeal() {
        return seal;
    }

    /**
     * 设置印章,每次购买活动成功+1,退款成功-1
     *
     * @param seal 印章,每次购买活动成功+1,退款成功-1
     */
    public void setSeal(Integer seal) {
        this.seal = seal;
    }

    /**
     * 获取黄金印章
     *
     * @return gold_seal - 黄金印章
     */
    public Integer getGoldSeal() {
        return goldSeal;
    }

    /**
     * 设置黄金印章
     *
     * @param goldSeal 黄金印章
     */
    public void setGoldSeal(Integer goldSeal) {
        this.goldSeal = goldSeal;
    }

    /**
     * 获取所在城市
     *
     * @return city - 所在城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置所在城市
     *
     * @param city 所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取性格
     *
     * @return nature - 性格
     */
    public String getNature() {
        return nature;
    }

    /**
     * 设置性格
     *
     * @param nature 性格
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * 获取性格其他补充
     *
     * @return nature_remark - 性格其他补充
     */
    public String getNatureRemark() {
        return natureRemark;
    }

    /**
     * 设置性格其他补充
     *
     * @param natureRemark 性格其他补充
     */
    public void setNatureRemark(String natureRemark) {
        this.natureRemark = natureRemark;
    }

    /**
     * 获取兴趣
     *
     * @return interest - 兴趣
     */
    public String getInterest() {
        return interest;
    }

    /**
     * 设置兴趣
     *
     * @param interest 兴趣
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

    /**
     * 获取兴趣其他补充
     *
     * @return interest_remark - 兴趣其他补充
     */
    public String getInterestRemark() {
        return interestRemark;
    }

    /**
     * 设置兴趣其他补充
     *
     * @param interestRemark 兴趣其他补充
     */
    public void setInterestRemark(String interestRemark) {
        this.interestRemark = interestRemark;
    }

    /**
     * 获取家境
     *
     * @return family - 家境
     */
    public String getFamily() {
        return family;
    }

    /**
     * 设置家境
     *
     * @param family 家境
     */
    public void setFamily(String family) {
        this.family = family;
    }

    /**
     * 获取家境其他补充
     *
     * @return family_remark - 家境其他补充
     */
    public String getFamilyRemark() {
        return familyRemark;
    }

    /**
     * 设置家境其他补充
     *
     * @param familyRemark 家境其他补充
     */
    public void setFamilyRemark(String familyRemark) {
        this.familyRemark = familyRemark;
    }

    /**
     * 获取家庭住址
     *
     * @return address - 家庭住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置家庭住址
     *
     * @param address 家庭住址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取学校
     *
     * @return school - 学校
     */
    public String getSchool() {
        return school;
    }

    /**
     * 设置学校
     *
     * @param school 学校
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * 获取年级
     *
     * @return grade - 年级
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置年级
     *
     * @param grade 年级
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取家长的手机号 多个用","隔开
     *
     * @return phone - 家长的手机号 多个用","隔开
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置家长的手机号 多个用","隔开
     *
     * @param phone 家长的手机号 多个用","隔开
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取家长信息jsonArr,eg:[{uid:"",name:"",phone:"",idcard:""},{...}]
     *
     * @return parents - 家长信息jsonArr,eg:[{uid:"",name:"",phone:"",idcard:""},{...}]
     */
    public String getParents() {
        return parents;
    }

    /**
     * 设置家长信息jsonArr,eg:[{uid:"",name:"",phone:"",idcard:""},{...}]
     *
     * @param parents 家长信息jsonArr,eg:[{uid:"",name:"",phone:"",idcard:""},{...}]
     */
    public void setParents(String parents) {
        this.parents = parents;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取状态：0(默认)-正常，1-删除
     *
     * @return status - 状态：0(默认)-正常，1-删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0(默认)-正常，1-删除
     *
     * @param status 状态：0(默认)-正常，1-删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return crt_time
     */
    public Date getCrtTime() {
        return crtTime;
    }

    /**
     * @param crtTime
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * @return upd_time
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * @param updTime
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /**
     * 获取儿童头像
     *
     * @return avatar - 儿童头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置儿童头像
     *
     * @param avatar 儿童头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取证件资料图片,多张用逗号隔开
     *
     * @return pics - 证件资料图片,多张用逗号隔开
     */
    public String getPics() {
        return pics;
    }

    /**
     * 设置证件资料图片,多张用逗号隔开
     *
     * @param pics 证件资料图片,多张用逗号隔开
     */
    public void setPics(String pics) {
        this.pics = pics;
    }

    /**
     * 获取培训机构
     *
     * @return organ - 培训机构
     */
    public String getOrgan() {
        return organ;
    }

    /**
     * 设置培训机构
     *
     * @param organ 培训机构
     */
    public void setOrgan(String organ) {
        this.organ = organ;
    }

    /**
     * 获取上次生日短信发送日期
     *
     * @return birthday_sms - 上次生日短信发送日期
     */
    public Date getBirthdaySms() {
        return birthdaySms;
    }

    /**
     * 设置上次生日短信发送日期
     *
     * @param birthdaySms 上次生日短信发送日期
     */
    public void setBirthdaySms(Date birthdaySms) {
        this.birthdaySms = birthdaySms;
    }

    /**
     * 获取体重/kg
     *
     * @return weight - 体重/kg
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * 设置体重/kg
     *
     * @param weight 体重/kg
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * 获取是否是刚粉  0(默认)-不是   1-是
     *
     * @return fans - 是否是刚粉  0(默认)-不是   1-是
     */
    public Integer getFans() {
        return fans;
    }

    /**
     * 设置是否是刚粉  0(默认)-不是   1-是
     *
     * @param fans 是否是刚粉  0(默认)-不是   1-是
     */
    public void setFans(Integer fans) {
        this.fans = fans;
    }

    /**
     * 获取护照姓名
     *
     * @return passport_name - 护照姓名
     */
    public String getPassportName() {
        return passportName;
    }

    /**
     * 设置护照姓名
     *
     * @param passportName 护照姓名
     */
    public void setPassportName(String passportName) {
        this.passportName = passportName;
    }

    /**
     * 获取行前信息补充，json对象
     *
     * @return otherinfo - 行前信息补充，json对象
     */
    public String getOtherinfo() {
        return otherinfo;
    }

    /**
     * 设置行前信息补充，json对象
     *
     * @param otherinfo 行前信息补充，json对象
     */
    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }

    /**
     * 获取子证件：合并到本记录的儿童（格式：证件号-证件类型，多个儿童时以逗号隔开）
     *
     * @return child_cardinfo - 子证件：合并到本记录的儿童（格式：证件号-证件类型，多个儿童时以逗号隔开）
     */
    public String getChildCardinfo() {
        return childCardinfo;
    }

    /**
     * 设置子证件：合并到本记录的儿童（格式：证件号-证件类型，多个儿童时以逗号隔开）
     *
     * @param childCardinfo 子证件：合并到本记录的儿童（格式：证件号-证件类型，多个儿童时以逗号隔开）
     */
    public void setChildCardinfo(String childCardinfo) {
        this.childCardinfo = childCardinfo;
    }

    /**
     * 获取体型：0 偏瘦  1 正常  2 偏胖
     *
     * @return body_type - 体型：0 偏瘦  1 正常  2 偏胖
     */
    public Integer getBodyType() {
        return bodyType;
    }

    /**
     * 设置体型：0 偏瘦  1 正常  2 偏胖
     *
     * @param bodyType 体型：0 偏瘦  1 正常  2 偏胖
     */
    public void setBodyType(Integer bodyType) {
        this.bodyType = bodyType;
    }

    /**
     * 获取消费金额
     *
     * @return price - 消费金额
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置消费金额
     *
     * @param price 消费金额
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取是否黑名单 0(默认)-不是   1-是
     *
     * @return disabled - 是否黑名单 0(默认)-不是   1-是
     */
    public Integer getDisabled() {
        return disabled;
    }

    /**
     * 设置是否黑名单 0(默认)-不是   1-是
     *
     * @param disabled 是否黑名单 0(默认)-不是   1-是
     */
    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }
}