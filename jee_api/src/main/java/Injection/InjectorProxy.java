package Injection;

import Handlers.InjectionHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class InjectorProxy {

    public Object getInstanceProxy(Field field) {
        return Proxy.newProxyInstance(field.getClass().getClassLoader(), new Class<?>[] {field.getType()}, new InjectionHandler(field));
    }
}
