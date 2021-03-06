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
public interface SysAreaDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysArea queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysArea> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysArea 实例对象
     * @return 对象列表
     */
    List<SysArea> queryAll(SysArea sysArea);

    /**
     * 新增数据
     *
     * @param sysArea 实例对象
     * @return 影响行数
     */
    int insert(SysArea sysArea);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysArea> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysArea> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysArea> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SysArea> entities);

    /**
     * 修改数据
     *
     * @param sysArea 实例对象
     * @return 影响行数
     */
    int update(SysArea sysArea);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}