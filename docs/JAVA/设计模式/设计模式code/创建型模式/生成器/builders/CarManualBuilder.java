package com.example.设计模式.创建型模式.生成器.builders;

import com.example.设计模式.创建型模式.生成器.cars.CarType;
import com.example.设计模式.创建型模式.生成器.cars.Manual;
import com.example.设计模式.创建型模式.生成器.components.Engine;
import com.example.设计模式.创建型模式.生成器.components.GPSNavigator;
import com.example.设计模式.创建型模式.生成器.components.Transmission;
import com.example.设计模式.创建型模式.生成器.components.TripComputer;
/**
 * @description:
 * <p>与其他创建模式不同，Builder 可以构造不相关的产品，这些产品没有通用接口。</p>
 * <p>在本例中，我们使用与制造汽车相同的步骤为汽车构建用户手册。这允许为特定车型制作手册，配置不同的功能。</p>
 * @author: Azure
 * @date: 2024/8/28 周三 10:20
 * @Version 1.0
 **/

public class CarManualBuilder implements Builder{
    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
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

    public Manual getResult() {
        return new Manual(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }
}
