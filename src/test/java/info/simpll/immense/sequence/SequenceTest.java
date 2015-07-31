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

package info.simpll.immense.sequence;

import com.google.common.collect.Lists;
import info.simpll.immense.basic.BigIntegerBasics;
import info.simpll.immense.prime.Prime;
import org.junit.Test;

import java.math.BigInteger;

/**
 * @author Bhathiya
 */

public class SequenceTest {
    @Test
    public void testApi() {
        Sequence<Integer> s = new Sequence<>(Lists.newArrayList(10, 20, 30, 40));
        s.initialize();
        System.out.println(s.toString());
    }

    @Test
    public void eulerSumOfConsecutivePrimes() {
        BigInteger target = BigInteger.valueOf(1_000_000); // Target max the problem

        Prime primes = new Prime(target);
        Sequence<BigInteger> primeSequence;
        Object[] cumulativeSums;
        BigInteger longestConsecutiveSumPrime = BigInteger.ZERO;
        int primeCount;
        int sequenceSize = 0;
        int currentSequenceSize = 0;

        primes.calculate();
        primeSequence = new Sequence<>(primes.get());
        primeSequence.initialize();

        cumulativeSums = primeSequence.getCumulativeSums();

        primeCount = primes.count();

        for (int i = 1; i < primeCount; i++) {
            BigInteger sumLhs = (BigInteger) cumulativeSums[i];
            if (primeSequence.contains(sumLhs)) {
                currentSequenceSize = i + 1;
                if (currentSequenceSize > sequenceSize) {
                    sequenceSize = currentSequenceSize;
                    longestConsecutiveSumPrime = sumLhs;
                }
            }
            for (int j = i + 1; j < primeCount; j++) {
                BigInteger sumRhs = (BigInteger) cumulativeSums[j];
                BigInteger diffOfSums = sumRhs.subtract(sumLhs);
                if (BigIntegerBasics.isGreaterThanOrEquals(diffOfSums, target)) {
                    break;
                }
                if (primeSequence.contains(diffOfSums)) {
                    currentSequenceSize = j - i;
                    if (currentSequenceSize > sequenceSize) {
                        sequenceSize = currentSequenceSize;
                        longestConsecutiveSumPrime = diffOfSums;
                    }
                }
            }
        }

        System.out.printf("Answer to Euler 50 = %s, sequenceSize = %d %n",
                longestConsecutiveSumPrime.toString(10), sequenceSize);

    }
}
