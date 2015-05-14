package mainprojects.magazine.transaction;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCard {

    private List<Transaction> transactionList;

    public ShoppingCard() {
        this.transactionList = new ArrayList<>();
    }

    public void add(Transaction item) {
        transactionList.add(item);
    }

    public Transaction getItem (int index) {

        if(!transactionList.isEmpty()) {
            return transactionList.get(index);
        }
        return null;
    }

    public void removeItem() {
        if(!transactionList.isEmpty()) {
            transactionList.remove(transactionList.size() - 1);
        }
    }

    public void removeAll() {
        if(!transactionList.isEmpty()) {
            transactionList.removeAll(transactionList);
        }
    }

    public double getSum () {
        double sum = 0;
        if(!transactionList.isEmpty()) {
            for(Transaction transaction : transactionList) {
                sum += transaction.getPrice() * transaction.getQuantity();
            }
        }
        return sum;
    }

    public int getSize() {
        return transactionList.size();
    }
}
