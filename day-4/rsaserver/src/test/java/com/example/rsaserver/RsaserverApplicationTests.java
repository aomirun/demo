package com.example.rsaserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import com.example.rsaserver.utils.RSAUtils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RsaserverApplicationTests {

	final private String plain = "待加密字符串";

	@Test
	void genKeyPair() {
		try {
			final Map<String, Object> keyPair = RSAUtils.genKeyPair();
			assertNotNull(keyPair);

			String publicKey = RSAUtils.getPublicKey(keyPair);
			String privateKey = RSAUtils.getPrivateKey(keyPair);
			assertNotNull(publicKey);
			assertNotNull(privateKey);

			System.out.printf("RSAPublicKey base64 string is %s\n", publicKey);
			System.out.printf("RSAPrivateKey base64 string is %s\n", privateKey);

			System.out.printf("RSAPublicKey byte is %s", keyPair.get("RSAPublicKey"));
			System.out.printf("RSAPublicKey byte is %s", keyPair.get("RSAPrivateKey"));

		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void encodeAndDecode() {
		try {
			final Map<String, Object> keyPair = RSAUtils.genKeyPair();
			String publicKey = RSAUtils.getPublicKey(keyPair);
			String privateKey = RSAUtils.getPrivateKey(keyPair);

			// 公钥加密，私钥解密
			String enData = RSAUtils.encryptedDataOnJavaByPublicKey(plain, publicKey);
			String deData = RSAUtils.decryptDataOnJavaByPrivateKey(enData, privateKey);
			assertEquals(deData, plain);

			// 私钥加密，公钥解密
			enData = RSAUtils.encryptedDataOnJavaByPrivateKey(plain, privateKey);
			deData = RSAUtils.decryptDataOnJavaByPublicKey(enData, publicKey);
			assertEquals(deData, plain);
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void signAndVerify() {
		try {
			final Map<String, Object> keyPair = RSAUtils.genKeyPair();
			String publicKey = RSAUtils.getPublicKey(keyPair);
			String privateKey = RSAUtils.getPrivateKey(keyPair);

			// 正确的签名测试，签名内容与验证签名的内容相符
			String enSign = RSAUtils.sign(plain.getBytes(), privateKey);
			boolean ok = RSAUtils.verify(plain.getBytes(), publicKey, enSign);
			assertTrue(ok);

			// 错误的签名测试，签名内容与验证签名的内容不符合
			String errPlain = plain + "asdfasf";
			enSign = RSAUtils.sign(errPlain.getBytes(), privateKey);
			ok = RSAUtils.verify(plain.getBytes(), publicKey, enSign);
			assertFalse(ok);

			// 使用其它签名算法
			String algorithm = "SHA256withRSA";
			enSign = RSAUtils.sign(plain.getBytes(), privateKey, algorithm);
			ok = RSAUtils.verify(plain.getBytes(), publicKey, enSign, algorithm);
			assertTrue(ok);
		} catch (Exception e) {
			fail(e);
		}

	}
}
