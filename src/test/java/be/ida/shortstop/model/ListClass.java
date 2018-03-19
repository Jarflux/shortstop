package be.ida.shortstop.model;

import be.ida.shortstop.core.annotation.XML;

import java.util.List;

/**
 * Developer: Ben Oeyen
 * Date: 16/03/2018
 */

@XML(name = "Cars")
public class ListClass {

    @XML
    List<String> brands;

    public ListClass(List<String> brands) {
        this.brands = brands;
    }
}
