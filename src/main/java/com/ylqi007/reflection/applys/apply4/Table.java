package com.ylqi007.reflection.applys.apply4;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * ClassName: Table
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 10:42
 * @Version 1.0
 */
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String value() default "abc";
}
