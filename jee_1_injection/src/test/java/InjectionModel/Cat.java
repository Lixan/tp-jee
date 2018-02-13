package InjectionModel;

import Annotations.InjectAnnotation;

public class Cat implements IAnimal
{
    @InjectAnnotation
    IFur fur;

    @Override
    public String toString()
    {
        return "I'm a cat";
    }

    public IFur getFur() {
        return fur;
    }
}
