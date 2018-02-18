package Annotations;

import java.lang.annotation.*;

@Target(value={ElementType.FIELD})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface QualifierAnnotation {
    public String id();
}
