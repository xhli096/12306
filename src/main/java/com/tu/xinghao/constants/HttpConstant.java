package com.tu.xinghao.constants;

/**
 * @author: lixinghao
 * @date: 2019-10-17 00:18
 * @Description:
 */
public class HttpConstant {
    // 登录页
    public static final String LOGIN_INIT = "https://kyfw.12306.cn/otn/login/init";

    // 获取验证码
    public static final String VERIFICATION_CODE_URL = "https://kyfw.12306.cn/passport/captcha/captcha-image?login_site=E&module=login&rand=sjrand&";

    // 校验验证码
    public static final String VERIFICATION_CODE_CHECK_URL = "https://kyfw.12306.cn/passport/captcha/captcha-check";

    public static final String VERIFICATION_CODE_CHECK_CALLBACK = "jQuery19107521990954709705_";
}
