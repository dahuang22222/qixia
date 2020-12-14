package com.changjianghuyu.qixia.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色地区表(HyUserArea)实体类
 *
 * @author huang_kewang
 * @since 2020-11-11 16:24:56
 */
public class HyUserArea implements Serializable {
    private static final long serialVersionUID = -14267681752427255L;
    /**
     * 用户地区id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 地区id
     */
    private Long areaId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}