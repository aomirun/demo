// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.7.2.
// **********************************************************************

package com.example.tarsmqserver.service.rsaserver;

import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.annotation.*;
import com.qq.tars.common.support.Holder;
import java.util.concurrent.CompletableFuture;

@Servant
public interface CipherPrx {

	 boolean getPublicKey(@TarsMethodParameter(name="typ")int typ, @TarsHolder(name="pubKey") Holder<String> pubKey);

	 boolean getPublicKey(@TarsMethodParameter(name="typ")int typ, @TarsHolder(name="pubKey") Holder<String> pubKey, @TarsContext java.util.Map<String, String> ctx);

	 void async_getPublicKey(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="typ")int typ);

	 void async_getPublicKey(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="typ")int typ, @TarsContext java.util.Map<String, String> ctx);

	 CompletableFuture<java.lang.Boolean>  promise_getPublicKey(@TarsMethodParameter(name="typ")int typ, @TarsHolder(name="pubKey") Holder<String> pubKey);

	 CompletableFuture<java.lang.Boolean>  promise_getPublicKey(@TarsMethodParameter(name="typ")int typ, @TarsHolder(name="pubKey") Holder<String> pubKey, @TarsContext java.util.Map<String, String> ctx);

	 boolean genKey(@TarsHolder(name="priKey") Holder<String> priKey, @TarsHolder(name="pubKey") Holder<String> pubKey);

	 boolean genKey(@TarsHolder(name="priKey") Holder<String> priKey, @TarsHolder(name="pubKey") Holder<String> pubKey, @TarsContext java.util.Map<String, String> ctx);

	 void async_genKey(@TarsCallback CipherPrxCallback callback);

	 void async_genKey(@TarsCallback CipherPrxCallback callback, @TarsContext java.util.Map<String, String> ctx);

	 CompletableFuture<java.lang.Boolean>  promise_genKey(@TarsHolder(name="priKey") Holder<String> priKey, @TarsHolder(name="pubKey") Holder<String> pubKey);

	 CompletableFuture<java.lang.Boolean>  promise_genKey(@TarsHolder(name="priKey") Holder<String> priKey, @TarsHolder(name="pubKey") Holder<String> pubKey, @TarsContext java.util.Map<String, String> ctx);

	 boolean encodeByPrivateKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="enStr") Holder<String> enStr);

	 boolean encodeByPrivateKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="enStr") Holder<String> enStr, @TarsContext java.util.Map<String, String> ctx);

	 void async_encodeByPrivateKey(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="data")String data);

	 void async_encodeByPrivateKey(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="data")String data, @TarsContext java.util.Map<String, String> ctx);

	 CompletableFuture<java.lang.Boolean>  promise_encodeByPrivateKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="enStr") Holder<String> enStr);

	 CompletableFuture<java.lang.Boolean>  promise_encodeByPrivateKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="enStr") Holder<String> enStr, @TarsContext java.util.Map<String, String> ctx);

	 boolean encodeByPublicKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="enStr") Holder<String> enStr);

	 boolean encodeByPublicKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="enStr") Holder<String> enStr, @TarsContext java.util.Map<String, String> ctx);

	 void async_encodeByPublicKey(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="data")String data);

	 void async_encodeByPublicKey(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="data")String data, @TarsContext java.util.Map<String, String> ctx);

	 CompletableFuture<java.lang.Boolean>  promise_encodeByPublicKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="enStr") Holder<String> enStr);

	 CompletableFuture<java.lang.Boolean>  promise_encodeByPublicKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="enStr") Holder<String> enStr, @TarsContext java.util.Map<String, String> ctx);

	 boolean decodeByPrivateKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="plain") Holder<String> plain);

	 boolean decodeByPrivateKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="plain") Holder<String> plain, @TarsContext java.util.Map<String, String> ctx);

	 void async_decodeByPrivateKey(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="data")String data);

	 void async_decodeByPrivateKey(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="data")String data, @TarsContext java.util.Map<String, String> ctx);

	 CompletableFuture<java.lang.Boolean>  promise_decodeByPrivateKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="plain") Holder<String> plain);

	 CompletableFuture<java.lang.Boolean>  promise_decodeByPrivateKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="plain") Holder<String> plain, @TarsContext java.util.Map<String, String> ctx);

	 boolean decodeByPublicKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="plain") Holder<String> plain);

	 boolean decodeByPublicKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="plain") Holder<String> plain, @TarsContext java.util.Map<String, String> ctx);

	 void async_decodeByPublicKey(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="data")String data);

	 void async_decodeByPublicKey(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="data")String data, @TarsContext java.util.Map<String, String> ctx);

	 CompletableFuture<java.lang.Boolean>  promise_decodeByPublicKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="plain") Holder<String> plain);

	 CompletableFuture<java.lang.Boolean>  promise_decodeByPublicKey(@TarsMethodParameter(name="data")String data, @TarsHolder(name="plain") Holder<String> plain, @TarsContext java.util.Map<String, String> ctx);

	 boolean sign(@TarsMethodParameter(name="data")String data, @TarsMethodParameter(name="typ")int typ, @TarsHolder(name="signStr") Holder<String> signStr);

	 boolean sign(@TarsMethodParameter(name="data")String data, @TarsMethodParameter(name="typ")int typ, @TarsHolder(name="signStr") Holder<String> signStr, @TarsContext java.util.Map<String, String> ctx);

	 void async_sign(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="data")String data, @TarsMethodParameter(name="typ")int typ);

	 void async_sign(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="data")String data, @TarsMethodParameter(name="typ")int typ, @TarsContext java.util.Map<String, String> ctx);

	 CompletableFuture<java.lang.Boolean>  promise_sign(@TarsMethodParameter(name="data")String data, @TarsMethodParameter(name="typ")int typ, @TarsHolder(name="signStr") Holder<String> signStr);

	 CompletableFuture<java.lang.Boolean>  promise_sign(@TarsMethodParameter(name="data")String data, @TarsMethodParameter(name="typ")int typ, @TarsHolder(name="signStr") Holder<String> signStr, @TarsContext java.util.Map<String, String> ctx);

	 boolean verify(@TarsMethodParameter(name="plain")String plain, @TarsMethodParameter(name="signStr")String signStr, @TarsMethodParameter(name="typ")int typ);

	 boolean verify(@TarsMethodParameter(name="plain")String plain, @TarsMethodParameter(name="signStr")String signStr, @TarsMethodParameter(name="typ")int typ, @TarsContext java.util.Map<String, String> ctx);

	 void async_verify(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="plain")String plain, @TarsMethodParameter(name="signStr")String signStr, @TarsMethodParameter(name="typ")int typ);

	 void async_verify(@TarsCallback CipherPrxCallback callback, @TarsMethodParameter(name="plain")String plain, @TarsMethodParameter(name="signStr")String signStr, @TarsMethodParameter(name="typ")int typ, @TarsContext java.util.Map<String, String> ctx);

	 CompletableFuture<java.lang.Boolean>  promise_verify(@TarsMethodParameter(name="plain")String plain, @TarsMethodParameter(name="signStr")String signStr, @TarsMethodParameter(name="typ")int typ);

	 CompletableFuture<java.lang.Boolean>  promise_verify(@TarsMethodParameter(name="plain")String plain, @TarsMethodParameter(name="signStr")String signStr, @TarsMethodParameter(name="typ")int typ, @TarsContext java.util.Map<String, String> ctx);
}
