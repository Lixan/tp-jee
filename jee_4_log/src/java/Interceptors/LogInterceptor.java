package Interceptors;

import Annotations.LogAnnotation;
import Logger.*;

import java.lang.reflect.Method;

public class LogInterceptor implements IInterceptor {

    private IInterceptor nextInterceptor;

    private ILogger logger;

    public LogInterceptor() {
        logger = new Logger();
    }

    private void configureProperties(Object object, Method method) throws NoSuchMethodException, IllegalAccessException, InstantiationException {
        LogAnnotation logAnnot = object.getClass().getMethod(method.getName(), method.getParameterTypes())
                .getDeclaredAnnotationsByType(LogAnnotation.class)[0];
        logger = (ILogger) logAnnot.logger().newInstance();
        logger.setLevel(logAnnot.level());
    }

    public Object proceed(Object object, Method method, Object[] args) throws Exception {
        configureProperties(object, method);

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
