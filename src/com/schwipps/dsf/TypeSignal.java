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
 * <p>Java-Klasse f�r TypeSignal.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="TypeSignal"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="RS232"/&gt;
 *     &lt;enumeration value="RS422"/&gt;
 *     &lt;enumeration value="RS485"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TypeSignal")
@XmlEnum
public enum TypeSignal {

    @XmlEnumValue("RS232")
    RS_232("RS232"),
    @XmlEnumValue("RS422")
    RS_422("RS422"),
    @XmlEnumValue("RS485")
    RS_485("RS485");
    private final String value;

    TypeSignal(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeSignal fromValue(String v) {
        for (TypeSignal c: TypeSignal.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
