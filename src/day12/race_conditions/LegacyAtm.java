package day12.race_conditions;

public class LegacyAtm implements Atm {

    private Integer currentBalance;

    public LegacyAtm() {
        currentBalance = 1000;
    }

    @Override
    public void checkBalance(long accountid) {
        System.out.println(accountid + " is going to withdraw some money! Current balance is " + currentBalance);
    }

    @Override
    public void withdrawMoney(long acoountId, int amount) {
        synchronized (currentBalance) {
            if(allowWithrawMoney(acoountId, amount)) {
                updateBalance(acoountId, amount, TransactionType.WITHDRAWAL);
            } else {
                System.out.println("Operation failed, not enought money! Current balance is " + currentBalance + ", "
                        + acoountId + " want to withdraw " + amount);
            }
        }
    }

    private boolean allowWithrawMoney(long accountId, int amount) {
        boolean state = false;

        if(amount < currentBalance) {
            state = true;
        }
        return state;
    }

    private void updateBalance(long accountId, int amount, TransactionType type) {
       if(type.equals(TransactionType.DEPOSIT)) {
           currentBalance += amount;
       } else if (type.equals(TransactionType.WITHDRAWAL)) {
           currentBalance -= amount;
       }
    }

    enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }
}
