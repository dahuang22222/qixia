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
public interface HyPunchClockTimeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HyPunchClockTime queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HyPunchClockTime> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param hyPunchClockTime 实例对象
     * @return 对象列表
     */
    List<HyPunchClockTime> queryAll(HyPunchClockTime hyPunchClockTime);

    /**
     * 新增数据
     *
     * @param hyPunchClockTime 实例对象
     * @return 影响行数
     */
    int insert(HyPunchClockTime hyPunchClockTime);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HyPunchClockTime> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HyPunchClockTime> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HyPunchClockTime> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HyPunchClockTime> entities);

    /**
     * 修改数据
     *
     * @param hyPunchClockTime 实例对象
     * @return 影响行数
     */
    int update(HyPunchClockTime hyPunchClockTime);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}