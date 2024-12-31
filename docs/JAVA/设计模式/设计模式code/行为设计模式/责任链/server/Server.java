package com.example.设计模式.行为设计模式.责任链.server;

/**
 * @description: 授权目标
 * @author: Azure
 * @date: 2024/8/28 周三 23:11
 * @Version 1.0
 **/
import com.example.设计模式.行为设计模式.责任链.middleware.Middleware;

import java.util.HashMap;
import java.util.Map;

/**
 * Server class.
 */
public class Server {
    private Map<String, String> users = new HashMap<>();
    private Middleware middleware;

    /**
     * Client 将对象链传递给 server。这提高了灵活性，并使测试 server 类变得更加容易。
     */
    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
    }

    /**
     * Server 从客户端获取电子邮件和密码，并将授权请求发送到链上。
     */
    public boolean logIn(String email, String password) {
        if (middleware.check(email, password)) {
            System.out.println("授权成功！");

            // Do something useful here for authorized users.

            return true;
        }
        return false;
    }

    public void register(String email, String password) {
        users.put(email, password);
    }

    public boolean hasEmail(String email) {
        return users.containsKey(email);
    }

    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password);
    }
}
