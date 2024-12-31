package com.example.设计模式.创建型模式.生成器;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 10:32
 * @Version 1.0
 **/

import com.example.设计模式.创建型模式.生成器.builders.CarBuilder;
import com.example.设计模式.创建型模式.生成器.builders.CarManualBuilder;
import com.example.设计模式.创建型模式.生成器.cars.Car;
import com.example.设计模式.创建型模式.生成器.cars.Manual;
import com.example.设计模式.创建型模式.生成器.director.Director;

/**
 * Demo class. Everything comes together here.
 */
public class Demo {
    //"star": "⭐"
    public static void main(String[] args) {
        Director director = new Director();

        //director 从客户端（应用程序代码）获取具体的构建器对象。
        // 那是因为应用程序更清楚使用哪个构建器来获取特定产品。
        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);

        //最终产品通常是从 builder 对象中检索的
        // 因为director不知道也不依赖混凝土建筑商和产品。
        Car car = builder.getResult();
        System.out.println("Car built:\n" + car.getCarType());

        CarManualBuilder manualBuilder = new CarManualBuilder();

        //director可能知道几种建筑配方。
        director.constructSportsCar(manualBuilder);
        Manual carManual = manualBuilder.getResult();
        System.out.println("\nCar manual built:\n" + carManual.print());
    }

}
