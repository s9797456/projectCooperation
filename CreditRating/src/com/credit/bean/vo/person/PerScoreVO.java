package com.credit.bean.vo.person;

public class PerScoreVO {
	
	public String perID;//个人ID
	public String modelID;//模型ID
	public String processID;//流程表ID
	public String resultID;//评分结果表ID
	public String userName;//客户主键
	
	public String name;//姓名
	public String idcard;//身份证号
	public String updateTime;//企业信息更新时间
	
	public String modelName;//模型名称
	
	public int applyReportState;//是否申请报告
	public int scoreState;//评分状态
	public String scoreDesc;//评分状态说明
	
	public String cellphone;//客户电话;
	public String state;//流程状态
	
	public Integer visible;//帐号状态

	public PerScoreVO() {
		super();
	}

	public String getPerID() {
		return perID;
	}

	public void setPerID(String perID) {
		this.perID = perID;
	}

	public String getModelID() {
		return modelID;
	}

	public void setModelID(String modelID) {
		this.modelID = modelID;
	}

	public String getProcessID() {
		return processID;
	}

	public void setProcessID(String processID) {
		this.processID = processID;
	}

	public String getResultID() {
		return resultID;
	}

	public void setResultID(String resultID) {
		this.resultID = resultID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getApplyReportState() {
		return applyReportState;
	}

	public void setApplyReportState(int applyReportState) {
		this.applyReportState = applyReportState;
	}

	public int getScoreState() {
		return scoreState;
	}

	public void setScoreState(int scoreState) {
		this.scoreState = scoreState;
	}

	public String getScoreDesc() {
		return scoreDesc;
	}

	public void setScoreDesc(String scoreDesc) {
		this.scoreDesc = scoreDesc;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}


}
