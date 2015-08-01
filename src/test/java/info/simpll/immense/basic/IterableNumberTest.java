/*
 * The MIT License
 *
 * Copyright 2015 Bhathiya.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package info.simpll.immense.basic;

import info.simpll.immense.prime.Prime;
import info.simpll.immense.sequence.Sequence;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Bhathiya
 */
public class IterableNumberTest {

    @Test
    public void eulerLargestPandigital() throws Exception {

        // TODO This test case should produce the answer
        BigInteger largestPossible = BigInteger.valueOf(1000);
        Prime primes = new Prime(largestPossible);
        Sequence<BigInteger> primeSequence;
        primes.calculate();
        primeSequence = new Sequence<>(primes.get());
        primeSequence.initialize();

        Object[] array = primeSequence.getArray();
        for (int i = primes.count() - 1; i > 0; i--) {
            BigInteger current = (BigInteger) array[i];
            if (BigIntegerBasics.splitNumber(current).isPandigital()) {
                System.out.printf("Answer to Euler 41 = %s%n", current.toString());
            }
        }
    }

    @Test
    public void eulerCircularPrimes() throws Exception {
        Prime primes = new Prime(BigIntegerBasics.ONE_MILLION);
        primes.calculate();
        List<BigInteger> primeList = primes.get();
        Sequence<BigInteger> primeSequence = new Sequence<>(primeList);
        primeSequence.initialize();

        int circularCount = 1;
        outerLoop:
        for (BigInteger prime : primeList) {
            IterableNumber iterablePrime = BigIntegerBasics.splitNumber(prime);
            if (iterablePrime.hasEvenParts()) continue;
            for (int i = 0; i < iterablePrime.size(); i++) {
                iterablePrime.rotate();
                BigInteger newPrime = iterablePrime.build();
                if (!primeSequence.contains(newPrime)) {
                    continue outerLoop;
                }
            }
            circularCount++;
        }

        System.out.printf("Answer to Euler 35 = %d%n", circularCount);
    }


    @Test
    public void eulerTruncatablePrimes() throws Exception {
        Prime primes = new Prime(BigIntegerBasics.TWO_MILLION);
        primes.calculate();
        List<BigInteger> primeList = primes.get();
        Sequence<BigInteger> primeSequence = new Sequence<>(primeList);
        primeSequence.initialize();


        int truncatableCount = 0;
        BigInteger sum = BigInteger.ZERO;
        Object[] primeArray = primeSequence.getArray();

        outerLoop:
        for (int i = 4; i < primes.count(); i++) { // Since first 4 primes 2,3,5,7 are ignored, start from 5th
            BigInteger prime = (BigInteger) primeArray[i];
            IterableNumber leftIteration = BigIntegerBasics.splitNumber(prime);
            BigInteger[] leftParts = leftIteration.getLeftConsecutiveParts();
            BigInteger[] rightParts = leftIteration.getRightConsecutiveParts();
            for (int j = 0; j < leftIteration.size() - 1; j++) {
                if (!(primeSequence.contains(leftParts[j]) && primeSequence.contains(rightParts[j]))) {
                    continue outerLoop;
                }
            }
            truncatableCount++;
            sum = sum.add(prime);
            if (truncatableCount == 11) {
                break;
            }
        }

        System.out.printf("Answer to Euler 37 = %s, Count = %d %n", sum.toString(), truncatableCount);
    }

    @Test
    public void eulerDoubleBasePalindrome() {
        long max = 1_000_000;
        BigInteger sum = BigInteger.ZERO;
        for (long i = 1; i < max; i++) {
            BigInteger currentNumber = BigInteger.valueOf(i);
            if (!BigIntegerBasics.isBinaryPalindrome(currentNumber)) continue;
            IterableNumber currentNumberAsIterable = BigIntegerBasics.splitNumber(currentNumber);
            if (currentNumberAsIterable.isPalindrome()) sum = sum.add(currentNumber);
        }

        System.out.printf("Answer to Euler 36 = %s", sum.toString());
    }

}