package q1;

import java.util.InputMismatchException;
import java.util.Scanner;


/** Computes the Fibonacci Series */
public class Fib {

	/** Constructor for Fib
	 * @param f0 is the first number in series
	 * @param f1 is the first number in series
	 */
	public Fib(int f0, int f1) 
	{
		this.f0 = f0;
		this.f1 = f1;
	}


	/** computes F(n) using an ***iterative*** algorithm
	 * @param n is the index for the series. Series is printed to index n
	 * @return returns F(n)
	 */
	// use instance variables that store F(0) and F(1).
	// check parameter and throw exception if n < 0. Don't worry about arithmetic overflow.
	public int f(int n){
		this.f0 = 0;
		this.f1 = 1;
		if (n < 0) throw new IllegalArgumentException("n must be positive");
		if (n == this.f0) return this.f0;
		if (n == this.f1) return this.f1;
		
		int sum = 0;
		for (int i = 2; i <= n; i++){
			sum = this.f0 + this.f1;
			this.f0 = this.f1;
			this.f1 = sum;
		}
		
		return sum;
	}

	/** computes F(n) using the ***recursive*** algorithm
	 * @param n is the index for the series. Series is printed to index n
	 * @return returns F(n)
	 */
	// use instance variables that store F(0) and F(1).
	// check parameter and throw exception if n < 0. Don't worry about arithmetic overflow.
	public int fRec(int n) {
		if (n < 0) throw new IllegalArgumentException("n must be positive");
		
		if (n == 0){
			return 0;
		}
		if (n == 1){
			return 1;
		}
		
		return fRec(n-1) + fRec(n-2);
	}


	/** main function
     * @param args the command line arguments, stored in a String array
     */
	public static void main(String[] args) 
	{
		try{
			// get numbers F(0) and F(1) from args[0] and args[1].
			// use either the Scanner class or Integer.parseInt(args[...])
			// you must handle possible exceptions !
			
			Scanner in = new Scanner(System.in);
			
			System.out.print("Enter F(0): ");
			int arg1 = in.nextInt();
			System.out.print("Enter F(1): ");
			int arg2 = in.nextInt();
	
			// get n from args[2]:
			System.out.print("Enter F(n): ");
			int n = in.nextInt();
			in.close();
			
			// create a Fib object with params F(0) and F(1)
			Fib obj = new Fib(arg1,arg2);
	
	
			// calculate F(0), ..., F(n) and display them with System.out.println(...) using
			// the iterative methode f(i)
			System.out.printf("F(%d) using iterative method\n",n);
			for (int i = 0; i <= n; i++){
				System.out.print(obj.f(i));
				System.out.print(" ");
			}
			System.out.print("\n");
	
			// calculate F(0), ..., F(n) and display them with System.out.println(...) using
			// the recursive methode fRec(i)
			System.out.printf("F(%d) using recursive method\n",n);
			for (int i = 0; i <= n; i++){
				System.out.print(obj.fRec(i));
				System.out.print(" ");
			}
		}
		catch (IllegalArgumentException ex){
			System.out.println(ex);
			System.out.println("\nTry again.");
			main(args);
		}
		catch (InputMismatchException ex){
			System.out.println(ex);
			System.out.println("\nTry again.");
			main(args);
		}
	}

	// instance variables store F(0) and F(1):
	int f0;
	int f1;
	
}