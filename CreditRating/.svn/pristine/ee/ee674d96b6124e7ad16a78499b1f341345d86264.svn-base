package com.credit.bean.transferBean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="objInfos")
public class ScanFileList {
	
	List<ScanFile> list;

	/**
	 * @return the list
	 */
	@XmlElement(name="objInfo")
	public List<ScanFile> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<ScanFile> list) {
		this.list = list;
	}

	public ScanFileList(List<ScanFile> list) {
		
		this.list = list;
	}
	
	public ScanFileList(){}
	

}
