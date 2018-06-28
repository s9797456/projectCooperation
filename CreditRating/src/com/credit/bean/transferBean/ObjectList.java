package com.credit.bean.transferBean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="objInfos")
public class ObjectList<T> {
	
	private List<T> objList;

	/**
	 * @return the objList
	 */
	@XmlElement(name="objInfo")
	public List<T> getObjList() {
		return objList;
	}

	/**
	 * @param objList the objList to set
	 */
	public void setObjList(List<T> objList) {
		this.objList = objList;
	}

	
	
	
	
	

}
