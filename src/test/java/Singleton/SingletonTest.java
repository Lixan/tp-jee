package Singleton;

import Api.InjectAnnotation;
import ModelTest.Singleton.IService;
import ModelTest.Singleton.MyService;
import org.junit.Before;
import org.junit.Test;

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
        //IOC injectionContainer = new IOC();
        //injectionContainer.inject(this);
    }

    @Test
    public void implementationIsDog_Test()
    {
        assertEquals(myService1.getClass(), MyService.class);
    }

    @Test
    public void allAnimalsHaveSameInjectionClass_Test()
    {
        assertEquals(myService1.getClass(), myService2.getClass());
    }

    @Test
    public void animalsReferencesAreSame_Test()
    {
        assertEquals(myService1, myService2);
    }
}