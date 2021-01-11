package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.HyPunchClock;
import com.changjianghuyu.qixia.web.entity.HyPunchClockExpand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户打卡表(HyPunchClock)表数据库访问层
 *
 * @author huangkewang
 * @since 2020-11-11 16:24:40
 */
public interface HyPunchClockDaoSelf {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hyPunchClock 实例对象
     * @return 对象列表
     */
    List<HyPunchClock> queryAll(HyPunchClockExpand hyPunchClock);

    /**
     * 查询该用户近两个小时的打卡地点
     *
     * @param hyPunchClock 实例对象
     * @return 对象列表
     */
    List<HyPunchClock> queryAllNearTwoHour(HyPunchClock hyPunchClock);
}