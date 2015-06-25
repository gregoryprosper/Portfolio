package q2;

public class GreeterTester {
	
	/** main function
     * @param args the command line arguments, stored in a String array
     */
	public static void main(String[] args) {
		
		Greeter g = new Greeter("World");
		Greeter g2 = g.createQualifiedGreeter("Beutiful");
		
		System.out.println(g2.sayHello());
		
		g.swapNames(g2);
		System.out.println(g.sayHello());

	}

}
