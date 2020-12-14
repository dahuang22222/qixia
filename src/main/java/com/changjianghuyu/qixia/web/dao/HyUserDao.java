package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.HyUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息表(HyUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-14 14:25:50
 */
public interface HyUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HyUser queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HyUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param hyUser 实例对象
     * @return 对象列表
     */
    List<HyUser> queryAll(HyUser hyUser);

    /**
     * 新增数据
     *
     * @param hyUser 实例对象
     * @return 影响行数
     */
    int insert(HyUser hyUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HyUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HyUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HyUser> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HyUser> entities);

    /**
     * 修改数据
     *
     * @param hyUser 实例对象
     * @return 影响行数
     */
    int update(HyUser hyUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}