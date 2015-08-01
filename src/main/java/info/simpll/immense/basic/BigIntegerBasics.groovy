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
package info.simpll.immense.basic

/**
 * This class greatly simplifies BigInteger comparisons for Java
 *
 * @author Bhathiya
 */
public class BigIntegerBasics {

    public static BigInteger ONE_MILLION = BigInteger.valueOf(1_000_000l)
    public static BigInteger TWO_MILLION = BigInteger.valueOf(2_000_000l)
    public static BigInteger TEN_MILLION = BigInteger.valueOf(10_000_000l)
    public static BigInteger THOUSAND = BigInteger.valueOf(1000l)
    public static BigInteger HUNDRED = BigInteger.valueOf(100l)
    public static BigInteger FIVE = BigInteger.valueOf(5l)
    public static BigInteger TWO = BigInteger.valueOf(2l)


    public static boolean isLessThan(BigInteger leftHandSide,
                                     BigInteger rightHandSide) {

        return leftHandSide < rightHandSide
    }

    public static boolean isLessThan(BigInteger leftHandSide,
                                     int rightHandSide) {
        return leftHandSide < rightHandSide
    }

    public static boolean isGreaterThan(BigInteger leftHandSide,
                                        BigInteger rightHandSide) {
        return leftHandSide > rightHandSide
    }

    public static boolean isGreaterThan(BigInteger leftHandSide,
                                        int rightHandSide) {
        return leftHandSide > rightHandSide
    }

    public static boolean isLessThanOrEquals(BigInteger leftHandSide,
                                             BigInteger rightHandSide) {
        return leftHandSide <= rightHandSide
    }

    public static boolean isLessThanOrEquals(BigInteger leftHandSide,
                                             int rightHandSide) {
        return leftHandSide <= rightHandSide
    }

    public static boolean isGreaterThanOrEquals(BigInteger leftHandSide,
                                                BigInteger rightHandSide) {
        return leftHandSide >= rightHandSide
    }

    public static boolean isGreaterThanOrEquals(BigInteger leftHandSide,
                                                int rightHandSide) {
        return leftHandSide >= rightHandSide
    }

    public static boolean isZero(BigInteger value) {
        return (value == 0g)
    }

    public static IterableNumber splitNumber(BigInteger value) {
        return new IterableNumber(value)
    }

    public static boolean isBinaryPalindrome(BigInteger value) {
        int lastIndex = value.bitLength() - 1;
        for (int i = 0; i < value.bitLength() / 2; i++) {
            if (value.testBit(i) ^ value.testBit(lastIndex - i)) {
                return false
            }
        }
        return true
    }
}
