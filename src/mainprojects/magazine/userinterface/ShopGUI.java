package mainprojects.magazine.userinterface;

import mainprojects.magazine.customer.Customer;
import mainprojects.magazine.customer.CustomerDataBase;
import mainprojects.magazine.products.Goods;
import mainprojects.magazine.products.Product;
import mainprojects.magazine.service.Date;
import mainprojects.magazine.service.Stock;
import mainprojects.magazine.transaction.BuyMenu;
import mainprojects.magazine.transaction.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShopGUI {


    private List<Transaction> register;
    private Date dateRef;
    private Stock stock;
    private JFrame jFrame, fCard;
    private JTable jTable;
    private JPanel panelBuy;
    private JPanel panelTransactions;
    private JPanel panelWelcome;
    private BuyMenu buyMenu;
    private LinkedList<Customer> customerList;
    private CustomerDataBase customerDB;

    public ShopGUI(Date dateRef, Stock stock, CustomerDataBase customerDB) {
        register = new ArrayList<>();
        this.stock = stock;
        this.dateRef = dateRef;
        this.customerDB = customerDB;
        customerList = new LinkedList<>();
        runShop();
    }

    private void runShop() {

        jFrame = new JFrame("Best Product Shop");
        panelWelcome = new JPanel(new GridBagLayout());

        JLabel labelEmpty = new JLabel("Welcome. Please Click Menu To Start");
        panelWelcome.add(labelEmpty);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem menuItem = new JMenuItem("Buy");
        menu.add(menuItem);

        JMenuItem menuItem1 = new JMenuItem("Show transactions");
        menu.add(menuItem1);

        menuBar.add(menu);

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBuyProcess();
            }
        });

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTransactions();
            }
        });

        jFrame.add(panelWelcome);
        jFrame.setJMenuBar(menuBar);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setMinimumSize(new Dimension(800, 600));
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }

    private void startBuyProcess() {
        jFrame.remove(panelWelcome);

        if (panelTransactions != null) {
            jFrame.remove(panelTransactions);
        }

        panelBuy = new JPanel(new GridBagLayout());

        List<Goods> goodsList = stock.getListOfGoods();
        jTable = createGoodsTable(goodsList);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        panelBuy.add(jScrollPane, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START,
                GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

        JButton button1 = new JButton("Create Transaction");
        panelBuy.add(button1, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START,
                0, new Insets(0, 0, 0, 0), 25, 0));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBuyMenuFrame(goodsList);
            }
        });

        JButton button2 = new JButton("Exit");
        panelBuy.add(button2, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START,
                0, new Insets(0, 0, 0, 0), 25, 0));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jFrame.add(panelBuy);
        jFrame.pack();
        jFrame.repaint();
        return;
    }

    private JTable createGoodsTable(List<Goods> listOfGoods) {
        String[] columnNames = {"Product", "Type",
                "Price", "In Storage", "Want To Buy"};
        Object[][] tableData = new Object[listOfGoods.size()][];
        Goods g;

        for (int i = 0; i < listOfGoods.size(); i++) {
            tableData[i] = new Object[5];
            g = listOfGoods.get(i);
            tableData[i][0] = g.getName();
            tableData[i][1] = ((Product) g).getType();
            tableData[i][2] = new Integer(g.getPrice());
            tableData[i][3] = new Integer(stock.qetQuantityMap(g.getClass(), g.getName()));
            tableData[i][4] = 0;
        }
        return new JTable(tableData, columnNames);
    }

    private void createBuyMenuFrame(List<Goods> listOfGoods) {

        int numbersToBuy;
        int quantity;
        Goods g;
        buyMenu = new BuyMenu();

        for (int i = 0; i < jTable.getRowCount(); i++) {
            numbersToBuy = Integer.parseInt(jTable.getValueAt(i, 4).toString());
            quantity = Integer.parseInt(jTable.getValueAt(i, 3).toString());

            if (quantity < numbersToBuy && numbersToBuy < 0) {
                JOptionPane.showMessageDialog(null, "Not Enough Goods On Storage", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (numbersToBuy != 0) {
                g = listOfGoods.get(i);
                buyMenu.add(new Transaction(
                        i,              //int indexInGoodsList
                        "01.01.01",     //String currentDate
                        "customer",     //String client
                        g.getName(),    //String elementName
                        g.getPrice(),   //int price
                        numbersToBuy)   //int quantity
                );
            }

        }


        fCard = new JFrame("Transaction Menu");
        fCard.setMinimumSize(new Dimension(500, 300));
        fCard.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel panelCart = new JPanel();
        panelCart.setLayout(new GridBagLayout());

        JLabel l1 = new JLabel("Please Input Your Data");
        l1.setFont(new Font("Arial", Font.BOLD, 14));
        panelCart.add(l1, new GridBagConstraints(1, 0, 2, 1, 0, 0, GridBagConstraints.LINE_START,
                GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

        JLabel l2 = new JLabel("Total price:");
        panelCart.add(l2, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START,
                GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

        JLabel l3 = new JLabel(Double.toString(buyMenu.getSum()) + "grn");
        panelCart.add(l3, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START,
                GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

        JLabel label = new JLabel("Input Your Name:");
        panelCart.add(label, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START,
                GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

        final JTextField tf = new JTextField();
        tf.setColumns(10);
        panelCart.add(tf, new GridBagConstraints(2, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0), 0, 0));

        JLabel label1 = new JLabel("Input your Nick:");
        panelCart.add(label1, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START,
                GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));
        final JTextField tf1 = new JTextField();
        tf1.setColumns(15);
        panelCart.add(tf1, new GridBagConstraints(2, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START,
                GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

        JButton button1 = new JButton("Buy");
        panelCart.add(button1, new GridBagConstraints(1, 6, 1, 1, 0, 0, GridBagConstraints.LINE_START,
                0, new Insets(0, 0, 0, 0), 0, 0));

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tf.getText().isEmpty() && !tf1.getText().isEmpty()) {
                    Customer customer = new Customer();
                    customer.setName(tf.getText());
                    customer.setNickname(tf1.getText());
                    customerDB.addCustomer(customer);
                } else {
                    JOptionPane.showMessageDialog(null, "Empty Name Or Nick", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Transaction transaction;
                for (int i = 0; i < buyMenu.getSize(); i++) {
                    transaction = buyMenu.getItem(i);
                    transaction.setClient(tf.getText());
                    transaction.setCurrentDate(dateRef.currentData);
                    register.add(transaction);

                    stock.removeMap(listOfGoods.get(transaction.getIndexInGoodsList()).getClass(), transaction.getElementName(),
                            transaction.getQuantity());
                }
                buyMenu = null;
                jFrame.remove(panelBuy);
                jFrame.repaint();
                fCard.dispose();
            }
        });

        JButton button2 = new JButton("Close");
        panelCart.add(button2, new GridBagConstraints(2, 6, 1, 1, 0, 0, GridBagConstraints.LINE_START,
                0, new Insets(0, 0, 0, 0), 0, 0));

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyMenu = null;
                fCard.dispose();
            }
        });

        fCard.add(panelCart);
        fCard.pack();
        fCard.setLocationRelativeTo(null);
        fCard.setVisible(true);
    }

    private void displayTransactions() {
        panelTransactions = new JPanel(new GridBagLayout());
        jFrame.remove(panelWelcome);

        if (register.isEmpty()) {
            JLabel labelEmpty = new JLabel("No Transactions Yet");
            panelTransactions.add(labelEmpty);
        } else {
            String[] columnTitles = new String[]{"TID", "Date", "Client", "Goods", "Price", "Quantity"};
            Object[][] data = new Object[register.size()][];

            for (int i = 0; i < register.size(); i++) {
                Transaction t = register.get(i);
                data[i] = new Object[]{
                        new Integer(i + 1),                        // "TID"
                        register.get(i).getCurrentDate(),          //Date
                        register.get(i).getClient(),               // Client
                        register.get(i).getElementName(),          //Goods
                        new Integer(register.get(i).getPrice()),   //Price
                        new Integer(register.get(i).getQuantity()) // Quantity
                };

            }
            JTable table = new JTable(data, columnTitles);
            table.getColumnModel().getColumn(1).setPreferredWidth(120);

            JScrollPane scrollPane = new JScrollPane(table);
            panelTransactions.add(scrollPane);
        }
        jFrame.add(panelTransactions);
        jFrame.pack();
        jFrame.repaint();

        return;
    }
}
