package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.SysNotice;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * 系统公告表(SysNotice)表数据库访问层
 *
 * @author huangkewang
 * @since 2020-11-10 18:34:18
 */
public interface SysNoticeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysNotice queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysNotice> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysNotice 实例对象
     * @return 对象列表
     */
    List<SysNotice> queryAll(SysNotice sysNotice);

    /**
     * 新增数据
     *
     * @param sysNotice 实例对象
     * @return 影响行数
     */
    int insert(SysNotice sysNotice);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysNotice> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysNotice> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysNotice> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SysNotice> entities);

    /**
     * 修改数据
     *
     * @param sysNotice 实例对象
     * @return 影响行数
     */
    int update(SysNotice sysNotice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}