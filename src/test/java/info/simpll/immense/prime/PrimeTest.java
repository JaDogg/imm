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
package info.simpll.immense.prime;

import com.google.common.base.Joiner;
import com.google.common.math.LongMath;
import org.junit.Test;

import java.math.BigInteger;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * @author Bhathiya
 */
public class PrimeTest {

    @Test
    public void testApi() {
        Prime p = new Prime(BigInteger.TEN);
        assertEquals(p.count(), 7);
    }

    @Test
    public void testFirstFewPrimes() {
        Prime prime = new Prime(BigInteger.valueOf(7000));

        prime.calculate();
        String expected = Joiner.on(", ").join(PrimeList.get().subList(0, prime.count()));
        String actual = Joiner.on(", ").join(prime.get());

        System.out.printf("Calculated = %s%n", actual);
        System.out.printf("Stored     = %s%n", expected);
        assertEquals(expected, actual);
    }

    @Test
    public void eulerLargestPrimeFactor() {
        long targetNumber = 600851475143l;
        long targetSquareRoot = LongMath.sqrt(targetNumber, RoundingMode.CEILING);

        Prime prime = new Prime(BigInteger.valueOf(targetSquareRoot));
        prime.calculate();

        long lastPrime = -1;
        for (BigInteger p : prime.get()) {
            lastPrime = p.longValue();

            while (targetNumber > 1 && targetNumber % lastPrime == 0) {
                targetNumber /= lastPrime;
            }

            if (targetNumber == 1) {
                break;
            }
        }
        if (targetNumber != 1) {
            lastPrime = targetNumber;
        }

        assertNotSame(-1l, lastPrime);

        System.out.printf("Answer to Euler 03 = %d%n", lastPrime);
    }

    @Test
    public void eulerSumOfPrimesBelowTwoMillion() {
        Prime prime = new Prime(BigInteger.valueOf(2_000_000l));
        prime.calculate();

        BigInteger sum = BigInteger.ZERO;
        for (BigInteger p : prime.get()) {
            sum = sum.add(p);
        }
        System.out.printf("Answer to Euler 10 = %s%n", sum.toString(10));
        System.out.printf("Answer to Euler 07 = %s%n", prime.get().get(10000)); // zero based index
    }

}
