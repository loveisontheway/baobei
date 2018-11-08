package com.yaozhitech.baobei.child.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "operation_refund")
public class OperationRefund implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商户订单号（ out_trade_no ），可多个
     */
    private String orderid;

    /**
     * 订单主键，唯一
     */
    private String oid;

    /**
     * 模块名称
     */
    private String module;

    /**
     * 子模块名称
     */
    @Column(name = "child_module")
    private String childModule;

    /**
     * 名称
     */
    private String name;

    /**
     * 退款方式: 0支付宝 1微信遥指 2微信沐童
     */
    private Short manner;

    /**
     * 订单支付金额
     */
    @Column(name = "total_fee")
    private BigDecimal totalFee;

    /**
     * 退款金额
     */
    @Column(name = "refund_amount")
    private BigDecimal refundAmount;

    /**
     * 退款状态: 0成功 1失败
     */
    private Short status;

    /**
     * 系统内部的退款单号，唯一，用于一单多退
     */
    @Column(name = "out_refund_no")
    private String outRefundNo;

    /**
     * 退款原因说明
     */
    @Column(name = "refund_reason")
    private String refundReason;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作人id
     */
    @Column(name = "operator_id")
    private String operatorId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

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
     * 获取商户订单号（ out_trade_no ），可多个
     *
     * @return orderid - 商户订单号（ out_trade_no ），可多个
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * 设置商户订单号（ out_trade_no ），可多个
     *
     * @param orderid 商户订单号（ out_trade_no ），可多个
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * 获取订单主键，唯一
     *
     * @return oid - 订单主键，唯一
     */
    public String getOid() {
        return oid;
    }

    /**
     * 设置订单主键，唯一
     *
     * @param oid 订单主键，唯一
     */
    public void setOid(String oid) {
        this.oid = oid;
    }

    /**
     * 获取模块名称
     *
     * @return module - 模块名称
     */
    public String getModule() {
        return module;
    }

    /**
     * 设置模块名称
     *
     * @param module 模块名称
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * 获取子模块名称
     *
     * @return child_module - 子模块名称
     */
    public String getChildModule() {
        return childModule;
    }

    /**
     * 设置子模块名称
     *
     * @param childModule 子模块名称
     */
    public void setChildModule(String childModule) {
        this.childModule = childModule;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取退款方式: 0支付宝 1微信遥指 2微信沐童
     *
     * @return manner - 退款方式: 0支付宝 1微信遥指 2微信沐童
     */
    public Short getManner() {
        return manner;
    }

    /**
     * 设置退款方式: 0支付宝 1微信遥指 2微信沐童
     *
     * @param manner 退款方式: 0支付宝 1微信遥指 2微信沐童
     */
    public void setManner(Short manner) {
        this.manner = manner;
    }

    /**
     * 获取订单支付金额
     *
     * @return total_fee - 订单支付金额
     */
    public BigDecimal getTotalFee() {
        return totalFee;
    }

    /**
     * 设置订单支付金额
     *
     * @param totalFee 订单支付金额
     */
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * 获取退款金额
     *
     * @return refund_amount - 退款金额
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * 设置退款金额
     *
     * @param refundAmount 退款金额
     */
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 获取退款状态: 0成功 1失败
     *
     * @return status - 退款状态: 0成功 1失败
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置退款状态: 0成功 1失败
     *
     * @param status 退款状态: 0成功 1失败
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取系统内部的退款单号，唯一，用于一单多退
     *
     * @return out_refund_no - 系统内部的退款单号，唯一，用于一单多退
     */
    public String getOutRefundNo() {
        return outRefundNo;
    }

    /**
     * 设置系统内部的退款单号，唯一，用于一单多退
     *
     * @param outRefundNo 系统内部的退款单号，唯一，用于一单多退
     */
    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    /**
     * 获取退款原因说明
     *
     * @return refund_reason - 退款原因说明
     */
    public String getRefundReason() {
        return refundReason;
    }

    /**
     * 设置退款原因说明
     *
     * @param refundReason 退款原因说明
     */
    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    /**
     * 获取IP地址
     *
     * @return ip - IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP地址
     *
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
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
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取操作人id
     *
     * @return operator_id - 操作人id
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * 设置操作人id
     *
     * @param operatorId 操作人id
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}