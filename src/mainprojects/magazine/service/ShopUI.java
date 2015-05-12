package mainprojects.magazine.service;

import mainprojects.magazine.customer.Customer;
import mainprojects.magazine.transaction.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShopUI {

    Customer customer;
    private Shop shop;
    private Stock stock;

    private JFrame jframe;
    private JFrame transactionInfoFrame;

    private JTextField jtf;
    private JTextField jtf1;
    private List<JRadioButton> arrayRButton;
    private JPanel mainPanel;

    private ButtonGroup group;

    private JPanel radioPanel;
    private JPanel attributePanel;
    private JPanel quantPanel;
    private JPanel basePanel;

    private JLabel labelColumn1;
    private JLabel labelColumn2;
    private JLabel labelColumn3;

    private JPanel panel;


    public ShopUI(Shop shop, Stock stock) {

        this.shop = shop;
        this.stock = stock;

        mainPanel = createSellingPanel();

        jframe = new JFrame("Best Product Shop");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setMinimumSize(new Dimension(800, 600));
        jframe.add(mainPanel);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setAlwaysOnTop(false);
        jframe.setVisible(true);

    }

    private void createTransactionPanel() {
        JPanel transactionInfoPanel;

        transactionInfoFrame = new JFrame("Transaction Info");
        transactionInfoFrame.setMinimumSize(new Dimension(900, 400));
        transactionInfoFrame.setLocation(300, 200);
        transactionInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        transactionInfoPanel = new JPanel();
        transactionInfoPanel.setLayout(new GridLayout());

        JMenuBar jMenuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        jMenuBar.add(menuFile);

        JMenuItem jMenuItemNewTransaction = new JMenuItem("Show Transaction Operation");
        menuFile.add(jMenuItemNewTransaction);

        jMenuItemNewTransaction.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createTransactionPanel();
            }
        });

        transactionInfoFrame.setJMenuBar(jMenuBar);

        String[] columnNames = {"Customer Name", "Name Of Product", "Type Of Product",
                "How Much Unit Was Buy", "Price per 1", "Total Price", "Time"};

        Object[][] purchase = new Object[shop.getRegister().size()][7];
        List<Transaction> transactions = shop.getRegister();
        for (int i = 0; i < transactions.size(); i++) {
            purchase[i][0] = transactions.get(i).getClient();
            purchase[i][1] = transactions.get(i).getElementName();
            purchase[i][2] = transactions.get(i).getType();
            purchase[i][3] = transactions.get(i).getQuantity();
            purchase[i][4] = transactions.get(i).getPrice();
            purchase[i][5] = transactions.get(i).getPrice() * transactions.get(i).getQuantity();
            purchase[i][6] = transactions.get(i).getCurrentDate();
        }

        JTable table = new JTable(purchase, columnNames);

        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(1).setMinWidth(100);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(3).setMinWidth(100);
        table.getColumnModel().getColumn(4).setMinWidth(100);
        table.getColumnModel().getColumn(5).setMinWidth(100);
        table.getColumnModel().getColumn(6).setMinWidth(100);

        JScrollPane sp = new JScrollPane(table);
        transactionInfoPanel.add(sp);

        transactionInfoFrame.getContentPane().add(transactionInfoPanel);
        transactionInfoFrame.pack();
        transactionInfoFrame.setVisible(true);


    }


    private JPanel createSellingPanel() {

        /////////////////////////////////////////////////////////////////////////////////////////

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel label = new JLabel("Name Of Customer: ");
        panel.add(label, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(0, 0, 10, 0), 0, 0));

        jtf = new JTextField();
        jtf.setColumns(10);
        panel.add(jtf, new GridBagConstraints(2, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 10, 0), 0, 0));

        group = new ButtonGroup();
        radioPanel = new JPanel(new GridLayout(
                stock.getNumberTypesOnStockMap() + 1, 1, 1, 3));
        attributePanel = new JPanel(new GridLayout(
                stock.getNumberTypesOnStockMap() + 1, 1, 1, 9));
        quantPanel = new JPanel(new GridLayout(
                stock.getNumberTypesOnStockMap() + 1, 1, 1, 9));

        labelColumn1 = new JLabel("Name Of Product");
        labelColumn1.setFont(new Font("Arial", Font.BOLD, 14));
        radioPanel.add(labelColumn1);

        labelColumn2 = new JLabel("Brand Of Product");
        labelColumn2.setFont(new Font("Arial", Font.BOLD, 14));
        attributePanel.add(labelColumn2);

        labelColumn3 = new JLabel("In Storage");
        labelColumn3.setFont(new Font("Arial", Font.BOLD, 14));
        quantPanel.add(labelColumn3);

        arrayRButton = new ArrayList<>();
        for (int i = 0; i < stock.getNumberTypesOnStockMap(); i++) {
            arrayRButton.add(i, new JRadioButton(stock.getNameMap(i), false));
            group.add(arrayRButton.get(i));
            radioPanel.add(arrayRButton.get(i));
            attributePanel.add(new JLabel(stock.getAttributeMap(i)));
            quantPanel.add(new JLabel(Integer.toString(stock.qetQuantityMap(i))));

        }

        basePanel = new JPanel(new GridBagLayout());
        basePanel.add(radioPanel,
                new GridBagConstraints(0, 0, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 25, 0));
        basePanel.add(attributePanel,
                new GridBagConstraints(1, 0, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 25, 0));
        basePanel.add(quantPanel,
                new GridBagConstraints(2, 0, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 25, 0));
        panel.add(basePanel,
                new GridBagConstraints(0, 1, 3, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 25, 0));
        basePanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));

        JLabel label1 = new JLabel("You can choose product HERE !");
        label1.setFont(new Font("Arial", Font.PLAIN, 11));
        panel.add(label1,
                new GridBagConstraints(0, 2, 3, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10,
                        0), 0, 0));

        JLabel label2 = new JLabel("How Much Do You Need ?");
        panel.add(label2,
                new GridBagConstraints(0, 3, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10,
                        0), 0, 0));

        jtf1 = new JTextField(3);
        panel.add(jtf1,
                new GridBagConstraints(1, 3, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0, new Insets(0, 0, 10,
                        0), 0, 0));

        JButton button1 = new JButton("Buy");
        panel.add(button1,
                new GridBagConstraints(1, 4, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 25, 0));

        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!jtf.getText().isEmpty()) {
                    customer = new Customer(jtf.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Empty Name Of Customer", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!jtf1.getText().isEmpty()) {
                } else {
                    JOptionPane.showMessageDialog(null, "Empty Quantity Of Product", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int indexOfRButton = 0;
                while (indexOfRButton < arrayRButton.size()) {
                    if (arrayRButton.get(indexOfRButton).isSelected()) {
                        break;
                    } else {
                        indexOfRButton++;
                    }
                }
                if (indexOfRButton >= arrayRButton.size()) {
                    JOptionPane.showMessageDialog(null, "You Should Choose Some Product", "Warning", JOptionPane.WARNING_MESSAGE);
                    System.err.println("Please Choose product !");
                    return;
                }

//				shop.buy(customer, indexOfRButton, Integer.parseInt(jtf1.getText()));
                shop.buyMap(customer, indexOfRButton, Integer.parseInt(jtf1.getText()));

                radioPanel.removeAll();
                attributePanel.removeAll();
                quantPanel.removeAll();
                basePanel.removeAll();
                basePanel.setBorder(BorderFactory.createEmptyBorder());

                group = new ButtonGroup();
                radioPanel = new JPanel(new GridLayout(stock
                        .getNumberTypesOnStockMap() + 1, 1, 1, 3));
                attributePanel = new JPanel(new GridLayout(stock
                        .getNumberTypesOnStockMap() + 1, 1, 1, 9));
                quantPanel = new JPanel(new GridLayout(stock
                        .getNumberTypesOnStockMap() + 1, 1, 1, 9));

                labelColumn1 = new JLabel("Name Of Product");
                labelColumn1.setFont(new Font("Arial", Font.BOLD, 14));
                radioPanel.add(labelColumn1);

                labelColumn2 = new JLabel("Brand Of Product");
                labelColumn2.setFont(new Font("Arial", Font.BOLD, 14));
                attributePanel.add(labelColumn2);

                labelColumn3 = new JLabel("In Storage");
                labelColumn3.setFont(new Font("Arial", Font.BOLD, 14));
                quantPanel.add(labelColumn3);

                for (int i = 0; i < stock.getNumberTypesOnStockMap(); i++) {
                    arrayRButton.add(i, new JRadioButton(stock.getNameMap(i),
                            false));
                    group.add(arrayRButton.get(i));
                    radioPanel.add(arrayRButton.get(i));
                    attributePanel.add(new JLabel(stock.getAttributeMap(i)));
                    quantPanel.add(new JLabel(Integer.toString(stock
                            .qetQuantityMap(i))));

                }

                basePanel = new JPanel(new GridBagLayout());
                basePanel.add(radioPanel, new GridBagConstraints(0, 0, 1, 1, 0,
                        0, GridBagConstraints.LINE_START, 0, new Insets(0, 0,
                        0, 0), 25, 0));
                basePanel.add(attributePanel, new GridBagConstraints(1, 0, 1,
                        1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(
                        0, 0, 0, 0), 25, 0));
                basePanel.add(quantPanel, new GridBagConstraints(2, 0, 1, 1, 0,
                        0, GridBagConstraints.LINE_START, 0, new Insets(0, 0,
                        0, 0), 25, 0));
                panel.add(basePanel, new GridBagConstraints(0, 1, 3, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 25, 0));
                basePanel.setBorder(BorderFactory.createLineBorder(Color.gray,
                        1));

                panel.revalidate();

            }

        });

        JButton button2 = new JButton("Exit");
        panel.add(button2,
                new GridBagConstraints(2, 4, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 25, 0));

        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
            }

        });

        return panel;
    }

}
