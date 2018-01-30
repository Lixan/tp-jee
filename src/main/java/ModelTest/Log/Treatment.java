package ModelTest.Log;

public class Treatment implements ITreatment
{
    public int doSomething(int parameter1, int parameter2)
    {
        int result = parameter1+parameter2;

        System.out.println("Treatment done, result is : "+result);

        return result;
    }
}
