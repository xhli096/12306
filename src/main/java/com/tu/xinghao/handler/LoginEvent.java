package com.tu.xinghao.handler;

import com.tu.xinghao.service.LoginService;
import com.tu.xinghao.ui.Login;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * @author: lixinghao
 * @date: 2019-10-16 20:54
 * @Description:
 */
@Component
public class LoginEvent<T> {
    private static Logger log = Logger.getLogger(LoginEvent.class);

    @Autowired
    private Login<T> login;
    @Autowired
    private VerificationCodeEvent<T> verificationCodeEvent;
    @Autowired
    private FrameEvent<T> frameEvent;
    @Autowired
    private RefreshButtonEvent<T> refreshButtonEvent;
    @Autowired
    private LoginButtonEvent<T> loginButtonEvent;
    @Autowired
    private LoginService loginService;

    public void addEvent() {
        log.info("初始化cookie...");
        loginService.initTicket();
        loginService.init();

        log.info("初始化验证码...");
        login.getVerificationCode().setIcon(new ImageIcon(loginService.getVerificationCode()));

        log.info("添加登录面板事件...");
        login.getFrame().addMouseListener(frameEvent);

        log.info("添加验证码事件...");
        login.getVerificationCode().addMouseListener(verificationCodeEvent);

        log.info("添加刷新按钮事件...");
        login.getRefreshButton().addActionListener(refreshButtonEvent);

        log.info("添加登录按钮事件...");
        login.getLoginButton().addActionListener(loginButtonEvent);

    }
}
