package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.HyPunchClock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户打卡表(HyPunchClock)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-26 14:42:54
 */
public interface HyPunchClockDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HyPunchClock queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HyPunchClock> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param hyPunchClock 实例对象
     * @return 对象列表
     */
    List<HyPunchClock> queryAll(HyPunchClock hyPunchClock);

    /**
     * 新增数据
     *
     * @param hyPunchClock 实例对象
     * @return 影响行数
     */
    int insert(HyPunchClock hyPunchClock);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HyPunchClock> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HyPunchClock> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HyPunchClock> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HyPunchClock> entities);

    /**
     * 修改数据
     *
     * @param hyPunchClock 实例对象
     * @return 影响行数
     */
    int update(HyPunchClock hyPunchClock);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}