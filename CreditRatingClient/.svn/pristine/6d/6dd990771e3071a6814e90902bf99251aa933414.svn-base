package com.credit.util.financialexecl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelShower {

	/*
	 * // 获取html代码 public static String getExcelToHtml(XSSFWorkbook wb) { try {
	 * String htmlPage = getExcelInfo(wb); return htmlPage; } catch (Exception
	 * e) { e.printStackTrace(); } return null; }
	 */

	public static String getExcelInfo(String path) throws Exception {
		XSSFSheet sheet = null;
		File sourcefile = new File(path);
		InputStream is = new FileInputStream(sourcefile);
		XSSFWorkbook wb = new XSSFWorkbook(is);
		StringBuffer sb = new StringBuffer();
		if (wb.getNumberOfSheets() == 3) {
			List<String> list = new ArrayList<String>();
			list.add("assetsLiability");
			list.add("profitList");
			list.add("cashFlowStatement");
			sb.append("<div class='tab-content'>");
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				sheet = wb.getSheetAt(i);
				int lastRowNum = sheet.getLastRowNum();
				Map<String, String> map[] = getRowSpanColSpanMap(sheet);
				sb.append("<div class='tab-pane' id='" + list.get(i) + "'>");
				sb.append("<table style='border-collapse:collapse;'  width='100%'>");
				XSSFRow row = null;
				XSSFCell cell = null;
				// System.out.println(sheet.getPhysicalNumberOfRows());
				for (int rowNum = sheet.getFirstRowNum(); rowNum <= lastRowNum; rowNum++) {
					row = (XSSFRow) sheet.getRow(rowNum);
					if (row == null) {
						sb.append("<tr><td style='" + getNullCellBorderStyle()
								+ "' > &nbsp;</td></tr>");
						continue;
					}
					sb.append("<tr>");
					int lastColNum = row.getLastCellNum();
					for (int colNum = 0; colNum < lastColNum; colNum++) {
						cell = row.getCell(colNum);
						if (cell == null) {
							sb.append("<td style='" + getNullCellBorderStyle()
									+ "'>&nbsp;</td>");
							continue;
						}
						if(rowNum == 3 && colNum == 2){//此判断根据项目具体情况添加
							sb.append("<td></td>");
							continue;
						}
						String stringValue = getCellValue(cell);
						if (map[0].containsKey(rowNum + "," + colNum)) {
							String pointString = map[0].get(rowNum + ","
									+ colNum);
							map[0].remove(rowNum + "," + colNum);
							int bottomeRow = Integer.valueOf(pointString
									.split(",")[0]);
							int bottomeCol = Integer.valueOf(pointString
									.split(",")[1]);
							int rowSpan = bottomeRow - rowNum + 1;
							int colSpan = bottomeCol - colNum + 1;
							sb.append("<td rowspan= '" + rowSpan
									+ "' colspan= '" + colSpan + "' ");
						} else if (map[1].containsKey(rowNum + "," + colNum)) {
							map[1].remove(rowNum + "," + colNum);
							continue;
						} else {
							sb.append("<td ");
						}
						// 获取样式的内容

						XSSFCellStyle cellStyle = cell.getCellStyle();
						if (cellStyle != null) {
							short alignment = cellStyle.getAlignment();
							sb.append("align='" + convertAlignToHtml(alignment)
									+ "' ");
							short verticalAlignment = cellStyle
									.getVerticalAlignment();
							sb.append("valign='"
									+ convertVerticalAlignToHtml(verticalAlignment)
									+ "' ");
							// ---------
							XSSFFont xf = cellStyle.getFont();

							short boldWeight = xf.getBoldweight();

							XSSFColor xc = xf.getXSSFColor();

							sb.append("style='");

							String fontColorStr = ColorUtil
									.convertColorToHex(xc);

							int columnWidth = sheet.getColumnWidth(cell
									.getColumnIndex());

							sb.append("width:" + columnWidth + "px;");

							if (fontColorStr != null
									&& !"".equals(fontColorStr.trim())) {

								sb.append("color:" + fontColorStr + ";"); // 字体颜色
							}
							XSSFColor bgColor = cellStyle
									.getFillForegroundXSSFColor();
							String bgColorStr = ColorUtil
									.convertColorToHex(bgColor);

							if (bgColorStr != null
									&& !"".equals(bgColorStr.trim())) {
								System.out.println(bgColorStr);
								sb.append("background-color:" + bgColorStr+ ";"); // 背景颜色#92D050
							}

							sb.append(getBorderStyle(0,
									cellStyle.getBorderTop(),
									cellStyle.getTopBorderXSSFColor()));
							sb.append(getBorderStyle(1,
									cellStyle.getBorderRight(),
									cellStyle.getRightBorderXSSFColor()));
							sb.append(getBorderStyle(2,
									cellStyle.getBorderBottom(),
									cellStyle.getBottomBorderXSSFColor()));
							sb.append(getBorderStyle(3,
									cellStyle.getBorderLeft(),
									cellStyle.getLeftBorderXSSFColor()));
							// -----------------

							sb.append("font-weight:" + boldWeight + ";"); // 字体加粗

							sb.append("font-size: " + xf.getFontHeight() / 2.5
									+ "%;"); // 字体大小

							sb.append("' ");
						}
						// end------
						sb.append(">");

						if (stringValue == null
								|| "".equals(stringValue.trim())) {

							sb.append(" &nbsp; ");
						} else {
							// 将ascii码为160的空格转换为html下的空格（&nbsp;）
							sb.append(stringValue.replace(
									String.valueOf((char) 160), "&nbsp;"));
						}

						sb.append("</td>");
					}
					sb.append("</tr>");
				}
				sb.append("</table>");
				sb.append("</div>");
				is.close();
			}
		}

		sb.append("</div>");
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String>[] getRowSpanColSpanMap(Sheet sheet) {

		Map<String, String> map0 = new HashMap<String, String>();
		Map<String, String> map1 = new HashMap<String, String>();

		int mergedNum = sheet.getNumMergedRegions();

		CellRangeAddress range = null;

		for (int i = 0; i < mergedNum; i++) {

			range = sheet.getMergedRegion(i);

			int topRow = range.getFirstRow();

			int topCol = range.getFirstColumn();

			int bottomRow = range.getLastRow();

			int bottomCol = range.getLastColumn();

			map0.put(topRow + "," + topCol, bottomRow + "," + bottomCol);

			// System.out.println(topRow + "," + topCol + "," + bottomRow + ","
			// + bottomCol);

			int tempRow = topRow;

			while (tempRow <= bottomRow) {

				int tempCol = topCol;

				while (tempCol <= bottomCol) {

					map1.put(tempRow + "," + tempCol, "");

					tempCol++;
				}

				tempRow++;
			}

			map1.remove(topRow + "," + topCol);

		}

		@SuppressWarnings("rawtypes")
		Map[] map = { map0, map1 };

		return map;
	}

	private static String convertAlignToHtml(short alignment) {

		String align = "left";

		switch (alignment) {

		case XSSFCellStyle.ALIGN_LEFT:
			align = "left";
			break;
		case XSSFCellStyle.ALIGN_CENTER:
			align = "center";
			break;
		case XSSFCellStyle.ALIGN_RIGHT:
			align = "right";
			break;

		default:
			break;
		}

		return align;
	}

	private static String convertVerticalAlignToHtml(short verticalAlignment) {

		String valign = "middle";

		switch (verticalAlignment) {

		case XSSFCellStyle.VERTICAL_BOTTOM:
			valign = "bottom";
			break;
		case XSSFCellStyle.VERTICAL_CENTER:
			valign = "center";
			break;
		case XSSFCellStyle.VERTICAL_TOP:
			valign = "top";
			break;
		default:
			break;
		}

		return valign;
	}

/*	private static String getCellValue(XSSFCell cell) {

		switch (cell.getCellType()) {

		case XSSFCell.CELL_TYPE_NUMERIC:

			DecimalFormat format = new DecimalFormat();
			format.applyPattern("0.00%");
			return format.format(cell.getNumericCellValue());
			// return String.valueOf(cell.getNumericCellValue());

		case XSSFCell.CELL_TYPE_STRING:

			return cell.getStringCellValue();

		case XSSFCell.CELL_TYPE_FORMULA:

			return cell.getCellFormula();

		default:
			return "";
		}
	}*/

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private static String getCellValue(XSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case XSSFCell.CELL_TYPE_NUMERIC:
			case XSSFCell.CELL_TYPE_FORMULA: {
				try {
					// 取得当前Cell的数值
					DecimalFormat df = new DecimalFormat("0");
					cellvalue = df.format(cell.getNumericCellValue()).trim();
				} catch (Exception e) {
					cellvalue = cell.getCellFormula().trim();
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case XSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString().trim();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}
	static String[] bordesr = { "border-top:", "border-right:",
			"border-bottom:", "border-left:" };
	static String[] borderStyles = { "solid ", "solid ", "solid ", "solid ",
			"solid ", "solid ", "solid ", "solid ", "solid ", "solid", "solid",
			"solid", "solid", "solid" };

	// tring[] borderClors={};
	private static String getBorderStyle(int b, short s, XSSFColor t) {

		if (s == 0)
			return bordesr[b] + borderStyles[s] + "#d0d7e5 1px;";
		;
		String borderColorStr = ColorUtil.convertColorToHex(t);
		borderColorStr = borderColorStr == null || borderColorStr.length() < 1 ? "#000000"
				: borderColorStr;
		// System.out.println(
		// bordesr[b]+borderStyles[s]+borderColorStr+" 1px; "+t.getRgb());

		return bordesr[b] + borderStyles[s] + borderColorStr + " 1px;";
	}

	private static String getNullCellBorderStyle() {

		return "border: #d0d7e5 1px 1px 1px 1px;";
	}
}