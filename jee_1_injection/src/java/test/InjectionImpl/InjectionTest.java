package test.InjectionImpl;

import Annotations.QualifierAnnotation;
import Handlers.InjectionHandler;
import Injection.InjectionFramework;
import Annotations.InjectAnnotation;
import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import test.InjectionModel.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

public class InjectionTest
{
    @InjectAnnotation
    @QualifierAnnotation(id="Dog")
    IAnimal dog1;

    @InjectAnnotation
    @QualifierAnnotation(id="Dog")
    IAnimal dog2;

    @InjectAnnotation
    @QualifierAnnotation(id="Cat")
    IAnimal cat1;

    IAnimal cat2;

    @Before
    public void initialize() throws Exception
    {
        InjectionFramework injectionContainer = new InjectionFramework();
        injectionContainer.inject(this);
    }

    @Test
    public void notNull_Test() {
        assertNotNull(dog1);
        assertNotNull(dog2);
        assertNotNull(cat1);
    }

    @Test
    public void noInjection_Test() {
        assertNull(cat2);
    }

    @Test
    public void proxyWrapper_Test() {
        assertTrue(Proxy.isProxyClass(dog1.getClass()));
        assertTrue(Proxy.isProxyClass(dog2.getClass()));
        assertTrue(Proxy.isProxyClass(cat1.getClass()));
    }

    @Test
    public void instanceType_Test()
    {
        assertTrue(((InjectionHandler)Proxy.getInvocationHandler(dog1)).getInstance() instanceof Dog);
        assertTrue(((InjectionHandler)Proxy.getInvocationHandler(dog2)).getInstance() instanceof Dog);
        assertTrue(((InjectionHandler)Proxy.getInvocationHandler(cat1)).getInstance() instanceof Cat);
    }

    @Test
    public void notSingleton_Test()
    {
        assertNotEquals(((InjectionHandler)Proxy.getInvocationHandler(dog1)).getInstance(),
                ((InjectionHandler)Proxy.getInvocationHandler(dog2)).getInstance());
    }
}
