package test.TransactionalModel;


import InjectionModel.InjectAnnotation;

public class ComplexTreatmentProxy implements IComplexTreatment
{
    @InjectAnnotation
    private ComplexTreatment complexTreatment;

    private ComplexTreatment complexTreatmentSaved;

    private void beforeTreatment()
    {
        //TODO : open transaction ?
        //TODO : sauvegarde du complex treatment pour faire un rollback en cas de pb ?
        System.out.println("Before complex treatment");

        incrementValue();
    }

    private void afterTreatment()
    {
        //TODO : close transaction ?
        incrementValue();
        System.out.println("After complex treatment");
    }

    private void saveState()
    {
        complexTreatmentSaved = new ComplexTreatment();
        complexTreatmentSaved.setValue(getValue());
    }

    private void commit()
    {
        //TODO mettre à jour le complex treatment
        incrementValue();
    }

    private void rollback()
    {
        //TODO faire revenir le complex treatment à son état initial
        complexTreatment = complexTreatmentSaved;
    }

    @TransactionalAnnotation
    public void doSomething(boolean throwException) throws ComplexTreatmentException
    {
        beforeTreatment();

        try
        {
            complexTreatment.doSomething(throwException);
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

    public int getValue()
    {
        return complexTreatment.getValue();
    }

    public void incrementValue()
    {
        complexTreatment.incrementValue();
    }

    public void setValue(int value)
    {
        complexTreatment.setValue(value);
    }
}
