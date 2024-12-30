package project;

public class PriorityQueue {
	private int maxsize;
	private Process[] queueArray;
	private int size;

	public PriorityQueue(int nsize) {
		queueArray = new Process[nsize];
		maxsize = nsize;
		size = 0;
	}

	public void enqueue(Process p) {
		if (IsFull()) {
			System.out.println("Queue is full! Cannot enqueue process with ID " + p.getId());
			return;
		}

		int j;
		// If the queue is empty, add the first process
		if (size == 0) {
			queueArray[size++] = p;
			return;
		}

		// Move elements that are greater than the new process to the right
		for (j = size - 1; j >= 0; j--) {
			// Compare by memory size (smallest first)
			if (queueArray[j].getSize() > p.getSize()) {
				queueArray[j + 1] = queueArray[j]; // Shift element to the right
			}
			// If memory size is the same, compare by timeout (shorter timeout first)
			else if (queueArray[j].getSize() == p.getSize() && queueArray[j].getTimeOut() > p.getTimeOut()) {
				queueArray[j + 1] = queueArray[j]; // Shift element to the right
			} else {
				break; // Break if the correct position is found
			}
		}

		// Insert the new process at the correct position
		queueArray[j + 1] = p;
		size++; // Increment the size after insertion
	}

	// Dequeue the process (remove and return the highest priority process)
	public Process dequeue() {
		if (IsEmpty()) {
			System.out.println("Queue is empty! Cannot dequeue.");
			return null; // Return null to indicate failure
		}

		// Get the highest priority process (front of the queue)
		Process process = queueArray[0];

		// Shift all elements left to remove the first element
		for (int i = 0; i < size - 1; i++) {
			queueArray[i] = queueArray[i + 1]; // Shift left
		}

		size--; // Decrease the size after dequeuing
		return process; // Return the highest priority process
	}

	// Peek the highest priority process without removing it
	public Process peek() {
		if (IsEmpty()) {
			System.out.println("Queue is empty! Cannot peek.");
			return null; // Return null to indicate failure
		}
		return queueArray[0]; // Return the front of the queue
	}
	public Process peekatIndex(int i) {
		if (IsEmpty()) {
			System.out.println("Queue is empty! Cannot peek.");
			return null; // Return null to indicate failure
		}
		
		return queueArray[i];
		
	}
	// Check if the queue is empty
	public boolean IsEmpty() {
		return size == 0;
	}

	// Check if the queue is full
	public boolean IsFull() {
		return size == maxsize;
	}

	// Get the current size of the queue
	public int size() {
		return size;
	}
}
