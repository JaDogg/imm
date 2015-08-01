package info.simpll.immense.factorial;

import info.simpll.immense.basic.BigIntegerBasics;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by Bhathiya on 8/1/2015.
 */
public class FactorialTest {

    @Test
    public void eulerDigitFactorials() {
        Factorial factorials = new Factorial(BigIntegerBasics.TEN_MILLION);
        factorials.calculate();
        for (int i = 0; i < factorials.count(); i++) {
            System.out.println(factorials.get().get(i));
        }
    }

}