package com.tu.xinghao.util;

import com.tu.xinghao.constants.ImageConstant;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

/**
 * @author: lixinghao
 * @date: 2019-10-16 20:27
 * @Description:
 */
public class ConfigUtils {
    private Map<String, String> map;
    private File file;
    private static ConfigUtils configUtils;

    /**
     * 获取一个新的ConfigUtil
     */
    public synchronized static ConfigUtils getInstance() {
        if (Objects.isNull(configUtils)) {
            configUtils = new ConfigUtils();
        }

        return configUtils;
    }

    public synchronized static URL initVerificationCode() {
        return ConfigUtils.class.getResource(ImageConstant.LOADING);
    }
}
