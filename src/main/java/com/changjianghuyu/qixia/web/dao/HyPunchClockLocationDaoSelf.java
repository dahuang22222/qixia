package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.HyPunchClockLocation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设定的打卡地点表(HyPunchClockLocation)表数据库访问层
 *
 * @author huangkewang
 * @since 2020-11-11 10:55:38
 */
public interface HyPunchClockLocationDaoSelf {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hyPunchClockLocation 实例对象
     * @return 对象列表
     */
    List<HyPunchClockLocation> queryAll(HyPunchClockLocation hyPunchClockLocation);


    /**
     * 批量删除
     * @param idList
     * @return
     */
    int deleteByIds(List<Long> idList);

}