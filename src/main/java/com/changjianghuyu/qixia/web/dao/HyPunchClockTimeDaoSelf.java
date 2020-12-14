package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.HyPunchClockTime;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 打卡时间表(HyPunchClockTime)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-12 15:07:36
 */
public interface HyPunchClockTimeDaoSelf {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hyPunchClockTime 实例对象
     * @return 对象列表
     */
    List<HyPunchClockTime> queryAll(HyPunchClockTime hyPunchClockTime);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    int deleteByIds(List<Long> idList);
}