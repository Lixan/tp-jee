package Model.Transactional;


public class ComplexTreatmentProxy implements IComplexTreatment
{
    private ComplexTreatment complexTreatment;

    private ComplexTreatment treatment;

    private void beforeTreatment()
    {
        //TODO : open transaction ?
        //TODO : sauvegarde du complex treatment pour faire un rollback en cas de pb ?
        System.out.println("Before complex treatment");
    }

    private void afterTreatment()
    {
        //TODO : close transaction ?
        System.out.println("Before complex treatment");
    }

    private void commit()
    {
        //TODO mettre à jour le complex treatment
    }

    private void rollback()
    {
        //TODO faire revenir le complex treatment à son état initial
    }

    @TransactionalAnnotation
    public void doSomething() throws ComplexTreatmentException
    {
        beforeTreatment();

        try
        {
            treatment.doSomething();
            commit();
        }
        catch(ComplexTreatmentException e)
        {
            rollback();
            throw e;
        }
        finally
        {
            afterTreatment();
        }


    }
}
