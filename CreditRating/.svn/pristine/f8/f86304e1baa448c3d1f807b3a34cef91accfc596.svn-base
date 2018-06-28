package com.credit.bean.transferBean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="objInfos")
public class EnterpriseInfoList {
	
	List<EnterpriseInfo> list;

	/**
	 * @return the list
	 */
	@XmlElement(name="objInfo")
	public List<EnterpriseInfo> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<EnterpriseInfo> list) {
		this.list = list;
	}

	public EnterpriseInfoList(List<EnterpriseInfo> list) {
		
		this.list = list;
	}
	
	public EnterpriseInfoList() {}

}
