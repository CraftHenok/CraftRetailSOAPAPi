//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.24 at 11:34:47 PM EAT 
//


package com.craftsoftware.retail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreateProductCategory" type="{http://www.craftsoftware.com/retail}CreateProductCategory"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "createProductCategory"
})
@XmlRootElement(name = "CreateProductCategoryRequest")
public class CreateProductCategoryRequest {

    @XmlElement(name = "CreateProductCategory", required = true)
    protected CreateProductCategory createProductCategory;

    /**
     * Gets the value of the createProductCategory property.
     * 
     * @return
     *     possible object is
     *     {@link CreateProductCategory }
     *     
     */
    public CreateProductCategory getCreateProductCategory() {
        return createProductCategory;
    }

    /**
     * Sets the value of the createProductCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateProductCategory }
     *     
     */
    public void setCreateProductCategory(CreateProductCategory value) {
        this.createProductCategory = value;
    }

}
