package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BigStringNumbersTest {

    @Test
    void WhenBothAddendsAreProvidedReturnTheSumOfThem() {
        final Random rnd = new Random();
        final BigInteger addend = BigInteger.probablePrime(128, rnd);
        final BigInteger anotherAddend = BigInteger.probablePrime(128, rnd);
        final String expected = addend.add(anotherAddend).toString();
        final String actual = BigStringNumbers.add(addend.toString(), anotherAddend.toString());
        assertEquals(expected, actual);
    }
}