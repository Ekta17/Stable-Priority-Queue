/**
 * @author Ekta Arora
 * 
 *         Description of the file: 
 *         	This Java file is used to create stable min priority queue. 
 *         	It performs the following operations: 
 *         		- insertion of new element in the min queue. 
 *         		- extracting the minimum element from the min queue. 
 *         		- finding the minimum element of the min queue.
 *         
 *         TestCase:
 *         	To test this file, use MinPQTest.java file
 * 
 *         References: 
 *         	Following references were used to design the algorithm of the min priority queue: 
 *         		Introduction to Algorithms, Third Edition 
 *         			-By Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein
 *         			Chapter 6 Heapsort
 * 
 */

public class MinPQ {

	private int[] queue; // to hold the elements
	private int[] priorityQueue; // to hold counter, which is the insertion order of the elements
	private int maxSize; //Maximum number of elements which can be held by the Queue
	private int currentSize; //Current size, increments with every addition of a new element
	private int counter; // to keep track of insertion order
	
	//Getter and Setter
	public int getMaxSize() {
		return maxSize;
	}
	
	public int getCurrentSize() {
		return currentSize;
	}

	// Constructor
	public MinPQ(int size){

		maxSize=size;
		//Only if the max size is greater than zero, new queue is created. 
		if (getMaxSize() > 0) {
			this.currentSize = -1;
			this.counter = 0;
			queue = new int[maxSize];
			priorityQueue = new int[maxSize];
		}else
			maxSize=0;
	}

	/**
	 * Method to insert a new element into the min queue. 
	 * The new element may not be inserted into the queue due to the following reasons:
	 * 	- The size of the queue is full and no new element can be inserted.
	 * 	- The new element passed is less than the allowed minimum value of an integer in java.
	 * 
	 * @param newElement : element to be inserted in the queue
	 * @return 
	 * 		true: if new element is successfully inserted into the queue
	 * 		false: if new element could not be inserted into the queue 
	 */
	
	public boolean insert(int newElement) {
		if (currentSize == maxSize-1) {
			System.out.println("Queue is full");
			return false;
		}else if(newElement <= Integer.MIN_VALUE){
			System.out.println("error: new element smaller than current element");
			return false;
		}else {
			currentSize = currentSize + 1;
			counter = counter + 1;
			queue[currentSize] = Integer.MIN_VALUE;
			priorityQueue[currentSize] = Integer.MIN_VALUE;

			return HEAP_INCREASE_KEY(currentSize, newElement);
		}
	}
	
	/**
	 * Helper method to insert the new element in the queue at the right position 
	 * such that min priority queue property is maintained.
	 * 
	 * @param index : The position at which the new element should be inserted
	 * @param newElement : element to be inserted
	 * @return 
	 * 		true: if new element is successfully inserted into the queue
	 * 		false: if new element could not be inserted into the queue 
	 */
	private boolean HEAP_INCREASE_KEY(int index, int newElement) {
		
		queue[index] = newElement;
		priorityQueue[index] = counter;

		int temp = 0;

		while (index > 0 && queue[index / 2] > queue[index]) {
			temp = queue[index];
			queue[index] = queue[index / 2];
			queue[index / 2] = temp;

			temp = priorityQueue[index];
			priorityQueue[index] = priorityQueue[index / 2];
			priorityQueue[index / 2] = temp;

			index = index / 2;
		}

		displayQueue();
		
		return true;
	}

	/**
	 * Method to extract the minimum element from the min queue. 
	 * The minimum element is returned and the size of the queue is decreased by 1 element.
	 * 
	 * @return
	 * 		element: minimum element of the queue
	 */
	
	public int extractMin() {
		if (currentSize < 0) {
			System.out.println("Queue is empty");
			return -1;
		}
		
		//Min element is the head of the max queue
		int minValue = queue[0];

		int temp = 0;
		//This loop is to maintain the stability of the priority queue 
		//i.e. when two elements with same value are present in the queue, 
		//the FIFO property of the queue is checked and the 
		//element which was inserted first is extracted from the queue first. 
		//To check the insertion order of the elements, priorityQueue[] is referred. 
		for (int i = 1; i <= currentSize; i++) {
			if (minValue == queue[i]) {
				// found duplicate element
				if (priorityQueue[i] < priorityQueue[0]) {
					temp = queue[i];
					queue[i] = queue[0];
					queue[0] = temp;

					temp = priorityQueue[i];
					priorityQueue[i] = priorityQueue[0];
					priorityQueue[0] = temp;
				}
			}
		}

		queue[0] = queue[currentSize];
		currentSize = currentSize - 1;
		//Once the element is extracted, the min heapify helper function 
		//is used to maintain the property of heap in the min queue
		MIN_HEAPIFY(0);

		displayQueue();
		
		return minValue;
	}

	/**
	 * This a helper function used to maintain the property of the min priority queue designed as heap. 
	 * Such that the parent element is lesser than its two left and right children in the heap. 
	 * 
	 * @param index : position of the element to start the check of the  min priority queue property
	 */
	
	private void MIN_HEAPIFY(int index) {
		int left = 2 * index + 1;
		int right = 2 * index + 2;

		int minimum = Integer.MIN_VALUE;

		if (left <= currentSize && queue[left] < queue[index])
			minimum = left;
		else
			minimum = index;

		if (right <= currentSize && queue[right] < queue[minimum])
			minimum = right;

		int temp = 0;
		if (minimum != index) {
			temp = queue[index];
			queue[index] = queue[minimum];
			queue[minimum] = temp;

			temp = priorityQueue[index];
			priorityQueue[index] = priorityQueue[minimum];
			priorityQueue[minimum] = temp;

			MIN_HEAPIFY(minimum);
		}
	}

	/**
	 * Method to find the minimum element of the min queue. 
	 * The minimum element is stored at the head of the min queue. 
	 * Only the value is returned and the size of the queue is not affected by this operation
	 * 
	 * @return
	 * 		element : min element of the min priority queue
	 */
	
	public int minimum() {
		return queue[0];
	}

	/**
	 * The function to display the contents of the queue. 
	 */
	private void displayQueue() {
		if (currentSize <= -1) {
			System.out.println("Queue is Empty--> add elements first");
		} else {
			for (int i = 0; i <= currentSize; i++) {
				System.out.print(+queue[i]);
				if (i != currentSize)
					System.out.print(" , ");
			}
		}
		System.out.println("");
	}
}
