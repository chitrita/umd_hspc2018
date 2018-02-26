package hspc2018.flipping;

import java.util.Scanner;

// Thanks to: http://programmingcontest.usask.ca/

public class Flipping_solution {

	static int MAXSIZE = 16000;
	static Egg[] eggs = new Egg[MAXSIZE];

	static void listInit(int m) {
		int i;

		for (i = 0; i < m; i++) {
			Egg e = new Egg(i + 1, null, null);
			eggs[i] = e;
		}
		for (i = 1; i < m - 1; i++) {
			eggs[i].element = i + 1;
			eggs[i].next = eggs[i + 1];
			eggs[i].prev = eggs[i - 1];
		}
		eggs[m - 1].next = eggs[0];
		eggs[m - 1].prev = eggs[m - 2];
		eggs[0].prev = eggs[m - 1];
		eggs[0].next = eggs[1];

	}

	static void listDelete(Egg p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
	}

	static Egg flipNext(Egg pointer, int k, boolean print) {
		int i;
		Egg prev = pointer;

		for(i = 0; i < k; i++) {
			prev = pointer;
			pointer = pointer.next;
		}
		if(print){
			System.out.print((int) prev.element);
			System.out.print(" ");
		}
		listDelete(prev);
		return (pointer);
	}


	static void printRemaining(Egg e, int k) {
		if (e.next == e) {
			System.out.print((int) e.element);
			System.out.print(" ");
		}

		while (e.next != e) {
			e = flipNext(e, k, true);
		}
		e = flipNext(e, k, true);
		return;
	}

	public static void findUnflippedIndices(final int M, final int K,
			final int L) {

		assert M > 0;
		assert K > 0 && K <= M;
		assert L > 0 && K <= M;

		// Store the L final indices in this array
		// Indices should be stored in the order that they would've been
		// flipped by the arm, that is, the 0th index would've been flipped
		// before the 1st index .. before the L-1st and final index

		/* -------------------- INSERT CODE HERE ---------------------- */
		Egg e = null;
		listInit(M);
		e = eggs[0];
		for (int i = 0; i < M - L; i++) {
			e = flipNext(e, K, false);
		}
		printRemaining(e, K);
		System.out.println();

		/* -------------------- DO NOT CHANGE BELOW THIS POINT ---------------------- */
		return;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Read in number of test cases
		int numOfTestCases = sc.nextInt();
		assert numOfTestCases > 0;
		sc.nextLine();

		// Read in each individual test case
		for (int testCase = 0; testCase < numOfTestCases; testCase++) {

			int M = sc.nextInt();
			int K = sc.nextInt();
			int L = sc.nextInt();

			findUnflippedIndices(M, K, L);
			sc.nextLine();
		}

		sc.close();
	}
}
