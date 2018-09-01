//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.09.01 um 10:02:54 PM CEST 
//


package com.schwipps.dsf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse f�r TypeDataProvider complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TypeDataProvider"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIdentifier" /&gt;
 *       &lt;attribute name="Description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="LinkId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIdentifier" /&gt;
 *       &lt;attribute name="ByteOrder" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeEndianess" /&gt;
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="BuildDate" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Checksum" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeChecksum" /&gt;
 *       &lt;attribute name="ModelLocation" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIdentifier" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeDataProvider")
public class TypeDataProvider {

    @XmlAttribute(name = "Id", required = true)
    protected short id;
    @XmlAttribute(name = "Description", required = true)
    protected String description;
    @XmlAttribute(name = "LinkId", required = true)
    protected short linkId;
    @XmlAttribute(name = "ByteOrder", required = true)
    protected TypeEndianess byteOrder;
    @XmlAttribute(name = "Version")
    protected String version;
    @XmlAttribute(name = "BuildDate")
    protected String buildDate;
    @XmlAttribute(name = "Checksum", required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] checksum;
    @XmlAttribute(name = "ModelLocation")
    protected Short modelLocation;

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
     * Ruft den Wert der linkId-Eigenschaft ab.
     * 
     */
    public short getLinkId() {
        return linkId;
    }

    /**
     * Legt den Wert der linkId-Eigenschaft fest.
     * 
     */
    public void setLinkId(short value) {
        this.linkId = value;
    }

    /**
     * Ruft den Wert der byteOrder-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeEndianess }
     *     
     */
    public TypeEndianess getByteOrder() {
        return byteOrder;
    }

    /**
     * Legt den Wert der byteOrder-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeEndianess }
     *     
     */
    public void setByteOrder(TypeEndianess value) {
        this.byteOrder = value;
    }

    /**
     * Ruft den Wert der version-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Legt den Wert der version-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Ruft den Wert der buildDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildDate() {
        return buildDate;
    }

    /**
     * Legt den Wert der buildDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildDate(String value) {
        this.buildDate = value;
    }

    /**
     * Ruft den Wert der checksum-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getChecksum() {
        return checksum;
    }

    /**
     * Legt den Wert der checksum-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChecksum(byte[] value) {
        this.checksum = value;
    }

    /**
     * Ruft den Wert der modelLocation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getModelLocation() {
        return modelLocation;
    }

    /**
     * Legt den Wert der modelLocation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setModelLocation(Short value) {
        this.modelLocation = value;
    }

}
