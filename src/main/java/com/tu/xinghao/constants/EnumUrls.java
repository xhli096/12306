package com.tu.xinghao.constants;

import com.tu.xinghao.vo.UrlConfig;

/**
 * @author: lixinghao
 * @date: 2019-11-01 17:10
 * @Description:
 */
public enum EnumUrls {
    LOGIN_DEVICE(new UrlConfig(HttpConstant.DEVICE_INFO_URL, CommonConstant.HTTP_GET, HttpConstant.LOGIN_INIT)),
    // 登录页
    LOGIN_INIT(new UrlConfig(HttpConstant.LOGIN_INIT, CommonConstant.HTTP_GET, HttpConstant.LOGIN_INIT)),
    // 获取登录验证码
    CAPTCHA(new UrlConfig(HttpConstant.CAPTCHA, CommonConstant.HTTP_GET, HttpConstant.LOGIN_HTML)),
    // 检验登录验证码
    CAPTCHA_CHECK(new UrlConfig(HttpConstant.CAPTCHA_CHECK, CommonConstant.HTTP_POST, HttpConstant.LOGIN_HTML));

    private UrlConfig urlConfig;

    EnumUrls(UrlConfig urlConfig) {
        this.urlConfig = urlConfig;
    }

    public void setUrlConfig(UrlConfig urlConfig) {
        this.urlConfig = urlConfig;
    }

    public UrlConfig getUrlConfig() {
        return urlConfig;
    }
}
