package hw5.pair;

import java.util.*;

public class Utils {

	@SuppressWarnings("unchecked")
	public static <K extends Comparable<K>, V> Collection<Pair<K, V>> sortPairCollection(Collection <Pair<K,V>> col){
		
		ArrayList<K> array = new ArrayList<>();
		ArrayList<Pair<K,V>> pairArray = new ArrayList<>();
		
		for (Iterator<Pair<K, V>> iterator = col.iterator(); iterator.hasNext();) {
			Pair<K, V> pair = (Pair<K, V>) iterator.next();
			array.add(pair.k());
		}
		array.sort(null);
		
		for (int i = 0; i < array.size(); i++) {
			K key = array.get(i);
			for (Iterator<?> iterator = col.iterator(); iterator.hasNext();) {
				Pair<K, V> pair = (Pair<K, V>) iterator.next();
				if (pair.k() == key) {
					pairArray.add(new Pair<K,V>(key,pair.v()));
				}
			}
		}
		
		return pairArray;
	}
	
	public static void main(String[] args) {
		Collection<Pair<String,Integer>> col = new ArrayList<>();
		col.add(new Pair<String,Integer>("Greg",1));
		col.add(new Pair<String,Integer>("Alex",2));
		col.add(new Pair<String,Integer>("Bobby",3));
		col.add(new Pair<String,Integer>("Zack",3));
		col.add(new Pair<String,Integer>("Abel",3));
		col.add(new Pair<String,Integer>("Prince",3));
		col.add(new Pair<String,Integer>("Darrel",3));
		
		System.out.println(col);
		ArrayList<Pair<String,Integer>> sorted = (ArrayList<Pair<String, Integer>>) Utils.sortPairCollection(col);
		System.out.println(sorted);
	}
}
