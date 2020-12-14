package com.changjianghuyu.qixia.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * 打卡时间表(HyPunchClockTime)实体类
 *
 * @author huang_kewang
 * @since 2020-11-12 15:07:36
 */
public class HyPunchClockTime implements Serializable {
    private static final long serialVersionUID = 769371523383429887L;
    /**
     * 打卡地点id
     */
    private Long id;
    /**
     * 设定的打卡地点id
     */
    private Long punchClockLocationId;
    /**
     * 打卡开始时间
     */
    @JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
    private Time beginTime;
    /**
     * 打卡结束时间
     */
    @JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
    private Time endTime;
    /**
     * 打卡的半径
     */
    private BigDecimal clockR;
    /**
     * 所属街道id
     */
    private Long streetId;
    /**
     * 所属街道名称
     */
    private String street;
    /**
     * 村id
     */
    private Long villageId;
    /**
     * 村子名称
     */
    private String village;
    /**
     * 0:未删除 1：删除
     */
    private Integer isDelete;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPunchClockLocationId() {
        return punchClockLocationId;
    }

    public void setPunchClockLocationId(Long punchClockLocationId) {
        this.punchClockLocationId = punchClockLocationId;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getClockR() {
        return clockR;
    }

    public void setClockR(BigDecimal clockR) {
        this.clockR = clockR;
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

}