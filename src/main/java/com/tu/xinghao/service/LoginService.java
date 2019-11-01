package com.tu.xinghao.service;

import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import com.google.common.base.Strings;
import com.tu.xinghao.constants.CommonConstant;
import com.tu.xinghao.constants.EnumUrls;
import com.tu.xinghao.constants.HttpConstant;
import com.tu.xinghao.constants.ImageConstant;
import com.tu.xinghao.handler.VerificationCodeEvent;
import com.tu.xinghao.ui.Login;
import com.tu.xinghao.util.Captcha;
import com.tu.xinghao.util.CommonUtil;
import com.tu.xinghao.util.HttpUtil;
import com.tu.xinghao.util.Session;
import com.tu.xinghao.vo.UrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author: lixinghao
 * @date: 2019-10-17 00:12
 * @Description:
 */
@Service
public class LoginService {
    private static Log log = Log.get();

    private Session session;
    private Captcha captcha;

    @Autowired
    private Login login;

    private StringBuilder newCode = new StringBuilder();

    @PostConstruct
    public void init() {
        if (Objects.isNull(session)) {
            session = new Session();
            HttpResponse response = session.getHttpClient().send(EnumUrls.LOGIN_INIT);
            session.setCookie(response.getCookies());
        }
        if (Objects.isNull(captcha)) {
            captcha = new Captcha(session);
        }
        initLogDevice();
    }

    public LoginService(Login login) {
        this.login = login;
    }

    /**
     * 登录界面，用于获取cookie
     */
    public void initTicket() {
        HttpUtil.doGet(HttpConstant.LOGIN_INIT);
    }

    /**
     * 获取登录界面验证码
     *
     * @return
     */
    public byte[] getVerificationCode() {
        InputStream verificationCodeInputStream = captcha.getLoginCaptchaImg();
        if (Objects.isNull(verificationCodeInputStream)) {
            try {
                log.error("登录界面-获取12306验证码输入流失败");
                verificationCodeInputStream = VerificationCodeEvent.class.getResource(ImageConstant.LOAD_ERROR).openStream();
            } catch (IOException e) {
                log.error("获取loadError.png失败", e);
                return new byte[]{};
            }
        }
        byte[] res = CommonUtil.inputstreamToByte(verificationCodeInputStream);

        return res;
    }

    public void check() {
        if (!checkNecessary()) {
            return;
        }
        captcha.checkLoginCaptchaImg();
    }

    /**
     * 初始化设备信息，必须要有，否则无法携带body信息，不知道为什么
     */
    private void initLogDevice() {
        Session newSession = new Session();
        UrlConfig urlConfig = EnumUrls.LOGIN_DEVICE.getUrlConfig();
        urlConfig.setUrl(urlConfig.getUrl()
                .replace("{0}", CommonConstant.USER_AGENT)
                .replace("{1}", String.valueOf(System.currentTimeMillis())));
        EnumUrls.LOGIN_DEVICE.setUrlConfig(urlConfig);

        HttpResponse response = newSession.getHttpClient().send(EnumUrls.LOGIN_DEVICE);
        String body = response.body();
        String startIndexStr = "{";
        String endIndexStr = "}";

        body = body.substring(body.indexOf(startIndexStr), body.indexOf(endIndexStr) + 1);
        JSONObject jsonObject = JSONUtil.parseObj(body);
        String expiration = jsonObject.getStr("exp");
        String deviceId = jsonObject.getStr("dfp");
        this.session.setCookie("RAIL_EXPIRATION=" + expiration);
        this.session.setCookie("RAIL_DEVICEID=" + deviceId);
    }

    /**
     * 校验必要的参数是否齐全
     *
     * @return
     */
    private boolean checkNecessary() {
        if (Strings.isNullOrEmpty(login.getUserNameField().getText())) {
            log.error("未填写用户名信息");
            login.getMsgLabel().setText("提示：请填写用户名");
            login.getMsgLabel().setForeground(Color.RED);
            return false;
        }
        if (login.getPasswordField().getPassword().length <= 0) {
            log.error("未填写密码");
            login.getMsgLabel().setText("提示：请填写密码");
            login.getMsgLabel().setForeground(Color.RED);
            return false;
        }

        return true;
    }


}