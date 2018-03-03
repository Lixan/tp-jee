package Annotations;

import InterceptorLogic.Interceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value= RetentionPolicy.RUNTIME)
public @interface InterceptorAnnotation {
    Class<? extends Interceptor> interceptor();
}
