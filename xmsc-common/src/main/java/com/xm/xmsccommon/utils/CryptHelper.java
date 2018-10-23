package com.xm.xmsccommon.utils;

import lombok.NonNull;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class CryptHelper {
    public CryptHelper() {
    }

    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return (new BigInteger(1, md.digest())).toString(16);
        } catch (Exception var2) {
            throw new RuntimeException("MD5加密出现错误");
        }
    }

    public static String genUUid() {
        return UUID.randomUUID().toString();
    }

    public static String encryptByAESAlgorithm(String strToEncrypt, String secret) {
        if (null != strToEncrypt && null != secret) {
            String encryptStr = null;

            try {
                SecretKeySpec secretKey = getAESSecretKey(secret);
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(1, secretKey);
                byte[] encryptBytes = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
                encryptStr = Base64.getEncoder().encodeToString(encryptBytes);
                return encryptStr;
            } catch (Exception var6) {
                throw new RuntimeException("Error while encrypting: " + var6.toString());
            }
        } else {
            return null;
        }
    }

    public static String decryptByAESAlgorithm(String strToDecrypt, String secret) {
        if (null != strToDecrypt && null != secret) {
            String decryptStr = null;

            try {
                SecretKeySpec secretKey = getAESSecretKey(secret);
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                cipher.init(2, secretKey);
                decryptStr = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
                return decryptStr;
            } catch (Exception var5) {
                throw new RuntimeException("Error while decrypting: " + var5.toString());
            }
        } else {
            return null;
        }
    }

    private static SecretKeySpec getAESSecretKey(@NonNull String myKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (myKey == null) {
            throw new NullPointerException("myKey");
        } else {
            SecretKeySpec secretKey = null;
            byte[] key = null;
            MessageDigest sha = null;
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
            return secretKey;
        }
    }
}
