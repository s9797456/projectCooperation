
package com.nbchina.ws.api.entinfo.baseinfo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="queryResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultlist" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="totalrecord" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryResult", propOrder = {
    "resultlist",
    "totalrecord"
})
public class QueryResult {

    @XmlElement(nillable = true)
    protected List<Object> resultlist;
    protected long totalrecord;

    /**
     * Gets the value of the resultlist property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultlist property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultlist().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getResultlist() {
        if (resultlist == null) {
            resultlist = new ArrayList<Object>();
        }
        return this.resultlist;
    }

    /**
     * 获取totalrecord属性的值。
     * 
     */
    public long getTotalrecord() {
        return totalrecord;
    }

    /**
     * 设置totalrecord属性的值。
     * 
     */
    public void setTotalrecord(long value) {
        this.totalrecord = value;
    }

}
