package hw5.queue;

import java.util.ArrayList;

public class QueueTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LQueue<String> queue = new LQueue<>();
		
		//Test Enqueue() and Head()
		queue.Enqueue("Enqueue() and Head() worked");
		System.out.println(queue.head());
		
		//Test Dequeue
		queue.dequeue();
		if (queue.isEmpty()) {
			System.out.println("Dequeue and isEmpty() worked");
		}
		
		//Test Size
		queue.Enqueue("1");
		queue.Enqueue("2");
		queue.Enqueue("3");
		System.out.print(queue.size());
		System.out.println(" Size() worked");
		
		//Test addAll
		ArrayList<String> stringArrayList = new ArrayList<>();
		stringArrayList.add("4");
		stringArrayList.add("5");
		stringArrayList.add("6");
		
		queue.addAll(stringArrayList);
		
		while (!queue.isEmpty()) {
			System.out.print(queue.dequeue());
		}
		System.out.println(" AddAll() worked");

	}

}
