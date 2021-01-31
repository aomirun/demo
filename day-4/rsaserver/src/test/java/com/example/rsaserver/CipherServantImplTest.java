package com.example.rsaserver;

import static org.junit.Assert.assertTrue;

import com.example.rsaserver.service.rsaserver.KeyType;
import com.example.rsaserver.service.rsaserver.impl.CipherServantImpl;
import com.qq.tars.common.support.Holder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
@Component
public class CipherServantImplTest {

    private CipherServantImpl imp = new CipherServantImpl();

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
    public void encodeByPrivateKey() {
        String data = "as65df4656a4sd6fa";
        Holder<String> enStr = new Holder<String>();
        boolean ok = imp.encodeByPrivateKey(data, enStr);
        assertTrue(ok);
       
    }
}