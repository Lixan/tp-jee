package test.LogModel;

import Annotation.LogAnnotation;

public class Treatment implements ITreatment
{
    @LogAnnotation
    public int doSomething(int parameter1, int parameter2)
    {
        int result = parameter1+parameter2;

        System.out.println("Treatment done, result is : "+result);

        return result;
    }
}
