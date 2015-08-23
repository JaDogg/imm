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
package info.simpll.immense.fibonacci;

import org.junit.Test;

import java.math.BigInteger;

/**
 * @author Bhathiya
 */
public class FibonacciTest {

    @Test
    public void testApi() {
        Fibonacci fib = new Fibonacci(BigInteger.valueOf(2000));
        fib.calculate();
        int index = 1;
        int lastSize = 0;
        int lastIndex = 1;
        for (BigInteger number : fib.get()) {
            int currentSize = number.toString().length();
            if (currentSize > lastSize) {
                System.out.printf("%d %d %d%n",lastIndex, index - lastIndex, currentSize - 1);
                lastSize = currentSize;
                lastIndex = index;
            }
            index++;
        }
    }

}