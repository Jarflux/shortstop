package be.ida.shortstop.model;

import be.ida.shortstop.core.annotation.XML;

/**
 * Developer: Ben Oeyen
 * Date: 15/03/2018
 */
public class SimpleClass {
    @XML
    private String car;

    public SimpleClass(String car) {
        this.car = car;
    }
}