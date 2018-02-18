package SingletonImpl;

import Api.InjectionFramework;
import Annotations.InjectAnnotation;
import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import SingletonModel.IService;
import SingletonModel.MyService;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    public void implementationHasBeenImplemented_Test()
    {
        assertEquals(myService1.getClass(), MyService.class);
    }

    @Test
    public void serviceHaveSameInjectionClass_Test()
    {
        assertEquals(myService1.getClass(), myService2.getClass());
    }

    @Test
    public void servicesReferencesAreTheSame_Test()
    {
        assertEquals(myService1, myService2);
    }
}