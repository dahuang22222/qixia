package com.changjianghuyu.qixia.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 地区表(SysArea)实体类
 *
 * @author huang_kewang
 * @since 2020-11-12 15:17:16
 */
public class SysArea implements Serializable {
    private static final long serialVersionUID = -70658754652564082L;
    /**
     * 地区id
     */
    private Long id;
    /**
     * 父类id
     */
    private String parentId;
    /**
     * 地区编码
     */
    private String areaCode;
    /**
     * 地区名称
     */
    private String areaName;
    /**
     * 排列顺序越小越优先
     */
    private Integer dispOrder;
    /**
     * 地区级别 ： 9：村  8：街道
     */
    private Integer level;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    /**
     * 是否删除：0 不删除 1：删除
     */
    private Integer isDelete;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getDispOrder() {
        return dispOrder;
    }

    public void setDispOrder(Integer dispOrder) {
        this.dispOrder = dispOrder;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}