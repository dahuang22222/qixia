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
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public SysArea insert(SysArea sysArea) {
        if(StringUtils.isNotBlank(sysArea.getAreaCode())){
            SysArea tempSysArea = new SysArea();
            tempSysArea.setAreaCode(sysArea.getAreaCode());
            List<SysArea> sysAreaList = sysAreaDao.queryAll(tempSysArea);
            if(sysAreaList.size() != 0){
                return null;
            }
        }
        sysArea.setIsDelete(0);
        sysArea.setCreateTime(new Date());
        sysArea.setDispOrder(5);
        this.sysAreaDao.insert(sysArea);
        return sysArea;
    }

    /**
     * 修改数据
     *
     * @param sysArea 实例对象
     * @return 实例对象
     */
    @Override
    public SysArea update(SysArea sysArea) {
        this.sysAreaDao.update(sysArea);
        return this.queryById(sysArea.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysAreaDao.deleteById(id) > 0;
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
        if(StringUtils.isNotBlank(map.get("areaName"))){
            sysArea.setAreaName(map.get("areaName"));
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
     * @param sysArea
     * @return
     */
    @Override
    public List<SysArea> getAreaList(SysArea sysArea) {
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