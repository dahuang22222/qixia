package com.changjianghuyu.qixia.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * 用户打卡统计表(HyUserPunchClock)实体类
 *
 * @author huang_kewang
 * @since 2020-11-26 10:37:28
 */
public class HyUserPunchClock implements Serializable {
    private static final long serialVersionUID = -60644315204979519L;
    /**
     * 打卡地点id
     */
    private Long id;
    /**
     * 设定的打卡的经度
     */
    private BigDecimal clockLongitude;
    /**
     * 设定的打卡的纬度
     */
    private BigDecimal clockLatitude;
    /**
     * 设定的打卡开始时间
     */
    @JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
    private Time beginTime;
    /**
     * 设定的打卡结束时间
     */
    @JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
    private Time endTime;
    /**
     * 打开的状态：0：待打卡 1：正常打卡  2：异常打卡  9：其他错误
     */
    private Integer clockStatus;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 所属街道id
     */
    private Long streetId;
    /**
     * 村id
     */
    private Long villageId;
    /**
     * 打卡表主键id
     */
    private Long punchClockId;
    /**
     * 地址表主键
     */
    private Long punchClockLocationId;
    /**
     * 时间表主键
     */
    private Long punchClockTimeId;
    /**
     * 0:未删除 1：删除
     */
    private Integer isDelete;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


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

    public Long getPunchClockId() {
        return punchClockId;
    }

    public void setPunchClockId(Long punchClockId) {
        this.punchClockId = punchClockId;
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