package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigStringNumberValidatorTest extends NumbersBaseTest {

    private BigStringNumberValidator sut;

    @BeforeEach
    public void setUp() {
        super.setUp();
        sut = new BigStringNumberValidator();
    }

    @Test
    public void GivenAPositiveIntegerNumberMustValidateSuccessfully(){
        final String number = "12345";
        sut.validate(number);
    }

    @Test
    public void GivenABigPositiveIntegerNumberMustValidateSuccessfully(){
        final String number = getRandomBigInteger().toString();
        sut.validate(number);
    }

    @Test
    public void GivenANegativeIntegerNumberMustThrownException(){
        final String number = "-12345";
        final String expected = "For input string: \"" + number + "\"";
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            sut.validate(number);
        });
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void GivenANonNumberMustThrownException(){
        final String number = "abdc";
        final String expected = "For input string: \"" + number + "\"";
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            sut.validate(number);
        });
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void GivenANullNumberMustThrownException(){
        final String expected = "null";
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            sut.validate(null);
        });
        assertEquals(expected, exception.getMessage());
    }

}