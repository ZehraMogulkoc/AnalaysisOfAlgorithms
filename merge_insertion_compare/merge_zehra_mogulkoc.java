package lab2;

import java.util.Arrays;
import java.util.Random;

public class merge {
	public static void main(String[] args) {
		int array_size =  1048576;
		int[] array = new int[array_size];
		long start_time, end_time, elapsed_time, elapsed_time_insertion, elapsed_time_merge;

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < array_size; i++)
			array[i] = rand.nextInt(100);

		// part 2(b)
	/*	print_array(array);
		merge_sort(array, 0, array.length - 1);
		print_array(array);*/
		// part 3(a)
		/*start_time= System.currentTimeMillis(); 
		insertion_sort(array);
		end_time= System.currentTimeMillis(); 
		elapsed_time_insertion=end_time-start_time;*/
		
		/*start_time= System.currentTimeMillis(); 
		merge_sort(array, 0, array.length - 1);
		end_time= System.currentTimeMillis(); 
		elapsed_time_insertion=end_time-start_time;
	System.out.println(elapsed_time_insertion);*/
 
		// part 3(b)
for(long i =2;i<Long.MAX_VALUE;i++) {
	double x = Math.log(i)/Math.log(2);
	while(8*Math.pow(i,2)<=64*x) {
		System.out.println(i);
		i++;
	}
}
	}

	// indices p and r can start from 0
	public static void merge_sort(int[] A, int p, int r) {
		if (p < r) {
			// Find the middle point
			int m =( p + r ) / 2;

			// Sort first and second halves
			merge_sort(A, p, m);
			merge_sort(A, m + 1, r);

			// Merge the sorted halves
			merge(A, p, m, r);
		}
	}

	// Part 2(a)
	public static void merge(int[] A, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;


		int L[] = new int[n1];
		int R[] = new int[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = A[p + i];
		for (int j = 0; j < n2; ++j)
			R[j] = A[q + 1 + j];

	
		int i = 0, j = 0;

		int k = p;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				A[k] = L[i];
				i++;
			} else {
				A[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			A[k] = L[i];
			i++;
			k++;
		}

		
		while (j < n2) {
			A[k] = R[j];
			j++;
			k++;
		}
	}

	  public static void insertion_sort(int [] A)
      {
      	 int n = A.length;
           for (int i =1 ; i < n; ++i) {
               int key = A[i];
               int j = i - 1;

               while (j >= 0 && A[j] > key) {
                   A[j + 1] = A[j];
                   j = j - 1;
               }
               A[j + 1] = key;
           }
       
      }
	   public static void insertion_sort_reverse(int [] A)
       {
       	 int n = A.length;
            for (int i = 1; i < n; ++i) {
                int key = A[i];
                int j = i - 1;

                while (j >= 0 && A[j] < key) {
                    A[j + 1] = A[j];
                    j = j - 1;
                }
                A[j + 1] = key;
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
