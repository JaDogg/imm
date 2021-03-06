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

import com.google.common.base.CharMatcher;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.LongSummaryStatistics;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to handle numbers that represented in strings
 *
 * @author Bhathiya
 */
public class StringNumberBasics {

    public static int SAFE_LONG_STRING_LENGTH = String.valueOf(Long.MAX_VALUE).length() - 1;
    public static int SAFE_INT_STRING_LENGTH = String.valueOf(Integer.MAX_VALUE).length() - 1;
    public static int SAFE_BYTE_STRING_LENGTH = String.valueOf(Byte.MAX_VALUE).length() - 1;

    public static List<Byte> extractDigits(String textWithDigits) {

        List<Byte> digits = new ArrayList<>();
        for (String singleChar : textWithDigits.split("")) {
            try {
                digits.add(Byte.valueOf(singleChar));
            } catch (NumberFormatException ex) {
                // Ignore
            }
        }

        return digits;
    }

    public static List<Long> extractNumbersRight(String text, int digitCount) {
        List<Long> list = new ArrayList<>();
        Pattern regex = Pattern.compile("\\d+");
        Matcher matcher = regex.matcher(text);
        while (matcher.find()) {
            String number = matcher.group();
            list.add(Long.parseLong(number.substring(
                    number.length() - digitCount, number.length())));
        }

        return list;
    }

    public static List<Long> extractNumbersLeft(String text, int digitCount) {
        List<Long> list = new ArrayList<>();
        Pattern regex = Pattern.compile("\\d+");
        Matcher matcher = regex.matcher(text);
        while (matcher.find()) {
            String number = matcher.group();
            list.add(Long.parseLong(number.substring(0, digitCount)));
        }

        return list;
    }

    public static List<BigInteger> extractNumbers(String text) {
        List<BigInteger> list = new ArrayList<>();
        Pattern regex = Pattern.compile("\\d+");
        Matcher matcher = regex.matcher(text);
        while (matcher.find()) {
            String number = matcher.group();
            list.add(new BigInteger(number));
        }

        return list;
    }

    public static String keepDigits(String textWithDigits) {
        return CharMatcher.DIGIT.retainFrom(textWithDigits);
    }

    public static String removeDigits(String textWithDigits) {
        return CharMatcher.DIGIT.removeFrom(textWithDigits);
    }

}
