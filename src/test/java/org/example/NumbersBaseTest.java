package org.example;

import org.junit.jupiter.api.BeforeEach;

import java.math.BigInteger;
import java.util.Random;

public abstract class NumbersBaseTest {
    private Random rnd;

    protected void setUp() {
        rnd = new Random();
    }

    protected BigInteger getRandomBigInteger() {
        return getRandomBigInteger(rnd.nextInt(126) + 2);
    }

    protected BigInteger getRandomBigInteger(int bitLength) {
        return BigInteger.probablePrime(bitLength, rnd);
    }

    protected int getBigIntegerSize(BigInteger number) {
        return number.toString().trim().length();
    }
}
