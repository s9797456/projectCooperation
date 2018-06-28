
package com.nbchina.ws.api.entinfo.riskinfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>selectChattelMortgageDetailResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="selectChattelMortgageDetailResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://riskInfo.entInfo.api.ws.nbchina.com/}chattelMortgage" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "selectChattelMortgageDetailResponse", propOrder = {
    "_return"
})
public class SelectChattelMortgageDetailResponse {

    @XmlElement(name = "return")
    protected ChattelMortgage _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ChattelMortgage }
     *     
     */
    public ChattelMortgage getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ChattelMortgage }
     *     
     */
    public void setReturn(ChattelMortgage value) {
        this._return = value;
    }

}
