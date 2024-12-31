package com.example.设计模式.创建型模式.生成器.components;

/**
 * @description: GPS导航
 * @author: Azure
 * @date: 2024/8/28 周三 10:25
 * @Version 1.0
 **/
public class GPSNavigator {
    private String route;

    public GPSNavigator() {
        this.route = "221b, Baker Street, London  to Scotland Yard, 8-10 Broadway, London";
    }

    public GPSNavigator(String manualRoute) {
        this.route = manualRoute;
    }

    public String getRoute() {
        return route;
    }
}
