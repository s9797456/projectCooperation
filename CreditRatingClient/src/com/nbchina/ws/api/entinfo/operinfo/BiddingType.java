
package com.nbchina.ws.api.entinfo.operinfo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>biddingType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="biddingType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ZBJG"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "biddingType")
@XmlEnum
public enum BiddingType {

    ZBJG;

    public String value() {
        return name();
    }

    public static BiddingType fromValue(String v) {
        return valueOf(v);
    }

}
