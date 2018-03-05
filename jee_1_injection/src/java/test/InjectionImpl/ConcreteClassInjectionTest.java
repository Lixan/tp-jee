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
    public void initialize()
    {
        try
        {
            InjectionFramework injectionContainer = new InjectionFramework();
            injectionContainer.inject(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (MultiplePreferredImplementationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ImplementationClassNotFoundException e) {
            e.printStackTrace();
        }
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
