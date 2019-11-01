package com.tu.xinghao.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.tu.xinghao.ai.ImageAi;
import com.tu.xinghao.ai.impl.Easy12306Ai;
import com.tu.xinghao.constants.CommonConstant;
import com.tu.xinghao.constants.EnumUrls;
import com.tu.xinghao.constants.HttpConstant;
import com.tu.xinghao.vo.UrlConfig;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * @author: lixinghao
 * @date: 2019-11-01 18:33
 * @Description:
 */
public class Captcha {
    private static Log log = Log.get();

    private Session session;
    private String loginCaptchaImageFileName;

    public Captcha(Session session) {
        this.session = session;
    }

    /**
     * 获得验证码图片
     *
     * @return
     */
    public InputStream getLoginCaptchaImg() {
        UrlConfig urlConfig = EnumUrls.CAPTCHA.getUrlConfig();
        String url = urlConfig.getUrl().replace("{0}", CommonUtil.randNumber());
        urlConfig.setUrl(url);
        System.out.println(url);
        EnumUrls.CAPTCHA.setUrlConfig(urlConfig);
        HttpResponse httpResponse = session.getHttpClient().send(EnumUrls.CAPTCHA);
        loginCaptchaImageFileName = getNewLoginCaptchaImagFileName();
        httpResponse.writeBody(new File(CommonConstant.CAPTCHA_IMG_PRE_PATH + loginCaptchaImageFileName));
        return httpResponse.bodyStream();
    }

    public boolean checkLoginCaptchaImg() {
        ImageAi imageAi = new Easy12306Ai(HttpConstant.IMAGE_AI_URL, CommonConstant.CAPTCHA_IMG_PRE_PATH + loginCaptchaImageFileName);
        String code = imageAi.printCode();
        String answerCode = TicketUtil.getCaptchapos(code);
        Map<String, Object> params = Maps.newHashMap();
        params.put("answer", answerCode);
        params.put("login_site", "E");
        params.put("rand", "sjrand");
        System.out.println("answerCode:" + answerCode);
        HttpResponse response = session.getHttpClient().send(EnumUrls.CAPTCHA_CHECK, params);
        String body = response.body();
        JSONObject object = JSONUtil.parseObj(body);
        System.out.println("JsonObject:" + object);
        String checkResult = object.getStr("result_code");

        System.out.println("checkResult:" + (!Strings.isNullOrEmpty(checkResult) && CommonConstant.CHECK_SUCCESS == Integer.parseInt(checkResult)));
        return !Strings.isNullOrEmpty(checkResult) && CommonConstant.CHECK_SUCCESS == Integer.parseInt(checkResult);
    }

    private String getNewLoginCaptchaImagFileName() {
        return "login" + RandomUtil.randomString(5) + ".png";
    }

    public String getLoginCaptchaImageFileName() {
        return loginCaptchaImageFileName;
    }
}
