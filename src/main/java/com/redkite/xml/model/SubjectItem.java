
package com.redkite.xml.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for subject-item complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subject-item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="work-name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="work-type" type="{scheduler}subjectitem-type"/>
 *         &lt;element name="duration" type="{scheduler}hours-count"/>
 *         &lt;element name="retrieve-date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="deadline" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subject-item", namespace = "scheduler", propOrder = {
    "workName",
    "description",
    "workType",
    "duration",
    "retrieveDate",
    "deadline"
})
public class SubjectItem {

    @XmlElement(name = "work-name", namespace = "scheduler", required = true)
    protected String workName;
    @XmlElement(namespace = "scheduler")
    protected String description;
    @XmlElement(name = "work-type", namespace = "scheduler", required = true)
    @XmlSchemaType(name = "string")
    protected SubjectItemType workType;
    @XmlElement(namespace = "scheduler", required = true)
    protected int duration;
    @XmlElement(name = "retrieve-date", namespace = "scheduler", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar retrieveDate;
    @XmlElement(namespace = "scheduler", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deadline;

    /**
     * Gets the value of the workName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkName() {
        return workName;
    }

    /**
     * Sets the value of the workName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkName(String value) {
        this.workName = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the workType property.
     * 
     * @return
     *     possible object is
     *     {@link SubjectItemType }
     *     
     */
    public SubjectItemType getWorkType() {
        return workType;
    }

    /**
     * Sets the value of the workType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubjectItemType }
     *     
     */
    public void setWorkType(SubjectItemType value) {
        this.workType = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDuration(int value) {
        this.duration = value;
    }

    /**
     * Gets the value of the retrieveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRetrieveDate() {
        return retrieveDate;
    }

    /**
     * Sets the value of the retrieveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRetrieveDate(XMLGregorianCalendar value) {
        this.retrieveDate = value;
    }

    /**
     * Gets the value of the deadline property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeadline() {
        return deadline;
    }

    /**
     * Sets the value of the deadline property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeadline(XMLGregorianCalendar value) {
        this.deadline = value;
    }

}
