package cn.javadevelop.test.拦截器;

import java.lang.reflect.Method;

/**
 * Created by jianhao on 2018/4/1.
 */
public class MyInterceptor implements Interceptor {
    @Override
    public boolean before(Object proxy, Object targer, Method method, Object[] args) {
        System.out.println("反射方法前逻辑");
        return false;
    }

    @Override
    public void around(Object proxy, Object target, Method method, Object[] args) {
        System.out.printf("取消了被代理对象的方法");
    }

    @Override
    public void after(Object proxy, Object target, Method method, Object[] args) {
        System.out.printf("反射方法后逻辑");
    }

}
