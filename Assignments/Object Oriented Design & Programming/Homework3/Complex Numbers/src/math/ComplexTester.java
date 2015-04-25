package math;

public class ComplexTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Complex n1 = new Complex(5, 2);
		Complex n2 = new Complex(7, 4);
		Complex n3 = new Complex(5, 2);
		
		// addition
		Complex N1plusN2 = n1.add(n2);

		// subtraction
		Complex N1subN2 = n1.sub(n2);

		// multiplication
		Complex N1multN2 = n1.mult(n2);

		// division
		Complex N1divN2 = n1.div(n2);

		// conjugate
		n1 = new Complex(5, 2);
		Complex N1conjugate = n1.conj();

		// equal
		boolean N1equalN3 = n1.equals(n3);
		
		// equal
		boolean N1equalN2 = n1.equals(n2);

		System.out.print("n1 real number: ");
		System.out.println(n1.r());
		System.out.print("n1 imaginary number: ");
		System.out.println(n1.i());
		System.out.print("n2 real number: ");
		System.out.println(n2.r());
		System.out.print("n2 imaginary number: ");
		System.out.println(n2.i());
		System.out.printf("Addition of (%s) and (%s):\n", n1.toString(), n2.toString());
		System.out.println(N1plusN2.toString());
		System.out.printf("Subtraction of (%s) and (%s):\n", n1.toString(), n2.toString());
		System.out.println(N1subN2.toString());
		System.out.printf("Multiplication of (%s) and (%s):\n", n1.toString(), n2.toString());
		System.out.println(N1multN2.toString());
		System.out.printf("Division of (%s) and (%s):\n", n1.toString(), n2.toString());
		System.out.println(N1divN2.toString());
		System.out.println("Conugate of n1:");
		System.out.println(N1conjugate.toString());
		System.out.println("Equality of n1 and n3:");
		System.out.println(N1equalN3);
		System.out.println("Equality of n1 and n2:");
		System.out.println(N1equalN2);
		
	}

}
