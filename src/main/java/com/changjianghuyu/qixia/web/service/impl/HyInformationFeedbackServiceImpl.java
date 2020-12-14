package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.dao.HyInformationFeedbackDao;
import com.changjianghuyu.qixia.web.dao.HyInformationFeedbackDaoSelf;
import com.changjianghuyu.qixia.web.dao.HyUserDao;
import com.changjianghuyu.qixia.web.entity.HyInformationFeedback;
import com.changjianghuyu.qixia.web.entity.HyUser;
import com.changjianghuyu.qixia.web.service.HyInformationFeedbackService;
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
 * 信息反馈表(HyInformationFeedback)表服务实现类
 *
 * @author makejava
 * @since 2020-11-11 15:10:55
 */
@Service("hyInformationFeedbackService")
public class HyInformationFeedbackServiceImpl implements HyInformationFeedbackService {
    @Resource
    private HyInformationFeedbackDao hyInformationFeedbackDao;

    @Resource
    private HyInformationFeedbackDaoSelf hyInformationFeedbackDaoSelf;

    @Resource
    private HyUserDao hyUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HyInformationFeedback queryById(Long id) {
        return this.hyInformationFeedbackDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<HyInformationFeedback> queryAllByLimit(int offset, int limit) {
        return this.hyInformationFeedbackDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param hyInformationFeedback 实例对象
     * @return 实例对象
     */
    @Override
    public HyInformationFeedback insert(HyInformationFeedback hyInformationFeedback) {
        hyInformationFeedback.setIsDelete(0);
        hyInformationFeedback.setCreateTime(new Date());
        hyInformationFeedback.setProvideTime(new Date());
        HyUser user = hyUserDao.queryById(hyInformationFeedback.getProvideUserId());
        hyInformationFeedback.setStreet(user.getStreet());
        hyInformationFeedback.setStreetId(user.getStreetId());
        hyInformationFeedback.setVillageId(user.getVillageId());
        hyInformationFeedback.setVillage(user.getVillage());
        hyInformationFeedback.setStatus(1);
        hyInformationFeedback.setProvideName(user.getUserName());
        this.hyInformationFeedbackDao.insert(hyInformationFeedback);
        return hyInformationFeedback;
    }

    /**
     * 修改数据
     *
     * @param hyInformationFeedback 实例对象
     * @return 实例对象
     */
    @Override
    public HyInformationFeedback update(HyInformationFeedback hyInformationFeedback) {
        if(null != hyInformationFeedback.getHandlingUserId()){
            hyInformationFeedback.setStatus(3);
            hyInformationFeedback.setHandlingTime(new Date());
        }
        this.hyInformationFeedbackDao.update(hyInformationFeedback);
        return this.queryById(hyInformationFeedback.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.hyInformationFeedbackDao.deleteById(id) > 0;
    }

    /**
     * 查询多条数据
     *
     * @param map 查询起始位置
     * @return 对象列表
     */
    @Override
    public PageInfo getInformationFeedbackList(Map<String,String> map) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer pageNo = 1;
        Integer pageSize = 12;
        if(StringUtils.isNotBlank(map.get("pageNo"))){
            pageNo = Integer.valueOf(map.get("pageNo"));//页码
        }
        if(StringUtils.isNotBlank(map.get("pageSize"))){
            pageSize = Integer.valueOf(map.get("pageSize"));//页面数
        }
        HyInformationFeedback hyInformationFeedback = new HyInformationFeedback();
        if(StringUtils.isNotBlank(map.get("content"))){
            hyInformationFeedback.setContent(map.get("content"));
        }
        if(StringUtils.isNotBlank(map.get("provideUserId"))){
            hyInformationFeedback.setProvideUserId(Long.valueOf(map.get("provideUserId")));
        }
        if(StringUtils.isNotBlank(map.get("provideName"))){
            hyInformationFeedback.setProvideName(map.get("provideName"));
        }
        if(StringUtils.isNotBlank(map.get("provideTime"))){
            hyInformationFeedback.setProvideTime(sdf.parse(map.get("provideTime")));
        }
        if(StringUtils.isNotBlank(map.get("content"))){
            hyInformationFeedback.setContent(map.get("content"));
        }
        if(StringUtils.isNotBlank(map.get("remarks"))){
            hyInformationFeedback.setRemarks(map.get("remarks"));
        }
        if(StringUtils.isNotBlank(map.get("streetId"))){
            hyInformationFeedback.setStreetId(Long.valueOf(map.get("streetId")));
        }
        if(StringUtils.isNotBlank(map.get("street"))){
            hyInformationFeedback.setStreet(map.get("street"));
        }
        if(StringUtils.isNotBlank(map.get("villageId"))){
            hyInformationFeedback.setVillageId(Long.valueOf(map.get("villageId")));
        }
        if(StringUtils.isNotBlank(map.get("village"))){
            hyInformationFeedback.setVillage(map.get("village"));
        }
        if(StringUtils.isNotBlank(map.get("handlingOpinions"))){
            hyInformationFeedback.setHandlingOpinions(map.get("handlingOpinions"));
        }
        if(StringUtils.isNotBlank(map.get("handlingUserId"))){
            hyInformationFeedback.setHandlingUserId(Long.valueOf(map.get("handlingUserId")));
        }
        if(StringUtils.isNotBlank(map.get("handlingUserName"))){
            hyInformationFeedback.setHandlingOpinions(map.get("handlingUserName"));
        }
        if(StringUtils.isNotBlank(map.get("handlingTime"))){
            hyInformationFeedback.setHandlingTime(sdf.parse(map.get("handlingTime")));
        }
        if(StringUtils.isNotBlank(map.get("feedbackAddress"))){
            hyInformationFeedback.setFeedbackAddress(map.get("feedbackAddress"));
        }
        if(StringUtils.isNotBlank(map.get("status"))){
            hyInformationFeedback.setStatus(Integer.valueOf(map.get("status")));
        }
        if(StringUtils.isNotBlank(map.get("isDelete"))){
            hyInformationFeedback.setIsDelete(Integer.valueOf(map.get("isDelete")));
        }
        PageHelper.startPage(pageNo,pageSize);
        List<HyInformationFeedback> informationFeedbackList = hyInformationFeedbackDaoSelf.queryAll(hyInformationFeedback);
        PageInfo result = new PageInfo(informationFeedbackList);
        return result;
    }

    @Override
    public int deleteByIds(String ids) {
        String[] tempList = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String id :tempList){
            idList.add(Long.valueOf(id));
        }
        return hyInformationFeedbackDaoSelf.deleteByIds(idList);
    }
}