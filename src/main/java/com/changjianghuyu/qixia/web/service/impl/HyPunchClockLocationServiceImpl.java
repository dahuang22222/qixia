package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.common.utils.GPSUtil;
import com.changjianghuyu.qixia.web.dao.HyPunchClockLocationDao;
import com.changjianghuyu.qixia.web.dao.HyPunchClockLocationDaoSelf;
import com.changjianghuyu.qixia.web.dao.SysAreaDao;
import com.changjianghuyu.qixia.web.entity.HyPunchClockLocation;
import com.changjianghuyu.qixia.web.entity.SysArea;
import com.changjianghuyu.qixia.web.service.HyPunchClockLocationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 设定的打卡地点表(HyPunchClockLocation)表服务实现类
 *
 * @author makejava
 * @since 2020-11-11 10:55:46
 */
@Service("hyPunchClockLocationService")
public class HyPunchClockLocationServiceImpl implements HyPunchClockLocationService {
    @Resource
    private HyPunchClockLocationDao hyPunchClockLocationDao;

    @Resource
    private HyPunchClockLocationDaoSelf hyPunchClockLocationDaoSelf;

    @Resource
    private SysAreaDao sysAreaDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HyPunchClockLocation queryById(Long id) {
        return this.hyPunchClockLocationDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<HyPunchClockLocation> queryAllByLimit(int offset, int limit) {
        return this.hyPunchClockLocationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param hyPunchClockLocation 实例对象
     * @return 实例对象
     */
    @Override
    public HyPunchClockLocation insert(HyPunchClockLocation hyPunchClockLocation) {
        hyPunchClockLocation.setDispOrder(5);
        hyPunchClockLocation.setIsDelete(0);
        hyPunchClockLocation.setCreateTime(new Date());
        if(null != hyPunchClockLocation.getVillageId()){
            SysArea sysArea = sysAreaDao.queryById(hyPunchClockLocation.getVillageId());
            hyPunchClockLocation.setVillage(sysArea.getAreaName());
            hyPunchClockLocation.setStreetId(Long.valueOf(sysArea.getParentId()));
            SysArea streetArea = sysAreaDao.queryById(Long.valueOf(sysArea.getParentId()));
            hyPunchClockLocation.setStreet(streetArea.getAreaName());
        }
        this.hyPunchClockLocationDao.insert(hyPunchClockLocation);
        return hyPunchClockLocation;
    }

    /**
     * 修改数据
     *
     * @param hyPunchClockLocation 实例对象
     * @return 实例对象
     */
    @Override
    public HyPunchClockLocation update(HyPunchClockLocation hyPunchClockLocation) {
        this.hyPunchClockLocationDao.update(hyPunchClockLocation);
        return this.queryById(hyPunchClockLocation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.hyPunchClockLocationDao.deleteById(id) > 0;
    }

    /**
     * 查询多条数据
     *
     * @param map 查询起始位置
     * @return 对象列表
     */
    @Override
    public PageInfo getPunchClockLocationList(Map<String,String> map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer pageNo = 1;
        Integer pageSize = 12;
        if(StringUtils.isNotBlank(map.get("pageNo"))){
            pageNo = Integer.valueOf(map.get("pageNo"));//页码
        }
        if(StringUtils.isNotBlank(map.get("pageSize"))){
            pageSize = Integer.valueOf(map.get("pageSize"));//页面数
        }
        HyPunchClockLocation hyPunchClockLocation = new HyPunchClockLocation();
        if(StringUtils.isNotBlank(map.get("addressName"))){
            hyPunchClockLocation.setAddressName(map.get("addressName"));
        }
        if(StringUtils.isNotBlank(map.get("clockR"))){
            hyPunchClockLocation.setClockR(new BigDecimal(map.get("clockR")));
        }
        if(StringUtils.isNotBlank(map.get("streetId"))){
            hyPunchClockLocation.setStreetId(Long.valueOf(map.get("streetId")));
        }
        if(StringUtils.isNotBlank(map.get("street"))){
            hyPunchClockLocation.setStreet(map.get("street"));
        }
        if(StringUtils.isNotBlank(map.get("villageId"))){
            hyPunchClockLocation.setVillageId(Long.valueOf(map.get("villageId")));
        }
        if(StringUtils.isNotBlank(map.get("village"))){
            hyPunchClockLocation.setVillage(map.get("village"));
        }
        if(StringUtils.isNotBlank(map.get("isDelete"))){
            hyPunchClockLocation.setIsDelete(Integer.valueOf(map.get("isDelete")));
        }
        if(StringUtils.isNotBlank(map.get("createTime"))){
            hyPunchClockLocation.setCreateTime(sdf.parse(map.get("createTime")));
        }
        PageHelper.startPage(pageNo,pageSize);
        List<HyPunchClockLocation> hyPunchClockLocationList = hyPunchClockLocationDaoSelf.queryAll(hyPunchClockLocation);
        PageInfo result = new PageInfo(hyPunchClockLocationList);
        return result;
    }

    @Override
    public List<HyPunchClockLocation> queryAllList(HyPunchClockLocation hyPunchClockLocation) {
        return hyPunchClockLocationDaoSelf.queryAll(hyPunchClockLocation);
    }

    /**
     * 根据入参经纬度、所属的村id 查询最近的打卡地点
     * @param hyPunchClockLocation
     * @return
     */
    @Override
    public HyPunchClockLocation getPunchClockLocationByAddress(HyPunchClockLocation hyPunchClockLocation){
        HyPunchClockLocation result = new HyPunchClockLocation();
        HyPunchClockLocation tempHyPunchClockLocation = new HyPunchClockLocation();
        tempHyPunchClockLocation.setVillageId(hyPunchClockLocation.getVillageId());
        List<HyPunchClockLocation> hyPunchClockLocationList = hyPunchClockLocationDao.queryAll(tempHyPunchClockLocation);
        if(CollectionUtils.isEmpty(hyPunchClockLocationList)) {
            return null;
        }
        Double minDistance = new Double(0.0);
        for (int i = 0;i < hyPunchClockLocationList.size() ; i++) {
            HyPunchClockLocation addressHyPunchClockLocation = hyPunchClockLocationList.get(i);
            Double distance = GPSUtil.GetDistance(hyPunchClockLocation.getClockLongitude(), hyPunchClockLocation.getClockLatitude(), addressHyPunchClockLocation.getClockLongitude(), addressHyPunchClockLocation.getClockLatitude());
            if(i == 0){
                minDistance = distance;
                result = addressHyPunchClockLocation;
            }else{
                if(distance.compareTo(minDistance) == -1){
                    minDistance = distance;
                    result = addressHyPunchClockLocation;
                }
            }
        }
        return result;
    }

    @Override
    public int deleteByIds(String ids) {
        String[] tempList = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String id :tempList){
            idList.add(Long.valueOf(id));
        }
        return hyPunchClockLocationDaoSelf.deleteByIds(idList);
    }
}