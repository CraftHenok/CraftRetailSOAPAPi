//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.10 at 05:43:23 PM EAT 
//


package com.craftsoftware.ProductCategorys;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.dataaccesslayer.entity.ProductCategory;
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
 *         &lt;element name="ProductCategory" type="{http://www.craftsoftware.com/ProductCategorys}ProductCategory"/>
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
    "ProductCategory"
})
@XmlRootElement(name = "GetProductCategoryResponse")
public class GetProductCategoryResponse {

    @XmlElement(name = "ProductCategory", required = true)
    protected ProductCategory  ProductCategory;

    /**
     * Gets the value of the ProductCategory property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCategory }
     *     
     */
    public ProductCategory getProductCategory() {
        return ProductCategory;
    }

    /**
     * Sets the value of the ProductCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCategory }
     *     
     */
    public void setProductCategory(ProductCategory value) {
        this.ProductCategory = value;
    }

}
