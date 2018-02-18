package InjectionModel;

import Annotations.InjectAnnotation;
import Annotations.QualifierAnnotation;

public class Cat implements IAnimal
{
    @InjectAnnotation
    @QualifierAnnotation(id="Silky")
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
