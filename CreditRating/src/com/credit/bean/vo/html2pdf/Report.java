package com.credit.bean.vo.html2pdf;


import java.util.List;

import com.credit.bean.enterprise.Executives;
import com.credit.bean.enterprise.Shareholder;
import com.credit.util.model.IndexRateVo;

import lombok.Data;


@Data
public class Report {

    private String entName;//企业名称
    private String encoding;//报告编码
    private String qrcode;//报告二维码
    private String gradeTime;//报告时间
    private String valueTime;//有效期至
    private List<IndexRateVo> indexRates;//指标得分比重
    private String finalLevel;//终评等级
    private String scoreSummary;//评分总结
    private String entType;//企业类型
    private String address;//注册地址
    private String zipCode;//邮政编码
    private String legalPerson;//法定代表人
    private String regiCapital;//注册资本
    private String setupDate;//注册日期
    private String uscc;//统一社会信用代码
    private String regisOrg;//登记机关
    private String website;//公司网站
    private String tel;//联系电话
    private String fax;//传真
    private String scale;//雇员数量
    private String businessScope;//经营范围
    private List<Shareholder> shareHolders;//股东
    private List<Executives> executives;//高管
    private List<FinancialData> financialDatas;//资产负债表数据
    private List<YearVo> years;//资产负债表所用年份
    private List<FinancialData> profitDatas;//利润表数据
    private List<YearVo> profitYears;//利润表所用年份
    private YearVo importantYears;//重要财务数据表中所用年份
    private List<FinancialChange> changes;//重要财务数据表
    private List<FinancialChange> changes2;//重要财务比率表
    private String img1;//图片
    private String img2;//图片
	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getGradeTime() {
		return gradeTime;
	}

	public void setGradeTime(String gradeTime) {
		this.gradeTime = gradeTime;
	}

	public String getValueTime() {
		return valueTime;
	}

	public void setValueTime(String valueTime) {
		this.valueTime = valueTime;
	}

	public List<IndexRateVo> getIndexRates() {
		return indexRates;
	}

	public void setIndexRates(List<IndexRateVo> indexRates) {
		this.indexRates = indexRates;
	}

	public String getFinalLevel() {
		return finalLevel;
	}

	public void setFinalLevel(String finalLevel) {
		this.finalLevel = finalLevel;
	}

	public String getScoreSummary() {
		return scoreSummary;
	}

	public void setScoreSummary(String scoreSummary) {
		this.scoreSummary = scoreSummary;
	}

	public String getEntType() {
		return entType;
	}

	public void setEntType(String entType) {
		this.entType = entType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getRegiCapital() {
		return regiCapital;
	}

	public void setRegiCapital(String regiCapital) {
		this.regiCapital = regiCapital;
	}

	public String getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(String setupDate) {
		this.setupDate = setupDate;
	}

	public String getUscc() {
		return uscc;
	}

	public void setUscc(String uscc) {
		this.uscc = uscc;
	}

	public String getRegisOrg() {
		return regisOrg;
	}

	public void setRegisOrg(String regisOrg) {
		this.regisOrg = regisOrg;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public List<Shareholder> getShareHolders() {
		return shareHolders;
	}

	public void setShareHolders(List<Shareholder> shareHolders) {
		this.shareHolders = shareHolders;
	}

	public List<Executives> getExecutives() {
		return executives;
	}

	public void setExecutives(List<Executives> executives) {
		this.executives = executives;
	}

	public List<FinancialData> getFinancialDatas() {
		return financialDatas;
	}

	public void setFinancialDatas(List<FinancialData> financialDatas) {
		this.financialDatas = financialDatas;
	}

	public List<YearVo> getYears() {
		return years;
	}

	public void setYears(List<YearVo> years) {
		this.years = years;
	}

	public List<YearVo> getProfitYears() {
		return profitYears;
	}

	public void setProfitYears(List<YearVo> profitYears) {
		this.profitYears = profitYears;
	}

	public List<FinancialData> getProfitDatas() {
		return profitDatas;
	}

	public void setProfitDatas(List<FinancialData> profitDatas) {
		this.profitDatas = profitDatas;
	}

	public YearVo getImportantYears() {
		return importantYears;
	}

	public void setImportantYears(YearVo importantYears) {
		this.importantYears = importantYears;
	}


	public List<FinancialChange> getChanges() {
		return changes;
	}

	public void setChanges(List<FinancialChange> changes) {
		this.changes = changes;
	}

	public List<FinancialChange> getChanges2() {
		return changes2;
	}

	public void setChanges2(List<FinancialChange> changes2) {
		this.changes2 = changes2;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	
}
