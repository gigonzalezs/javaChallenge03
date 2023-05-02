package org.example;

public interface NumberFormatter {

    byte[] stringNumberToDigitArray(String number);

    byte[] stringNumberToDigitArray(String number, int size);

    String digitArrayToStringNumber(byte[] digitArray);

    String addLeadingZeros(String number, int expectedSize);
}
