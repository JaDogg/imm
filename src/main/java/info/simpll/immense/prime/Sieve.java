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

import java.math.BigInteger;
import java.util.BitSet;

/**
 *
 * @author Bhathiya
 */
public class Sieve {
    public static int MAX_SIZE = 8 * 1024 * 1024;
    
    private final int size;
    private BigInteger startIndex;
    private BigInteger endIndex;
    private final BitSet sieve;
    
    /**
     * Constructor for the sieve
     * @param size size of the sieve
     * @param startIndex starting index of the sieve
     */
    public Sieve(int size, BigInteger startIndex) {
        
        if (size > MAX_SIZE || size < 2) {
            throw new RuntimeException("Size is larger than "
                    + "the allowed maximum or smaller than 2");
        }
        
        this.size = size;
        this.startIndex = startIndex;
        endIndex = startIndex.add(BigInteger.valueOf(size));
        sieve = new BitSet(size);
    }
    
    public Sieve(int size, long startIndex) {
        this(size, BigInteger.valueOf(startIndex));
    }
    
    public boolean get(BigInteger index) {
        return sieve.get(calculateIndex(index));
    }
    
    public void set(BigInteger index) {
        sieve.set(calculateIndex(index), true);
    }
    
    public boolean get(long index) {
        return get(BigInteger.valueOf(index));
    }
    
    public void set(long index) {
        set(BigInteger.valueOf(index));
    }
            
    private int calculateIndex(BigInteger index) throws RuntimeException {
       
        if (getEndIndex().compareTo(index) == 1 
                || getStartIndex().compareTo(index) == -1 ) {
            throw new RuntimeException("Invalid position");
        }
        // This should not be a problem since we are ensuring that the size
        // Is less than MAX_SIZE, which is an integer
        return (int) (index.subtract(getStartIndex()).longValue());       
    }
    
    public void clear() {
        sieve.clear();
    }

    public int getSize() {
        return size;
    }

    public BigInteger getStartIndex() {
        return startIndex;
    }

    public BigInteger getEndIndex() {
        return endIndex;
    }
    
    public void increment() {
        clear();
        startIndex = endIndex.add(BigInteger.ONE);
        endIndex = startIndex.add(BigInteger.valueOf(size));
    }
}
