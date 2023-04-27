package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BigStringNumbers {

    private BigStringNumbers() {}

    public static String add(String aNumber, String anotherNumber) {
        final int maxLength = getMaxLength(aNumber, anotherNumber) + 1;
        final byte[][] board = new byte[4][maxLength];
        board[1] = toByteArray(aNumber, maxLength);
        board[2] = toByteArray(anotherNumber, maxLength);

        for (int i = (maxLength-1); i > 0; i--) {
            int sumByColumn = board[0][i] + board[1][i] + board[2][i];
            if (sumByColumn > 10) {
                sumByColumn = sumByColumn - 10;
                board[0][i-1] = 1;
            }
            board[3][i] = (byte) sumByColumn;
        }
        return null;
    }



    public static int getMaxLength(String... numbers) {
        return Arrays.stream(numbers)
                .map(n ->  n.trim().length() )
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    public static byte[] toByteArray(String number) {
        return toByteArray(number, number.trim().length());
    }

    public static byte[] toByteArray(String number, int size) {
        final String numberWithLeadingZeros = addLeadingZeros(number, size);
        final byte zeroPositionInAsciiTable = 48;
        byte[] numberAsBytes = numberWithLeadingZeros.getBytes();
        for (int i=0; i< numberAsBytes.length; i++) {
            numberAsBytes[i] = (byte) (numberAsBytes[i] - zeroPositionInAsciiTable);
        }
        return numberAsBytes;
    }

    public static String addLeadingZeros(String number, int expectedSize) {
        final int currentSize = number.trim().length();
        if (currentSize >= expectedSize) return number;
        final int leadingZeros = expectedSize - currentSize;
        return String.format("%0"+ leadingZeros +"d", 0) + number;
    }
}
