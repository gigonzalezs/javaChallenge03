package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BigStringNumbersTest extends NumbersBaseTest {

    private BigStringNumbers sut;

    @Mock
    private BigStringNumberValidator validator;
    @BeforeEach
    public void setUp() {
        super.setUp();
        sut = new BigStringNumbers(validator);
    }

    @Test
    void GivenBothAddendsWhenAddReturnsTheSumOfThem() {
        final BigInteger addend = getRandomBigInteger(128);
        final BigInteger anotherAddend = getRandomBigInteger(128);
        final String expected = addend.add(anotherAddend).toString();
        doNothing().when(validator).validate(any());
        final String actual = sut.addNumbers(addend.toString(), anotherAddend.toString());
        assertEquals(expected, actual);
    }

    @Test
    void GivenBothAddendsStartingWithDigitNineWhenAddReturnsTheSumOfThem() {
        final BigInteger addend = new BigInteger( "9" + getRandomBigInteger(128)
                .toString().substring(1));
        final BigInteger anotherAddend = new BigInteger( "9" + getRandomBigInteger(128)
                .toString().substring(1));
        final String expected = addend.add(anotherAddend).toString();
        doNothing().when(validator).validate(any());
        final String actual = sut.addNumbers(addend.toString(), anotherAddend.toString());
        assertEquals(expected, actual);
    }

    @Test
    void GivenTwoStringsReturnsTheMaxLengthOfThem() {
        final BigInteger first = getRandomBigInteger();
        final int firstSize = getBigIntegerSize(first);
        final BigInteger second = getRandomBigInteger();
        final int secondSize = getBigIntegerSize(second);
        final int expected = (firstSize >= secondSize)? firstSize : secondSize;
        final int actual = sut.getMaxDigits(first.toString(), second.toString());
        assertEquals(expected, actual);
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
    void GivenOneStringNumberWithLessDigitsReturnNumberWithLeadinZeros() {
        final BigInteger number = getRandomBigInteger(8);
        final int expected = 10;
        final String numberWithLeadingZeros = sut.addLeadingZeros(number.toString(), expected);
        final int actual = numberWithLeadingZeros.length();
        assertEquals(expected, actual);
    }

    @Test
    void GivenAnArrayOfDigitsReturnStringRepresentation() {
        final byte[] numberAsArray = new byte[] {0, 0, 0, 1, 2, 0, 3 ,4, 5, 0};
        final String expected = "1203450";
        final String actual = sut.digitArrayToStringNumber(numberAsArray);
        assertEquals(expected, actual);
    }
}