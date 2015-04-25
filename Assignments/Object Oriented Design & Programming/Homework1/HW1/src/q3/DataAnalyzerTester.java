package q3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/** Class to Test DataAnalyzer */
public class DataAnalyzerTester {

	/** main function
     * @param args the command line arguments, stored in a String array
     */
	public static void main(String[] args) {
		try{
			Scanner in = new Scanner(System.in);
			PrintWriter writer;
			LinkedList<Integer> nums = new LinkedList<Integer>();
			
			System.out.println("How many numbers do you wish to analyze: ");
			int numbersToAnalyze = in.nextInt();
			
			for (int i = 0; i < numbersToAnalyze; i++){
				nums.add(in.nextInt());
			}
			
			DataAnalyzer analyzer = new DataAnalyzer(nums);
			System.out.print("\nMin: ");
			System.out.println(analyzer.min());
			System.out.print("Max: ");
			System.out.println(analyzer.max());
			System.out.print("Average: ");
			System.out.println(analyzer.average());
			System.out.println("Enter File Name to save as. (omit .txt)");
			
			try {
				writer = new PrintWriter(in.next() + ".txt", "UTF-8");
				writer.print("\nMin: ");
				writer.println(analyzer.min());
				writer.print("\nMax: ");
				writer.println(analyzer.max());
				writer.print("\nAverage: ");
				writer.println(analyzer.average());
				writer.close();
			} catch (FileNotFoundException e) {
				System.out.println(e);
				System.out.println("\nTry again.");
				main(args);
			} catch (UnsupportedEncodingException e) {
				System.out.println(e);
				System.out.println("\nTry again.");
				main(args);
			}
			finally{
				in.close();
			}
		}
		catch (InputMismatchException e){
			System.out.println(e);
			System.out.println("\nTry again.");
			main(args);
		}
	}

}
