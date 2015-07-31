package info.simpll.immense.basic

import com.google.common.base.MoreObjects

public class IterableNumber implements Iterable<BigInteger>, Indexable<BigInteger> {
    public int count
    public byte[] partsAsByte
    public BigInteger[] partsAsBigInteger
    public BigInteger[] consecutiveParts
    public BigInteger original
    private boolean maybeEven
    private boolean[] hasDigit
    private int[] digitCount


    private IterableNumber(int approximateSize) {
        count = 0
        partsAsByte = new byte[approximateSize]
        partsAsBigInteger = new BigInteger[approximateSize]
        consecutiveParts = new BigInteger[approximateSize]
        hasDigit = new boolean[10]
        digitCount = new int[10]

        for (int i = 0; i < 10; i++) {
            digitCount[i] = 0
            hasDigit[i] = false
        }

        original = BigInteger.ZERO
        maybeEven = false
    }

    public IterableNumber(BigInteger value) {
        this((value.bitLength() / 2) + 1 as int)
        BigInteger current = value.add(BigInteger.ZERO)
        int index = -1

        consecutiveParts[0] = current

        while (current > 0) {
            BigInteger[] parts = current.divideAndRemainder(BigInteger.TEN)

            current = parts[0]

            partsAsBigInteger[++index] = parts[1]
            consecutiveParts[index + 1] = current
            byte byteVal = (byte) parts[1].intValue()
            partsAsByte[index] = byteVal
            maybeEven |= ((byteVal & 1) == 1)
            hasDigit[byteVal] = true
            digitCount[byteVal]++
        }

        count = index + 1
        original = value
    }

    @Override
    Iterator<BigInteger> iterator() {
        return new Iterator<BigInteger>() {
            private int pos = 0

            @Override
            public boolean hasNext() {
                return count > pos;
            }

            @Override
            public BigInteger next() {
                return partsAsBigInteger[pos++];
            }
        }
    }

    public BigInteger getLast() {
        return partsAsBigInteger[count - 1]
    }

    public byte getLastAsByte() {
        return partsAsByte[count - 1]
    }

    public BigInteger getFirst() {
        return partsAsBigInteger[0]
    }

    public byte getFirstAsByte() {
        return partsAsByte[0]
    }

    @Override
    public BigInteger get(int index) {
        return partsAsBigInteger[index]
    }

    @Override
    public boolean has(int index) {
        return (index < count && index >= 0)
    }

    @Override
    public boolean size() {
        return count
    }

    public boolean hasEvenParts() {
        return maybeEven
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("count", count)
                .add("partsAsByte", Arrays.toString(partsAsByte))
                .add("consecutiveParts", Arrays.toString(consecutiveParts))
                .add("original", original)
                .add("maybeEven", hasEvenParts())
                .toString()
    }

    public boolean isPandigital() {
        boolean pandigital = true

        for(int i = 0; i < 10; i++) {
            pandigital &= (digitCount[i] == 0 || digitCount[i] == 1)
        }

        return pandigital
    }
}
