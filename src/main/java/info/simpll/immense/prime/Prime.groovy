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

package info.simpll.immense.prime

import com.google.common.base.MoreObjects
import com.google.common.collect.Lists

/**
 * Prime class is used for calculating primes
 *
 * @author Bhathiya
 */
class Prime {
    private sieve
    private final List<BigInteger> primes
    private BigInteger currentPos
    private BigInteger upTo
    private BigInteger lastPrime

    public Prime(BigInteger upTo) {
        sieve = new Sieve(Sieve.MAX_SIZE, 2g)
        primes = Lists.newArrayList(2g, 3g, 5g, 7g, 11g, 13g, 17g)
        currentPos = BigInteger.valueOf(17)
        this.upTo = upTo
        crossOutNonPrimes()
        lastPrime = 17g
    }

    private crossOutNonPrimes() {
        primes.stream().skip(1).forEach {
            prime ->
                crossOutNonPrimes(prime)
        }
    }

    private crossOutNonPrimes(BigInteger prime) {
        for (BigInteger i = prime; i < upTo; i += prime + prime) {
            sieve.set(i)
        }
    }

    public calculate() {
        while (true) {
            while (sieve.get(currentPos) && currentPos < upTo) {
                currentPos += 2
            }

            if (currentPos >= upTo) {
                return
            }

            primes.add(currentPos)
            lastPrime = currentPos

            crossOutNonPrimes(currentPos)
        }
    }

    public int count() {
        return primes.size()
    }

    public List<BigInteger> get() {
        return primes
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("currentPos", currentPos)
                .add("upTo", upTo)
                .add("count", count())
                .toString();
    }

    BigInteger getLastPrime() {
        return lastPrime
    }
}

