package register;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class for the Register object.
 * */
public class Register {

	/**
	 * Constructs a Register object.
	 * */
	public Register (){
		System.out.print("Register Initialized. Enter UPC numbers or Enter 'pay' to complete transaction\n");
	}
	
	/**
	 * Scans for UPC numbers until user inputs 'pay'.
	 * */
	public void scan (){
		Scanner in = new Scanner(System.in);
		
		while (in.hasNext()) {
			if (in.hasNextInt()) {
				int upc = in.nextInt();
				if (upc >= 100 && upc <= 115) {
					this.cart.add(this.inventory.getProduct(upc));
				}
				else {
					System.out.print("Invalid UPC number, must be within 100 - 115 \n");
				}
			}
			else if (in.next().equalsIgnoreCase("pay")) {
				this.printReciept();
				break;
			}
			else{
				System.out.print("Invalid Input. Enter UPC number or 'pay'.\n");
			}
		}
		
		in.close();
	}
	
	/**
	 * Prints the total to the console.
	 * */
	public void printTotal (){
		float total = 0;
		for (Product p  : this.cart) {
			total += p.getPrice();
		}
		System.out.printf("\nTotal: $%.2f", total);
	}
	
	/**
	 * Prints Every item in cart to console then prints total to console.
	 * */
	public void printReciept (){
		System.out.print("Reciept:\n");
		System.out.printf("%3s %15s %10s\n","UPC","Item","Price");
		
		if (this.cart.isEmpty()) {
			System.out.print("(No Items in Cart)");
		}
		else {
			for (Product p  : this.cart) {
				System.out.printf("%3d %15s %10.2f \n", p.getUPC(),p.getName(),p.getPrice());
			}
		}
		this.printTotal();
	}
	
	private ArrayList<Product> cart = new ArrayList<>();
	private Inventory inventory = new Inventory();
}
