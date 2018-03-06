package Interceptors;

import Annotations.TransactionalAnnotation;
import Transaction.*;

import java.lang.reflect.Method;

public class TransactionalInterceptor implements IInterceptor {

    private IInterceptor nextInterceptor;

    private ITransaction configureProperties(Object object, Method method) throws NoSuchMethodException, IllegalAccessException, InstantiationException {
        TransactionalAnnotation transacAnnot = object.getClass().getMethod(method.getName(), method.getParameterTypes())
                .getDeclaredAnnotationsByType(TransactionalAnnotation.class)[0];
        return TransactionInstanciator.getTransaction(transacAnnot.type());
    }

    public Object proceed(Object object, Method method, Object[] args) throws Exception{
        ITransaction transaction = configureProperties(object, method);
        Object result = null;
        try {
            result = getNext().proceed(object, method, args);
            transaction.commit();
        }
        catch(Exception e) {
            transaction.rollback();
            throw e;
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
