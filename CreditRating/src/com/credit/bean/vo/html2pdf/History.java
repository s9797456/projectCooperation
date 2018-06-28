package com.credit.bean.vo.html2pdf;



import java.util.List;

import com.credit.util.model.IndexVO;

import lombok.Data;


@Data
public class History {

    private String entName;//企业名称

    private String encoding;//报告编码

    private String gradeTime;//评分时间

    private String uscc;//统一社会信用代码
    
	private String setupDate; // 企业成立日期
    
	private String issueDate; // 企业发照日期
    
	private String startDate;// 营业开始日期
    
	private String endDate;// 营业结束日期
    
	private String address; // 企业地址
    
	private String regiCapital; // 注册资本
    
	private String legalPerson; // 法定代表人
	
	private String tel; // 电话

	private String email;// 电子邮箱
	
	private String website;//官网
	
	private String scale; //人员规模
	
	private String entType; //公司性质
	
	private String regisOrg; //登记机关
	
	private String industry; // 所属行业
	
	private String businessScope; // 经营范围
	
	private String brief; //企业简介
	
	private String model;//模型名称
	
	private String finalScore;//终评分数
	
	private String finalLevel;//终评等级
	
	private String adjustOption;//调整项
	
	private List<IndexVO> indexs;

	/**
	 * @return the entName
	 */
	public String getEntName() {
		return entName;
	}

	/**
	 * @param entName the entName to set
	 */
	public void setEntName(String entName) {
		this.entName = entName;
	}

	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * @return the gradeTime
	 */
	public String getGradeTime() {
		return gradeTime;
	}

	/**
	 * @param gradeTime the gradeTime to set
	 */
	public void setGradeTime(String gradeTime) {
		this.gradeTime = gradeTime;
	}

	/**
	 * @return the uscc
	 */
	public String getUscc() {
		return uscc;
	}

	/**
	 * @param uscc the uscc to set
	 */
	public void setUscc(String uscc) {
		this.uscc = uscc;
	}

	/**
	 * @return the setupDate
	 */
	public String getSetupDate() {
		return setupDate;
	}

	/**
	 * @param setupDate the setupDate to set
	 */
	public void setSetupDate(String setupDate) {
		this.setupDate = setupDate;
	}

	/**
	 * @return the issueDate
	 */
	public String getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the regiCapital
	 */
	public String getRegiCapital() {
		return regiCapital;
	}

	/**
	 * @param regiCapital the regiCapital to set
	 */
	public void setRegiCapital(String regiCapital) {
		this.regiCapital = regiCapital;
	}

	/**
	 * @return the legalPerson
	 */
	public String getLegalPerson() {
		return legalPerson;
	}

	/**
	 * @param legalPerson the legalPerson to set
	 */
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the scale
	 */
	public String getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(String scale) {
		this.scale = scale;
	}

	/**
	 * @return the entType
	 */
	public String getEntType() {
		return entType;
	}

	/**
	 * @param entType the entType to set
	 */
	public void setEntType(String entType) {
		this.entType = entType;
	}

	/**
	 * @return the regisOrg
	 */
	public String getRegisOrg() {
		return regisOrg;
	}

	/**
	 * @param regisOrg the regisOrg to set
	 */
	public void setRegisOrg(String regisOrg) {
		this.regisOrg = regisOrg;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * @return the businessScope
	 */
	public String getBusinessScope() {
		return businessScope;
	}

	/**
	 * @param businessScope the businessScope to set
	 */
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	/**
	 * @return the brief
	 */
	public String getBrief() {
		return brief;
	}

	/**
	 * @param brief the brief to set
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * @return the finalScore
	 */
	public String getFinalScore() {
		return finalScore;
	}

	/**
	 * @param finalScore the finalScore to set
	 */
	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}

	/**
	 * @return the finalLevel
	 */
	public String getFinalLevel() {
		return finalLevel;
	}

	/**
	 * @param finalLevel the finalLevel to set
	 */
	public void setFinalLevel(String finalLevel) {
		this.finalLevel = finalLevel;
	}

	/**
	 * @return the adjustOption
	 */
	public String getAdjustOption() {
		return adjustOption;
	}

	/**
	 * @param adjustOption the adjustOption to set
	 */
	public void setAdjustOption(String adjustOption) {
		this.adjustOption = adjustOption;
	}

	/**
	 * @return the indexs
	 */
	public List<IndexVO> getIndexs() {
		return indexs;
	}

	/**
	 * @param indexs the indexs to set
	 */
	public void setIndexs(List<IndexVO> indexs) {
		this.indexs = indexs;
	}
	
	
}
