package com.credit.modelvo.organization;

public class StatisticalListVO {

	private String c_scorestate;//统计个数
	
	private String c_applyreportstate;//统计个数
	
	private String scorestate;//统计scorestate
	
	private String applyreportstate;//统计applyreportstate

	public String getC_scorestate() {
		return c_scorestate;
	}

	public void setC_scorestate(String c_scorestate) {
		this.c_scorestate = c_scorestate;
	}

	public String getC_applyreportstate() {
		return c_applyreportstate;
	}

	public void setC_applyreportstate(String c_applyreportstate) {
		this.c_applyreportstate = c_applyreportstate;
	}

	public String getScorestate() {
		return scorestate;
	}

	public void setScorestate(String scorestate) {
		this.scorestate = scorestate;
	}

	public String getApplyreportstate() {
		return applyreportstate;
	}

	public void setApplyreportstate(String applyreportstate) {
		this.applyreportstate = applyreportstate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((applyreportstate == null) ? 0 : applyreportstate.hashCode());
		result = prime * result
				+ ((scorestate == null) ? 0 : scorestate.hashCode());
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
		StatisticalListVO other = (StatisticalListVO) obj;
		if (applyreportstate == null) {
			if (other.applyreportstate != null)
				return false;
		} else if (!applyreportstate.equals(other.applyreportstate))
			return false;
		if (scorestate == null) {
			if (other.scorestate != null)
				return false;
		} else if (!scorestate.equals(other.scorestate))
			return false;
		return true;
	}
	
}
