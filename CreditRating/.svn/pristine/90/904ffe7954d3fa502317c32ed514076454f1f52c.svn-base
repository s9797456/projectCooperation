package com.credit.bean.transferBean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FinanceList")
public class FinanceList {

	List<Finance2> financeList;

	@XmlElement(name="Finance")
	public List<Finance2> getFinanceList() {
		return financeList;
	}

	public void setFinanceList(List<Finance2> financeList) {
		this.financeList = financeList;
	}

	public FinanceList() {
	}
	
	public FinanceList(List<Finance2> list){
		this.financeList=list;
	}
}
