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

import com.google.common.collect.Lists;
import java.util.BitSet;
import java.util.List;

/**
 * 
 * 
 * @author Bhathiya
 * 
 * 
 * Prime.upto(1000).calculate()
 * Prime.untilNth(10001).calculate()
 * Prime.getLast()
 */
public class Prime {

    private final int max;
    private int slotStart;
    private int slotEnd;
    private final BitSet sieve;
    private final List<Integer> primes;
    private final List<Integer> lastMultiple;
    private int currentPos;
    

    public Prime(int max) {
        this.max = max;
        slotStart = 2;
        slotEnd = 998;
        sieve = new BitSet();
        primes = Lists.newArrayList(2, 3, 5, 7, 11, 13, 17);
        lastMultiple = Lists.newArrayList(primes);
        currentPos = 17;
        crossOutNonPrimes();
    }

    private void crossOutNonPrimes() {
        primes.stream().forEach((Integer prime) -> {
            crossOutNonPrimes(prime);
        });
    }

    private void crossOutNonPrimes(Integer prime) {
        for (int i = prime; i < max; i += prime) {
            sieve.set(calculatePos(i));
        }
    }

    public int size() {
        return primes.size();
    }
    
    public void calculate() {

        while (true) {
            while (sieve.get(calculatePos(currentPos)) && currentPos < max) {
                currentPos += 2;
            }

            if (currentPos >= max) {
                return;
            }
            
            // Found a new prime
            primes.add(currentPos);
            
            crossOutNonPrimes(currentPos);
        }
    }

    private int calculatePos(int pos) {
        return pos - 2;
    }

    public long geMax() {
        return max;
    }

    public List<Integer> get() {
        return primes;
    }

    public void printUnset() {
        for (int i = 2; i < max; i++) {
            if (!sieve.get(calculatePos(i))) {
                System.out.printf("Prime : %d\n", i);
            }
        }
    }
}
