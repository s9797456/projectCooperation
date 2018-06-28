
package com.nbchina.ws.api.entinfo.riskinfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>businessExceptionWithBLOBs complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="businessExceptionWithBLOBs">
 *   &lt;complexContent>
 *     &lt;extension base="{http://riskInfo.entInfo.api.ws.nbchina.com/}businessException">
 *       &lt;sequence>
 *         &lt;element name="listinreason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listoutreason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "businessExceptionWithBLOBs", propOrder = {
    "listinreason",
    "listoutreason"
})
public class BusinessExceptionWithBLOBs
    extends BusinessException
{

    protected String listinreason;
    protected String listoutreason;

    /**
     * ��ȡlistinreason���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListinreason() {
        return listinreason;
    }

    /**
     * ����listinreason���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListinreason(String value) {
        this.listinreason = value;
    }

    /**
     * ��ȡlistoutreason���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListoutreason() {
        return listoutreason;
    }

    /**
     * ����listoutreason���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListoutreason(String value) {
        this.listoutreason = value;
    }

}
