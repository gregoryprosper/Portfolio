package q3;

import java.util.ArrayList;
import java.util.LinkedList;

/** Object to analyze data */
public class DataAnalyzer {
	
	/** Constructor for DataAnalyzer 
	 * @param numList LinkedList with numbers to be analyzed
	 */
	public DataAnalyzer(LinkedList<Integer> numList) {
		
		Integer nums[] = numList.toArray(new Integer[numList.size()]);
		
		for (int i = 0; i < nums.length; i++){
			this.list.add(nums[i]);
		}
	}
	
	/** Calculates minimum number in list 
	 * @return minimum number in list
	 */
	public int min() {
		int num = this.list.get(0);
		for (int i = 0; i < this.list.size(); i++){
			if (num > this.list.get(i)){
				num = this.list.get(i);
			}
		}
		return num;
	}
	
	/** Calculates maximum number in list 
	 * @return maximum number in list
	 */
	public int max() {
		int num = this.list.get(0);
		for (int i = 0; i < this.list.size(); i++){
			if (num < this.list.get(i)){
				num = this.list.get(i);
			}
		}
		return num;
	}
	
	/** Calculates average number in list 
	 * @return average number in list
	 */
	public double average() {
		double total = 0;
		for (int i = 0; i < this.list.size(); i++){
			total += this.list.get(i);
		}
		return total / (double) this.list.size();
	}
	
	ArrayList<Integer> list = new ArrayList<>();
}
