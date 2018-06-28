package com.credit.bean.person;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="TI_Process")
public class PerProcess implements Serializable{

	private static final long serialVersionUID = -7401340443391014044L;

	@Id
	@Column(length=32)
	private String uuid;//主键

	@Column(columnDefinition="number(8) default 0")
	private Integer applyReportState=0;//是否申请报告
	
	@Column(columnDefinition="number(8) default 0")
	private Integer scoreState=0;//评分状态  0无操作，1进行了调查，2进行编辑调查，3等待用户反馈，4进行评分，5管理评分报告
	
	@Column(columnDefinition="number(8) default 0")
	private Integer readState=0;//阅读协议
	
	@Column(columnDefinition="number(8) default 0")
	private Integer baseInfoState=0;//基本信息录入状态
	
	@Column(columnDefinition="number(8) default 0")
	private Integer educationState=0;//教育信息录入状态
	
	@Column(columnDefinition="number(8) default 0")
	private Integer trainState=0;//培训经历录入状态
	
	@Column(columnDefinition="number(8) default 0")
	private Integer careerState=0;//职业生涯录入状态
	
	@Column(columnDefinition="number(8) default 0")
	private Integer skillState=0;//专业技能录入状态
	
	@Column(columnDefinition="number(8) default 0")
	private Integer uploadFileState=0;//上传文件信息录入状态
	
	@Column(columnDefinition="number(8) default 0")
	private Integer pushModelState=0;//推送模型信息录入状态
	
	@OneToOne(cascade = CascadeType.REMOVE, optional = true)
	@JoinColumn(name = "PerID")
	private PerBaseInfo perBaseInfo;//个人信息外键
	
	public PerProcess() {
		super();
	}

	public PerProcess(String uuid) {
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
		PerProcess other = (PerProcess) obj;
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

	public Integer getApplyReportState() {
		return applyReportState;
	}

	public void setApplyReportState(Integer applyReportState) {
		this.applyReportState = applyReportState;
	}

	public Integer getScoreState() {
		return scoreState;
	}

	public void setScoreState(Integer scoreState) {
		this.scoreState = scoreState;
	}

	public Integer getReadState() {
		return readState;
	}

	public void setReadState(Integer readState) {
		this.readState = readState;
	}

	public Integer getBaseInfoState() {
		return baseInfoState;
	}

	public void setBaseInfoState(Integer baseInfoState) {
		this.baseInfoState = baseInfoState;
	}

	public Integer getEducationState() {
		return educationState;
	}

	public void setEducationState(Integer educationState) {
		this.educationState = educationState;
	}

	public Integer getTrainState() {
		return trainState;
	}

	public void setTrainState(Integer trainState) {
		this.trainState = trainState;
	}

	public Integer getCareerState() {
		return careerState;
	}

	public void setCareerState(Integer careerState) {
		this.careerState = careerState;
	}

	public Integer getSkillState() {
		return skillState;
	}

	public void setSkillState(Integer skillState) {
		this.skillState = skillState;
	}

	public Integer getUploadFileState() {
		return uploadFileState;
	}

	public void setUploadFileState(Integer uploadFileState) {
		this.uploadFileState = uploadFileState;
	}

	public Integer getPushModelState() {
		return pushModelState;
	}

	public void setPushModelState(Integer pushModelState) {
		this.pushModelState = pushModelState;
	}

	public PerBaseInfo getPerBaseInfo() {
		return perBaseInfo;
	}

	public void setPerBaseInfo(PerBaseInfo perBaseInfo) {
		this.perBaseInfo = perBaseInfo;
	}

}
