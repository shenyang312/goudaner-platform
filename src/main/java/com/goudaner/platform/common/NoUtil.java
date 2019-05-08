package com.goudaner.platform.common;

public class NoUtil {
    public static String getOrderNo(){
        return String.format("O%s%s", DateUtil.formatNow("MMddHHmmssSSSS"), RandomUtil.randomStr(5));
    }
}
