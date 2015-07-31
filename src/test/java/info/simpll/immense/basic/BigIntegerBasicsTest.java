package info.simpll.immense.basic;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;


public class BigIntegerBasicsTest {

    @Test
    public void testSplitNumber() throws Exception {
        BigInteger b = BigInteger.valueOf(123456789);
        IterableNumber in = BigIntegerBasics.splitNumber(b);
        System.out.println(in);
    }
}