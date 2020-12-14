package com.changjianghuyu.qixia.web.service;

import com.changjianghuyu.qixia.web.entity.HyPunchClockLocation;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 设定的打卡地点表(HyPunchClockLocation)表服务接口
 *
 * @author huangkewang
 * @since 2020-11-11 10:55:44
 */
public interface HyPunchClockLocationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HyPunchClockLocation queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HyPunchClockLocation> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param hyPunchClockLocation 实例对象
     * @return 实例对象
     */
    HyPunchClockLocation insert(HyPunchClockLocation hyPunchClockLocation);

    /**
     * 修改数据
     *
     * @param hyPunchClockLocation 实例对象
     * @return 实例对象
     */
    HyPunchClockLocation update(HyPunchClockLocation hyPunchClockLocation);

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
    PageInfo getPunchClockLocationList(Map<String,String> map) throws ParseException;

    /**
     * 按条件查询所有的数据
     * @param hyPunchClockLocation
     * @return
     */
    List<HyPunchClockLocation> queryAllList(HyPunchClockLocation hyPunchClockLocation);

    /**
     * 根据入参经纬度、所属的村id 查询最近的打卡地点
     * @param hyPunchClockLocation
     * @return
     */
    HyPunchClockLocation getPunchClockLocationByAddress(HyPunchClockLocation hyPunchClockLocation);


    /**
     * 批量删除
     * @param id
     * @return
     */
    int deleteByIds(String id);

}