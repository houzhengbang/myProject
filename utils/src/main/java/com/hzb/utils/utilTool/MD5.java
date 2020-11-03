package com.hzb.utils.utilTool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 *
 * @author jiang.li
 * @date 2013-12-17 14:09
 */
public class MD5 {

    /**
     * 全局数组
     **/
    private final static String[] DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F"};

    /**
     * 返回形式为数字跟字符串
     */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return DIGITS[iD1] + DIGITS[iD2];
    }

    /**
     * 转换字节数组为16进制字串
     */
    private static String byteToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aBByte : bytes) {
            sb.append(byteToArrayString(aBByte));
        }
        return sb.toString();
    }

    /**
     * MD5加密
     *
     * @param str 待加密的字符串
     * @return MD5加密后的字符串
     */
    public static String getMD5Code(String str) {
        String result = str;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = byteToString(md.digest(str.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * MD5加密
     *
     * @param str       待加密的字符串
     * @param lowerCase 大小写
     * @return MD5加密后的字符串
     */
    public static String getMD5Code(String str, boolean lowerCase) {
        String result = str;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = byteToString(md.digest(str.getBytes()));
            if (lowerCase) {
                result = result.toLowerCase();
            }
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
