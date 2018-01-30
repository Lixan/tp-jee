package Model.Injection;

import Model.Injection.IAnimal;

public class Dog implements IAnimal
{
    @Override
    public String toString()
    {
        return "I'm a dog";
    }
}
