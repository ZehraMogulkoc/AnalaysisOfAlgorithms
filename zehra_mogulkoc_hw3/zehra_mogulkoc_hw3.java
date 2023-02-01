package hw3;

public class binary_search_pair {

	static boolean checkSum(int arr[], int n, int sum) {//look for the number that makes the sum as desired.
		for (int i = 0; i < n; i++) {// n time
			boolean k = binarySearch(arr, 0, n - 1, sum - arr[i]);// logn time
			if (k == true) {
				return true;
			}
		}
		return false;
	}

	static boolean binarySearch(int arr[], int l, int r, int x) {
		if (r >= l) {
			int mid = l + (r - l) / 2;
			if (arr[mid] == x)
				return true;

			if (arr[mid] > x)
				return binarySearch(arr, l, mid - 1, x);

			return binarySearch(arr, mid + 1, r, x);
		}

		return false;
	}

	public static void main(String[] args) {
		int arr[] = { 1,2, 3, 4, 10, 40};//should be sorted
		//if the array is not sorted, merge sort can be used, and its running time is also nlogn,
		//so the total running time would not be changed.
		int n = arr.length;
		int x = 41;
		boolean result = checkSum(arr, n, x);
		System.out.println(result);
	}

}
