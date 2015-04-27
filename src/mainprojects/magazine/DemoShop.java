package mainprojects.magazine;

import mainprojects.magazine.justconstructor.Product;
import mainprojects.magazine.serviceclass.Date;
import mainprojects.magazine.serviceclass.Shop;
import mainprojects.magazine.serviceclass.ShopUI;
import mainprojects.magazine.serviceclass.TypeOfProducts;
import mainprojects.magazine.serviceclass.mainclass.Stock;
import mainprojects.magazine.serviceclass.mainclass.StockHash;

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

//		Date date = new Date();
//		Stock stock = new Stock();
//		Shop productshop = new Shop(date, stock);
//
//		Product p = new Product();
//		p.setName("Apple");
//		p.setBrand("Macintosh");
//		p.setType(TypeOfProducts.FRUIT);
//		p.setPrice(3);
//		stock.addMoreThanOne(p, 300);
//
//		Product p1 = new Product();
//		p1.setName("Pear");
//		p1.setBrand("Leven");
//		p1.setType(TypeOfProducts.FRUIT);
//		p1.setPrice(5);
//		stock.addMoreThanOne(p1, 200);
//
//		Product p2 = new Product();
//		p2.setName("Mandarin");
//		p2.setBrand("European");
//		p2.setType(TypeOfProducts.FRUIT);
//		p2.setPrice(2);
//		stock.addMoreThanOne(p2, 400);
//
//		ShopUI ui = new ShopUI(productshop, stock);

		StockHash sh = new StockHash();

		Product p = new Product();
		p.setName("Apple");
		p.setBrand("Test");
		p.setType(TypeOfProducts.FRUIT);
		p.setPrice(5);
		p.setId(1);

		sh.add(p);

//		System.out.println(p.getName());
//		System.out.println(p.getBrand());
//		System.out.println(p.getType());
//		System.out.println(p.getPrice());
//		System.out.println(p.getId());

	}

}
