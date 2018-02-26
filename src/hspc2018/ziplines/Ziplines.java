package hspc2018.ziplines;

import java.util.Scanner;


public class Ziplines {

	public static int largestIslandChain(Scanner sc) {
		//Read the number of islands.
		int islands=sc.nextInt();

		//Read the number of ziplines.
		int ziplines=sc.nextInt();

		//Read the individual ziplines.
		for (int z=0; z<ziplines; z++) {
			int from=sc.nextInt();
			int to=sc.nextInt();

			//You'll need to store/use this information.
		}


		//Your code to determine and return the solution...




		return -1;
	}






	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine();

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			int answer = largestIslandChain(sc);
			System.out.println("Biggest group size: " + answer);
		}

		sc.close();
	}

}
