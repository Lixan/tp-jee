package test.SingletonImpl;

import Handlers.InjectionHandler;
import Injection.InjectionFramework;
import Annotations.InjectAnnotation;
import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import test.SingletonModel.IService;
import test.SingletonModel.MyService;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Proxy;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class SingletonTest
{
    @InjectAnnotation
    IService myService1;

    @InjectAnnotation
    IService myService2;

    @Before
    public void initialize()
    {
        try {
            InjectionFramework injectionContainer = new InjectionFramework();
            injectionContainer.inject(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (MultiplePreferredImplementationException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ImplementationClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notNull_Test() {
        assertNotNull(myService1);
        assertNotNull(myService2);
    }

    @Test
    public void proxyWrapper_Test() {
        assertTrue(Proxy.isProxyClass(myService1.getClass()));
        assertTrue(Proxy.isProxyClass(myService2.getClass()));
    }

    @Test
    public void instanceType_Test() {
        assertTrue(((InjectionHandler)Proxy.getInvocationHandler(myService1)).getInstance() instanceof MyService);
        assertTrue(((InjectionHandler)Proxy.getInvocationHandler(myService2)).getInstance() instanceof MyService);
    }

    @Test
    public void singleton_Test()
    {
        assertEquals(((InjectionHandler)Proxy.getInvocationHandler(myService1)).getInstance(),
                ((InjectionHandler)Proxy.getInvocationHandler(myService2)).getInstance());
    }
}