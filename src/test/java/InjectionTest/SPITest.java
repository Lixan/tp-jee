package InjectionTest;

import Api.SPI;
import ModelTest.Injection.Cat;
import org.junit.Test;

public class SPITest
{
    @Test
    public void AnimalTest()
    {
        SPI spi = new SPI();
        spi.load();
        assertEquals(spi.getImplementation("IAnimal"), Cat.class);
    }
}
