package hspc2018.primetime;

import java.util.Scanner;

public class Primetime {

	public static int getNthPrime(int N) {
		assert N>0;
		int nthPrime = -1;

		// Given positive integer N, compute the Nth prime number
		// E.g., if N = 3, set nthPrime = 5
		//       if N = 4, set nthPrime = 7
		/* -------------------- INSERT CODE HERE ----------------------*/



		/* -------------------- DO NOT CHANGE BELOW THIS POINT ----------------------*/
		return nthPrime;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Read in number of test cases
		int numOfTestCases = sc.nextInt();
		assert numOfTestCases > 0;
		sc.nextLine();

		// Read in each individual test case
		for (int testCase=0; testCase < numOfTestCases; testCase++) { 

			int nthPrime = sc.nextInt();
			System.out.println(getNthPrime(nthPrime));
			
			sc.nextLine();
		}

		sc.close();
	}
}
