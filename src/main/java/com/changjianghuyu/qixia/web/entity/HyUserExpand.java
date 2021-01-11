package com.changjianghuyu.qixia.web.entity;

import java.io.Serializable;

public class HyUserExpand extends HyUser implements Serializable {


    private static final long serialVersionUID = -41716431258885358L;

    /**
     * 用户token
     */
    private String token;

    public HyUserExpand() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HyUserExpand( HyUser user,String token) {
        this.setId(user.getId());
        this.setUserName(user.getUserName());
        this.setUserType(user.getUserType());
        this.setImage(user.getImage());
        this.setPhone(user.getPhone());
        this.setAge(user.getAge());
        this.setStreet(user.getStreet());
        this.setVillage(user.getVillage());
        this.setStreetId(user.getStreetId());
        this.setVillageId(user.getVillageId());
        this.setIsDelete(user.getIsDelete());
        this.setCreateTime(user.getCreateTime());
        this.setToken(token);
    }
}
