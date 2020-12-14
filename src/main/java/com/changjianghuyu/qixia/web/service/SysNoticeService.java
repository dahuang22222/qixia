package com.changjianghuyu.qixia.web.service;

import com.changjianghuyu.qixia.web.entity.SysNotice;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 系统公告表(SysNotice)表服务接口
 *
 * @author huangkewang
 * @since 2020-11-10 18:34:18
 */
public interface SysNoticeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysNotice queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysNotice> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysNotice 实例对象
     * @return 实例对象
     */
    SysNotice insert(SysNotice sysNotice);

    /**
     * 修改数据
     *
     * @param sysNotice 实例对象
     * @return 实例对象
     */
    SysNotice update(SysNotice sysNotice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 分页获取数据列表
     * @param map
     * @return
     */
    PageInfo getNoticeListByPage(Map<String,String> map) throws ParseException;

    /**
     * 小程序公告列表
     * @param map
     * @return
     */
    PageInfo userGetNoticeListByPage(Map<String,String> map) throws ParseException;


    /**
     * 根据id删除多条
     * @param id
     * @return
     */
    int deleteByIds(String id);

}