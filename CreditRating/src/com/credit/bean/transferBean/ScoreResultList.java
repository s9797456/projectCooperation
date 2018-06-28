package com.credit.bean.transferBean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="objInfos")
public class ScoreResultList {
	List<ScoreResult> list;

	@XmlElement(name="objInfo")
	public List<ScoreResult> getList() {
		return list;
	}

	public void setList(List<ScoreResult> list) {
		this.list = list;
	}

	public ScoreResultList() {
	}
	
	public ScoreResultList(List<ScoreResult> list) {
		this.list=list;
	}
	
}
