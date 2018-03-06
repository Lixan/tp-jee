package test.InjectionImpl;

import Annotations.InjectAnnotation;
import Annotations.QualifierAnnotation;
import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import Handlers.InjectionHandler;
import Injection.InjectionFramework;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import test.InjectionModel.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

public class CascadeInjectionTest {

    @InjectAnnotation
    @QualifierAnnotation(id="Dog")
    IAnimal dog;

    @Before
    public void initialize() throws Exception
    {
        InjectionFramework injectionContainer = new InjectionFramework();
        injectionContainer.inject(this);
    }

    @Test
    public void cascadeInjection_Test()
    {
        assertTrue(((InjectionHandler) Proxy.getInvocationHandler(dog.getFur())).getInstance() instanceof Silky);
    }
}
