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
package info.simpll.immense.fibonacci

import com.google.common.collect.Lists
import info.simpll.immense.common.Calculator

/**
 * Fibonacci calculator
 *
 * @author Bhathiya
 */
class Fibonacci implements Calculator {

    private BigInteger upTo
    private BigInteger last
    private BigInteger beforeLast
    private List<BigInteger> fibonacciNumbers

    public Fibonacci(BigInteger upTo) {
        this.upTo = upTo
        fibonacciNumbers = Lists.newArrayList(1g, 1g)
        last = 1g
        beforeLast = 1g
    }

    @Override
    public void calculate() {
        BigInteger current = 2g;
        BigInteger count = 1g;
        while (count <= upTo) {
            fibonacciNumbers.add(current)
            count++
            beforeLast = last
            last = current
            current = last + beforeLast
        }
    }

    @Override
    BigInteger getLast() {
        return last
    }

    @Override
    List<BigInteger> get() {
        return fibonacciNumbers
    }

    @Override
    int count() {
        return fibonacciNumbers.size()
    }
}
