package be.ida.shortstop.model;

import be.ida.shortstop.core.annotation.XML;

import java.util.List;

/**
 * Developer: Ben Oeyen
 * Date: 16/03/2018
 */

@XML(name = "Cars")
public class CustomListClass {

    @XML
    List<SimpleClass> brands;

    public CustomListClass(List<SimpleClass> brands) {
        this.brands = brands;
    }
}
