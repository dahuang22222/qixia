package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.HyUser;
import com.changjianghuyu.qixia.web.entity.HyUserOauth;
import com.changjianghuyu.qixia.web.service.HyUserOauthService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 第三方信息表(HyUserOauth)表控制层
 *
 * @author huangkewanggetUserPunchClockList
 * @since 2020-11-11 16:25:08
 */
@RestController
@RequestMapping("/hyUserOauth")
public class HyUserOauthController {
    /**
     * 服务对象
     */
    @Resource
    private HyUserOauthService hyUserOauthService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public HyUserOauth selectOne(Long id) {
        return this.hyUserOauthService.queryById(id);
    }

}