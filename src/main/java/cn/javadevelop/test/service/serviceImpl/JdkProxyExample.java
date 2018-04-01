package cn.javadevelop.test.service.serviceImpl;

import cn.javadevelop.test.service.HelloWorld;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by jianhao on 2018/3/31.
 */
public class JdkProxyExample implements InvocationHandler {
    //真实对象
    private Object target = null;

    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.printf("进入代理逻辑方法");
        System.out.printf("在调度真实对象之前的服务");
        Object obj = method.invoke(target,args);
        System.out.printf("在调度真实对象之后的服务");
        return obj;
    }

    @Test
    public void test(){
//        JdkProxyExample jdkProxyExample = new JdkProxyExample();
//        HelloWorld proxy = (HelloWorld)jdkProxyExample.bind(new HelloWorldImpl());
//        proxy.sayHelloWorld();

        HelloWorld proxy1 = new HelloWorldImpl();
        proxy1.sayHelloWorld();

    }

    public void test1(Class<Object> cls){
        JdkProxyExample jdkProxyExample = new JdkProxyExample();
//        cls proxy = (cls) jdkProxyExample.bind(new HelloWorldImpl());

    }
}
