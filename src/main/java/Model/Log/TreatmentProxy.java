package Model.Log;

public class TreatmentProxy implements ITreatment
{
    private Treatment treatment;

    private void beforeTreatment()
    {
        System.out.println("Before treatment");
    }

    private void afterTreatment()
    {
        System.out.println("Before treatment");
    }

    public void doSomething()
    {
        beforeTreatment();
        treatment.doSomething();
        afterTreatment();
    }
}
