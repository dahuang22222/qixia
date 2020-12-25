package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.common.utils.GPSUtil;
import com.changjianghuyu.qixia.web.dao.*;
import com.changjianghuyu.qixia.web.entity.*;
import com.changjianghuyu.qixia.web.service.HyPunchClockLocationService;
import com.changjianghuyu.qixia.web.service.HyPunchClockService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.util.DateUtil.now;

/**
 * 用户打卡表(HyPunchClock)表服务实现类
 *
 * @author makejava
 * @since 2020-11-11 16:24:41
 */
@Service("hyPunchClockService")
public class HyPunchClockServiceImpl implements HyPunchClockService {
    @Resource
    private HyPunchClockDao hyPunchClockDao;

    @Resource
    private HyPunchClockDaoSelf hyPunchClockDaoSelf;

    @Resource
    private HyPunchClockLocationDao hyPunchClockLocationDao;

    @Resource
    private HyPunchClockTimeDao hyPunchClockTimedao;

    @Resource
    private HyUserPunchClockDao hyUserPunchClockDao;

    @Resource
    private HyUserPunchClockDaoSelf hyUserPunchClockDaoSelf;

    @Resource
    private VillagePunchClockDayDao villagePunchClockDayDao;

    @Resource
    private HyPunchClockLocationService hyPunchClockLocationService;

