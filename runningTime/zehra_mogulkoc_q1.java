import java.util.*;

public class h1 {
//HW1 QUESTİON 1
	static void equation() {
		double x = Math.log(100);

		for (int n = 1; n < 100; n++) {
			double num = x + Math.log(Math.pow(n, 2));
			if (num < n * Math.log(2)) {
				System.out.println("Answer of question 1 is: " + n);// The answer is 15
				break;
			}
		}
	}

	public static void main(String[] args) {
		equation();

	}

}
