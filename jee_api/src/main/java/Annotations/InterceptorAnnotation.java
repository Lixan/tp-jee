package Annotations;

import Interceptors.IInterceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value= RetentionPolicy.RUNTIME)
public @interface InterceptorAnnotation {
    Class<? extends IInterceptor> interceptor();
}
