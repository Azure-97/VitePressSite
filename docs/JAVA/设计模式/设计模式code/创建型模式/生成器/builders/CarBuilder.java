package com.example.设计模式.创建型模式.生成器.builders;

import com.example.设计模式.创建型模式.生成器.cars.Car;
import com.example.设计模式.创建型模式.生成器.cars.CarType;
import com.example.设计模式.创建型模式.生成器.components.Engine;
import com.example.设计模式.创建型模式.生成器.components.GPSNavigator;
import com.example.设计模式.创建型模式.生成器.components.Transmission;
import com.example.设计模式.创建型模式.生成器.components.TripComputer;
/**
 * @description: 汽车生成器
 * @author: Azure
 * @date: 2024/8/28 周三 10:20
 * @Version 1.0
 **/
public class CarBuilder implements Builder {
    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    public void setCarType(CarType type) {
        this.type = type;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
    }

    public Car getResult() {
        return new Car(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }
}
