package com.tu.xinghao.util;

import cn.hutool.core.util.RandomUtil;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;

/**
 * @author: lixinghao
 * @date: 2019-10-17 10:29
 * @Description:
 */
public class CommonUtil {
    private static Logger logger = Logger.getLogger(CommonUtil.class);

    /**
     * 将inputstream转换为byte数组
     *
     * @param inputStream
     * @return
     */
    public static byte[] inputstreamToByte(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        byte[] result = null;
        int length = 0;
        try {
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            result = outputStream.toByteArray();
        } catch (IOException e) {
            logger.warn("读取输入流出错", e);
        }

        return result;
    }

    public static String randNumber() {
        return String.valueOf(RandomUtil.randomDouble(0, 0.9, 17, RoundingMode.HALF_UP));
    }
}
