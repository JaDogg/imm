package info.simpll.immense.basic

import com.google.common.base.MoreObjects
import info.simpll.immense.common.Indexable

public class IterableNumber implements Iterable<BigInteger>, Indexable<BigInteger> {
    private int count
    private int lastIndex
    private byte[] partsAsByte
    private BigInteger[] partsAsBigInteger
    private BigInteger[] leftConsecutiveParts
    private BigInteger[] rightConsecutiveParts
    private BigInteger original
    private boolean maybeEven
    private boolean[] hasDigit
    private int[] digitCount

    private IterableNumber(int approximateSize) {
        count = 0
        lastIndex = -1
        partsAsByte = new byte[approximateSize]
        partsAsBigInteger = new BigInteger[approximateSize]
        leftConsecutiveParts = new BigInteger[approximateSize]
        rightConsecutiveParts = new BigInteger[approximateSize]
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

        while (current > 0) {
            BigInteger[] parts = current.divideAndRemainder(BigInteger.TEN)
            current = parts[0]
            partsAsBigInteger[++index] = parts[1]
            leftConsecutiveParts[index] = current
            byte byteVal = (byte) parts[1].intValue()
            partsAsByte[index] = byteVal
            if ((byteVal % 2) == 0) {
                maybeEven = true
            }
            hasDigit[byteVal] = true
            digitCount[byteVal]++
        }

        count = index + 1
        lastIndex = index
        original = value

        BigInteger recalculated = BigInteger.ZERO
        BigInteger powerOfTen = BigInteger.ONE
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                rightConsecutiveParts[i - 1] = recalculated
            }
            recalculated += partsAsBigInteger[i].multiply(powerOfTen)
            powerOfTen = powerOfTen.multiply(BigInteger.TEN)
        }

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
        return partsAsBigInteger[lastIndex]
    }

    public byte getLastAsByte() {
        return partsAsByte[lastIndex]
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
    public int size() {
        return count
    }

    public boolean hasEvenParts() {
        return maybeEven
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("partsAsByte", Arrays.toString(partsAsByte))
                .add("leftConsecutiveParts", Arrays.toString(leftConsecutiveParts))
                .add("rightConsecutiveParts", Arrays.toString(rightConsecutiveParts))
                .toString()
    }

    public boolean isPandigital() {
        boolean pandigital = true

        for (int i = 0; i < 10; i++) {
            pandigital &= (digitCount[i] == 0 || digitCount[i] == 1)
        }

        return pandigital
    }

    public BigInteger build() {
        BigInteger recalculated = BigInteger.ZERO
        BigInteger powerOfTen = BigInteger.ONE
        for (int i = 0; i < count; i++) {
            recalculated += partsAsBigInteger[i].multiply(powerOfTen)
            powerOfTen = powerOfTen.multiply(BigInteger.TEN)
        }
        return recalculated
    }

    public rotate() {
        // TODO this should be a faster if this is a linked list
        byte lastByte = partsAsByte[lastIndex]
        BigInteger lastBigInt = partsAsBigInteger[lastIndex]
        for (int i = lastIndex - 1; i >= 0; i--) {
            partsAsByte[i + 1] = partsAsByte[i]
            partsAsBigInteger[i + 1] = partsAsBigInteger[i]
        }
        partsAsByte[0] = lastByte
        partsAsBigInteger[0] = lastBigInt
    }

    public IterableNumber reverse() {
        for (int i = 0; i < count; i++) {
            BigInteger bigIntTemp = partsAsBigInteger[i]
            byte byteTemp = partsAsByte[i]

            partsAsBigInteger[i] = partsAsBigInteger[lastIndex - i]
            partsAsBigInteger[lastIndex - i] = bigIntTemp

            partsAsByte[i] = partsAsByte[lastIndex - i]
            partsAsByte[lastIndex - i] = byteTemp
        }
        return this
    }

    public IterableNumber cloneReversed() {
        BigInteger recalculated = BigInteger.ZERO
        BigInteger powerOfTen = BigInteger.ONE
        for (int i = lastIndex; i >= 0; i--) {
            recalculated += partsAsBigInteger[i].multiply(powerOfTen)
            powerOfTen = powerOfTen.multiply(BigInteger.TEN)
        }
        return new IterableNumber(recalculated)
    }

    public IterableNumber clone() {
        return new IterableNumber(original)
    }

    public int getCount() {
        return count
    }

    public int getLastIndex() {
        return lastIndex
    }

    public byte[] getPartsAsByte() {
        return partsAsByte
    }

    public BigInteger[] getPartsAsBigInteger() {
        return partsAsBigInteger
    }

    public BigInteger[] getLeftConsecutiveParts() {
        return leftConsecutiveParts
    }

    public BigInteger[] getRightConsecutiveParts() {
        return rightConsecutiveParts
    }

    public boolean isPalindrome() {
        for (int i = 0; i < count / 2; i++) {
            if (partsAsByte[i] != partsAsByte[lastIndex - i]) {
                return false
            }
        }
        return true
    }
}
