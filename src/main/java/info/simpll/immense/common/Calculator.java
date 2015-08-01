package info.simpll.immense.common;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Bhathiya on 8/1/2015.
 */
public interface Calculator {
    public void calculate();
    public BigInteger getLast();
    public List<BigInteger> get();
    public int count();
}