    /**
     * 用户打卡
     * @param hyPunchClock
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> punchClock(HyPunchClock hyPunchClock) {
        //返回结果
        Map<String, Object> result = new HashMap<String, Object>();
        int totleNum = 0;
        //两小时无法打卡
//        HyPunchClock hyPunchClock2 = new HyPunchClock();
//        hyPunchClock2.setUserId(hyPunchClock.getUserId());
//        hyPunchClock2.setVillageId(hyPunchClock.getVillageId());
//        hyPunchClock2.setClockTime(new Date());
//        List<HyPunchClock> hyPunchClocks = hyPunchClockDaoSelf.queryAllNearTwoHour(hyPunchClock2);
//        if(hyPunchClocks.size() != 0){
//            result.put("status",4);
//            result.put("msg","两小时之内，请勿重复打卡！");
//            return result;
//        }
        //用户打卡
        HyPunchClockLocation hyPunchClockLocation = hyPunchClockLocationDao.queryById(hyPunchClock.getPunchClockLocationId());
        BigDecimal distance1 = new BigDecimal(GPSUtil.GetDistance(hyPunchClockLocation.getClockLongitude(), hyPunchClockLocation.getClockLatitude(), hyPunchClock.getClockLongitude(), hyPunchClock.getClockLatitude()));
        if(distance1.compareTo(hyPunchClockLocation.getClockR()) == 1){
            hyPunchClock.setClockStatus(2);
            result.put("status",2);
            result.put("msg","请在规定区域内打卡！");
            return result;
        }else{
            HyPunchClockTime paramHyPunchClockTime = new HyPunchClockTime();
            paramHyPunchClockTime.setPunchClockLocationId(hyPunchClockLocation.getId());
            List<HyPunchClockTime> hyPunchClockTimeList1 = hyPunchClockTimedao.queryAll(paramHyPunchClockTime);
            for (HyPunchClockTime tempHyPunchClockTime1: hyPunchClockTimeList1) {
                Time now = new Time(now().getHours(), now().getMinutes(), now().getSeconds());
                if(now.after(tempHyPunchClockTime1.getBeginTime()) && now.before(tempHyPunchClockTime1.getEndTime())){
                    //打卡时间正确
                    hyPunchClock.setClockStatus(1);
                    hyPunchClock.setPunchClockTimeId(tempHyPunchClockTime1.getId());
                    result.put("status",1);
                    result.put("msg","打卡成功！");
                    break;
                }else{
                    hyPunchClock.setClockStatus(3);
                    result.put("status",3);
                    result.put("msg","请在规定时间内打卡！");
                }
            }
        }
        //打卡循环结束时时间不正确则返回给前端
        if("3".equals(String.valueOf(result.get("status")))){
            return result;
        }
        hyPunchClock.setIsDelete(0);
        hyPunchClock.setCreateTime(new Date());
        hyPunchClock.setClockTime(new Date());
        hyPunchClock.setVillage(hyPunchClockLocation.getVillage());
        hyPunchClock.setVillageId(hyPunchClockLocation.getVillageId());
        hyPunchClock.setStreet(hyPunchClockLocation.getStreet());
        hyPunchClock.setStreetId(hyPunchClockLocation.getStreetId());
        hyPunchClockDao.insert(hyPunchClock);

        //用户打卡统计
        HyUserPunchClock paramHyUserPunchClock = new HyUserPunchClock();
        paramHyUserPunchClock.setUserId(hyPunchClock.getUserId());
        paramHyUserPunchClock.setCreateTime(new Date());
        List<HyUserPunchClock> hyUserPunchClocks = hyUserPunchClockDaoSelf.queryAll(paramHyUserPunchClock);
        if(hyUserPunchClocks.size() == 0) {
            //村地址列表
            HyPunchClockLocation paramHyPunchClockLocation = new HyPunchClockLocation();
            paramHyPunchClockLocation.setVillageId(hyPunchClock.getVillageId());
            List<HyPunchClockLocation> hyPunchClockLocationList = hyPunchClockLocationDao.queryAll(paramHyPunchClockLocation);
            for (HyPunchClockLocation tempHyPunchClockLocation : hyPunchClockLocationList) {
                HyPunchClockTime paramHyPunchClockTime = new HyPunchClockTime();
                paramHyPunchClockTime.setPunchClockLocationId(tempHyPunchClockLocation.getId());
                List<HyPunchClockTime> hyPunchClockTimeList = hyPunchClockTimedao.queryAll(paramHyPunchClockTime);
                totleNum = hyPunchClockTimeList.size() * hyPunchClockLocationList.size();
                for (HyPunchClockTime tempHyPunchClockTime : hyPunchClockTimeList) {
                    //个人打卡统计表对象
                    HyUserPunchClock hyUserPunchClock = new HyUserPunchClock();
                    hyUserPunchClock.setBeginTime(tempHyPunchClockTime.getBeginTime());
                    hyUserPunchClock.setEndTime(tempHyPunchClockTime.getEndTime());
                    hyUserPunchClock.setClockLongitude(tempHyPunchClockLocation.getClockLongitude());
                    hyUserPunchClock.setClockLatitude(tempHyPunchClockLocation.getClockLatitude());
                    hyUserPunchClock.setCreateTime(new Date());
                    hyUserPunchClock.setUserId(hyPunchClock.getUserId());
                    hyUserPunchClock.setStreetId(tempHyPunchClockLocation.getStreetId());
                    hyUserPunchClock.setVillageId(tempHyPunchClockLocation.getVillageId());
                    hyUserPunchClock.setClockStatus(0);
                    hyUserPunchClock.setIsDelete(0);
                    hyUserPunchClock.setPunchClockLocationId(tempHyPunchClockLocation.getId());
                    hyUserPunchClock.setPunchClockTimeId(tempHyPunchClockTime.getId());
                    hyUserPunchClockDao.insert(hyUserPunchClock);
                    //时间和地点一致
                    if(hyPunchClock.getPunchClockLocationId() == tempHyPunchClockLocation.getId() && hyPunchClock.getPunchClockTimeId() == tempHyPunchClockTime.getId() && hyPunchClock.getClockStatus() == 1){
                     //正常的打卡 更新操作
                        hyUserPunchClock.setClockStatus(1);
                        hyUserPunchClock.setPunchClockId(hyPunchClock.getId());
                        hyUserPunchClockDao.update(hyUserPunchClock);
                    }else if(hyPunchClock.getPunchClockLocationId() == tempHyPunchClockLocation.getId() && hyPunchClock.getClockStatus() != 1){
                        //非正常打卡 更新操作
                        HyUserPunchClock paramHyUserPunchClock2 = new HyUserPunchClock();
                        paramHyUserPunchClock2.setUserId(hyPunchClock.getUserId());
                        paramHyUserPunchClock2.setCreateTime(new Date());
                        paramHyUserPunchClock2.setPunchClockLocationId(hyPunchClock.getPunchClockLocationId());
                        paramHyUserPunchClock2.setPunchClockTimeId(tempHyPunchClockTime.getId());
                        paramHyUserPunchClock2.setClockStatus(0);
                        List<HyUserPunchClock> hyUserPunchClocks2 = hyUserPunchClockDaoSelf.queryAll(paramHyUserPunchClock2);
                        if(hyUserPunchClocks2.size() != 0 ){
                            HyUserPunchClock hyUserPunchClock3 = hyUserPunchClocks2.get(0);
                            hyUserPunchClock3.setClockStatus(2);
                            hyUserPunchClockDao.update(hyUserPunchClock3);
                        }
                    }
                }
            }
        }else{
            //具体的打卡点和打卡时间的个人打卡统计表对象
            HyUserPunchClock tempUserPunchClock = new HyUserPunchClock();
            tempUserPunchClock.setUserId(hyPunchClock.getUserId());
            tempUserPunchClock.setStreetId(hyPunchClock.getStreetId());
            tempUserPunchClock.setVillageId(hyPunchClock.getVillageId());
            tempUserPunchClock.setIsDelete(0);
            tempUserPunchClock.setPunchClockLocationId(hyPunchClock.getPunchClockLocationId());
            tempUserPunchClock.setPunchClockTimeId(hyPunchClock.getPunchClockTimeId());
            tempUserPunchClock.setCreateTime(new Date());
            List<HyUserPunchClock> hyUserPunchClockList = hyUserPunchClockDaoSelf.getTodayList(tempUserPunchClock);
            totleNum = hyUserPunchClocks.size();
            if(hyPunchClock.getPunchClockTimeId() != null){
                HyUserPunchClock hyUserPunchClock = hyUserPunchClockList.get(0);
                if(hyUserPunchClock.getClockStatus() == 0){
                    //时间和地点一致
                    if(hyPunchClock.getClockStatus() == 1){
                        //正常的打卡 更新操作
                        hyUserPunchClock.setClockStatus(1);

                    }else {
                        hyUserPunchClock.setClockStatus(2);
                    }
                }
                hyUserPunchClock.setPunchClockId(hyPunchClock.getId());
                //同一打卡点，打卡时间，当天只可以打卡一次
                HyUserPunchClock tempHyUserPunchClock = new HyUserPunchClock();
                tempHyUserPunchClock.setPunchClockTimeId(hyUserPunchClock.getPunchClockTimeId());
                tempHyUserPunchClock.setPunchClockLocationId(hyUserPunchClock.getPunchClockLocationId());
                tempHyUserPunchClock.setCreateTime(new Date());
                tempHyUserPunchClock.setClockStatus(1);
                List<HyUserPunchClock> todayList = hyUserPunchClockDaoSelf.getTodayList(tempHyUserPunchClock);
                if(todayList.size()>0){
                    int a =  5/0;
                }
                //更新拥挤信息
                hyUserPunchClockDao.update(hyUserPunchClock);
            }
        }

        //村统计表对象
        HyUserPunchClock paramHyUserPunchClock4 = new HyUserPunchClock();
        paramHyUserPunchClock4.setUserId(hyPunchClock.getUserId());
        paramHyUserPunchClock4.setCreateTime(new Date());
        paramHyUserPunchClock4.setClockStatus(1);
        List<HyUserPunchClock> hyUserPunchClocks4= hyUserPunchClockDaoSelf.queryAll(paramHyUserPunchClock4);

        if(totleNum == hyUserPunchClocks4.size()){
            VillagePunchClockDay villagePunchClockDay = new VillagePunchClockDay();
            villagePunchClockDay.setUserId(hyPunchClock.getUserId());
            villagePunchClockDay.setClockStatus(1);
            villagePunchClockDay.setClockDate(LocalDate.now());

            villagePunchClockDay.setIsDelete(0);
            villagePunchClockDay.setStreet(hyPunchClockLocation.getStreet());
            villagePunchClockDay.setStreetId(hyPunchClockLocation.getStreetId());
            villagePunchClockDay.setVillage(hyPunchClockLocation.getVillage());
            villagePunchClockDay.setVillageId(hyPunchClockLocation.getVillageId());
            //
            List<VillagePunchClockDay> list = villagePunchClockDayDao.queryAll(villagePunchClockDay);
            if (list.size() == 0) {
                villagePunchClockDay.setCreateTime(new Date());
                //村统计
                villagePunchClockDayDao.insert(villagePunchClockDay);
            }
        }
        return result;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HyPunchClock queryById(Long id) {
        return this.hyPunchClockDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<HyPunchClock> queryAllByLimit(int offset, int limit) {
        return this.hyPunchClockDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param hyPunchClock 实例对象
     * @return 实例对象
     */
    @Override
    public HyPunchClock insert(HyPunchClock hyPunchClock) {
        this.hyPunchClockDao.insert(hyPunchClock);
        return hyPunchClock;
    }

