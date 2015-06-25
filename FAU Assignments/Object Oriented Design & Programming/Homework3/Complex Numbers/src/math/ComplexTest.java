package math;

import org.junit.* ;
import static org.junit.Assert.* ;

public class ComplexTest {

	@Test
	public void testEqualsComplex() {
		System.out.println("run test equals()");
		double a = 1, b = 2;
		Complex x = new Complex(a, b);
		Complex y = new Complex(a, b);
		assertTrue(x.equals(y));
	}

	@Test
	public void testToString() {
		System.out.println("run test toString()");
		double a = 1, b = 2;
		Complex x = new Complex(a, b);
		assertEquals("1.00 + 2.00i", x.toString());
	}

	@Test
	public void testR() {
		System.out.println("run test r()");
		double a = 1, b = 2;
		Complex x = new Complex(a, b);
		assert a == x.r();
	}

	@Test
	public void testI() {
		System.out.println("run test i()");
		double a = 1, b = 2;
		Complex x = new Complex(a, b);
		assert b == x.i();
	}

	@Test
	public void testAdd() {
		System.out.println("run test add()");
		double a = 1, b = 2, c = -3, d = 4;
		double e = a + c, f = b + d;
		Complex x = new Complex(a, b);
		Complex y = new Complex(c, d);

		Complex w = x.add(y);

		Complex z = new Complex(e, f);

		// set up Complex objects
		// test condition using the Complex equals() method:
		assertTrue(z.equals(w));
	}

	@Test
	public void testSub() {
		System.out.println("run test sub()");
		double a = 1, b = 2, c = -3, d = 4;
		double e = a - c, f = b - d;
		Complex x = new Complex(a, b);
		Complex y = new Complex(c, d);

		Complex w = x.sub(y);

		Complex z = new Complex(e, f);

		// set up Complex objects
		// test condition using the Complex equals() method:
		assertTrue(z.equals(w));
	}

	@Test
	public void testConj() {
		System.out.println("run test conj()");
		double a = 1, b = 2;
		Complex x = new Complex(a, b);

		assertTrue(x.conj().equals(new Complex(1, -2)));
	}

	@Test
	public void testMult() {
		System.out.println("run test sub()");
		Complex x = new Complex(2, 3);
		Complex y = new Complex(4, 5);

		Complex w = x.mult(y);

		Complex z = new Complex(-7,22);

		// set up Complex objects
		// test condition using the Complex equals() method:
		assertTrue(z.equals(w));
	}

	@Test
	public void testDiv() {
		System.out.println("run test div()");
		Complex x = new Complex(4, 2);
		Complex y = new Complex(3, -1);
		Complex z = x.div(y);
		// set up Complex objects
		// test condition using the Complex equals() method:
		assertTrue(z.equals(new Complex(1,1)));
	}

}
