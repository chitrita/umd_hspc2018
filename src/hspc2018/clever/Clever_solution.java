package hspc2018.clever;

import java.util.Scanner;

// Thank you to: Benelux Algorithm Programming Contest Leiden

public class Clever_solution {

	public static int getConfigurationCount(int K) {
		assert K>0;
		int numConfigurations = -1;

		// Symmetry breaking + represent each configuration as a bitmask -> int
		final int[] a = {679,3879,6670,8547,8552,6663,7423,4872,9740,5808,9447,560,4199,267,5135,2584,3344,1455,1460,3337,6128,9328,1};
		final int[] f = new int[5001];
		final int[] fInit = {1,229,7728,2069,4990,1182,8338,3409,2588,4676,5205,8420,6749,6107,1784,4701,3574,6252,4989,1032,8473,8155,2806};
		for(int fIdx=0; fIdx<f.length; fIdx++) {
			if(fIdx < fInit.length) {
				f[fIdx] = fInit[fIdx];
			} else {
				f[fIdx] = 0;
			}
		}

		// Initialize DP arrays
		for(int j=23;j<f.length;j++) {
			for(int i=0;i<23;i++) {
				f[j]=(f[j]+f[j-1-i]*a[i])%10007;
			}
		}

		// Get numConfigurations from the DP array
		if(K % 2 == 1) {
			numConfigurations = 0;   // odd #rooms = no legal configs
		} else {
			numConfigurations = f[K/2];
		}

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

