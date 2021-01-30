package com.example.mqwebservice.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义默认过滤配置中的包和类
 *
 * @author Tom
 * @date 2020-12-18
 */
@ConfigurationProperties(GlobalDefaultProperties.PREFIX)
public class GlobalDefaultProperties {

    /**
     * 定义过滤拦截头部
     */
    public static final String PREFIX = "dispose";

    /**
     * 统一返回过滤包
     */
    private List<String> adviceFilterPackage = new ArrayList<>();

    /**
     * 统一返回过滤类
     */
    private List<String> adviceFilterClass = new ArrayList<>();

    public List<String> getAdviceFilterPackage() {
        return adviceFilterPackage;
    }

    public void setAdviceFilterPackage(List<String> adviceFilterPackage) {
        this.adviceFilterPackage = adviceFilterPackage;
    }

    public List<String> getAdviceFilterClass() {
        return adviceFilterClass;
    }

    public void setAdviceFilterClass(List<String> adviceFilterClass) {
        this.adviceFilterClass = adviceFilterClass;
    }

}