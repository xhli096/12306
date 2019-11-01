package com.tu.xinghao.util;

import com.google.common.base.Strings;

import java.util.Arrays;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2019-11-01 18:27
 * @Description:
 */
public class TicketUtil {
    public static String getCaptchapos(String code) {
        if (Strings.isNullOrEmpty(code)) {
            return "";
        }
        final List<String> DICT_CODE = Arrays.asList("36,46", "116,46", "188,46", "267,43", "40,118", "113,119", "190,122", "264,115");
        StringBuilder sb = new StringBuilder();
        for (String i : code.split(",")) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(DICT_CODE.get(Integer.parseInt(i) - 1));
        }
        return sb.toString();
    }
}
