package com.credit.util.financeExecl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.credit.bean.vo.enterprise.FinanceVo;

public class ResolveFinance {
	
	   	public static List<FinanceVo> ReadExecl(String path) throws IOException {
	        if (path == null || Common.EMPTY.equals(path)) {
	            return null;
	        } else {
	            String postfix = Util.getPostfix(path);
	            if (!Common.EMPTY.equals(postfix)) {
	                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
	                    //return readXls(path);
	                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
	                    return ResolveExecl(path);
	                }
	            } else {
	                System.out.println(path + Common.NOT_EXCEL_FILE);
	            }
	        }
	        return null;
	    }
	   	private static List<FinanceVo> ResolveExecl(String path) throws IOException {
	        System.out.println(Common.PROCESSING + path);
	        InputStream is = new FileInputStream(path);
	        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
	        List<FinanceVo> list = new ArrayList<FinanceVo>();
	        // Read the Sheet
	        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
	        	if(numSheet > 0){
	        		continue;
	        	}
	            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
	            if (xssfSheet == null) {
	                continue;
	            }
	            // Read the Row
	            String table=new String();
            	String year1=new String();
            	String year2=new String();
            	String year3=new String();
	            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
	                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
	                if (xssfRow != null) {
	                	if(rowNum==1){
	                		 XSSFCell name = xssfRow.getCell(3);
	                		 if(name!=null)table=getValue(name);
	                	}else if(rowNum==3){
	                		XSSFCell value1 = xssfRow.getCell(4);
		                    XSSFCell value2 = xssfRow.getCell(5);
		                    XSSFCell value3 = xssfRow.getCell(6);
		                    if(value1!=null)year1=getValue(value1);
	 	                    if(value2!=null)year2=getValue(value2);
	 	                    if(value3!=null)year3=getValue(value3);
	                	}else{
	                		XSSFCell name = xssfRow.getCell(3);
	 	                    XSSFCell value1 = xssfRow.getCell(4);
	 	                    XSSFCell value2 = xssfRow.getCell(5);
	 	                    XSSFCell value3 = xssfRow.getCell(6);
	 	                    if(name!=null&&rowNum!=2){
	 	                    	FinanceVo finance1 = new FinanceVo();
	 	                    	FinanceVo finance2 = new FinanceVo();
	 	                    	FinanceVo finance3 = new FinanceVo();
	 	                    	finance1.setName(getValue(name));
	 	                    	finance1.setTable(table);
	 	                    	finance2.setName(getValue(name));
	 	                    	finance2.setTable(table);
	 	                    	finance3.setName(getValue(name));
	 	                    	finance3.setTable(table);
	 	                    	if(value1!=null){
	 	                    		finance1.setYear(year1);
	 	                    		finance1.setValue(getValue(value1));
	 	                    		finance1.setType(1);
	 	                    	}
	 	                    	if(value2!=null){
	 	                    		finance2.setYear(year2);
	 	                    		finance2.setValue(getValue(value2));
	 	                    		finance2.setType(2);
	 	                    	}
	 	                    	if(value3!=null){
	 	                    		finance3.setYear(year3);
	 	                    		finance3.setValue(getValue(value3));
	 	                    		finance3.setType(3);
	 	                    	}
	 	                    	list.add(finance1);
	 	                    	list.add(finance2);
	 	                    	list.add(finance3);
	 	                    }
	                	}
	                }
	            }
	        }
			return list;
}
	   	public static List<FinanceVo> ReadExecl1(String path) throws IOException {
	        if (path == null || Common.EMPTY.equals(path)) {
	            return null;
	        } else {
	            String postfix = Util.getPostfix(path);
	            if (!Common.EMPTY.equals(postfix)) {
	                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
	                    //return readXls(path);
	                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
	                    return ResolveExecl1(path);
	                }
	            } else {
	                System.out.println(path + Common.NOT_EXCEL_FILE);
	            }
	        }
	        return null;
	    }
	   	private static List<FinanceVo> ResolveExecl1(String path) throws IOException {
	        System.out.println(Common.PROCESSING + path);
	        InputStream is = new FileInputStream(path);
	        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
	        List<FinanceVo> list = new ArrayList<FinanceVo>();
	        // Read the Sheet
	        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
	        	if(numSheet != 1){
	        		continue;
	        	}
	            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
	            if (xssfSheet == null) {
	                continue;
	            }
	            // Read the Row
	            String table=new String();
            	String year1=new String();
            	String year2=new String();
            	String year3=new String();
	            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
	                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
	                if (xssfRow != null) {
	                	if(rowNum==1){
	                		 XSSFCell name = xssfRow.getCell(3);
	                		 if(name!=null)table=getValue(name);
	                	}else if(rowNum==3){
	                		XSSFCell value1 = xssfRow.getCell(4);
		                    XSSFCell value2 = xssfRow.getCell(5);
		                    XSSFCell value3 = xssfRow.getCell(6);
		                    if(value1!=null)year1=getValue(value1);
	 	                    if(value2!=null)year2=getValue(value2);
	 	                    if(value3!=null)year3=getValue(value3);
	                	}else{
	                		XSSFCell name = xssfRow.getCell(3);
	 	                    XSSFCell value1 = xssfRow.getCell(4);
	 	                    XSSFCell value2 = xssfRow.getCell(5);
	 	                    XSSFCell value3 = xssfRow.getCell(6);
	 	                    if(name!=null&&rowNum!=2){
	 	                    	FinanceVo finance1 = new FinanceVo();
	 	                    	FinanceVo finance2 = new FinanceVo();
	 	                    	FinanceVo finance3 = new FinanceVo();
	 	                    	finance1.setName(getValue(name));
	 	                    	finance1.setTable(table);
	 	                    	finance2.setName(getValue(name));
	 	                    	finance2.setTable(table);
	 	                    	finance3.setName(getValue(name));
	 	                    	finance3.setTable(table);
	 	                    	if(value1!=null){
	 	                    		finance1.setYear(year1);
	 	                    		finance1.setValue(getValue(value1));
	 	                    		finance1.setType(1);
	 	                    	}
	 	                    	if(value2!=null){
	 	                    		finance2.setYear(year2);
	 	                    		finance2.setValue(getValue(value2));
	 	                    		finance2.setType(2);
	 	                    	}
	 	                    	if(value3!=null){
	 	                    		finance3.setYear(year3);
	 	                    		finance3.setValue(getValue(value3));
	 	                    		finance3.setType(3);
	 	                    	}
	 	                    	list.add(finance1);
	 	                    	list.add(finance2);
	 	                    	list.add(finance3);
	 	                    }
	                	}
	                }
	            }
	        }
			return list;
}
	   	
	   	public static List<FinanceVo> ReadExecl2(String path) throws IOException {
	        if (path == null || Common.EMPTY.equals(path)) {
	            return null;
	        } else {
	            String postfix = Util.getPostfix(path);
	            if (!Common.EMPTY.equals(postfix)) {
	                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
	                    //return readXls(path);
	                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
	                    return ResolveExecl2(path);
	                }
	            } else {
	                System.out.println(path + Common.NOT_EXCEL_FILE);
	            }
	        }
	        return null;
	    }
	   	private static List<FinanceVo> ResolveExecl2(String path) throws IOException {
	        System.out.println(Common.PROCESSING + path);
	        InputStream is = new FileInputStream(path);
	        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
	        List<FinanceVo> list = new ArrayList<FinanceVo>();
	        // Read the Sheet
	        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
	            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
	            if (xssfSheet == null) {
	                continue;
	            }
	            // Read the Row
	            String table=new String();
            	String year1=new String();
            	String year2=new String();
            	String year3=new String();
	            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
	                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
	                if (xssfRow != null) {
	                	if(rowNum==1){
	                		 XSSFCell name = xssfRow.getCell(3);
	                		 if(name!=null)table=getValue(name);
	                	}else if(rowNum==3){
	                		XSSFCell value1 = xssfRow.getCell(4);
		                    XSSFCell value2 = xssfRow.getCell(5);
		                    XSSFCell value3 = xssfRow.getCell(6);
		                    if(value1!=null)year1=getValue(value1);
	 	                    if(value2!=null)year2=getValue(value2);
	 	                    if(value3!=null)year3=getValue(value3);
	                	}else{
	                		XSSFCell name = xssfRow.getCell(3);
	 	                    XSSFCell value1 = xssfRow.getCell(4);
	 	                    XSSFCell value2 = xssfRow.getCell(5);
	 	                    XSSFCell value3 = xssfRow.getCell(6);
	 	                    if(name!=null&&rowNum!=2){
	 	                    	FinanceVo finance1 = new FinanceVo();
	 	                    	FinanceVo finance2 = new FinanceVo();
	 	                    	FinanceVo finance3 = new FinanceVo();
	 	                    	finance1.setName(getValue(name));
	 	                    	finance1.setTable(table);
	 	                    	finance2.setName(getValue(name));
	 	                    	finance2.setTable(table);
	 	                    	finance3.setName(getValue(name));
	 	                    	finance3.setTable(table);
	 	                    	if(value1!=null){
	 	                    		finance1.setYear(year1);
	 	                    		finance1.setValue(getValue(value1));
	 	                    		finance1.setType(1);
	 	                    	}
	 	                    	if(value2!=null){
	 	                    		finance2.setYear(year2);
	 	                    		finance2.setValue(getValue(value2));
	 	                    		finance2.setType(2);
	 	                    	}
	 	                    	if(value3!=null){
	 	                    		finance3.setYear(year3);
	 	                    		finance3.setValue(getValue(value3));
	 	                    		finance3.setType(3);
	 	                    	}
	 	                    	list.add(finance1);
	 	                    	list.add(finance2);
	 	                    	list.add(finance3);
	 	                    }
	                	}
	                }
	            }
	        }
			return list;
}
	    private static String getValue(XSSFCell xssfRow) {
	        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(xssfRow.getBooleanCellValue());
	        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
	            return String.valueOf(xssfRow.getNumericCellValue());
	        } else {
	            return String.valueOf(xssfRow.getStringCellValue());
	        }
	    }
}
