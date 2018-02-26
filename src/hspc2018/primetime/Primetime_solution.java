package hspc2018.primetime;

import java.util.Scanner;

// Thanks to StackOverflow user Daniel Fischer
// https://stackoverflow.com/questions/9625663/calculating-and-printing-the-nth-prime-number

public class Primetime_solution {

	public static int getNthPrime(int N) {
		assert N>0;
		int nthPrime = -1;

		// Given positive integer N, compute the Nth prime number
		// E.g., if N = 3, set nthPrime = 5
		//       if N = 4, set nthPrime = 7
		/* -------------------- INSERT CODE HERE ----------------------*/
		if (N < 2) return 2;
		if (N == 2) return 3;
		int limit, root, count = 1;
		limit = (int) (N*(Math.log(N) + Math.log(Math.log(N)))) + 3;
		root = (int) Math.sqrt(limit) + 1;
		limit = (limit-1)/2;
		root = root/2 - 1;
		boolean[] sieve = new boolean[limit];
		for(int i = 0; i < root; ++i) {
			if (!sieve[i]) {
				++count;
				for(int j = 2*i*(i+3)+3, p = 2*i+3; j < limit; j += p) {
					sieve[j] = true;
				}
			}
		}
		int p;
		for(p = root; count < N; ++p) {
			if (!sieve[p]) {
				++count;
			}
		}
		nthPrime = 2*p+1;


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
