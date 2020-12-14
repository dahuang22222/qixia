package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.VillagePunchClockDay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 村打卡统计(VillagePunchClockDay)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-19 16:49:25
 */
public interface VillagePunchClockDayDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VillagePunchClockDay queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<VillagePunchClockDay> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param villagePunchClockDay 实例对象
     * @return 对象列表
     */
    List<VillagePunchClockDay> queryAll(VillagePunchClockDay villagePunchClockDay);

    /**
     * 新增数据
     *
     * @param villagePunchClockDay 实例对象
     * @return 影响行数
     */
    int insert(VillagePunchClockDay villagePunchClockDay);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VillagePunchClockDay> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VillagePunchClockDay> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VillagePunchClockDay> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<VillagePunchClockDay> entities);

    /**
     * 修改数据
     *
     * @param villagePunchClockDay 实例对象
     * @return 影响行数
     */
    int update(VillagePunchClockDay villagePunchClockDay);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}