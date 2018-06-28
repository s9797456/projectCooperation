package com.credit.bean.addition;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.transferBean.Model2;

@Entity
@Table(name="TB_Model")
public class Model implements Serializable {
	
	private static final long serialVersionUID = 4118828708261007287L;

	@Id
	@Column(length = 32)
	private String uuid;//主键

	@Column(length = 10)
	private String version;//版本号

	@Column(length = 100)
	private String name;//名称

	@Column(length = 200)
	private String XMLurl;//模型地址
	
	@Column(nullable = false)
	private Integer orderNO;// 排序号

	@Column(nullable=false)
	private Integer visible=1;	// 启用标志
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date updateTime;//更新时间
	
	@Column(length = 300)
	private String remark;//模型描述
	
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},mappedBy="parent",fetch=FetchType.LAZY)//fetch=FetchType.EAGER   EAGER立即加载LAZY是延迟加载
	private Set<Model> childs = new HashSet<Model>();

	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="parentID")
	@OrderBy("version asc")
	private Model parent;
	
	@OneToMany(mappedBy="model",cascade=CascadeType.REMOVE,fetch = FetchType.LAZY)
	private Set<EntResult> entResult  = new HashSet<EntResult>();
	
	public Model() {
		super();
	}

	public Model(String uuid) {
		super();
		this.uuid = uuid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the xMLurl
	 */
	public String getXMLurl() {
		return XMLurl;
	}

	/**
	 * @param xMLurl the xMLurl to set
	 */
	public void setXMLurl(String xMLurl) {
		XMLurl = xMLurl;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the childs
	 */
	public Set<Model> getChilds() {
		return childs;
	}

	/**
	 * @param childs the childs to set
	 */
	public void setChilds(Set<Model> childs) {
		this.childs = childs;
	}

	/**
	 * @return the parent
	 */
	public Model getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Model parent) {
		this.parent = parent;
	}

	/**
	 * @return the entResult
	 */
	public Set<EntResult> getEntResult() {
		return entResult;
	}

	/**
	 * @param entResult the entResult to set
	 */
	public void setEntResult(Set<EntResult> entResult) {
		this.entResult = entResult;
	}

	/**
	 * @return the orderNO
	 */
	public Integer getOrderNO() {
		return orderNO;
	}

	/**
	 * @param orderNO the orderNO to set
	 */
	public void setOrderNO(Integer orderNO) {
		this.orderNO = orderNO;
	}

	/**
	 * @return the visible
	 */
	public Integer getVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public Model(Model2 model) {
		super();
		this.uuid = model.getUuid().replace("-", "");
		this.version = "v1.0";
		this.name = model.getName();
		this.orderNO = model.getOrderNO();
		this.visible = model.getVisible();
		this.updateTime = model.getUpdateTime();
		this.remark = model.getRemarks();
	}

	

}
