package org.example;

public class BigStringNumberFormatter implements NumberFormatter {

    private  final byte ZERO_POSITION_IN_ASCII_TABLE = 48;

    @Override
    public byte[] stringNumberToDigitArray(String number) {
        return stringNumberToDigitArray(number, number.trim().length());
    }

    @Override
    public byte[] stringNumberToDigitArray(String number, int size) {
        final String numberWithLeadingZeros = addLeadingZeros(number, size);
        byte[] numberAsBytes = numberWithLeadingZeros.getBytes();
        for (int i=0; i< numberAsBytes.length; i++) {
            numberAsBytes[i] = (byte) (numberAsBytes[i] - ZERO_POSITION_IN_ASCII_TABLE);
        }
        return numberAsBytes;
    }

    @Override
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

    @Override
    public String addLeadingZeros(String number, int expectedSize) {
        final int currentSize = number.trim().length();
        if (currentSize >= expectedSize) {
            return number;
        }
        final int leadingZeros = expectedSize - currentSize;
        return String.format("%0"+ leadingZeros +"d", 0) + number;
    }
}
