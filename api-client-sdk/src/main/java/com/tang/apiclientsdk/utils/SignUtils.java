package com.tang.apiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtils {
    /**
     * 生成签名
     * @param body 请求体内容
     * @param secretKey 密钥
     * @return 生成的签名字符串
     */
    public static String getSign(String body, String secretKey) {
        // 使用SHA256算法的Digester
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        // 构建签名内容，将body和密钥链接在一起
        String testStr = body.toString() + "." + secretKey;
        // 计算签名的摘要并返回摘要的十六进制表示形式
        String digestHex = md5.digestHex(testStr);
        return digestHex;
    }
}
