package com.controller;

import com.bean.MobileLoginBean;
import com.bean.NeteaseImageValidateBean;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.UUID;

/**
 * APP登录
 * @author guofa.liu
 * @description
 * @create 2020/05/24 17:23
 */

@RestController
@RequestMapping("/mobile")
public class MobileController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 校验网易云验证码
     * 201:请求参数校验错误
     * 200:短信发送成功
     * 4001:用户直接登录
     * @return
     */
    @PostMapping("/NeteaseImageValidate")
    public JSONObject neteaseImageValidate(@RequestBody NeteaseImageValidateBean bean){
        bean.setDeviceId(UUID.randomUUID().toString());
        bean.setSmsflag("01");
        bean.setSign("");
        bean.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        bean.setPlatform("02");

        String url = "https://bjjj.zhongchebaolian.com/industryguild_mobile_standard_self2.1.2/mobile/standard/neteaseImageValidate/v2";

        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.setContentType(MediaType.APPLICATION_JSON_UTF8);
        requestHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));

        HttpEntity<String> formEntity = new HttpEntity(JSONObject.toJSONString(bean), requestHeader);
        JSONObject response = restTemplate.postForEntity(url, formEntity, JSONObject.class).getBody();
        return response;
    }


    /**
     * 登录
     * 200:登录成功
     * @return
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody MobileLoginBean bean){
        bean.setDevicetype("1");
        bean.setVertype("1");
        bean.setDeviceid(UUID.randomUUID().toString());
        bean.setSign("");
        bean.setSource("0");
        bean.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        bean.setPlatform("02");

        String url = "https://bjjj.zhongchebaolian.com/industryguild_mobile_standard_self2.1.2/mobile/standard/login";

        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.setContentType(MediaType.APPLICATION_JSON_UTF8);
        requestHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));

        HttpEntity<String> formEntity = new HttpEntity(JSONObject.toJSONString(bean), requestHeader);
        JSONObject response = restTemplate.postForEntity(url, formEntity, JSONObject.class).getBody();
        return response;
    }
}
