
package com.nbchina.ws.api.entinfo.intelproperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>trademarkWithBLOBs complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="trademarkWithBLOBs">
 *   &lt;complexContent>
 *     &lt;extension base="{http://intelProperty.entInfo.api.ws.nbchina.com/}trademark">
 *       &lt;sequence>
 *         &lt;element name="dynamics" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="products" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trademarkWithBLOBs", propOrder = {
    "dynamics",
    "products"
})
public class TrademarkWithBLOBs
    extends Trademark
{

    protected String dynamics;
    protected String products;

    /**
     * 获取dynamics属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDynamics() {
        return dynamics;
    }

    /**
     * 设置dynamics属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDynamics(String value) {
        this.dynamics = value;
    }

    /**
     * 获取products属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducts() {
        return products;
    }

    /**
     * 设置products属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducts(String value) {
        this.products = value;
    }

}
