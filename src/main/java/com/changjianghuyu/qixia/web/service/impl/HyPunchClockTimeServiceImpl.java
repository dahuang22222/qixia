package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.dao.HyPunchClockTimeDao;
import com.changjianghuyu.qixia.web.dao.HyPunchClockTimeDaoSelf;
import com.changjianghuyu.qixia.web.entity.HyPunchClockLocation;
import com.changjianghuyu.qixia.web.entity.HyPunchClockTime;
import com.changjianghuyu.qixia.web.service.HyPunchClockTimeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 打卡时间表(HyPunchClockTime)表服务实现类
 *
 * @author makejava
 * @since 2020-11-12 15:07:36
 */
@Service("hyPunchClockTimeService")
public class HyPunchClockTimeServiceImpl implements HyPunchClockTimeService {
    @Resource
    private HyPunchClockTimeDao hyPunchClockTimeDao;

    @Resource
    private HyPunchClockTimeDaoSelf hyPunchClockTimeDaoSelf;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HyPunchClockTime queryById(Long id) {
        return this.hyPunchClockTimeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<HyPunchClockTime> queryAllByLimit(int offset, int limit) {
        return this.hyPunchClockTimeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param hyPunchClockTime 实例对象
     * @return 实例对象
     */
    @Override
    public HyPunchClockTime insert(HyPunchClockTime hyPunchClockTime) {
        hyPunchClockTime.setIsDelete(0);
        this.hyPunchClockTimeDao.insert(hyPunchClockTime);
        return hyPunchClockTime;
    }

    /**
     * 修改数据
     *
     * @param hyPunchClockTime 实例对象
     * @return 实例对象
     */
    @Override
    public HyPunchClockTime update(HyPunchClockTime hyPunchClockTime) {
        this.hyPunchClockTimeDao.update(hyPunchClockTime);
        return this.queryById(hyPunchClockTime.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.hyPunchClockTimeDao.deleteById(id) > 0;
    }

    /**
     * 分页获取打卡的时间
     * @param map 查询起始位置
     * @return
     * @throws ParseException
     */
    @Override
    public PageInfo getHyPunchClockTimeList(Map<String, String> map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer pageNo = 1;
        Integer pageSize = 12;
        if(StringUtils.isNotBlank(map.get("pageNo"))){
            pageNo = Integer.valueOf(map.get("pageNo"));//页码
        }
        if(StringUtils.isNotBlank(map.get("pageSize"))){
            pageSize = Integer.valueOf(map.get("pageSize"));//页面数
        }
        HyPunchClockTime hyPunchClockTime = new HyPunchClockTime();
        if(StringUtils.isNotBlank(map.get("punchClockLocationId"))){
            hyPunchClockTime.setPunchClockLocationId(Long.valueOf(map.get("punchClockLocationId")));
        }
        if(StringUtils.isNotBlank(map.get("beginTime"))){
            hyPunchClockTime.setBeginTime(Time.valueOf(map.get("beginTime")));
        }
        if(StringUtils.isNotBlank(map.get("endTime"))){
            hyPunchClockTime.setEndTime(Time.valueOf(map.get("endTime")));
        }

        if(StringUtils.isNotBlank(map.get("clockR"))){
            hyPunchClockTime.setClockR(new BigDecimal(map.get("clockR")));
        }
        if(StringUtils.isNotBlank(map.get("streetId"))){
            hyPunchClockTime.setStreetId(Long.valueOf(map.get("streetId")));
        }
        if(StringUtils.isNotBlank(map.get("street"))){
            hyPunchClockTime.setStreet(map.get("street"));
        }
        if(StringUtils.isNotBlank(map.get("villageId"))){
            hyPunchClockTime.setVillageId(Long.valueOf(map.get("villageId")));
        }
        if(StringUtils.isNotBlank(map.get("village"))){
            hyPunchClockTime.setVillage(map.get("village"));
        }
        if(StringUtils.isNotBlank(map.get("isDelete"))){
            hyPunchClockTime.setIsDelete(Integer.valueOf(map.get("isDelete")));
        }
        if(StringUtils.isNotBlank(map.get("createTime"))){
            hyPunchClockTime.setCreateTime(sdf.parse(map.get("createTime")));
        }
        PageHelper.startPage(pageNo,pageSize);
        List<HyPunchClockTime> hyPunchClockTimeList = hyPunchClockTimeDaoSelf.queryAll(hyPunchClockTime);
        PageInfo result = new PageInfo(hyPunchClockTimeList);
        return result;
    }

    @Override
    public int deleteByIds(String ids) {
        String[] tempList = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String id :tempList){
            idList.add(Long.valueOf(id));
        }
        return hyPunchClockTimeDaoSelf.deleteByIds(idList);
    }
}