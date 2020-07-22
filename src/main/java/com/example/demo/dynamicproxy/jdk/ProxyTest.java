package com.example.demo.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest implements InvocationHandler {

    private Target target;

    private ProxyTest(Target target) {
        this.target = target;
    }

    public static Target newProxyInstance(Target target) {
        return (Target) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
                new Class<?>[]{Target.class},
                new ProxyTest(target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("preInvoke");
        Object retValue = method.invoke(target, args);
        System.out.println("postInvoke");

        return retValue;
    }

}
