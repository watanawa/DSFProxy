//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.09.01 um 10:02:54 PM CEST 
//


package com.schwipps.dsf;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java-Klasse f�r TypeArchitectureItem complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TypeArchitectureItem"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ArchitectureItem" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeArchitectureItem" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIdentifier" /&gt;
 *       &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeArchitectureName" /&gt;
 *       &lt;attribute name="Description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="IconFile" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeArchitectureItem", propOrder = {
    "architectureItem"
})
public class TypeArchitectureItem {

    @XmlElement(name = "ArchitectureItem")
    protected List<TypeArchitectureItem> architectureItem;
    @XmlAttribute(name = "Id", required = true)
    protected short id;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "Description", required = true)
    protected String description;
    @XmlAttribute(name = "IconFile")
    protected String iconFile;

    /**
     * Gets the value of the architectureItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the architectureItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArchitectureItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeArchitectureItem }
     * 
     * 
     */
    public List<TypeArchitectureItem> getArchitectureItem() {
        if (architectureItem == null) {
            architectureItem = new ArrayList<TypeArchitectureItem>();
        }
        return this.architectureItem;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public short getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(short value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der name-Eigenschaft ab.
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
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der description-Eigenschaft ab.
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
     * Legt den Wert der description-Eigenschaft fest.
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
     * Ruft den Wert der iconFile-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIconFile() {
        return iconFile;
    }

    /**
     * Legt den Wert der iconFile-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIconFile(String value) {
        this.iconFile = value;
    }

}
