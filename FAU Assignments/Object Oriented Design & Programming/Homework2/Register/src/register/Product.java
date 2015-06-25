package register;

/**
 * A class for the Product object.
 * */
public class Product {
	
	/**
	 * Constructs a Product object from the given parameters.
	 * @param name the name of the product.
	 * @param price the price of the product.
	 * @param upc the UPC number of the product.
	 * */
	public Product(String name, float price, int upc){
		this.name = name;
		this.price = price;
		this.upc = upc;
	}
	
	/**
	 * Retrieves the name of the product.
	 * @return the name of the product.
	 * */
	public String getName (){
		return this.name;
	}
	
	/**
	 * Retrieves the price of the product.
	 * @return the price of the product.
	 * */
	public float getPrice (){
		return this.price;
	}
	
	/**
	 * Retrieves the UPC number of the product.
	 * @return the UPC number of the product.
	 * */
	public int getUPC (){
		return this.upc;
	}
	 
	private String name;
	private float price;
	private int upc;
}
