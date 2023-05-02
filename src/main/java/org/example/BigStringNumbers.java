package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class BigStringNumbers {
    private  final int BOARD_TOTAL_ROWS = 4;
    private  final int CARRY_ROW = 0;
    private  final int FIRST_ADDEND_ROW = 1;
    private  final int SECOND_ADDEND_ROW = 2;
    private  final int RESULT_ROW = 3;
    private  final int TEN = 10;

    private final NumberValidator validator;
    private final NumberFormatter formatter;

    public BigStringNumbers(NumberValidator validator, NumberFormatter formatter) {
        this.validator = validator;
        this.formatter = formatter;
    }

    public String addNumbers(String aNumber, String anotherNumber) {
        validator.validate(aNumber);
        validator.validate(anotherNumber);
        final int maxDigits = getMaxDigits(aNumber, anotherNumber) + 1;
        final byte[][] board = new byte[BOARD_TOTAL_ROWS][maxDigits];
        board[1] = formatter.stringNumberToDigitArray(aNumber, maxDigits);
        board[2] = formatter.stringNumberToDigitArray(anotherNumber, maxDigits);

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
        return formatter.digitArrayToStringNumber(board[RESULT_ROW]);
    }

    public int getMaxDigits(String... numbers) {
        return Arrays.stream(numbers)
                .map(number -> number.trim().length() )
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

}
