
public class h1 {
//HW1 QUESTİON 2
	static void binarySearch(double compare) {// for nlogn
		long left = 0, right = Long.MAX_VALUE;
		long result = 0;
		while (left <= right) {
			long mid = left + (right - left) / 2;
			long n = mid;
			double num = n * (Math.log(n) / Math.log(2));
			if (compare == 36524 * 24 * 60) {
				num = num / Math.pow(10, 6);
				num = num / 60;
			}
			if (num <= compare) {
				result = n;
				left = mid + 1;
				long n_next = n + 1;
				double num_next = n_next * (Math.log(n_next) / Math.log(2));
				if (compare == 36524 * 24 * 60) {
					num_next = num_next / Math.pow(10, 6);
					num_next = num_next / 60;
				}
				if (num_next > compare) {

					break;
				}
			}
			if (num > compare) {
				right = mid - 1;
			}
		}
		System.out.println(result);
	}

	static void linearSearch(double compare) {//for n!
		long result = 0;

		for (long n = 1; n < Long.MAX_VALUE; n++) {
			double num = factorial(n) / Math.pow(10, 6);
			if (compare == 36524 * 24 * 60) {
				num = num / 60;
			}
			if (num <= compare) {
				result = n;
			} else {
				break;
			}
		}

		System.out.println(result);
	}

	static void question_1() {

		binarySearch(Math.pow(10, 6));
		// max n=62746 for 1 sec=10^6 microsec
		binarySearch(60 * Math.pow(10, 6));
		// max n=2801417 for 1 min=60*10^6 microsec
		binarySearch(60 * 60 * Math.pow(10, 6));
		// max n=133378058 for 1 hour=60*60*10^6 microsec
		binarySearch(24 * 60 * 60 * Math.pow(10, 6));
		// max n=2755147513 for 1 day=24*60*60*10^6 microsec
		binarySearch(30 * 24 * 60 * 60 * Math.pow(10, 6));
		// max n=71870856404 for 1 month=30*24*60*60*10^6 microsec
		binarySearch(365 * 24 * 60 * 60 * Math.pow(10, 6));
		// max n=797633893349 for 1 year=365*24*60*60*10^6 microsec
		binarySearch(36524 * 24 * 60);
		// max n=68654697441062 for 1 century=36524*24*60*60*10^6 microsec

	}

	static void question_2() {

		linearSearch(1);
		// max n=9 for 1 sec=10^6 microsec
		linearSearch(60);
		// max n=11 for 1 min=60*10^6 microsec
		linearSearch(60 * 60);
		// max n=12 for 1 hour=60*60*10^6 microsec
		linearSearch(24 * 60 * 60);
		// max n=13 for 1 day=24*60*60*10^6 microsec
		linearSearch(30 * 24 * 60 * 60);
		// max n=15 for 1 month=30*24*60*60*10^6 microsec
		linearSearch(365 * 24 * 60 * 60);
		// max n=16 for 1 year=365*24*60*60*10^6 microsec
		linearSearch(36524 * 24 * 60);
		// max n=17 for 1 century=36524*24*60*60*10^6 microsec

	}

	static long factorial(long number) {
		long i, fact = 1;
		for (i = 1; i <= number; i++) {
			fact = fact * i;
		}
		return fact;
	}

	public static void main(String[] args) {
		System.out.println("Max n values for nlogn");
		question_1();
		System.out.println();
		System.out.println("Max n values for n!");
		question_2();

	}

}
