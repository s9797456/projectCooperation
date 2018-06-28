package com.credit.bean.vo.html2pdf;


import java.util.List;

import com.credit.util.model.IndexRateVo;

import lombok.Data;


@Data
public class PerReport {

    private String name;//委托对象
    private String birthday;//出生日期
    private String phone;//联络电话
    private String email;//电子邮箱
    private String qrcode;//报告二维码
    private String gradeTime;//报告时间
    private String valueTime;//有效期至
    private List<IndexRateVo> indexRates;//指标得分比重
    private String finalScore;//终评等级
    private String scoreSummary;//评分总结
    
    private String IDCard;//身份证号
	private String usedName; // 曾用名
	private String gender; // 性别
	private String nation; // 民族
	private String politicalOutlook; // 政治面貌
	private String nationality; // 国籍
	private String nativePlace; // 籍贯
	private String maritalStatus; //婚姻状况
	private String fertilityCondition; // 生育情况
	private String permanentAddress; // 户籍地址
	private String presentAddress; //现居住地址
	private String IDIssuingAgency; //身份证签发机关
	private String IDTermStart;//身份证有效期开始
	private String IDTermEnd;//身份证有效期结束
	private String presentZipCode; //现居住地邮政编码
	
	private String educationScore;//学历评分
	private String educationRemarks;//学历备注
	private String universityScore;//毕业院校评分
	private String universityRemarks;//毕业院校备注
	private String majorScore;//专业评分
	private String majorRemarks;//专业备注
	private String diplomaNoScore;//证书编号评分
	private String diplomaNoRemarks;//证书编号备注
	
	private String image1;//毕业院校、供职单位、供职行业匹配度指数
	
	private String averageLife;//近两次工作平均年限
	private String longestLife;//最长工作年限
	
	private List<CareerChange> careerChange1;//就职单位性质变化
	private List<CareerChange> careerChange2;//就职单位规模变化
	private List<CareerChange> careerChange3;//行业变动
	
	private String allLife1;//总工作年限--委托对象
	private String allLife2;//总工作年限--同行业
	private String allLife3;//总工作年限--同岗位
	private String averageLife1;//单位平均工作年限--委托对象
	private String averageLife2;//单位平均工作年限--同行业
	private String averageLife3;//单位平均工作年限--同岗位
	private String sameLife1;//同行业工作年限--委托对象
	private String sameLife2;//同行业工作年限--同行业
	private String sameLife3;//同行业工作年限--同岗位
	
	private String image2;//工作年限风险提示

	private String trainScore;//是否正在或有自身素质提高的意愿评分
	private String trainRemarks;//是否正在或有自身素质提高的意愿备注
	private String trainNoScore;//再教育或培训证书编号评分
	private String trainNoRemarks;//再教育或培训证书编号备注
	
	private List<TrainVo> trains;//培训经历
	
	private String postScore;//职务评分
	private String postRemarks;//职务备注
	private String qualificationsScore;//职业资质评分
	private String qualificationsRemarks;//职业资质备注
	private String unitPropertiesScore;//单位性质评分
	private String unitPropertiesRemarks;//单位性质备注
	private String scaleScore;//单位规模评分
	private String scaleRemarks;//单位规模备注
	
