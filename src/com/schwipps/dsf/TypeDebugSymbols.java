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
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java-Klasse f�r TypeDebugSymbols complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TypeDebugSymbols"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DebugSymbolSet" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeDebugSymbolSet" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeDebugSymbols", propOrder = {
    "debugSymbolSet"
})
public class TypeDebugSymbols {

    @XmlElement(name = "DebugSymbolSet")
    protected List<TypeDebugSymbolSet> debugSymbolSet;

    /**
     * Gets the value of the debugSymbolSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the debugSymbolSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDebugSymbolSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeDebugSymbolSet }
     * 
     * 
     */
    public List<TypeDebugSymbolSet> getDebugSymbolSet() {
        if (debugSymbolSet == null) {
            debugSymbolSet = new ArrayList<TypeDebugSymbolSet>();
        }
        return this.debugSymbolSet;
    }

}
