package Annotations;


import java.lang.annotation.*;

@Target(value={ElementType.METHOD,ElementType.CONSTRUCTOR,ElementType.TYPE})
@Retention(value= RetentionPolicy.RUNTIME)
@Documented
public @interface SingletonAnnotation {
}
