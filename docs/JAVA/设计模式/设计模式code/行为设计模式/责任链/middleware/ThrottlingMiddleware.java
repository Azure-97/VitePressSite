package com.example.设计模式.行为设计模式.责任链.middleware;

/**
 * @description: 检查请求数量限制
 * @author: Azure
 * @date: 2024/8/28 周三 23:06
 * @Version 1.0
 **/
/**
 * ConcreteHandler。检查登录失败请求是否过多。
 */
public class ThrottlingMiddleware extends Middleware {
    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    /**
     * 请不要在此方法的开头和结尾插入 checkNext（） 调用。
     * 这比对所有中间件对象进行简单的循环提供了更大的灵活性。
     * 例如，链的元素可以通过在所有其他检查之后运行其检查来更改检查的顺序。
     */
    public boolean check(String email, String password) {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;

        if (request > requestPerMinute) {
            System.out.println("Request limit exceeded!");
            Thread.currentThread().stop();
        }
        return checkNext(email, password);
    }
}
