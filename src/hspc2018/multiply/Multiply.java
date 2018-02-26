package hspc2018.multiply;

import java.util.Scanner;

public class Multiply {

	public static void multiplyRationals(int numRationals, int[][] rationals) {

		assert numRationals > 0;

		// Store the final reduced numerator and denominator of
		// the product of all numRationals rationals here
		int finalNumerator = -1;
		int finalDenominator = -1;

		// Example access:
		// rationals[4][0] is the numerator of the 5th rational
		// rationals[4][1] is the denominator of the 5th rational

		/* -------------------- INSERT CODE HERE ----------------------*/



		/* -------------------- DO NOT CHANGE BELOW THIS POINT ----------------------*/
		System.out.println(finalNumerator + "/" + finalDenominator);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Read in number of test cases
		int numOfTestCases = sc.nextInt();
		assert numOfTestCases > 0;
		sc.nextLine();

		// Read in each individual test case
		for (int testCase=0; testCase < numOfTestCases; testCase++) { 

			// Get number of rationals to be multiplied in this test case
			final int numOfRationals = sc.nextInt();
			assert numOfRationals > 0;
			int[][] rationals = new int[numOfRationals][2];
			for(int ratNum=0; ratNum<numOfRationals; ratNum++) {
				String rat = sc.next();
				int numerator = Integer.parseInt(rat.split("/")[0]);
				int denominator = Integer.parseInt(rat.split("/")[1]);
				rationals[ratNum][0] = numerator;
				rationals[ratNum][1] = denominator;
			}

			multiplyRationals(numOfRationals, rationals);
			sc.nextLine();
		}

		sc.close();
	}
}
