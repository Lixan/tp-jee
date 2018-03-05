package test.PreferredImpl;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PreferredTest
{
    @InjectAnnotation
    IDatabase database;

    @InjectAnnotation
    IDatabase database_2;

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
