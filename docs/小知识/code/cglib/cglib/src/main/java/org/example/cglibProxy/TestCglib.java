package org.example.cglibProxy;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class TestCglib {
    public static void main(String args[]) {
        /**
         * @Author 84642
         * @Description 这里Enhancer类是CGLib中的一个字节码增强器，
         * 它可以方便的对你想要处理的类进行扩展，以后会经常看到它。
         * 首先将被代理类TargetObject设置成父类，
         * 然后设置拦截器TargetInterceptor，
         * 最后执行enhancer.create()动态生成一个代理类，
         * 并从Object强制转型成父类型TargetObject。
         * 最后，在代理类上调用方法。
         **/
        //字节码增强器
        Enhancer enhancer =new Enhancer();
        //设置字节码增强器的父类
        enhancer.setSuperclass(TargetObject.class);
        //过滤器
        CallbackFilter callbackFilter = new TargetMethodCallbackFilter();
        //拦截器
        Callback callback1=new TargetInterceptor();
        //线程安全单例实例
        Callback noopCb=NoOp.INSTANCE;
        //返回原始方法调用应返回的对象。 每次 方法调用时都会调用此方法
        Callback fixedValue=new TargetResultFixed();
        Callback[] cbarray=new Callback[]{callback1,noopCb,fixedValue};
        //目标对象拦截器，实现MethodInterceptor
        //enhancer.setCallback(new TargetInterceptor());


        enhancer.setCallbacks(cbarray);
        //设置回调过滤器
        enhancer.setCallbackFilter(callbackFilter);

        //创建实例
        TargetObject targetObject2=(TargetObject)enhancer.create();
        //调用方法
        System.out.println(targetObject2.method1("mmm1"));
        System.out.println(targetObject2.method2(100));
        System.out.println(targetObject2.method3(200));
    }
}