package com.changjianghuyu.qixia.web.dao;

import com.changjianghuyu.qixia.web.entity.SysNotice;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 系统公告表(SysNotice)表数据库访问层
 *
 * @author huangkewang
 * @since 2020-11-10 18:34:18
 */
public interface SysNoticeDaoSelf {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysNotice 实例对象
     * @return 对象列表
     */
    List<SysNotice> queryAll(SysNotice sysNotice);

    /**
     * 小程序公告列表
     *
     * @param
     * @return 对象列表
     */
    List<SysNotice> userGetNoticeListByPage();

    /**
     * 批量删除
     * @param idList
     * @return
     */
    int deleteByIds(List<Long> idList);
}