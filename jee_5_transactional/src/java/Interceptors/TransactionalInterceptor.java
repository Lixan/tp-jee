package Interceptors;

import java.lang.reflect.Method;

public class TransactionalInterceptor implements IInterceptor {

    private IInterceptor nextInterceptor;

    public Object proceed(Object object, Method method, Object[] args) throws Exception{

        Object result = null;
        try {
            result = getNext().proceed(object, method, args);
            //commit();
        }
        catch(Exception e) {
            //rollback();
            throw e;
        }
        finally  {
            //afterTreatment();
        }
        return result;
    }

    public IInterceptor getNext() {
        return this.nextInterceptor;
    }

    public void setNext(IInterceptor interceptor) {
        this.nextInterceptor = interceptor;
    }
}
