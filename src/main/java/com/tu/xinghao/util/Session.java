package com.tu.xinghao.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Strings;
import com.tu.xinghao.constants.CommonConstant;
import com.tu.xinghao.constants.EnumUrls;
import com.tu.xinghao.vo.UrlConfig;

import java.net.HttpCookie;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: lixinghao
 * @date: 2019-11-01 16:58
 * @Description: 会话实体类
 */
public class Session {
    private HttpClient httpClient;
    private String token;
    private String cookie;

    public Session() {
        httpClient = new HttpClient();
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        if (Strings.isNullOrEmpty(cookie)) {
            this.cookie = "";
        } else {
            this.cookie += ";";
        }
        this.cookie += cookie;
    }

    public void setCookie(List<HttpCookie> cookies) {
        cookies.forEach(item -> {
            if (Strings.isNullOrEmpty(cookie)) {
                this.cookie = "";
            } else {
                this.cookie += ";";
            }
            this.cookie += item.toString();
        });
    }

    public class HttpClient {
        private HttpRequest httpRequest;

        public HttpClient() {
        }

        public HttpResponse send(EnumUrls enumUrls) {
            return send(enumUrls, null, false);
        }

        public HttpResponse send(EnumUrls enumUrls, Map<String, Object> params) {
            return send(enumUrls, params, false);
        }

        /**
         * 异步传递
         *
         * @param enumUrls
         * @return
         */
        public HttpResponse sendAsync(EnumUrls enumUrls) {
            return send(enumUrls, null, true);
        }

        public HttpResponse sendAsync(EnumUrls enumUrls, Map<String, Object> params) {
            return send(enumUrls, params, true);
        }

        public HttpResponse send(EnumUrls enumUrls, Map<String, Object> params, boolean async) {
            UrlConfig urlConfig = enumUrls.getUrlConfig();

            if (Objects.isNull(httpRequest)) {
                if (CommonConstant.HTTP_POST.equalsIgnoreCase(enumUrls.getUrlConfig().getMethod())) {
                    httpRequest = HttpUtil.createPost(urlConfig.getUrl());
                } else {
                    httpRequest = HttpUtil.createGet(urlConfig.getUrl());
                }
                httpRequest.header("Host", CommonConstant.HOST);
                httpRequest.header("Connection", "keep-alive");
                httpRequest.header("Accept", CommonConstant.ACCEPT);
                httpRequest.header("Content-Type", "application/json");
            } else {
                httpRequest.setUrl(urlConfig.getUrl());
                httpRequest.setMethod(getMethod(urlConfig.getMethod()));
            }

            if (!Strings.isNullOrEmpty(urlConfig.getReferer())) {
                httpRequest.header("Referer", urlConfig.getReferer());
            }
            if (!Strings.isNullOrEmpty(cookie)) {
                httpRequest.header("Cookie", cookie);
            }
            if (Objects.nonNull(params)) {
                System.out.println(JSONUtil.toJsonStr(params));
                httpRequest.body(JSONUtil.toJsonStr(params));
            }

            System.out.println("=======================");
            System.out.println(httpRequest);
            System.out.println("=======================");

            if (async) {
                return httpRequest.executeAsync();
            } else {
                return httpRequest.execute();
            }
        }

        private Method getMethod(String methodName) {
            return !Strings.isNullOrEmpty(methodName) && methodName.equalsIgnoreCase("get") ? Method.GET : Method.POST;
        }
    }
}
