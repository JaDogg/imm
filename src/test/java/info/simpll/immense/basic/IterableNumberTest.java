package info.simpll.immense.basic;

import info.simpll.immense.prime.Prime;
import info.simpll.immense.sequence.Sequence;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by Bhathiya on 7/31/2015.
 */
public class IterableNumberTest {

    @Test
    public void eulerLargestPandigital() throws Exception {

        // TODO This test case should produce the answer
        BigInteger largestPossible = BigInteger.valueOf(1000);
        Prime primes = new Prime(largestPossible);
        Sequence<BigInteger> primeSequence;
        primes.calculate();
        primeSequence = new Sequence<>(primes.get());
        primeSequence.initialize();

        Object[] array = primeSequence.getArray();
        for (int i = primes.count() - 1; i > 0; i--) {
            BigInteger current = (BigInteger) array[i];
            if (BigIntegerBasics.splitNumber(current).isPandigital()) {
                System.out.printf("Answer to Euler 41 = %s%n", current.toString());
            }
        }
    }
}