package Annotations;

import Interceptors.LogInterceptor;
import Logger.*;

import java.lang.annotation.*;

@Target(value={ElementType.METHOD})
@Retention(value= RetentionPolicy.RUNTIME)
@InterceptorAnnotation(interceptor= LogInterceptor.class)
public @interface LogAnnotation {

    Level level() default Level.INFO;

    Class<? extends ILogger> logger() default Logger.class;
}
