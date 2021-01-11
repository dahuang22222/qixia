package com.changjianghuyu.qixia.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户消息(HyUserMessage)实体类
 *
 * @author huang_kewang
 * @since 2021-01-10 14:10:41
 */
public class HyUserMessage implements Serializable {
    private static final long serialVersionUID = 296611156809717486L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 关联的消息表id
     */
    private Long connectionId;
    /**
     * 消息类型：1.系统公告
     */
    private Integer type;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 是否删除：0未删除 1删除
     */
    private Integer isDelete;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否已读：0 未读 1已读
     */
    private Integer isRead;
    /**
     * 推送位置：仅小程序
     */
    private Integer methond;


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

    public Long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Long connectionId) {
        this.connectionId = connectionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getMethond() {
        return methond;
    }

    public void setMethond(Integer methond) {
        this.methond = methond;
    }

}