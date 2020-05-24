package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.utils.AESUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

/**
 * @author guofa.liu
 * @description 北京交警进京证次数
 * @create 2020/05/24 13:50
 */

@RestController
@RequestMapping(("/jjz"))
public class JjzController {


    /**
     * 车牌号
     */
    private static String licenseNo = "车牌号"; //TODO
    /**
     * 号牌类型
     * ("大型汽车", "01"))
     * ("小型汽车", "02"))
     * ("使馆汽车", "03"))
     * ("领馆汽车", "04"))
     * ("境外汽车", "05"))
     * ("外籍汽车", "06"))
     * ("挂车", "15"))
     * ("香港入出境车", "26"))
     * ("澳门入出境车", "27"))
     * ("大型新能源汽车", "51"))
     * ("小型新能源汽车", "52"))
     */
    private static String carType = "号牌类型"; //TODO
    /**
     * 发动机号后六位
     */
    private static String engineNo = "发动机号后六位"; //TODO
    /**
     * 车辆所有人
     */
    private static String ownerName = "车辆所有人";  //TODO
    /**
     * 登录后获取的token
     */
    private static String token = "token";  //TODO
    private static String userId = token;
    /**
     * 登录手机号
     */
    private static String p = "登录手机号";  //TODO
    /**
     * 版本号
     */
    private static String v = "2.7.1";
    /**
     * 签名key
     */
    private static String signKey = "_zcbl2019";


    @Autowired
    private RestTemplate restTemplate;

    /**
     * 查询进京证记录
     * @return
     */
    @GetMapping("/record")
    public JSONObject record() throws NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnknownHostException {
        String url = "https://bjjj.zhongchebaolian.com/bjjjfront/record";

        MultiValueMap<String, String> requestMap= new LinkedMultiValueMap();
        requestMap.add("data", buildJjzRecordParam());
        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        requestHeader.add("User-Agent", "okhttp-okgo/jeasonlzy");
        HttpEntity requestEntity = new HttpEntity(requestMap, requestHeader);
        JSONObject response = restTemplate.postForObject(url, requestEntity, JSONObject.class);
        if(response.getString("rspCd").equals("200")){
            response.put("data", JSONObject.parseObject(AESUtils.decryptAES(response.getString("data"), AESUtils.aesKey)));
        }
        return response;
    }


    /**
     * 构建进京证记录查询请求参数
     * @return
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    private String buildJjzRecordParam() throws NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        String n = UUID.randomUUID().toString();
        long t = (new Date()).getTime();

        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("licenseNo", licenseNo);
        jsonObject.put("carType", carType);
        jsonObject.put("engineNo", engineNo);
        jsonObject.put("ownerName", ownerName);

        jsonObject.put("token", token);
        jsonObject.put("userId", userId);
        jsonObject.put("p", p);

        jsonObject.put("v", v);
        jsonObject.put("n", n);
        jsonObject.put("t", t);

        jsonObject.put("s", DigestUtils.md5Hex(token+t+n+signKey).toLowerCase());
        return AESUtils.encryptAES(jsonObject.toString(), AESUtils.aesKey);
    }
}
