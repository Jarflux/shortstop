package be.ida.shortstop.model;

import be.ida.shortstop.core.annotation.XML;

/**
 * Developer: Ben Oeyen
 * Date: 15/03/2018
 */
public class NestedClass {
    @XML
    public String name;
    @XML
    protected int age;
    @XML
    Boolean isAwesome;
    @XML
    private SimpleClass nested;

    public NestedClass(String name, int age, Boolean isAwesome, SimpleClass nested) {
        this.name = name;
        this.age = age;
        this.isAwesome = isAwesome;
        this.nested = nested;
    }

}