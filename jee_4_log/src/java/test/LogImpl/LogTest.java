package test.LogImpl;

import Handlers.InjectionHandler;
import Injection.InjectionFramework;
import Annotations.InjectAnnotation;
import Exceptions.ImplementationClassNotFoundException;
import Exceptions.MultiplePreferredImplementationException;
import test.LogModel.ITreatment;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import test.LogModel.Treatment;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LogTest {

    @InjectAnnotation
    ITreatment treatment;

    private int parameter1 = 22;
    private int parameter2 = 66;

    @Before
    public void initialize() throws Exception {
        InjectionFramework injectionContainer = new InjectionFramework();
        injectionContainer.inject(this);
    }

    @Test
    public void notNull_Test() {
        assertNotNull(treatment);
    }

    @Test
    public void proxyWrapper_Test() {
        assertTrue(Proxy.isProxyClass(treatment.getClass()));
    }

    @Test
    public void instanceType_Test() {
        assertTrue(((InjectionHandler)Proxy.getInvocationHandler(treatment)).getInstance() instanceof Treatment);
    }

    @Test
    public void baseLog_Test() throws IOException
    {
       treatment.doSomething(parameter1, parameter2);
    }

    @Test
    public void errorLog_Test() throws IOException
    {
        treatment.doSomethingInCaseOfError();
    }

    @Test
    public void warningLog_Test() throws IOException {
        cleanLogFile();

        treatment.doSomethingInCaseOfWarning();
        String filecontent = getFileContent("log.txt");
        boolean messageBeforeMethod = filecontent.contains("[WARNING] doSomethingInCaseOfWarning: in progress...");
        boolean messageAfterMethod = filecontent.contains("[WARNING] doSomethingInCaseOfWarning: finished");
        assertTrue(messageBeforeMethod);
        assertTrue(messageAfterMethod);
    }

    @Test
    public void debugLog_Test() throws IOException {
        cleanLogFile();

        treatment.doSomethingInCaseOfDebug();
        String filecontent = getFileContent("log.txt");
        boolean messageBeforeMethod = filecontent.contains("[DEBUG] doSomethingInCaseOfDebug: in progress...");
        boolean messageAfterMethod = filecontent.contains("[DEBUG] doSomethingInCaseOfDebug: finished");
        assertTrue(messageBeforeMethod);
        assertTrue(messageAfterMethod);
    }

    private void cleanLogFile() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("log.txt"));
        bufferedWriter.flush();
        bufferedWriter.close();
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
