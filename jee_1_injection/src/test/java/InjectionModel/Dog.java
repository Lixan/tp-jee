package InjectionModel;

import Annotations.InjectAnnotation;
import Annotations.QualifierAnnotation;

public class Dog implements IAnimal
{
    @InjectAnnotation
    @QualifierAnnotation(id="Silky")
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
