package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.dao.SysAreaDao;
import com.changjianghuyu.qixia.web.dao.SysAreaDaoSelf;
import com.changjianghuyu.qixia.web.entity.SysArea;
import com.changjianghuyu.qixia.web.entity.SysAreaExpand;
import com.changjianghuyu.qixia.web.service.SysAreaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 地区表(SysArea)表服务实现类
 *
 * @author makejava
 * @since 2020-11-12 15:17:16
 */
@Service("sysAreaService")
public class SysAreaServiceImpl implements SysAreaService {
    @Resource
    private SysAreaDao sysAreaDao;

    @Resource
    private SysAreaDaoSelf sysAreaDaoSelf;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysArea queryById(Long id) {
        return this.sysAreaDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysArea> queryAllByLimit(int offset, int limit) {
        return this.sysAreaDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysArea 实例对象
     * @return 实例对象
     */
    @Override
    public Map<String, Object> insert(SysArea sysArea) {
        HashMap<String, Object> result = new HashMap<>();
        if(StringUtils.isNotBlank(sysArea.getAreaCode())){
            SysArea tempSysArea = new SysArea();
            tempSysArea.setAreaCode(sysArea.getAreaCode());
            List<SysArea> sysAreaList = sysAreaDao.queryAll(tempSysArea);
            if(sysAreaList.size() != 0){
                result.put("message","地区编码重复！");
                return result;
            }
        }
        if(StringUtils.isNotBlank(sysArea.getAreaName())){
            SysArea tempSysArea = new SysArea();
            tempSysArea.setAreaName(sysArea.getAreaName());
            tempSysArea.setLevel(sysArea.getLevel());
            List<SysArea> sysAreaList = sysAreaDao.queryAll(tempSysArea);
            if(sysAreaList.size() != 0){
                result.put("message","地区名称重复！");
                return result;
            }
        }
        sysArea.setIsDelete(0);
        sysArea.setCreateTime(new Date());
        sysArea.setDispOrder(5);
        this.sysAreaDao.insert(sysArea);
        result.put("sysArea",sysArea);
        result.put("message","插入成功！");
        return result;
    }

    /**
     * 修改数据
     *
     * @param sysArea 实例对象
     * @return 实例对象
     */
    @Override
    public Map<String, Object> update(SysArea sysArea) {
        Map<String, Object> result = new HashMap<>();
        SysArea tempSysArea = sysAreaDao.queryById(sysArea.getId());
        if(tempSysArea.getLevel() != null && tempSysArea.getLevel() == 8L  && tempSysArea.getLevel() != sysArea.getLevel()){
            SysArea tempSysArea2 =  new SysArea();
            tempSysArea2.setParentId(String.valueOf(sysArea.getId()));
            List<SysArea> areaList = sysAreaDao.queryAll(tempSysArea2);
            if(areaList.size() > 0){
                result.put("message","请先清空子目录");
                return result;
            }
        }
        if(StringUtils.isNotBlank(sysArea.getAreaName())){
            SysArea tempSysArea2 = new SysArea();
            tempSysArea2.setAreaName(sysArea.getAreaName());
            List<SysArea> sysAreaList = sysAreaDao.queryAll(tempSysArea2);
            if(sysAreaList.size() != 0){
                result.put("message","地区名称重复！");
                return result;
            }
        }
        if(StringUtils.isNotBlank(sysArea.getAreaCode())){
            SysArea tempSysArea2 = new SysArea();
            tempSysArea2.setAreaCode(sysArea.getAreaCode());
            List<SysArea> sysAreaList = sysAreaDao.queryAll(tempSysArea2);
            if(sysAreaList.size() != 0){
                result.put("message","地区编码重复！");
                return result;
            }
        }
        this.sysAreaDao.update(sysArea);
        result.put("sysArea",this.queryById(sysArea.getId()));
        result.put("message","更新成功");
        return result;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public  Map<String, Object> deleteById(Long id) {
        Map<String, Object> result = new HashMap<>();
        SysArea tempSysArea = sysAreaDao.queryById(id);
        if(null != tempSysArea.getLevel() && tempSysArea.getLevel() == 8L){
            SysArea tempSysArea2 =  new SysArea();
            tempSysArea2.setParentId(String.valueOf(id));
            List<SysArea> areaList = sysAreaDao.queryAll(tempSysArea2);
            if(areaList.size() > 0){
                result.put("status","-1");
                result.put("message","请先清空子目录");
                return result;
            }
        }
        result.put("status",this.sysAreaDao.deleteById(id));
        result.put("message","更新成功！");
        return result;
    }

    /**
     * 分页查询地理信息
     * @param map
     * @return
     */
    @Override
    public PageInfo getAreaListByPage(Map<String,String> map) {
        Integer pageNo = 1;
        Integer pageSize = 12;
        if(StringUtils.isNotBlank(map.get("pageNo"))){
            pageNo = Integer.valueOf(map.get("pageNo"));//页码
        }
        if(StringUtils.isNotBlank(map.get("pageSize"))){
            pageSize = Integer.valueOf(map.get("pageSize"));//页面数
        }
        SysArea sysArea = new SysArea();
        sysArea.setIsDelete(0);
        if(StringUtils.isNotBlank(map.get("areaCode"))){
            sysArea.setAreaCode(map.get("areaCode"));
        }
        if(StringUtils.isNotBlank(map.get("parentId"))){
            sysArea.setParentId(map.get("parentId"));
        }
        if(StringUtils.isNotBlank(map.get("parentId"))){
            sysArea.setParentId(map.get("parentId"));
        }
        if(StringUtils.isNotBlank(map.get("areaName"))){
            sysArea.setAreaName(map.get("areaName"));
        }
        if(StringUtils.isNotBlank(map.get("streetId")) && StringUtils.isBlank(map.get("villageId"))){
            sysArea.setId(Long.valueOf(map.get("streetId")));
        }
        if(StringUtils.isNotBlank(map.get("villageId"))){
            sysArea.setId(Long.valueOf(map.get("villageId")));
        }
        if(StringUtils.isNotBlank(map.get("level"))){
            sysArea.setLevel(Integer.valueOf(map.get("level")));
        }
        PageHelper.startPage(pageNo,pageSize);
        List<SysArea> list = sysAreaDaoSelf.queryAll(sysArea);
        PageInfo result = new PageInfo(list);
        return result;
    }

    /**
     * 查询所有街道列表
     * @param map
     * @return
     */
    @Override
    public List<SysArea> getAreaList(Map<String,String> map) {
        SysArea sysArea = new SysArea();
        if(StringUtils.isNotBlank(map.get("areaCode"))){
            sysArea.setAreaCode(map.get("areaCode"));
        }
        if(StringUtils.isNotBlank(map.get("parentId"))){
            sysArea.setParentId(map.get("parentId"));
        }
        if(StringUtils.isNotBlank(map.get("areaName"))){
            sysArea.setAreaName(map.get("areaName"));
        }

        if(StringUtils.isNotBlank(map.get("streetId")) && StringUtils.isBlank(map.get("villageId"))){
            sysArea.setId(Long.valueOf(map.get("streetId")));
        }
        if(StringUtils.isNotBlank(map.get("villageId"))){
            sysArea.setId(Long.valueOf(map.get("villageId")));
        }
        if(StringUtils.isNotBlank(map.get("level"))){
            sysArea.setLevel(Integer.valueOf(map.get("level")));
            //管理员获取街道列表时
            if("8".equals(map.get("level")) && StringUtils.isNotBlank(map.get("streetId"))){
                sysArea.setId(Long.valueOf(map.get("streetId")));
            }
        }
        sysArea.setIsDelete(0);
        return  sysAreaDaoSelf.queryAll(sysArea);
    }

    @Override
    public PageInfo getAreaExpandListByPage(Map<String, String> map) {
        Integer pageNo = 1;
        Integer pageSize = 12;
        ArrayList<SysAreaExpand> areaExpandList = new ArrayList<SysAreaExpand>();

        if(StringUtils.isNotBlank(map.get("pageNo"))){
            pageNo = Integer.valueOf(map.get("pageNo"));//页码
        }
        if(StringUtils.isNotBlank(map.get("pageSize"))){
            pageSize = Integer.valueOf(map.get("pageSize"));//页面数
        }
        SysArea sysArea = new SysArea();
        sysArea.setIsDelete(0);
        sysArea.setLevel(8);
        if(StringUtils.isNotBlank(map.get("areaCode"))){
            sysArea.setAreaCode(map.get("areaCode"));
        }
        if(StringUtils.isNotBlank(map.get("parentId"))){
            sysArea.setParentId(map.get("parentId"));
        }
        if(StringUtils.isNotBlank(map.get("areaName"))){
            sysArea.setAreaName(map.get("areaName"));
        }
        if(StringUtils.isNotBlank(map.get("level"))){
            sysArea.setLevel(Integer.valueOf(map.get("level")));
        }
        PageHelper.startPage(pageNo,pageSize);
        List<SysArea> list = sysAreaDaoSelf.queryAll(sysArea);
        for (SysArea area: list) {
            SysArea childArea = new SysArea();
            childArea.setParentId(String.valueOf(area.getId()));
            List<SysArea> childAreaList = sysAreaDao.queryAll(childArea);
            SysAreaExpand sysAreaExpand = new SysAreaExpand(area, childAreaList);
            areaExpandList.add(sysAreaExpand);
        }
        PageInfo result = new PageInfo(areaExpandList);
        return result;
    }

    @Override
    public int deleteByIds(String ids) {
        String[] tempList = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String id :tempList){
            idList.add(Long.valueOf(id));
        }
        return sysAreaDaoSelf.deleteByIds(idList);
    }

}