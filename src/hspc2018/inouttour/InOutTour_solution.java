package hspc2018.inouttour;

//----------------------------------------------------------------------
// Best viewed with tabs every 4 columns
//----------------------------------------------------------------------

import java.util.*;

/** InOutTour
 *
 * The objective of this problem is to compute an In-and-Out Tour, that
 * is, a minimum length path that starts and ends at a given starting
 * point and visits a set of points, all lying to the right of the start
 * point, subject to the condition that the path travels outbound
 * (increasing monotonically along the x-axis), and then inbound
 * (decreasing monotonically along the x-axis).
 *
 * The input consists of a sequence of n+1 points p(0), ..., p(n) in the
 * plane, which are given by two coordinate arrays x[0..n] and y[0..n].
 * Point p(0) is the start point and has the smallest x-coordinate. The
 * other points are given in (strictly) increasing order of
 * x-coordinates. The distance between two points p(i) and p(j) is
 * defined to be |x[j] - x[i]| + |y[j] - y[i]| (the so-called Manhattan
 * or taxi-cab distance).
 *
 * The tour starts p(0) and turns around at p(n). Each of the other
 * points is visited exactly once, either on the outbound (increasing x)
 * or inbound (decreasing x) portions of the tour. The function
 * GetInOutTour is given the point coordinates and returns a boolean
 * array outBound, where for i in [1,n-1]) outBound[i] indicates whether
 * the i-th point is visited along the outbound or inbound portions of
 * the tour. Without loss of generality, we may assume that p(1) is
 * visited on the outbound portion (since otherwise we can simply swap
 * outbound and inbound).
 */

public class InOutTour_solution {

