package com.changjianghuyu.qixia.web.service.impl;

import com.changjianghuyu.qixia.web.common.secret.MD5Utils;
import com.changjianghuyu.qixia.web.dao.HyUserDao;
import com.changjianghuyu.qixia.web.dao.HyUserDaoSelf;
import com.changjianghuyu.qixia.web.dao.SysAreaDao;
import com.changjianghuyu.qixia.web.entity.HyUser;
import com.changjianghuyu.qixia.web.entity.SysArea;
import com.changjianghuyu.qixia.web.service.HyUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户信息表(HyUser)表服务实现类
 *
 * @author makejava
 * @since 2020-11-12 18:31:23
 */
@Service("hyUserService")
public class HyUserServiceImpl implements HyUserService {
    @Resource
    private HyUserDao hyUserDao;

    @Resource
    private HyUserDaoSelf hyUserDaoSelf;

    @Resource
    private SysAreaDao sysAreaDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HyUser queryById(Long id) {
        return this.hyUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<HyUser> queryAllByLimit(int offset, int limit) {
        return this.hyUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param hyUser 实例对象
     * @return 实例对象
     */
    @Override
    public HyUser insert(HyUser hyUser) {
        if(StringUtils.isBlank(hyUser.getPhone())){
            return null;
        }
        //判断用户是否已经存在
        HyUser user = new HyUser();
        user.setPhone(hyUser.getPhone());
        List<HyUser> hyUsers = hyUserDao.queryAll(user);
        if(!CollectionUtils.isEmpty(hyUsers)){
            return null;
        }
        //密码加密
        if(StringUtils.isNotBlank(hyUser.getPassword())) {
            String pwd = MD5Utils.encodedMD5(hyUser.getPassword());
            hyUser.setPassword(pwd);
        }else{
            String pwd = MD5Utils.encodedMD5("cjhy"+hyUser.getPhone().substring(7,11));
            hyUser.setPassword(pwd);
        }
        if(null != hyUser.getVillageId()){
            SysArea sysArea = sysAreaDao.queryById(hyUser.getVillageId());
            hyUser.setVillage(sysArea.getAreaName());
            hyUser.setStreetId(Long.valueOf(sysArea.getParentId()));
            SysArea streetArea = sysAreaDao.queryById(Long.valueOf(sysArea.getParentId()));
            hyUser.setStreet(streetArea.getAreaName());
        }
        hyUser.setIsDelete(0);
        hyUser.setCreateTime(new Date());
        //插入用户
        this.hyUserDao.insert(hyUser);
        return hyUser;
    }

    /**
     * 新增用户集合
     *
     * @param userList 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<HyUser> insertUserList( List<HyUser> userList) {
        List<HyUser> userResult = new ArrayList<HyUser>();
        for (HyUser user : userList)
        //判断用户是否已经存在
        {
            HyUser tempUser = new HyUser();
            tempUser.setPhone(user.getPhone());
            List<HyUser> hyUsers = hyUserDao.queryAll(tempUser);
            if(!CollectionUtils.isEmpty(hyUsers)){
                userResult.clear();
                new Exception();
            }
            //密码加密
                if(StringUtils.isNotBlank(user.getPassword())) {
                    String pwd = MD5Utils.encodedMD5(user.getPassword());
                    user.setPassword(pwd);
            }else{
                    String pwd = MD5Utils.encodedMD5("cjhy"+user.getPhone().substring(7,11));
                    user.setPassword(pwd);
                }
            //插入用户
            user.setIsDelete(0);
            user.setCreateTime(new Date());
            this.hyUserDao.insert(user);
            userResult.add(user);
        }
        return userResult;
    }

    /**
     * 修改数据
     *
     * @param hyUser 实例对象
     * @return 实例对象
     */
    @Override
    public HyUser update(HyUser hyUser) {
        //密码加密
        if(StringUtils.isNotBlank(hyUser.getPassword())) {
            String pwd = MD5Utils.encodedMD5(hyUser.getPassword());
            hyUser.setPassword(pwd);
        }
        if(hyUser.getUserType() != null && hyUser.getUserType() == 1) {
            hyUser.setVillageId(null);
            hyUser.setVillage(null);
            hyUser.setStreetId(null);
            hyUser.setStreet(null);
        }
        this.hyUserDao.update(hyUser);
        return this.queryById(hyUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.hyUserDao.deleteById(id) > 0;
    }

    /**
     * 查询多条数据
     *
     * @param map 查询起始位置
     * @return 对象列表
     */
    @Override
    public PageInfo getUserList(Map<String,String> map) {
        Integer pageNo = 1;
        Integer pageSize = 12;
        if(StringUtils.isNotBlank(map.get("pageNo"))){
            pageNo = Integer.valueOf(map.get("pageNo"));//页码
        }
        if(StringUtils.isNotBlank(map.get("pageSize"))){
            pageSize = Integer.valueOf(map.get("pageSize"));//页面数
        }
        HyUser hyUser = new HyUser();
        if(StringUtils.isNotBlank(map.get("userName"))){
            hyUser.setUserName(map.get("userName"));
        }
        if(StringUtils.isNotBlank(map.get("userType"))){
            hyUser.setUserType(Integer.valueOf(map.get("userType")));
        }
        if(StringUtils.isNotBlank(map.get("phone"))){
            hyUser.setPhone("phone");
        }
        if(StringUtils.isNotBlank(map.get("village"))){
            hyUser.setVillage(map.get("village"));
        }
        if(StringUtils.isNotBlank(map.get("street"))){
            hyUser.setStreet(map.get("street"));
        }
        PageHelper.startPage(pageNo,pageSize);
        List<HyUser> userList = hyUserDaoSelf.queryAllByUser(hyUser);
        PageInfo result = new PageInfo(userList);
        return result;
    }

    @Override
    public int deleteByIds(String ids) {
        String[] tempList = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String id :tempList){
            idList.add(Long.valueOf(id));
        }
        return hyUserDaoSelf.deleteByIds(idList);
    }

    @Override
    public Map<String, Object> updateUserPassword(Map<String,String> map) {
        Map<String, Object> result = new HashMap<>();
        HyUser hyUser = new HyUser();
        hyUser.setId(Long.valueOf(map.get("id")));
        if(StringUtils.isNotBlank(map.get("oldPassword"))) {
            HyUser tempUser = hyUserDao.queryById(Long.valueOf(map.get("id")));
            String oldPassword = MD5Utils.encodedMD5(map.get("oldPassword"));
            if (!oldPassword.equals(tempUser.getPassword())){
                result.put("message","旧密码错误！");
                return result;
            }
        }else{
            result.put("message","旧密码不存在！");
        }
        String newPassword = map.get("newPassword");
        //密码加密
        if(StringUtils.isNotBlank(map.get("newPassword"))) {
            String password = MD5Utils.encodedMD5(newPassword);
            hyUser.setPassword(password);
        }

        this.hyUserDao.update(hyUser);
        result.put("user",this.queryById(hyUser.getId()));
        result.put("message","更新成功！");
        return result;
    }
}