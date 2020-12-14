package com.changjianghuyu.qixia.web.controller;

import com.changjianghuyu.qixia.web.common.msg.HanderCode;
import com.changjianghuyu.qixia.web.common.msg.MsgHander;
import com.changjianghuyu.qixia.web.entity.HyInformationFeedback;
import com.changjianghuyu.qixia.web.service.HyInformationFeedbackService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

/**
 * 信息反馈表(HyInformationFeedback)表控制层
 *
 * @author huangkewang
 * @since 2020-11-11 15:10:55
 */
@RestController
@RequestMapping("/hyInformationFeedback")
public class HyInformationFeedbackController {
    /**
     * 服务对象
     */
    @Resource
    private HyInformationFeedbackService hyInformationFeedbackService;


    /**
     * 8.1分页获取信息反馈列表
     * @param map
     * @return
     */
    @GetMapping("/getInformationFeedbackList")
    public MsgHander getInformationFeedbackList(@RequestParam Map<String,String> map) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        try {
            msg.setContext(hyInformationFeedbackService.getInformationFeedbackList(map));
        } catch (ParseException e) {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 8.2反馈上传
     * @param hyInformationFeedback
     * @return
     */
    @PostMapping("/insertHyInformationFeedback")
    public MsgHander insertHyInformationFeedback(@RequestBody HyInformationFeedback hyInformationFeedback) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        HyInformationFeedback insert = hyInformationFeedbackService.insert(hyInformationFeedback);
        msg.setContext(insert);
        if(null == insert){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }


    /**
     * 8.3 真实删除反馈列表
     * @param hyInformationFeedback
     * @return
     */
    @PostMapping("/deleteById")
    public MsgHander deleteHyInformationFeedbackById(@RequestBody HyInformationFeedback hyInformationFeedback) {
        Long id = hyInformationFeedback.getId();
        MsgHander msg = new MsgHander();

        boolean deleteResult = hyInformationFeedbackService.deleteById(id);
        msg.setContext(deleteResult);
        if(deleteResult){
            msg.setMessage("获取成功");
            msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        }else {
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }

    /**
     * 8.5通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MsgHander selectOne(Long id) {
        return new MsgHander(this.hyInformationFeedbackService.queryById(id));
    }

    /**
     * 8.4通过id修改反馈列表
     * @param hyInformationFeedback
     * @return
     */
    @PostMapping("/updateHyInformationFeedback")
    public MsgHander updateHyInformationFeedback(@RequestBody HyInformationFeedback hyInformationFeedback) {
        MsgHander msg = new MsgHander();
        msg.setMessage("获取成功");
        msg.setStatus(HanderCode.CONTROLLER_CODE_SUCCESS);
        HyInformationFeedback update = hyInformationFeedbackService.update(hyInformationFeedback);
        msg.setContext(update);
        if(null == update){
            msg.setMessage("系统异常");
            msg.setStatus(HanderCode.CONTROLLER_CODE_ERROR);
        }
        return msg;
    }


    /**
     * 8.6删除多个反馈信息
     * @param map
     * @return
     */
    @PostMapping("/deleteByIds")
    public MsgHander deleteByIds(@RequestBody Map<String,String> map) {
        String Ids = map.get("ids");
        return new MsgHander(this.hyInformationFeedbackService.deleteByIds(Ids));
    }

}