package com.changjianghuyu.qixia.web.service;

import com.changjianghuyu.qixia.web.entity.HyInformationFeedback;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 信息反馈表(HyInformationFeedback)表服务接口
 *
 * @author huangkewang
 * @since 2020-11-11 15:10:55
 */
public interface HyInformationFeedbackService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HyInformationFeedback queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HyInformationFeedback> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param hyInformationFeedback 实例对象
     * @return 实例对象
     */
    HyInformationFeedback insert(HyInformationFeedback hyInformationFeedback);

    /**
     * 修改数据
     *
     * @param hyInformationFeedback 实例对象
     * @return 实例对象
     */
    HyInformationFeedback update(HyInformationFeedback hyInformationFeedback);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询多条数据
     *
     * @param map 查询起始位置
     * @return 对象列表
     */
    PageInfo getInformationFeedbackList(Map<String,String> map) throws ParseException;

    /**
     * 根据id删除多条反馈信息
     * @param id
     * @return
     */
    int deleteByIds(String id);

}