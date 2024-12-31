package com.example.设计模式.行为设计模式.模板方法模式.networks;

/**
 * @description: 社交网络的基类。
 * @author: Azure
 * @date: 2024/8/27 周二 14:55
 * @Version 1.0
 **/
public abstract class Network {
    String userName;
    String password;

    Network() {}

    /**
     * 将数据发布到任何网络。
     */
    public boolean post(String message) {
        //发布前进行身份验证。每个网络使用不同的
        //authentication 方法。
        if (logIn(this.userName, this.password)) {
            // Send the post data.
            boolean result =  sendData(message.getBytes());
            logOut();
            return result;
        }
        return false;
    }

    abstract boolean logIn(String userName, String password);
    abstract boolean sendData(byte[] data);
    abstract void logOut();
}
