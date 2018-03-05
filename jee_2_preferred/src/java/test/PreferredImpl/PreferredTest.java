package test.PreferredImpl;

import Handlers.InjectionHandler;
import Injection.InjectionFramework;
import Annotations.InjectAnnotation;
import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import test.PreferredModel.IDatabase;
import test.PreferredModel.RealDatabase;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

public class PreferredTest
{
    @InjectAnnotation
    IDatabase database;

    @InjectAnnotation
    IDatabase database_2;

    @Before
    public void initialize() {
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
        assertNotNull(database);
        assertNotNull(database_2);
    }

    @Test
    public void proxyWrapper_Test() {
        assertTrue(Proxy.isProxyClass(database.getClass()));
        assertTrue(Proxy.isProxyClass(database_2.getClass()));
    }

    @Test
    public void instanceTypePreferred_Test() {
        assertTrue(((InjectionHandler)Proxy.getInvocationHandler(database)).getInstance() instanceof RealDatabase);
        assertTrue(((InjectionHandler)Proxy.getInvocationHandler(database_2)).getInstance() instanceof RealDatabase);
    }
}
