package com.credit.bean.enterprise;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.credit.bean.member.Customer;
import com.credit.bean.transferBean.EnterpriseInfo;


@Entity
@Table(name = "TE_EntBaseInfo")
public class EntBaseInfo implements Serializable {
	private static final long serialVersionUID = -3446014372176930019L;

	@Id
	@Column(length = 32)
	private String uuid;// 主键
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date createTime;//创建日期
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date updateTime;// 更新日期 
	
	@Column(length = 300)
	private String name; // 企业名称
	
	@Column(length = 100)
	private String ename; // 企业英文名称

	@Temporal(TemporalType.DATE)
	@Column
	private Date setupDate; // 企业成立日期
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date issueDate; // 企业发照日期
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date startDate;// 营业开始日期
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date endDate;// 营业结束日期

	@Column(length = 500)
	private String address; // 企业地址
	
	@Column(length = 100)
	private String regiCapital; // 注册资本
	
	@Column(length = 20)
	private String currencyType; //币种

	@Column(length = 100)
	private String legalPerson; // 法定代表人
	
	@Column(length = 20)
	private String tel; // 电话
	
	@Column(length = 20)
	private String fax; // 传真
	
	@Column(length = 20)
	private String zipCode; //邮编
	
	@Column(length = 30)
	private String email;// 电子邮箱
	
	@Column(length = 20)
	private String website;//官网
	
	@Column(length = 20)
	private String scale; //人员规模
	
	@Column(length = 100)
	private String entType; //公司性质
	
	@Column(length = 100)
	private String regisOrg; //登记机关
	
	@Column(length = 100)
	private String industry; // 所属行业
	
	@Column(length = 20)
	private String industryCode; // 行业代码
	
	@Column(length = 20)
	private String areaCode; //地区编码
	
	@Column(length = 2000)
	private String businessScope; // 经营范围
	
	@Column(length = 1000)
	private String brief; //企业简介
	
	@Column(length = 30)
	private String USCC; // 统一社会信用代码
	
	@Column(columnDefinition="number(8) default 0",nullable = true)
	private Integer status = 0;// 经营状态，0默认，1开业，2存续，3内资存活，4注销
	
	@OneToOne(mappedBy = "entBaseInfo", cascade = CascadeType.REFRESH)
	private EntResult entResult;//评分结果表
	
	@OneToOne(mappedBy = "entBaseInfo", cascade = CascadeType.REFRESH)
	private ProcessState process;//评分流程表
	
	@OneToOne(mappedBy = "entBaseInfo", cascade = CascadeType.REFRESH)
	private Customer customer;//评分流程表
	
	@OneToMany(mappedBy="entBaseInfo",cascade=CascadeType.REMOVE,fetch = FetchType.LAZY)
	private Set<Opinion> opinion  = new HashSet<Opinion>();//意见表

	@OneToMany(mappedBy="entBaseInfo",cascade=CascadeType.REMOVE,fetch = FetchType.LAZY)
	private Set<HistoricalData> historicalData  = new HashSet<HistoricalData>();//历史数据表


	public EntBaseInfo() {
		super();
	}

	public EntBaseInfo(String uuid) {
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
		EntBaseInfo other = (EntBaseInfo) obj;
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
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ename
	 */
	public String getEname() {
		return ename;
	}

	/**
	 * @param ename the ename to set
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}

	/**
	 * @return the setupDate
	 */
	public Date getSetupDate() {
		return setupDate;
	}

	/**
	 * @param setupDate the setupDate to set
	 */
	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
	}

	/**
	 * @return the issueDate
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
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
	 * @return the currencyType
	 */
	public String getCurrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType the currencyType to set
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
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
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
	 * @return the industryCode
	 */
	public String getIndustryCode() {
		return industryCode;
	}

	/**
	 * @param industryCode the industryCode to set
	 */
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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
	 * @return the uSCC
	 */
	public String getUSCC() {
		return USCC;
	}

	/**
	 * @param uSCC the uSCC to set
	 */
	public void setUSCC(String uSCC) {
		USCC = uSCC;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the entResult
	 */
	public EntResult getEntResult() {
		return entResult;
	}

	/**
	 * @param entResult the entResult to set
	 */
	public void setEntResult(EntResult entResult) {
		this.entResult = entResult;
	}

	/**
	 * @return the process
	 */
	public ProcessState getProcess() {
		return process;
	}

	/**
	 * @param process the process to set
	 */
	public void setProcess(ProcessState process) {
		this.process = process;
	}

	/**
	 * @return the opinion
	 */
	public Set<Opinion> getOpinion() {
		return opinion;
	}

	/**
	 * @param opinion the opinion to set
	 */
	public void setOpinion(Set<Opinion> opinion) {
		this.opinion = opinion;
	}

	/**
	 * @return the historicalData
	 */
	public Set<HistoricalData> getHistoricalData() {
		return historicalData;
	}

	/**
	 * @param historicalData the historicalData to set
	 */
	public void setHistoricalData(Set<HistoricalData> historicalData) {
		this.historicalData = historicalData;
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

	/*导数据用，使用enterpriseinfo构造baseinfo*/
	public EntBaseInfo(EnterpriseInfo info){
		this.businessScope=info.getBusiness_scope();
		this.issueDate=info.getRegi_date();
		this.email=info.getEmail();
		this.USCC=info.getBusiness_no();
		this.entType=info.getCompanyCharacter();
		this.endDate=info.getEndBus_date();
		this.setupDate=info.getFound_date();
		this.industry=info.getIndustry();
		this.legalPerson=info.getLega_rep();
		this.name=info.getName();
		this.regisOrg=info.getRegistAuth();
		this.createTime=info.getRegi_date();
		this.address=info.getRegi_site();
		this.scale=info.getScale();
		this.currencyType="人民币";
		this.regiCapital=info.getRegi_capital();
		this.startDate=info.getStartBus_date();
		this.createTime=new Date();
		this.updateTime=new Date();
		this.uuid=info.getUuid().replace("-", "");
		this.areaCode=info.getAreaCode();
		this.tel=info.getTel();
		this.fax=info.getFax();
		this.zipCode=info.getPostalcode();
	}
}
