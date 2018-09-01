//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.09.01 um 10:02:54 PM CEST 
//


package com.schwipps.dsf;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r TypeElementaryDataTypes.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="TypeElementaryDataTypes"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="char"/&gt;
 *     &lt;enumeration value="uchar"/&gt;
 *     &lt;enumeration value="int"/&gt;
 *     &lt;enumeration value="uint"/&gt;
 *     &lt;enumeration value="bool"/&gt;
 *     &lt;enumeration value="float"/&gt;
 *     &lt;enumeration value="string"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TypeElementaryDataTypes")
@XmlEnum
public enum TypeElementaryDataTypes {

    @XmlEnumValue("char")
    CHAR("char"),
    @XmlEnumValue("uchar")
    UCHAR("uchar"),
    @XmlEnumValue("int")
    INT("int"),
    @XmlEnumValue("uint")
    UINT("uint"),
    @XmlEnumValue("bool")
    BOOL("bool"),
    @XmlEnumValue("float")
    FLOAT("float"),
    @XmlEnumValue("string")
    STRING("string");
    private final String value;

    TypeElementaryDataTypes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeElementaryDataTypes fromValue(String v) {
        for (TypeElementaryDataTypes c: TypeElementaryDataTypes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
