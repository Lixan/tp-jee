package PreferredImpl;

import Api.IOC;
import Api.InjectAnnotation;
import InjectAnnotations.InjectAnnotation;
import PreferredAnnotations.PreferredAnnotation;
import PreferredModel.IDatabase;
import PreferredModel.RealDatabase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PreferredTest
{
    @InjectAnnotation
    @PreferredAnnotation
    IDatabase database;

    @InjectAnnotation
    @PreferredAnnotation
    IDatabase database_2;

    @Before
    public void initialize()
    {
        IOC injectionContainer = new IOC();
        // TODO : it should return PreferredAnnotationException if preferred is not specified OR if more than 2 preferred
        injectionContainer.inject(this);
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
