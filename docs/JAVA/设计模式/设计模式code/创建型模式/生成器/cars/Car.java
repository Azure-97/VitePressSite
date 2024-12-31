package com.example.设计模式.创建型模式.生成器.cars;

import com.example.设计模式.创建型模式.生成器.components.Engine;
import com.example.设计模式.创建型模式.生成器.components.GPSNavigator;
import com.example.设计模式.创建型模式.生成器.components.Transmission;
import com.example.设计模式.创建型模式.生成器.components.TripComputer;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/28 周三 10:23
 * @Version 1.0
 **/
public class Car {
    private final CarType carType;
    //座位
    private final int seats;
    private final Engine engine;
    private final Transmission transmission;
    private final TripComputer tripComputer;
    private final GPSNavigator gpsNavigator;
    //燃料
    private double fuel = 0;

    public Car(CarType carType, int seats, Engine engine, Transmission transmission,
               TripComputer tripComputer, GPSNavigator gpsNavigator) {
        this.carType = carType;
        this.seats = seats;
        this.engine = engine;
        this.transmission = transmission;
        this.tripComputer = tripComputer;
        if (this.tripComputer != null) {
            this.tripComputer.setCar(this);
        }
        this.gpsNavigator = gpsNavigator;
    }

    public CarType getCarType() {
        return carType;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getSeats() {
        return seats;
    }

    public Engine getEngine() {
        return engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public TripComputer getTripComputer() {
        return tripComputer;
    }

    public GPSNavigator getGpsNavigator() {
        return gpsNavigator;
    }
}
