package com.example.rsaserver.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author aomi.run
 */
@Component
public class RSAConfig {
    @Value("${test.key.public}")
    private String publicKey;

    @Value("${test.key.private}")
    private  String privateKey;


    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey(){
        return privateKey;
    }

}