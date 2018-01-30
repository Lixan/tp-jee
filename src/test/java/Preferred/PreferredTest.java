package Preferred;

import Api.InjectAnnotation;
import ModelTest.Preferred.IDatabase;
import ModelTest.Preferred.RealDatabase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PreferredTest
{
    @InjectAnnotation
    @Preferred
    IDatabase database;

    @InjectAnnotation
    @Preferred
    IDatabase database_2;

    @Before
    public void initialize()
    {
        //IOC injectionContainer = new IOC();

        // TODO : it should crash if preferred is not specified ? or if more than 2 preferred ? --> Exception tested
        try
        {
            //injectionContainer.inject(this);
        }
        catch(PreferredAnnotationException)
        {
            fail("No preferred / Multiple preferred");
        }
    }

    @Test
    public void preferredClassImplemented_Test()
    {
        assertEquals(database.getClass(), RealDatabase.class);
    }

    @Test
    public void implementedClassHaveSameType_Test()
    {
        assertEquals(database.getClass(), database_2.getClass());
    }

    @Test
    public void implementedClassHaveDifferentReference_Test()
    {
        assertNotEquals(database, database_2);
    }
}
