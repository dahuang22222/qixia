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
public interface HyPunchClockLocationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HyPunchClockLocation queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HyPunchClockLocation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param hyPunchClockLocation 实例对象
     * @return 对象列表
     */
    List<HyPunchClockLocation> queryAll(HyPunchClockLocation hyPunchClockLocation);

    /**
     * 新增数据
     *
     * @param hyPunchClockLocation 实例对象
     * @return 影响行数
     */
    int insert(HyPunchClockLocation hyPunchClockLocation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HyPunchClockLocation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HyPunchClockLocation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HyPunchClockLocation> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HyPunchClockLocation> entities);

    /**
     * 修改数据
     *
     * @param hyPunchClockLocation 实例对象
     * @return 影响行数
     */
    int update(HyPunchClockLocation hyPunchClockLocation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}