package com.example.tarsmqserver.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NsqConfig {
    
    @Value("${nsq.nsqlookupd-host}")
    private String nsqlookupd;

    @Value("${nsq.nsqlookupd-slave-host}")
    private String nsqlookupdSlave;

    @Value("${nsq.node}")
    private String node;

    private List<String> host;

    public List<String> getHost() {
        return host;
    }

    public String getNsqlookupd() {
        return nsqlookupd;
    }

    public String getNsqlookupdSlave() {
        return nsqlookupdSlave;
    }

    public String getNode() {
        return node;
    }
}