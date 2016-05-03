
package com.redkite.xml.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="completion-type" type="{scheduler}completion-type"/>
 *         &lt;element name="priority" type="{scheduler}priority"/>
 *         &lt;element name="pass-day" type="{scheduler}pass-day" maxOccurs="5"/>
 *         &lt;element name="subject-item" type="{scheduler}subject-item" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subject", namespace = "scheduler", propOrder = {
    "completionType",
    "priority",
    "passDay",
    "subjectItem"
})
public class Subject {

    @XmlElement(name = "completion-type", namespace = "scheduler", required = true)
    @XmlSchemaType(name = "string")
    protected CompletionType completionType;
    @XmlElement(namespace = "scheduler", required = true)
    @XmlSchemaType(name = "string")
    protected Priority priority;
    @XmlElement(name = "pass-day", namespace = "scheduler", required = true)
    @XmlSchemaType(name = "string")
    protected List<PassDay> passDay;
    @XmlElement(name = "subject-item", namespace = "scheduler", required = true)
    protected List<SubjectItem> subjectItem;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the completionType property.
     * 
     * @return
     *     possible object is
     *     {@link CompletionType }
     *     
     */
    public CompletionType getCompletionType() {
        return completionType;
    }

    /**
     * Sets the value of the completionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompletionType }
     *     
     */
    public void setCompletionType(CompletionType value) {
        this.completionType = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link Priority }
     *     
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Priority }
     *     
     */
    public void setPriority(Priority value) {
        this.priority = value;
    }

    /**
     * Gets the value of the passDay property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the passDay property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPassDay().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PassDay }
     * 
     * 
     */
    public List<PassDay> getPassDay() {
        if (passDay == null) {
            passDay = new ArrayList<PassDay>();
        }
        return this.passDay;
    }

    /**
     * Gets the value of the subjectItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subjectItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubjectItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubjectItem }
     * 
     * 
     */
    public List<SubjectItem> getSubjectItem() {
        if (subjectItem == null) {
            subjectItem = new ArrayList<SubjectItem>();
        }
        return this.subjectItem;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
