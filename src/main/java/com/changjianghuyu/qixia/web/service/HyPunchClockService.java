package com.changjianghuyu.qixia.web.service;

import com.changjianghuyu.qixia.web.entity.HyPunchClock;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 用户打卡表(HyPunchClock)表服务接口
 *
 * @author huangkewang
 * @since 2020-11-11 16:24:41
 */
public interface HyPunchClockService {

    /**
     * 用户打卡
     * @param hyPunchClock
     * @return
     */
    Map<String,Object> punchClock(HyPunchClock hyPunchClock);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HyPunchClock queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HyPunchClock> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param hyPunchClock 实例对象
     * @return 实例对象
     */
    HyPunchClock insert(HyPunchClock hyPunchClock);

    /**
     * 修改数据
     *
     * @param hyPunchClock 实例对象
     * @return 实例对象
     */
    HyPunchClock update(HyPunchClock hyPunchClock);

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
    public PageInfo getHyPunchClockList(Map<String,String> map) throws ParseException;

    /**
     * 获取打卡记录列表
     *
     * @param hyPunchClock 查询的参数
     * @return 对象列表
     */
    List<HyPunchClock> getTodayHyPunchClockList(HyPunchClock hyPunchClock);


}