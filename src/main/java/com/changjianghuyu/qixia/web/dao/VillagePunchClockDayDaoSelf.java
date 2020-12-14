package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.VillagePunchClockDay;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 村打卡统计(VillagePunchClockDay)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-19 16:49:25
 */
public interface VillagePunchClockDayDaoSelf {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param villagePunchClockDay 实例对象
     * @return 对象列表
     */
    List<VillagePunchClockDay> queryAll(VillagePunchClockDay villagePunchClockDay);

    /**
     * 获取村打卡统计信息
     * @param map
     * @return
     */
    List<Map<String,String>> getVillagePunchClockDayListByTime(Map<String,String> map);

}