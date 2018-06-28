package com.credit.bean.transferBean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="ModelList")
public class ModelList {

	List<Model2> modelList;

	@XmlElement(name="Model")
	public List<Model2> getModelList() {
		return modelList;
	}

	public void setModelList(List<Model2> modelList) {
		this.modelList = modelList;
	}
	
	
}
