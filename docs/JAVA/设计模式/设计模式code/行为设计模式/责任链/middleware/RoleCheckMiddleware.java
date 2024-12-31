package com.example.设计模式.行为设计模式.责任链.middleware;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 23:08
 * @Version 1.0
 **/
public class RoleCheckMiddleware extends Middleware {
    public boolean check(String email, String password) {
        if (email.equals("admin@example.com")) {
            System.out.println("Hello, admin!");
            return true;
        }
        System.out.println("Hello, user!");
        return checkNext(email, password);
    }
}
