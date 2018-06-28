package com.credit.util.Excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.bean.enterprise.Executives;
import com.credit.bean.enterprise.Shareholder;
import com.credit.bean.vo.addition.ModelIndex;

public class ReadOfData {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	//企业
	public List<EntBaseInfo> readEntBaseInfoExcel(String path) throws IOException, ParseException {
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    //return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readEntBaseInfoXlsx(path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
        return null;
    }
   	public List<EntBaseInfo> readEntBaseInfoXlsx(String path) throws IOException, ParseException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        EntBaseInfo ent = null;
        List<EntBaseInfo> list = new ArrayList<EntBaseInfo>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            int a=0;
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                	ent = new EntBaseInfo();

                    XSSFCell name = xssfRow.getCell(0);
                    XSSFCell ename = xssfRow.getCell(1);
                    XSSFCell brief = xssfRow.getCell(2);
                    XSSFCell areaCode = xssfRow.getCell(3);
                    XSSFCell USCC = xssfRow.getCell(4);
                    XSSFCell status = xssfRow.getCell(5);
                    XSSFCell businessScope = xssfRow.getCell(6);
                    XSSFCell setupDate = xssfRow.getCell(7);
                    XSSFCell issueDate = xssfRow.getCell(8);
                    XSSFCell startDate = xssfRow.getCell(9);
                    XSSFCell endDate = xssfRow.getCell(10);
                    XSSFCell address = xssfRow.getCell(11);
                    XSSFCell regiCapital = xssfRow.getCell(12);
                    XSSFCell currencyType = xssfRow.getCell(13);
                    XSSFCell legalPerson = xssfRow.getCell(14);
                    XSSFCell tel = xssfRow.getCell(15);
                    XSSFCell fax = xssfRow.getCell(16);
                    XSSFCell zipCode = xssfRow.getCell(17);
                    XSSFCell email = xssfRow.getCell(18);
                    XSSFCell website = xssfRow.getCell(19);
                    XSSFCell scale = xssfRow.getCell(20);
                    XSSFCell entType = xssfRow.getCell(21);
                    XSSFCell regisOrg = xssfRow.getCell(22);
                    XSSFCell industry = xssfRow.getCell(23);
                    XSSFCell industryCode = xssfRow.getCell(24);
                    

                    if(name!=null) ent.setName(getValue(name));	 
                    if(ename!=null) ent.setEname(getValue(ename));	 
                    if(brief!=null) ent.setBrief(getValue(brief));	 
                    if(areaCode!=null) ent.setAreaCode(getValue(areaCode));	 
                    if(USCC!=null) ent.setUSCC(getValue(USCC));	 
                    if(status!=null) ent.setStatus(Integer.parseInt(getValue(status).substring(0,1)));	 
                    if(businessScope!=null) ent.setBusinessScope(getValue(businessScope));
                    System.out.println(getValue(setupDate)+sdf.format(sdf.parse(getValue(setupDate))));
                    if(setupDate!=null) ent.setSetupDate(sdf.parse(getValue(setupDate)));	 
                    if(issueDate!=null) ent.setIssueDate(sdf.parse(getValue(issueDate)));	 
                    if(startDate!=null) ent.setStartDate(sdf.parse(getValue(startDate)));	 
                    if(endDate!=null) ent.setEndDate(sdf.parse(getValue(endDate)));	 
                    if(address!=null) ent.setAddress(getValue(address));
                    if(regiCapital!=null) ent.setRegiCapital(getValue(regiCapital));	 
                    if(currencyType!=null) ent.setCurrencyType(getValue(currencyType));	 
                    if(legalPerson!=null) ent.setLegalPerson(getValue(legalPerson));	 
                    if(tel!=null) ent.setTel(getValue(tel));	 
                    if(fax!=null) ent.setFax(getValue(fax));	 
                    if(zipCode!=null) ent.setZipCode(getValue(zipCode));	 
                    if(email!=null) ent.setEmail(getValue(email));	 
                    if(website!=null) ent.setWebsite(getValue(website));	 
                    if(scale!=null) ent.setScale(getValue(scale));	 
                    if(entType!=null) ent.setEntType(getValue(entType));	 
                    if(regisOrg!=null) ent.setRegisOrg(getValue(regisOrg));	 
                    if(industry!=null) ent.setIndustry(getValue(industry));	 
                    if(industryCode!=null) ent.setIndustryCode(getValue(industryCode));	 
                    
