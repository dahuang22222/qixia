package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.HyInformationFeedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 信息反馈表(HyInformationFeedback)表数据库访问层
 *
 * @author huangkewang
 * @since 2020-11-11 15:10:55
 */
public interface HyInformationFeedbackDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HyInformationFeedback queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HyInformationFeedback> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param hyInformationFeedback 实例对象
     * @return 对象列表
     */
    List<HyInformationFeedback> queryAll(HyInformationFeedback hyInformationFeedback);

    /**
     * 新增数据
     *
     * @param hyInformationFeedback 实例对象
     * @return 影响行数
     */
    int insert(HyInformationFeedback hyInformationFeedback);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HyInformationFeedback> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HyInformationFeedback> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HyInformationFeedback> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HyInformationFeedback> entities);

    /**
     * 修改数据
     *
     * @param hyInformationFeedback 实例对象
     * @return 影响行数
     */
    int update(HyInformationFeedback hyInformationFeedback);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}