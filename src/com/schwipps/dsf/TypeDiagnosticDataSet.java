//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.09.01 um 10:02:54 PM CEST 
//


package com.schwipps.dsf;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java-Klasse f�r TypeDiagnosticDataSet complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TypeDiagnosticDataSet"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DiagnosticDataItem" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                 &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIdentifier32" /&gt;
 *                 &lt;attribute name="Address" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeMemoryAddress" /&gt;
 *                 &lt;attribute name="ModelLocation" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIdentifier" /&gt;
 *                 &lt;attribute name="IconFile" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="DataType" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeDataItemDataType" /&gt;
 *                 &lt;attribute name="Size" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
 *                 &lt;attribute name="Unit" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeUnit" /&gt;
 *                 &lt;attribute name="Access" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeAccessRights" default="read" /&gt;
 *                 &lt;attribute name="Description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
@XmlType(name = "TypeDiagnosticDataSet", propOrder = {
    "diagnosticDataItem"
})
public class TypeDiagnosticDataSet {

    @XmlElement(name = "DiagnosticDataItem")
    protected List<DiagnosticDataItem> diagnosticDataItem;
    @XmlAttribute(name = "DataProviderId", required = true)
    protected short dataProviderId;

    /**
     * Gets the value of the diagnosticDataItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the diagnosticDataItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiagnosticDataItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DiagnosticDataItem }
     * 
     * 
     */
    public List<DiagnosticDataItem> getDiagnosticDataItem() {
        if (diagnosticDataItem == null) {
            diagnosticDataItem = new ArrayList<DiagnosticDataItem>();
        }
        return this.diagnosticDataItem;
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


    /**
     * <p>Java-Klasse f�r anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIdentifier32" /&gt;
     *       &lt;attribute name="Address" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeMemoryAddress" /&gt;
     *       &lt;attribute name="ModelLocation" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIdentifier" /&gt;
     *       &lt;attribute name="IconFile" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="DataType" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeDataItemDataType" /&gt;
     *       &lt;attribute name="Size" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
     *       &lt;attribute name="Unit" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeUnit" /&gt;
     *       &lt;attribute name="Access" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeAccessRights" default="read" /&gt;
     *       &lt;attribute name="Description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class DiagnosticDataItem {

        @XmlAttribute(name = "Name", required = true)
        protected String name;
        @XmlAttribute(name = "Id", required = true)
        protected long id;
        @XmlAttribute(name = "Address")
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        protected byte[] address;
        @XmlAttribute(name = "ModelLocation", required = true)
        protected short modelLocation;
        @XmlAttribute(name = "IconFile")
        protected String iconFile;
        @XmlAttribute(name = "DataType", required = true)
        protected String dataType;
        @XmlAttribute(name = "Size", required = true)
        @XmlSchemaType(name = "unsignedByte")
        protected short size;
        @XmlAttribute(name = "Unit")
        protected String unit;
        @XmlAttribute(name = "Access")
        protected TypeAccessRights access;
        @XmlAttribute(name = "Description", required = true)
        protected String description;

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
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         */
        public long getId() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         */
        public void setId(long value) {
            this.id = value;
        }

        /**
         * Ruft den Wert der address-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getAddress() {
            return address;
        }

        /**
         * Legt den Wert der address-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAddress(byte[] value) {
            this.address = value;
        }

        /**
         * Ruft den Wert der modelLocation-Eigenschaft ab.
         * 
         */
        public short getModelLocation() {
            return modelLocation;
        }

        /**
         * Legt den Wert der modelLocation-Eigenschaft fest.
         * 
         */
        public void setModelLocation(short value) {
            this.modelLocation = value;
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

        /**
         * Ruft den Wert der dataType-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDataType() {
            return dataType;
        }

        /**
         * Legt den Wert der dataType-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDataType(String value) {
            this.dataType = value;
        }

        /**
         * Ruft den Wert der size-Eigenschaft ab.
         * 
         */
        public short getSize() {
            return size;
        }

        /**
         * Legt den Wert der size-Eigenschaft fest.
         * 
         */
        public void setSize(short value) {
            this.size = value;
        }

        /**
         * Ruft den Wert der unit-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUnit() {
            return unit;
        }

        /**
         * Legt den Wert der unit-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUnit(String value) {
            this.unit = value;
        }

        /**
         * Ruft den Wert der access-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link TypeAccessRights }
         *     
         */
        public TypeAccessRights getAccess() {
            if (access == null) {
                return TypeAccessRights.READ;
            } else {
                return access;
            }
        }

        /**
         * Legt den Wert der access-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link TypeAccessRights }
         *     
         */
        public void setAccess(TypeAccessRights value) {
            this.access = value;
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

    }

}
