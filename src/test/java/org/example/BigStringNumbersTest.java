package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BigStringNumbersTest {

    private Random rnd;

    @BeforeEach
    void setUp() {
        rnd = new Random();
    }

    private BigInteger getRandomBigInteger() {
        return getRandomBigInteger(rnd.nextInt(126) + 2);
    }

    private BigInteger getRandomBigInteger(int bitLength) {
        return BigInteger.probablePrime(bitLength, rnd);
    }

    private int getBigIntegerSize(BigInteger number) {
        return number.toString().trim().length();
    }

    @Test
    void GivenBothAddendsWhenAddReturnsTheSumOfThem() {

        final BigInteger addend = getRandomBigInteger(128);
        final BigInteger anotherAddend = getRandomBigInteger(64);
        final String expected = addend.add(anotherAddend).toString();
        final String actual = BigStringNumbers.add(addend.toString(), anotherAddend.toString());
        assertEquals(expected, actual);
    }

    @Test
    void GivenTwoStringsReturnsTheMaxLengthOfThem() {
        final BigInteger first = getRandomBigInteger();
        final int firstSize = getBigIntegerSize(first);
        final BigInteger second = getRandomBigInteger();
        final int secondSize = getBigIntegerSize(second);
        final int expected = (firstSize >= secondSize)? firstSize : secondSize;
        final int actual = BigStringNumbers.getMaxLength(first.toString(), second.toString());
        assertEquals(expected, actual);
    }
}