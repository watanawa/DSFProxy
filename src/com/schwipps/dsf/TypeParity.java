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
 * <p>Java-Klasse f�r TypeParity.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="TypeParity"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="None"/&gt;
 *     &lt;enumeration value="Odd"/&gt;
 *     &lt;enumeration value="Even"/&gt;
 *     &lt;enumeration value="Mark"/&gt;
 *     &lt;enumeration value="Space"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TypeParity")
@XmlEnum
public enum TypeParity {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Odd")
    ODD("Odd"),
    @XmlEnumValue("Even")
    EVEN("Even"),
    @XmlEnumValue("Mark")
    MARK("Mark"),
    @XmlEnumValue("Space")
    SPACE("Space");
    private final String value;

    TypeParity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeParity fromValue(String v) {
        for (TypeParity c: TypeParity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
