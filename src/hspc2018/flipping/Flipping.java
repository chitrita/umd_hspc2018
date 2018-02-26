package hspc2018.flipping;

import java.util.Scanner;

public class Flipping {

	public static void findUnflippedIndices(final int M, final int K, final int L) {

		assert M > 0;
		assert K > 0 && K <= M;
		assert L > 0 && K <= M;
		
		// Store the L final indices in this array
		// Indices should be stored in the order that they would've been
		// flipped by the arm, that is, the 0th index would've been flipped
		// before the 1st index .. before the L-1st and final index
		int[] finalPositions = new int[L];
		
		/* -------------------- INSERT CODE HERE ----------------------*/



		/* -------------------- DO NOT CHANGE BELOW THIS POINT ----------------------*/
		for(int idx=0; idx<L; idx++) {
			System.out.print(finalPositions[idx]);
			if(idx==L-1) { System.out.println(); } else { System.out.print(" "); }
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Read in number of test cases
		int numOfTestCases = sc.nextInt();
		assert numOfTestCases > 0;
		sc.nextLine();

		// Read in each individual test case
		for (int testCase=0; testCase < numOfTestCases; testCase++) { 

			int M = sc.nextInt();
			int K = sc.nextInt();
			int L = sc.nextInt();

			findUnflippedIndices(M, K, L);
			sc.nextLine();
		}

		sc.close();
	}
}
