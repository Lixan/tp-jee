package test.TransactionalModel;

import Annotations.PreferredAnnotation;
import Annotations.TransactionalAnnotation;
import Transaction.TransactionType;

@PreferredAnnotation
public class ComplexTreatment implements IComplexTreatment
{
    private int value;

    public ComplexTreatment()
    {
        value = 0;
    }

    @TransactionalAnnotation(type = TransactionType.REQUIRED)
    public void doSomething(boolean throwException) throws ComplexTreatmentException
    {
        System.out.println("Complex treatment...");

        incrementValue();

        if(throwException)
        {
            throw new ComplexTreatmentException();
        }
    }

    public int getValue()
    {
        return value;
    }

    public void incrementValue()
    {
        ++value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
}
