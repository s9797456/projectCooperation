
package com.nbchina.ws.api.entinfo.riskinfo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>chattelMortgage complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="chattelMortgage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="collateralname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="collateralremarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="condition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duedate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="guaranteescope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idtype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isdelete" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="no" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownership" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pledgedrightamount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pledgedrighttype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="registerdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="registrationauthority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "chattelMortgage", propOrder = {
    "collateralname",
    "collateralremarks",
    "condition",
    "duedate",
    "guaranteescope",
    "idno",
    "idtype",
    "isdelete",
    "name",
    "no",
    "ownership",
    "pledgedrightamount",
    "pledgedrighttype",
    "registerdate",
    "registrationauthority",
    "remarks",
    "updateTime",
    "uuid"
})
public class ChattelMortgage {

    protected String collateralname;
    protected String collateralremarks;
    protected String condition;
    protected String duedate;
    protected String guaranteescope;
    protected String idno;
    protected String idtype;
    protected BigDecimal isdelete;
    protected String name;
    protected String no;
    protected String ownership;
    protected String pledgedrightamount;
    protected String pledgedrighttype;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar registerdate;
    protected String registrationauthority;
    protected String remarks;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTime;
    protected String uuid;

    /**
     * ��ȡcollateralname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollateralname() {
        return collateralname;
    }

    /**
     * ����collateralname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollateralname(String value) {
        this.collateralname = value;
    }

    /**
     * ��ȡcollateralremarks���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollateralremarks() {
        return collateralremarks;
    }

    /**
     * ����collateralremarks���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollateralremarks(String value) {
        this.collateralremarks = value;
    }

    /**
     * ��ȡcondition���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondition() {
        return condition;
    }

    /**
     * ����condition���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondition(String value) {
        this.condition = value;
    }

    /**
     * ��ȡduedate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuedate() {
        return duedate;
    }

    /**
     * ����duedate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuedate(String value) {
        this.duedate = value;
    }

    /**
     * ��ȡguaranteescope���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuaranteescope() {
        return guaranteescope;
    }

    /**
     * ����guaranteescope���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuaranteescope(String value) {
        this.guaranteescope = value;
    }

    /**
     * ��ȡidno���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdno() {
        return idno;
    }

    /**
     * ����idno���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdno(String value) {
        this.idno = value;
    }

    /**
     * ��ȡidtype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdtype() {
        return idtype;
    }

    /**
     * ����idtype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdtype(String value) {
        this.idtype = value;
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
     * ��ȡname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * ����name���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * ��ȡno���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNo() {
        return no;
    }

    /**
     * ����no���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNo(String value) {
        this.no = value;
    }

    /**
     * ��ȡownership���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnership() {
        return ownership;
    }

    /**
     * ����ownership���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnership(String value) {
        this.ownership = value;
    }

    /**
     * ��ȡpledgedrightamount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPledgedrightamount() {
        return pledgedrightamount;
    }

    /**
     * ����pledgedrightamount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPledgedrightamount(String value) {
        this.pledgedrightamount = value;
    }

    /**
     * ��ȡpledgedrighttype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPledgedrighttype() {
        return pledgedrighttype;
    }

    /**
     * ����pledgedrighttype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPledgedrighttype(String value) {
        this.pledgedrighttype = value;
    }

    /**
     * ��ȡregisterdate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRegisterdate() {
        return registerdate;
    }

    /**
     * ����registerdate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRegisterdate(XMLGregorianCalendar value) {
        this.registerdate = value;
    }

    /**
     * ��ȡregistrationauthority���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationauthority() {
        return registrationauthority;
    }

    /**
     * ����registrationauthority���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationauthority(String value) {
        this.registrationauthority = value;
    }

    /**
     * ��ȡremarks���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * ����remarks���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarks(String value) {
        this.remarks = value;
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
