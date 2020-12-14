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
public interface HyInformationFeedbackDaoSelf {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hyInformationFeedback 实例对象
     * @return 对象列表
     */
    List<HyInformationFeedback> queryAll(HyInformationFeedback hyInformationFeedback);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    int deleteByIds(List<Long> idList);
}