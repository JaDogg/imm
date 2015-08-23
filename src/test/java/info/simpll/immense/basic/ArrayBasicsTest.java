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
package info.simpll.immense.basic;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Bhathiya
 */
public class ArrayBasicsTest {

    @Test
    public void testApi() {
        //List<Integer> integers = Lists.newArrayList(10, 20, 30, 40);
        Byte[] array1 = {12, 12, 2, 32, 3};
        Integer[] array2 = {12, 12, 2, 32, 3};
        Long[] array3 = {12l, 12l, 2l, 32l, 3l};
        Double[] array4 = {12.0, 12.0, 2.0, 32.0, 3.0};
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Long> list2 = Lists.newArrayList(1l, 2l, 3l, 4l, 5l);

        System.out.println(Arrays.toString(ArrayBasics.toDoubleArray(array1)));
        System.out.println(Arrays.toString(ArrayBasics.toDoubleArray(array2)));
        System.out.println(Arrays.toString(ArrayBasics.toDoubleArray(array3)));
        System.out.println(Arrays.toString(ArrayBasics.toDoubleArray(array4)));
        System.out.println(Arrays.toString(ArrayBasics.toDoubleArray(list1)));
        System.out.println(Arrays.toString(ArrayBasics.toDoubleArray(list2)));

    }
}