package com.darkly.launch.factory;

import com.darkly.launch.service.FlagService;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public abstract class FeatureFlagFactoryBean<T> implements FactoryBean<T> {

    private final Class<T> targetClass;
    private final FlagService flagService;
    private final T beanWhenTrue;
    private final T beanWhenFalse;

    public FeatureFlagFactoryBean(
            Class<T> targetClass,
            FlagService flagService,
            T beanWhenTrue,
            T beanWhenFalse) {
        this.targetClass = targetClass;
        this.flagService = flagService;
        this.beanWhenTrue = beanWhenTrue;
        this.beanWhenFalse = beanWhenFalse;
    }

    @Override
    public T getObject() {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            if (flagService.isNewServiceEnabled()) {
                return method.invoke(beanWhenTrue, args);
            } else {
                return method.invoke(beanWhenFalse, args);
            }
        };

        Object proxy = Proxy.newProxyInstance(
                targetClass.getClassLoader(),
                new Class[]{targetClass},
                invocationHandler);

        return (T) proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return targetClass;
    }
}
