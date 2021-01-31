package com.example.rsaserver.service.rsaserver.impl;

import java.util.Map;

import com.example.rsaserver.domain.RSAConfig;
import com.example.rsaserver.service.rsaserver.CipherServant;
import com.example.rsaserver.service.rsaserver.KeyType;
import com.example.rsaserver.utils.RSAUtils;
import com.qq.tars.common.support.Holder;
import com.qq.tars.spring.annotation.TarsServant;
import com.qq.tars.support.log.LoggerFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

/**
 * @author aomi.run
 */
@TarsServant("cipherObj")
@Component
public class CipherServantImpl implements CipherServant {

    @Autowired
    private RSAConfig rsaConfig;

    private final static Logger RSA_LOGGER = LoggerFactory.getLogger("RSA");

    @Override
    public boolean genKey(Holder<String> priKey, Holder<String> pubKey) {
        try {
            final Map<String, Object> keyPair = RSAUtils.genKeyPair();
            String publickey = RSAUtils.getPublicKey(keyPair);
            String privatekey = RSAUtils.getPrivateKey(keyPair);
            priKey.setValue(privatekey);
            pubKey.setValue(publickey);
        } catch (Exception e) {
            return exHandler("genKey", e);
        }

        RSA_LOGGER.info("genkey() successfully call");
        return true;
    }

    @Override
    public boolean getPublicKey(int typ, Holder<String> pubKey) {
        String publicKey = rsaConfig.getPublicKey();
        // KeyType keyType = KeyType.PASSWORD;
        KeyType convert = KeyType.convert(typ);

        // TODO 这里目前没有往下扩展，后续可以扩展完善
        // 目前所有都返回统一的公钥
        switch (convert) {
            case PASSWORD:
                pubKey.setValue(publicKey);
                break;

            case PLAIN:
                pubKey.setValue(publicKey);
                break;

            case SIGN:
                pubKey.setValue(publicKey);
                break;

            case JWT:
                pubKey.setValue(publicKey);
                break;

            default:
                pubKey.setValue(null);
                break;
        }

        if (StringUtils.isBlank(pubKey.getValue())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean encodeByPrivateKey(String data, Holder<String> enStr) {
        try {
            String privateKey = rsaConfig.getPrivateKey();
            String enData = RSAUtils.encryptedDataOnJavaByPrivateKey(data, privateKey);
            enStr.setValue(enData);
        } catch (Exception e) {
            return exHandler("encodeByPrivateKey", e);
        }

        RSA_LOGGER.info("encodeByPrivateKey() successfully call");
        return true;
    }

    @Override
    public boolean encodeByPublicKey(String data, Holder<String> enStr) {
        try {
            String publicKey = rsaConfig.getPublicKey();
            String enData = RSAUtils.encryptedDataOnJavaByPublicKey(data, publicKey);
            enStr.setValue(enData);
        } catch (Exception e) {
            return exHandler("encodeByPublicKey", e);
        }

        RSA_LOGGER.info("encodeByPublicKey() successfully call");
        return true;
    }

    @Override
    public boolean decodeByPrivateKey(String data, Holder<String> plain) {
        try {
            String privateKey = rsaConfig.getPrivateKey();
            String deData = RSAUtils.decryptDataOnJavaByPrivateKey(data, privateKey);
            plain.setValue(deData);
        } catch (Exception e) {
            return exHandler("decodeByPrivateKey", e);
        }

        RSA_LOGGER.info("decodeByPrivateKey() successfully call");
        return true;
    }

    @Override
    public boolean decodeByPublicKey(String data, Holder<String> plain) {
        try {
            String publicKey = rsaConfig.getPublicKey();
            String deData = RSAUtils.decryptDataOnJavaByPublicKey(data, publicKey);
            plain.setValue(deData);
        } catch (Exception e) {
            return exHandler("decodeByPublicKey", e);
        }

        RSA_LOGGER.info("decodeByPublicKey() successfully call");
        return true;
    }

    @Override
    public boolean sign(String data, int typ, Holder<String> signStr) {
        try {
            String privateKey = rsaConfig.getPrivateKey();
            String enSign = RSAUtils.sign(data.getBytes(), privateKey);
            signStr.setValue(enSign);
        } catch (Exception e) {
            return exHandler("sign", e);
        }

        RSA_LOGGER.info("sign() successfully call");
        return true;
    }

    @Override
    public boolean verify(String plain, String signStr, int typ) {
        try {
            String publicKey = rsaConfig.getPublicKey();
            boolean ok = RSAUtils.verify(plain.getBytes(), publicKey, signStr);
            RSA_LOGGER.info("verify() successfully call");
            return ok;
        } catch (Exception e) {
            return exHandler("verify", e);
        }
    }

    private boolean exHandler(String funcname, Exception e) {
        e.printStackTrace();
        RSA_LOGGER.error("{}(): {}", funcname, e.toString());
        return false;

    }

}