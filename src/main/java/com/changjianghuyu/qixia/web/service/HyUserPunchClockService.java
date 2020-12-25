package com.changjianghuyu.qixia.web.service;

import com.changjianghuyu.qixia.web.entity.HyUserPunchClock;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 用户打卡统计表(HyUserPunchClock)表服务接口
 *
 * @author makejava
 * @since 2020-11-26 10:37:35
 */
public interface HyUserPunchClockService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HyUserPunchClock queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HyUserPunchClock> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param hyUserPunchClock 实例对象
     * @return 实例对象
     */
    HyUserPunchClock insert(HyUserPunchClock hyUserPunchClock);

    /**
     * 修改数据
     *
     * @param hyUserPunchClock 实例对象
     * @return 实例对象
     */
    HyUserPunchClock update(HyUserPunchClock hyUserPunchClock);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 分页获取反馈列表
     * @param map
     * @return
     * @throws ParseException
     */
    PageInfo getUserPunchClockList(Map<String,String> map) throws ParseException;

    /**
     * 根据条件获取所有打卡列表
     * @param hyUserPunchClock
     * @return
     */
    List<HyUserPunchClock> queryAll(HyUserPunchClock hyUserPunchClock);

    /**
     * 根据打卡的时间id,修改用户当天统计的打卡时间
     * @param hyUserPunchClock
     * @return
     */
    int updateTodayTime(HyUserPunchClock hyUserPunchClock);
}