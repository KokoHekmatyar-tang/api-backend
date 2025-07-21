package com.tang.apiinterface.controller;


import com.tang.apiclientsdk.model.User;
import com.tang.apiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 名称 API
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/get")
    public String getNameByGet( String name,HttpServletRequest request) {
        System.out.println(request.getHeader("tang"));
        return "GET 你的名字是" + name;
    }

//    @GetMapping("/get")
//    public String getNameByGet(@RequestParam String name) {
//        return "GET 你的名字是" + name;
//    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }


    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");

        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");
        //校验,查询数据库的验证权限
        if (!accessKey.equals("tang")) {
            throw new RuntimeException("无权限");
        }
//        TODO
        // 3.校验一下随机数,随机数存储
        // 校验随机数,模拟一下,直接判断nonce是否大于10000
        if (Long.parseLong(nonce) > 10000) {
            throw new RuntimeException("无权限");
        }
        // 4.校验时间戳与当前时间的差距
        long timestamps = Long.parseLong(timestamp);

        // 获取当前时间的秒级时间戳
        long currentTimestamp = System.currentTimeMillis() / 1000;

        // 判断是否超过5分钟（300秒）
        if (currentTimestamp - timestamps > 300) {
            throw new RuntimeException("无权限");
//            throw new RuntimeException("时间戳已过期，超过5分钟");
        }
        String serverSign = SignUtils.getSign(body, "abcdefgh");
        //如果生成的标签不一致,则抛出异常
        if (!sign.equals(serverSign)) {
            throw new RuntimeException("无权限");
        }
        //TODO 调用次数+1
        String result = "POST 用户名字是" + user.getUsername();
        return result;
    }
}
