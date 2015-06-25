package hw5.pair;

import java.io.*;

public class PairTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pair<String, Integer> pair = new Pair<>("One", 1);
		Pair<String, Integer> pair2 = new Pair<>("One", 2);

		// Test constructer() And toString()
		System.out.println(pair);

		// Test clone()
		Pair<String, Integer> cloned = pair.clone();
		System.out.println(cloned);

		// Test equals()
		System.out.println(pair.equals(cloned));

		// Test hashCode
		System.out.println(pair.hashCode() == cloned.hashCode());
		System.out.println(pair.hashCode() == pair2.hashCode());

		//Test Serialization
		try {
			FileOutputStream fileOut = new FileOutputStream("/tmp/pair.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(pair);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in /tmp/pair.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}

		Pair<String,Integer> deserializedPair = null;
		try {
			FileInputStream fileIn = new FileInputStream("/tmp/pair.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			deserializedPair = (Pair<String, Integer>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}
		
		System.out.println(deserializedPair);
		System.out.println(pair.equals(deserializedPair));

	}

}
