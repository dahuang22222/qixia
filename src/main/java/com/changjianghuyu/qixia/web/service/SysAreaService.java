package com.changjianghuyu.qixia.web.service;

import com.changjianghuyu.qixia.web.entity.SysArea;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 地区表(SysArea)表服务接口
 *
 * @author makejava
 * @since 2020-11-12 15:17:16
 */
public interface SysAreaService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysArea queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysArea> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysArea 实例对象
     * @return 实例对象
     */
    SysArea insert(SysArea sysArea);

    /**
     * 修改数据
     *
     * @param sysArea 实例对象
     * @return 实例对象
     */
    SysArea update(SysArea sysArea);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 分页获取区域划分
     * @param map
     * @return
     */
    public PageInfo getAreaListByPage(Map<String,String> map);

    /**
     * 直接查询所有的地区列表
     * @param sysArea
     * @return
     */
    public List<SysArea> getAreaList(SysArea sysArea);


    /**
     * 分页获取区域划分
     * @param map
     * @return
     */
    public PageInfo getAreaExpandListByPage(Map<String,String> map);


    /**
     * 根据id删除多条
     * @param id
     * @return
     */
    int deleteByIds(String id);


}