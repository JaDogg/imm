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

    public initialize() {
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
                .add("cumulativeSums", Arrays.toString(cumulativeSums))
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
