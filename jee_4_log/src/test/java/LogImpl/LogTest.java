package LogImpl;

import Api.IOC;
import Api.InjectAnnotation;
import Annotations.InjectAnnotation;
import LogModel.ITreatment;
import LogModel.TreatmentProxy;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LogTest {

    @InjectAnnotation
    ITreatment treatment;

    private int parameter1 = 22;
    private int parameter2 = 66;
    private int result = -1;

    @Before
    public void initialize()
    {
        IOC injectionContainer = new IOC();
        injectionContainer.inject(this);
    }

    @Test
    public void theProxyIsReturned_Test()
    {
        assertEquals(treatment, TreatmentProxy.class);
    }

    @Test
    public void logBeforeMethod_Test() throws IOException
    {
       treatment.doSomething(parameter1, parameter2);

       String filecontent = getFileContent("C:\\Users\\clpulby1\\Desktop\\log.txt");

       boolean fileContainLogBefore = filecontent.contains("[INFO] LogModel before method parameter1="+parameter1+", parameter2="+parameter2);

       assertEquals(true, fileContainLogBefore);
    }

    @Test
    public void methodSuccessfullyCalled_Test() throws IOException
    {
        result = treatment.doSomething(parameter1, parameter2);

        String filecontent = getFileContent("C:\\Users\\clpulby1\\Desktop\\log.txt");

        boolean fileContainTreamentResult = filecontent.contains("Treatment done, result is : "+result);

        assertEquals(true, fileContainTreamentResult);
    }

    @Test
    public void logAfterMethod_Test() throws IOException
    {
        treatment.doSomething(parameter1, parameter2);

        String filecontent = getFileContent("C:\\Users\\clpulby1\\Desktop\\log.txt");

        boolean fileContainLogAfter = filecontent.contains("[INFO] LogModel after method parameter1="+parameter1+", parameter2="+parameter2);

        assertEquals(true, fileContainLogAfter);
    }

    private String getFileContent(String filepath) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String fileContent = "";

        try
        {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null)
            {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
                line = br.readLine();
            }
            fileContent = sb.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return fileContent;
        }
    }
}
