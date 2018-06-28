package com.credit.bean.vo.html2pdf;

public class FinancialChange {
	
	private String name;//财务名称
	private String value1;//YearVo.year1  2015年对应值
	private String value2;//YearVo.year2  2014年对应值
	private String value3;//YearVo.year3  2013年对应值
	private String change1;//2015相对于2014的变化率
	private String change2;//2014相对于2013的变化率
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getChange1() {
		return change1;
	}
	public void setChange1(String change1) {
		this.change1 = change1;
	}
	public String getChange2() {
		return change2;
	}
	public void setChange2(String change2) {
		this.change2 = change2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
