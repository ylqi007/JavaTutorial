package com.ylqi007.chap09exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class Demo02UncheckedExceptionTest {


    /**
     * java.lang.NullPointerException
     */
    @Test
    public void testNullPointerException() {
        // java.lang.NullPointerException: Cannot read the array length because "arr" is null
        int[] arr = null;
        Assertions.assertThrows(NullPointerException.class, () -> System.out.println(arr.length));

        String str = null;
        Assertions.assertThrows(NullPointerException.class, () -> System.out.println(str.charAt(0)));
    }

    @Test
    public void testIndexOutOfBoundsException() {
        // java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 10
        int[] arr = new int[10];
        // arr[10] = 1;
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> arr[10] = 1);

        // java.lang.StringIndexOutOfBoundsException: String index out of range: 3
        String str = "abc";
        // System.out.println(str.charAt(3));
        Assertions.assertThrows(StringIndexOutOfBoundsException.class, () -> str.charAt(3));
    }

    @Test
    public void testClassCastException() {
        // java.lang.ClassCastException: class java.util.Date cannot be cast to class java.lang.String (java.util.Date and java.lang.String are in module java.base of loader 'bootstrap')
        Object obj = new Date();
        // String str = (String)obj;
        Assertions.assertThrows(ClassCastException.class, () -> { String str = (String)obj; });
    }

    @Test
    public void testNumberFormatException() {
        String str1 = "123";
        int num = Integer.parseInt(str1);

        String str2 = "abc";
        // Integer.parseInt(str2); // java.lang.NumberFormatException: For input string: "abc"
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.parseInt(str2));
    }
}
