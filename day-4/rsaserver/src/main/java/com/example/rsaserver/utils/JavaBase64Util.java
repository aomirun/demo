package com.example.rsaserver.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @Author fankunfeng
 * @Date 2019-01-22 14:39:23
 * @Describe Java8之后Util包Base64编码，比apache方式效率更高
 */
public class JavaBase64Util {
    public static final String UTF_8 = "UTF-8";
    public static Base64.Encoder encoder;
    public static Base64.Encoder urlEncoder;
    public static Base64.Decoder decoder;
    public static Base64.Decoder urlDecoder;

    static {
        encoder = Base64.getEncoder();
        urlEncoder = Base64.getUrlEncoder();
        decoder = Base64.getDecoder();
        urlDecoder = Base64.getUrlDecoder();
    }

    /**
     * encode
     * @param bytes
     * @return byte[]
     */
    public static byte[] encode(final byte[] bytes) {
        return encoder.encode(bytes);
    }

    /**
     * encode
     * @param string
     * @return
     */
    public static String encode(final String string) {
        final byte[] encode = encode(string.getBytes());
        try {
            return new String(encode, UTF_8);
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * encode
     * @param bytes
     * @return
     */
    public static String encode2String(final byte[] bytes) {
        return encoder.encodeToString(bytes);
    }

    /**
     * encode
     * @param string
     * @return
     */
    public static byte[] encode2Byte(final String string) {
        return encode(string.getBytes());
    }

    /**
     * urlEncoder
     * @param bytes
     * @return
     */
    public static byte[] urlEncode(final byte[] bytes) {
        return urlEncoder.encode(bytes);
    }

    /**
     * urlEncoder
     * @param string
     * @return
     */
    public static String urlEncode(final String string) {
        final byte[] encode = urlEncode(string.getBytes());
        try {
            return new String(encode, UTF_8);
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * urlEncoder
     * @param bytes
     * @return
     */
    public static String urlEncode2String(final byte[] bytes) {
        return urlEncoder.encodeToString(bytes);
    }

    /**
     * urlEncoder
     * @param string
     * @return
     */
    public static byte[] urlEncode2Byte(final String string) {
        return urlEncode(string.getBytes());
    }

    /**
     * decode
     * @param bytes
     * @return
     */
    public static byte[] decode(final byte[] bytes) {
        return decoder.decode(bytes);
    }

    /**
     * decode
     * @param string
     * @return
     */
    public static byte[] decode2Byte(final String string) {
        return decoder.decode(string.getBytes());
    }

    /**
     * decode
     * @param bytes
     * @return
     */
    public static String decode2String(final byte[] bytes) {
        try {
            return new String(decoder.decode(bytes), UTF_8);
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * decode
     * @param string
     * @return
     */
    public static String decode(final String string) {
        final byte[] decode = decode(string.getBytes());
        try {
            return new String(decode, UTF_8);
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * urlDecode
     * @param bytes
     * @return
     */
    public static byte[] urlDecode(final byte[] bytes) {
        return urlDecoder.decode(bytes);
    }

    /**
     * urlDecode
     * @param string
     * @return
     */
    public static byte[] urlDecode2Byte(final String string) {
        return urlDecode(string.getBytes());
    }

    /**
     * urlDecode
     * @param bytes
     * @return
     */
    public static String urlDecode2String(final byte[] bytes) {
        try {
            return new String(urlDecode(bytes), UTF_8);
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * urlDecode
     * @param string
     * @return
     */
    public static String urlDecode(final String string) {
        final byte[] decode = urlDecode(string.getBytes());
        try {
            return new String(decode, UTF_8);
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}