package com.credit.bean.vo.privilege;

public class MenuVO {
	/**
	 * 主键
	 */
	private String uuid;
	/**
	 * 上级菜单的主键
	 */
	private String parentID;
	/**
	 * 上级菜单名称
	 */
	private String parentName;
	/**
	 * 当前菜单名称
	 */
	private String name;
	/**
	 * 菜单编号
	 */
	private String sn;

	/**
	 * 排序号
	 */
	private int orderNO;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 地址
	 */
	private String url;
	/**
	 * target 
	 */
	private String target;
	/**
	 * rel
	 */
	private String rel;
	/**
	 * target | rel
	 */
	private String target_rel;
	/**
	 * 分配地址 | 赋权
	 */
	private String distributionOfAddress;
	
	private Integer visible;
	
	public MenuVO(){}
	
	public MenuVO(String uuid){
		this.uuid=uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(int orderNO) {
		this.orderNO = orderNO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getDistributionOfAddress() {
		return distributionOfAddress;
	}

	public void setDistributionOfAddress(String distributionOfAddress) {
		this.distributionOfAddress = distributionOfAddress;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getTarget_rel() {
		return target_rel;
	}
	public void setTarget_rel(String target_rel) {
		this.target_rel = target_rel;
	}
	public Integer getVisible() {
		return visible;
	}


	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuVO other = (MenuVO) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MenuVo [uuid=" + uuid + ", parentID=" + parentID
				+ ", parentName=" + parentName + ", name=" + name + ", sn="
				+ sn + ", orderNO=" + orderNO + ", status=" + status + ", url="
				+ url + ", target=" + target + ", distributionOfAddress="
				+ distributionOfAddress + "]";
	}
	
}