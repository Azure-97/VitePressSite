package com.example.设计模式.创建型模式.生成器.components;

import com.example.设计模式.创建型模式.生成器.cars.Car;

/**
 * @description: 行车电脑
 * @author: Azure
 * @date: 2024/8/28 周三 10:28
 * @Version 1.0
 **/
public class TripComputer {

    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    public void showFuelLevel() {
        System.out.println("Fuel level: " + car.getFuel());
    }

    public void showStatus() {
        if (this.car.getEngine().isStarted()) {
            System.out.println("Car is started");
        } else {
            System.out.println("Car isn't started");
        }
    }
}
