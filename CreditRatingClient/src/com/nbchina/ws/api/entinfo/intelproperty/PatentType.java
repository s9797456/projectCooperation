
package com.nbchina.ws.api.entinfo.intelproperty;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>patentType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="patentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SYXX"/>
 *     &lt;enumeration value="FMGB"/>
 *     &lt;enumeration value="FMSQ"/>
 *     &lt;enumeration value="WGSJ"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "patentType")
@XmlEnum
public enum PatentType {

    SYXX,
    FMGB,
    FMSQ,
    WGSJ;

    public String value() {
        return name();
    }

    public static PatentType fromValue(String v) {
        return valueOf(v);
    }

}
