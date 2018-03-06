package test.TransactionalImpl;

import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import Handlers.InjectionHandler;
import Injection.InjectionFramework;
import Annotations.InjectAnnotation;
import test.TransactionalModel.ComplexTreatment;
import test.TransactionalModel.ComplexTreatmentException;
import test.TransactionalModel.IComplexTreatment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TransactionalTest
{
    @InjectAnnotation
    IComplexTreatment complexTreatment;

    @Before
    public void initialize() throws Exception {
        InjectionFramework injectionContainer = new InjectionFramework();
        injectionContainer.inject(this);
    }

    @Test
    public void notNull_Test() {
        assertNotNull(complexTreatment);
    }

    @Test
    public void proxyWrapper_Test() {
        assertTrue(Proxy.isProxyClass(complexTreatment.getClass()));
    }

    @Test
    public void instanceType_Test() {
        assertTrue(((InjectionHandler)Proxy.getInvocationHandler(complexTreatment)).getInstance() instanceof ComplexTreatment);
    }

    @Test
    public void injectionComplexTreatmentInitialization_Test()
    {
        // test state before call
        assertEquals(0, complexTreatment.getValue());
    }

    @Test
    public void proxySuccessfulTreatment_Test()
    {
        // Test that beforeTreatment, doSomething, commit and afterTreatment changed the state
        try
        {
            complexTreatment.doSomething(false);

            //constructor => 0
            //beforeTreatment => 1
            //doSomething => 2
            //commit => 3
            //afterTreatment => 4
            assertEquals(4, complexTreatment.getValue());
        }
        catch (ComplexTreatmentException e)
        {
            Assert.fail("Exception should not be thrown");
        }
    }

    @Test
    public void proxyFailTreatment_Test()
    {
        // Test that rollback and afterTreatment changed the state
        try
        {
            complexTreatment.doSomething(true);
            Assert.fail("Exception should be thrown");
        }
        catch (ComplexTreatmentException e)
        {
            //constructor => 0
            //beforeTreatment => 1
            //doSomething => 2
            //rollback => 0
            //afterTreatment => 1
            assertEquals(1, complexTreatment.getValue());
        }

    }


}
