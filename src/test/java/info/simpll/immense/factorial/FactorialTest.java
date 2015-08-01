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
package info.simpll.immense.factorial;

import info.simpll.immense.basic.BigIntegerBasics;
import info.simpll.immense.basic.IterableNumber;
import org.junit.Test;

import java.math.BigInteger;

/**
 * @author Bhathiya
 */
public class FactorialTest {

    @Test
    public void eulerDigitFactorials() {

        //TODO Figure out an exit condition, Note: Answer = 145 + 40585
        Factorial factorials = new Factorial(BigIntegerBasics.TEN_MILLION);
        factorials.calculate();
        for (int i = 0; i < factorials.count(); i++) {
            System.out.printf("%d!%s%n", i, factorials.get().get(i));
        }
        BigInteger[] factorialArrayBigInt = new BigInteger[factorials.count()];
        factorialArrayBigInt = factorials.get().toArray(factorialArrayBigInt);
        long[] factorialArray = new long[factorials.count()];
        for (int n = 0; n < factorials.count(); n++) {
            factorialArray[n] = factorialArrayBigInt[n].longValue();
        }
        for (long i = 100; i < 999999999; i++) {
            IterableNumber iter = BigIntegerBasics.splitNumber(BigInteger.valueOf(i));
            long fac = 0;
            for (int j = 0; j < iter.size(); j++) {
                fac += factorialArray[iter.getAsByte(j)];
            }
            if (i == fac) {
                System.out.println(i);
            }
        }
    }

}