                    list.add(ent);
                	a++;
                	System.out.println(a);
                }
            }
        }
		return list;
}
   	//股东
	public List<Shareholder> readShareholderExcel(String path) throws IOException, ParseException {
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    //return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readShareholderXlsx(path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
        return null;
    }
   	public List<Shareholder> readShareholderXlsx(String path) throws IOException, ParseException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Shareholder mi = null;
        List<Shareholder> list = new ArrayList<Shareholder>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            int a=0;
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                	mi = new Shareholder();

                	XSSFCell entName = xssfRow.getCell(0);
                	XSSFCell name = xssfRow.getCell(1);
                    XSSFCell stockpercent = xssfRow.getCell(2);
                    XSSFCell type = xssfRow.getCell(3);
                    XSSFCell realcapi = xssfRow.getCell(4);
                    XSSFCell realTime = xssfRow.getCell(5);
                    XSSFCell shouldcapi = xssfRow.getCell(6);
                    XSSFCell shouldTime = xssfRow.getCell(7);
                    

                    if(entName!=null) mi.setEntID(getValue(entName));
                    if(name!=null) mi.setName(getValue(name));
                    if(stockpercent!=null) mi.setStockpercent(getValue(stockpercent));
                    if(type !=null) mi.setType(getValue(type));
                    if(realcapi!=null) mi.setRealcapi(getValue(realcapi));
                    if(realTime!=null) mi.setRealTime(sdf.parse(getValue(realTime)));
                    if(shouldcapi!=null) mi.setShouldcapi(getValue(shouldcapi));
                    if(shouldTime!=null) mi.setShouldTime(sdf.parse(getValue(shouldTime)));
                    list.add(mi);
                	a++;
                	System.out.println(a);
                }
            }
        }
		return list;
}
	//高管
   	
   	public List<Executives> readExecutivesExcel(String path) throws IOException, ParseException {
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    //return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readExecutivesXlsx(path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
        return null;
    }
   	public List<Executives> readExecutivesXlsx(String path) throws IOException, ParseException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Executives mi = null;
        List<Executives> list = new ArrayList<Executives>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            int a=0;
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                	mi = new Executives();

                	XSSFCell entName = xssfRow.getCell(0);
                    XSSFCell name = xssfRow.getCell(1);
                    XSSFCell age = xssfRow.getCell(2);
                    XSSFCell gender = xssfRow.getCell(3);
                    XSSFCell job = xssfRow.getCell(4);
                    XSSFCell department = xssfRow.getCell(5);
                    XSSFCell education = xssfRow.getCell(6);
                    XSSFCell workExp = xssfRow.getCell(7);
                    XSSFCell IDCard = xssfRow.getCell(8);
                    

                    if(entName!=null) mi.setEntID(getValue(entName));	       
                    if(name!=null) mi.setName(getValue(name));	       
                    if(age!=null) mi.setAge(Integer.parseInt(getValue(age)));	       
                    if(gender !=null) mi.setGender(getValue(gender));	       
                    if(job!=null) mi.setJob(getValue(job));	       
                    if(department!=null) mi.setDepartment(getValue(department));	       
                    if(education!=null) mi.setEducation(getValue(education));	       
                    if(workExp!=null) mi.setWorkExp(getValue(workExp));	       
                    if(IDCard!=null) mi.setIDCard(getValue(IDCard));	       
                    list.add(mi);
                	a++;
                	System.out.println(a);
                }
            }
        }
		return list;
}
	
	   	public List<ModelIndex> readModelIndexExcel(String path) throws IOException {
	        if (path == null || Common.EMPTY.equals(path)) {
	            return null;
	        } else {
	            String postfix = Util.getPostfix(path);
	            if (!Common.EMPTY.equals(postfix)) {
	                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
	                    //return readXls(path);
	                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
	                    return readModelIndexXlsx(path);
	                }
	            } else {
	                System.out.println(path + Common.NOT_EXCEL_FILE);
	            }
	        }
	        return null;
	    }
	   	public List<ModelIndex> readModelIndexXlsx(String path) throws IOException {
	        System.out.println(Common.PROCESSING + path);
	        InputStream is = new FileInputStream(path);
	        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
	        ModelIndex mi = null;
	        List<ModelIndex> list = new ArrayList<ModelIndex>();
	        // Read the Sheet
	        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
	            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
	            if (xssfSheet == null) {
	                continue;
	            }
	            int a=0;
	            // Read the Row
	            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
	                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
	                if (xssfRow != null) {
	                	mi = new ModelIndex();

	                    XSSFCell first = xssfRow.getCell(0);//一级指标
	                    XSSFCell second = xssfRow.getCell(1);//二级指标
	                    XSSFCell third = xssfRow.getCell(2);//三级指标
	                    XSSFCell weight = xssfRow.getCell(3);//权重
	                    XSSFCell one = xssfRow.getCell(4);//1
	                    XSSFCell half = xssfRow.getCell(5);//0.5
	                    XSSFCell zero = xssfRow.getCell(6);//0
	                    

	                    if(first!=null) mi.setFirst(getValue(first));	       
	                    if(second!=null) mi.setSecond(getValue(second));	       
	                    if(third !=null) mi.setThird(getValue(third));	       
	                    if(weight!=null) mi.setWight(getValue(weight));	       
	                    if(one!=null) mi.setOne(getValue(one));	       
	                    if(half!=null) mi.setHalf(getValue(half));	       
	                    if(zero!=null) mi.setZero(getValue(zero));	       
	                    list.add(mi);
	                	a++;
	                	System.out.println(a);
	                }
	            }
	        }
			return list;
}
	   	@SuppressWarnings("static-access")
	    private String getValue(XSSFCell xssfRow) {
	        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(xssfRow.getBooleanCellValue());
	        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
	            return String.valueOf(xssfRow.getNumericCellValue());
	        } else {
	            return String.valueOf(xssfRow.getStringCellValue());
	        }
	    }
}
