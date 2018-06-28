package com.credit.bean.vo.addition;

import java.io.Serializable;
/**
 * Description:Excel获取集合用
 * Company: 汇誉通
 * @author 严树炜
 * @date 2017-9-18下午4:51:48
 */
public class ModelIndex implements Serializable{
	
	private static final long serialVersionUID = 543375639447825937L;
	private String uuid;//主键
	private String first;//一级指标
	private String second;//二级指标
	private String third;//三级指标
	private String wight;//权重
	private String one;//1
	private String point75;//0.75
	private String half;//0.5
	private String point25;//0.25
	private String zero;//0
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getThird() {
		return third;
	}
	public void setThird(String third) {
		this.third = third;
	}
	public String getWight() {
		return wight;
	}
	public void setWight(String wight) {
		this.wight = wight;
	}
	public String getOne() {
		return one;
	}
	public void setOne(String one) {
		this.one = one;
	}
	public String getHalf() {
		return half;
	}
	public void setHalf(String half) {
		this.half = half;
	}
	public String getZero() {
		return zero;
	}
	public void setZero(String zero) {
		this.zero = zero;
	}
	public String getPoint75() {
		return point75;
	}
	public void setPoint75(String point75) {
		this.point75 = point75;
	}
	public String getPoint25() {
		return point25;
	}
	public void setPoint25(String point25) {
		this.point25 = point25;
	}
	
}
