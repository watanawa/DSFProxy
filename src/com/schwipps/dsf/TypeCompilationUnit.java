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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java-Klasse f�r TypeCompilationUnit complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TypeCompilationUnit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded"&gt;
 *         &lt;element name="Variable" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
 *                 &lt;attribute name="Address" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeMemoryAddress" /&gt;
 *                 &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *                 &lt;attribute name="DataTypeId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CharacterDataType" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                 &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="BitSize" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *                 &lt;attribute name="Signed" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeYesNo" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BooleanDataType" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                 &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="BitSize" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="IntegerDataType" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                 &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="BitSize" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *                 &lt;attribute name="Signed" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeYesNo" /&gt;
 *                 &lt;attribute name="RangeLower" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeInteger64" /&gt;
 *                 &lt;attribute name="RangeUpper" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeInteger64" /&gt;
 *                 &lt;attribute name="ScaleMultiply" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *                 &lt;attribute name="ScaleDivide" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="FloatDataType" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                 &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="BitSize" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PointerDataType" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                 &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="BitSize" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *                 &lt;attribute name="ReferencedDataTypeId" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="EnumDataType" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="EnumElement" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
 *                           &lt;attribute name="Value" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                 &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="BitSize" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ListDataType" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ListElement"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="DataTypeId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                           &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ListIndex" maxOccurs="3"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="RangeLower" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *                           &lt;attribute name="RangeUpper" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                 &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RecordDataType" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="RecordElement" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
 *                           &lt;attribute name="BitOffset" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *                           &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *                           &lt;attribute name="DataTypeId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                 &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="UnionDataType" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="UnionElement" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
 *                           &lt;attribute name="DataTypeId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                           &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *                           &lt;attribute name="Id" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
 *                 &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Language" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
 *       &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
 *       &lt;attribute name="Path" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeCompilationUnit", propOrder = {
    "variableAndCharacterDataTypeAndBooleanDataType"
})
public class TypeCompilationUnit {

