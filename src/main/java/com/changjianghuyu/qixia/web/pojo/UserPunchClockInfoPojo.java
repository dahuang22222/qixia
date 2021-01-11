package com.changjianghuyu.qixia.web.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class UserPunchClockInfoPojo {
    private static final long serialVersionUID = 963449371045795534L;

    /**
     * 打卡时间:哪一天的打卡
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date clockDate;

    /**
     * 打卡次数
     */
    private int clockTimes;

    /**
     * 打卡状态：1：合格 0：不合格
     */
    private int clockStatus;

    /**
     * 打卡详情的集合
     */
    private List<PunchClockInfoPojo> punchClockInfoList;

    public Date getClockDate() {
        return clockDate;
    }

    public void setClockDate(Date clockDate) {
        this.clockDate = clockDate;
    }

    public int getClockTimes() {
        return clockTimes;
    }

    public void setClockTimes(int clockTimes) {
        this.clockTimes = clockTimes;
    }

    public int getClockStatus() {
        return clockStatus;
    }

    public void setClockStatus(int clockStatus) {
        this.clockStatus = clockStatus;
    }

    public List<PunchClockInfoPojo> getPunchClockInfoList() {
        return punchClockInfoList;
    }

    public void setPunchClockInfoList(List<PunchClockInfoPojo> punchClockInfoList) {
        this.punchClockInfoList = punchClockInfoList;
    }
}
