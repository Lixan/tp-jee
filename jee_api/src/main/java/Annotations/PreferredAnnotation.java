package Annotations;

import java.lang.annotation.*;

@Target(value={ElementType.TYPE})
@Retention(value= RetentionPolicy.RUNTIME)
public @interface PreferredAnnotation
{
}
