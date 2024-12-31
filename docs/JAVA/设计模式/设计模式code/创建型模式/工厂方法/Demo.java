package com.example.设计模式.创建型模式.工厂方法;

import com.example.设计模式.创建型模式.工厂方法.factory.Dialog;
import com.example.设计模式.创建型模式.工厂方法.factory.HtmlDialog;
import com.example.设计模式.创建型模式.工厂方法.factory.WindowsDialog;
/**
 * @description: Demo class. Everything comes together here.
 * @author: Azure
 * @date: 2024/8/28 周三 9:26
 * @Version 1.0
 **/

public class Demo {
    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    /**
     * The concrete factory is usually chosen depending on configuration or
     * environment options.
     */
    static void configure() {
        if ("Windows 10".equals(System.getProperty("os.name")) || "Windows 11".equals(System.getProperty("os.name"))) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    /**
     * All of the client code should work with factories and products through
     * abstract interfaces. This way it does not care which factory it works
     * with and what kind of product it returns.
     */
    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}
