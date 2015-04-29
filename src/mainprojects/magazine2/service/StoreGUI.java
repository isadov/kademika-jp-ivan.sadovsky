package mainprojects.magazine2.service;

import mainprojects.magazine2.products.Product;
import mainprojects.magazine2.purchase.Purchase;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.GregorianCalendar;
import java.util.List;

public class StoreGUI {

    private Store store;
    private String customerName;
    private String productType;
    private String countryManufacturer;
    private String nameOfProduct;
    private int number;

    JFrame purchaseInfoFrame;

    public StoreGUI(Store store) {
        this.store = store;

        newPurchaseInfoTable();
    }

    private void newPurchaseInfoTable() {

        JPanel purchaseInfoPanel;

        purchaseInfoFrame = new JFrame("Purchase Information");
        purchaseInfoFrame.setMinimumSize(new Dimension(900, 400));
        purchaseInfoFrame.setLocation(300, 200);
        purchaseInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        purchaseInfoPanel = new JPanel();
        purchaseInfoPanel.setLayout(new GridLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);

        JMenuItem menuItemNewPurchase = new JMenuItem("Make new purchase");
        menuFile.add(menuItemNewPurchase);

        menuItemNewPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeNewPurchaseForm();
            }
        });

        purchaseInfoFrame.setJMenuBar(menuBar);

        String[] columnNames = {"Customer Name", "Product Country", "Name Of Product", "Number", "Price", "Date"};

        Object[][] purchase = new Object[store.getDb().getPurchase().size()][6];
        List<Purchase> purchaseList = store.getDb().getPurchase();
        for(int i = 0; i < purchaseList.size(); i++) {
            purchase[i][0] = purchaseList.get(i).getCustomer().getName();
            purchase[i][1] = purchaseList.get(i).getProduct().getCountryManufacturer();
            purchase[i][2] = purchaseList.get(i).getProduct().getName();
            purchase[i][3] = purchaseList.get(i).getNumber();
            purchase[i][4] = purchaseList.get(i).getPrice();
            purchase[i][5] = purchaseList.get(i).getCalendar().getTime();

        }

        JTable table = new JTable(purchase, columnNames);

        table.getColumnModel().getColumn(0).setMinWidth(200);
        table.getColumnModel().getColumn(1).setMinWidth(200);
        table.getColumnModel().getColumn(2).setMinWidth(200);
        table.getColumnModel().getColumn(3).setMinWidth(200);
        table.getColumnModel().getColumn(4).setMinWidth(200);
        table.getColumnModel().getColumn(5).setMinWidth(200);

        JScrollPane sp = new JScrollPane(table);
        purchaseInfoPanel.add(sp);

        purchaseInfoFrame.getContentPane().add(purchaseInfoPanel);
        purchaseInfoFrame.pack();
        purchaseInfoFrame.setVisible(true);
    }

    private void makeNewPurchaseForm() {

        JPanel panel;
        JLabel customerNameLabel;
        JLabel productNameLabel;
        JLabel productNumberLabel;
        final JLabel printInfoLabel;
        JLabel chooseTypeLabel;
        JLabel chooseCountryLabel;

        final JTextField customerNameTextField;
        final JTextField productNameTextField;
        final JTextField productNumberTextField;

        final JComboBox jComboBoxProductType;
        final JComboBox jComboBoxProductCountry;

        final JTextArea jTextArea;
        JScrollPane scrollPane;

        JRadioButton jRadioButtonAllInfo;

        JButton jButtonBuy;
        JButton jButtonShowPurchases;
        JButton jButtonShowPurchasesStatistics;

        JFrame frame = new JFrame("Best Product Shop");
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLocation(200, 100);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JPanel customerPanel = new JPanel();
        customerNameLabel = new JLabel("Your Name Here");
        customerPanel.add(customerNameLabel);

        customerNameTextField = new JTextField();
        customerNameTextField.setColumns(25);
        customerPanel.add(customerNameTextField);
        panel.add(customerPanel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JPanel chooseProductPanel = new JPanel();

        chooseTypeLabel = new JLabel("Choose Type Of Product");
        chooseProductPanel.add(chooseTypeLabel);

        String[] jComboBoxProductTypeValues = new String[store.getDb().getProductTypeMap().keySet().size()];
        int i = 0;
        for (String s : store.getDb().getProductTypeMap().keySet()) {
            jComboBoxProductTypeValues[i] = s;
            i++;
        }

        jComboBoxProductType = new JComboBox(jComboBoxProductTypeValues);
        chooseProductPanel.add(jComboBoxProductType);

        chooseCountryLabel = new JLabel("Choose Product Country");
        chooseProductPanel.add(chooseCountryLabel);

        String[] jComboBoxProductCountryValues = new String[store.getDb().getCountryManufacturerMap().keySet().size()];
        i = 0;
        for (String s : store.getDb().getCountryManufacturerMap().keySet()) {
            jComboBoxProductCountryValues[i] = s;
            i++;
        }

        jComboBoxProductCountry = new JComboBox(jComboBoxProductCountryValues);
        chooseProductPanel.add(jComboBoxProductCountry);

        panel.add(chooseProductPanel, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JPanel enterFieldProductPanel = new JPanel();

        productNameLabel = new JLabel("Enter Product Name");
        enterFieldProductPanel.add(productNameLabel);

        productNameTextField = new JTextField();
        productNameTextField.setColumns(10);
        enterFieldProductPanel.add(productNameTextField);

        productNumberLabel = new JLabel("Enter Number");
        enterFieldProductPanel.add(productNumberLabel);

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        productNumberTextField = new JTextField();
        productNumberTextField.setDocument(new PlainDocument() {
            String chars = "0123456789.";

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (chars.indexOf(str) != -1) {
                    super.insertString(offs, str, a);
                }
            }
        });
        productNumberTextField.setText("1");
        productNumberTextField.setColumns(2);
        enterFieldProductPanel.add(productNumberTextField);
        nameOfProduct = productNumberTextField.getText();

        panel.add(enterFieldProductPanel, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        jTextArea = new JTextArea(10, 50);
        jTextArea.setEditable(false);
        jTextArea.setText(store.printStore());
        scrollPane = new JScrollPane(jTextArea);
        panel.add(scrollPane, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        // 212 printNumberOfGuitarType

        jRadioButtonAllInfo = new JRadioButton("All Information");
        jRadioButtonAllInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.setText(null);
                jTextArea.setText(store.printStore());
            }
        });

        ButtonGroup buttonGroupPrintInfo = new ButtonGroup();
        buttonGroupPrintInfo.add(jRadioButtonAllInfo);

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridBagLayout());
        filterPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel actionButtons = new JPanel();

        jButtonBuy = new JButton("Buy");
        actionButtons.add(jButtonBuy);

        jButtonBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product choosenProduct = null;
                    countryManufacturer = (String) jComboBoxProductCountry.getSelectedItem();
                    productType = (String) jComboBoxProductType.getSelectedItem();
                    nameOfProduct = productNameTextField.getText().toUpperCase();
                    number = Integer.parseInt(productNumberTextField.getText());
                    customerName = customerNameTextField.getText();

                    if (nameOfProduct.trim().isEmpty()) {
                        throw new NullPointerException();
                    }

                    for (Product product : store.getDb().getProductTypeMap().get(productType)) {
                        if (product.getCountryManufacturer().toString().equals(countryManufacturer) &&
                                product.getName().equals(nameOfProduct.toUpperCase())) {
                            choosenProduct = product;
                        }
                    }

                    if (choosenProduct != null) {
                        if (customerName.trim().isEmpty()) {
                            store.newPurchase(choosenProduct, number, "", new GregorianCalendar());
                        } else {
                            store.newPurchase(choosenProduct, number, customerName, new GregorianCalendar());

                        }

                    } else {
                        throw new IllegalArgumentException();
                    }

                    jTextArea.setText(null);
                    jTextArea.setText(store.printStore());

                } catch (NullPointerException exc) {
                    JOptionPane.showMessageDialog(null, "One of fields is empty. Enter values", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                } catch (IllegalArgumentException exc) {
                    JOptionPane.showMessageDialog(null, "There is no such product in magazine", "Information", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } catch (IllegalStateException exc) {
                    JOptionPane.showMessageDialog(null, "Illegal number", "Error Message", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                purchaseInfoFrame.dispose();
                purchaseInfoFrame.getContentPane().removeAll();
                newPurchaseInfoTable();
            }
        });

        jButtonShowPurchases = new JButton("Show Purchases");
        actionButtons.add(jButtonShowPurchases);
        jButtonShowPurchases.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseInfoFrame.dispose();
                purchaseInfoFrame.getContentPane().removeAll();
                newPurchaseInfoTable();
            }
        });

        jButtonShowPurchasesStatistics = new JButton("Show Purchase By Week");
        actionButtons.add(jButtonShowPurchasesStatistics);
        jButtonShowPurchasesStatistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPurchaseInfoWindow(store.printNumberOfPurchasesByWeek());
            }
        });

        panel.add(actionButtons, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

    }

    private void newPurchaseInfoWindow(String info) {

        JFrame frame = new JFrame("Purchase Info");
        frame.setMinimumSize(new Dimension(600, 450));
        frame.setLocation(300, 200);
        JPanel jPanel = new JPanel();

        JTextArea jTextArea = new JTextArea(20, 50);
        jTextArea.setEditable(false);
        jTextArea.setText(info);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jPanel.add(jScrollPane);

        frame.getContentPane().add(jPanel);

        frame.pack();
        frame.setVisible(true);
    }
}
