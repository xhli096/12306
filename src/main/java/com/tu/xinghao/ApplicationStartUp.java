package com.tu.xinghao;

import com.tu.xinghao.handler.LoginEvent;
import com.tu.xinghao.ui.Login;
import org.apache.log4j.Logger;

/**
 * @author: lixinghao
 * @date: 2019-10-16 20:46
 * @Description:
 */
public class ApplicationStartUp {
    private static Logger logger = Logger.getLogger(ApplicationStartUp.class);

    public static <T> void main(String[] args) {
        logger.info("正在加载资源文件...");
        Login login = (Login) ApplicationContextFactory.getBean(Login.class);
        logger.info("开始启动登录界面...");
        login.getFrame().setVisible(true);

        logger.info("正在加载登录界面事件...");
        LoginEvent<T> loginEvent = (LoginEvent<T>) ApplicationContextFactory.getBean(LoginEvent.class);
        loginEvent.addEvent();
    }
}
