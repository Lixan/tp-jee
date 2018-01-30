package Model.Transactional;

public class ComplexTreatment implements IComplexTreatment
{
    public void doSomething() throws ComplexTreatmentException
    {
        System.out.println("Complex treatment...");

        //TODO : cas aléatoire qui renvoie une fois sur deux une exception catch dans le proxy
        throw new ComplexTreatmentException();
    }
}
