package day10.magazinediscountsystem;

import mainprojects.magazine.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DiscountSystem {

    private List<Transaction> container; // try generics

    public DiscountSystem() {
        this.container = new ArrayList<>();
    }

    public void addItem(Transaction item) {
        container.add(item);
    }

    public void removeItem() { // remove last transaction
        if (!container.isEmpty()) {
            container.remove(container.size() - 1);
        }
    }

    public void removeAllItem() {
            container.clear();
        }


    public Transaction getItemByIndex(int index) {
        if (!container.isEmpty()) {
            return container.get(index);
        }
        return null;
    }

    public double getSum() {
        double sum = 0;
        if (!container.isEmpty()) {
            for (Transaction trans : container) {
                sum += trans.getPrice() * trans.getQuantity();
            }
        }
        return sum;
    }

    public int getDiscount() {

        double sum = 0;
        sum = getSum();
        if (sum >= 1000) {
            return 10;
        } else {
            if (sum >= 50) {
                return 5;
            } else {
                return 0;
            }

        }


    }

    public double getTotalSum() {
        return getSum() - getSum() * getDiscount() / 100;
    }

    public int getSizeOfContainer() {
        return container.size();
    }

    
}

