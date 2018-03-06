package test.InjectionImpl;

import Annotations.InjectAnnotation;
import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import Handlers.InjectionHandler;
import Injection.InjectionFramework;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import test.InjectionModel.Dog;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

public class ConcreteClassInjectionTest {

    @InjectAnnotation
    Dog dog;

    @Before
    public void initialize() throws Exception
    {
        InjectionFramework injectionContainer = new InjectionFramework();
        injectionContainer.inject(this);
    }

    @Test
    public void notNull_Test() {
        assertNotNull(dog);
    }

    @Test
    public void instanceType_Test() {
        assertTrue(dog instanceof Dog);
    }

    @Test
    public void noProxyWrapper_Test()
    {
        assertFalse(Proxy.isProxyClass(dog.getClass()));
    }
}
