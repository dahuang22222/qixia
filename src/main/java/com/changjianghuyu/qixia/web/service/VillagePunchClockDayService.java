package com.changjianghuyu.qixia.web.service;

import com.changjianghuyu.qixia.web.entity.VillagePunchClockDay;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 村打卡统计(VillagePunchClockDay)表服务接口
 *
 * @author makejava
 * @since 2020-11-19 16:49:25
 */
public interface VillagePunchClockDayService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VillagePunchClockDay queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<VillagePunchClockDay> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param villagePunchClockDay 实例对象
     * @return 实例对象
     */
    VillagePunchClockDay insert(VillagePunchClockDay villagePunchClockDay);

    /**
     * 修改数据
     *
     * @param villagePunchClockDay 实例对象
     * @return 实例对象
     */
    VillagePunchClockDay update(VillagePunchClockDay villagePunchClockDay);

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
    public PageInfo getVillagePunchClockDayList(Map<String,String> map) throws ParseException;

    /**
     *
     * @param map
     * @return
     */
    PageInfo getVillagePunchClockDayListByTime(Map<String,String> map);
}