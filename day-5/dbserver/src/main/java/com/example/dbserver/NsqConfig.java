package com.example.dbserver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * nsq 配置类
 * @author aomi.run
 */
@ConfigurationProperties(value = "nsq.host")
@Component
public class NsqConfig {
    private String nsqlookupd;
    private String node;

    public String getNode() {
        return node;
    }

    public String getNsqlookupdHost() {
        return nsqlookupd;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public void setNsqlookupd(String nsqlookupd) {
        this.nsqlookupd = nsqlookupd;
    }

}
