package Annotations;

import java.lang.annotation.*;

@Target(value={ElementType.METHOD,ElementType.CONSTRUCTOR,ElementType.FIELD})
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
public @interface InjectAnnotation
{
}