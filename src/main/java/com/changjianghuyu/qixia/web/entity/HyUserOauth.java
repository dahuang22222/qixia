package com.changjianghuyu.qixia.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方信息表(HyUserOauth)实体类
 *
 * @author huang_kewang
 * @since 2020-11-11 16:25:07
 */
public class HyUserOauth implements Serializable {
    private static final long serialVersionUID = 343191215743468222L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 第三方id
     */
    private String oauthId;
    /**
     * 第三方类型：1：微信
     */
    private Integer oauthType;
    /**
     * openid
     */
    private String openid;
    /**
     * 其他信息集合
     */
    private String openinfo;
    /**
     * unionid
     */
    private String unionid;
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

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    public Integer getOauthType() {
        return oauthType;
    }

    public void setOauthType(Integer oauthType) {
        this.oauthType = oauthType;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpeninfo() {
        return openinfo;
    }

    public void setOpeninfo(String openinfo) {
        this.openinfo = openinfo;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}