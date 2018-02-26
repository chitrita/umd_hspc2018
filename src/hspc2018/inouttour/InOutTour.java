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

public class InOutTour {

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
		
	}

}
