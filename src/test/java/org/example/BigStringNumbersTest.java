package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BigStringNumbersTest extends NumbersBaseTest {

    private static final VerificationMode ONCE = times(1);

    private BigStringNumbers sut;
    @Mock
    private BigStringNumberValidator validator;
    @Spy
    private BigStringNumberFormatter formatter;

    @BeforeEach
    public void setUp() {
        super.setUp();
        sut = new BigStringNumbers(validator, formatter);
    }

    @Test
    void GivenBothAddendsWhenAddReturnsTheSumOfThem() {
        final BigInteger addend = getRandomBigInteger(128);
        final BigInteger anotherAddend = getRandomBigInteger(128);
        final String expected = addend.add(anotherAddend).toString();
        doNothing().when(validator).validate(any());
        final String actual = sut.addNumbers(addend.toString(), anotherAddend.toString());

        assertEquals(expected, actual);
        Mockito.verify(validator).validate(eq(addend.toString()));
        Mockito.verify(validator).validate(eq(anotherAddend.toString()));

        final int maxDigits = 1 + sut.getMaxDigits(addend.toString(), anotherAddend.toString());
        Mockito.verify(formatter).stringNumberToDigitArray(eq(addend.toString()), eq(maxDigits));
        Mockito.verify(formatter).stringNumberToDigitArray(eq(anotherAddend.toString()), eq(maxDigits));
        Mockito.verify(formatter, ONCE).digitArrayToStringNumber(any());

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
        Mockito.verify(validator).validate(eq(addend.toString()));
        Mockito.verify(validator).validate(eq(anotherAddend.toString()));

        final int maxDigits = 1 + sut.getMaxDigits(addend.toString(), anotherAddend.toString());
        Mockito.verify(formatter).stringNumberToDigitArray(eq(addend.toString()), eq(maxDigits));
        Mockito.verify(formatter).stringNumberToDigitArray(eq(anotherAddend.toString()), eq(maxDigits));
        Mockito.verify(formatter, ONCE).digitArrayToStringNumber(any());
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

}