// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.7.2.
// **********************************************************************

package com.example.tarsmqserver.service.mqserver;

import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.annotation.*;
import com.qq.tars.common.support.Holder;

@Servant
public interface MessageServant {

	 boolean send(@TarsMethodParameter(name="msg")String msg);

	 boolean encode(@TarsMethodParameter(name="sign")String sign, @TarsHolder(name="enStr") Holder<String> enStr);

	 boolean encodeWithSend(@TarsMethodParameter(name="msg")String msg);

	 boolean signWithSend(@TarsMethodParameter(name="msg")String msg, @TarsMethodParameter(name="isEncode")boolean isEncode);
}
