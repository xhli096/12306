package com.tu.xinghao;

import static org.junit.Assert.assertTrue;

import cn.hutool.http.HttpResponse;
import com.tu.xinghao.constants.EnumUrls;
import com.tu.xinghao.service.LoginService;
import com.tu.xinghao.ui.Login;
import com.tu.xinghao.util.Captcha;
import com.tu.xinghao.util.Session;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test() {
        Session session = new Session();
        HttpResponse response = session.getHttpClient().send(EnumUrls.LOGIN_INIT);
        session.setCookie(response.getCookies());
        System.out.println(response.getCookies());
        Captcha captcha = new Captcha(session);

        captcha.getLoginCaptchaImg();
        captcha.checkLoginCaptchaImg();
    }
}
