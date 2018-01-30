package Injection;

import java.lang.reflect.Field;

public class IOC
{
    public void inject(Object object)
    {
        //TODO : Should check singleton ? preferred ?
        //TODO : check class and methods annotation
        /*
        for(Field field  : object.getClass().getDeclaredFields())
        {
            if (field.isAnnotationPresent(InjectAnnotation.class))
            {
                String interfaceName = field.getType().getName().replace("interface ", "");
                SPI spi = new SPI();
                spi.getImplementation(interfaceName);

            }
        }
        */
    }
}
