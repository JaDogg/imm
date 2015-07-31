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
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Bhathiya
 */
public class JPrimeTest {

    @Test
    public void testCreateObject() {
        JPrime prime = new JPrime(30);
        prime.printUnset();
    }

    @Test
    public void testFirstFewPrimes() {
        JPrime prime = new JPrime(102); 
        
        prime.calculate();
        String expected = Joiner.on(", ").join(PrimeList.get().subList(0, prime.size()));
        String actual = Joiner.on(", ").join(prime.get());
        
        System.out.println("Calculated = " + actual);
        System.out.println("Stored     = " + expected);
        Assert.assertEquals(expected, actual);
    }
}