    /**
     * 修改数据
     *
     * @param hyPunchClock 实例对象
     * @return 实例对象
     */
    @Override
    public HyPunchClock update(HyPunchClock hyPunchClock) {
        this.hyPunchClockDao.update(hyPunchClock);
        return this.queryById(hyPunchClock.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.hyPunchClockDao.deleteById(id) > 0;
    }


    /**
     * 查询多条数据
     *
     * @param map 查询起始位置
     * @return 对象列表
     */
    @Override
    public PageInfo getHyPunchClockList(Map<String,String> map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer pageNo = 1;
        Integer pageSize = 12;
        if(StringUtils.isNotBlank(map.get("pageNo"))){
            pageNo = Integer.valueOf(map.get("pageNo"));//页码
        }
        if(StringUtils.isNotBlank(map.get("pageSize"))){
            pageSize = Integer.valueOf(map.get("pageSize"));//页面数
        }
        HyPunchClock hyPunchClock = new HyPunchClock();
        if(StringUtils.isNotBlank(map.get("userId"))){
            hyPunchClock.setUserId(Long.valueOf(map.get("userId")));
        }
        if(StringUtils.isNotBlank(map.get("clockTime"))){
            hyPunchClock.setClockTime(sdf.parse(map.get("clockTime")));
        }
        if(StringUtils.isNotBlank(map.get("streetId"))){
            hyPunchClock.setStreetId(Long.valueOf(map.get("streetId")));
        }
        if(StringUtils.isNotBlank(map.get("street"))){
            hyPunchClock.setStreet(map.get("street"));
        }
        if(StringUtils.isNotBlank(map.get("villageId"))){
            hyPunchClock.setVillageId(Long.valueOf(map.get("villageId")));
        }
        if(StringUtils.isNotBlank(map.get("village"))){
            hyPunchClock.setVillage(map.get("village"));
        }
        if(StringUtils.isNotBlank(map.get("isDelete"))){
            hyPunchClock.setIsDelete(Integer.valueOf(map.get("isDelete")));
        }
        if(StringUtils.isNotBlank(map.get("createTime"))){
            hyPunchClock.setCreateTime(sdf.parse(map.get("createTime")));
        }
        PageHelper.startPage(pageNo,pageSize);
        List<HyPunchClock> hyPunchClockList = hyPunchClockDaoSelf.queryAll(hyPunchClock);
        PageInfo result = new PageInfo(hyPunchClockList);
        return result;
    }

    /**
     *
     * 获取打卡记录列表
     * @param hyPunchClock 查询的参数
     * @return
     */
    @Override
    public List<HyPunchClock> getTodayHyPunchClockList(HyPunchClock hyPunchClock) {
        hyPunchClock.setClockTime(new Date());
        List<HyPunchClock> hyPunchClockList = hyPunchClockDaoSelf.queryAll(hyPunchClock);
        return hyPunchClockList;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 3);
        if("3".equals(String.valueOf(map.get("status")))){
            System.out.println("状态核对成功");
        }

    }
}