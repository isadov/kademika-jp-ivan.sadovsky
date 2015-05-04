package mainprojects.store_hashmap_structure.service;

import mainprojects.store_hashmap_structure.customer.Customer;
import mainprojects.store_hashmap_structure.guitar.*;
import mainprojects.store_hashmap_structure.purchase.Purchase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Store {

    private DataBase db;

    public Store() {
        db = new DataBase();
    }

    public void newPurchase(Guitar g, int number, String customerName, Calendar date)
            throws RuntimeException {

        Purchase purchase = new Purchase();
        Customer customer;

        if (customerName == "") {
            customer = new Customer();
        } else {
            customer = new Customer(customerName);
        }

        purchase.setCustomer(customer);
        purchase.setNumber(number);
        purchase.setDate(date);
        purchase.setGuitar(g);
        double commonPrice = g.getPrice() * number;

        if (customerName == "") {
            purchase.setDiscount(0D);
            purchase.setCommonPrice(commonPrice);
        } else {
            double discount = calculateDiscount(calculateCommonPrice(customer, commonPrice));
            purchase.setDiscount(discount);
            purchase.setCommonPrice(commonPrice - commonPrice * discount);
        }

        executePurchase(purchase, g.getModel(), number);

    }

    private void executePurchase(Purchase purchase, String model, int number) throws IllegalStateException {

        Guitar g = purchase.getGuitar();

        if (number < 1 || number > this.getGuitarNumberByModel(model, db.getGuitarTypeMap().get(g.getGuitarType().toString()))) {
            throw new IllegalStateException();
        }

        for (int idx = 0; idx < number; idx++) {
            db.removeGuitarFromDB(g);
        }

        db.getPurchase().add(purchase);
    }

    public double calculateCommonPrice(Customer customer, double price) {

        double sum = 0.0;

        String customerName = customer.getName();

        for (Purchase p : this.getDb().getPurchase()) {
            if (customerName.equals(p.getCustomer().getName())) {
                sum += (p.getGuitar().getPrice() * p.getNumber());
            }
        }

        sum += price;

        return sum;
    }

    public double calculateDiscount(double sum) {

        double discount = 0D;
        if (sum > 50000 && sum < 100000) {
            discount = 0.05;
        } else if (sum > 100000) {
            discount = 0.1;
        }

        return discount;
    }

    public String printStore(boolean onlyPrices) {

        StringBuilder resultString = new StringBuilder("");

        for (List<Guitar> guitarType : this.getDb().getGuitarTypeMap().values()) {
            for (Guitar guitarTemp : guitarType) {
                resultString.append(guitarTemp.getClass().getSimpleName() + " ");
                resultString.append(guitarTemp.getGuitarBrand() + " ");
                resultString.append(guitarTemp.getColor() + " ");
                resultString.append(guitarTemp.getModel() + " ");

                if (onlyPrices == false) {
                    resultString.append(guitarTemp.getFreatboardMaterial() + " ");
                    resultString.append(guitarTemp.isFreatboardGlued() + " ");
                    resultString.append(guitarTemp.getNumberOfStrings() + " ");
                    resultString.append(guitarTemp.getNumberOfFrets() + " ");
                    resultString.append(guitarTemp.getManufacturer() + " ");
                }

                resultString.append(guitarTemp.getPrice() + " ");

                if (onlyPrices == true) {
                    resultString.append("\n");
                    continue;
                }

                if (guitarTemp instanceof ElectricGuitar
                        || (guitarTemp instanceof BassGuitar)) {
                    ElectricGuitar valueTemp = (ElectricGuitar) guitarTemp;
                    resultString.append(valueTemp.getBodyMaterial() + " ");
                    resultString.append(valueTemp.getNeckName() + " ");
                    resultString.append(valueTemp.getBridgeName() + " ");
                    resultString.append(valueTemp.isFloydRose());
                }

                if (guitarTemp instanceof AcousticGuitar) {
                    AcousticGuitar valueTemp = (AcousticGuitar) guitarTemp;
                    resultString.append(valueTemp.isStringsIsNylon());

                }

                resultString.append("\n");
            }
        }

        return resultString.toString();
    }

    public String printGuitarByType(GuitarType guitarType, boolean onlyPrices) {

        StringBuilder resultString = new StringBuilder("");

        for (Guitar guitarTemp : this.getDb().getGuitarTypeMap().get(guitarType.toString())) {
            resultString.append(guitarTemp.getClass().getSimpleName() + " ");
            resultString.append(guitarTemp.getGuitarBrand() + " ");
            resultString.append(guitarTemp.getColor() + " ");
            resultString.append(guitarTemp.getModel() + " ");


            if (onlyPrices == false) {
                resultString.append(guitarTemp.getFreatboardMaterial() + " ");
                resultString.append(guitarTemp.isFreatboardGlued() + " ");
                resultString.append(guitarTemp.getNumberOfStrings() + " ");
                resultString.append(guitarTemp.getNumberOfFrets() + " ");
                resultString.append(guitarTemp.getManufacturer() + " ");

            }

            resultString.append(guitarTemp.getPrice() + " ");

            if (onlyPrices == true) {
                resultString.append("\n");
                continue;
            }

            if (guitarTemp instanceof ElectricGuitar
                    || (guitarTemp instanceof BassGuitar)) {
                ElectricGuitar valueTemp = (ElectricGuitar) guitarTemp;
                resultString.append(valueTemp.getBodyMaterial() + " ");
                resultString.append(valueTemp.getNeckName() + " ");
                resultString.append(valueTemp.getBridgeName() + " ");
                resultString.append(valueTemp.isFloydRose());
            }

            if (guitarTemp instanceof AcousticGuitar) {
                AcousticGuitar valueTemp = (AcousticGuitar) guitarTemp;
                resultString.append(valueTemp.isStringsIsNylon());

            }

            resultString.append("\n");

        }

        return resultString.toString();

    }

    public String printNumberOfGuitarType() {

        String result = "";

        return result += "We have " + db.getNumberOfAcousticGuitar()
                + " acoustic guitars, " + db.getNumberOfElectricGuitar()
                + " electric guitars, " + db.getNumberOfBassGuitar()
                + " bass guitars in the stock\n";

    }

    public String printPurchases(Calendar date) {

        int sum = 0;
        int number = 0;
        StringBuilder resultString = new StringBuilder("");

        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("EEEE dd.MM.YYYY k:mm");

        for (Purchase value : db.getPurchase()) {
            if (value != null) {
                if (date != null) {
                    if (!(value.getDate().getTime().toString().equals(date.getTime().toString()))) {
                        continue;
                    }
                }

                number++;
                sum += (value.getNumber() * value.getGuitar().getPrice());
                resultString.append("Purchase ");
                resultString.append(number + " ");
                resultString.append(value.getCustomer().getName() + " ");
                resultString.append(value.getGuitar().getGuitarBrand() + " ");
                resultString.append(value.getGuitar().getModel() + " ");
                resultString.append(value.getNumber() + " ");
                resultString.append((value.getNumber() * value.getGuitar().getPrice()) + " ");
                resultString.append(formatter.format(value.getDate().getTime()) + "\n");

            }

        }

        resultString.append("\nAll " + number + " purchases, all wasted money - " + sum + "\n");

        return resultString.toString();

    }

    public String printNumberOfPurchasesByWeek() {

        String result = "";
        Calendar now = new GregorianCalendar();
        int[] numberOfPurchaseByWeek = new int[7];

        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("EEEE dd.MM.YYYY");

        for (int i = 0; i < numberOfPurchaseByWeek.length; i++) {

            for (Purchase value : db.getPurchase()) {

                {
                    String valueDate = formatter.format(value.getDate().getTime());
                    String currentDate = formatter.format(now.getTime());

                    if (valueDate.equals(currentDate)) {
                        numberOfPurchaseByWeek[i]++;
                    }

                }

            }

            now.add(Calendar.DATE, -1);
        }

        for (int i = 0; i < numberOfPurchaseByWeek.length; i++) {

            if (i == 0) {
                result += numberOfPurchaseByWeek[i]
                        + " purchases were made today\n";
            } else if (i == 1) {
                result += numberOfPurchaseByWeek[i]
                        + " purchases were made yesterday\n";
            } else
                result += numberOfPurchaseByWeek[i] + " purchases were made "
                        + i + " days ago\n";

        }

        return result;

    }

    int getGuitarNumberByModel(String model, List<Guitar> guitars) {

        int kol = 0;
        model = model.toUpperCase();

        for (Guitar g : guitars) {
            if (g.getModel().toUpperCase().equals(model)) {
                kol++;
            }
        }

        return kol;
    }

    public DataBase getDb() {
        return db;
    }

}
