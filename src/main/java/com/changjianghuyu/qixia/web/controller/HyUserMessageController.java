package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.BaseController;
import com.changjianghuyu.qixia.web.common.jwt.UserInfo;
import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.HyUserMessage;
import com.changjianghuyu.qixia.web.service.HyUserMessageService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户消息(HyUserMessage)表控制层
 *
 * @author makejava
 * @since 2021-01-09 23:40:09
 */
@RestController
@RequestMapping("/hyUserMessage")
public class HyUserMessageController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private HyUserMessageService hyUserMessageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public HyUserMessage selectOne(Long id) {
        return this.hyUserMessageService.queryById(id);
    }

    @GetMapping("/queryMessageRead")
    public MsgHander queryAll(HyUserMessage hyUserMessage) {
        MsgHander msg = new MsgHander();

        UserInfo userInfo = getUserInfo();
        Long userId = userInfo.getId();
        hyUserMessage.setUserId(userId);
        hyUserMessage.setIsRead(0);
        hyUserMessage.setMethond(1);
        List<HyUserMessage> hyUserMessageList = hyUserMessageService.queryAll(hyUserMessage);
        msg.setContext(hyUserMessageList.size());
        if (hyUserMessageList.size()>0) {
            msg.setMessage("您有未读消息！");
        }else{
            msg.setMessage("全部已读！");
        }
        return msg;
    }

}