package com.example.mqwebservice.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 全局统一返回忽略标识注解(作用于: 类或方法上,标识后的类或方法则不进行全局返回的封装)
 *
 * @author Tom
 * @date 2020-12-18
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreGlobalResponse {}