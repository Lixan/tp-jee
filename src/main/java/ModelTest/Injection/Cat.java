package ModelTest.Injection;

import Api.InjectAnnotation;

public class Cat implements IAnimal
{
    @InjectAnnotation
    IFur fur;

    @Override
    public String toString()
    {
        return "I'm a cat";
    }

    @Override
    public IFur getFur() {
        return fur;
    }
}
