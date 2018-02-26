package hspc2018.divide;

import java.util.Scanner;

public class Divide {

	public static int divideWithoutDivide(int numerator, int denominator) {

		assert numerator >= 0;
		assert denominator > 0;
		int result = 0;
		
		// Divide numerator by denominator and store the resulting integer in
		// "result" -- you can assume numerator is divisible by denominator
		
		/* -------------------- INSERT CODE HERE ----------------------*/



		/* -------------------- DO NOT CHANGE BELOW THIS POINT ----------------------*/
		return result;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Read in number of test cases
		int numOfTestCases = sc.nextInt();
		assert numOfTestCases > 0;
		sc.nextLine();

		// Read in each individual test case
		for (int testCase=0; testCase < numOfTestCases; testCase++) { 

			String rat = sc.next();
			int numerator = Integer.parseInt(rat.split("/")[0]);
			int denominator = Integer.parseInt(rat.split("/")[1]);

			int res = divideWithoutDivide(numerator, denominator);
			System.out.println(res);
		}

		sc.close();
	}

}
