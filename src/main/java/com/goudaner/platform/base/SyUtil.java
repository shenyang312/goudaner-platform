package com.goudaner.platform.base;

import java.io.*;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create by emmet
 * create at 26/04/2018
 * 核心工具类
 */
public final class SyUtil {

    public static final String UTF8 = "UTF-8";
    public static final String GBK = "GBK";

    /** 判断对象是否为空 **/
    public static boolean isEmpty(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) return true;
            if ("".equals(obj.toString().trim())) return true;
            if (obj instanceof Collection) {
                Collection collection = (Collection) obj;
                return collection.isEmpty();
            }
            if (obj instanceof Map) {
                Map map = (Map) obj;
                return map.isEmpty();
            }
        }
        return false;
    }

    /** 判断对象是否为空 **/
    public static boolean isNotEmpty(Object... objs) {
        return !isEmpty(objs);
    }

    /** 对象为空 则替换另外一个对象 */
    public static <T> T isEmptyReplace(T checkStr, T replace) {
        return isEmpty(checkStr) ? replace : checkStr;
    }

    public static boolean isTure(String flag) {
        return isEmpty(flag) ? false : flag.equalsIgnoreCase("true") ? true : false;
    }

    public static boolean isFalse(String flag) {
        return isEmpty(flag) ? false : flag.equalsIgnoreCase("false") ? true : false;

    }

    /**
     * 判断字符串中是否包含中文
     * @param str
     * 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 深拷贝对象
     * @param obj
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj){
        T clonedObj = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            clonedObj = (T) ois.readObject();
            ois.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return clonedObj;
    }

    public static String joinSemicolon(String ... fileds) {
        return String.join(";", fileds);
    }
}
