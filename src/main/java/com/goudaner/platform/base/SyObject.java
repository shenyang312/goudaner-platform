package com.goudaner.platform.base;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class SyObject implements Serializable {
    /**
     * 通过JSON重写对象的toString方法
     * @return
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
