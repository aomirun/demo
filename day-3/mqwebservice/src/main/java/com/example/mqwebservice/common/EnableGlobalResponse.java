package com.example.mqwebservice.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import org.springframework.context.annotation.Import;

/**
 * 全局统一返回启用注解(作用于启动类上)
 *
 * @author Tom
 * @date 2020-12-18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(GlobalDefaultConfig.class)
public @interface EnableGlobalResponse {}