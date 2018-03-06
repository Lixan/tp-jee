package test.LogModel;

import Annotations.LogAnnotation;
import Annotations.PreferredAnnotation;
import Logger.Level;
import Logger.LoggerFile;

@PreferredAnnotation
public class Treatment implements ITreatment
{
    @LogAnnotation
    public int doSomething(int parameter1, int parameter2)
    {
        int result = parameter1+parameter2;
        return result;
    }

    @LogAnnotation(level = Level.ERROR)
    public void doSomethingInCaseOfError() {
        System.err.println("An error occured");
    }

    @LogAnnotation(level = Level.WARNING, logger = LoggerFile.class)
    public void doSomethingInCaseOfWarning() {
        int a = 0;
    }

    @LogAnnotation(level = Level.DEBUG, logger = LoggerFile.class)
    public  void doSomethingInCaseOfDebug() {
        int b = 2;
    }
}
