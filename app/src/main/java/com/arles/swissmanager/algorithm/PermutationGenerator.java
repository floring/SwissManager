package com.arles.swissmanager.algorithm;

import java.math.BigInteger;

/**
 * Created by Admin on 17.03.2015.
 */
public class PermutationGenerator {
    private int[] a;
    private BigInteger mNumRemain;
    private BigInteger mTotal;

    public PermutationGenerator(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Min 1");
        }
        a = new int[number];
        mTotal = getFactorial(number);
        reset();
    }

    public void reset() {
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        mNumRemain = new BigInteger(mTotal.toString());
    }

    /**
     * Returns number of permutations not yet generated
     */
    public BigInteger getNumRemain() {
        return mNumRemain;
    }

    /**
     * Returns total number of permutations
     */
    public BigInteger getTotal() {
        return mTotal;
    }

    /**
     * Checks if the remain permutations are exist
     */
    public boolean hasMore() {
        return mNumRemain.compareTo(BigInteger.ZERO) == 1;
    }

    /**
     * Calculates factorial
     *
     * @param n
     * @return
     */
    private static BigInteger getFactorial(int n) {
        BigInteger fact = BigInteger.ONE;
        for (int i = n; i > 1; i--) {
            fact = fact.multiply(new BigInteger(Integer.toString(i)));
        }
        return fact;
    }

    /**
     * Generate next permutation
     *
     * @return
     */
    public int[] getNext() {
        if (mNumRemain.equals(mTotal)) {
            mNumRemain = mNumRemain.subtract(BigInteger.ONE);
            return a;
        }
        int temp;
// Find largest index j with a[j] < a[j+1]
        int j = a.length - 2;
        while (a[j] > a[j + 1]) {
            j--;
        }
// Find index k such that a[k] is smallest integer
// greater than a[j] to the right of a[j]
        int k = a.length - 1;
        while (a[j] > a[k]) {
            k--;
        }
// Interchange a[j] and a[k]
        temp = a[k];
        a[k] = a[j];
        a[j] = temp;
// Put tail end of permutation after jth position in increasing order
        int r = a.length - 1;
        int s = j + 1;
        while (r > s) {
            temp = a[s];
            a[s] = a[r];
            a[r] = temp;
            r--;
            s++;
        }
        mNumRemain = mNumRemain.subtract(BigInteger.ONE);
        return a;
    }
}
