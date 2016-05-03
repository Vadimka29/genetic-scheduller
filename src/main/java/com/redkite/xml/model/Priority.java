
package com.redkite.xml.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for priority.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="priority">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="low"/>
 *     &lt;enumeration value="medium"/>
 *     &lt;enumeration value="high"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "priority", namespace = "scheduler")
@XmlEnum
public enum Priority {

    @XmlEnumValue("low")
    LOW("low"),
    @XmlEnumValue("medium")
    MEDIUM("medium"),
    @XmlEnumValue("high")
    HIGH("high");
    private final String value;

    Priority(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Priority fromValue(String v) {
        for (Priority c: Priority.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
