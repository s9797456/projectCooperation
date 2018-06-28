package com.credit.bean.vo.privilege;

import java.util.Date;

public class OrganizationVO {
		// 主键
		private String uuid;
		// 名称
		private String name;
		// 描述
		private String description;
		// 联系电话
		private String phone;
		// 邮件地址
		private String email;
		// 启用标志
		private String visible ;
		// 代表图片
		private String imgUrl;
		// 二级域名
		private String twoDomainNames;
		// 公司网站
		private String orgUrl;
		// 状态
		private String status;
		// 帐号
		private String userName;
		// 登录次数
		private Integer loginTimes;
		// 注册日期
		private Date regTime;
		// 更新日期
		private Date updateTime;
		//类型
		private int type = 0;
		// 成员状态 true为激活,false为关闭
		private Integer cusVisible ;
		
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
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		/**
		 * @return the phone
		 */
		public String getPhone() {
			return phone;
		}
		/**
		 * @param phone the phone to set
		 */
		public void setPhone(String phone) {
			this.phone = phone;
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
		 * @return the visible
		 */
		public String getVisible() {
			return visible;
		}
		/**
		 * @param visible the visible to set
		 */
		public void setVisible(String visible) {
			this.visible = visible;
		}
		/**
		 * @return the imgUrl
		 */
		public String getImgUrl() {
			return imgUrl;
		}
		/**
		 * @param imgUrl the imgUrl to set
		 */
		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
		/**
		 * @return the twoDomainNames
		 */
		public String getTwoDomainNames() {
			return twoDomainNames;
		}
		/**
		 * @param twoDomainNames the twoDomainNames to set
		 */
		public void setTwoDomainNames(String twoDomainNames) {
			this.twoDomainNames = twoDomainNames;
		}
		/**
		 * @return the orgUrl
		 */
		public String getOrgUrl() {
			return orgUrl;
		}
		/**
		 * @param orgUrl the orgUrl to set
		 */
		public void setOrgUrl(String orgUrl) {
			this.orgUrl = orgUrl;
		}
		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}
		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}
		/**
		 * @return the userName
		 */
		public String getUserName() {
			return userName;
		}
		/**
		 * @param userName the userName to set
		 */
		public void setUserName(String userName) {
			this.userName = userName;
		}
		/**
		 * @return the loginTimes
		 */
		public Integer getLoginTimes() {
			return loginTimes;
		}
		/**
		 * @param loginTimes the loginTimes to set
		 */
		public void setLoginTimes(Integer loginTimes) {
			this.loginTimes = loginTimes;
		}
		/**
		 * @return the cusVisible
		 */
		public Integer getCusVisible() {
			return cusVisible;
		}
		/**
		 * @param cusVisible the cusVisible to set
		 */
		public void setCusVisible(Integer cusVisible) {
			this.cusVisible = cusVisible;
		}
		/**
		 * @return the regTime
		 */
		public Date getRegTime() {
			return regTime;
		}
		/**
		 * @param regTime the regTime to set
		 */
		public void setRegTime(Date regTime) {
			this.regTime = regTime;
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
		 * @return the type
		 */
		public int getType() {
			return type;
		}
		/**
		 * @param type the type to set
		 */
		public void setType(int type) {
			this.type = type;
		}
		
}