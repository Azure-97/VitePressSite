package com.example.设计模式.行为设计模式.访问者.visitor;

import com.example.设计模式.行为设计模式.访问者.shapes.*;

/**
 * @description: 通用访问者接口
 * @author: Azure
 * @date: 2024/8/28 周三 21:09
 * @Version 1.0
 **/
public interface Visitor {
    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);
}
