package lab8;

import java.util.Arrays;
import java.util.Random;

public class heap {
	static int heap_size;

	public static void main(String[] args) {
		int array_size = 10;
		int[] array = new int[array_size];
		long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < array_size; i++)
			array[i] = rand.nextInt(100);

		print_array(array);
		heap_sort(array);
		print_array(array);
		
		// part 3(a)
		/*for(int k=4;k<=67108864;k=k*4) {
		array_size = k;
		int[] array_2 = new int[array_size]; // will be used for insertion sort
		int[] array_3 = new int[array_size]; // will be used for merge sort

		for (int i = 0; i < array_size; i++) {
			array_2[i] = rand.nextInt(100);
			array_3[i] = array_2[i]; // make a copy of array_2
		}

		start_time = System.nanoTime();
		merge_sort(array_3, 0, array_size - 1);
		end_time = System.nanoTime();
		elapsed_time = end_time - start_time;
		System.out.printf("Elapsed time in nanoseconds for merge sort when "+k+" integers are sorted: %d\n",
				elapsed_time);
		
		start_time = System.nanoTime();
		heap_sort(array_2);
		end_time = System.nanoTime();
		elapsed_time = end_time - start_time;
		System.out.printf("Elapsed time in nanoseconds for heap sort when "+k+" integers are sorted: %d\n",
				elapsed_time);
		}*/
		array_size = 67108864;
		int[] array_2 = new int[array_size]; // will be used for insertion sort
		int[] array_3 = new int[array_size]; // will be used for merge sort

		for (int i = 0; i < array_size; i++) {
			array_2[i] = rand.nextInt(100);
			array_3[i] = array_2[i]; // make a copy of array_2
		}

		start_time = System.nanoTime();
		merge_sort(array_3, 0, array_size - 1);
		end_time = System.nanoTime();
		elapsed_time = end_time - start_time;
		System.out.printf("Elapsed time in nanoseconds for merge sort when "+67108864+" integers are sorted: %d\n",
				elapsed_time);
		/* = System.nanoTime();
		heap_sort(array_2);
		end_time = System.nanoTime();
		elapsed_time = end_time - start_time;
		System.out.printf("Elapsed time in nanoseconds for heap sort when "+67108864+" integers are sorted: %d\n",
				elapsed_time);*/
	}

	public static int Left(int i) {
		return 2 * i;
	}

	public static int Right(int i) {
		return 2 * i + 1;
	}
	public static int Parent(int i) {
		return  (int) Math.floor(i/ 2);
	}
	
	public static void MaxHeapify(int[] A, int i) {
		int left = Left(i);
		int right = Right(i);
		int largest;
		if(left<=heap_size && A[left]>A[i]) {
			largest = left;
		}else {
			largest = i;
		}
		if(right<=heap_size && A[right]>A[largest]) {
			largest = right;
		}
		if(largest != i) {
			int temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			MaxHeapify(A,largest);
		}
	}

	// Implement heap sort algorithm below
	public static void heap_sort(int[] A) {
		buildMaxHeap(A);
		for(int i=A.length-1 ; 1<=i;i--) {
			int temp = A[0];
			A[0] = A[i];
			A[i] = temp;
			heap_size --;
			MaxHeapify(A,0);
		}
		
	}

	public static void buildMaxHeap(int[] A) {
		heap_size = A.length-1;
		for (int i=(int) Math.floor(A.length/2);0<=i;i--) {
			MaxHeapify(A,i);
		}
	}

	// indices p and r can start from 0
	public static void merge_sort(int[] A, int p, int r) {
		int q;

		if (p < r) {
			q = (int) Math.floor((p + r) / 2);
			merge_sort(A, p, q);
			merge_sort(A, q + 1, r);
			merge(A, p, q, r);
		}
	}

	// Part 2(a)
	public static void merge(int[] A, int p, int q, int r) {
		int n1, n2;
		int i, j;

		n1 = q - p + 1;
		n2 = r - q;

		int[] L = new int[n1];
		int[] R = new int[n2];

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
	public static void print_array(int[] A) {
		System.out.printf("[");
		for (int i = 0; i < A.length - 1; i++) {
			System.out.printf("%d, ", A[i]);
		}

		System.out.printf("%d]\n", A[A.length - 1]);

	}
}
