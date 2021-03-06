package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.HyUser;

import java.util.List;

public interface HyUserDaoSelf {
    /**
     * 按照条件模糊查询用户列表
     * @param user
     * @return
     */
   List<HyUser> queryAllByUser (HyUser user);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    int deleteByIds(List<Long> idList);

    /**
     * 修改数据
     *
     * @param hyUser 实例对象
     * @return 影响行数
     */
    int update(HyUser hyUser);
}
