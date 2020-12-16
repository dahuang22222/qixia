package com.changjianghuyu.qixia.web.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 腾讯地图api
 * @author: LiYang
 * @Email: lyflyyvip@163.com
 * @create: 2020-05-11 10:20
 **/
@RestController
@RequestMapping("/map")
public class MapController {

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 微信地图搜多跨域
     * @param name
     * @return
     */
    @RequestMapping("/search")
    @CrossOrigin(origins = "*")
    public String restTemplateDemo(@RequestParam String name){
        try {
            //跨域访问表头
            HttpHeaders headers = new HttpHeaders();
            //接口地址
            String url = "https://apis.map.qq.com/ws/geocoder/v1?address=" + name + "&key=ZWZBZ-Q7LWF-OM3JC-JFSUO-HW7MJ-FKFLH";
            //restTemplate的POST请求，必须是使用MultiValueMap
            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            //利用multiValueMap插入需要传输的数据
//			multiValueMap.add("id","1");
//			multiValueMap.add("name","小明");
            //放入Http传输的数据
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap,headers);
            //访问接口并获取返回值
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
            //输出接口所返回过来的值
            return responseEntity.getBody();
        }catch(Exception e){
            e.printStackTrace();
        }finally {

        }
        return null;
    }

}