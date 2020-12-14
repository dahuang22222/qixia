package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.HyUserPunchClock;
import com.changjianghuyu.qixia.web.entity.HyUserPunchClockMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户打卡统计表(HyUserPunchClock)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-24 16:35:00
 */
public interface HyUserPunchClockDaoSelf {
    /**
     * 通过实体作为筛选条件查询
     *
     * @param hyUserPunchClock 实例对象
     * @return 对象列表
     */
    List<HyUserPunchClock> queryAll(HyUserPunchClock hyUserPunchClock);

     /**
     * 获取用户当天的数据
     *
     * @param hyUserPunchClock 实例对象
     * @return 对象列表
     */
    List<HyUserPunchClock> getTodayList(HyUserPunchClock hyUserPunchClock);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hyUserPunchClock 实例对象
     * @return 对象列表
     */
    List<HyUserPunchClockMap> getUserClockCensusList(HyUserPunchClock hyUserPunchClock);

}