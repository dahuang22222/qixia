package com.changjianghuyu.qixia.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * 设定的打卡地点表(HyPunchClockLocation)实体类
 *
 * @author huang_kewang
 * @since 2020-11-11 10:55:34
 */
public class HyPunchClockLocation implements Serializable {
    private static final long serialVersionUID = -31543985608392792L;
    /**
     * 打卡地点id
     */
    private Long id;
    /**
     * 地址名称
     */
    private String addressName;
    /**
     * 打卡地点的经度
     */
    private BigDecimal clockLongitude;
    /**
     * 打卡地点的纬度
     */
    private BigDecimal clockLatitude;
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
     * 排列顺序：越小优先级越高
     */
    private Integer dispOrder;
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

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
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

    public Integer getDispOrder() {
        return dispOrder;
    }

    public void setDispOrder(Integer dispOrder) {
        this.dispOrder = dispOrder;
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