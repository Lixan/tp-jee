package test.TransactionalModel;

public interface IComplexTreatment
{
    void doSomething(boolean throwException) throws ComplexTreatmentException;
    void doSomethingElse(boolean throwException) throws ComplexTreatmentException;

    int getValue();
    void incrementValue();
    void setValue(int value);
}
