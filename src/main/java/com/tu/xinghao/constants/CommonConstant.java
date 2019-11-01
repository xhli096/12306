package com.tu.xinghao.constants;

import cn.hutool.core.util.ClassUtil;

/**
 * @author: lixinghao
 * @date: 2019-10-21 18:44
 * @Description:
 */
public class CommonConstant {
    public static final String HTTP_GET = "GET";

    public static final String HTTP_POST = "POST";

    public static final String QUESTIONMARK = "?";

    public static final String COOKIE = "Set-Cookie";

    public static final String HTTPS = "https";

    public static final String ENCODE = "utf-8";

    public static final String HOST = "kyfw.12306.cn";

    public static final String ACCEPT = "text/javascript, application/javascript, application/ecmascript,application/json, application/x-ecmascript,image/webp,image/apng,image/*,*/*; q=0.01";

    /**
     * 验证码图片生成保存路径
     */
    public static final String CAPTCHA_IMG_PRE_PATH = ClassUtil.getClassPath() + "captcha/";

    /**
     * 登录验证码校验成功
     */
    public static final Integer CHECK_SUCCESS = 4;

    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36";
}
