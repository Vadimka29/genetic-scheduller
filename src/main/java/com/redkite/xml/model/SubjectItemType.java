
package com.redkite.xml.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subjectitem-type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="subjectitem-type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="lab-work"/>
 *     &lt;enumeration value="rgr"/>
 *     &lt;enumeration value="course-work"/>
 *     &lt;enumeration value="home-work"/>
 *     &lt;enumeration value="additional-work"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "subjectitem-type", namespace = "scheduler")
@XmlEnum
public enum SubjectItemType {

    @XmlEnumValue("lab-work")
    LAB_WORK("lab-work"),
    @XmlEnumValue("rgr")
    RGR("rgr"),
    @XmlEnumValue("course-work")
    COURSE_WORK("course-work"),
    @XmlEnumValue("home-work")
    HOME_WORK("home-work"),
    @XmlEnumValue("additional-work")
    ADDITIONAL_WORK("additional-work");
    private final String value;

    SubjectItemType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SubjectItemType fromValue(String v) {
        for (SubjectItemType c: SubjectItemType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
