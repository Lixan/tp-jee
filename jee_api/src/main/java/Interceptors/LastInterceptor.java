package Interceptors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LastInterceptor implements IInterceptor {

    public Object proceed(Object object, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(object, args);
    }

    public IInterceptor getNext() {
        return null;
    }

    public void setNext(IInterceptor interceptor) {}
}
