package com.credit.bean.enterprise;

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
import com.credit.bean.transferBean.ScoreResult;

@Entity
@Table(name="TE_EntResult")
public class EntResult implements Serializable {
	
	private static final long serialVersionUID = 4118828708261007287L;

	@Id
	@Column(length = 32)
	private String uuid;//主键

	@Column(columnDefinition="number(10,2) default 0")
	private Double preliminaryScore;//初评分数 

	@Column(length = 10)
	private String preliminaryLevel;//初评等级

	@Column(columnDefinition="number(10,2) default 0")
	private Double finalScore;//终评分数

	@Column(length = 10)
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
	@JoinColumn(name = "EntID")
	private EntBaseInfo entBaseInfo;//企业外键
	
	@OneToOne(cascade = CascadeType.REMOVE, optional = true)
	@JoinColumn(name = "customerID")
	private Customer customer;//用户外键
	
	public EntResult() {
		super();
	}
	
	public EntResult(ScoreResult sr) {
		this.preliminaryLevel=sr.getPreliminaryLevel();
		this.preliminaryScore=sr.getPreliminarySocre();
		this.finalLevel=sr.getFinalLevel();
		this.finalScore=sr.getFinalSocre();
	}

	public EntResult(String uuid) {
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
		EntResult other = (EntResult) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the preliminaryScore
	 */
	public Double getPreliminaryScore() {
		return preliminaryScore;
	}

	/**
	 * @param preliminaryScore the preliminaryScore to set
	 */
	public void setPreliminaryScore(Double preliminaryScore) {
		this.preliminaryScore = preliminaryScore;
	}

	/**
	 * @return the preliminaryLevel
	 */
	public String getPreliminaryLevel() {
		return preliminaryLevel;
	}

	/**
	 * @param preliminaryLevel the preliminaryLevel to set
	 */
	public void setPreliminaryLevel(String preliminaryLevel) {
		this.preliminaryLevel = preliminaryLevel;
	}

	/**
	 * @return the finalScore
	 */
	public Double getFinalScore() {
		return finalScore;
	}

	/**
	 * @param finalScore the finalScore to set
	 */
	public void setFinalScore(Double finalScore) {
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
	 * @return the adjustReason
	 */
	public String getAdjustReason() {
		return adjustReason;
	}

	/**
	 * @param adjustReason the adjustReason to set
	 */
	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}

	/**
	 * @return the advantageReason
	 */
	public String getAdvantageReason() {
		return advantageReason;
	}

	/**
	 * @param advantageReason the advantageReason to set
	 */
	public void setAdvantageReason(String advantageReason) {
		this.advantageReason = advantageReason;
	}

	/**
	 * @return the scoreSummary
	 */
	public String getScoreSummary() {
		return scoreSummary;
	}

	/**
	 * @param scoreSummary the scoreSummary to set
	 */
	public void setScoreSummary(String scoreSummary) {
		this.scoreSummary = scoreSummary;
	}

	/**
	 * @return the sentiment
	 */
	public String getSentiment() {
		return sentiment;
	}

	/**
	 * @param sentiment the sentiment to set
	 */
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	/**
	 * @return the gradeTime
	 */
	public Date getGradeTime() {
		return gradeTime;
	}

	/**
	 * @param gradeTime the gradeTime to set
	 */
	public void setGradeTime(Date gradeTime) {
		this.gradeTime = gradeTime;
	}

	/**
	 * @return the reportUrl
	 */
	public String getReportUrl() {
		return reportUrl;
	}

	/**
	 * @param reportUrl the reportUrl to set
	 */
	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
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
	 * @return the scoreXMLTime
	 */
	public Date getScoreXMLTime() {
		return scoreXMLTime;
	}

	/**
	 * @param scoreXMLTime the scoreXMLTime to set
	 */
	public void setScoreXMLTime(Date scoreXMLTime) {
		this.scoreXMLTime = scoreXMLTime;
	}

	/**
	 * @return the scoreXMLUrl
	 */
	public String getScoreXMLUrl() {
		return scoreXMLUrl;
	}

	/**
	 * @param scoreXMLUrl the scoreXMLUrl to set
	 */
	public void setScoreXMLUrl(String scoreXMLUrl) {
		this.scoreXMLUrl = scoreXMLUrl;
	}

	/**
	 * @return the inputXMLTime
	 */
	public Date getInputXMLTime() {
		return inputXMLTime;
	}

	/**
	 * @param inputXMLTime the inputXMLTime to set
	 */
	public void setInputXMLTime(Date inputXMLTime) {
		this.inputXMLTime = inputXMLTime;
	}

	/**
	 * @return the inputXMLUrl
	 */
	public String getInputXMLUrl() {
		return inputXMLUrl;
	}

	/**
	 * @param inputXMLUrl the inputXMLUrl to set
	 */
	public void setInputXMLUrl(String inputXMLUrl) {
		this.inputXMLUrl = inputXMLUrl;
	}

	public Integer getInputXMLState() {
		return inputXMLState;
	}

	public void setInputXMLState(Integer inputXMLState) {
		this.inputXMLState = inputXMLState;
	}
	
	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * @return the entBaseInfo
	 */
	public EntBaseInfo getEntBaseInfo() {
		return entBaseInfo;
	}

	/**
	 * @param entBaseInfo the entBaseInfo to set
	 */
	public void setEntBaseInfo(EntBaseInfo entBaseInfo) {
		this.entBaseInfo = entBaseInfo;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
