package ModelTest.Injection;

import Api.InjectAnnotation;

public class Dog implements IAnimal
{
    @InjectAnnotation
    IFur fur;

    @Override
    public String toString()
    {
        return "I'm a dog";
    }

    @Override
    public IFur getFur() {
        return fur;
    }
}
