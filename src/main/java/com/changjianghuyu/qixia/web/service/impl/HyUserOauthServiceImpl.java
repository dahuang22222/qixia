package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.dao.HyUserOauthDao;
import com.changjianghuyu.qixia.web.entity.HyUserOauth;
import com.changjianghuyu.qixia.web.service.HyUserOauthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 第三方信息表(HyUserOauth)表服务实现类
 *
 * @author makejava
 * @since 2020-11-11 16:25:08
 */
@Service("hyUserOauthService")
public class HyUserOauthServiceImpl implements HyUserOauthService {
    @Resource
    private HyUserOauthDao hyUserOauthDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HyUserOauth queryById(Long id) {
        return this.hyUserOauthDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<HyUserOauth> queryAllByLimit(int offset, int limit) {
        return this.hyUserOauthDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param hyUserOauth 实例对象
     * @return 实例对象
     */
    @Override
    public HyUserOauth insert(HyUserOauth hyUserOauth) {
        this.hyUserOauthDao.insert(hyUserOauth);
        return hyUserOauth;
    }

    /**
     * 修改数据
     *
     * @param hyUserOauth 实例对象
     * @return 实例对象
     */
    @Override
    public HyUserOauth update(HyUserOauth hyUserOauth) {
        this.hyUserOauthDao.update(hyUserOauth);
        return this.queryById(hyUserOauth.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.hyUserOauthDao.deleteById(id) > 0;
    }
}