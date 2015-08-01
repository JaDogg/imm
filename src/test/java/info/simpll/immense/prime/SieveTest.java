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

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * @author Bhathiya
 */
public class SieveTest {

    @Test
    public void testApi() {
        Sieve instance = new Sieve(10, 2);
        boolean result = instance.get(10);
        instance.debugPrint("testApi");
        assertFalse(result);
        assertTrue(instance.getMaxIndex()
                .compareTo(BigInteger.valueOf(11)) == 0);
    }

    @Test
    public void testSetAndGet() {
        Sieve instance = new Sieve(10, 2);
        for (int i = 2; i < (int) instance.getEndIndex().longValue(); i++) {
            instance.set(BigInteger.valueOf(i));
        }
        instance.debugPrint("testSetAndGet");
        for (int i = 2; i < (int) instance.getEndIndex().longValue(); i++) {
            assertTrue(instance.get(BigInteger.valueOf(i)));
        }
    }

    @Test
    public void testSetAndGetEdge() {
        Sieve instance = new Sieve(10, 2);
        instance.set(11);
        instance.set(2);

        assertTrue(instance.get(11));
        assertTrue(instance.get(2));
        instance.debugPrint("testSetAndGetEdge");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLowerEdgeOut() {
        Sieve instance = new Sieve(10, 2);
        instance.set(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testUpperEdgeOut() {
        Sieve instance = new Sieve(10, 2);
        instance.set(12);
    }

    @Test
    public void testIncrement() {
        Sieve instance = new Sieve(10, 2);
        for (int i = 2; i < (int) instance.getEndIndex().longValue(); i++) {
            instance.set(BigInteger.valueOf(i));
        }
        instance.increment();
        assertEquals(instance.getStartIndex(), BigInteger.valueOf(12));
        assertEquals(instance.getEndIndex(), BigInteger.valueOf(22));
        assertEquals(instance.getMaxIndex(), BigInteger.valueOf(21));
        instance.debugPrint("testIncrement");
    }

}
