package Annotations;

import Interceptors.TransactionalInterceptor;

import java.lang.annotation.*;

@Target(value={ElementType.METHOD})
@Retention(value= RetentionPolicy.RUNTIME)
@InterceptorAnnotation(interceptor= TransactionalInterceptor.class)
public @interface TransactionalAnnotation {
}
