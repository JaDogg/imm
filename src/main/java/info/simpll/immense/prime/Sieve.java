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

import com.google.common.base.MoreObjects;
import java.math.BigInteger;
import java.util.BitSet;
import java.util.Objects;
import java.util.stream.IntStream;
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
     *
     * @param size size of the sieve
     * @param startIndex starting index of the sieve
     */
    public Sieve(int size, BigInteger startIndex) {

        if (size > MAX_SIZE || size < 10) {
            throw new IllegalArgumentException("Size is larger than "
                    + "the allowed maximum or smaller than 10");
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

    private int calculateIndex(BigInteger index) {

        if (getEndIndex().compareTo(index) <= 0
                || getStartIndex().compareTo(index) == 1) {
            throw new IndexOutOfBoundsException("Invalid position");
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

    public BigInteger getMaxIndex() {
        return endIndex.subtract(BigInteger.ONE);
    }

    public void increment() {
        clear();
        startIndex = endIndex;
        endIndex = startIndex.add(BigInteger.valueOf(size));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("size", size)
                .add("startIndex", startIndex)
                .add("endIndex", endIndex)
                .add("sieve", sieve)
                .toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.size;
        hash = 71 * hash + Objects.hashCode(this.startIndex);
        hash = 71 * hash + Objects.hashCode(this.endIndex);
        hash = 71 * hash + Objects.hashCode(this.sieve);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sieve other = (Sieve) obj;
        if (this.size != other.size) {
            return false;
        }
        if (!Objects.equals(this.startIndex, other.startIndex)) {
            return false;
        }
        if (!Objects.equals(this.endIndex, other.endIndex)) {
            return false;
        }
        return Objects.equals(this.sieve, other.sieve);
    }

    public void debugPrint(String prepend) {
        StringBuilder primeList = new StringBuilder();
        IntStream.rangeClosed(0, size - 1).forEach(i -> {
            BigInteger index = startIndex.add(BigInteger.valueOf(i));
            if (!get(index)) {
                primeList.append(" ");
                primeList.append(index.toString(10));
            }
        });
        System.out.printf("SIEVE: %s %s: Unset {%s }\n",
                prepend, this.toString(), primeList);
    }
}
