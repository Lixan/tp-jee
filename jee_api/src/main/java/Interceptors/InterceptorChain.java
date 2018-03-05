package Interceptors;

import Injection.ClassInstanciator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InterceptorChain {

    private IInterceptor firstInterceptor;
    private IInterceptor lastInterceptor;

    public InterceptorChain() {
        lastInterceptor = new LastInterceptor();
        firstInterceptor = lastInterceptor;
    }

    public void addInterceptor(Class<? extends IInterceptor> interceptorClass) throws InstantiationException, IllegalAccessException {
        IInterceptor interceptorInstance = (IInterceptor) ClassInstanciator.instanciateClass(interceptorClass);

        IInterceptor tempInterceptor = firstInterceptor;
        firstInterceptor = interceptorInstance;
        firstInterceptor.setNext(tempInterceptor);
    }

    public Object proceed(Object object, Method method, Object[] args) throws Exception {
        return firstInterceptor.proceed(object, method, args);
    }
}
