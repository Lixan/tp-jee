package Injection;

import Annotations.InjectAnnotation;
import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import Handlers.InjectionHandler;
import Helpers.InstanceProvider;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Stack;

public class InjectionFramework
{

    public void inject(Object objectToCheckForInjection) throws IllegalAccessException, SAXException, MultiplePreferredImplementationException, InstantiationException, IOException, ParserConfigurationException, ClassNotFoundException, ImplementationClassNotFoundException
    {
        Stack<Object> listObjectsToCheckForInjection = new Stack<Object>(); //Cascade injection
        listObjectsToCheckForInjection.push(objectToCheckForInjection);
        InjectorProxy proxy = new InjectorProxy();
        Object instance;

        while(listObjectsToCheckForInjection.size() > 0)
        {
            Object objectToInject = listObjectsToCheckForInjection.pop();
            if(Proxy.isProxyClass(objectToInject.getClass())) {
                objectToInject = ((InjectionHandler)Proxy.getInvocationHandler(objectToInject)).getInstance();
            }

            for(Field field  : objectToInject.getClass().getDeclaredFields())
            {
                if (field.isAnnotationPresent(InjectAnnotation.class))
                {
                    if(field.getType().isInterface())
                        instance = proxy.getInstanceProxy(field);
                    else
                        instance = InstanceProvider.getInstance(field);
                    field.setAccessible(true);
                    field.set(objectToInject, instance);

                    listObjectsToCheckForInjection.push(instance); // Cascade injection
                }
            }
        }
    }
}
