package com.changjianghuyu.qixia.web.service;

import com.changjianghuyu.qixia.web.entity.HyUserOauth;

import java.util.List;

/**
 * 第三方信息表(HyUserOauth)表服务接口
 *
 * @author huangkewang
 * @since 2020-11-11 16:25:08
 */
public interface HyUserOauthService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HyUserOauth queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HyUserOauth> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param hyUserOauth 实例对象
     * @return 实例对象
     */
    HyUserOauth insert(HyUserOauth hyUserOauth);

    /**
     * 修改数据
     *
     * @param hyUserOauth 实例对象
     * @return 实例对象
     */
    HyUserOauth update(HyUserOauth hyUserOauth);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}