
package com.nbchina.ws.api.entinfo.baseinfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>selectByPrimaryKeyWithChildEntsResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="selectByPrimaryKeyWithChildEntsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://baseInfo.entInfo.api.ws.nbchina.com/}enterprise" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "selectByPrimaryKeyWithChildEntsResponse", propOrder = {
    "_return"
})
public class SelectByPrimaryKeyWithChildEntsResponse {

    @XmlElement(name = "return")
    protected Enterprise _return;

    /**
     * ��ȡreturn���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Enterprise }
     *     
     */
    public Enterprise getReturn() {
        return _return;
    }

    /**
     * ����return���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Enterprise }
     *     
     */
    public void setReturn(Enterprise value) {
        this._return = value;
    }

}
