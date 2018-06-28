
package com.nbchina.ws.api.entinfo.intelproperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>selectTrademarkByPKResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="selectTrademarkByPKResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://intelProperty.entInfo.api.ws.nbchina.com/}trademarkWithBLOBs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "selectTrademarkByPKResponse", propOrder = {
    "_return"
})
public class SelectTrademarkByPKResponse {

    @XmlElement(name = "return")
    protected TrademarkWithBLOBs _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TrademarkWithBLOBs }
     *     
     */
    public TrademarkWithBLOBs getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TrademarkWithBLOBs }
     *     
     */
    public void setReturn(TrademarkWithBLOBs value) {
        this._return = value;
    }

}
