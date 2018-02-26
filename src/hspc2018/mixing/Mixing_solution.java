package hspc2018.mixing;

import java.util.Scanner;

// Thank you to: http://programmingcontest.usask.ca/

public class Mixing_solution {

	public static int calculateIterations(int A, int B, int C, double T) {
		assert A > 0;
		assert B > 0;
		assert C > 0;
		assert T > 0.0;

		// Store final computing number of iterations here
		int numIterations = 0;

		// Inputs:
		// A is the number of units of Dino DNA #1 initially in dish #1
		// B is the number of units of Dino DNA #2 initially in dish #2
		// C is the amount of (mixed) DNA from one petri dish that is
		//   placed into the other petri dish at each iteration
		// T is a positive real-valued tolerance level

		/* -------------------- INSERT CODE HERE ----------------------*/
		double targ = (double) A/B;
		double Bmix = 0.0;
		double Amix = 1.0;

		while (((Bmix/(1-Bmix) - targ) < -1 * T) ||
				((Amix/(1-Amix) - targ) > T))
		{
			Bmix = (Bmix * B + Amix * C) /(B + C);
			Amix = (Amix*(A-C) + Bmix * C) / (A);
			numIterations++;

		}

		/* -------------------- DO NOT CHANGE BELOW THIS POINT ----------------------*/
		return numIterations;
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Read in number of test cases
		int numOfTestCases = sc.nextInt();
		assert numOfTestCases > 0;
		sc.nextLine();

		// Read in each individual test case
		for (int testCase=0; testCase < numOfTestCases; testCase++) { 

			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();
			double T = sc.nextDouble();

			int iterations = calculateIterations(A, B, C, T);
			System.out.println(iterations);
			sc.nextLine();
		}

		sc.close();
	}
}
