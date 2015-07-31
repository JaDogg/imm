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
 * Sieve holds a BitSet that is used to cross out numbers,
 * However unlike BitSet, bitSet uses different indexing scheme
 * Which is more suitable for prime number calculation
 *
 * @author Bhathiya
 */
public class Sieve {

    public static int MAX_SIZE = 8 * 1024 * 1024;

    private final int size;
    private BigInteger startIndex;
    private BigInteger endIndex;
    private final BitSet bitSet;

    /**
     * Constructor for the bitSet
     *
     * @param size       count of the bitSet
     * @param startIndex starting index of the bitSet
     */
    public Sieve(int size, BigInteger startIndex) {

        if (size > MAX_SIZE || size < 10) {
            throw new IllegalArgumentException("Size is larger than "
                    + "the allowed maximum or smaller than 10");
        }
        // TODO count > 2
        this.size = size;
        this.startIndex = startIndex;
        endIndex = startIndex.add(BigInteger.valueOf(size));
        bitSet = new BitSet(size);
    }

    public Sieve(int size, long startIndex) {
        this(size, BigInteger.valueOf(startIndex));
    }

    public boolean get(BigInteger index) {
        return bitSet.get(calculateIndex(index));
    }

    public void set(BigInteger index) {
        bitSet.set(calculateIndex(index), true);
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
        // This should not be a problem since we are ensuring that the count
        // Is less than MAX_SIZE, which is an integer
        return (int) (index.subtract(getStartIndex()).longValue());
    }

    public void clear() {
        bitSet.clear();
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


    public Sieve increment() {
        return increment(true);
    }

    /**
     * Increment the bitSet, use this method once the bitSet is exhausted.
     * If count is 100 and start is 2, then endIndex is 102
     * After increment startIndex is 102
     *
     * @param performClear if true this will clear the BitSet
     * @return this
     */
    public Sieve increment(boolean performClear) {
        if (performClear) clear();
        startIndex = endIndex;
        endIndex = startIndex.add(BigInteger.valueOf(size));
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("count", size)
                .add("startIndex", startIndex)
                .add("endIndex", endIndex)
                .add("bitSet", bitSet)
                .toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.size;
        hash = 71 * hash + Objects.hashCode(this.startIndex);
        hash = 71 * hash + Objects.hashCode(this.endIndex);
        hash = 71 * hash + Objects.hashCode(this.bitSet);
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
        return Objects.equals(this.bitSet, other.bitSet);
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
