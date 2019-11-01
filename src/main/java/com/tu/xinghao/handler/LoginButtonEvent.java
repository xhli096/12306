package com.tu.xinghao.handler;

import com.tu.xinghao.service.LoginService;
import com.tu.xinghao.ui.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: lixinghao
 * @date: 2019-10-17 12:52
 * @Description:
 */
@Slf4j
@Component
public class LoginButtonEvent<T> implements ActionListener {
    @Autowired
    private Login<T> login;
    @Resource
    private LoginService loginService;

    @Override
    public void actionPerformed(ActionEvent e) {
        loginService.check();
        //login.getFrame().setVisible(false);

    }
}
