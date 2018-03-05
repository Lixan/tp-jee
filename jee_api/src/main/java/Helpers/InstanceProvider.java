package Helpers;

import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import Injection.ClassInstanciator;
import Injection.ClassRetriever;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;

public class InstanceProvider {

    public static Object getInstance(Field field) throws IllegalAccessException, InstantiationException, ClassNotFoundException,
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
}
