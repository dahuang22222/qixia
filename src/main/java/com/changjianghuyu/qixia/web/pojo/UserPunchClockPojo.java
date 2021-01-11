package com.changjianghuyu.qixia.web.pojo;

import com.github.pagehelper.PageInfo;

/**
 * 接口9.4专用返回类型
 */
public class UserPunchClockPojo {
    private static final long serialVersionUID = 322849371045795534L;

    /**
     * 合格数量
     */
    private int qualifiedNumber;

    /**
     * 不合格数量
     */
    private int notQualifiedNumber;

    /**
     * 内侧页面的分页数据
     */
    private PageInfo clockList;

    public int getQualifiedNumber() {
        return qualifiedNumber;
    }

    public void setQualifiedNumber(int qualifiedNumber) {
        this.qualifiedNumber = qualifiedNumber;
    }

    public int getNotQualifiedNumber() {
        return notQualifiedNumber;
    }

    public void setNotQualifiedNumber(int notQualifiedNumber) {
        this.notQualifiedNumber = notQualifiedNumber;
    }

    public PageInfo getClockList() {
        return clockList;
    }

    public void setClockList(PageInfo clockList) {
        this.clockList = clockList;
    }
}
