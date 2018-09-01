//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.09.01 um 10:02:54 PM CEST 
//


package com.schwipps.dsf;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r TypeSerialLink complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TypeSerialLink"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIdentifier" /&gt;
 *       &lt;attribute name="SignalType" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeSignal" /&gt;
 *       &lt;attribute name="BaudRate" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" /&gt;
 *       &lt;attribute name="DataBits" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeDataBits" /&gt;
 *       &lt;attribute name="StopBits" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeStopBits" /&gt;
 *       &lt;attribute name="Parity" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeParity" /&gt;
 *       &lt;attribute name="FlowControl" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeFlowControl" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeSerialLink")
public class TypeSerialLink {

    @XmlAttribute(name = "Id", required = true)
    protected short id;
    @XmlAttribute(name = "SignalType", required = true)
    protected TypeSignal signalType;
    @XmlAttribute(name = "BaudRate", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long baudRate;
    @XmlAttribute(name = "DataBits", required = true)
    protected short dataBits;
    @XmlAttribute(name = "StopBits", required = true)
    protected BigDecimal stopBits;
    @XmlAttribute(name = "Parity", required = true)
    protected TypeParity parity;
    @XmlAttribute(name = "FlowControl", required = true)
    protected TypeFlowControl flowControl;

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
     * Ruft den Wert der signalType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeSignal }
     *     
     */
    public TypeSignal getSignalType() {
        return signalType;
    }

    /**
     * Legt den Wert der signalType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeSignal }
     *     
     */
    public void setSignalType(TypeSignal value) {
        this.signalType = value;
    }

    /**
     * Ruft den Wert der baudRate-Eigenschaft ab.
     * 
     */
    public long getBaudRate() {
        return baudRate;
    }

    /**
     * Legt den Wert der baudRate-Eigenschaft fest.
     * 
     */
    public void setBaudRate(long value) {
        this.baudRate = value;
    }

    /**
     * Ruft den Wert der dataBits-Eigenschaft ab.
     * 
     */
    public short getDataBits() {
        return dataBits;
    }

    /**
     * Legt den Wert der dataBits-Eigenschaft fest.
     * 
     */
    public void setDataBits(short value) {
        this.dataBits = value;
    }

    /**
     * Ruft den Wert der stopBits-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStopBits() {
        return stopBits;
    }

    /**
     * Legt den Wert der stopBits-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStopBits(BigDecimal value) {
        this.stopBits = value;
    }

    /**
     * Ruft den Wert der parity-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeParity }
     *     
     */
    public TypeParity getParity() {
        return parity;
    }

    /**
     * Legt den Wert der parity-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeParity }
     *     
     */
    public void setParity(TypeParity value) {
        this.parity = value;
    }

    /**
     * Ruft den Wert der flowControl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeFlowControl }
     *     
     */
    public TypeFlowControl getFlowControl() {
        return flowControl;
    }

    /**
     * Legt den Wert der flowControl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeFlowControl }
     *     
     */
    public void setFlowControl(TypeFlowControl value) {
        this.flowControl = value;
    }

}
