package InterceptorLogic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LastInterceptor extends Interceptor {

    @Override
    public Object proceed(Object object, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(object, args);
    }

    @Override
    public Interceptor getNext() {
        return null;
    }

    @Override
    public void setNext(Interceptor interceptor) {}
}
