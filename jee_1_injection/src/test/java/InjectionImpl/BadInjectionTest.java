package InjectionImpl;

import Annotations.InjectAnnotation;
import Annotations.QualifierAnnotation;
import Api.InjectionFramework;
import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import InjectionModel.Dog;
import InjectionModel.IAnimal;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class BadInjectionTest {

    @InjectAnnotation
    IAnimal myAnimal;

    @Test(expected = ImplementationClassNotFoundException.class)
    public void implementationIsDog_Test() throws IllegalAccessException,
            MultiplePreferredImplementationException, InstantiationException,
            ClassNotFoundException, ParserConfigurationException, SAXException,
            ImplementationClassNotFoundException, IOException {
        InjectionFramework injectionContainer = new InjectionFramework();
        injectionContainer.inject(this);
    }

}
