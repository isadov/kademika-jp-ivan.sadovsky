package mainprojects.magazine.transaction;

public class Transaction {
    private int indexInGoodsList;
    private String currentDate;
    private String client;
    private String elementName;
    private int price;
    private int quantity;
    private int id;

    public Transaction() {

    }

    public Transaction(int indexInGoodsList, String currentDate, String client, String elementName, int price, int quantity) {
        super();
        this.indexInGoodsList = indexInGoodsList;
        this.currentDate = currentDate;
        this.client = client;
        this.elementName = elementName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentData) {
        this.currentDate = currentData;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getElementName() {
        return elementName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndexInGoodsList() {
        return indexInGoodsList;
    }

    @Override
    public String toString() {

        return "Transaction happened !";

    }


}
