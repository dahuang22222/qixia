package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.dao.VillagePunchClockDayDao;
import com.changjianghuyu.qixia.web.dao.VillagePunchClockDayDaoSelf;
import com.changjianghuyu.qixia.web.entity.HyPunchClockLocation;
import com.changjianghuyu.qixia.web.entity.VillagePunchClockDay;
import com.changjianghuyu.qixia.web.service.VillagePunchClockDayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 村打卡统计(VillagePunchClockDay)表服务实现类
 *
 * @author makejava
 * @since 2020-11-19 16:49:25
 */
@Service("villagePunchClockDayService")
public class VillagePunchClockDayServiceImpl implements VillagePunchClockDayService {
    @Resource
    private VillagePunchClockDayDao villagePunchClockDayDao;

    @Resource
    private VillagePunchClockDayDaoSelf villagePunchClockDayDaoSelf;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象VillagePunchClockDay
     */
    @Override
    public VillagePunchClockDay queryById(Long id) {
        return this.villagePunchClockDayDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<VillagePunchClockDay> queryAllByLimit(int offset, int limit) {
        return this.villagePunchClockDayDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param villagePunchClockDay 实例对象
     * @return 实例对象
     */
    @Override
    public VillagePunchClockDay insert(VillagePunchClockDay villagePunchClockDay) {
        this.villagePunchClockDayDao.insert(villagePunchClockDay);
        return villagePunchClockDay;
    }

    /**
     * 修改数据
     *
     * @param villagePunchClockDay 实例对象
     * @return 实例对象
     */
    @Override
    public VillagePunchClockDay update(VillagePunchClockDay villagePunchClockDay) {
        this.villagePunchClockDayDao.update(villagePunchClockDay);
        return this.queryById(villagePunchClockDay.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.villagePunchClockDayDao.deleteById(id) > 0;
    }

    /**
     * 查询多条数据
     *
     * @param map 查询起始位置
     * @return 对象列表
     */
    @Override
    public PageInfo getVillagePunchClockDayList(Map<String,String> map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer pageNo = 1;
        Integer pageSize = 12;
        if(StringUtils.isNotBlank(map.get("pageNo"))){
            pageNo = Integer.valueOf(map.get("pageNo"));//页码
        }
        if(StringUtils.isNotBlank(map.get("pageSize"))){
            pageSize = Integer.valueOf(map.get("pageSize"));//页面数
        }
        VillagePunchClockDay villagePunchClockDay = new VillagePunchClockDay();
        if(StringUtils.isNotBlank(map.get("clockDate"))){
            villagePunchClockDay.setClockDate(LocalDate.parse(map.get("clockDate")));
        }
        if(StringUtils.isNotBlank(map.get("clockStatus"))){
            villagePunchClockDay.setClockStatus(Integer.valueOf(map.get("clockStatus")));
        }
        if(StringUtils.isNotBlank(map.get("userId"))){
            villagePunchClockDay.setUserId(Long.valueOf(map.get("userId")));
        }
        if(StringUtils.isNotBlank(map.get("streetId"))){
            villagePunchClockDay.setStreetId(Long.valueOf(map.get("streetId")));
        }
        if(StringUtils.isNotBlank(map.get("street"))){
            villagePunchClockDay.setStreet(map.get("street"));
        }
        if(StringUtils.isNotBlank(map.get("villageId"))){
            villagePunchClockDay.setVillageId(Long.valueOf(map.get("villageId")));
        }
        if(StringUtils.isNotBlank(map.get("village"))){
            villagePunchClockDay.setVillage(map.get("village"));
        }
        if(StringUtils.isNotBlank(map.get("isDelete"))){
            villagePunchClockDay.setIsDelete(Integer.valueOf(map.get("isDelete")));
        }
        if(StringUtils.isNotBlank(map.get("createTime"))){
            villagePunchClockDay.setCreateTime(sdf.parse(map.get("createTime")));
        }
        PageHelper.startPage(pageNo,pageSize);
        List<VillagePunchClockDay> villagePunchClockDayList = villagePunchClockDayDaoSelf.queryAll(villagePunchClockDay);
        PageInfo result = new PageInfo(villagePunchClockDayList);
        return result;
    }


    /**
     * 获取村打卡统计信息
     * @param map
     * @return
     */
    @Override
    public PageInfo getVillagePunchClockDayListByTime(Map<String,String> map){
        Integer pageNo = 1;
        Integer pageSize = 12;
        if(StringUtils.isNotBlank(map.get("pageNo"))){
            pageNo = Integer.valueOf(map.get("pageNo"));//页码
        }
        if(StringUtils.isNotBlank(map.get("pageSize"))){
            pageSize = Integer.valueOf(map.get("pageSize"));//页面数
        }
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String, String>> clockDayList = villagePunchClockDayDaoSelf.getVillagePunchClockDayListByTime(map);
        PageInfo result = new PageInfo(clockDayList);
       return result;
    }
}