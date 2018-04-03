package cn.javadevelop.test.service.serviceImpl;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by jianhao on 2018/4/1.
 */
public class CglibProxyExample implements MethodInterceptor{

    public Object getProxy(Class cls){
        //CGLIB enhancer 增强类对象
        Enhancer enhancer = new Enhancer();
        //设置增强类型
        enhancer.setSuperclass(cls);
        //定义代理逻辑对象为当前对象 要求当前对象实现MethodInterceptor方法
        enhancer.setCallback(this);
        //生成并返回代理对象
        return enhancer.create();

    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.err.println("调用真实对象前");
        Object result = methodProxy.invokeSuper(o,objects);
        System.err.println("调用真实对象后");
        return result;
    }


    @Test
    public void  testCGLIBProxy(){
        CglibProxyExample cpe = new CglibProxyExample();
        ReflectServiceImpl obj = (ReflectServiceImpl)cpe.getProxy(ReflectServiceImpl.class);
        obj.sayHello("yangyang");
    }





























}
