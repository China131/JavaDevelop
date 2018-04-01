package cn.javadevelop.test.service.serviceImpl;

import cn.javadevelop.test.service.HelloWorld;

/**
 * Created by jianhao on 2018/3/31.
 */
public class HelloWorldImpl implements HelloWorld{
    @Override
    public void sayHelloWorld() {
        System.out.println("Hello world!");
    }
}
