package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.SysArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 地区表(SysArea)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-12 15:17:16
 */
public interface SysAreaDaoSelf {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysArea 实例对象
     * @return 对象列表
     */
    List<SysArea> queryAll(SysArea sysArea);


    /**
     * 批量删除
     * @param idList
     * @return
     */
    int deleteByIds(List<Long> idList);
}