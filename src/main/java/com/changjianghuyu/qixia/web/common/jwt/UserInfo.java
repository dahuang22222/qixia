package com.changjianghuyu.qixia.web.common.jwt;

import com.changjianghuyu.qixia.web.entity.HyUser;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserInfo {
    private static final long serialVersionUID = -769913908254202074L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户类型：1 系统管理员  3：管理员  5:普通用户
     */
    private Integer userType;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 街道
     */
    private String street;
    /**
     * 村
     */
    private String village;
    /**
     * 街道id
     */
    private Long streetId;
    /**
     * 村id
     */
    private Long villageId;
    /**
     * 用户对象
     */
    private HyUser userInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public HyUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(HyUser userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo(Long id, String userName, Integer userType, String phone, String street, String village, Long streetId, Long villageId, HyUser userInfo) {
        this.id = id;
        this.userName = userName;
        this.userType = userType;
        this.phone = phone;
        this.street = street;
        this.village = village;
        this.streetId = streetId;
        this.villageId = villageId;
        this.userInfo = userInfo;
    }

    public UserInfo() {
    }
}
