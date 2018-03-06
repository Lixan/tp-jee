package Interceptors;

import Annotations.TransactionalAnnotation;
import Transaction.*;

import java.lang.reflect.Method;

public class TransactionalInterceptor implements IInterceptor {

    private IInterceptor nextInterceptor;

    private ITransaction transaction;

    public ITransaction getTransaction() {
        return transaction;
    }

    private ITransaction configureProperties(Object object, Method method) throws NoSuchMethodException, IllegalAccessException, InstantiationException {
        TransactionalAnnotation transacAnnot = object.getClass().getMethod(method.getName(), method.getParameterTypes())
                .getDeclaredAnnotationsByType(TransactionalAnnotation.class)[0];
        return TransactionManager.getTransaction(transacAnnot.type(), object);
    }

    public Object proceed(Object object, Method method, Object[] args) throws Exception{
        transaction = configureProperties(object, method);
        Object result = null;
        try {
            result = getNext().proceed(object, method, args);
            transaction.commit();
        }
        catch(Exception e) {
            transaction.rollback();
        }
        TransactionManager.closeTransaction();
        return result;
    }

    public IInterceptor getNext() {
        return this.nextInterceptor;
    }

    public void setNext(IInterceptor interceptor) {
        this.nextInterceptor = interceptor;
    }
}
