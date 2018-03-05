package Injection;

import Exceptions.MultiplePreferredImplementationException;
import Handlers.InjectionHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class InjectorProxy {

    public Object getInstanceProxy(Field field) throws ClassNotFoundException, SAXException, ParserConfigurationException, MultiplePreferredImplementationException, IOException {
        return Proxy.newProxyInstance(field.getType().getClassLoader(), new Class<?>[] {field.getType()}, new InjectionHandler(field));
    }
}
