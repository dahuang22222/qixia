package com.changjianghuyu.qixia.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 信息反馈表(HyInformationFeedback)实体类
 *
 * @author huang_kewang
 * @since 2020-11-11 15:10:54
 */
public class HyInformationFeedbackExpand implements Serializable {
    private static final long serialVersionUID = 322849371045719506L;
    /**
     * 信息反馈表的主键id
     */
    private Long id;
    /**
     * 信息提供者id
     */
    private Long provideUserId;
    /**
     * 信息提供者姓名
     */
    private String provideName;
    /**
     * 信息反馈时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date provideTime;
    /**
     * 信息内容
     */
    private String content;
    /**
     * 图片地址
     */
    private String images;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 街道id
     */
    private Long streetId;
    /**
     * 街道名称
     */
    private String street;
    /**
     * 村id
     */
    private Long villageId;
    /**
     * 村名称
     */
    private String village;
    /**
     * 处理意见
     */
    private String handlingOpinions;
    /**
     * 处理人员id
     */
    private Long handlingUserId;
    /**
     * 处理人员名称
     */
    private String handlingUserName;
    /**
     * 处理时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date handlingTime;
    /**
     * 报告的经度
     */
    private BigDecimal feedbackLongitude;
    /**
     * 报告的纬度
     */
    private BigDecimal feedbackLatitude;
    /**
     * 反馈的地址名称
     */
    private String feedbackAddress;
    /**
     * 信息的状态：1： 反馈中  2：处理中 3：已处理
     */
    private Integer status;
    /**
     * 0：未删除 1：删除
     */
    private Integer isDelete;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date beginTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProvideUserId() {
        return provideUserId;
    }

    public void setProvideUserId(Long provideUserId) {
        this.provideUserId = provideUserId;
    }

    public String getProvideName() {
        return provideName;
    }

    public void setProvideName(String provideName) {
        this.provideName = provideName;
    }

    public Date getProvideTime() {
        return provideTime;
    }

    public void setProvideTime(Date provideTime) {
        this.provideTime = provideTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getHandlingOpinions() {
        return handlingOpinions;
    }

    public void setHandlingOpinions(String handlingOpinions) {
        this.handlingOpinions = handlingOpinions;
    }

    public Long getHandlingUserId() {
        return handlingUserId;
    }

    public void setHandlingUserId(Long handlingUserId) {
        this.handlingUserId = handlingUserId;
    }

    public String getHandlingUserName() {
        return handlingUserName;
    }

    public void setHandlingUserName(String handlingUserName) {
        this.handlingUserName = handlingUserName;
    }

    public Date getHandlingTime() {
        return handlingTime;
    }

    public void setHandlingTime(Date handlingTime) {
        this.handlingTime = handlingTime;
    }

    public BigDecimal getFeedbackLongitude() {
        return feedbackLongitude;
    }

    public void setFeedbackLongitude(BigDecimal feedbackLongitude) {
        this.feedbackLongitude = feedbackLongitude;
    }

    public BigDecimal getFeedbackLatitude() {
        return feedbackLatitude;
    }

    public void setFeedbackLatitude(BigDecimal feedbackLatitude) {
        this.feedbackLatitude = feedbackLatitude;
    }

    public String getFeedbackAddress() {
        return feedbackAddress;
    }

    public void setFeedbackAddress(String feedbackAddress) {
        this.feedbackAddress = feedbackAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}