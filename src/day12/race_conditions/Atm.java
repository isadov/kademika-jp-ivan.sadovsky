package day12.race_conditions;

public interface Atm {

    public void checkBalance(long accountid);

    public void withdrawMoney(long acoountId, int amount);
}
