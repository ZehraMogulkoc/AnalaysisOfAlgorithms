package lab2;

import java.util.Arrays;
import java.util.Random;

public class insertion
{
	public static void main(String [] args)
	{
		int array_size = 100000;
		long start_time, end_time, elapsed_time;

		int array [] = new int[array_size];

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		//initialize elements of array with random integers
		for (int i = 0; i < array.length; i++)
			array[i] = rand.nextInt(100);
		
			insertion_sort(array);
		//part 1(c)
		start_time= System.nanoTime(); 
		insertion_sort(array);
		end_time= System.nanoTime(); 
		elapsed_time=end_time-start_time;
		System.out.println(elapsed_time);
		//part 1(d) 
		insertion_sort_reverse(array);
		Long start_time2= System.nanoTime(); 
		insertion_sort(array);
		Long end_time2= System.nanoTime(); 
		Long elapsed_time2=end_time2-start_time2;
		System.out.println(elapsed_time2);
		//part 1(e)
	}

        //part 1(a) implementing insertion sort algorithm as a method below
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

        //part 1(b) implementing insertion sort algorithm that sorts in descending order as a method below
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

	//prints the elements of the array A on the screen
	public static void print_array(int [] A)
	{
		System.out.printf("[");
		for (int i = 0; i < A.length-1; i++)
		{
			System.out.printf("%d, ", A[i]);
		}
		
		System.out.printf("%d]\n", A[A.length-1]);

	}
}

