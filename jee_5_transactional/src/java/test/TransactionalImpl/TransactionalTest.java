package test.TransactionalImpl;

import Handlers.InjectionHandler;
import Injection.InjectionFramework;
import Annotations.InjectAnnotation;
import test.TransactionalModel.ComplexTreatment;
import test.TransactionalModel.IComplexTreatment;
import org.junit.Before;
import org.junit.Test;
import Transaction.Transaction;


import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TransactionalTest
{
    @InjectAnnotation
    IComplexTreatment complexTreatment;

    @Before
    public void initialize() throws Exception {
        InjectionFramework injectionContainer = new InjectionFramework();
        injectionContainer.inject(this);
    }

    @Test
    public void notNull_Test() {
        assertNotNull(complexTreatment);
    }

    @Test
    public void proxyWrapper_Test() {
        assertTrue(Proxy.isProxyClass(complexTreatment.getClass()));
    }

    @Test
    public void instanceType_Test() {
        assertTrue(((InjectionHandler)Proxy.getInvocationHandler(complexTreatment)).getInstance() instanceof ComplexTreatment);
    }

    @Test
    public void commit_Test() throws Exception
    {
        Transaction.reinitialize();
        complexTreatment.doSomething(false);
        assertEquals(Transaction.getCommits(), 1);
        assertEquals(Transaction.getRollbacks(), 0);
    }

    @Test
    public void rollback_Test() throws Exception
    {
        Transaction.reinitialize();
        complexTreatment.doSomething(true);
        assertEquals(Transaction.getCommits(), 0);
        assertEquals(Transaction.getRollbacks(), 1);
    }

    @Test
    public void require_Test() throws Exception
    {
        Transaction.reinitialize();
        complexTreatment.doSomething(false);
        complexTreatment.doSomething(false);
        assertEquals(Transaction.getCommits(), 2);
        assertEquals(Transaction.getRollbacks(), 0);
        assertEquals(Transaction.getBegin(), 1);
    }

    @Test
    public void requireNew_Test() throws Exception
    {
        Transaction.reinitialize();
        complexTreatment.doSomethingElse(true);
        complexTreatment.doSomethingElse(false);
        assertEquals(Transaction.getCommits(), 1);
        assertEquals(Transaction.getRollbacks(), 1);
        assertEquals(Transaction.getBegin(), 2);

    }
}
