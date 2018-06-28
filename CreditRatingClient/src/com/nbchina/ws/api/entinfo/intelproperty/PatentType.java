
package com.nbchina.ws.api.entinfo.intelproperty;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>patentType�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
