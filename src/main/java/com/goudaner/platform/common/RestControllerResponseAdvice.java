package com.goudaner.platform.common;

import com.alibaba.fastjson.JSON;
import com.goudaner.platform.base.SyCode;
import com.goudaner.platform.base.SyResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.Serializable;

@ControllerAdvice
public class RestControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        // 对body进行封装处理
        if (body instanceof String) {
            String msg = (String) body;
            SyResult resultJson = new SyResult(SyCode.OK,msg);
            // 因为在controller层中返回的是String类型，这边如果换成SyResult的话，会导致StringMessageConverter方法类型转换异常，所以这边将对象转成字符串
            return resultJson;
        } else if (body instanceof Object) {
            Object data = (Object) body;
            SyResult resultJson = new SyResult(data);
            return resultJson;
        }

        return body;
    }
}

