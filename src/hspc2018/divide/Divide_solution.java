package hspc2018.divide;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

// Thanks to: https://stackoverflow.com/questions/5284898/implement-division-with-bit-wise-operator
// (Among others, including the Randy Bryant book + 15-231 bit twiddling problems)

public class Divide_solution {

    public static int divideWithoutDivide(int numerator, int denominator) {

        assert numerator >= 0;
        assert denominator > 0;
        int result = 0;

        // Divide numerator by denominator and store the resulting integer in
        // "result" -- you can assume numerator is divisible by denominator

        /* -------------------- INSERT CODE HERE ----------------------*/

        final int nUnits = 30;
        int[] units = new int[nUnits];
        int[] weights = new int[nUnits];
        units[0] = denominator;
        weights[0] = 1;
        for (int i = 1; i < nUnits; ++i) {
            units[i] = units[i - 1] + units[i - 1];
            weights[i] = weights[i - 1] + weights[i - 1];
        }

        for (int i = nUnits - 1; i >= 0; --i) {
            while (units[i] >= 1 && numerator >= units[i]) {
                numerator -= units[i];
                result += weights[i];
                assert numerator >= 0;
            }
        }

        /* -------------------- DO NOT CHANGE BELOW THIS POINT ----------------------*/
        return result;
    }

    public static int rng(Random r, int min, int max) {
    	// Can't assume recent Java on HSPC machines
    	//return ThreadLocalRandom.current().nextInt(min, max + 1);
        return ( r.nextInt(max + 1 - min) + min );
    }

    public static void test() {
    	Random r = new Random();
        int B = rng(r, 1, 1 << 10);
        int A = rng(r, 1, 1 << 10) * B;
        System.out.println(A + " / " + B);
        BigInteger bigN = new BigInteger("" + A);
        BigInteger bigD = new BigInteger("" + B);
        int result = divideWithoutDivide(A, B);
        System.out.println(result + " = " + bigN.divide(bigD));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read in number of test cases
        int numOfTestCases = sc.nextInt();
        assert numOfTestCases > 0;
        sc.nextLine();

        // Read in each individual test case
        for (int testCase = 0; testCase < numOfTestCases; testCase++) {

            String rat = sc.next();
            int numerator = Integer.parseInt(rat.split("/")[0]);
            int denominator = Integer.parseInt(rat.split("/")[1]);

            int res = divideWithoutDivide(numerator, denominator);
            System.out.println(res);
        }

        sc.close();
    }

}
