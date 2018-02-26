package hspc2018.clever;

import java.util.Scanner;

public class Clever {
	
	public static int getConfigurationCount(int K) {
		assert K>0;
		int numConfigurations = -1;

		// Given positive integer K, compute the number of legal pairings of 
		// velociraptors with their hunting partners, MODULO 10007
		// --> store this value in numConfigurations
		/* -------------------- INSERT CODE HERE ----------------------*/



		/* -------------------- DO NOT CHANGE BELOW THIS POINT ----------------------*/
		return numConfigurations;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Read in number of test cases
		int numOfTestCases = sc.nextInt();
		assert numOfTestCases > 0;
		sc.nextLine();

		// Read in each individual test case
		for (int testCase=0; testCase < numOfTestCases; testCase++) { 

			int K = sc.nextInt();
			System.out.println(getConfigurationCount(K));
			
			sc.nextLine();
		}

		sc.close();
	}
}
