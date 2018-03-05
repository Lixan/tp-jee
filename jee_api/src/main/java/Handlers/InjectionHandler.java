package Handlers;

import Annotations.InterceptorAnnotation;
import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import Helpers.InstanceProvider;
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
        instance = InstanceProvider.getInstance(field);
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

    private InterceptorChain buildInterceptorChain(Object instance, Method method) throws NoSuchMethodException, IllegalAccessException, InstantiationException {
        Method instanceClassMethod = instance.getClass().getMethod(method.getName(), method.getParameterTypes());
        InterceptorChain chain = null;
        if (instanceClassMethod != null) {
            chain = new InterceptorChain();

            for (Annotation annotation : instanceClassMethod.getDeclaredAnnotations()) {
                for (InterceptorAnnotation interceptorAnnot : annotation.annotationType().getDeclaredAnnotationsByType(InterceptorAnnotation.class)) {
                    chain.addInterceptor(interceptorAnnot.interceptor());
                }
            }
        }
        return chain;
    }
}
