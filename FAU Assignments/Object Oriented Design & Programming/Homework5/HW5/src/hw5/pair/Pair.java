package hw5.pair;

import java.io.Serializable;

public class Pair<K,V> implements Cloneable,Serializable{

	private K key;
	private V value;
	
	public Pair(K k, V v){
		this.key = k;
		this.value = v;
	}
	
	public K k(){
		return this.key;
	}
	
	public V v(){
		return this.value;
	}

	@Override
	public String toString() {
		return "Pair [key=" + key + ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		int result = 0;
		result += ((key == null) ? 0 : key.hashCode());
		result += ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Pair<K, V> other = (Pair<K, V>) obj;
		
		if (!this.key.equals(other.key)) {
			return false;
		}
		else if (!this.value.equals(other.value)) {
			return false;
		}
		else{
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pair<K,V> clone() {
		// TODO Auto-generated method stub
		try {
			return (Pair<K,V>) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	

}
