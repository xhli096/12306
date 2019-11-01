package com.tu.xinghao.ai.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.tu.xinghao.ai.ImageAi;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author: lixinghao
 * @date: 2019-11-01 16:34
 * @Description:
 */
@Slf4j
public class Easy12306Ai implements ImageAi {
    private String aiUrl;
    private String imagePath;

    public Easy12306Ai(String aiUrl, String imagePath) {
        this.aiUrl = aiUrl;
        this.imagePath = imagePath;
    }

    @Override
    public String printCode() {
        try {
            HttpRequest httpRequest = HttpUtil.createPost(aiUrl);
            httpRequest.form("file", new File(this.imagePath));
            String body = httpRequest.execute().body();
            body = body.replaceAll(" ", "");

            String tagText = body.substring(body.indexOf("text:") + 5, body.indexOf(",images"));
            String imagesText = body.substring(body.indexOf(",images") + 7);
            String[] imageTextArr = imagesText.split(",");

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < imageTextArr.length; i++) {
                if (tagText.equals(imageTextArr[i])) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(i + 1);
                }
            }

            return stringBuilder.toString();
        } catch (StringIndexOutOfBoundsException e) {
            log.error("图片验证码识别AI异常，无法自动识别验证码，请重试", e);
        }

        return null;
    }
}
