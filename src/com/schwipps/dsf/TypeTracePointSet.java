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
 * <p>Java-Klasse f�r TypeTracePointSet complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TypeTracePointSet"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CompilationUnit" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeTPCompilationUnit" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="DataProviderId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIdentifier" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeTracePointSet", propOrder = {
    "compilationUnit"
})
public class TypeTracePointSet {

    @XmlElement(name = "CompilationUnit")
    protected List<TypeTPCompilationUnit> compilationUnit;
    @XmlAttribute(name = "DataProviderId", required = true)
    protected short dataProviderId;

    /**
     * Gets the value of the compilationUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compilationUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompilationUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeTPCompilationUnit }
     * 
     * 
     */
    public List<TypeTPCompilationUnit> getCompilationUnit() {
        if (compilationUnit == null) {
            compilationUnit = new ArrayList<TypeTPCompilationUnit>();
        }
        return this.compilationUnit;
    }

    /**
     * Ruft den Wert der dataProviderId-Eigenschaft ab.
     * 
     */
    public short getDataProviderId() {
        return dataProviderId;
    }

    /**
     * Legt den Wert der dataProviderId-Eigenschaft fest.
     * 
     */
    public void setDataProviderId(short value) {
        this.dataProviderId = value;
    }

}
