package Transaction;

public class Transaction implements ITransaction {

    private static int begin = 0;
    private static int commit = 0;
    private static int rollback = 0;

    public Transaction(Object object) {
        ++begin;
    }

    public static int getBegin() {
        return begin;
    }

    public static int getCommits() {
        return commit;
    }

    public static void reinitialize() {
        commit = 0;
        rollback = 0;
        begin = 0;
    }

    public static int getRollbacks() {
        return rollback;
    }

    public void commit() {
        ++commit;
    }

    public void rollback() {
        ++rollback;
    }
}
