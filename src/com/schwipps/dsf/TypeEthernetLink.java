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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r TypeEthernetLink complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TypeEthernetLink"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIdentifier" /&gt;
 *       &lt;attribute name="AgentIPAddress" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIPAddress" /&gt;
 *       &lt;attribute name="TransportProtocol" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeTransportProtocol" /&gt;
 *       &lt;attribute name="AgentTransportProtocolPort" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeEthernetLink")
public class TypeEthernetLink {

    @XmlAttribute(name = "Id", required = true)
    protected short id;
    @XmlAttribute(name = "AgentIPAddress", required = true)
    protected String agentIPAddress;
    @XmlAttribute(name = "TransportProtocol", required = true)
    protected TypeTransportProtocol transportProtocol;
    @XmlAttribute(name = "AgentTransportProtocolPort", required = true)
    @XmlSchemaType(name = "unsignedShort")
    protected int agentTransportProtocolPort;

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
     * Ruft den Wert der agentIPAddress-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentIPAddress() {
        return agentIPAddress;
    }

    /**
     * Legt den Wert der agentIPAddress-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentIPAddress(String value) {
        this.agentIPAddress = value;
    }

    /**
     * Ruft den Wert der transportProtocol-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeTransportProtocol }
     *     
     */
    public TypeTransportProtocol getTransportProtocol() {
        return transportProtocol;
    }

    /**
     * Legt den Wert der transportProtocol-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeTransportProtocol }
     *     
     */
    public void setTransportProtocol(TypeTransportProtocol value) {
        this.transportProtocol = value;
    }

    /**
     * Ruft den Wert der agentTransportProtocolPort-Eigenschaft ab.
     * 
     */
    public int getAgentTransportProtocolPort() {
        return agentTransportProtocolPort;
    }

    /**
     * Legt den Wert der agentTransportProtocolPort-Eigenschaft fest.
     * 
     */
    public void setAgentTransportProtocolPort(int value) {
        this.agentTransportProtocolPort = value;
    }

}
