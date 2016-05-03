
package com.redkite.xml.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pass-day.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="pass-day">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Monday"/>
 *     &lt;enumeration value="Tuesday"/>
 *     &lt;enumeration value="Wednesday"/>
 *     &lt;enumeration value="Thursday"/>
 *     &lt;enumeration value="Friday"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "pass-day", namespace = "scheduler")
@XmlEnum
public enum PassDay {

    @XmlEnumValue("Monday")
    MONDAY("Monday"),
    @XmlEnumValue("Tuesday")
    TUESDAY("Tuesday"),
    @XmlEnumValue("Wednesday")
    WEDNESDAY("Wednesday"),
    @XmlEnumValue("Thursday")
    THURSDAY("Thursday"),
    @XmlEnumValue("Friday")
    FRIDAY("Friday");
    private final String value;

    PassDay(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PassDay fromValue(String v) {
        for (PassDay c: PassDay.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
