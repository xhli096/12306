package com.tu.xinghao.service;

import com.google.common.base.Strings;
import com.tu.xinghao.constants.HttpConstant;
import com.tu.xinghao.constants.ImageConstant;
import com.tu.xinghao.handler.VerificationCodeEvent;
import com.tu.xinghao.ui.Login;
import com.tu.xinghao.util.CommonUtil;
import com.tu.xinghao.util.HttpUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
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
    private static Logger log = Logger.getLogger(LoginService.class);


    @Autowired
    private Login login;

    private StringBuilder newCode = new StringBuilder();

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
        InputStream verificationCodeInputStream = HttpUtil.doGet(HttpConstant.VERIFICATION_CODE_URL + Math.random());
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
        JComponent component = login.getFrame().getLayeredPane();
        Component[] components = component.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (i <= 0) {
                newCode = new StringBuilder();
            }
            if (components[i] instanceof JLabel) {
                if (i > 0) {
                    newCode.append(",");
                }
                JLabel label = (JLabel) components[i];
                newCode.append(label.getX() - 64 + (label.getIcon().getIconWidth() / 2)).append(",");
                newCode.append(label.getY() - 179 + (label.getIcon().getIconHeight() / 2));
            }
        }
        login.getMsgLabel().setText("当前验证码：" + newCode.toString());
        System.out.println(newCode.toString());
        String randCode = newCode.toString().replaceAll(",", "%2C");
        System.out.println("randCode:" + randCode);
        String temp = HttpConstant.VERIFICATION_CODE_CHECK_URL + "?callback=" + new StringBuilder(HttpConstant.VERIFICATION_CODE_CHECK_CALLBACK)
                .append(System.currentTimeMillis()).toString()
                + "&answer=" + randCode
                + "&rand=sjrand&login_site=E&_=" + System.currentTimeMillis();
        System.out.println("校验验证码url" + temp);


/*
        Map<String, String> params = Maps.newHashMap();
        params.put("callback", new StringBuilder("jQuery19107521990954709705_").append(String.valueOf(System.currentTimeMillis())).toString());
        params.put("randCode", newCode.toString());
        params.put("rand", "sjrand");
        params.put("login_site", "E");
        HttpEntity entity = HttpUtil.doPost(HttpConstant.VERIFICATION_CODE_CHECK_URL, params);
*/
        String body = HttpUtil.outHtml(HttpUtil.doGet(temp));
        System.out.println("body:" + body);
    }

    private String buildVerificationCodeString() {


        return null;
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
        JComponent component = login.getFrame().getLayeredPane();
        int index = 0;
        Component[] components = component.getComponents();
        for (; index < components.length; index++) {
            if (components[index] instanceof JLabel) {
                break;
            }
        }
        if (index >= components.length) {
            log.error("未选择验证码");
            login.getMsgLabel().setText("提示：请选择验证码");
            login.getMsgLabel().setForeground(Color.RED);
            return false;
        }

        return true;
    }
}
