package Interceptors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface IInterceptor {

    Object proceed(Object object, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;

    IInterceptor getNext();

    void setNext(IInterceptor interceptor);

}
