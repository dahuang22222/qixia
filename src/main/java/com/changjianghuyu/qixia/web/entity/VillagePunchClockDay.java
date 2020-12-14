package com.changjianghuyu.qixia.web.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * 村打卡统计(VillagePunchClockDay)实体类
 *
 * @author huang_kewang
 * @since 2020-11-19 16:49:24
 */
public class VillagePunchClockDay implements Serializable {
    private static final long serialVersionUID = -49484800335296408L;
    /**
     * 打卡地点id
     */
    private Long id;
    /**
     * 村id
     */
    private Long villageId;
    /**
     * 村子名称
     */
    private String village;
    /**
     * 所属街道id
     */
    private Long streetId;
    /**
     * 所属街道名称
     */
    private String street;
    /**
     * 打卡时间
     */
    private LocalDate clockDate;
    /**
     * 打开的状态：1：合格 2：不及格
     */
    private Integer clockStatus;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 0:未删除 1：删除
     */
    private Integer isDelete;
    /**
     * 创建时间
     */
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getClockDate() {
        return clockDate;
    }

    public void setClockDate(LocalDate clockDate) {
        this.clockDate = clockDate;
    }

    public Integer getClockStatus() {
        return clockStatus;
    }

    public void setClockStatus(Integer clockStatus) {
        this.clockStatus = clockStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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