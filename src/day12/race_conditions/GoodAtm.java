package day12.race_conditions;

public class GoodAtm implements Atm {

    @Override
    public void checkBalance(long accountid) {
        System.out.println(accountid + " going to withdraw some money");
    }

    @Override
    public void withdrawMoney(long acoountId, int amount) {
        if(allowWithdraw(acoountId, amount)) {
            updateBalance(acoountId, amount, TransactionType.WITHDRAWAL);
        }
    }

    private boolean allowWithdraw(long accountId, int amount) {
        return true;
    }

    private void updateBalance(long accountId, int amount, TransactionType type) {
        System.out.println("Successful " + type + " account: " + " amount: " + amount);
    }

    enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }


}
