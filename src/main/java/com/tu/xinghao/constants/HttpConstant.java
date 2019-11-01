package com.tu.xinghao.constants;

/**
 * @author: lixinghao
 * @date: 2019-10-17 00:18
 * @Description:
 */
public class HttpConstant {
    public static final String DEVICE_INFO_URL = "https://kyfw.12306.cn/otn/HttpZF/logdevice?algID=WAa2rRuEOC&hashCode=f7CYuO-1NULoIcNkfelibUeR5CpjIpv-j2WhckLPvf8&FMQw=0&q4f3=zh-CN&VPIf=1&custID=133&VEek=unknown&dzuS=0&yD16=0&EOQP=8f58b1186770646318a429cb33977d8c&jp76=52d67b2a5aa5e031084733d5006cc664&hAqN=Win32&platform=WEB&ks0Q=d22ca0b81584fbea62237b14bd04c866&TeRS=1040x1920&tOHY=24xx1080x1920&Fvje=i1l1o1s1&q5aJ=-8&wNLf=99115dfb07133750ba677d055874de87&0aew={0}&E3gR=9564647ce82bb8fb9e3489ea64d9ee3d&timestamp={1}";

    // 登录页
    public static final String LOGIN_INIT = "https://kyfw.12306.cn/otn/login/init";
    public static final String LOGIN_HTML = "https://kyfw.12306.cn/otn/resources/login.html";

    // 获取验证码
    public static final String CAPTCHA = "https://kyfw.12306.cn/passport/captcha/captcha-image?login_site=E&module=login&rand=sjrand&{0}";

    // 校验验证码
    public static final String CAPTCHA_CHECK = "https://kyfw.12306.cn/passport/captcha/captcha-check";

    // AI校验验证码
    public static final String IMAGE_AI_URL = "http://shell.teachx.cn:12306/predict";


}
