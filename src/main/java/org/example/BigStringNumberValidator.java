package org.example;

public class BigStringNumberValidator implements NumberValidator {

    private static final byte ZERO_POSITION_IN_ASCII_TABLE = 48;
    private static final byte NINE_POSITION_IN_ASCII_TABLE = 57;

    @Override
    public void validate(String number) {
        if (number == null) {
            throw new NumberFormatException("null");
        }
        if (number == "") {
            throw new NumberFormatException("empty");
        }
        final byte[] numberAsBytes = number.getBytes();
        for (int i=0; i< numberAsBytes.length; i++) {
            if (numberAsBytes[i] < ZERO_POSITION_IN_ASCII_TABLE
                    || numberAsBytes[i] > NINE_POSITION_IN_ASCII_TABLE) {
                throw new NumberFormatException("For input string: \"" + number + "\"");
            }
        }
    }
}
