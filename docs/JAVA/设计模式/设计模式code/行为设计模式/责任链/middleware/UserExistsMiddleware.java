package com.example.设计模式.行为设计模式.责任链.middleware;

/**
 * @description: 检查用户登录信息
 * @author: Azure
 * @date: 2024/8/28 周三 23:06
 * @Version 1.0
 **/

import com.example.设计模式.行为设计模式.责任链.server.Server;

/**
 * ConcreteHandler。检查具有给定凭据的用户是否存在。
 */
public class UserExistsMiddleware extends Middleware {
    private Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) {
            System.out.println("This email is not registered!");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password!");
            return false;
        }
        return checkNext(email, password);
    }
}
