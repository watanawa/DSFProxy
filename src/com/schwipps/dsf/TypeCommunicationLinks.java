//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.09.01 um 10:02:54 PM CEST 
//


package com.schwipps.dsf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r TypeCommunicationLinks complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TypeCommunicationLinks"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice maxOccurs="unbounded"&gt;
 *         &lt;element name="EthernetLink" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeEthernetLink"/&gt;
 *         &lt;element name="SerialLink" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeSerialLink"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeCommunicationLinks", propOrder = {
    "ethernetLinkOrSerialLink"
})
public class TypeCommunicationLinks {

    @XmlElements({
        @XmlElement(name = "EthernetLink", type = TypeEthernetLink.class),
        @XmlElement(name = "SerialLink", type = TypeSerialLink.class)
    })
    protected List<Object> ethernetLinkOrSerialLink;

    /**
     * Gets the value of the ethernetLinkOrSerialLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ethernetLinkOrSerialLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEthernetLinkOrSerialLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeEthernetLink }
     * {@link TypeSerialLink }
     * 
     * 
     */
    public List<Object> getEthernetLinkOrSerialLink() {
        if (ethernetLinkOrSerialLink == null) {
            ethernetLinkOrSerialLink = new ArrayList<Object>();
        }
        return this.ethernetLinkOrSerialLink;
    }

}
