package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class BigStringNumbers {

    private BigStringNumbers() {}

    public static String add(String aNumber, String anotherNumber) {
        final int maxLength = getMaxLength(aNumber, anotherNumber) + 1;
        final byte[][] board = new byte[2][maxLength];
        return null;
    }

    public static int getMaxLength(String... numbers) {
        return Arrays.stream(numbers)
                .map(n ->  n.trim().length() )
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

}
