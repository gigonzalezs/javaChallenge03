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

        final BigInteger addend = getRandomBigInteger(12);
        final BigInteger anotherAddend = getRandomBigInteger(8);
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

    @Test
    void GivenOneStringNumberReturnArrayOfDigits() {
        final BigInteger number = getRandomBigInteger(128);
        final int numberSize = getBigIntegerSize(number);
        final byte[] numberAsArray = BigStringNumbers.toByteArray(number.toString());
        assertEquals(numberSize, numberAsArray.length);
        for (int i=0; i< numberSize; i++) {
            final String expected = number.toString().substring(i, i+1);
            final String actual = String.valueOf(numberAsArray[i]);
            assertEquals(expected, actual);
        }
    }

    @Test
    void GivenOneStringNumberWithLessDigitsReturnNumberWithLeadinZeros() {
        final BigInteger number = getRandomBigInteger(8);
        final int expected = 10;
        final String numberWithLeadingZeros = BigStringNumbers.addLeadingZeros(number.toString(), expected);
        final int actual = numberWithLeadingZeros.length();
        assertEquals(expected, actual);
    }
}