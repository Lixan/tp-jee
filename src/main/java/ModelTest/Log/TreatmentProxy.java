package ModelTest.Log;

public class TreatmentProxy implements ITreatment
{
    private Treatment treatment;

    private void beforeTreatment(int parameter1, int parameter2)
    {
        System.out.println("[INFO] Log before method parameter1="+parameter1+", parameter2="+parameter2);
    }

    private void afterTreatment(int parameter1, int parameter2)
    {
        System.out.println("[INFO] Log after method parameter1="+parameter1+", parameter2="+parameter2);
    }

    @LogAnnotation
    public int doSomething(int parameter1, int parameter2)
    {
        int result;

        beforeTreatment(parameter1, parameter2);
        result = treatment.doSomething(parameter1, parameter2);
        afterTreatment(parameter1, parameter2);

        return result;
    }
}
