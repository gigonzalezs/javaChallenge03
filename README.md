# Adding Big String Numbers

This is a Java program that provides a function for adding two arbitrarily large non-negative numbers represented as strings.

## Usage

The `addNumbers` function takes two strings as input, representing the non-negative numbers to be added. Here's an example usage:

```java
String result = addNumbers("123456789012345678901", "12345678");
System.out.println(result); // prints "123456789012358024579"
```

## Remarks

The `addNumbers` function adds two arbitrarily large non-negative numbers represented as strings, using an array-based approach with a matrix-like data structure called board. The function uses a validator object to check that the input strings only contain valid characters (digits 0-9), and a formatter object to convert the input strings into arrays of digits and vice versa. The board array has four rows: CARRY_ROW, FIRST_ADDEND_ROW, SECOND_ADDEND_ROW, and RESULT_ROW, each representing a component of the addition operation. The board is used to add the digits together, starting from the rightmost digit. The formatter.stringNumberToDigitArray function adds leading zeros to make the input strings the same length.

