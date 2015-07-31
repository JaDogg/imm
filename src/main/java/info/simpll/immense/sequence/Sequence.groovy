package info.simpll.immense.sequence

import com.google.common.base.MoreObjects

/**
 * Sequence of elements, elements must be unique
 *
 * @author Bhathiya
 */
class Sequence<T> extends ArrayList<T> {

    private T[] consecutiveSums

    private T[] consecutiveDiffs

    private T[] cumulativeSums
    private T[] reversedCumulativeSums

    private T[] array

    private Set<T> set

    public Sequence(List<T> values) {
        super(values)
    }

    public calculate() {
        consecutiveSums = new T[this.size()]
        consecutiveDiffs = new T[this.size()]

        cumulativeSums = new T[this.size()]
        reversedCumulativeSums = new T[this.size()]

        array = new T[this.size()]

        set = new HashSet<T>(this)

        def prev = 0
        def prevSum = 0
        def lastIndex = size() - 1
        for (int i = 0; i < size(); i++) {
            def current = this.get(i)
            consecutiveSums[i] = prev + current
            consecutiveDiffs[i] = prev - current

            cumulativeSums[i] = prevSum + current
            reversedCumulativeSums[lastIndex - i] = cumulativeSums[i]

            array[i] = current

            prev = current
            prevSum = cumulativeSums[i]
        }


    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("size", size())
                .add("consecutiveSums", Arrays.toString(consecutiveSums))
                .add("consecutiveDiffs", Arrays.toString(consecutiveDiffs))
                .add("cumulativeSums", Arrays.toString(cumulativeSums))
                .add("reversedCumulativeSums", Arrays.toString(reversedCumulativeSums))
                .add("array", Arrays.toString(array))
                .toString()
    }

    public boolean contains(T value) {
        return set.contains(value)
    }

    Object[] getConsecutiveSums() {
        return consecutiveSums
    }

    Object[] getConsecutiveDiffs() {
        return consecutiveDiffs
    }

    Object[] getCumulativeSums() {
        return cumulativeSums
    }

    Object[] getReversedCumulativeSums() {
        return reversedCumulativeSums
    }

    Object[] getArray() {
        return array
    }
}
