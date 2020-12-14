package com.changjianghuyu.qixia.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表(HyUser)实体类
 *
 * @author huang_kewang
 * @since 2020-11-14 14:25:50
 */
public class HyUser implements Serializable {
    private static final long serialVersionUID = -76991390309102074L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户类型：1 系统管理员  3：管理员  5:普通用户
     */
    private Integer userType;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 微信号
     */
    private String weichatNumber;
    /**
     * qq号码
     */
    private String qqNumber;
    /**
     * 职业
     */
    private String profession;
    /**
     * 头像
     */
    private String image;
    /**
     * 年龄
     */
    private Integer age;
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
     * 0：不删除  1：删除
     */
    private Integer isDelete;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    /**
     * 密码
     */
    private String password;


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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getWeichatNumber() {
        return weichatNumber;
    }

    public void setWeichatNumber(String weichatNumber) {
        this.weichatNumber = weichatNumber;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HyUser() {
    }

    public HyUser(Long id, String userName, String nickname, Integer userType, String phone, String weichatNumber, String qqNumber, String profession, String image, Integer age, String street, String village, Long streetId, Long villageId, Integer isDelete, Date createTime, String password) {
        this.id = id;
        this.userName = userName;
        this.nickname = nickname;
        this.userType = userType;
        this.phone = phone;
        this.weichatNumber = weichatNumber;
        this.qqNumber = qqNumber;
        this.profession = profession;
        this.image = image;
        this.age = age;
        this.street = street;
        this.village = village;
        this.streetId = streetId;
        this.villageId = villageId;
        this.isDelete = isDelete;
        this.createTime = createTime;
        this.password = password;
    }
}