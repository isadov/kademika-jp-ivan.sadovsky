package mainprojects.store_hashmap_structure.service;

import mainprojects.store_hashmap_structure.guitar.Guitar;
import mainprojects.store_hashmap_structure.guitar.GuitarType;
import mainprojects.store_hashmap_structure.purchase.Purchase;

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
    private String guitarType;
    private String guitarBrand;
    private String model;
    private int number;

    JFrame purchaseInfoFrame;

    public StoreGUI(Store store) {

        this.store = store;

        newPurchaseInfoTable();
    }

    private void newPurchaseInfoTable() {

        JPanel purchaseInfoPanel;

        purchaseInfoFrame = new JFrame("Purchase information");
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
                // TODO Auto-generated method stub
                makeNewPurchaseForm();
            }
        });

        purchaseInfoFrame.setJMenuBar(menuBar);

        String[] columnNames = {"Customer Name", "Guitar Brand",
                "Guitar Model", "Number", "Discount", "Common price", "Date"};

        Object[][] purchase = new Object[store.getDb().getPurchase().size()][7];
        List<Purchase> purchaseList = store.getDb().getPurchase();
        for (int i = 0; i < purchaseList.size(); i++) {
            purchase[i][0] = purchaseList.get(i).getCustomer().getName();
            purchase[i][1] = purchaseList.get(i).getGuitar().getGuitarBrand();
            purchase[i][2] = purchaseList.get(i).getGuitar().getModel();
            purchase[i][3] = purchaseList.get(i).getNumber();
            purchase[i][5] = purchaseList.get(i).getCommonPrice();
            purchase[i][4] = purchaseList.get(i).getDiscount();
            purchase[i][6] = purchaseList.get(i).getDate().getTime();
        }

        JTable table = new JTable(purchase, columnNames);

        table.getColumnModel().getColumn(0).setMinWidth(200);
        table.getColumnModel().getColumn(1).setMinWidth(100);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(3).setMinWidth(75);
        table.getColumnModel().getColumn(5).setMinWidth(100);
        table.getColumnModel().getColumn(4).setMinWidth(50);
        table.getColumnModel().getColumn(6).setMinWidth(200);

        JScrollPane sp = new JScrollPane(table);
        purchaseInfoPanel.add(sp);

        purchaseInfoFrame.getContentPane().add(purchaseInfoPanel);
        purchaseInfoFrame.pack();
        purchaseInfoFrame.setVisible(true);

    }

    private void makeNewPurchaseForm() {

        JPanel panel;
        JLabel customerNameLabel;
        JLabel guitarModelLabel;
        JLabel guitarNumberLabel;
        final JLabel printInfoLabel;
        JLabel chooseTypeLabel;
        JLabel chooseBrandLabel;
        final JTextField customerNameTextField;
        final JTextField guitarModelTextField;
        final JTextField guitarNumberTextField;
        final JComboBox jComboBoxGuitarType;
        final JComboBox jComboBoxGuitarBrand;
        final JTextArea jTextArea;
        JScrollPane scrollPane;
        final JRadioButton jRadioButtonPricesOnly;
        JRadioButton jRadioButtonAllInfo;
        JRadioButton jRadioButtonAcousticType;
        JRadioButton jRadioButtonElectricType;
        JRadioButton jRadioButtonBassType;
        JButton jButtonBuy;
        JButton jButtonShowPurchases;
        JButton jButtonShowPurchaseStatistics;

        JFrame frame = new JFrame("Welcome to guitar store");
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLocation(200, 100);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JPanel customerPanel = new JPanel();
        customerNameLabel = new JLabel("Enter your name");
        customerPanel.add(customerNameLabel);

        customerNameTextField = new JTextField();
        customerNameTextField.setColumns(25);
        customerPanel.add(customerNameTextField);
        panel.add(customerPanel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JPanel chooseGuitarPanel = new JPanel();

        chooseTypeLabel = new JLabel("Choose guitar type");
        chooseGuitarPanel.add(chooseTypeLabel);

        String[] jComboBoxGuitarTypeValues = new String[store.getDb().getGuitarTypeMap().keySet().size()];
        int i = 0;
        for (String s : store.getDb().getGuitarTypeMap().keySet()) {
            jComboBoxGuitarTypeValues[i] = s;
            i++;
        }

        jComboBoxGuitarType = new JComboBox(jComboBoxGuitarTypeValues);
        chooseGuitarPanel.add(jComboBoxGuitarType);

        chooseBrandLabel = new JLabel("Choose guitar brand");
        chooseGuitarPanel.add(chooseBrandLabel);

        String[] jComboBoxGuitarBrandValues = new String[store.getDb().getGuitarBrandMap().keySet().size()];
        i = 0;
        for (String s : store.getDb().getGuitarBrandMap().keySet()) {
            jComboBoxGuitarBrandValues[i] = s;
            i++;
        }

        jComboBoxGuitarBrand = new JComboBox(jComboBoxGuitarBrandValues);
        chooseGuitarPanel.add(jComboBoxGuitarBrand);

        panel.add(chooseGuitarPanel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JPanel enterFieldsGuitarPanel = new JPanel();

        guitarModelLabel = new JLabel("Enter guitar model");
        enterFieldsGuitarPanel.add(guitarModelLabel);

        guitarModelTextField = new JTextField();
        guitarModelTextField.setColumns(10);
        enterFieldsGuitarPanel.add(guitarModelTextField);

        guitarNumberLabel = new JLabel("Enter number");
        enterFieldsGuitarPanel.add(guitarNumberLabel);

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        guitarNumberTextField = new JTextField();
        guitarNumberTextField.setDocument(new PlainDocument() {
            String chars = "0123456789.";

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (chars.indexOf(str) != -1) {
                    super.insertString(offs, str, a);
                }
            }
        });
        guitarNumberTextField.setText("1");
        guitarNumberTextField.setColumns(2);
        enterFieldsGuitarPanel.add(guitarNumberTextField);
        model = guitarNumberTextField.getText();

        panel.add(enterFieldsGuitarPanel, new GridBagConstraints(0, 2, 1, 1, 0,
                0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        jTextArea = new JTextArea(10, 50);
        jTextArea.setEditable(false);
        jTextArea.setText(store.printStore(true));
        scrollPane = new JScrollPane(jTextArea);
        panel.add(scrollPane, new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        printInfoLabel = new JLabel(store.printNumberOfGuitarType());
        printInfoLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        panel.add(printInfoLabel, new GridBagConstraints(0, 4, 1, 1, 0, 0,
                GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));

        jRadioButtonPricesOnly = new JRadioButton("Prices only");
        jRadioButtonPricesOnly.setSelected(true);
        jRadioButtonPricesOnly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jTextArea.setText(null);
                jTextArea.setText(store.printStore(true));
            }
        });

        jRadioButtonAllInfo = new JRadioButton("All information");
        jRadioButtonAllInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                jTextArea.setText(null);
                jTextArea.setText(store.printStore(false));
            }
        });

        ButtonGroup buttonGroupPrintInfo = new ButtonGroup();
        buttonGroupPrintInfo.add(jRadioButtonPricesOnly);
        buttonGroupPrintInfo.add(jRadioButtonAllInfo);

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridBagLayout());
        filterPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        jRadioButtonAcousticType = new JRadioButton("Show acoustic");
        jRadioButtonAcousticType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jTextArea.setText(null);
                jTextArea.setText(store.printGuitarByType(GuitarType.ACOUSTIC,
                        jRadioButtonPricesOnly.isSelected()));
            }
        });

        jRadioButtonElectricType = new JRadioButton("Show electric");
        jRadioButtonElectricType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                jTextArea.setText(null);
                jTextArea.setText(store.printGuitarByType(GuitarType.ELECTRIC,
                        jRadioButtonPricesOnly.isSelected()));
            }
        });

        jRadioButtonBassType = new JRadioButton("Show bass");
        jRadioButtonBassType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                jTextArea.setText(null);
                jTextArea.setText(store.printGuitarByType(GuitarType.BASS,
                        jRadioButtonPricesOnly.isSelected()));
            }
        });

        ButtonGroup buttonGroupPrintType = new ButtonGroup();
        buttonGroupPrintType.add(jRadioButtonAcousticType);
        buttonGroupPrintType.add(jRadioButtonElectricType);
        buttonGroupPrintType.add(jRadioButtonBassType);
        filterPanel.add(jRadioButtonPricesOnly, new GridBagConstraints(0, 0, 1,
                1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0,
                0), 0, 0));
        filterPanel.add(jRadioButtonAllInfo, new GridBagConstraints(1, 0, 1, 1,
                0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0),
                0, 0));
        filterPanel.add(jRadioButtonAcousticType, new GridBagConstraints(0, 1,
                1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0,
                0, 0), 0, 0));
        filterPanel.add(jRadioButtonElectricType, new GridBagConstraints(1, 1,
                1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0,
                0, 0), 0, 0));
        filterPanel.add(jRadioButtonBassType, new GridBagConstraints(2, 1, 1,
                1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0,
                0), 0, 0));
        panel.add(filterPanel, new GridBagConstraints(0, 5, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JPanel actionButtons = new JPanel();

        jButtonBuy = new JButton("Buy");
        actionButtons.add(jButtonBuy);

        jButtonBuy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg) {
                // TODO Auto-generated method stub

                try {

                    Guitar choosenGuitar = null;
                    guitarBrand = (String) jComboBoxGuitarBrand.getSelectedItem();
                    guitarType = (String) jComboBoxGuitarType.getSelectedItem();
                    model = guitarModelTextField.getText().toUpperCase();
                    number = Integer.parseInt(guitarNumberTextField.getText());
                    customerName = customerNameTextField.getText();

                    if (model.trim().isEmpty()) {
                        throw new NullPointerException();
                    }

                    for (Guitar guitar : store.getDb().getGuitarTypeMap().get(guitarType)) {
                        if (guitar.getGuitarBrand().toString().equals(guitarBrand) &&
                                guitar.getModel().equals(model.toUpperCase())) {
                            choosenGuitar = guitar;
                        }
                    }

                    if (choosenGuitar != null) {
                        if (customerName.trim().isEmpty()) {
                            store.newPurchase(choosenGuitar, number, "", new GregorianCalendar());
                        } else {
                            store.newPurchase(choosenGuitar, number, customerName, new GregorianCalendar());
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }

                    jTextArea.setText(null);
                    jTextArea.setText(store.printStore(jRadioButtonPricesOnly
                            .isSelected()));

                    printInfoLabel.setText(store.printNumberOfGuitarType());
                    printInfoLabel.setFont(new Font(Font.MONOSPACED,
                            Font.PLAIN, 12));

                } catch (NullPointerException e) {
                    // TODO: handle exception
                    JOptionPane.showMessageDialog(null,
                            "One of fields is empty. You should enter values!",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null,
                            "There is no such guitar in store!", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                } catch (IllegalStateException e) {
                    JOptionPane.showMessageDialog(null,
                            "Illegal number! We don't have it in store",
                            "Error Message", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                purchaseInfoFrame.dispose();
                purchaseInfoFrame.getContentPane().removeAll();
                newPurchaseInfoTable();
            }

        });

        jButtonShowPurchases = new JButton("Show purchases");
        actionButtons.add(jButtonShowPurchases);
        jButtonShowPurchases.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                purchaseInfoFrame.dispose();
                purchaseInfoFrame.getContentPane().removeAll();
                newPurchaseInfoTable();
            }
        });

        jButtonShowPurchaseStatistics = new JButton(
                "Show purchase statistics by week");
        actionButtons.add(jButtonShowPurchaseStatistics);
        jButtonShowPurchaseStatistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                newPurchaseInfoWindow(store.printNumberOfPurchasesByWeek());
            }
        });

        panel.add(actionButtons, new GridBagConstraints(0, 6, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

    }

    private void newPurchaseInfoWindow(String info) {

        JFrame frame = new JFrame("Purchase information");
        frame.setMinimumSize(new Dimension(600, 450));
        frame.setLocation(300, 200);
        JPanel jp = new JPanel();

        JTextArea ta = new JTextArea(20, 50);
        ta.setEditable(false);
        ta.setText(info);
        JScrollPane sp = new JScrollPane(ta);
        jp.add(sp);

        frame.getContentPane().add(jp);

        frame.pack();
        frame.setVisible(true);

    }

}
