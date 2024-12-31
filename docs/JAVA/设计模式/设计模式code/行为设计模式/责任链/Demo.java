package com.example.设计模式.行为设计模式.责任链;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 23:12
 * @Version 1.0
 **/
import com.example.设计模式.行为设计模式.责任链.middleware.Middleware;
import com.example.设计模式.行为设计模式.责任链.middleware.RoleCheckMiddleware;
import com.example.设计模式.行为设计模式.责任链.middleware.ThrottlingMiddleware;
import com.example.设计模式.行为设计模式.责任链.middleware.UserExistsMiddleware;
import com.example.设计模式.行为设计模式.责任链.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Demo {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init() {
        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        // 所有检查都已链接。客户可以使用相同的组件构建各种链。
        Middleware middleware = Middleware.link(
                new ThrottlingMiddleware(2),
                new UserExistsMiddleware(server),
                new RoleCheckMiddleware()
        );

        // Server 从 Client 端代码获取链。
        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success;
        do {
            System.out.print("Enter email: ");
            String email = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();
            success = server.logIn(email, password);
        } while (!success);
    }
}
