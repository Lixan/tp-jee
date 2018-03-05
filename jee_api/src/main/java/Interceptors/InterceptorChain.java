package Interceptors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InterceptorChain {

    private IInterceptor firstInterceptor;
    private IInterceptor lastInterceptor;

    public InterceptorChain() {
        lastInterceptor = new LastInterceptor();
        firstInterceptor = lastInterceptor;
    }

    public void addInterceptor(IInterceptor interceptor) {
        IInterceptor tempInterceptor = firstInterceptor;
        firstInterceptor = interceptor;
        firstInterceptor.setNext(tempInterceptor);
    }

    public Object proceed(Object object, Method method, Object[] args) throws Exception {
        return firstInterceptor.proceed(object, method, args);
    }
}
