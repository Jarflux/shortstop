package be.ida.shortstop.model;

import be.ida.shortstop.core.annotation.XML;

import java.util.Date;

/**
 * Developer: Ben Oeyen
 * Date: 15/03/2018
 */
@XML(prefix = "ben", namespace = "www.google.com")
public class ComplexClass {
    @XML
    private Date date;
    @XML(name = "Alternate")
    private SimpleClass simple;
    @XML
    private String proxytype;
    @XML(name = "Hidden", unwrap = true)
    private SimpleClass simple2;

    public ComplexClass(SimpleClass simple, String proxytype, SimpleClass simple2) {
        this.simple = simple;
        this.proxytype = proxytype;
        this.simple2 = simple2;
    }
}
