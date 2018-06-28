package com.credit.bean.transferBean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="ShareHolderList")
public class ShareHolderList {

	List<ShareHolder> shareHolderList;

	@XmlElement(name="ShareHolder")
	public List<ShareHolder> getShareHolderList() {
		return shareHolderList;
	}

	public void setShareHolderList(List<ShareHolder> shareHolderList) {
		this.shareHolderList = shareHolderList;
	}
	
}
