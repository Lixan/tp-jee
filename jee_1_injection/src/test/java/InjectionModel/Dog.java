package InjectionModel;

import InjectAnnotations.InjectAnnotation;

public class Dog implements IAnimal
{
    @InjectAnnotation
    IFur fur;

    @Override
    public String toString()
    {
        return "I'm a dog";
    }

    public IFur getFur() {
        return fur;
    }
}
