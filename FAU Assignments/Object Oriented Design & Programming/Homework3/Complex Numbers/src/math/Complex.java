package math;

/**
 * Class for representing complex numbers
 */
public class Complex {

	/**
	 * Constructor that takes real and imaginary numbers
	 * 
	 * @param real
	 *            real number
	 * @param imaginary
	 *            imaginary number
	 */
	Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	/**
	 * Constructor that takes real and sets imaginary number to 0
	 * 
	 * @param real
	 *            real number
	 */
	Complex(double real) {
		this.real = real;
		this.imaginary = 0;
	}

	/**
	 * Returns complex number as a string
	 * 
	 * @return Complex number
	 */
	public String toString() {
		String s;
		if (this.imaginary > 0) {
			s = String.format("%.2f + %.2fi", this.real, this.imaginary);
		} else if (this.imaginary == 0) {
			s = String.format("%.2f", this.real);
		} else {
			s = String.format("%.2f - %.2fi", this.real, this.imaginary * -1);
		}

		return s;
	}

	/**
	 * Returns the complex number's real number value
	 * 
	 * @return Complex number's real number
	 */
	public double r() {
		return this.real;
	}

	/**
	 * Returns the complex number's imaginary number value
	 * 
	 * @return Complex number's imaginary number
	 */
	public double i() {
		return this.imaginary;
	}

	/**
	 * Returns the sum between this complex number and another
	 * 
	 * @return Complex number that is the sum between this complex number and
	 *         another
	 */
	public Complex add(Complex other) {
		double realTemp = this.real + other.real;
		double imaginaryTemp = this.imaginary + other.imaginary;
		return new Complex(realTemp, imaginaryTemp);
	}

	/**
	 * Returns the difference between this complex number and another
	 * 
	 * @return Complex number that is the difference between this complex number
	 *         and another
	 */
	public Complex sub(Complex other) {
		double realTemp = this.real - other.real;
		double imaginaryTemp = this.imaginary - other.imaginary;
		return new Complex(realTemp, imaginaryTemp);
	}

	/**
	 * Returns the Conjugate of this complex number
	 * 
	 * @return Conjugate of this complex number
	 */
	public Complex conj() {
		double imaginaryTemp = this.imaginary * -1;
		return new Complex(this.real, imaginaryTemp);
	}

	/**
	 * Returns the multiplication between this complex number and another
	 * 
	 * @return Complex number that is the multiplication between this complex
	 *         number and another
	 */
	public Complex mult(Complex other) {
		double first = this.real * other.real;
		double outerImaginary = this.real * other.imaginary;
		double innerImaginary = this.imaginary * other.real;
		double last = this.imaginary * other.imaginary * -1;

		return new Complex(first + last, outerImaginary + innerImaginary);
	}

	/**
	 * Returns the division between this complex number and another
	 * 
	 * @return Complex number that is the division between this complex number
	 *         and another
	 * @throws IllegalArgumentException
	 *             if d.real == 0
	 */
	public Complex div(Complex other) {
		Complex conjugate = other.conj();
		Complex n = new Complex(this.real, this.imaginary).mult(conjugate);
		Complex d = new Complex(other.real, other.imaginary).mult(conjugate);
		if (d.real == 0) {
			throw new IllegalArgumentException("Argument 'divisor' is 0");
		}
		return new Complex(n.real / d.real, n.imaginary / d.real);

	}

	/**
	 * Test to see if this complex number is equal to another
	 * 
	 * @return boolean that is true if this complex number is equal to another
	 */
	public boolean equals(Complex other) {
		if (Math.abs(this.real - other.real) < Complex.MARGIN
				&& Math.abs(this.imaginary - other.imaginary) < Complex.MARGIN) {
			return true;
		} else
			return false;
	}

	private final double real;
	private final double imaginary;
	private static final double MARGIN = 0.000000001;
}
