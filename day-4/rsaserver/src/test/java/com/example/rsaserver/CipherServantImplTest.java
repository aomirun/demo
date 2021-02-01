package com.example.rsaserver;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.rsaserver.service.rsaserver.KeyType;
import com.example.rsaserver.service.rsaserver.impl.CipherServantImpl;
import com.qq.tars.common.support.Holder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
@Component
public class CipherServantImplTest {

    // 因CipherServantImpl中有 @Autowired 所以测试的时候不能使用new，
    // 必须使用 @Autowired来装载类才能读取到配置，要不然会出现空指针对错误
    @Autowired
    private CipherServantImpl imp;

    @Test
    void genKey() {
        Holder<String> priKey = new Holder<String>();
        Holder<String> pubKey = new Holder<String>();
        boolean ok = imp.genKey(priKey, pubKey);
        assertTrue(ok);
    }

    @Test
    public void getPublicKey() {
        KeyType convert = KeyType.convert(0);
        System.out.println(convert.toString());
        Holder<String> pubKey = new Holder<String>();
        boolean ok = imp.getPublicKey(0, pubKey);
        assertTrue(ok);
    }

    @Test
    public void encodeByPrivateKeyWithDecodeByPublicKey() {
        String data = "as65df4656a4sd6fa";
        Holder<String> enStr = new Holder<String>();
        boolean ok = imp.encodeByPrivateKey(data, enStr);
        assertTrue(ok);

        Holder<String> plain = new Holder<String>();
        imp.decodeByPublicKey(enStr.getValue(), plain);
        assertEquals(plain.value, data);

    }

    @Test
    public void encodeByPublicKeyWithDecodeByPrivateKey() {
        String data = "as65df4656a4sd6fa";
        Holder<String> enStr = new Holder<String>();
        boolean ok = imp.encodeByPublicKey(data, enStr);
        assertTrue(ok);

        Holder<String> plain = new Holder<String>();
        imp.decodeByPrivateKey(enStr.getValue(), plain);
        assertEquals(plain.value, data);
    }

    @Test
    public void signWithVerify() {
        String data = "as65df4656a4sd6fa";
        Holder<String> signStr = new Holder<String>();
        boolean ok = imp.sign(data, 0, signStr);
        assertTrue(ok);

        ok = imp.verify(data, signStr.getValue(), 0);
        assertTrue(ok);

    }
}