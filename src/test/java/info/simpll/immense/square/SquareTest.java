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
package info.simpll.immense.square;

import com.google.common.math.BigIntegerMath;
import info.simpll.immense.basic.BigIntegerBasics;
import info.simpll.immense.sequence.Sequence;
import org.junit.Test;

import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * @author Bhathiya
 */
public class SquareTest {

    @Test
    public void eulerSpecialPythagoreanTriplet() {
        Square squares = new Square(BigIntegerBasics.ONE_MILLION);
        squares.calculate();
        Sequence<BigInteger> squareSequence = new Sequence<>(squares.get());
        squareSequence.initialize();
        Object[] squareArray = squareSequence.getArray();
        for (int a = 3; a < squares.count(); a++) {
            for (int b = a + 1; b < squares.count(); b++) {
                BigInteger lhs = (BigInteger) squareArray[a];
                BigInteger rhs = (BigInteger) squareArray[b];
                BigInteger sum = lhs.add(rhs);
                if (squareSequence.contains(sum)) {
                    BigInteger c = BigIntegerMath.sqrt(sum, RoundingMode.CEILING);
                    BigInteger sumOfRoots = c.add(BigInteger.valueOf(a));
                    sumOfRoots = sumOfRoots.add(BigInteger.valueOf(b));
                    if (sumOfRoots.equals(BigIntegerBasics.THOUSAND)) {
                        System.out.printf("Answer to Euler 09 = %s%n",
                                BigIntegerBasics.calculateProduct(a, b, c));
                    }
                }
            }
        }
    }
}