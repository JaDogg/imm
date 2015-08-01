package info.simpll.immense.factorial

import com.google.common.collect.Lists
import info.simpll.immense.common.Calculator

/**
 * Created by Bhathiya on 8/1/2015.
 */
class Factorial implements Calculator {

    private BigInteger upTo
    private BigInteger last
    private List<BigInteger> factorials

    public Factorial(BigInteger upTo) {
        this.upTo = upTo
        last = 0g
        factorials = Lists.newArrayList(1g, 1g)
        last = 2g
    }

    @Override
    public void calculate() {
        BigInteger current = 2g
        BigInteger base = 2g
        while (current <= upTo) {
            factorials.add(current)
            last = current
            current *= ++base;

        }
    }

    @Override
    BigInteger getLast() {
        return last
    }

    @Override
    List<BigInteger> get() {
        return factorials
    }

    @Override
    int count() {
        return factorials.size()
    }
}
