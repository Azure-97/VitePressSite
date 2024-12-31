package com.example.设计模式.结构型模式.适配器;

/**
 * @description: TODO
 * @author: Azure
 * @date: 2024/8/27 周二 15:57
 * @Version 1.0
 **/

import com.example.设计模式.结构型模式.适配器.adapters.SquarePegAdapter;
import com.example.设计模式.结构型模式.适配器.round.RoundHole;
import com.example.设计模式.结构型模式.适配器.round.RoundPeg;
import com.example.设计模式.结构型模式.适配器.square.SquarePeg;

/**
 * 让方钉适配圆孔
 * 这个简单的例子展示了适配器如何让不兼容的对象相互合作。
 */
public class Demo {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);//圆孔
        RoundPeg rpeg = new RoundPeg(5);//圆钉
        if (hole.fits(rpeg)) {
            System.out.println("圆钉 r5 适合圆孔 r5。");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);
        // hole.fits(smallSqPeg); // 无法编译。

        // Adapter 解决了这个问题。
        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);
        if (hole.fits(smallSqPegAdapter)) {
            System.out.println("方钉 w2 适合圆孔 r5。");
        }
        if (!hole.fits(largeSqPegAdapter)) {
            System.out.println("方钉 w20 不适合圆孔 r5。");
        }
    }
}
