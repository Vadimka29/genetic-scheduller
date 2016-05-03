
package com.redkite.xml.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for completion-type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="completion-type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="credit"/>
 *     &lt;enumeration value="differentiated-credit"/>
 *     &lt;enumeration value="exam"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "completion-type", namespace = "scheduler")
@XmlEnum
public enum CompletionType {

    @XmlEnumValue("credit")
    CREDIT("credit"),
    @XmlEnumValue("differentiated-credit")
    DIFFERENTIATED_CREDIT("differentiated-credit"),
    @XmlEnumValue("exam")
    EXAM("exam");
    private final String value;

    CompletionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CompletionType fromValue(String v) {
        for (CompletionType c: CompletionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
