package com.credit.bean.vo.addition;



public class ModelVO {
	private String uuid;
	private String name;
	private String version;
	private String XMLurl;
	private String pushXMLurl;
	private String updateTime;
	private String remarks;
	private String childs;
	private String parentID;
	private String visible;
	private String orderNO;


	
	public ModelVO(){}



	public ModelVO(String uuid, String name, String version, String xMLurl,
			String pushXMLurl, String updateTime, String remarks,
			String childs, String parentID,String visible,String orderNO) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.version = version;
		XMLurl = xMLurl;
		this.pushXMLurl = pushXMLurl;
		this.updateTime = updateTime;
		this.remarks = remarks;
		this.childs = childs;
		this.parentID = parentID;
		this.visible = visible;
		this.orderNO = orderNO;
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
		ModelVO other = (ModelVO) obj;
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
	 * @return the pushXMLurl
	 */
	public String getPushXMLurl() {
		return pushXMLurl;
	}



	/**
	 * @param pushXMLurl the pushXMLurl to set
	 */
	public void setPushXMLurl(String pushXMLurl) {
		this.pushXMLurl = pushXMLurl;
	}



	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}



	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}



	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}



	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	/**
	 * @return the childs
	 */
	public String getChilds() {
		return childs;
	}



	/**
	 * @param childs the childs to set
	 */
	public void setChilds(String childs) {
		this.childs = childs;
	}



	/**
	 * @return the parentID
	 */
	public String getParentID() {
		return parentID;
	}



	/**
	 * @param parentID the parentID to set
	 */
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}



	public String getVisible() {
		return visible;
	}



	public void setVisible(String visible) {
		this.visible = visible;
	}



	public String getOrderNO() {
		return orderNO;
	}



	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}

	
	
}
