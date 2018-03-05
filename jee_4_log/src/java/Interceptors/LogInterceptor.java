package Interceptors;

import Logger.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LogInterceptor implements IInterceptor {

    private IInterceptor nextInterceptor;

    private Logger logger;

    public Object proceed(Object object, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        logger.log(method.getName() + ": in progress...");
        Object result = this.getNext().proceed(object, method, args);
        logger.log(method.getName() + ": finished.");
        return result;
    }

    public IInterceptor getNext() {
        return this.nextInterceptor;
    }

    public void setNext(IInterceptor interceptor) {
        this.nextInterceptor = interceptor;
    }
}
