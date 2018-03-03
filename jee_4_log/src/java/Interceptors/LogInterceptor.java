package Interceptors;

import Annotations.InjectAnnotation;
import InterceptorLogic.Interceptor;
import Logger.Logger;

import java.lang.reflect.Method;

public class LogInterceptor extends Interceptor {

    private Logger logger;

    @Override
    public Object proceed(Object object, Method method, Object[] args) {
        logger.log(method.getName() + ": in progress...");
        Object result = this.getNext().proceed(object, method, args);
        logger.log(method.getName() + ": finished.");
        return result;
    }
}
