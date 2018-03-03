package InterceptorLogic;

import java.lang.reflect.Method;

public abstract class Interceptor {

    private Interceptor nextInterceptor;

    public abstract Object proceed(Object object, Method method, Object[] args);

    public Interceptor getNext() {
        return this.nextInterceptor;
    }

    void setNext(Interceptor interceptor) {
        this.nextInterceptor = interceptor;
    }

}
