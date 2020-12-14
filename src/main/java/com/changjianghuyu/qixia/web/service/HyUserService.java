package com.changjianghuyu.qixia.web.service;

import com.changjianghuyu.qixia.web.entity.HyUser;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表(HyUser)表服务接口
 *
 * @author makejava
 * @since 2020-11-12 18:31:23
 */
public interface HyUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HyUser queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HyUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param hyUser 实例对象
     * @return 实例对象
     */
    HyUser insert(HyUser hyUser);

    /**
     * 新增用户集合
     *
     * @param userList 实例对象
     * @return 实例对象
     */
    public List<HyUser> insertUserList( List<HyUser> userList);

    /**
     * 修改数据
     *
     * @param hyUser 实例对象
     * @return 实例对象
     */
    HyUser update(HyUser hyUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询用户列表
     *
     * @param map 查询起始位置
     * @return 对象列表
     */
    public PageInfo getUserList(Map<String,String> map);

    /**
     * 根据id删除多条
     * @param id
     * @return
     */
    int deleteByIds(String id);

}