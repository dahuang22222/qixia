package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.dao.HyUserAreaDao;
import com.changjianghuyu.qixia.web.entity.HyUserArea;
import com.changjianghuyu.qixia.web.service.HyUserAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色地区表(HyUserArea)表服务实现类
 *
 * @author makejava
 * @since 2020-11-11 16:24:56
 */
@Service("hyUserAreaService")
public class HyUserAreaServiceImpl implements HyUserAreaService {
    @Resource
    private HyUserAreaDao hyUserAreaDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HyUserArea queryById(Long id) {
        return this.hyUserAreaDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<HyUserArea> queryAllByLimit(int offset, int limit) {
        return this.hyUserAreaDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param hyUserArea 实例对象
     * @return 实例对象
     */
    @Override
    public HyUserArea insert(HyUserArea hyUserArea) {
        this.hyUserAreaDao.insert(hyUserArea);
        return hyUserArea;
    }

    /**
     * 修改数据
     *
     * @param hyUserArea 实例对象
     * @return 实例对象
     */
    @Override
    public HyUserArea update(HyUserArea hyUserArea) {
        this.hyUserAreaDao.update(hyUserArea);
        return this.queryById(hyUserArea.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.hyUserAreaDao.deleteById(id) > 0;
    }

    /**
     * 获取用户关联的地区
     * @param hyUserArea
     * @return
     */
    @Override
    public List<HyUserArea> queryHyUserAreaList(HyUserArea hyUserArea) {
        return hyUserAreaDao.queryAll(hyUserArea);
    }
}