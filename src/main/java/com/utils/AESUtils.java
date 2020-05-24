package com.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES加密工具类
 * @author guofa.liu
 * @description
 * @create 2020/05/24 14:54
 */
public class AESUtils {


    public static String aesKey = "E39BC7E453B03166";

    public static String decryptAES(String paramString1) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        return decryptAES(paramString1, aesKey);
    }

    public static String decryptAES(String paramString1, String paramString2) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        byte[] arrayOfByte = paramString2.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretKeySpec, new IvParameterSpec(arrayOfByte));
        return new String(cipher.doFinal(Base64.decodeBase64(paramString1.getBytes())));
    }

    public static String encryptAES(String paramString1) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        return encryptAES(paramString1, aesKey);
    }

    public static String encryptAES(String paramString1, String paramString2) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        byte[] arrayOfByte = paramString2.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec, new IvParameterSpec(arrayOfByte));
        return Base64.encodeBase64String(cipher.doFinal(paramString1.getBytes("UTF-8"))).replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
    }
}
