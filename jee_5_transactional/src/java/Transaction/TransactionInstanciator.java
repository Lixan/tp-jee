package Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TransactionInstanciator {

    private static Stack<ITransaction> transactions = new Stack<ITransaction>();

    public static ITransaction getTransaction(TransactionType type) {
        ITransaction transaction = null;
        if(type == TransactionType.REQUIRED) {
            if(transactions.size() > 0) {
                transaction = transactions.peek();
            }
            else {
                transaction = createTransaction();
            }
        }
        else {
            if(type == TransactionType.REQUIRED_NEW) {
                transaction = createTransaction();
            }
        }
        return transaction;
    }

    private static ITransaction createTransaction() {
        ITransaction transaction = new Transaction();
        transactions.push(transaction);
        return transaction;
    }


}
