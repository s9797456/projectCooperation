
package com.nbchina.ws.api.entinfo.baseinfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>partner complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="partner">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identifyno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identifytype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isdelete" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="partnername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="personid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="realCapiItems" type="{http://baseInfo.entInfo.api.ws.nbchina.com/}partnerRealCapi" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="realcapi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shouldCapiItems" type="{http://baseInfo.entInfo.api.ws.nbchina.com/}partnerShouldCapi" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="shouldcapi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stockpercent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stocktype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partner", propOrder = {
    "entid",
    "identifyno",
    "identifytype",
    "isdelete",
    "partnername",
    "personid",
    "realCapiItems",
    "realcapi",
    "shouldCapiItems",
    "shouldcapi",
    "stockpercent",
    "stocktype",
    "type",
    "updateTime",
    "uuid"
})
public class Partner {

    protected String entid;
    protected String identifyno;
    protected String identifytype;
    protected BigDecimal isdelete;
    protected String partnername;
    protected String personid;
    @XmlElement(nillable = true)
    protected List<PartnerRealCapi> realCapiItems;
    protected String realcapi;
    @XmlElement(nillable = true)
    protected List<PartnerShouldCapi> shouldCapiItems;
    protected String shouldcapi;
    protected String stockpercent;
    protected String stocktype;
    protected String type;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTime;
    protected String uuid;

    /**
     * ��ȡentid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntid() {
        return entid;
    }

    /**
     * ����entid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntid(String value) {
        this.entid = value;
    }

    /**
     * ��ȡidentifyno���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifyno() {
        return identifyno;
    }

    /**
     * ����identifyno���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifyno(String value) {
        this.identifyno = value;
    }

    /**
     * ��ȡidentifytype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifytype() {
        return identifytype;
    }

    /**
     * ����identifytype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifytype(String value) {
        this.identifytype = value;
    }

    /**
     * ��ȡisdelete���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIsdelete() {
        return isdelete;
    }

    /**
     * ����isdelete���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIsdelete(BigDecimal value) {
        this.isdelete = value;
    }

    /**
     * ��ȡpartnername���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnername() {
        return partnername;
    }

    /**
     * ����partnername���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnername(String value) {
        this.partnername = value;
    }

    /**
     * ��ȡpersonid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonid() {
        return personid;
    }

    /**
     * ����personid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonid(String value) {
        this.personid = value;
    }

    /**
     * Gets the value of the realCapiItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the realCapiItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRealCapiItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerRealCapi }
     * 
     * 
     */
    public List<PartnerRealCapi> getRealCapiItems() {
        if (realCapiItems == null) {
            realCapiItems = new ArrayList<PartnerRealCapi>();
        }
        return this.realCapiItems;
    }

    /**
     * ��ȡrealcapi���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealcapi() {
        return realcapi;
    }

    /**
     * ����realcapi���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealcapi(String value) {
        this.realcapi = value;
    }

    /**
     * Gets the value of the shouldCapiItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shouldCapiItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShouldCapiItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerShouldCapi }
     * 
     * 
     */
    public List<PartnerShouldCapi> getShouldCapiItems() {
        if (shouldCapiItems == null) {
            shouldCapiItems = new ArrayList<PartnerShouldCapi>();
        }
        return this.shouldCapiItems;
    }

    /**
     * ��ȡshouldcapi���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShouldcapi() {
        return shouldcapi;
    }

    /**
     * ����shouldcapi���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShouldcapi(String value) {
        this.shouldcapi = value;
    }

    /**
     * ��ȡstockpercent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStockpercent() {
        return stockpercent;
    }

    /**
     * ����stockpercent���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStockpercent(String value) {
        this.stockpercent = value;
    }

    /**
     * ��ȡstocktype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStocktype() {
        return stocktype;
    }

    /**
     * ����stocktype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStocktype(String value) {
        this.stocktype = value;
    }

    /**
     * ��ȡtype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * ����type���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * ��ȡupdateTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTime() {
        return updateTime;
    }

    /**
     * ����updateTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTime(XMLGregorianCalendar value) {
        this.updateTime = value;
    }

    /**
     * ��ȡuuid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * ����uuid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

}
