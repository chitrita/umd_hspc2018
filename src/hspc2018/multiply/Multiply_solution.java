package hspc2018.multiply;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Multiply_solution {

    public static void getPrimes(int max, int[] primes) {
        int root = (int) Math.sqrt(max) + 1;
        int limit = (max - 1) / 2;
        root = root / 2 - 1;
        boolean[] sieve = new boolean[max];
        int count = 0;
        for (int p = 2; p < root; ++p) {
            if (!sieve[p]) {
                primes[count++] = p;
                for (int i = p * 2; i < max; i += p) {
                    sieve[i] = true;
                }
            }
        }

        for (int p = root; p < max; ++p) {
            if (!sieve[p]) {
                primes[count++] = p;
            }
        }
    }

    public static void factorizeAndCount(int x, int[] primes, int[] count, int inc) {
        for (int i = 0; x > 1 && i < primes.length && primes[i] > 1; ++i) {
            while (x % primes[i] == 0) {
                x /= primes[i];
                count[i] += inc;
            }
        }
    }

    public static long rPower(int x, int p) {
        if (p == 0)
            return 1;
        if (p % 2 == 1) {
            long result = x * rPower(x, p - 1);
            assert result >= 0 && result < Integer.MAX_VALUE;
            return result;
        }
        long y = rPower(x, p / 2);
        long result = y * y;
        assert result >= 0 && result < Integer.MAX_VALUE;
        return result;
    }

    public static void multiplyRationals(int numRationals, int[][] rationals) {

        assert numRationals > 0;

        // Store the final reduced numerator and denominator of
        // the product of all numRationals rationals here
        long finalNumerator = 1;
        long finalDenominator = 1;

        // Example access:
        // rationals[4][0] is the numerator of the 5th rational
        // rationals[4][1] is the denominator of the 5th rational

        /* -------------------- INSERT CODE HERE ----------------------*/

        final int N = 1 << 16;
        int[] primes = new int[N];
        int[] powers = new int[N];

        getPrimes(N, primes);

        for (int i = 0; i < numRationals; ++i) {
            if (rationals[i][0] == 0) finalNumerator = 0;
            factorizeAndCount(rationals[i][0], primes, powers, +1);
            factorizeAndCount(rationals[i][1], primes, powers, -1);
        }

        for (int i = 0; i < primes.length && primes[i] > 1; ++i) {
            if (powers[i] > 0)
                finalNumerator *= rPower(primes[i], powers[i]);
            else
                finalDenominator *= rPower(primes[i], -powers[i]);
            assert finalNumerator >= 0 && finalNumerator < Integer.MAX_VALUE;
            assert finalDenominator >= 0 && finalDenominator < Integer.MAX_VALUE;
        }

        if (finalNumerator == 0)
            finalDenominator = 1;

        /* -------------------- DO NOT CHANGE BELOW THIS POINT ----------------------*/
        System.out.println(finalNumerator + "/" + finalDenominator);
    }

    public static int rng(Random r, int min, int max) {
        // Can't assume recent Java on HSPC machines
    	//return ThreadLocalRandom.current().nextInt(min, max + 1);
        return ( r.nextInt(max + 1 - min) + min );
    }

    public static void test() {
    	Random r = new Random();
        final int numOfRationals = rng(r, 1 << 2, 1 << 3);
        assert numOfRationals > 0;
        int[][] rationals = new int[numOfRationals][2];
        BigInteger bigN = BigInteger.ONE;
        BigInteger bigD = BigInteger.ONE;
        for (int ratNum = 0; ratNum < numOfRationals; ratNum++) {
            rationals[ratNum][0] = rng(r, 0, 1 << 6);
            rationals[ratNum][1] = rng(r, 1, 1 << 6);
            // System.out.print(rationals[ratNum][0] + "/" + rationals[ratNum][1]);
            bigN = bigN.multiply(new BigInteger("" + rationals[ratNum][0]));
            bigD = bigD.multiply(new BigInteger("" + rationals[ratNum][1]));
        }
        // System.out.println();
        multiplyRationals(numOfRationals, rationals);
        BigInteger gcd = bigN.gcd(bigD);
        System.out.println(bigN.divide(gcd) + "/" + bigD.divide(gcd));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read in number of test cases
        int numOfTestCases = sc.nextInt();
        assert numOfTestCases > 0;
        sc.nextLine();

        // Read in each individual test case
        for (int testCase = 0; testCase < numOfTestCases; testCase++) {

            // Get number of rationals to be multiplied in this test case
            final int numOfRationals = sc.nextInt();
            assert numOfRationals > 0;
            int[][] rationals = new int[numOfRationals][2];
            for (int ratNum = 0; ratNum < numOfRationals; ratNum++) {
                String rat = sc.next();
                int numerator = Integer.parseInt(rat.split("/")[0]);
                int denominator = Integer.parseInt(rat.split("/")[1]);
                rationals[ratNum][0] = numerator;
                rationals[ratNum][1] = denominator;
            }

            multiplyRationals(numOfRationals, rationals);
            sc.nextLine();
        }

        sc.close();
    }
}
