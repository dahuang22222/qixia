package com.changjianghuyu.qixia.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户打卡表(HyPunchClock)实体类
 *
 * @author huang_kewang
 * @since 2020-11-26 14:42:53
 */
public class HyPunchClock implements Serializable {
    private static final long serialVersionUID = 521905317567173941L;
    /**
     * 打卡地点id
     */
    private Long id;
    /**
     * 打卡的经度
     */
    private BigDecimal clockLongitude;
    /**
     * 打卡的纬度
     */
    private BigDecimal clockLatitude;
    /**
     * 打卡地点名称
     */
    private String clockAddressName;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 打卡时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date clockTime;
    /**
     * 打开的状态：1：正常 2：地理位置错误 3;打卡时间错误  4：地点时间都不正确  9：其他错误
     */
    private Integer clockStatus;
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
    /**
     * 打卡地址表主键id
     */
    private Long punchClockLocationId;
    /**
     * 打卡时间表主键id
     */
    private Long punchClockTimeId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getClockLongitude() {
        return clockLongitude;
    }

    public void setClockLongitude(BigDecimal clockLongitude) {
        this.clockLongitude = clockLongitude;
    }

    public BigDecimal getClockLatitude() {
        return clockLatitude;
    }

    public void setClockLatitude(BigDecimal clockLatitude) {
        this.clockLatitude = clockLatitude;
    }

    public String getClockAddressName() {
        return clockAddressName;
    }

    public void setClockAddressName(String clockAddressName) {
        this.clockAddressName = clockAddressName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getClockTime() {
        return clockTime;
    }

    public void setClockTime(Date clockTime) {
        this.clockTime = clockTime;
    }

    public Integer getClockStatus() {
        return clockStatus;
    }

    public void setClockStatus(Integer clockStatus) {
        this.clockStatus = clockStatus;
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

    public Long getPunchClockLocationId() {
        return punchClockLocationId;
    }

    public void setPunchClockLocationId(Long punchClockLocationId) {
        this.punchClockLocationId = punchClockLocationId;
    }

    public Long getPunchClockTimeId() {
        return punchClockTimeId;
    }

    public void setPunchClockTimeId(Long punchClockTimeId) {
        this.punchClockTimeId = punchClockTimeId;
    }

}