package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.HyUserPunchClock;
import com.changjianghuyu.qixia.web.entity.HyUserPunchClockMap;
import com.changjianghuyu.qixia.web.pojo.PunchClockInfoPojo;
import com.changjianghuyu.qixia.web.pojo.UserPunchClockInfoPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据打卡的时间id,修改用户当天统计的打卡时间
     * @param hyUserPunchClock
     * @return
     */
    int updateTodayTime(HyUserPunchClock hyUserPunchClock);

    /**
     * 按照用户分组查询
     * @param hyUserPunchClock
     * @return
     */
    List<HyUserPunchClock> queryAllGroupByUser(HyUserPunchClock hyUserPunchClock);

    /**
     * 获取打卡详情信息
     * @param map
     * @return
     */
    List<PunchClockInfoPojo> getClockList (Map<String,String> map);

    /**
     * 获取打卡详情信息
     * @param map
     * @return
     */
    List<UserPunchClockInfoPojo> getClockListInfoList (Map<String,String> map);
}