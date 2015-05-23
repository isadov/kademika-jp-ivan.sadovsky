package mainprojects.magazine;

import mainprojects.magazine.customer.CustomerDataBase;
import mainprojects.magazine.products.Product;
import mainprojects.magazine.products.TypeOfProducts;
import mainprojects.magazine.service.Date;
import mainprojects.magazine.service.Stock;
import mainprojects.magazine.userinterface.ShopGUI;

public class DemoShop {

	public static void main(String[] args) throws Exception {

//		SplashScreen splash = SplashScreen.getSplashScreen();
//		Thread.sleep(3000);
//
//		Graphics2D g = splash.createGraphics();
//		g.setColor(Color.BLACK);
//		g.drawString("Loading. Please wait..", 70, 420);
//		splash.update();
//		Thread.sleep(2000);
//
//		splash.close();
//		VM Option -splash:src\mainprojects\magazine\images\splash.png

		Date date = new Date();
		Stock stock = new Stock();
		CustomerDataBase customerDB = new CustomerDataBase();

		Product p = new Product();
		p.setName("Apple");
		p.setBrand("Macintosh");
		p.setType(TypeOfProducts.FRUIT);
		p.setPrice(3);
		p.setId(1);
		stock.addMoreThanOne(p, 3);

		Product p1 = new Product();
		p1.setName("Pear");
		p1.setBrand("Leven");
		p1.setType(TypeOfProducts.FRUIT);
		p1.setPrice(5);
		stock.addMoreThanOne(p1, 2);

		Product p2 = new Product();
		p2.setName("Mandarin");
		p2.setBrand("European");
		p2.setType(TypeOfProducts.FRUIT);
		p2.setPrice(2);
		stock.addMoreThanOne(p2, 2);

		new ShopGUI(date, stock, customerDB);
	}

}
