package com.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author guofa.liu
 * @description
 * @create 2020/05/24 17:29
 */
public class NeteaseImageValidateBean {

    private String phone;
    private String smsflag;
    private String deviceId;
    private String sign;
    private String timestamp;
    private String platform;
    @JSONField(name="NECaptchaValidate")
    private String nECaptchaValidate;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsflag() {
        return smsflag;
    }

    public void setSmsflag(String smsflag) {
        this.smsflag = smsflag;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getnECaptchaValidate() {
        return nECaptchaValidate;
    }

    public void setnECaptchaValidate(String nECaptchaValidate) {
        this.nECaptchaValidate = nECaptchaValidate;
    }
}
