package Transactional;

import Api.InjectAnnotation;
import ModelTest.Transactional.ComplexTreatment;
import ModelTest.Transactional.ComplexTreatmentException;
import ModelTest.Transactional.ComplexTreatmentProxy;
import ModelTest.Transactional.IComplexTreatment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionalTest
{
    @InjectAnnotation
    IComplexTreatment complexTreatment;

    @Before
    public void initialize()
    {
        //IOC injectionContainer = new IOC();
        //injectionContainer.inject(this);
    }

    @Test
    public void proxyReturned_Test()
    {
        assertEquals(complexTreatment, ComplexTreatmentProxy.class);
    }

    @Test
    public void injectionComplexTreatment_Test()
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
            //beforeTreatment += 1
            //doSomething += 1
            //commit += 1
            //afterTreatment += 1
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
            //beforeTreatment += 1
            //doSomething += 1
            //rollback => 0
            //afterTreatment += 1
            assertEquals(1, complexTreatment.getValue());
        }

    }


}
