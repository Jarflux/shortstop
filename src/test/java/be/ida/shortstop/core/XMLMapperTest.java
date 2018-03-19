package be.ida.shortstop.core;

import be.ida.shortstop.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Developer: Ben Oeyen
 * Date: 15/03/2018
 */

@RunWith(JUnit4.class)
public class XMLMapperTest {

    private XMLMapper mapper = new XMLMapper();

    @Test
    public void StringToXml() throws IllegalAccessException {
        // Create object
        String object = "Ben";

        // Execute call
        String xml = mapper.writeValue(object);

        // Validate result
        assertThat(xml).isEqualTo("Ben");
    }

    @Test
    public void SimpleClassToXml() throws IllegalAccessException {
        // Create object
        SimpleClass object = new SimpleClass("BMW");

        // Execute call
        String xml = mapper.writeValue(object);

        // Validate result
        assertThat(xml).isEqualTo("<SimpleClass><car>BMW</car></SimpleClass>");
    }

    @Test
    public void NestedClassToXml() throws IllegalAccessException {
        // Create object
        NestedClass object = new NestedClass("Ben", 28, true, new SimpleClass("BMW"));

        // Execute call
        String xml = mapper.writeValue(object);

        // Validate result
        assertThat(xml).isEqualTo("<NestedClass><name>Ben</name><age>28</age><isAwesome>true</isAwesome><nested><SimpleClass><car>BMW</car></SimpleClass></nested></NestedClass>");
    }


    @Test
    public void ComplexClassToXml() throws IllegalAccessException {
        // Create object
        ComplexClass object = new ComplexClass(new SimpleClass("BMW"), "Standard", new SimpleClass("BMW"));

        // Execute call
        String xml = mapper.writeValue(object);

        // Validate result
        assertThat(xml).isEqualTo("<ComplexClass xmlns:ben=\"www.google.com\"><Alternate><SimpleClass><car>BMW</car></SimpleClass></Alternate><proxytype>Standard</proxytype><SimpleClass><car>BMW</car></SimpleClass></ComplexClass>");
    }

    @Test
    public void ListClassToXml() throws IllegalAccessException {
        // Create object
        List<String> strings = new ArrayList<>();
        strings.add("BMW");
        strings.add("Audi");
        strings.add("Aston Martin");
        ListClass object = new ListClass(strings);

        // Execute call
        String xml = mapper.writeValue(object);

        // Validate result
        assertThat(xml).isEqualTo("<Cars><brands>BMW,Audi,Aston Martin</brands></Cars>");
    }


    @Test
    public void CustomListClassToXml() throws IllegalAccessException {
        // Create object
        List<SimpleClass> strings = new ArrayList<>();
        strings.add(new SimpleClass("BMW"));
        strings.add(new SimpleClass("Audi"));
        strings.add(new SimpleClass("Aston Martin"));
        CustomListClass object = new CustomListClass(strings);

        // Execute call
        String xml = mapper.writeValue(object);

        // Validate result
        assertThat(xml).isEqualTo("<Cars><brands><SimpleClass><car>BMW</car></SimpleClass><SimpleClass><car>Audi</car></SimpleClass><SimpleClass><car>Aston Martin</car></SimpleClass></brands></Cars>");
    }

}