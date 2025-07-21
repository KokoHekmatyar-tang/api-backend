package com.tang.apiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.tang.apiclientsdk.model.User;

import java.util.HashMap;
import java.util.Map;

import static com.tang.apiclientsdk.utils.SignUtils.getSign;

public class ApiClient {

    private static final String GATEWAY_HOST="http://localhost:8090";

    private String accessKey;

    private String secretKey;



    public ApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    // 使用GET方法从服务器获取名称信息
    public String getNameByGet(String name) {
        // 可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        // 将"name"参数添加到映射中
        paramMap.put("name", name);
        // 使用HttpUtil工具发起GET请求，并获取服务器返回的结果
        String result = HttpUtil.get(GATEWAY_HOST+"/api/name/", paramMap);
        // 打印服务器返回的结果
        System.out.println(result);
        // 返回服务器返回的结果
        return result;
    }

    // 使用POST方法从服务器获取名称信息
    public String getNameByPost(String name) {
        // 可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        // 使用HttpUtil工具发起POST请求，并获取服务器返回的结果
        String result = HttpUtil.post(GATEWAY_HOST+"/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    // 使用POST方法向服务器发送User对象，并获取服务器返回的结果
    public String getUserNameByPost(User user) {
        // 将User对象转换为JSON字符串
        String json = JSONUtil.toJsonStr(user);
        // 使用HttpRequest工具发起POST请求，并获取服务器的响应
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST+"/api/name/user")
                .addHeaders(getHeaderMap(json)) //添加构造的请求头
                .body(json) // 将JSON字符串设置为请求体
                .execute(); // 执行请求
        // 打印服务器返回的状态码
        System.out.println(httpResponse.getStatus());
        // 获取服务器返回的结果
        String result = httpResponse.body();
        // 打印服务器返回的结果
        System.out.println(result);
        // 返回服务器返回的结果
        return result;
    }

    //创建私有方法,用于构建请求头
    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hasMap = new HashMap<>();
        hasMap.put("accessKey", accessKey);
        //不能直接发送密钥
//        hasMap.put("secretKey", secretKey);
        hasMap.put("nonce", RandomUtil.randomNumbers(4));
        //请求体内容
        hasMap.put("body", body);
        //得到的毫秒数/1000
        hasMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hasMap.put("sign", getSign(body, secretKey));
        return hasMap;
    }
}
