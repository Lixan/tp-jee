package test.LogModel;

public interface ITreatment
{
    int doSomething(int parameter1, int parameter2);

    void doSomethingInCaseOfError();

    void doSomethingInCaseOfWarning();

    void doSomethingInCaseOfDebug();
}
