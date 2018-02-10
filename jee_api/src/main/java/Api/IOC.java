package Api;

import Annotations.InjectAnnotation;
import Annotations.PreferredAnnotation;
import Annotations.SingletonAnnotation;
import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class IOC
{
    private static Map<String, Object> singletonMapping = new HashMap<String, Object>(); // Keep instance for singleton injection

    public void inject(Object objectToCheckForInjection) throws IllegalAccessException, SAXException, MultiplePreferredImplementationException, InstantiationException, IOException, ParserConfigurationException, ClassNotFoundException, ImplementationClassNotFoundException {
        Stack<Object> listObjectsToCheckForInjection = new Stack<Object>(); //Cascade injection
        listObjectsToCheckForInjection.push(objectToCheckForInjection);

        while(listObjectsToCheckForInjection.size() > 0)
        {
            Object objectToInject = listObjectsToCheckForInjection.pop();

            for(Field field  : objectToInject.getClass().getDeclaredFields())
            {
                if (field.isAnnotationPresent(InjectAnnotation.class))
                {
                    Object instance = getInstance(field);

                    field.setAccessible(true);
                    field.set(objectToInject, instance);

                    listObjectsToCheckForInjection.push(instance); // Cascade injection
                }
            }
        }
    }

    private Object getInstance(Field field) throws IllegalAccessException, InstantiationException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException, MultiplePreferredImplementationException, ImplementationClassNotFoundException {
        String interfaceName = field.getType().getName().replace("interface ", "");
        SPI spi = new SPI();
        Class classImplementation = null;
        Object instance = null;

        classImplementation = spi.getImplementationPreferred(interfaceName);

        if(classImplementation == null)
        {
            classImplementation = spi.getImplementationFromXML(interfaceName);
        }

        if(classImplementation != null)
        {
            for(Annotation annotation : classImplementation.getDeclaredAnnotations()) // Check annotations of the class to implement
            {
                if(annotation.annotationType().getName().equals(SingletonAnnotation.class.getName())) // Check if the class is annotated with @SingletonANnotation
                {
                    if(singletonMapping.containsKey(classImplementation.getName()))
                    {
                        instance = singletonMapping.get(classImplementation.getName());
                    }
                    else
                    {
                        instance = classImplementation.newInstance();
                        singletonMapping.put(classImplementation.getName(), instance);
                    }
                }
            }

            if(instance == null)
            {
                instance = classImplementation.newInstance();
            }
        }
        else
        {
            throw new ImplementationClassNotFoundException(interfaceName);
        }
        return instance;
    }
}
