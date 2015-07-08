package day12.race_conditions;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BadAtm implements Atm {

    private Lock accountLock;


    public BadAtm() {
        accountLock = new ReentrantLock();
    }


    @Override
    public void checkBalance(long accountId) {
        System.out.println(accountId + " going to withdraw some money.");
    }

    @Override
    public void withdrawMoney(long acoountId, int amount) {
        accountLock.lock();

        try {
            if(allowWithdrawal(acoountId, amount)) {
                updateBalance(acoountId, amount, TransactionType.WITHDRAWAL);
            }
        } finally {
            accountLock.unlock();
        }
    }

    private boolean allowWithdrawal(long accountId, int amount) {
        return true;
    }

    private void updateBalance(long accountId, int amount, TransactionType type) {
        System.out.println("Successful " + type + " account: " + accountId + " amount: " + amount);
    }

    enum TransactionType {
        DEPOSIT, WITHDRAWAL;
    }
}
