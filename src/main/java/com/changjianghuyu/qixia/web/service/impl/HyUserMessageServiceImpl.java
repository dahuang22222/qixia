package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.dao.HyUserMessageDao;
import com.changjianghuyu.qixia.web.entity.HyUserMessage;
import com.changjianghuyu.qixia.web.service.HyUserMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户消息(HyUserMessage)表服务实现类
 *
 * @author makejava
 * @since 2021-01-09 23:40:09
 */
@Service("hyUserMessageService")
public class HyUserMessageServiceImpl implements HyUserMessageService {
    @Resource
    private HyUserMessageDao hyUserMessageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HyUserMessage queryById(Long id) {
        return this.hyUserMessageDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<HyUserMessage> queryAllByLimit(int offset, int limit) {
        return this.hyUserMessageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param hyUserMessage 实例对象
     * @return 实例对象
     */
    @Override
    public HyUserMessage insert(HyUserMessage hyUserMessage) {
        this.hyUserMessageDao.insert(hyUserMessage);
        return hyUserMessage;
    }

    /**
     * 修改数据
     *
     * @param hyUserMessage 实例对象
     * @return 实例对象
     */
    @Override
    public HyUserMessage update(HyUserMessage hyUserMessage) {
        this.hyUserMessageDao.update(hyUserMessage);
        return this.queryById(hyUserMessage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.hyUserMessageDao.deleteById(id) > 0;
    }

    @Override
    public List<HyUserMessage> queryAll(HyUserMessage hyUserMessage) {
        return hyUserMessageDao.queryAll(hyUserMessage);
    }
}