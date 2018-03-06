package Annotations;

import Interceptors.TransactionalInterceptor;
import Transaction.TransactionType;

import java.lang.annotation.*;

@Target(value={ElementType.METHOD})
@Retention(value= RetentionPolicy.RUNTIME)
@InterceptorAnnotation(interceptor= TransactionalInterceptor.class)
public @interface TransactionalAnnotation {
    TransactionType type() default TransactionType.REQUIRED_NEW;
}
