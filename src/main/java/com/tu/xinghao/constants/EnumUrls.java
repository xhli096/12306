package com.tu.xinghao.constants;

import com.tu.xinghao.vo.UrlConfig;

/**
 * @author: lixinghao
 * @date: 2019-11-01 17:10
 * @Description:
 */
public enum EnumUrls {
    LOGIN_INIT(new UrlConfig(HttpConstant.LOGIN_INIT, CommonConstant.HTTP_GET, HttpConstant.LOGIN_INIT));

    private UrlConfig urlConfig;

    EnumUrls(UrlConfig urlConfig) {
        this.urlConfig = urlConfig;
    }

    public UrlConfig getUrlConfig() {
        return urlConfig;
    }
}
