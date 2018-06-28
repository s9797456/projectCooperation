package com.credit.bean.vo.member;

public class UserPassword {
	private String userName;
	//原密码
    private String oldpassword;
    //新密码
    private String newpassword;
    //重复新密码
    private String repeatpassword;
    
    
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getRepeatpassword() {
		return repeatpassword;
	}
	public void setRepeatpassword(String repeatpassword) {
		this.repeatpassword = repeatpassword;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((newpassword == null) ? 0 : newpassword.hashCode());
		result = prime * result
				+ ((oldpassword == null) ? 0 : oldpassword.hashCode());
		result = prime * result
				+ ((repeatpassword == null) ? 0 : repeatpassword.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPassword other = (UserPassword) obj;
		if (newpassword == null) {
			if (other.newpassword != null)
				return false;
		} else if (!newpassword.equals(other.newpassword))
			return false;
		if (oldpassword == null) {
			if (other.oldpassword != null)
				return false;
		} else if (!oldpassword.equals(other.oldpassword))
			return false;
		if (repeatpassword == null) {
			if (other.repeatpassword != null)
				return false;
		} else if (!repeatpassword.equals(other.repeatpassword))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}
