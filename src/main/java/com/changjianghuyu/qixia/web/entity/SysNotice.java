package com.changjianghuyu.qixia.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统公告表(SysNotice)实体类
 *
 * @author huang_kewang
 * @since 2020-11-10 18:34:17
 */
public class SysNotice implements Serializable {
    private static final long serialVersionUID = -41712011090809664L;
    /**
     * 系统公告表主键id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 图片地址
     */
    private String images;
    /**
     * 消息开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date beginTime;
    /**
     * 消息结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;
    /**
     * 是否置顶：1：是 0:否
     */
    private Integer isTop;
    /**
     * 是否使用时间范围：0：不使用 1：使用
     */
    private Integer timeUser;
    /**
     * 优先级别:数字越小优先级别越低，默认5
     */
    private Integer dispOrder;
    /**
     * 是否删除
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getTimeUser() {
        return timeUser;
    }

    public void setTimeUser(Integer timeUser) {
        this.timeUser = timeUser;
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