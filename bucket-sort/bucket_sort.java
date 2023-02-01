package lab13;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class bucket_sort {
	static lab13.SinglyLinkedList.Node<Float> head;
	static lab13.SinglyLinkedList.Node<Float> sorted;

	public static void main(String[] args) {
		int array_size = 10;
		float[] A = new float[array_size];
		long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

		// an array of linked lists
		SinglyLinkedList<Float>[] B = new SinglyLinkedList[array_size];

		for (int i = 0; i < A.length; i++)
			B[i] = new SinglyLinkedList<>(); // each element of B array is a linked list object

		Float value = new Float(1.0);
		Float value_2 = new Float(2.0);
		Float value_3 = new Float(3.0);

		B[0].addFirst(value);
		B[0].addFirst(value_2);
		B[0].addFirst(value_3);
		B[0].insertion_sort();

		System.out.printf("%s\n", B[0]);

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < array_size; i++)
			A[i] = rand.nextFloat();
		System.out.println("Question 1");
		print_float_array(A);
		bucketSort(A, A.length);
		print_float_array(A);

		System.out.println("Part 2");

		/*for (int i = 10; i <= 100000000; i = i * 10) {
			System.out.println("İnput size is: " + i);
			float[] A_2 = new float[i];
			float[] A_3 = new float[i];
			A = new float[i];

			for (int a = 0; a < i; a++) {
				A[a] = rand.nextFloat();
				A_2[a] = A[a];
				A_3[a] = A[a];
			}
			start_time = System.nanoTime();
			bucketSort(A, A.length);
			end_time = System.nanoTime();
			elapsed_time = end_time - start_time;
			System.out.printf("Elapsed time in nanoseconds for Bucket sort: %d\n", elapsed_time);

			start_time = System.nanoTime();
			merge_sort(A_2, 0, i - 1); // couldnt find the size bug of counting sort, so put 100000000 for the code to
										// work
			end_time = System.nanoTime();
			elapsed_time = end_time - start_time;
			System.out.printf("Elapsed time in nanoseconds for merge sort: %d\n", elapsed_time);

			start_time = System.nanoTime();
			heap_sort(A_3);
			end_time = System.nanoTime();
			elapsed_time = end_time - start_time;
			System.out.printf("Elapsed time in nanoseconds for heap sort: %d\n", elapsed_time);

		}*/
		System.out.println("Part 1-D");
		float[] A_2 = new float[10000000];
		float[] A_3 = new float[10000000];
		A = new float[10000000];
		for (int j = 0; j < 10000000; j++) {
			A[j] = rand.nextFloat();
			A_2[j] = A[j];
			A_3[j] = A[j];
		}
		//bucketSort(A, A.length);
		// merge_sort(A_2, 0, 100000000-1);
		 heap_sort(A_3);
	}

	static void bucketSort(float arr[], int n) {
		if (n <= 0)
			return;

		// 1) Create n empty buckets
		@SuppressWarnings("unchecked")
		Vector<Float>[] buckets = new Vector[n];

		for (int i = 0; i < n; i++) {
			buckets[i] = new Vector<Float>();
		}

		// 2) Put array elements in different buckets
		for (int i = 0; i < n; i++) {
			float idx = arr[i] * n;
			buckets[(int) idx].add(arr[i]);
		}

		// 3) Sort individual buckets
		for (int i = 0; i < n; i++) {
			Collections.sort(buckets[i]);
		}

		// 4) Concatenate all buckets into arr[]
		int index = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < buckets[i].size(); j++) {
				arr[index++] = buckets[i].get(j);
			}
		}
	}

	// Implement bucket sort algorithm below
	public static void bucket_sort2(float[] a, SinglyLinkedList<Float>[] B) {
		int n = a.length;
		if (n <= 0)
			return;

		for (int i = 0; i < n; i++) {
			B[i] = new SinglyLinkedList<Float>();
		}
		for (int i = 0; i < n; i++) {
			float idx = a[i] * n;
			B[(int) idx].addFirst(a[i]);
			;
		}

		for (int i = 0; i < n; i++) {
			InsertionSort(B[i].getHead());
		}
		int index = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < B[i].size(); j++) {
				a[index++] = B[i].removeFirst();
			}
		}
	}

	static void InsertionSort(lab13.SinglyLinkedList.Node<Float> node) {
		// Initialize sorted linked list
		sorted = null;
		lab13.SinglyLinkedList.Node<Float> current = node;
		// Traverse the given linked list and insert every
		// node to sorted
		while (current != null) {
			// Store next for next iteration
			lab13.SinglyLinkedList.Node<Float> next = current.next;
			// insert current in sorted linked list
			sortedInsert(current);
			// Update current
			current = next;
		}
		// Update head_ref to point to sorted linked list
		head = sorted;
	}

	static void sortedInsert(lab13.SinglyLinkedList.Node<Float> current2) {
		/* Special case for the head end */
		if (sorted == null || (Float) sorted.element >= current2.element) {
			current2.next = sorted;
			sorted = current2;
		} else {
			lab13.SinglyLinkedList.Node<Float> current = sorted;
			/* Locate the node before the point of insertion */
			while (current.next != null && (Float) current.next.element < current2.element) {
				current = current.next;
			}
			current2.next = current.next;
			current.next = current2;
		}
	}

	// assumes that index i starts from 1
	public static int parent(int i) {
		return (int) Math.floor(i / 2);
	}

	// assumes that index i starts from 1
	public static int left(int i) {
		return 2 * i;
	}

	// assumes that index i starts from 1
	public static int right(int i) {
		return (2 * i + 1);
	}

	// assumes that index i starts from 1
	public static void max_heapify(float[] A, int array_size, int i) {
		int left_index, right_index, index_of_largest;
		float temp;

		left_index = left(i);
		right_index = right(i);

		if ((left_index <= array_size) && (A[left_index - 1] > A[i - 1]))
			index_of_largest = left_index;
		else
			index_of_largest = i;

		if ((right_index <= array_size) && (A[right_index - 1] > A[index_of_largest - 1]))
			index_of_largest = right_index;

		if (index_of_largest != i) {
			temp = A[i - 1];
			A[i - 1] = A[index_of_largest - 1];
			A[index_of_largest - 1] = temp;
			max_heapify(A, array_size, index_of_largest);
		}
	}

	public static void build_max_heap(float[] A) {
		int middle_index = (int) Math.floor(A.length / 2);
		int array_size = A.length;

		for (int i = middle_index; i >= 1; i--)
			max_heapify(A, array_size, i);
	}

	public static void heap_sort(float[] A) {
		float temp;
		int array_size = A.length;
		build_max_heap(A);

		for (int i = A.length; i >= 2; i--) {
			temp = A[0];
			A[0] = A[i - 1];
			A[i - 1] = temp;
			array_size--;
			max_heapify(A, array_size, 1);
		}
	}

	// indices p and r can start from 0
	public static void merge_sort(float[] A, int p, int r) {
		int q;

		if (p < r) {
			q = (int) Math.floor((p + r) / 2);
			merge_sort(A, p, q);
			merge_sort(A, q + 1, r);
			merge(A, p, q, r);
		}
	}

	public static void merge(float[] A, int p, int q, int r) {
		int n1, n2;
		int i, j;

		n1 = q - p + 1;
		n2 = r - q;

		float[] L = new float[n1];
		float[] R = new float[n2];

		for (i = 0; i < n1; i++)
			L[i] = A[p + i];

		for (i = 0; i < n2; i++)
			R[i] = A[q + i + 1];

		i = 0;
		j = 0;

		for (int k = p; k <= r; k++) {
			if (i >= n1) // the left array finished, copy from right array
			{
				A[k] = R[j];
				j++;
				continue;
			}

			if (j >= n2) // the right array finished, copy from left array
			{
				A[k] = L[i];
				i++;
				continue;
			}

			if (L[i] <= R[j]) {
				A[k] = L[i];
				i++;
			} else {
				A[k] = R[j];
				j++;
			}
		}
	}

	// prints the elements of the array A on the screen
	public static void print_float_array(float[] A) {
		System.out.printf("[");
		for (int i = 0; i < A.length - 1; i++) {
			System.out.printf("%f, ", A[i]);
		}

		System.out.printf("%f]\n", A[A.length - 1]);

	}

	private static class Node<E> {

		/** The element stored at this node */
		private E element; // reference to the element stored at this node

		/** A reference to the subsequent node in the list */
		private Node<E> next; // reference to the subsequent node in the list

		/**
		 * Creates a node with the given element and next node.
		 *
		 * @param e the element to be stored
		 * @param n reference to a node that should follow the new node
		 */
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		// Accessor methods
		/**
		 * Returns the element stored at the node.
		 * 
		 * @return the element stored at the node
		 */
		public E getElement() {
			return element;
		}

		public void setElement(E e) {
			element = e;
		}

		/**
		 * Returns the node that follows this one (or null if no such node).
		 * 
		 * @return the following node
		 */
		public Node<E> getNext() {
			return next;
		}

		// Modifier methods
		/**
		 * Sets the node's next reference to point to Node n.
		 * 
		 * @param n the node that should follow this one
		 */
		public void setNext(Node<E> n) {
			next = n;
		}
	} // ----------- end of nested Node class -----------

}
