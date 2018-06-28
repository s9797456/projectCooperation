package com.credit.bean.transferBean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class EnterpriseInfo {
	private String uuid;
	private String name; // 企业名称
	private Date createScoreDate; // 授信报告调查日期
	private Date regi_date; // 企业注册日期
	private String regi_site; // 注册地址
	private String regi_capital; // 注册资本
	private String paid_capital; // 实收资本
	private String deal_site; // 实际经营地址
	private String lega_rep; // 法定代表人
	private String tel; // 电话
	private String fax; // 传真
	private String scale; // 规模
	private String business_no; // 营业执照号码
	private String orga_code_cert; // 法人代码证号
	private String basic_account_bank; // 基本账户开户行
	private String load_card_no; // 贷款卡号码
	private String tax_regi_no; // 税务登记证号
	private String postalcode;// 邮政编码
	private Date found_date; // 成立时间
	private String prim_business; // 主营业务
	private String prod_capacity; // 生产能力/服务能力
	private String companyCharacter; // 公司性质
	private String industry; // 所属行业
	private String qual_cert; // 行业资质等级
	private String belong; // 主管部门或隶属于
	private String regi_capi_arrive; // 注册资本到位率
	private String net_asset; // 净资产
	private String total_income; // 总收入
	private Date pre_rate_date; // datetime前次评级时间
	private Date pre_rate_vtime; // datetime前次评级有效期
	private String pre_rate_grade; // 前次审定等级
	private String approved_credit; // 已核定授信额度
	private String consume_credit; // 已支用额度
	private String business_scope; // 经营范围
	private String business_place; // 经营场所简介
	private String deve_history; // 发展历史
	private String mana_analysis; // 客户经营情况分析
	private String quali_asse; // 定性指标评价
	private String net_asset_ratio; // 净资产收益率（％）
	private String net_profit_ratio; // 资产净利率(%)
	private String total_asset_ratio; // 总资产报酬率（％）
	private String sale_ratio; // 销售（营业）利润率（％）
	private String profit_grow_ratio; // 利润增长率（％）
	private String tasset_grow_ratio; // 总资产增长率(%)
	private String sale_grow_ratio; // 销售（营业）增长率（％）
	private String capital_accu_ratio; // 资本积累率（％）
	private String raccount_turn_ratio; // 应收账款周转率（％）
	private String raccount_grow_ratio;// 应收账款增长率（％）
	private String inventory_turn_ratio; // 存货周转率（％）
	private String quick_ratio; // 速动比率
	private String tinterest_earn_ratio; // 利息保障倍数
	private String oper_cash_ratio; // 经营现金流动负债比率（％）
	private String debt_asset_ratio; // 资产负债率（％）
	private String bank_debt_ratio;// 银行债务覆盖比(%)
	private String long_asset_ratio; // 长期资产适合率（％）
	private String cont_liaby_ratio; // 或有负债率（％）
	private String bussiness_income; // 营业收入
	private String total_asset; // 总资产
	private String quant_risk_assess; // 定量风险评价意见
	private String credit_record_anal; // 信用记录分析
	private String other_matter_explain; // 其他需要说明的事项
	private String email;// 电子邮箱
	private String url;// 网址
	private String confirmstatus;
	// 提交额外信息数据
	private String extraIndexStatus;
	// 是否阅读协议
	private String readStatus;
	// 是否申请报告
	private String applyReportStatus;
	private Date startBus_date;// 营业开始日期
	private Date endBus_date;// 营业结束日期
	private String org_no;// 机构代码
	private String registAuth;// 登记机关
	private String companyProfile;// 公司概况
	private String adjustReason;// 调整说明
	private String advantageReason;// 优势说明
	private String financialContent;// 财务报表的内容
	// 标识属性对应的字段
	
	private String encoding; // 评级编码
	private String areaCode; // 地区编码
	private String comCode; // 公司性质编码
	
	
	private String model;
	
	/*外键保存在User表*/
	//private User user;
	
	public EnterpriseInfo(){}
	

	
	/**
	 * @return the name
	 */
	@XmlElement
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
	 * @return the createScoreDate
	 */
	@XmlElement
	public Date getCreateScoreDate() {
		return createScoreDate;
	}
	/**
	 * @param createScoreDate the createScoreDate to set
	 */
	public void setCreateScoreDate(Date createScoreDate) {
		this.createScoreDate = createScoreDate;
	}
	/**
	 * @return the regi_date
	 */
	@XmlElement
	public Date getRegi_date() {
		return regi_date;
	}
	/**
	 * @param regi_date the regi_date to set
	 */
	public void setRegi_date(Date regi_date) {
		this.regi_date = regi_date;
	}
	/**
	 * @return the regi_site
	 */
	@XmlElement
	public String getRegi_site() {
		return regi_site;
	}
	/**
	 * @param regi_site the regi_site to set
	 */
	public void setRegi_site(String regi_site) {
		this.regi_site = regi_site;
	}
	/**
	 * @return the regi_capital
	 */
	@XmlElement
	public String getRegi_capital() {
		return regi_capital;
	}
	/**
	 * @param regi_capital the regi_capital to set
	 */
	public void setRegi_capital(String regi_capital) {
		this.regi_capital = regi_capital;
	}
	/**
	 * @return the paid_capital
	 */
	@XmlElement
	public String getPaid_capital() {
		return paid_capital;
	}
	/**
	 * @param paid_capital the paid_capital to set
	 */
	public void setPaid_capital(String paid_capital) {
		this.paid_capital = paid_capital;
	}
	/**
	 * @return the deal_site
	 */
	@XmlElement
	public String getDeal_site() {
		return deal_site;
	}
	/**
	 * @param deal_site the deal_site to set
	 */
	public void setDeal_site(String deal_site) {
		this.deal_site = deal_site;
	}
	/**
	 * @return the lega_rep
	 */
	@XmlElement
	public String getLega_rep() {
		return lega_rep;
	}
	/**
	 * @param lega_rep the lega_rep to set
	 */
	public void setLega_rep(String lega_rep) {
		this.lega_rep = lega_rep;
	}
	/**
	 * @return the tel
	 */
	@XmlElement
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
	@XmlElement
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
	 * @return the scale
	 */
	@XmlElement
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
	 * @return the business_no
	 */
	@XmlElement
	public String getBusiness_no() {
		return business_no;
	}
	/**
	 * @param business_no the business_no to set
	 */
	public void setBusiness_no(String business_no) {
		this.business_no = business_no;
	}
	/**
	 * @return the orga_code_cert
	 */
	@XmlElement
	public String getOrga_code_cert() {
		return orga_code_cert;
	}
	/**
	 * @param orga_code_cert the orga_code_cert to set
	 */
	public void setOrga_code_cert(String orga_code_cert) {
		this.orga_code_cert = orga_code_cert;
	}
	/**
	 * @return the basic_account_bank
	 */
	@XmlElement
	public String getBasic_account_bank() {
		return basic_account_bank;
	}
	/**
	 * @param basic_account_bank the basic_account_bank to set
	 */
	public void setBasic_account_bank(String basic_account_bank) {
		this.basic_account_bank = basic_account_bank;
	}
	/**
	 * @return the load_card_no
	 */
	@XmlElement
	public String getLoad_card_no() {
		return load_card_no;
	}
	/**
	 * @param load_card_no the load_card_no to set
	 */
	public void setLoad_card_no(String load_card_no) {
		this.load_card_no = load_card_no;
	}
	/**
	 * @return the tax_regi_no
	 */
	@XmlElement
	public String getTax_regi_no() {
		return tax_regi_no;
	}
	/**
	 * @param tax_regi_no the tax_regi_no to set
	 */
	
	public void setTax_regi_no(String tax_regi_no) {
		this.tax_regi_no = tax_regi_no;
	}
	/**
	 * @return the postalcode
	 */
	@XmlElement
	public String getPostalcode() {
		return postalcode;
	}
	/**
	 * @param postalcode the postalcode to set
	 */
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	/**
	 * @return the found_date
	 */
	@XmlElement
	public Date getFound_date() {
		return found_date;
	}
	/**
	 * @param found_date the found_date to set
	 */
	public void setFound_date(Date found_date) {
		this.found_date = found_date;
	}
	/**
	 * @return the prim_business
	 */
	@XmlElement
	public String getPrim_business() {
		return prim_business;
	}
	/**
	 * @param prim_business the prim_business to set
	 */
	public void setPrim_business(String prim_business) {
		this.prim_business = prim_business;
	}
	/**
	 * @return the prod_capacity
	 */
	@XmlElement
	public String getProd_capacity() {
		return prod_capacity;
	}
	/**
	 * @param prod_capacity the prod_capacity to set
	 */
	public void setProd_capacity(String prod_capacity) {
		this.prod_capacity = prod_capacity;
	}
	/**
	 * @return the companyCharacter
	 */
	@XmlElement
	public String getCompanyCharacter() {
		return companyCharacter;
	}
	/**
	 * @param companyCharacter the companyCharacter to set
	 */
	public void setCompanyCharacter(String companyCharacter) {
		this.companyCharacter = companyCharacter;
	}
	/**
	 * @return the industry
	 */
	@XmlElement
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
	 * @return the qual_cert
	 */
	@XmlElement
	public String getQual_cert() {
		return qual_cert;
	}
	/**
	 * @param qual_cert the qual_cert to set
	 */
	public void setQual_cert(String qual_cert) {
		this.qual_cert = qual_cert;
	}
	/**
	 * @return the belong
	 */
	@XmlElement
	public String getBelong() {
		return belong;
	}
	/**
	 * @param belong the belong to set
	 */
	public void setBelong(String belong) {
		this.belong = belong;
	}

	@XmlElement
	public String getRegi_capi_arrive() {
		return regi_capi_arrive;
	}

	/**
	 * @param regi_capi_arrive the regi_capi_arrive to set
	 */
	public void setRegi_capi_arrive(String regi_capi_arrive) {
		this.regi_capi_arrive = regi_capi_arrive;
	}

	@XmlElement
	public String getNet_asset() {
		return net_asset;
	}

	/**
	 * @param net_asset the net_asset to set
	 */
	public void setNet_asset(String net_asset) {
		this.net_asset = net_asset;
	}

	@XmlElement
	public String getTotal_income() {
		return total_income;
	}

	/**
	 * @param total_income the total_income to set
	 */
	public void setTotal_income(String total_income) {
		this.total_income = total_income;
	}

	@XmlElement
	public Date getPre_rate_date() {
		return pre_rate_date;
	}

	/**
	 * @param pre_rate_date the pre_rate_date to set
	 */
	public void setPre_rate_date(Date pre_rate_date) {
		this.pre_rate_date = pre_rate_date;
	}

	@XmlElement
	public Date getPre_rate_vtime() {
		return pre_rate_vtime;
	}

	/**
	 * @param pre_rate_vtime the pre_rate_vtime to set
	 */
	public void setPre_rate_vtime(Date pre_rate_vtime) {
		this.pre_rate_vtime = pre_rate_vtime;
	}

	@XmlElement
	public String getPre_rate_grade() {
		return pre_rate_grade;
	}

	/**
	 * @param pre_rate_grade the pre_rate_grade to set
	 */
	public void setPre_rate_grade(String pre_rate_grade) {
		this.pre_rate_grade = pre_rate_grade;
	}

	@XmlElement
	public String getApproved_credit() {
		return approved_credit;
	}

	/**
	 * @param approved_credit the approved_credit to set
	 */
	public void setApproved_credit(String approved_credit) {
		this.approved_credit = approved_credit;
	}

	@XmlElement
	public String getConsume_credit() {
		return consume_credit;
	}

	/**
	 * @param consume_credit the consume_credit to set
	 */
	public void setConsume_credit(String consume_credit) {
		this.consume_credit = consume_credit;
	}

	@XmlElement
	public String getBusiness_scope() {
		return business_scope;
	}

	/**
	 * @param business_scope the business_scope to set
	 */
	public void setBusiness_scope(String business_scope) {
		this.business_scope = business_scope;
	}

	@XmlElement
	public String getBusiness_place() {
		return business_place;
	}

	/**
	 * @param business_place the business_place to set
	 */
	public void setBusiness_place(String business_place) {
		this.business_place = business_place;
	}

	@XmlElement
	public String getDeve_history() {
		return deve_history;
	}

	/**
	 * @param deve_history the deve_history to set
	 */
	public void setDeve_history(String deve_history) {
		this.deve_history = deve_history;
	}

	@XmlElement
	public String getMana_analysis() {
		return mana_analysis;
	}

	/**
	 * @param mana_analysis the mana_analysis to set
	 */
	public void setMana_analysis(String mana_analysis) {
		this.mana_analysis = mana_analysis;
	}

	@XmlElement
	public String getQuali_asse() {
		return quali_asse;
	}

	/**
	 * @param quali_asse the quali_asse to set
	 */
	public void setQuali_asse(String quali_asse) {
		this.quali_asse = quali_asse;
	}

	@XmlElement
	public String getNet_asset_ratio() {
		return net_asset_ratio;
	}

	/**
	 * @param net_asset_ratio the net_asset_ratio to set
	 */
	public void setNet_asset_ratio(String net_asset_ratio) {
		this.net_asset_ratio = net_asset_ratio;
	}

	@XmlElement
	public String getNet_profit_ratio() {
		return net_profit_ratio;
	}

	/**
	 * @param net_profit_ratio the net_profit_ratio to set
	 */
	public void setNet_profit_ratio(String net_profit_ratio) {
		this.net_profit_ratio = net_profit_ratio;
	}

	@XmlElement
	public String getTotal_asset_ratio() {
		return total_asset_ratio;
	}

	/**
	 * @param total_asset_ratio the total_asset_ratio to set
	 */
	public void setTotal_asset_ratio(String total_asset_ratio) {
		this.total_asset_ratio = total_asset_ratio;
	}

	@XmlElement
	public String getSale_ratio() {
		return sale_ratio;
	}

	/**
	 * @param sale_ratio the sale_ratio to set
	 */
	public void setSale_ratio(String sale_ratio) {
		this.sale_ratio = sale_ratio;
	}

	@XmlElement
	public String getProfit_grow_ratio() {
		return profit_grow_ratio;
	}

	/**
	 * @param profit_grow_ratio the profit_grow_ratio to set
	 */
	public void setProfit_grow_ratio(String profit_grow_ratio) {
		this.profit_grow_ratio = profit_grow_ratio;
	}
	@XmlElement
	public String getTasset_grow_ratio() {
		return tasset_grow_ratio;
	}

	/**
	 * @param tasset_grow_ratio the tasset_grow_ratio to set
	 */
	public void setTasset_grow_ratio(String tasset_grow_ratio) {
		this.tasset_grow_ratio = tasset_grow_ratio;
	}

	@XmlElement
	public String getSale_grow_ratio() {
		return sale_grow_ratio;
	}

	/**
	 * @param sale_grow_ratio the sale_grow_ratio to set
	 */
	public void setSale_grow_ratio(String sale_grow_ratio) {
		this.sale_grow_ratio = sale_grow_ratio;
	}

	@XmlElement
	public String getCapital_accu_ratio() {
		return capital_accu_ratio;
	}

	/**
	 * @param capital_accu_ratio the capital_accu_ratio to set
	 */
	public void setCapital_accu_ratio(String capital_accu_ratio) {
		this.capital_accu_ratio = capital_accu_ratio;
	}

	@XmlElement
	public String getRaccount_turn_ratio() {
		return raccount_turn_ratio;
	}

	/**
	 * @param raccount_turn_ratio the raccount_turn_ratio to set
	 */
	public void setRaccount_turn_ratio(String raccount_turn_ratio) {
		this.raccount_turn_ratio = raccount_turn_ratio;
	}

	@XmlElement
	public String getRaccount_grow_ratio() {
		return raccount_grow_ratio;
	}

	/**
	 * @param raccount_grow_ratio the raccount_grow_ratio to set
	 */
	public void setRaccount_grow_ratio(String raccount_grow_ratio) {
		this.raccount_grow_ratio = raccount_grow_ratio;
	}

	@XmlElement
	public String getInventory_turn_ratio() {
		return inventory_turn_ratio;
	}

	/**
	 * @param inventory_turn_ratio the inventory_turn_ratio to set
	 */
	public void setInventory_turn_ratio(String inventory_turn_ratio) {
		this.inventory_turn_ratio = inventory_turn_ratio;
	}

	@XmlElement
	public String getQuick_ratio() {
		return quick_ratio;
	}

	/**
	 * @param quick_ratio the quick_ratio to set
	 */
	public void setQuick_ratio(String quick_ratio) {
		this.quick_ratio = quick_ratio;
	}

	@XmlElement
	public String getTinterest_earn_ratio() {
		return tinterest_earn_ratio;
	}

	/**
	 * @param tinterest_earn_ratio the tinterest_earn_ratio to set
	 */
	public void setTinterest_earn_ratio(String tinterest_earn_ratio) {
		this.tinterest_earn_ratio = tinterest_earn_ratio;
	}

	@XmlElement
	public String getOper_cash_ratio() {
		return oper_cash_ratio;
	}

	/**
	 * @param oper_cash_ratio the oper_cash_ratio to set
	 */
	public void setOper_cash_ratio(String oper_cash_ratio) {
		this.oper_cash_ratio = oper_cash_ratio;
	}

	@XmlElement
	public String getDebt_asset_ratio() {
		return debt_asset_ratio;
	}

	/**
	 * @param debt_asset_ratio the debt_asset_ratio to set
	 */
	public void setDebt_asset_ratio(String debt_asset_ratio) {
		this.debt_asset_ratio = debt_asset_ratio;
	}

	@XmlElement
	public String getBank_debt_ratio() {
		return bank_debt_ratio;
	}

	/**
	 * @param bank_debt_ratio the bank_debt_ratio to set
	 */
	public void setBank_debt_ratio(String bank_debt_ratio) {
		this.bank_debt_ratio = bank_debt_ratio;
	}

	@XmlElement
	public String getLong_asset_ratio() {
		return long_asset_ratio;
	}

	/**
	 * @param long_asset_ratio the long_asset_ratio to set
	 */
	public void setLong_asset_ratio(String long_asset_ratio) {
		this.long_asset_ratio = long_asset_ratio;
	}

	@XmlElement
	public String getCont_liaby_ratio() {
		return cont_liaby_ratio;
	}

	/**
	 * @param cont_liaby_ratio the cont_liaby_ratio to set
	 */
	public void setCont_liaby_ratio(String cont_liaby_ratio) {
		this.cont_liaby_ratio = cont_liaby_ratio;
	}

	@XmlElement
	public String getBussiness_income() {
		return bussiness_income;
	}

	/**
	 * @param bussiness_income the bussiness_income to set
	 */
	public void setBussiness_income(String bussiness_income) {
		this.bussiness_income = bussiness_income;
	}

	@XmlElement
	public String getTotal_asset() {
		return total_asset;
	}

	/**
	 * @param total_asset the total_asset to set
	 */
	public void setTotal_asset(String total_asset) {
		this.total_asset = total_asset;
	}

	@XmlElement
	public String getQuant_risk_assess() {
		return quant_risk_assess;
	}

	/**
	 * @param quant_risk_assess the quant_risk_assess to set
	 */
	public void setQuant_risk_assess(String quant_risk_assess) {
		this.quant_risk_assess = quant_risk_assess;
	}

	@XmlElement
	public String getCredit_record_anal() {
		return credit_record_anal;
	}

	/**
	 * @param credit_record_anal the credit_record_anal to set
	 */
	public void setCredit_record_anal(String credit_record_anal) {
		this.credit_record_anal = credit_record_anal;
	}

	@XmlElement
	public String getOther_matter_explain() {
		return other_matter_explain;
	}

	/**
	 * @param other_matter_explain the other_matter_explain to set
	 */
	public void setOther_matter_explain(String other_matter_explain) {
		this.other_matter_explain = other_matter_explain;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@XmlElement
	public String getConfirmstatus() {
		return confirmstatus;
	}

	/**
	 * @param confirmstatus the confirmstatus to set
	 */
	public void setConfirmstatus(String confirmstatus) {
		this.confirmstatus = confirmstatus;
	}

	@XmlElement
	public String getExtraIndexStatus() {
		return extraIndexStatus;
	}

	/**
	 * @param extraIndexStatus the extraIndexStatus to set
	 */
	public void setExtraIndexStatus(String extraIndexStatus) {
		this.extraIndexStatus = extraIndexStatus;
	}

	@XmlElement
	public String getReadStatus() {
		return readStatus;
	}

	/**
	 * @param readStatus the readStatus to set
	 */
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	@XmlElement
	public String getApplyReportStatus() {
		return applyReportStatus;
	}

	/**
	 * @param applyReportStatus the applyReportStatus to set
	 */
	public void setApplyReportStatus(String applyReportStatus) {
		this.applyReportStatus = applyReportStatus;
	}

	@XmlElement
	public Date getStartBus_date() {
		return startBus_date;
	}

	/**
	 * @param startBus_date the startBus_date to set
	 */
	public void setStartBus_date(Date startBus_date) {
		this.startBus_date = startBus_date;
	}

	@XmlElement
	public Date getEndBus_date() {
		return endBus_date;
	}

	/**
	 * @param endBus_date the endBus_date to set
	 */
	public void setEndBus_date(Date endBus_date) {
		this.endBus_date = endBus_date;
	}

	@XmlElement
	public String getOrg_no() {
		return org_no;
	}

	/**
	 * @param org_no the org_no to set
	 */
	public void setOrg_no(String org_no) {
		this.org_no = org_no;
	}

	@XmlElement
	public String getRegistAuth() {
		return registAuth;
	}

	/**
	 * @param registAuth the registAuth to set
	 */
	public void setRegistAuth(String registAuth) {
		this.registAuth = registAuth;
	}

	@XmlElement
	public String getCompanyProfile() {
		return companyProfile;
	}

	/**
	 * @param companyProfile the companyProfile to set
	 */
	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}

	@XmlElement
	public String getAdjustReason() {
		return adjustReason;
	}

	/**
	 * @param adjustReason the adjustReason to set
	 */
	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}

	@XmlElement
	public String getAdvantageReason() {
		return advantageReason;
	}

	/**
	 * @param advantageReason the advantageReason to set
	 */
	public void setAdvantageReason(String advantageReason) {
		this.advantageReason = advantageReason;
	}

	@XmlElement
	public String getFinancialContent() {
		return financialContent;
	}

	/**
	 * @param financialContent the financialContent to set
	 */
	public void setFinancialContent(String financialContent) {
		this.financialContent = financialContent;
	}

	
	@XmlElement
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@XmlElement
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@XmlElement
	public String getComCode() {
		return comCode;
	}

	/**
	 * @param comCode the comCode to set
	 */
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	@XmlElement
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}



	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	

	
}
