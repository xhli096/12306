package com.tu.xinghao.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: lixinghao
 * @date: 2019-11-01 17:00
 * @Description:
 */
@Data
@Accessors(chain = true)
public class UrlConfig {
    private String url;
    private String method;
    private String host;
    private String referer;

    public UrlConfig(String url, String method, String referer) {
        this.url = url;
        this.method = method;
        this.referer = referer;
    }
}
