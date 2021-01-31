// package com.example.rsaserver.service.rsaserver.impl;

// import com.example.rsaserver.domain.RSAConfig;
// import com.example.rsaserver.service.rsaserver.CipherServant;
// import com.qq.tars.common.support.Holder;
// import com.qq.tars.spring.annotation.TarsServant;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// /**
//  * @author aomi.run
//  */
// @TarsServant("cipherObj")
// @Component
// public class CipherServantImpl implements CipherServant {

//     @Autowired
//     private RSAConfig rsaConfig;

//     private String privateKey;
//     private String publicKey;


//     public CipherServantImpl() {
//         this.privateKey = rsaConfig.getPrivateKey();
//         this.publicKey = rsaConfig.getPublicKey();
//     }

//     @Override
//     public boolean genKey(Holder<String> priKey, Holder<String> pubKey) {
//         return true;
//     }

//     @Override
//     public boolean getPublicKey(int typ, Holder<String> pubKey) {
//         return true;
//     }

//     @Override
//     public boolean encodeByPrivateKey(String data, Holder<String> enStr) {
//         return true;
//     }

//     @Override
//     public boolean encodeByPublicKey(String data, Holder<String> enStr) {
//         return true;
//     }

//     @Override
//     public boolean decodeByPrivateKey(String data, Holder<String> plain) {
//         return true;
//     }

//     @Override
//     public boolean decodeByPublicKey(String data, Holder<String> plain) {
//         return true;
//     }

//     @Override
//     public boolean sign(String data, int typ, Holder<String> signStr) {
//         return true;
//     }

//     @Override
//     public boolean verify(String plain, String signStr, int typ) {
//         return true;
//     }


// }