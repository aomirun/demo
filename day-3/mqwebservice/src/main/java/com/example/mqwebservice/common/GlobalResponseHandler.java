package com.example.mqwebservice.common;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter; 
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局统一返回处理类(重要)
 * {@link IgnoreGlobalResponse} 处理解析 {@link ResponseBodyAdvice} 统一返回包装器
 *
 * @author Tom
 * @date 2020-12-18
 */
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private GlobalDefaultProperties globalDefaultProperties;

    /**
     * 通过构造器将默认过滤配置注入
     * @param globalDefaultProperties
     */
    public GlobalResponseHandler(GlobalDefaultProperties globalDefaultProperties) {
        this.globalDefaultProperties = globalDefaultProperties;
    }

    /**
     * 重写supports方法,进行自定义规则拦截
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter,Class<? extends HttpMessageConverter<?>> aClass) {
        return filter(methodParameter);
    }

    /**
     * 全局统一返回处理类 - 核心方法
     * 返回值为Object类型并且返回为空  AbstractMessageConverterMethodProcessor#writeWithMessageConverters 方法
     * 无法触发调用本类的 beforeBodyWrite 处理,所以在Controller层尽量避免直接使用 "Object" 类型返回。
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Nullable
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,Class<? extends HttpMessageConverter<?>> aClass,
                                   ServerHttpRequest serverHttpRequest,ServerHttpResponse serverHttpResponse) {
        /**o is null -> return response*/
        if (o == null) {
            /**当 o 返回类型为 string 并且为null会出现 java.lang.ClassCastException: Result cannot be cast to java.lang.String,则封装ResultMsg对象并转换为String返回*/
            if (methodParameter.getParameterType().getName().equals("java.lang.String")) {
                return JSON.toJSON(new ResultMsg(true,200)).toString();
            }
            return new ResultMsg(true,200);
        }
        /**当 o 返回类型为ResultMsg(统一封装返回对象),则直接返回*/
        if (o instanceof ResultMsg) {
            return o;
        }
        /**当 o 为string 则特殊处理 java.lang.ClassCastException: Result cannot be cast to java.lang.String,封装ResultMsg对象并转换为String返回*/
        if (o instanceof String) {
            return JSON.toJSON(new ResultMsg(true,200,o)).toString();
        }
        return new ResultMsg(true,200,o);
    }

    /**
     * 自定义规则拦截器
     * 过滤: 1.检查过滤包路径 2.检查<类>过滤列表 3.检查忽略注解是否存在于类上 4.检查注解是否存在于方法上
     * @param methodParameter
     * @return
     */
    private Boolean filter(MethodParameter methodParameter) {
        Class<?> declaringClass = methodParameter.getDeclaringClass();
        /**检查过滤包路径*/
        long count = globalDefaultProperties.getAdviceFilterPackage().stream().filter(l -> declaringClass.getName().contains(l)).count();
        if (count > 0) {
            return false;
        }
        /**检查<类>过滤列表*/
        if (globalDefaultProperties.getAdviceFilterClass().contains(declaringClass.getName())) {
            return false;
        }
        /**检查忽略注解是否存在于类上*/
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreGlobalResponse.class)) {
            return false;
        }
        /**检查注解是否存在于方法上*/
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreGlobalResponse.class)) {
            return false;
        }
        return true;
    }
}