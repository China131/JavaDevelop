package cn.javadevelop.test.拦截器;

import cn.javadevelop.test.service.HelloWorld;
import cn.javadevelop.test.service.serviceImpl.HelloWorldImpl;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by jianhao on 2018/4/1.
 */
public class InterceptorJdkProxy implements InvocationHandler{
    private Object target;//真实对象
    private String interceptorClass = null;//拦截器全限定名
    public InterceptorJdkProxy(Object target,String interceptorClass){
        this.target = target;
        this.interceptorClass = interceptorClass;
    }

    public InterceptorJdkProxy(){}

    /**
     * 绑定委托对象并返回一个代理占位
     * @param target
     * @param interceptorClass
     * @return
     */
    public static Object bind(Object target,String interceptorClass){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces()
                ,new InterceptorJdkProxy(target,interceptorClass));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (interceptorClass == null){
            return method.invoke(target,args);
        }
        Object result = null;
        //通过反射生成拦截器
        Interceptor interceptor = (Interceptor) Class.forName(interceptorClass).newInstance();
        if (interceptor.before(proxy,target,method,args)){
            result = method.invoke(target,args);
        }else {
            interceptor.around(proxy,target,method,args);
        }
        interceptor.after(proxy,target,method,args);
        return result;
    }

    public static void main(String[] args){
        HelloWorld proxy = (HelloWorld)InterceptorJdkProxy.bind(new HelloWorldImpl(),"cn.javadevelop.test.拦截器.MyInterceptor");
        proxy.sayHelloWorld();
    }
}
