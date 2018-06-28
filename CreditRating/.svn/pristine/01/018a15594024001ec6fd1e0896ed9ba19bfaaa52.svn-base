package com.credit.bean.person;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.credit.bean.addition.Model;
import com.credit.bean.member.Customer;

@Entity
@Table(name="TI_PerResult")
public class PerResult implements Serializable {
	
	private static final long serialVersionUID = 1738012262511552378L;

	@Id
	@Column(length = 32)
	private String uuid;//主键

	@Column(columnDefinition="number(10,2) default 0")
	private Double preliminaryScore;//初评分数 

	@Column(length = 20)
	private String preliminaryLevel;//初评等级

	@Column(columnDefinition="number(10,2) default 0")
	private Double finalScore;//终评分数

	@Column(length = 20)
	private String finalLevel;//终评等级

	@Column(length = 200)
	private String adjustOption;//调整项
	
	@Column(length = 500)
	private String adjustReason;//调整说明
	
	@Column(length = 500)
	private String advantageReason;//优势说明
	
	@Column(length = 500)
	private String scoreSummary;//评分总结

	@Column(length = 500)
	private String sentiment;//舆情信息 
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date gradeTime;//评级时间
	
	@Column(length = 150)
	private String reportUrl;//报告地址
	
	@Column(length = 100)
	private String encoding;//评级编码
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date scoreXMLTime;//调查更新时间
	
	@Column(length = 100)
	private String scoreXMLUrl;//模型地址（评分人员调查保存模型地址）
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date inputXMLTime;//录入保存时间
	
	@Column(length = 100)
	private String inputXMLUrl;//模型地址（客户前断录入保存模型地址）
	
	@Column(columnDefinition="number(8) default 0")
	private Integer inputXMLState;//模型数据是否跨域上传至后台
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "modelID")
	private Model model;//模型外键
	
	@OneToOne(cascade = CascadeType.REMOVE, optional = true)
	@JoinColumn(name = "PerID")
	private PerBaseInfo perBaseInfo;//个人外键
	
	@OneToOne(cascade = CascadeType.REMOVE, optional = true)
	@JoinColumn(name = "customerID")
	private Customer customer;//客户外键
	
	public PerResult() {
		super();
	}
	
	public PerResult(String uuid) {
		super();
		this.uuid = uuid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerResult other = (PerResult) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Double getPreliminaryScore() {
		return preliminaryScore;
	}

	public void setPreliminaryScore(Double preliminaryScore) {
		this.preliminaryScore = preliminaryScore;
	}

	public String getPreliminaryLevel() {
		return preliminaryLevel;
	}

	public void setPreliminaryLevel(String preliminaryLevel) {
		this.preliminaryLevel = preliminaryLevel;
	}

	public Double getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(Double finalScore) {
		this.finalScore = finalScore;
	}

	public String getFinalLevel() {
		return finalLevel;
	}

	public void setFinalLevel(String finalLevel) {
		this.finalLevel = finalLevel;
	}

	public String getAdjustOption() {
		return adjustOption;
	}

	public void setAdjustOption(String adjustOption) {
		this.adjustOption = adjustOption;
	}

	public String getAdjustReason() {
		return adjustReason;
	}

	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}

	public String getAdvantageReason() {
		return advantageReason;
	}

	public void setAdvantageReason(String advantageReason) {
		this.advantageReason = advantageReason;
	}

	public String getScoreSummary() {
		return scoreSummary;
	}

	public void setScoreSummary(String scoreSummary) {
		this.scoreSummary = scoreSummary;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public Date getGradeTime() {
		return gradeTime;
	}

	public void setGradeTime(Date gradeTime) {
		this.gradeTime = gradeTime;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Date getScoreXMLTime() {
		return scoreXMLTime;
	}

	public void setScoreXMLTime(Date scoreXMLTime) {
		this.scoreXMLTime = scoreXMLTime;
	}

	public String getScoreXMLUrl() {
		return scoreXMLUrl;
	}

	public void setScoreXMLUrl(String scoreXMLUrl) {
		this.scoreXMLUrl = scoreXMLUrl;
	}

	public Date getInputXMLTime() {
		return inputXMLTime;
	}

	public void setInputXMLTime(Date inputXMLTime) {
		this.inputXMLTime = inputXMLTime;
	}

	public String getInputXMLUrl() {
		return inputXMLUrl;
	}

	public void setInputXMLUrl(String inputXMLUrl) {
		this.inputXMLUrl = inputXMLUrl;
	}

	public Integer getInputXMLState() {
		return inputXMLState;
	}

	public void setInputXMLState(Integer inputXMLState) {
		this.inputXMLState = inputXMLState;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public PerBaseInfo getPerBaseInfo() {
		return perBaseInfo;
	}

	public void setPerBaseInfo(PerBaseInfo perBaseInfo) {
		this.perBaseInfo = perBaseInfo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
}
