package Handlers;

import Annotations.InterceptorAnnotation;
import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import Injection.ClassInstanciator;
import Injection.ClassRetriever;
import Interceptors.InterceptorChain;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InjectionHandler implements InvocationHandler {

    private Object instance;

    public InjectionHandler(Field field) throws IllegalAccessException, MultiplePreferredImplementationException, InstantiationException, IOException, SAXException, ParserConfigurationException, ImplementationClassNotFoundException, ClassNotFoundException {
        instance = this.getInstanceFromClassRetriever(field);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if(instance != null) {
            InterceptorChain chain = this.buildInterceptorChain(instance, method);
            result = chain.proceed(instance, method, args);
        }
        return result;
    }

    public Object getInstance() {
        return instance;
    }

    private Object getInstanceFromClassRetriever(Field field) throws IllegalAccessException, InstantiationException, ClassNotFoundException,
            ParserConfigurationException, SAXException, IOException, MultiplePreferredImplementationException,
            ImplementationClassNotFoundException {
        Class<?> classToInstanciate = null;
        Object instance = null;

        ClassRetriever classRetriever = new ClassRetriever();
        classToInstanciate = classRetriever.getClassToImplement(field);

        if(classToInstanciate != null)
        {
            instance = ClassInstanciator.instanciateClass(classToInstanciate);
        }
        else
        {
            throw new ImplementationClassNotFoundException(field.getName());
        }

        return instance;
    }

    private InterceptorChain buildInterceptorChain(Object instance, Method method) throws NoSuchMethodException {
        Method instanceClassMethod = instance.getClass().getMethod(method.getName(), method.getParameterTypes());
        InterceptorChain chain = null;
        if (instanceClassMethod != null) {
            chain = new InterceptorChain();

            for (Annotation annotation : instanceClassMethod.getDeclaredAnnotations()) {
                for (InterceptorAnnotation interceptor : annotation.annotationType().getDeclaredAnnotationsByType(InterceptorAnnotation.class)) {
                    chain.addInterceptor(interceptor.interceptor());
                }
            }
        }
        return chain;
    }
}
