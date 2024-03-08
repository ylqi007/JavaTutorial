package com.atguigu.api;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    public void test1() {
        String star = "";
        System.out.println(star);

        star = null;
        System.out.println(star.toString());
        //Assertions.assertThrows(NullPointerException.class, star::toString);
    }

    @Test
    public void test2() {
        String star = "";
        star = null;

        Optional<String> optional = Optional.ofNullable(star);

        //System.out.println(optional.get());
        System.out.println(optional.orElse("Other star"));
    }
}
