package Injection;

import Annotations.SingletonAnnotation;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class ClassInstanciator
{
    private static Map<String, Object> singletonMapping = new HashMap<String, Object>(); // Keep instance for singleton injection

    public static Object instanciateClass(Class<?> classToInstanciate) throws IllegalAccessException, InstantiationException
    {
        Object instance = null;

        for(Annotation annotation : classToInstanciate.getDeclaredAnnotations()) // Check annotations of the class to implement
        {
            if(annotation.annotationType().getName().equals(SingletonAnnotation.class.getName())) // Check if the class is annotated with @SingletonANnotation
            {
                if(singletonMapping.containsKey(classToInstanciate.getName()))
                {
                    instance = singletonMapping.get(classToInstanciate.getName());
                }
                else
                {
                    instance = classToInstanciate.newInstance();
                    singletonMapping.put(classToInstanciate.getName(), instance);
                }
            }
        }

        if(instance == null)
        {
            instance = classToInstanciate.newInstance();
        }

        return instance;
    }
}