    @XmlElements({
        @XmlElement(name = "Variable", type = Variable.class),
        @XmlElement(name = "CharacterDataType", type = CharacterDataType.class),
        @XmlElement(name = "BooleanDataType", type = BooleanDataType.class),
        @XmlElement(name = "IntegerDataType", type = IntegerDataType.class),
        @XmlElement(name = "FloatDataType", type = FloatDataType.class),
        @XmlElement(name = "PointerDataType", type = PointerDataType.class),
        @XmlElement(name = "EnumDataType", type = EnumDataType.class),
        @XmlElement(name = "ListDataType", type = ListDataType.class),
        @XmlElement(name = "RecordDataType", type = RecordDataType.class),
        @XmlElement(name = "UnionDataType", type = UnionDataType.class)
    })
    protected List<Object> variableAndCharacterDataTypeAndBooleanDataType;
    @XmlAttribute(name = "Language", required = true)
    protected String language;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "Path")
    protected String path;

    /**
     * Gets the value of the variableAndCharacterDataTypeAndBooleanDataType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variableAndCharacterDataTypeAndBooleanDataType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariableAndCharacterDataTypeAndBooleanDataType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Variable }
     * {@link CharacterDataType }
     * {@link BooleanDataType }
     * {@link IntegerDataType }
     * {@link FloatDataType }
     * {@link PointerDataType }
     * {@link EnumDataType }
     * {@link ListDataType }
     * {@link RecordDataType }
     * {@link UnionDataType }
     * 
     * 
     */
    public List<Object> getVariableAndCharacterDataTypeAndBooleanDataType() {
        if (variableAndCharacterDataTypeAndBooleanDataType == null) {
            variableAndCharacterDataTypeAndBooleanDataType = new ArrayList<Object>();
        }
        return this.variableAndCharacterDataTypeAndBooleanDataType;
    }

    /**
     * Ruft den Wert der language-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Legt den Wert der language-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
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
     * Ruft den Wert der path-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPath() {
        return path;
    }

    /**
     * Legt den Wert der path-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPath(String value) {
        this.path = value;
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
     *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="BitSize" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class BooleanDataType {

        @XmlAttribute(name = "Id", required = true)
        protected String id;
        @XmlAttribute(name = "Name")
        protected String name;
        @XmlAttribute(name = "BitSize", required = true)
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger bitSize;

        /**
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
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
         * Ruft den Wert der bitSize-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBitSize() {
            return bitSize;
        }

        /**
         * Legt den Wert der bitSize-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBitSize(BigInteger value) {
            this.bitSize = value;
        }

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
     *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="BitSize" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *       &lt;attribute name="Signed" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeYesNo" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class CharacterDataType {

        @XmlAttribute(name = "Id", required = true)
        protected String id;
        @XmlAttribute(name = "Name")
        protected String name;
        @XmlAttribute(name = "BitSize", required = true)
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger bitSize;
        @XmlAttribute(name = "Signed", required = true)
        protected TypeYesNo signed;

        /**
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
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
         * Ruft den Wert der bitSize-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBitSize() {
            return bitSize;
        }

        /**
         * Legt den Wert der bitSize-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBitSize(BigInteger value) {
            this.bitSize = value;
        }

        /**
         * Ruft den Wert der signed-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link TypeYesNo }
         *     
         */
        public TypeYesNo getSigned() {
            return signed;
        }

        /**
         * Legt den Wert der signed-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link TypeYesNo }
         *     
         */
        public void setSigned(TypeYesNo value) {
            this.signed = value;
        }

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
     *       &lt;sequence&gt;
     *         &lt;element name="EnumElement" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
     *                 &lt;attribute name="Value" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="BitSize" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "enumElement"
    })
    public static class EnumDataType {

        @XmlElement(name = "EnumElement", required = true)
        protected List<EnumElement> enumElement;
        @XmlAttribute(name = "Id", required = true)
        protected String id;
        @XmlAttribute(name = "Name")
        protected String name;
        @XmlAttribute(name = "BitSize", required = true)
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger bitSize;

        /**
         * Gets the value of the enumElement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the enumElement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEnumElement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EnumElement }
         * 
         * 
         */
        public List<EnumElement> getEnumElement() {
            if (enumElement == null) {
                enumElement = new ArrayList<EnumElement>();
            }
            return this.enumElement;
        }

        /**
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
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
         * Ruft den Wert der bitSize-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBitSize() {
            return bitSize;
        }

        /**
         * Legt den Wert der bitSize-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBitSize(BigInteger value) {
            this.bitSize = value;
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
         *       &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
         *       &lt;attribute name="Value" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class EnumElement {

            @XmlAttribute(name = "Name", required = true)
            protected String name;
            @XmlAttribute(name = "Value", required = true)
            protected BigInteger value;

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
             * Ruft den Wert der value-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getValue() {
                return value;
            }

            /**
             * Legt den Wert der value-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setValue(BigInteger value) {
                this.value = value;
            }

        }

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
     *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="BitSize" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class FloatDataType {

        @XmlAttribute(name = "Id", required = true)
        protected String id;
        @XmlAttribute(name = "Name")
        protected String name;
        @XmlAttribute(name = "BitSize", required = true)
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger bitSize;

        /**
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
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
         * Ruft den Wert der bitSize-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBitSize() {
            return bitSize;
        }

        /**
         * Legt den Wert der bitSize-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBitSize(BigInteger value) {
            this.bitSize = value;
        }

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
     *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="BitSize" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *       &lt;attribute name="Signed" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeYesNo" /&gt;
     *       &lt;attribute name="RangeLower" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeInteger64" /&gt;
     *       &lt;attribute name="RangeUpper" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeInteger64" /&gt;
     *       &lt;attribute name="ScaleMultiply" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *       &lt;attribute name="ScaleDivide" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class IntegerDataType {

        @XmlAttribute(name = "Id", required = true)
        protected String id;
        @XmlAttribute(name = "Name")
        protected String name;
        @XmlAttribute(name = "BitSize", required = true)
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger bitSize;
        @XmlAttribute(name = "Signed", required = true)
        protected TypeYesNo signed;
        @XmlAttribute(name = "RangeLower", required = true)
        protected BigDecimal rangeLower;
        @XmlAttribute(name = "RangeUpper", required = true)
        protected BigDecimal rangeUpper;
        @XmlAttribute(name = "ScaleMultiply")
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger scaleMultiply;
        @XmlAttribute(name = "ScaleDivide")
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger scaleDivide;

        /**
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
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
         * Ruft den Wert der bitSize-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBitSize() {
            return bitSize;
        }

        /**
         * Legt den Wert der bitSize-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBitSize(BigInteger value) {
            this.bitSize = value;
        }

        /**
         * Ruft den Wert der signed-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link TypeYesNo }
         *     
         */
        public TypeYesNo getSigned() {
            return signed;
        }

        /**
         * Legt den Wert der signed-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link TypeYesNo }
         *     
         */
        public void setSigned(TypeYesNo value) {
            this.signed = value;
        }

        /**
         * Ruft den Wert der rangeLower-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getRangeLower() {
            return rangeLower;
        }

        /**
         * Legt den Wert der rangeLower-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setRangeLower(BigDecimal value) {
            this.rangeLower = value;
        }

        /**
         * Ruft den Wert der rangeUpper-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getRangeUpper() {
            return rangeUpper;
        }

        /**
         * Legt den Wert der rangeUpper-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setRangeUpper(BigDecimal value) {
            this.rangeUpper = value;
        }

        /**
         * Ruft den Wert der scaleMultiply-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getScaleMultiply() {
            return scaleMultiply;
        }

        /**
         * Legt den Wert der scaleMultiply-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setScaleMultiply(BigInteger value) {
            this.scaleMultiply = value;
        }

        /**
         * Ruft den Wert der scaleDivide-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getScaleDivide() {
            return scaleDivide;
        }

        /**
         * Legt den Wert der scaleDivide-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setScaleDivide(BigInteger value) {
            this.scaleDivide = value;
        }

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
     *       &lt;sequence&gt;
     *         &lt;element name="ListElement"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="DataTypeId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *                 &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ListIndex" maxOccurs="3"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="RangeLower" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
     *                 &lt;attribute name="RangeUpper" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "listElement",
        "listIndex"
    })
    public static class ListDataType {

        @XmlElement(name = "ListElement", required = true)
        protected ListElement listElement;
        @XmlElement(name = "ListIndex", required = true)
        protected List<ListIndex> listIndex;
        @XmlAttribute(name = "Id", required = true)
        protected String id;
        @XmlAttribute(name = "Name")
        protected String name;
        @XmlAttribute(name = "BitSize")
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger bitSize;

        /**
         * Ruft den Wert der listElement-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link ListElement }
         *     
         */
        public ListElement getListElement() {
            return listElement;
        }

        /**
         * Legt den Wert der listElement-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link ListElement }
         *     
         */
        public void setListElement(ListElement value) {
            this.listElement = value;
        }

        /**
         * Gets the value of the listIndex property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the listIndex property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getListIndex().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ListIndex }
         * 
         * 
         */
        public List<ListIndex> getListIndex() {
            if (listIndex == null) {
                listIndex = new ArrayList<ListIndex>();
            }
            return this.listIndex;
        }

        /**
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
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
         * Ruft den Wert der bitSize-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBitSize() {
            return bitSize;
        }

        /**
         * Legt den Wert der bitSize-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBitSize(BigInteger value) {
            this.bitSize = value;
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
         *       &lt;attribute name="DataTypeId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
         *       &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class ListElement {

            @XmlAttribute(name = "DataTypeId", required = true)
            protected String dataTypeId;
            @XmlAttribute(name = "BitSize")
            @XmlSchemaType(name = "unsignedLong")
            protected BigInteger bitSize;

            /**
             * Ruft den Wert der dataTypeId-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDataTypeId() {
                return dataTypeId;
            }

            /**
             * Legt den Wert der dataTypeId-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDataTypeId(String value) {
                this.dataTypeId = value;
            }

            /**
             * Ruft den Wert der bitSize-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getBitSize() {
                return bitSize;
            }

            /**
             * Legt den Wert der bitSize-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setBitSize(BigInteger value) {
                this.bitSize = value;
            }

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
         *       &lt;attribute name="RangeLower" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
         *       &lt;attribute name="RangeUpper" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class ListIndex {

            @XmlAttribute(name = "RangeLower", required = true)
            protected BigInteger rangeLower;
            @XmlAttribute(name = "RangeUpper", required = true)
            protected BigInteger rangeUpper;

            /**
             * Ruft den Wert der rangeLower-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getRangeLower() {
                return rangeLower;
            }

            /**
             * Legt den Wert der rangeLower-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setRangeLower(BigInteger value) {
                this.rangeLower = value;
            }

            /**
             * Ruft den Wert der rangeUpper-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getRangeUpper() {
                return rangeUpper;
            }

            /**
             * Legt den Wert der rangeUpper-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setRangeUpper(BigInteger value) {
                this.rangeUpper = value;
            }

        }

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
     *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="BitSize" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *       &lt;attribute name="ReferencedDataTypeId" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class PointerDataType {

        @XmlAttribute(name = "Id", required = true)
        protected String id;
        @XmlAttribute(name = "Name")
        protected String name;
        @XmlAttribute(name = "BitSize", required = true)
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger bitSize;
        @XmlAttribute(name = "ReferencedDataTypeId")
        protected String referencedDataTypeId;

        /**
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
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
         * Ruft den Wert der bitSize-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBitSize() {
            return bitSize;
        }

        /**
         * Legt den Wert der bitSize-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBitSize(BigInteger value) {
            this.bitSize = value;
        }

        /**
         * Ruft den Wert der referencedDataTypeId-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReferencedDataTypeId() {
            return referencedDataTypeId;
        }

        /**
         * Legt den Wert der referencedDataTypeId-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReferencedDataTypeId(String value) {
            this.referencedDataTypeId = value;
        }

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
     *       &lt;sequence&gt;
     *         &lt;element name="RecordElement" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
     *                 &lt;attribute name="BitOffset" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
     *                 &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *                 &lt;attribute name="DataTypeId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "recordElement"
    })
    public static class RecordDataType {

        @XmlElement(name = "RecordElement", required = true)
        protected List<RecordElement> recordElement;
        @XmlAttribute(name = "Id", required = true)
        protected String id;
        @XmlAttribute(name = "Name")
        protected String name;
        @XmlAttribute(name = "BitSize")
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger bitSize;

        /**
         * Gets the value of the recordElement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the recordElement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRecordElement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RecordElement }
         * 
         * 
         */
        public List<RecordElement> getRecordElement() {
            if (recordElement == null) {
                recordElement = new ArrayList<RecordElement>();
            }
            return this.recordElement;
        }

        /**
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
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
         * Ruft den Wert der bitSize-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBitSize() {
            return bitSize;
        }

        /**
         * Legt den Wert der bitSize-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBitSize(BigInteger value) {
            this.bitSize = value;
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
         *       &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
         *       &lt;attribute name="BitOffset" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
         *       &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
         *       &lt;attribute name="DataTypeId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class RecordElement {

            @XmlAttribute(name = "Name", required = true)
            protected String name;
            @XmlAttribute(name = "BitOffset", required = true)
            protected BigInteger bitOffset;
            @XmlAttribute(name = "BitSize")
            @XmlSchemaType(name = "unsignedLong")
            protected BigInteger bitSize;
            @XmlAttribute(name = "DataTypeId", required = true)
            protected String dataTypeId;

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
             * Ruft den Wert der bitOffset-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getBitOffset() {
                return bitOffset;
            }

            /**
             * Legt den Wert der bitOffset-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setBitOffset(BigInteger value) {
                this.bitOffset = value;
            }

            /**
             * Ruft den Wert der bitSize-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getBitSize() {
                return bitSize;
            }

            /**
             * Legt den Wert der bitSize-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setBitSize(BigInteger value) {
                this.bitSize = value;
            }

            /**
             * Ruft den Wert der dataTypeId-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDataTypeId() {
                return dataTypeId;
            }

            /**
             * Legt den Wert der dataTypeId-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDataTypeId(String value) {
                this.dataTypeId = value;
            }

        }

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
     *       &lt;sequence&gt;
     *         &lt;element name="UnionElement" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
     *                 &lt;attribute name="DataTypeId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *                 &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *                 &lt;attribute name="Id" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="Id" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "unionElement"
    })
    public static class UnionDataType {

        @XmlElement(name = "UnionElement", required = true)
        protected List<UnionElement> unionElement;
        @XmlAttribute(name = "Id", required = true)
        protected String id;
        @XmlAttribute(name = "Name")
        protected String name;
        @XmlAttribute(name = "BitSize")
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger bitSize;

        /**
         * Gets the value of the unionElement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the unionElement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUnionElement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link UnionElement }
         * 
         * 
         */
        public List<UnionElement> getUnionElement() {
            if (unionElement == null) {
                unionElement = new ArrayList<UnionElement>();
            }
            return this.unionElement;
        }

        /**
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
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
         * Ruft den Wert der bitSize-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBitSize() {
            return bitSize;
        }

        /**
         * Legt den Wert der bitSize-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBitSize(BigInteger value) {
            this.bitSize = value;
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
         *       &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
         *       &lt;attribute name="DataTypeId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
         *       &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
         *       &lt;attribute name="Id" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class UnionElement {

            @XmlAttribute(name = "Name", required = true)
            protected String name;
            @XmlAttribute(name = "DataTypeId", required = true)
            protected String dataTypeId;
            @XmlAttribute(name = "BitSize")
            @XmlSchemaType(name = "unsignedLong")
            protected BigInteger bitSize;
            @XmlAttribute(name = "Id")
            protected String id;

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
             * Ruft den Wert der dataTypeId-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDataTypeId() {
                return dataTypeId;
            }

            /**
             * Legt den Wert der dataTypeId-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDataTypeId(String value) {
                this.dataTypeId = value;
            }

            /**
             * Ruft den Wert der bitSize-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getBitSize() {
                return bitSize;
            }

            /**
             * Legt den Wert der bitSize-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setBitSize(BigInteger value) {
                this.bitSize = value;
            }

            /**
             * Ruft den Wert der id-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getId() {
                return id;
            }

            /**
             * Legt den Wert der id-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setId(String value) {
                this.id = value;
            }

        }

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
     *       &lt;attribute name="Name" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeNonEmptyString" /&gt;
     *       &lt;attribute name="Address" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeMemoryAddress" /&gt;
     *       &lt;attribute name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" /&gt;
     *       &lt;attribute name="DataTypeId" use="required" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeName_1_50" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Variable {

        @XmlAttribute(name = "Name", required = true)
        protected String name;
        @XmlAttribute(name = "Address", required = true)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        protected byte[] address;
        @XmlAttribute(name = "BitSize")
        @XmlSchemaType(name = "unsignedLong")
        protected BigInteger bitSize;
        @XmlAttribute(name = "DataTypeId", required = true)
        protected String dataTypeId;

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
         * Ruft den Wert der bitSize-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBitSize() {
            return bitSize;
        }

        /**
         * Legt den Wert der bitSize-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBitSize(BigInteger value) {
            this.bitSize = value;
        }

        /**
         * Ruft den Wert der dataTypeId-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDataTypeId() {
            return dataTypeId;
        }

        /**
         * Legt den Wert der dataTypeId-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDataTypeId(String value) {
            this.dataTypeId = value;
        }

    }

}
