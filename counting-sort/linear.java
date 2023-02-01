package lab11;

import java.util.Arrays;
import java.util.Random;

public class linear {
	public static void main(String[] args) {
		int array_size = 10;// 67108864;
		int[] array = new int[array_size];
		int[] B = new int[array_size];
		long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < array_size; i++)
			array[i] = rand.nextInt(10);
		System.out.println("part 1-B");
		print_array(array);
		counting_sort(array, B, array_size);
		print_array(B);
		System.out.println("Part 1-C & D");// in part c, instead of 10000000, we have i as input size
		/*
		 * for (int i = 10; i <= 100000000; i = i * 10) {
		 * System.out.println("İnput size is: " + i); array = new int[i]; int[] array_2
		 * = new int[i]; int[] array_3 = new int[i]; B = new int[i]; for (int a = 0; a <
		 * i; a++) { array[a] = rand.nextInt(10000000); array_2[a] = array[a];
		 * array_3[a] = array[a]; }
		 * 
		 * start_time = System.nanoTime(); counting_sort(array, B, 10000000); end_time =
		 * System.nanoTime(); elapsed_time = end_time - start_time;
		 * System.out.printf("Elapsed time in nanoseconds for counting sort: %d\n",
		 * elapsed_time);
		 * 
		 * start_time = System.nanoTime(); heap_sort(array_2); end_time =
		 * System.nanoTime(); elapsed_time = end_time - start_time;
		 * System.out.printf("Elapsed time in nanoseconds for heap sort: %d\n",
		 * elapsed_time);
		 * 
		 * start_time = System.nanoTime(); merge_sort(array_3, 0, i - 1); end_time =
		 * System.nanoTime(); elapsed_time = end_time - start_time;
		 * System.out.printf("Elapsed time in nanoseconds for merge sort: %d\n",
		 * elapsed_time);
		 * 
		 * }
		 */
//part e
		System.out.println("Part 1-E");
		array = new int[100000000];
		int[] array_2 = new int[100000000];
		int[] array_3 = new int[100000000];
		B = new int[100000000];
		for (int j = 0; j < 100000000; j++) {
			array[j] = rand.nextInt(10000000);
			array_2[j] = array[j];
			array_3[j] = array[j];
		}

	//	counting_sort(array, B, 10000000);
//merge_sort(array,0,100000000-1);
heap_sort(array);

	}

	// Implement counting sort algorithm below
	public static void counting_sort(int[] A, int[] B, int k) {
		int[] C = new int[k];
		for (int i = 0; i < k; i++) {
			C[i] = 0;
		}
		for (int j = 0; j < A.length; j++) {
			C[A[j]] = C[A[j]] + 1;
		}
		for (int i = 1; i < k; i++) {
			C[i] = C[i] + C[i - 1];
		}
		for (int j = A.length - 1; j > 0; j--) {
			B[C[A[j]] - 1] = A[j];
			C[A[j]] = C[A[j]] - 1;
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
	public static void max_heapify(int[] A, int array_size, int i) {
		int left_index, right_index, index_of_largest;
		int temp;

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

	public static void build_max_heap(int[] A) {
		int middle_index = (int) Math.floor(A.length / 2);
		int array_size = A.length;

		for (int i = middle_index; i >= 1; i--)
			max_heapify(A, array_size, i);
	}

	public static void heap_sort(int[] A) {
		int temp;
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
	public static void merge_sort(int[] A, int p, int r) {
		int q;

		if (p < r) {
			q = (int) Math.floor((p + r) / 2);
			merge_sort(A, p, q);
			merge_sort(A, q + 1, r);
			merge(A, p, q, r);
		}
	}

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