	private String skillsScore;//专业知识技能评分
	private String skillsRemarks;//专业知识技能备注
	private String skillsTrainScore;//专业技能培训评分
	private String skillsTrainRemarks;//专业技能培训备注
	private String allLifeScore;//总工作年限评分
	private String allLifeRemarks;//总工作年限备注
	private String sameLifeScore;//同行业工作年限评分
	private String sameLifeRemarks;//同行业工作年限备注
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}
	public String getScoreSummary() {
		return scoreSummary;
	}
	public void setScoreSummary(String scoreSummary) {
		this.scoreSummary = scoreSummary;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getUsedName() {
		return usedName;
	}
	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPoliticalOutlook() {
		return politicalOutlook;
	}
	public void setPoliticalOutlook(String politicalOutlook) {
		this.politicalOutlook = politicalOutlook;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getFertilityCondition() {
		return fertilityCondition;
	}
	public void setFertilityCondition(String fertilityCondition) {
		this.fertilityCondition = fertilityCondition;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getPresentAddress() {
		return presentAddress;
	}
	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}
	public String getIDIssuingAgency() {
		return IDIssuingAgency;
	}
	public void setIDIssuingAgency(String iDIssuingAgency) {
		IDIssuingAgency = iDIssuingAgency;
	}
	public String getIDTermStart() {
		return IDTermStart;
	}
	public void setIDTermStart(String iDTermStart) {
		IDTermStart = iDTermStart;
	}
	public String getIDTermEnd() {
		return IDTermEnd;
	}
	public void setIDTermEnd(String iDTermEnd) {
		IDTermEnd = iDTermEnd;
	}
	public String getPresentZipCode() {
		return presentZipCode;
	}
	public void setPresentZipCode(String presentZipCode) {
		this.presentZipCode = presentZipCode;
	}
	public String getEducationScore() {
		return educationScore;
	}
	public void setEducationScore(String educationScore) {
		this.educationScore = educationScore;
	}
	public String getEducationRemarks() {
		return educationRemarks;
	}
	public void setEducationRemarks(String educationRemarks) {
		this.educationRemarks = educationRemarks;
	}
	public String getUniversityScore() {
		return universityScore;
	}
	public void setUniversityScore(String universityScore) {
		this.universityScore = universityScore;
	}
	public String getUniversityRemarks() {
		return universityRemarks;
	}
	public void setUniversityRemarks(String universityRemarks) {
		this.universityRemarks = universityRemarks;
	}
	public String getMajorScore() {
		return majorScore;
	}
	public void setMajorScore(String majorScore) {
		this.majorScore = majorScore;
	}
	public String getMajorRemarks() {
		return majorRemarks;
	}
	public void setMajorRemarks(String majorRemarks) {
		this.majorRemarks = majorRemarks;
	}
	public String getDiplomaNoScore() {
		return diplomaNoScore;
	}
	public void setDiplomaNoScore(String diplomaNoScore) {
		this.diplomaNoScore = diplomaNoScore;
	}
	public String getDiplomaNoRemarks() {
		return diplomaNoRemarks;
	}
	public void setDiplomaNoRemarks(String diplomaNoRemarks) {
		this.diplomaNoRemarks = diplomaNoRemarks;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getAverageLife() {
		return averageLife;
	}
	public void setAverageLife(String averageLife) {
		this.averageLife = averageLife;
	}
	public String getLongestLife() {
		return longestLife;
	}
	public void setLongestLife(String longestLife) {
		this.longestLife = longestLife;
	}
	public List<CareerChange> getCareerChange1() {
		return careerChange1;
	}
	public void setCareerChange1(List<CareerChange> careerChange1) {
		this.careerChange1 = careerChange1;
	}
	public List<CareerChange> getCareerChange2() {
		return careerChange2;
	}
	public void setCareerChange2(List<CareerChange> careerChange2) {
		this.careerChange2 = careerChange2;
	}
	public List<CareerChange> getCareerChange3() {
		return careerChange3;
	}
	public void setCareerChange3(List<CareerChange> careerChange3) {
		this.careerChange3 = careerChange3;
	}
	public String getAllLife1() {
		return allLife1;
	}
	public void setAllLife1(String allLife1) {
		this.allLife1 = allLife1;
	}
	public String getAllLife2() {
		return allLife2;
	}
	public void setAllLife2(String allLife2) {
		this.allLife2 = allLife2;
	}
	public String getAllLife3() {
		return allLife3;
	}
	public void setAllLife3(String allLife3) {
		this.allLife3 = allLife3;
	}
	public String getAverageLife1() {
		return averageLife1;
	}
	public void setAverageLife1(String averageLife1) {
		this.averageLife1 = averageLife1;
	}
	public String getAverageLife2() {
		return averageLife2;
	}
	public void setAverageLife2(String averageLife2) {
		this.averageLife2 = averageLife2;
	}
	public String getAverageLife3() {
		return averageLife3;
	}
	public void setAverageLife3(String averageLife3) {
		this.averageLife3 = averageLife3;
	}
	public String getSameLife1() {
		return sameLife1;
	}
	public void setSameLife1(String sameLife1) {
		this.sameLife1 = sameLife1;
	}
	public String getSameLife2() {
		return sameLife2;
	}
	public void setSameLife2(String sameLife2) {
		this.sameLife2 = sameLife2;
	}
	public String getSameLife3() {
		return sameLife3;
	}
	public void setSameLife3(String sameLife3) {
		this.sameLife3 = sameLife3;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getTrainScore() {
		return trainScore;
	}
	public void setTrainScore(String trainScore) {
		this.trainScore = trainScore;
	}
	public String getTrainRemarks() {
		return trainRemarks;
	}
	public void setTrainRemarks(String trainRemarks) {
		this.trainRemarks = trainRemarks;
	}
	public String getTrainNoScore() {
		return trainNoScore;
	}
	public void setTrainNoScore(String trainNoScore) {
		this.trainNoScore = trainNoScore;
	}
	public String getTrainNoRemarks() {
		return trainNoRemarks;
	}
	public void setTrainNoRemarks(String trainNoRemarks) {
		this.trainNoRemarks = trainNoRemarks;
	}
	public List<TrainVo> getTrains() {
		return trains;
	}
	public void setTrains(List<TrainVo> trains) {
		this.trains = trains;
	}
	public String getPostScore() {
		return postScore;
	}
	public void setPostScore(String postScore) {
		this.postScore = postScore;
	}
	public String getPostRemarks() {
		return postRemarks;
	}
	public void setPostRemarks(String postRemarks) {
		this.postRemarks = postRemarks;
	}
	public String getQualificationsScore() {
		return qualificationsScore;
	}
	public void setQualificationsScore(String qualificationsScore) {
		this.qualificationsScore = qualificationsScore;
	}
	public String getQualificationsRemarks() {
		return qualificationsRemarks;
	}
	public void setQualificationsRemarks(String qualificationsRemarks) {
		this.qualificationsRemarks = qualificationsRemarks;
	}
	public String getUnitPropertiesScore() {
		return unitPropertiesScore;
	}
	public void setUnitPropertiesScore(String unitPropertiesScore) {
		this.unitPropertiesScore = unitPropertiesScore;
	}
	public String getUnitPropertiesRemarks() {
		return unitPropertiesRemarks;
	}
	public void setUnitPropertiesRemarks(String unitPropertiesRemarks) {
		this.unitPropertiesRemarks = unitPropertiesRemarks;
	}
	public String getScaleScore() {
		return scaleScore;
	}
	public void setScaleScore(String scaleScore) {
		this.scaleScore = scaleScore;
	}
	public String getScaleRemarks() {
		return scaleRemarks;
	}
	public void setScaleRemarks(String scaleRemarks) {
		this.scaleRemarks = scaleRemarks;
	}
	public String getSkillsScore() {
		return skillsScore;
	}
	public void setSkillsScore(String skillsScore) {
		this.skillsScore = skillsScore;
	}
	public String getSkillsRemarks() {
		return skillsRemarks;
	}
	public void setSkillsRemarks(String skillsRemarks) {
		this.skillsRemarks = skillsRemarks;
	}
	public String getSkillsTrainScore() {
		return skillsTrainScore;
	}
	public void setSkillsTrainScore(String skillsTrainScore) {
		this.skillsTrainScore = skillsTrainScore;
	}
	public String getSkillsTrainRemarks() {
		return skillsTrainRemarks;
	}
	public void setSkillsTrainRemarks(String skillsTrainRemarks) {
		this.skillsTrainRemarks = skillsTrainRemarks;
	}
	public String getAllLifeScore() {
		return allLifeScore;
	}
	public void setAllLifeScore(String allLifeScore) {
		this.allLifeScore = allLifeScore;
	}
	public String getAllLifeRemarks() {
		return allLifeRemarks;
	}
	public void setAllLifeRemarks(String allLifeRemarks) {
		this.allLifeRemarks = allLifeRemarks;
	}
	public String getSameLifeScore() {
		return sameLifeScore;
	}
	public void setSameLifeScore(String sameLifeScore) {
		this.sameLifeScore = sameLifeScore;
	}
	public String getSameLifeRemarks() {
		return sameLifeRemarks;
	}
	public void setSameLifeRemarks(String sameLifeRemarks) {
		this.sameLifeRemarks = sameLifeRemarks;
	}
	
}
