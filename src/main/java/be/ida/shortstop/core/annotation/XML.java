package be.ida.shortstop.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Developer: Ben Oeyen
 * Date: 15/03/2018
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface XML {
    String namespace() default "";
    String prefix() default "";
    String name() default "";
    boolean unwrap() default false;
}