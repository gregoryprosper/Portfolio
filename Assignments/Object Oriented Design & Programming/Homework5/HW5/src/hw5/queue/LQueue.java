package hw5.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LQueue<E> implements MyQueue<E> {
	
	private LinkedList<E> list;
	
	
	public LQueue(){
		this.list = new LinkedList<E>();
	}

	/**
	 * Returns object at head.
	 * 
	 * @return the first object in queue
	 * @precondition size() > 0
	 */
	@Override
	public E head() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return this.list.getFirst();
	}

	/**
	 * Returns and Removes object at head.
	 * 
	 * @return the object that has been removed from the queue
	 * @precondition size() > 0
	 */
	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		E item = this.list.removeFirst();
		return item;
	}

	/**
	 * Adds object to end of queue.
	 */
	@Override
	public void Enqueue(E e) {
		// TODO Auto-generated method stub
		this.list.add(e);
	}

	/**
	 * Returns size of queue.
	 * 
	 * @return size of the queue.
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.list.size();
	}

	/**
	 * Checks to see if queue is empty.
	 * 
	 * @return true if queue is empty.
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.list.isEmpty();
	}

	/**
	 * Adds all objects in Collection to this queue.
	 */
	@Override
	public void addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		Iterator<? extends E> iter = c.iterator();
		
		while (iter.hasNext()) {
			E item = (E) iter.next();
			this.Enqueue(item);
			
		}
	}

}
