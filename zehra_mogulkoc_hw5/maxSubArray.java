package hw5;

public class maxSubArray {
	public static int FindMaxSubarray(int[] A, int low, int high, int[] result) {
		int max = Integer.MIN_VALUE; //set max sum as min value at the beginning
		int current = 0;
		int left = 0;
		int right = 0;
		int current_left = 0;
		for (int i = low; i < high; i++) {
			current += A[i]; // current sum
			if (current > max) { 
				max = current;// if current sum bigger, update sum value
				left = current_left;  //new left value
				right = i;
			}
			if (current < 0) {
				current = 0; //set current to 0 again
				current_left = i + 1; 
			}

		}
		result[0] = left;
		result[1] = right;
		result[2] = max;
		return max;
	}

	public static void print_array(int[] A) { //print array
		System.out.printf("[");
		for (int i = 0; i < A.length - 1; i++) {
			System.out.printf("%d, ", A[i]);
		}

		System.out.printf("%d]\n", A[A.length - 1]);

	}

	public static void main(String[] args) {

		int[] arr = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7 };
		int[] result = new int[3];

		print_array(arr);
		FindMaxSubarray(arr, 0, arr.length, result);
		System.out.println(
				"From index: " + (result[0] + 1) + " to index: " + (result[1] + 1) + " max sum is: " + result[2]);
	}

}