	/** 
	 *  Main Program - Read the input and output the final answer.
	 */
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		try {
			int numTrials = scanner.nextInt();	// number of trials to run

			for(int t = 1; t <= numTrials; t++) {
				int n = scanner.nextInt();		// number of points (excluding start)
				if (n < 1) {
					System.err.println("Error - Must have at least one point");
					return;
				}

				int[] x = new int[n+1];			// point coordinates
				int[] y = new int[n+1];

				for (int i = 0; i <= n; i++) {	// input points
					x[i] = scanner.nextInt();
					y[i] = scanner.nextInt();
												// in increasing-x order
					if ((i >= 1) && (x[i] <= x[i-1])) {
						System.err.println("Error - Points must be given in increasing-x order");
						return;
					}
				}
												// diagnostic output
				System.out.println("Trial: " + t);
				System.out.println("  Points:");
				for (int i = 0; i <= n; i++) {
					System.out.println("    [" + i + "]: (" + x[i] + "," + y[i] + ")");
				}

				boolean[] outBound = new boolean[n+1]; // result storage
				GetInOutTour(x, y, outBound);	// solve it

				PrintTour(x, y, outBound);		// summarize the result
			}
		}
		finally {								// close scanner resource
			if (scanner != null) {
				scanner.close();
			}
		}
		System.exit(0); // not sure why needed - but it keeps Eclipse happy
	}

	/** Print the tour
	 */

	private static void PrintTour(int[] x, int[] y, boolean[] outBound)
	{
		int n = x.length - 1;					// number of points
		if (n >= 2 && !outBound[1]) {			// p(1) should be on outbound side
			System.out.println("Warning - First point should always be on outbound side");
		}

		System.out.print("  Tour: 0");
		for (int i = 1; i < n; i++) {			// outbound portion
			if (outBound[i]) {
				System.out.print(" " + i);
			}
		}
		System.out.print(" " + n);				// turn-around point
		for (int i = n-1; i >= 1; i--) {		// inbound portion
			if (!outBound[i]) {
				System.out.print(" " + i);
			}
		}
		System.out.println(" 0");				// back to start

		int totalDist = 0;						// get the total distance
		int prevInBound = 0;
		int prevOutBound = 0;
		for (int i = 1; i < n; i++) {
			if (outBound[i]) {
				totalDist += dist(prevOutBound, i, x, y);
				prevOutBound = i;
			}
			else {
				totalDist += dist(prevInBound, i, x, y);
				prevInBound = i;
			}
		}
												// two segments into p(n)
		totalDist += dist(prevOutBound, n, x, y) + dist(prevInBound, n, x, y);
		System.out.println("  Total distance = " + totalDist);
	}
	

	/** Compute distance between points p(i) and p(j)
	 */

	private static int dist(int i, int j, int[] x, int[] y) {
		return Math.abs(x[j] - x[i]) + Math.abs(y[j] - y[i]);
	}
	
	private static void GetInOutTour(int[] x, int[] y, boolean[] outBound) {

	/* -------------------- INSERT CODE HERE ----------------------*/
		
	//-------------------------------------------------------------------
	// START OF PROTECTED MATERIAL
	//-------------------------------------------------------------------

	/* --------------------- HOW IT WORKS --------------------------
	 *
	 * Our solution is based on dynamic programming. We say that a path
	 * is x-monotone if it visits points in increasing order of
	 * x-coordinates. Our approach is to compute two paths, both
	 * emanating from p(0).
	 *
	 * For 1 <= i <= n, let M[i] denote the minimum cost of two
	 * x-monotone paths, both starting at p(0), where one path ends at
	 * p(i) and the other path ends at p(i-1), and the points p(1), ...,
	 * p(i) are visited by one path or the other. We will show how to
	 * compute M[i] efficiently and will show that, once computed, we
	 * can use it to solve the in-and-out tour problem.
	 *
	 * First, for 0 <= k <= n, define
	 *
	 * 		D[k] = sum_{0 < j <= k} dist(j-1, j).
	 *
	 * That is, this is the length of the path that visits the sequence
	 * of points p(0), ..., p(k). We can compute D[] efficiently by:
	 *
	 * 		D[0] = 0  and  D[j] = D[j-1] + dist(j-1, j)
	 *
	 * Given this, we can compute the path length along any sequence of
	 * points p(a), ..., p(b) as D[b] - D[a], which we denote by D(a,b)
	 *
	 * Now, we show how to compute M[]. For the basis case, M[1] =
	 * dist(0, 1). In general, M[i] is computed as follows. One portion
	 * of the path ends at p(i) and the other at p(i-1). The point just
	 * prior to p(i) on the first portion is some point p(j), where 0 <=
	 * j <= i-2. The other portion of the path must contain all the
	 * points from p(j+1) up to p(i-1). (If j = i-2, this path is
	 * trivial.) If we knew the value of j, then the best way to
	 * assemble the path would be to compute the best pair of paths
	 * ending at p(j) and p(j+1) (M[j+1]), add the segment from p(j) to
	 * p(i) (dist(j,i)), and add the entire path from p(j+1) to p(i-1)
	 * (D(j+1,i-1)). Since we do not know what j should be, so we try
	 * all possibilities and take the best. This leads to the following
	 * rule:
	 *
	 * M[i] = min_{0 <= j <= i-2} (M[j+1] + dist(j,i) + D(j+1,i-1)).
	 *
	 * To obtain the cost of overall in-and-out tour, we observe that by
	 * removing the segment from n-1 to n, we obtain M[n]. This implies
	 * that the final cost is M[n] + dist(n-1, n).
	 *
	 * This only yields the cost of the tour. To obtain the actual tour
	 * we invoke the function GetTour. To help in this regard, we define
	 * a helper index H[i], which stores the value of j+1, where j is
	 * the index that achieves the minimum in the definition of M[i].
	 * ----------------------------------------------------------------
	 */

		int n = x.length - 1;			// number of points
		int[] D = new int[n+1];			// path lengths
		int[] M = new int[n+1];			// DP cost storage
		int[] H = new int[n+1];			// DP helpers

		D[0] = 0;						// compute path lengths
		for (int a = 0; a < n; a++) {
			D[a+1] = D[a] + dist(a, a+1, x, y);
		}

		M[0] = 0;  H[0] = -1;			// basis cases
		M[1] = dist(0, 1, x, y);  H[1] = 0;
		for (int i = 2; i <= n; i++) {
			M[i] = Integer.MAX_VALUE;
			for (int j = 0; j <= i-2; j++) {
										// compute the cost for j
				int cost = M[j+1] + dist(j, i, x, y) + (D[i-1] - D[j+1]);
				if (cost < M[i]) {		// new min cost?
					M[i] = cost;		// save it
					H[i] = j+1;
				}
			}
		}

		GetTour(H, outBound);			// compute the tour
	}

	/** Compute the in-and-out tour
	 *
	 * Determines which points are on the outbound versus inbound
	 * portions of the tour.
	 *
	 * This function uses the helper values H[]. Recall from the above
	 * description that for each i, M[i] contains the segment (p(j),
	 * p(i)) on one side and the path p(j+1), ..., p(i-1) on the other
	 * side. We store the value of j+1 in H[i]. Thus, the path from
	 * p(H[i]) to p(i-1) lies on one side of the tour. Then to switch to
	 * the other side of the tour, we set i to H[i]. We repeat until
	 * getting back to i = 0.
	 *
	 * We have the convention that p(1) is on the outbound portion of
	 * the tour. If we find that it was placed on the wrong side, we
	 * complement all the values in the result.
	 *
	 * (Note that the values of outBound[0] and outBound[n] are not set,
	 * since they are not needed.)
	 */

	private static void GetTour(int[] H, boolean[] outBound) {
										// initialize
		boolean side = true;
		int n = H.length - 1;			// number of points
		int i = n;						// start at the last point
		while (i > 0) {					// trace back to start
			for (int j = H[i]; j < i; j++) {
				outBound[j] = side;		// p(H[i]),...,p(i-1) on same side
			}
			side = !side;				// switch sides
			i = H[i];					// go to previous helper
		}

		if (!outBound[1]) {				// point 1 on wrong side?
										// complement everything
			for (int j = 1; j < n; j++) {
				outBound[j] = !outBound[j];
			}
		}
	
	//-------------------------------------------------------------------
	// END OF PROTECTED MATERIAL
	//-------------------------------------------------------------------
	}

}
