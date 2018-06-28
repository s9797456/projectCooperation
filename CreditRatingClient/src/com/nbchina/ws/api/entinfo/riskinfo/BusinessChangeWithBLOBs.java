
package com.nbchina.ws.api.entinfo.riskinfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>businessChangeWithBLOBs complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="businessChangeWithBLOBs">
 *   &lt;complexContent>
 *     &lt;extension base="{http://riskInfo.entInfo.api.ws.nbchina.com/}businessChange">
 *       &lt;sequence>
 *         &lt;element name="afterchange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beforechange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "businessChangeWithBLOBs", propOrder = {
    "afterchange",
    "beforechange"
})
public class BusinessChangeWithBLOBs
    extends BusinessChange
{

    protected String afterchange;
    protected String beforechange;

    /**
     * ��ȡafterchange���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAfterchange() {
        return afterchange;
    }

    /**
     * ����afterchange���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAfterchange(String value) {
        this.afterchange = value;
    }

    /**
     * ��ȡbeforechange���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeforechange() {
        return beforechange;
    }

    /**
     * ����beforechange���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeforechange(String value) {
        this.beforechange = value;
    }

}
