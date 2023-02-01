package lab9;

import java.util.Arrays;
import java.util.Random;

public class permute {
	public static void main(String[] args) {
		int array_size = 10;
		int[] array = new int[array_size];
		int[] frequency_array = new int[24];
		int permutation_index = 0;

		Random rand = new Random();
		System.out.println("Question 1");
		for (int i = 0; i < array_size; i++)
			array[i] = rand.nextInt(100);
		System.out.println("Before Randomize-in-place: ");
		print_array(array);
		for (int i = 0; i < 5; i++) {
			System.out.println("Permutation " + (i + 1));
			randomize_in_place(array);
			print_array(array);
		}
		System.out.println("Question 2");
		for (int i = 0; i < array_size; i++)
			array[i] = rand.nextInt(100);
		System.out.println("Before Permute-with-all: ");
		print_array(array);
		for (int i = 0; i < 5; i++) {
			System.out.println("Permutation " + (i + 1));
			permute_with_all(array);
			print_array(array);
		}
		System.out.println("Question 3-a RANDOMIZE-IN-PLACE ");
		array_size = 4;
		array = new int[array_size];
		array[0] = 4;
		array[1] = 3;
		array[2] = 2;
		array[3] = 1;
		for (int i = 0; i < 24000; i++) {
			randomize_in_place(array);
			permutation_index = compute_permutation_index(array);
			// System.out.printf("permutation index = %d\n", permutation_index);
			frequency_array[permutation_index]++;

		}
		print_array(frequency_array);
		frequency_array = new int[24];
		array = new int[array_size];
		array[0] = 4;
		array[1] = 3;
		array[2] = 2;
		array[3] = 1;
		System.out.println("Question 3-b PERMUTE-WITH-ALL");
		for (int i = 0; i < 24000; i++) {
			permute_with_all(frequency_array);
			permutation_index = compute_permutation_index(array);
			// System.out.printf("permutation index = %d\n", permutation_index);
			frequency_array[permutation_index]++;

		}
		print_array(frequency_array);
	}

	// Implement randomize in place algorithm below
	public static void randomize_in_place(int[] A) {
		Random rand = new Random();
		int n = A.length;
		for (int i = 0; i < n; i++) {
			int rand_index = rand.nextInt(n - i) + i;
			int temp = A[i];
			A[i] = A[rand_index];
			A[rand_index] = temp;
		}

	}

	// Implement permute with all algorithm below
	public static void permute_with_all(int[] A) {
		Random rand = new Random();
		int n = A.length;
		for (int i = 0; i < n; i++) {
			int rand_index = rand.nextInt(n);
			int temp = A[i];
			A[i] = A[rand_index];
			A[rand_index] = temp;
		}
	}

	public static int compute_permutation_index(int[] A) {
		int permutation_index = 0;
		int next_number = 0;
		int index_next_number = 0;

		for (int starting_index = 0; starting_index < A.length - 1; starting_index++) {
			int[] remaining_numbers = new int[A.length - starting_index];
			int[] remaining_numbers_sorted = new int[A.length - starting_index];

			for (int i = 0; i < remaining_numbers.length; i++) {
				remaining_numbers[i] = A[starting_index + i];
				remaining_numbers_sorted[i] = remaining_numbers[i];
			}

			insertion_sort(remaining_numbers_sorted);

			next_number = A[starting_index];

			for (int i = 0; i < remaining_numbers_sorted.length; i++) {
				if (remaining_numbers_sorted[i] == next_number) {
					index_next_number = i;
					break;
				}
			}

			permutation_index += index_next_number * factorial(remaining_numbers.length - 1);
		}

		return permutation_index;

	}

	public static int factorial(int x) {
		int product = 1;

		for (int i = x; i >= 1; i--)
			product *= i;

		return product;
	}

	public static void insertion_sort(int[] A) {
		int key;
		int i;

		for (int j = 1; j < A.length; j++) {
			key = A[j];

			// insert A[j] into the sorted sequence A[1..j-1]
			i = j - 1;

			while ((i >= 0) && (A[i] > key)) {
				A[i + 1] = A[i];
				i = i - 1;
			}

			A[i + 1] = key;
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
