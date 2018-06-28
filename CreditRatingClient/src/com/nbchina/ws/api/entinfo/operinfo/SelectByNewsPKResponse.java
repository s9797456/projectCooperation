
package com.nbchina.ws.api.entinfo.operinfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>selectByNewsPKResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="selectByNewsPKResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://operInfo.entInfo.api.ws.nbchina.com/}news" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "selectByNewsPKResponse", propOrder = {
    "_return"
})
public class SelectByNewsPKResponse {

    @XmlElement(name = "return")
    protected News _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link News }
     *     
     */
    public News getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link News }
     *     
     */
    public void setReturn(News value) {
        this._return = value;
    }

}
