package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.dao.SysNoticeDao;
import com.changjianghuyu.qixia.web.dao.SysNoticeDaoSelf;
import com.changjianghuyu.qixia.web.entity.SysArea;
import com.changjianghuyu.qixia.web.entity.SysNotice;
import com.changjianghuyu.qixia.web.service.SysNoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统公告表(SysNotice)表服务实现类
 *
 * @author makejava
 * @since 2020-11-10 18:34:18
 */
@Service("sysNoticeService")
public class SysNoticeServiceImpl implements SysNoticeService {
    @Resource
    private SysNoticeDao sysNoticeDao;

    @Resource
    private SysNoticeDaoSelf sysNoticeDaoSelf;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysNotice queryById(Long id) {
        return this.sysNoticeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysNotice> queryAllByLimit(int offset, int limit) {
        return this.sysNoticeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysNotice 实例对象
     * @return 实例对象
     */
    @Override
    public SysNotice insert(SysNotice sysNotice) {
        sysNotice.setIsDelete(0);
        sysNotice.setCreateTime(new Date());
        if(null == sysNotice.getIsTop()){
            sysNotice.setIsTop(0);
        }
        if(null == sysNotice.getTimeUser()){
            sysNotice.setTimeUser(0);
        }
        this.sysNoticeDao.insert(sysNotice);
        return sysNotice;
    }

    /**
     * 修改数据
     *
     * @param sysNotice 实例对象
     * @return 实例对象
     */
    @Override
    public SysNotice update(SysNotice sysNotice) {
        this.sysNoticeDao.update(sysNotice);
        return this.queryById(sysNotice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysNoticeDao.deleteById(id) > 0;
    }

    @Override
    public PageInfo getNoticeListByPage(Map<String,String> map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer pageNo = 1;
        Integer pageSize = 12;
        if(StringUtils.isNotBlank(map.get("pageNo"))){
            pageNo = Integer.valueOf(map.get("pageNo"));//页码
        }
        if(StringUtils.isNotBlank(map.get("pageSize"))){
            pageSize = Integer.valueOf(map.get("pageSize"));//页面数
        }
        SysNotice sysNotice = new SysNotice();
        if(StringUtils.isNotBlank(map.get("title"))){
            sysNotice.setTitle(map.get("title"));
        }
        if(StringUtils.isNotBlank(map.get("content"))){
            sysNotice.setContent(map.get("content"));
        }
        /* 是否使用时间范围：0：不使用 1：使用*/
        if(StringUtils.isNotBlank(map.get("timeUser"))){
            sysNotice.setTimeUser(Integer.valueOf(map.get("timeUser")));
        }
        if(StringUtils.isNotBlank(map.get("beginTime"))){
            sysNotice.setBeginTime(sdf.parse(map.get("beginTime")));
        }
        if(StringUtils.isNotBlank(map.get("endTime"))){
            sysNotice.setEndTime(sdf.parse(map.get("endTime")));
        }
        PageHelper.startPage(pageNo,pageSize);
        List<SysNotice> list = sysNoticeDaoSelf.queryAll(sysNotice);
        PageInfo result = new PageInfo(list);
        return result;
    }

    @Override
    public PageInfo userGetNoticeListByPage(Map<String, String> map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer pageNo = 1;
        Integer pageSize = 12;
        if(StringUtils.isNotBlank(map.get("pageNo"))){
            pageNo = Integer.valueOf(map.get("pageNo"));//页码
        }
        if(StringUtils.isNotBlank(map.get("pageSize"))){
            pageSize = Integer.valueOf(map.get("pageSize"));//页面数
        }
        PageHelper.startPage(pageNo,pageSize);
        List<SysNotice> list = sysNoticeDaoSelf.userGetNoticeListByPage();
        PageInfo result = new PageInfo(list);
        return result;
    }

    @Override
    public int deleteByIds(String ids) {
        String[] tempList = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String id :tempList){
            idList.add(Long.valueOf(id));
        }
        return sysNoticeDaoSelf.deleteByIds(idList);
    }
}