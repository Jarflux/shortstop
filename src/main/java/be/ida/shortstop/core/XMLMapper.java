package be.ida.shortstop.core;

import be.ida.shortstop.core.annotation.XML;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Developer: Ben Oeyen
 * Date: 15/03/2018
 */

public class XMLMapper {

    public String writeValue(Object object) throws IllegalAccessException {
        if (object != null) {
            if (isPredifinedClass(object)) {
                if (object instanceof Iterable<?>) {
                    return serializeGenericIterable((Iterable<?>) object);
                }else{
                    return object.toString();
                }
            }
            return serializeCustomClass(object);
        } else {
            return "";
        }
    }

    private boolean isPredifinedClass(Object object) {
        return object instanceof String
                || object instanceof Number
                || object instanceof Boolean
                || object instanceof Date
                || object instanceof Character
                || object instanceof Iterable<?>;
    }

    private String serializeGenericIterable(Iterable<?> object) throws IllegalAccessException {
        String separator = "";
        StringBuilder xml = new StringBuilder();
        for (Object item : object) {
            if(isPredifinedClass(item)){
                xml.append(separator);
                xml.append(writeValue(item));
                separator = ",";
            }else{
                xml.append(serializeCustomClass(item));
            }

        }
        return xml.toString();
    }

    private String serializeCustomClass(Object object) throws IllegalAccessException {
        Class<?> cls = object.getClass();
        StringBuilder xml = new StringBuilder();
        boolean wrap = !cls.isAnnotationPresent(XML.class) || (cls.isAnnotationPresent(XML.class) && !cls.getAnnotation(XML.class).unwrap());
        xml.append(wrap ? getOpeningTag(cls) : "");
        for (Field field : cls.getDeclaredFields()) {

            if (field.isAnnotationPresent(XML.class)) {
                xml.append(serializeProperty(object, field));
            }
        }
        xml.append(wrap ? getClosingTag(cls) : "");
        return xml.toString();
    }

    private String serializeProperty(Object object, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        if (field.get(object) != null) {
            StringBuilder xml = new StringBuilder();
            boolean wrap = !field.isAnnotationPresent(XML.class)
                    || (field.isAnnotationPresent(XML.class) && !field.getAnnotation(XML.class).unwrap());
            xml.append(wrap ? getOpeningTag(field) : "");
            xml.append(writeValue(field.get(object)));
            xml.append(wrap ? getClosingTag(field) : "");
            return xml.toString();
        } else {
            return "";
        }
    }

    private String getOpeningTag(Field field) {
        XML annotation = field.getAnnotation(XML.class);
        String name = getNameValueFromAnnotation(annotation, field.getName());
        String prefix = getPrefixValueFromAnnotation(annotation);
        String namespace = getNameSpaceValueFromAnnotation(annotation);
        return getOpeningTag(name, prefix, namespace);
    }

    private String getClosingTag(Field field) {
        XML annotation = field.getAnnotation(XML.class);
        String name = getNameValueFromAnnotation(annotation, field.getName());
        return getClosingTag(name);
    }


    private String getOpeningTag(Class<?> cls) {
        String name = getNameValueFromAnnotation(cls.getAnnotation(XML.class), cls.getSimpleName());
        String prefix = getPrefixValueFromAnnotation(cls.getAnnotation(XML.class));
        String namespace = getNameSpaceValueFromAnnotation(cls.getAnnotation(XML.class));
        return getOpeningTag(name, prefix, namespace);
    }

    private String getClosingTag(Class<?> cls) {
        String name = getNameValueFromAnnotation(cls.getAnnotation(XML.class), cls.getSimpleName());
        return getClosingTag(name);
    }

    private String getOpeningTag(String element, String prefix, String namespace) {
        String tag = "<";
        tag += element;
        if (namespace != null) {
            if (prefix != null) {
                tag += " xmlns:" + prefix + "=\"" + namespace + "\"";
            } else {
                tag += " xmlns=\"" + namespace + "\"";
            }
        }
        return tag + ">";
    }

    private String getClosingTag(String element) {
        return "</" + element + ">";
    }

    private String getNameValueFromAnnotation(XML annotation, String fallback) {
        return annotation == null || "".equals(annotation.name()) ? fallback : annotation.name();
    }

    private String getPrefixValueFromAnnotation(XML annotation) {
        return annotation == null || "".equals(annotation.prefix()) ? null : annotation.prefix();
    }

    private String getNameSpaceValueFromAnnotation(XML annotation) {
        return annotation == null || "".equals(annotation.namespace()) ? null : annotation.namespace();
    }
}
