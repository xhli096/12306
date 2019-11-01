package com.tu.xinghao.handler;

import com.tu.xinghao.service.LoginService;
import com.tu.xinghao.ui.Login;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: lixinghao
 * @date: 2019-10-17 15:14
 * @Description:
 */
@Component
public class RefreshButtonEvent<T> implements ActionListener {
    private static Logger log = Logger.getLogger(RefreshButtonEvent.class);

    @Autowired
    private Login<T> login;
    @Autowired
    private LoginService loginService;

    @Override
    public void actionPerformed(ActionEvent e) {
        log.info("刷新验证码");

        // 移除标志
        JComponent component = login.getFrame().getLayeredPane();
        java.awt.Component[] components = component.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JLabel) {
                JLabel label = (JLabel) components[i];
                component.remove(label);
            }
        }

        login.getVerificationCode().setIcon(new ImageIcon(loginService.getVerificationCode()));
        login.getFrame().repaint();
    }
}
