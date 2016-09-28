package com.coral.practice.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by qiuhai on 2016/6/3.
 */
public class EncryptUtils {

    /**
     * 加密算法
     * @param encryptType 加密算法类型 支持sha md5
     * @param target 加密字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String encryptType,String target){

        try {
            MessageDigest digest = MessageDigest.getInstance(encryptType);
            digest.update(target.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String [] args){
        String test="{412325197409201216}{098767683}";
        String sha1 = encrypt("sha1",test);
        System.out.println(sha1);
        System.out.println("7110eda4d09e062aa5e4a390b0a572ac0d2c0220".equals(sha1));
    }
}
