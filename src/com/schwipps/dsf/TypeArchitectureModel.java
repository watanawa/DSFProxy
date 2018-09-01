//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.09.01 um 10:02:54 PM CEST 
//


package com.schwipps.dsf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r TypeArchitectureModel complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TypeArchitectureModel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ArchitectureItem" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeArchitectureItem"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeArchitectureModel", propOrder = {
    "architectureItem"
})
public class TypeArchitectureModel {

    @XmlElement(name = "ArchitectureItem", required = true)
    protected TypeArchitectureItem architectureItem;

    /**
     * Ruft den Wert der architectureItem-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeArchitectureItem }
     *     
     */
    public TypeArchitectureItem getArchitectureItem() {
        return architectureItem;
    }

    /**
     * Legt den Wert der architectureItem-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeArchitectureItem }
     *     
     */
    public void setArchitectureItem(TypeArchitectureItem value) {
        this.architectureItem = value;
    }

}
