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
 * <p>Java-Klasse f�r TypeFlowControl.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="TypeFlowControl"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="None"/&gt;
 *     &lt;enumeration value="RTS/CTS"/&gt;
 *     &lt;enumeration value="DTR/DSR"/&gt;
 *     &lt;enumeration value="XON/XOFF"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TypeFlowControl")
@XmlEnum
public enum TypeFlowControl {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("RTS/CTS")
    RTS_CTS("RTS/CTS"),
    @XmlEnumValue("DTR/DSR")
    DTR_DSR("DTR/DSR"),
    @XmlEnumValue("XON/XOFF")
    XON_XOFF("XON/XOFF");
    private final String value;

    TypeFlowControl(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeFlowControl fromValue(String v) {
        for (TypeFlowControl c: TypeFlowControl.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
