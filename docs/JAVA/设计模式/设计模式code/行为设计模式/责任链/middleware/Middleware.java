package com.example.设计模式.行为设计模式.责任链.middleware;

/**
 * @description: 基础验证接口
 * @author: Azure
 * @date: 2024/8/28 周三 23:05
 * @Version 1.0
 **/
public abstract class Middleware {
    private Middleware next;

    /**
     * 构建中间件对象链。
     */
    public static Middleware link(Middleware first, Middleware... chain) {
        Middleware head = first;
        for (Middleware nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    /**
     * 子类将通过具体检查来实现此方法。
     */
    public abstract boolean check(String email, String password);

    /**
     * 对 chain 中的下一个对象运行 check，如果我们在 chain 中的最后一个对象中，则结束遍历
     */
    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}
