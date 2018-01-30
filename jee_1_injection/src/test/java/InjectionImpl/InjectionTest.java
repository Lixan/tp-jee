package InjectionImpl;

import Api.InjectAnnotation;
import Api.IOC;
import InjectAnnotations.InjectAnnotation;
import InjectionModel.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class InjectionTest
{
    @InjectAnnotation
    IAnimal myAnimal;

    @InjectAnnotation
    IAnimal myAnimal_2;

    @Before
    public void initialize()
    {
        IOC injectionContainer = new IOC();
        injectionContainer.inject(this);
    }

    @Test
    public void implementationIsDog_Test()
    {
        assertEquals(myAnimal.getClass(), Dog.class);
    }

    @Test
    public void implementationIsNotCat_Test()
    {
        assertNotEquals(myAnimal.getClass(), Cat.class);
    }

    @Test
    public void allAnimalsHaveSameInjectionClass_Test()
    {
        assertEquals(myAnimal.getClass(), myAnimal_2.getClass());
    }

    @Test
    public void animalsReferencesAreDifferents_Test()
    {
        assertNotEquals(myAnimal, myAnimal_2);
    }

    @Test
    public void cascade_implementationIsSilky_Test()
    {
        assertEquals(myAnimal.getFur(), Silky.class);
    }

    @Test
    public void cascade_implementationIsNotRough_Test()
    {
        assertNotEquals(myAnimal.getFur(), Rough.class);
    }
}
