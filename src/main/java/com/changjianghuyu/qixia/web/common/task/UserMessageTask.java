package com.changjianghuyu.qixia.web.common.task;

import com.changjianghuyu.qixia.web.common.utils.SpringContextBeanUtil;
import com.changjianghuyu.qixia.web.entity.HyUser;
import com.changjianghuyu.qixia.web.entity.HyUserMessage;
import com.changjianghuyu.qixia.web.service.HyUserMessageService;
import com.changjianghuyu.qixia.web.service.HyUserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ResolvableType;
import org.springframework.jca.context.SpringContextResourceAdapter;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class UserMessageTask implements Runnable{

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 关联的消息表id
     */
    private Long connectionId;
    /**
     * 消息类型：1.系统公告
     */
    private Integer type;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 是否删除：0未删除 1删除
     */
    private Integer isDelete;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Long connectionId) {
        this.connectionId = connectionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public UserMessageTask(Long userId, Long connectionId, Integer type, String content) {
        this.userId = userId;
        this.connectionId = connectionId;
        this.type = type;
        this.content = content;
    }

    @Resource
    private static final HyUserService hyUserService;

    @Resource
    private static final HyUserMessageService hyUserMessageService;

    static {
        hyUserService = SpringContextBeanUtil.getBeanByClass(HyUserService.class);
        hyUserMessageService = SpringContextBeanUtil.getBeanByClass(HyUserMessageService.class);
    }

    @Override
    public void run() {
        System.out.println("线程进入run方法");
        sendMsg();
    }

    /**
     * 发送消息
     *
     * @param
     */
    public void sendMsg() {
        List<HyUser> hyUsers = hyUserService.queryAll(new HyUser());
        for (HyUser user: hyUsers) {
            Long userId = user.getId();
            HyUserMessage hyUserMessage = new HyUserMessage();
            hyUserMessage.setUserId(userId);
            hyUserMessage.setType(this.type);
            hyUserMessage.setConnectionId(this.connectionId);
            hyUserMessage.setContent(this.content);
            hyUserMessage.setCreateTime(new Date());
            hyUserMessage.setIsDelete(0);
            hyUserMessage.setIsRead(0);
            hyUserMessage.setMethond(1);
            hyUserMessageService.insert(hyUserMessage);
        }
    }

}
