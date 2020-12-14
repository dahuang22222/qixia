package com.changjianghuyu.qixia.web.service;

import com.changjianghuyu.qixia.web.entity.HyPunchClockTime;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 打卡时间表(HyPunchClockTime)表服务接口
 *
 * @author makejava
 * @since 2020-11-12 15:07:36
 */
public interface HyPunchClockTimeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HyPunchClockTime queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HyPunchClockTime> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param hyPunchClockTime 实例对象
     * @return 实例对象
     */
    HyPunchClockTime insert(HyPunchClockTime hyPunchClockTime);

    /**
     * 修改数据
     *
     * @param hyPunchClockTime 实例对象
     * @return 实例对象
     */
    HyPunchClockTime update(HyPunchClockTime hyPunchClockTime);

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
    PageInfo getHyPunchClockTimeList(Map<String, String> map) throws ParseException;


    /**
     * 根据id删除多条
     * @param id
     * @return
     */
    int deleteByIds(String id);

}