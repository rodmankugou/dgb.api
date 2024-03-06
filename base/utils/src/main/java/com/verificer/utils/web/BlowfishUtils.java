package com.verificer.utils.web;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 */
public final class BlowfishUtils {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String ALGORITHM_NAME = "Blowfish";

    public static String encrypt(String text, String key) {
        try {
            SecretKeySpec e = new SecretKeySpec(DigestUtils.md5(key), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(1, e);
            return Base64.encodeBase64URLSafeString(cipher.doFinal(text.getBytes("UTF-8")));
        } catch (Exception var4) {
            throw new IllegalStateException("", var4);
        }
    }

    public static String decrypt(String cipherText, String key) {
        try {
            SecretKeySpec e = new SecretKeySpec(DigestUtils.md5(key), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(2, e);
            byte[] data = Base64.decodeBase64(cipherText);
            return new String(cipher.doFinal(data), "UTF-8");
        } catch (Exception var5) {
            throw new IllegalStateException("", var5);
        }
    }

    private BlowfishUtils() {
    }
}
