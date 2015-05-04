package mainprojects.magazine.service;

import mainprojects.magazine.customer.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShopUI {
	Shop shop;
	Customer customer;
	private Shop productshop;
	private Stock stock;
	private JFrame jframe;
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
	private JPanel pTable;
	private JPanel showingPanel;

	private JTable tTransaction;

	public ShopUI(Shop productshop, Stock stock) {

		this.productshop = productshop;
		this.stock = stock;
		jframe = new JFrame("Best Product Shop");
		mainPanel = createPanel();

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setMinimumSize(new Dimension(800, 600));
		jframe.add(mainPanel);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setAlwaysOnTop(false);
		jframe.setVisible(true);
	}

	private JPanel createPanel() {

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
				stock.getNumberTypesOnStock() + 1, 1, 1, 3));
		attributePanel = new JPanel(new GridLayout(
				stock.getNumberTypesOnStock() + 1, 1, 1, 9));
		quantPanel = new JPanel(new GridLayout(
				stock.getNumberTypesOnStock() + 1, 1, 1, 9));

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
		for (int i = 0; i < stock.getNumberTypesOnStock(); i++) {
			arrayRButton.add(i, new JRadioButton(stock.getName(i), false));
			group.add(arrayRButton.get(i));
			radioPanel.add(arrayRButton.get(i));
			attributePanel.add(new JLabel(stock.getAttribute(i)));
			quantPanel.add(new JLabel(Integer.toString(stock.getQuantity(i))));

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
					JOptionPane.showMessageDialog(null, "Please Choose Product", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}

				productshop.buy(customer, indexOfRButton, Integer.parseInt(jtf1.getText()));

				radioPanel.removeAll();
				attributePanel.removeAll();
				quantPanel.removeAll();
				basePanel.removeAll();
				basePanel.setBorder(BorderFactory.createEmptyBorder());

				group = new ButtonGroup();
				radioPanel = new JPanel(new GridLayout(stock
						.getNumberTypesOnStock() + 1, 1, 1, 3));
				attributePanel = new JPanel(new GridLayout(stock
						.getNumberTypesOnStock() + 1, 1, 1, 9));
				quantPanel = new JPanel(new GridLayout(stock
						.getNumberTypesOnStock() + 1, 1, 1, 9));

				labelColumn1 = new JLabel("Name Of Product");
				labelColumn1.setFont(new Font("Arial", Font.BOLD, 14));
				radioPanel.add(labelColumn1);

				labelColumn2 = new JLabel("Brand Of Product");
				labelColumn2.setFont(new Font("Arial", Font.BOLD, 14));
				attributePanel.add(labelColumn2);

				labelColumn3 = new JLabel("In Storage");
				labelColumn3.setFont(new Font("Arial", Font.BOLD, 14));
				quantPanel.add(labelColumn3);

				for (int i = 0; i < stock.getNumberTypesOnStock(); i++) {
					arrayRButton.add(i, new JRadioButton(stock.getName(i),
							false));
					group.add(arrayRButton.get(i));
					radioPanel.add(arrayRButton.get(i));
					attributePanel.add(new JLabel(stock.getAttribute(i)));
					quantPanel.add(new JLabel(Integer.toString(stock
							.getQuantity(i))));

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
