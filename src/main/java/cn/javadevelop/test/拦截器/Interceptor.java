package cn.javadevelop.test.拦截器;

import java.lang.reflect.Method;

/**
 * Created by jianhao on 2018/4/1.
 */
public interface Interceptor {
    public boolean before(Object proxy, Object targer, Method method,Object[] args);

    public void around(Object proxy,Object target,Method method,Object[] args);

    public void after(Object proxy,Object target,Method method,Object[] args);
}
