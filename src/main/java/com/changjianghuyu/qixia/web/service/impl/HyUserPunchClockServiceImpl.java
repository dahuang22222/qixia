package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.dao.HyUserPunchClockDao;
import com.changjianghuyu.qixia.web.dao.HyUserPunchClockDaoSelf;
import com.changjianghuyu.qixia.web.entity.HyInformationFeedback;
import com.changjianghuyu.qixia.web.entity.HyUserPunchClock;
import com.changjianghuyu.qixia.web.entity.HyUserPunchClockMap;
import com.changjianghuyu.qixia.web.service.HyUserPunchClockService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户打卡统计表(HyUserPunchClock)表服务实现类
 *
 * @author makejava
 * @since 2020-11-26 10:37:36
 */
@Service("hyUserPunchClockService")
public class HyUserPunchClockServiceImpl implements HyUserPunchClockService {
    @Resource
    private HyUserPunchClockDao hyUserPunchClockDao;

    @Resource
    private HyUserPunchClockDaoSelf hyUserPunchClockDaoSelf;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HyUserPunchClock queryById(Long id) {
        return this.hyUserPunchClockDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<HyUserPunchClock> queryAllByLimit(int offset, int limit) {
        return this.hyUserPunchClockDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param hyUserPunchClock 实例对象
     * @return 实例对象
     */
    @Override
    public HyUserPunchClock insert(HyUserPunchClock hyUserPunchClock) {
        hyUserPunchClock.setCreateTime(new Date());
        hyUserPunchClock.setIsDelete(0);
        this.hyUserPunchClockDao.insert(hyUserPunchClock);
        return hyUserPunchClock;
    }

    /**
     * 修改数据
     *
     * @param hyUserPunchClock 实例对象
     * @return 实例对象
     */
    @Override
    public HyUserPunchClock update(HyUserPunchClock hyUserPunchClock) {
        this.hyUserPunchClockDao.update(hyUserPunchClock);
        return this.queryById(hyUserPunchClock.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.hyUserPunchClockDao.deleteById(id) > 0;
    }


    /**
     * 查询多条数据
     *
     * @param map 查询起始位置
     * @return 对象列表
     */
    @Override
    public PageInfo getUserPunchClockList(Map<String,String> map) throws ParseException {
        Integer pageNo = 1;
        Integer pageSize = 12;
        if(StringUtils.isNotBlank(map.get("pageNo"))){
            pageNo = Integer.valueOf(map.get("pageNo"));//页码
        }
        if(StringUtils.isNotBlank(map.get("pageSize"))){
            pageSize = Integer.valueOf(map.get("pageSize"));//页面数
        }
        HyUserPunchClock hyUserPunchClock = new HyUserPunchClock();
        if(StringUtils.isNotBlank(map.get("clockLongitude"))){
            hyUserPunchClock.setClockLongitude(new BigDecimal(map.get("clockLongitude")));
        }
        if(StringUtils.isNotBlank(map.get("clockLongitude"))){
            hyUserPunchClock.setClockLatitude(new BigDecimal(map.get("clockLatitude")));
        }
        if(StringUtils.isNotBlank(map.get("clockStatus"))){
            hyUserPunchClock.setClockStatus(Integer.valueOf(map.get("clockStatus")));
        }
        if(StringUtils.isNotBlank(map.get("userId"))){
            hyUserPunchClock.setUserId(Long.valueOf(map.get("userId")));
        }
        if(StringUtils.isNotBlank(map.get("streetId"))){
            hyUserPunchClock.setStreetId(Long.valueOf(map.get("streetId")));
        }
        if(StringUtils.isNotBlank(map.get("villageId"))){
            hyUserPunchClock.setVillageId(Long.valueOf(map.get("villageId")));
        }
        if(StringUtils.isNotBlank(map.get("isDelete"))){
            hyUserPunchClock.setIsDelete(Integer.valueOf(map.get("isDelete")));
        }
        PageHelper.startPage(pageNo,pageSize);
        List<HyUserPunchClockMap> hyUserPunchClockList = hyUserPunchClockDaoSelf.getUserClockCensusList(hyUserPunchClock);
        PageInfo result = new PageInfo(hyUserPunchClockList);
        return result;
    }

    @Override
    public List<HyUserPunchClock> queryAll(HyUserPunchClock hyUserPunchClock) {
        hyUserPunchClock.setCreateTime(new Date());
        return hyUserPunchClockDaoSelf.getTodayList(hyUserPunchClock);
    }
}