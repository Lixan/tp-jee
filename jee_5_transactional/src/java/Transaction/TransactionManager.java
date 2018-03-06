package Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Stack;

public class TransactionManager {
    private static Stack<ITransaction> transactions = new Stack<ITransaction>();

    public static ITransaction getTransaction(TransactionType type, Object object) {
        ITransaction transaction = null;
        if(type == TransactionType.REQUIRED) {
            if(transactions.size() > 0) {
                transaction = transactions.peek();
            }
            else {
                transaction = createTransaction(object);
            }
        }
        else {
            if(type == TransactionType.REQUIRED_NEW) {
                transaction = createTransaction(object);
            }
        }
        return transaction;
    }

    private static ITransaction createTransaction(Object object) {
        ITransaction transaction = new Transaction(object);
        transactions.push(transaction);
        return transaction;
    }
}
