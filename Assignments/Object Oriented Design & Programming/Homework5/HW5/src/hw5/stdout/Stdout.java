package hw5.stdout;

public class Stdout {

	private Stdout() {

	}

	public void printline(String s) {
		// print s to System.out
		System.out.println(s);
	}

	public static Stdout getInstance() {
		return instance;
	}

	private static Stdout instance = new Stdout();
}
