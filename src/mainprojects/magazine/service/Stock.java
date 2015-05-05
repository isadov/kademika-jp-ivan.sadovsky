package mainprojects.magazine.service;

import mainprojects.magazine.products.Goods;
import mainprojects.magazine.products.Product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Stock<T extends Goods> {

	private List<LinkedList<Goods>> arrayOfList;


//попробовать Map. Попробовать связать по ид. Поле ид в типах. 
	
	public Stock() {
		arrayOfList = new ArrayList<>(); //arrayOfList = new ArrayList<LinkedList<>>();
	}

	public void add(T g) {
		if (arrayOfList.isEmpty()) {
			arrayOfList.add(new LinkedList<>());
			arrayOfList.get(0).push(g); //esli massiv pust lozim na mesto pervoho elementa nash tovar predvaritel'no sozdav novui LinkedList

			
		} else {
			for (int i = 0; i < arrayOfList.size(); i++) {
				if (arrayOfList.get(i).peek().equals(g)) {
					arrayOfList.get(i).push(g);
					return;
				}
			}

			arrayOfList.add(new LinkedList<>()); // pozvoliaet dobavit' ne tolko poslednii product no i vse do neho
			arrayOfList.get(arrayOfList.size() - 1).push(g);
			
			// eti dve zapisi reguliruut nashe dobavlenie productov pytem polychenia razmera massiva i sozdania novoho LinkedList
		}
	}

	public void addMoreThanOne(T g, int quantity) {
		for (int i = 0; i < quantity; i++) {
			add(g);
		}
	}

	public int getNumberTypesOnStock() {
		return arrayOfList.size();
	}

	public String getName(int index) {
		return arrayOfList.get(index).get(0).getName();
	}

	public String getAttribute(int index) {
		String result = null;
		if (arrayOfList.get(index).get(0) instanceof Product) {
			result = ((Product) arrayOfList.get(index).get(0)).getBrand().toString();
		}

		return result;
	}

	public int getQuantity(int index) {
		return arrayOfList.get(index).size();
	}

	public int getPrice(int index) {
		return (int) arrayOfList.get(index).get(0).getPrice();
	}

	public boolean remove(int index, int quantity) {
		if (quantity <= arrayOfList.get(index).size()) {
			for (int i = 0; i < quantity; i++) {
				arrayOfList.get(index).pop();
			}
			if (arrayOfList.get(index).isEmpty()) {
				arrayOfList.remove(index);
			}
			return true;
		} else {
			return false;
		}

	}
	
}
