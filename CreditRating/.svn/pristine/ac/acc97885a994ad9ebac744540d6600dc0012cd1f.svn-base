package com.credit.bean.enterprise;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="TE_Process")
public class ProcessState implements Serializable{
	private static final long serialVersionUID = 7408534349870919430L;
	@Id
	@Column(length=32)
	private String uuid;//主键

	@Column(columnDefinition="number(8) default 0")
	private Integer applyReportState=0;//是否申请报告
	
	@Column(columnDefinition="number(8) default 0")
	private Integer scoreState=0;//评分状态  0无操作，1进行了调查，2进行编辑调查，3等待用户反馈，4进行评分，5管理评分报告
	
	@Column(columnDefinition="number(8) default 0")
	private Integer readState=0;//企业阅读协议
	
	@Column(columnDefinition="number(8) default 0")
	private Integer baseInfoState=0;//企业基本信息录入状态
	
	@Column(columnDefinition="number(8) default 0")
	private Integer shareholderState=0;//股东信息录入状态
	
	@Column(columnDefinition="number(8) default 0")
	private Integer executivesState=0;//高层信息录入状态
	
	@Column(columnDefinition="number(8) default 0")
	private Integer uploadFileState=0;//上传文件信息录入状态
	
	@Column(columnDefinition="number(8) default 0")
	private Integer financeState=0;//财务信息录入状态
	
	@Column(columnDefinition="number(8) default 0")
	private Integer pushModelState=0;//推送模型信息录入状态
	
	@OneToOne(cascade = CascadeType.REMOVE, optional = true)
	@JoinColumn(name = "EntID")
	private EntBaseInfo entBaseInfo;//企业外键
	
	public ProcessState() {
		super();
	}

	public ProcessState(String uuid) {
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
		ProcessState other = (ProcessState) obj;
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
	 * @return the applyReportState
	 */
	public Integer getApplyReportState() {
		return applyReportState;
	}

	/**
	 * @param applyReportState the applyReportState to set
	 */
	public void setApplyReportState(Integer applyReportState) {
		this.applyReportState = applyReportState;
	}

	/**
	 * @return the scoreState
	 */
	public Integer getScoreState() {
		return scoreState;
	}

	/**
	 * @param scoreState the scoreState to set
	 */
	public void setScoreState(Integer scoreState) {
		this.scoreState = scoreState;
	}

	/**
	 * @return the readState
	 */
	public Integer getReadState() {
		return readState;
	}

	/**
	 * @param readState the readState to set
	 */
	public void setReadState(Integer readState) {
		this.readState = readState;
	}

	/**
	 * @return the baseInfoState
	 */
	public Integer getBaseInfoState() {
		return baseInfoState;
	}

	/**
	 * @param baseInfoState the baseInfoState to set
	 */
	public void setBaseInfoState(Integer baseInfoState) {
		this.baseInfoState = baseInfoState;
	}

	/**
	 * @return the shareholderState
	 */
	public Integer getShareholderState() {
		return shareholderState;
	}

	/**
	 * @param shareholderState the shareholderState to set
	 */
	public void setShareholderState(Integer shareholderState) {
		this.shareholderState = shareholderState;
	}

	/**
	 * @return the executivesState
	 */
	public Integer getExecutivesState() {
		return executivesState;
	}

	/**
	 * @param executivesState the executivesState to set
	 */
	public void setExecutivesState(Integer executivesState) {
		this.executivesState = executivesState;
	}

	/**
	 * @return the uploadFileState
	 */
	public Integer getUploadFileState() {
		return uploadFileState;
	}

	/**
	 * @param uploadFileState the uploadFileState to set
	 */
	public void setUploadFileState(Integer uploadFileState) {
		this.uploadFileState = uploadFileState;
	}

	/**
	 * @return the financeState
	 */
	public Integer getFinanceState() {
		return financeState;
	}

	/**
	 * @param financeState the financeState to set
	 */
	public void setFinanceState(Integer financeState) {
		this.financeState = financeState;
	}

	/**
	 * @return the pushModelState
	 */
	public Integer getPushModelState() {
		return pushModelState;
	}

	/**
	 * @param pushModelState the pushModelState to set
	 */
	public void setPushModelState(Integer pushModelState) {
		this.pushModelState = pushModelState;
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


	
	
}
