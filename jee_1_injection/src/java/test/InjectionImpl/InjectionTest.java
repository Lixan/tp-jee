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
    IAnimal myAnimal_2;

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
    public void notNullDog_Test() {
        assertNotNull(myAnimal_2);
    }

    @Test
    public void proxyImplementation() {
        assertTrue(Proxy.isProxyClass(myAnimal_2.getClass()));
    }

    @Test
    public void implementationIsDog_Test()
    {
        assertEquals(((InjectionHandler)Proxy.getInvocationHandler(myAnimal_2)).getInstance(), Dog.class);
    }

    /*@Test
    public void implementationIsNotCat_Test()
    {
        assertNotEquals(myAnimal_2.getClass(), Cat.class);
    }*/

    /*@Test
    public void allAnimalsHaveSameInjectionClass_Test()
    {
        assertEquals(dog.getClass(), myAnimal_2.getClass());
    }*/

    @Test
    public void animalsReferencesAreDifferents_Test()
    {
        assertNotEquals(dog, myAnimal_2);
    }

    /*@Test
    public void cascade_implementationIsSilky_Test()
    {
        assertEquals(myAnimal_2.getFur().getClass(), Silky.class);
    }*/

    @Test
    public void cascade_implementationIsNotRough_Test()
    {
        assertNotEquals(myAnimal_2.getFur(), Rough.class);
    }
}
