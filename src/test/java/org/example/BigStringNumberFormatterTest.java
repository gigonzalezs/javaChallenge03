package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BigStringNumberFormatterTest extends NumbersBaseTest {

    private BigStringNumberFormatter sut;

    @BeforeEach
    public void setUp() {
        super.setUp();
        sut = new BigStringNumberFormatter();
    }

    @Test
    void GivenOneStringNumberReturnArrayOfDigits() {
        final BigInteger number = getRandomBigInteger(128);
        final int numberSize = getBigIntegerSize(number);
        final byte[] numberAsArray = sut.stringNumberToDigitArray(number.toString());
        assertEquals(numberSize, numberAsArray.length);
        for (int i=0; i< numberSize; i++) {
            final String expected = number.toString().substring(i, i+1);
            final String actual = String.valueOf(numberAsArray[i]);
            assertEquals(expected, actual);
        }
    }

    @Test
    void GivenOneStringNumberWithLessDigitsReturnNumberWithLeadingZeros() {
        final BigInteger number = getRandomBigInteger(8);
        final int expected = 10;
        final String numberWithLeadingZeros = sut.addLeadingZeros(number.toString(), expected);
        final int actual = numberWithLeadingZeros.length();
        assertEquals(expected, actual);
    }

    @Test
    void GivenAnArrayOfDigitsReturnStringRepresentation() {
        final byte[] numberAsArray = new byte[] {1, 2, 0, 3 ,4, 5, 0};
        final String expected = "1203450";
        final String actual = sut.digitArrayToStringNumber(numberAsArray);
        assertEquals(expected, actual);
    }

    @Test
    void GivenAnArrayOfDigitsWithLeadingZerosReturnStringRepresentationWithoutLeadingZeros() {
        final byte[] numberAsArray = new byte[] {0, 0, 0, 1, 2, 0, 3 ,4, 5, 0};
        final String expected = "1203450";
        final String actual = sut.digitArrayToStringNumber(numberAsArray);
        assertEquals(expected, actual);
    }
}