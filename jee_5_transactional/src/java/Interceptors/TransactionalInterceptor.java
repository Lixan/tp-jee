package Interceptors;

import java.lang.reflect.Method;

public class TransactionalInterceptor extends IInterceptor {

    public Object proceed(Object object, Method method, Object[] args) {
        Object result = getNext().proceed(object, method, args);
        return result;
    }
}
