package com.changjianghuyu.qixia.web.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PunchClockInfoPojo {
    private static final long serialVersionUID = 963485371045795534L;

    /**
     * 打卡的地点名称
     */
    private String clockAddressName;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 打卡点名
     */
    private String addressName;
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
     * 所属街道名称
     */
    private String street;
    /**
     * 村子名称
     */
    private String village;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}
