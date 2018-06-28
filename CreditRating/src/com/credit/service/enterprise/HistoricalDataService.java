package com.credit.service.enterprise;

import java.util.List;

import com.credit.bean.enterprise.HistoricalData;
import com.credit.dao.DAO;

public interface HistoricalDataService extends DAO<HistoricalData> {

	List<HistoricalData> selectByEntId(String entid);
	
}
