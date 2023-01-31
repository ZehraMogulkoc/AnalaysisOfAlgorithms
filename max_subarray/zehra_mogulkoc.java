package lab4;

import java.util.Arrays;
import java.util.Random;

public class max_subarray {
	public static void main(String[] args) {
		int array_size = 65536;
		int[] A = new int[array_size];
		int[] test_A = new int[5];
		int[] diff_test_A = new int[4];
		int[] diff_A = new int[array_size - 1];
		int[] outputs = new int[3];
		int best_left_index = 0;
		int best_right_index = 0;
		int max_difference = 0;

		long start_time, end_time, elapsed_time;
		long elapsed_time_brute_force, elapsed_time_divide_and_conquer;

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		// part 1(b)
		test_A[0] = 10;
		test_A[1] = 11;
		test_A[2] = 7;
		test_A[3] = 10;
		test_A[4] = 6;
		brute_force(test_A);
		// initialize elements of array with random integers and compute difference
		// array
		for (int i = 0; i < A.length; i++) {
			A[i] = rand.nextInt(100);
			if (i > 0)
				diff_A[i - 1] = A[i] - A[i - 1];
		}

		// part 1(c) compute the elapsed time for brute-force algorithm
		start_time = System.nanoTime();
		brute_force(A);
		end_time = System.nanoTime();
		elapsed_time = end_time - start_time;
		System.out.println(elapsed_time);
		// part 2(b)
		diff_test_A[0] = 1;
		diff_test_A[1] = -4;
		diff_test_A[2] = 3;
		diff_test_A[3] = -4;
		
		 System.out.println("question 2b");
	        outputs = find_maximum_subarray(diff_test_A,0,diff_test_A.length-1);
	        System.out.println("left: "+(outputs[0]+1)+" right: "+(outputs[1]+1)+" maximum difference: "+outputs[2]);
		// print the left index, right index and maximum difference (i.e. net profit)

		// part 2(c) compute the elapsed time for divide and conquer algorithm

		// print the left index, right index and maximum difference (i.e. net profit)
	    	System.out.println("question 2C");
			  start_time = System.nanoTime();
			outputs=find_maximum_subarray(diff_A,0,diff_A.length-1);
			end_time = System.nanoTime();
	        elapsed_time = end_time - start_time;
	        System.out.println("Time "+elapsed_time);
	        System.out.println("left: "+(outputs[0]+1)+" right: "+(outputs[1]+1)+" maximum difference: "+outputs[2]);
		// part 3

		int max_array_size = 1000;
		int[] A_2 = new int[max_array_size];
		int[] diff_A_2 = new int[max_array_size - 1];

		// initialize elements of array with random integers and compute difference
		// array
		for (int i = 0; i < A_2.length; i++) {
			A_2[i] = rand.nextInt(100);
			if (i > 0)
				diff_A_2[i - 1] = A_2[i] - A_2[i - 1];
		}
	
		for (array_size = 2; array_size <= max_array_size; array_size++) {

			  start_time = System.nanoTime();
	            brute_force_2(A_2,A_2.length-1);
	            end_time = System.nanoTime();
	            elapsed_time_brute_force= end_time - start_time;

	            start_time = System.nanoTime();
	            find_maximum_subarray(diff_A_2,0,diff_A_2.length-1);
	            end_time = System.nanoTime();
	            elapsed_time_divide_and_conquer= end_time - start_time;

	            if(elapsed_time_divide_and_conquer<elapsed_time_brute_force){
	                break;
	            }
			
		}
		System.out.println("cross-over point is "+array_size);
	}

	// part 1(a) implementing the brute-force algorithm
	public static void brute_force(int[] A) { // print the left index, right index and maximum difference (i.e. net
												// profit)
		int n = A.length;
		int maximumSubArrayDif = Integer.MIN_VALUE;
		int left = 0;
		int right = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (A[j]-A[i]>maximumSubArrayDif) {
					maximumSubArrayDif = A[j] - A[i];
					left = i;
                    right = j;
				}
			}

		}
		left = left +1;
		right = right + 1;
		System.out.println("i:" + left);
		System.out.println("j: " + right);
		System.out.println("Maximum Difference: " + maximumSubArrayDif);

	}

	public static void brute_force_2(int[] A, int array_size) {
		// print the left index, right index and maximum difference (i.e. net profit)

	}

	// part 2(a) implementing the recursive algorithm that uses divide and conquer
	// and finds the maximum subarray
	public static int[] find_maximum_subarray(int[] diff_A, int low, int high) {
		// print the left index, right index and maximum difference (i.e. net profit)
		 int outputs[]=new int[3];
		if (high == low) {

			outputs[0] = low;
			outputs[1] = high;
			outputs[2] = diff_A[low];
			return outputs;
		} else {
			 int mid = (int) Math.floor((low+high)/2);
			 int left[] = find_maximum_subarray(diff_A,low,mid);

	            int right[] = find_maximum_subarray(diff_A,mid+1,high);

	            int cross[] = find_max_crossing_subarray(diff_A,low,mid,high);
	            
			
			if (left[2] >= right[2] && left[2] >= cross[2]) {
				outputs[0] = left[0];
				outputs[1] = left[1];
				outputs[2] = left[2];
				return outputs;
			} else if (right[2]>= left[2] && right[2] >= cross[2]) {
				outputs[0] = right[0];
				outputs[1] = right[1];
				outputs[2] = right[2];
				return outputs;
			} else {
				outputs[0] = cross[0];
				outputs[1] = cross[1];
				outputs[2] = cross[2];
				return outputs;
			}
		}
	}

	public static int[] find_max_crossing_subarray(int[] diff_A, int low, int mid, int high) {
		int outputs[]=new int[3];
		int max_left = 0;
		int maxRight = 0;
		int left_sum = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = mid; i > low; i--) {
			sum = sum + diff_A[i];
			if (sum > left_sum) {
				left_sum = sum;
				max_left = i;
			}
		}
		int right_sum = Integer.MIN_VALUE;
		sum = 0;
		for (int j = mid + 1; j < high; j++) {
			sum = sum + diff_A[j];
			if (sum > right_sum) {
				right_sum = sum;
				maxRight = j;
			}
		}
		outputs[0] = max_left;
		outputs[1] = maxRight;
		outputs[2] = left_sum + right_sum;
		return outputs;
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
