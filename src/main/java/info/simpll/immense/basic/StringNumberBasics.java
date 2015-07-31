package info.simpll.immense.basic;

import com.google.common.base.CharMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhathiya on 8/1/2015.
 */
public class StringNumberBasics {

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

    public static String keepDigits(String textWithDigits) {
        return CharMatcher.DIGIT.retainFrom(textWithDigits);
    }

    public static String removeDigits(String textWithDigits) {
        return CharMatcher.DIGIT.removeFrom(textWithDigits);
    }

}
