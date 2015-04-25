package register;

import java.util.ArrayList;

/**
 * A class for the Inventory object.
 * */
public class Inventory {
	
	/**
	 * Constructs a Inventory object.
	 * */
	public Inventory (){
		String[] names = {"Bread","Cookies","Pantry","Couch","T-Shirt","Tv","Hammer","Shoes","Drawer","Basket","Belt"
				,"Toothbrush","Radio","Lotion","Slippers","Curtains"};
		
		float[] prices = {(float) 2.34,(float) 1.12,(float) 13.23,(float) 532,(float) 5.99,(float) 1200,(float) 17.78,
				(float) 34.99,(float) 32.89,(float) 2.12,(float) 3.45,(float) 7.99,(float) 133,(float) 3.55,(float) 13.44,
				(float) 198.10};
		
		//Creates products and adds them to products ArrayList
		for (int i = 0; i < prices.length; i++) {
			Product p = new Product(names[i],prices[i],i+100);
			this.products.add(p);
		}
	}
	
	/**
	 * Retrieves a product from the Inventory by UPC number.
	 * @param upc the UPC number of the product to be searched for.
	 * @return a product from the inventory with the matching UPC number.
	 * */
	public Product getProduct (int upc){
		for (Product p : this.products) {
			if (p.getUPC() == upc) {
				return p;
			}
		}
		Product p = new Product("",0,0);
		return p;
	}
	
	private ArrayList<Product> products = new ArrayList<>();
}
