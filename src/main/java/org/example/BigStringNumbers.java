package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class BigStringNumbers {

    private  final byte ZERO_POSITION_IN_ASCII_TABLE = 48;
    private  final int BOARD_TOTAL_ROWS = 4;
    private  final int CARRY_ROW = 0;
    private  final int FIRST_ADDEND_ROW = 1;
    private  final int SECOND_ADDEND_ROW = 2;
    private  final int RESULT_ROW = 3;
    private  final int TEN = 10;

    private final NumberValidator validator;

    public BigStringNumbers(NumberValidator validator) {
        this.validator = validator;
    }

    public String addNumbers(String aNumber, String anotherNumber) {
        validator.validate(aNumber);
        validator.validate(anotherNumber);
        final int maxDigits = getMaxDigits(aNumber, anotherNumber) + 1;
        final byte[][] board = new byte[BOARD_TOTAL_ROWS][maxDigits];
        board[1] = stringNumberToDigitArray(aNumber, maxDigits);
        board[2] = stringNumberToDigitArray(anotherNumber, maxDigits);

        for (int column = (maxDigits - 1); column >= 0; column--) {
            int columnSum = board[CARRY_ROW][column]
                    + board[FIRST_ADDEND_ROW][column]
                    + board[SECOND_ADDEND_ROW][column];
            if (columnSum >= TEN) {
                columnSum -= TEN;
                board[CARRY_ROW][column - 1] = 1;
            }
            board[RESULT_ROW][column] = (byte) columnSum;
        }
        return digitArrayToStringNumber(board[RESULT_ROW]);
    }

    public int getMaxDigits(String... numbers) {
        return Arrays.stream(numbers)
                .map(number -> number.trim().length() )
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    public byte[] stringNumberToDigitArray(String number) {
        return stringNumberToDigitArray(number, number.trim().length());
    }

    public byte[] stringNumberToDigitArray(String number, int size) {
        final String numberWithLeadingZeros = addLeadingZeros(number, size);
        byte[] numberAsBytes = numberWithLeadingZeros.getBytes();
        for (int i=0; i< numberAsBytes.length; i++) {
            numberAsBytes[i] = (byte) (numberAsBytes[i] - ZERO_POSITION_IN_ASCII_TABLE);
        }
        return numberAsBytes;
    }

    public String digitArrayToStringNumber(byte[] digitArray) {
        boolean currentDigitCanBeTrailingZero = true;
        for (int i=0; i< digitArray.length; i++) {
            if (currentDigitCanBeTrailingZero && digitArray[i] == 0) {
                digitArray[i] = 32;

            } else {
                currentDigitCanBeTrailingZero = false;
                digitArray[i] = (byte) (digitArray[i] + ZERO_POSITION_IN_ASCII_TABLE);
            }
        }
        return new String(digitArray).trim();
    }

    public String addLeadingZeros(String number, int expectedSize) {
        final int currentSize = number.trim().length();
        if (currentSize >= expectedSize) {
            return number;
        }
        final int leadingZeros = expectedSize - currentSize;
        return String.format("%0"+ leadingZeros +"d", 0) + number;
    }
}
