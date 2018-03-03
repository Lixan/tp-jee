package InterceptorLogic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InterceptorChain {

    private Interceptor firstInterceptor;
    private Interceptor lastInterceptor;

    public InterceptorChain() {
        lastInterceptor = new LastInterceptor();
        firstInterceptor = lastInterceptor;
    }

    public void addInterceptor(Interceptor interceptor) {
        Interceptor tempInterceptor = firstInterceptor;
        firstInterceptor = interceptor;
        firstInterceptor.setNext(tempInterceptor);
    }

    public Object proceed(Object object, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        return firstInterceptor.proceed(object, method, args);
    }
}
