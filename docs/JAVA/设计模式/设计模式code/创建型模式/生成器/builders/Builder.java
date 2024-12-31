package com.example.设计模式.创建型模式.生成器.builders;

import com.example.设计模式.创建型模式.生成器.cars.CarType;
import com.example.设计模式.创建型模式.生成器.components.Engine;
import com.example.设计模式.创建型模式.生成器.components.GPSNavigator;
import com.example.设计模式.创建型模式.生成器.components.Transmission;
import com.example.设计模式.创建型模式.生成器.components.TripComputer;

/**
 * @description: 通用生成器接口
 * @author: Azure
 * @date: 2024/8/28 周三 10:19
 * @Version 1.0
 **/
public interface Builder {
    void setCarType(CarType type);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
