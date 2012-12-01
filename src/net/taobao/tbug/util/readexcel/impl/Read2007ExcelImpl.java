package net.taobao.tbug.util.readexcel.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import net.taobao.tbug.util.readexcel.Read2007Excel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read2007ExcelImpl implements Read2007Excel {
	
	/**
	 * 获取excel中各sheet的名称
	 * @param xwb
	 * @return
	 */
	public List<String> getSheetNames(XSSFWorkbook xwb){
		List<String> sheetNameList = new LinkedList<String>();
		// 获取sheet数量
		int sheetNum = xwb.getNumberOfSheets();
		for (int i = 0; i < sheetNum; i++) {
			sheetNameList.add(xwb.getSheetName(i));
		}
		return sheetNameList;
		
	}
	
	/**
	 * 获取指定行内容
	 * @param sheet rowIndex
	 * @return
	 */
	public List<Object> getRowContent(XSSFSheet sheet, int rowIndex){
		List<Object> valueList = new LinkedList<Object>();
		if( sheet != null){
			XSSFRow row = sheet.getRow(rowIndex);
			if( row != null){
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
					valueList.add(getCellContent(sheet, rowIndex, j));
				}
			}
		}
		return valueList;
	}
	
	/**
	 * 获取指定列内容
	 * @param sheet rowIndex 
	 * @return
	 */
	public List<Object> getColumnContent(XSSFSheet sheet, int columnIndex){
		List<Object> valueList = new LinkedList<Object>();
		if( sheet != null){
			for (int i = 2; i <= sheet.getPhysicalNumberOfRows(); i++) {
				valueList.add(getCellContent(sheet, i, columnIndex));
			}
		}
		return valueList;
		
	}
	
	/**
	 * 获取指定单元格内容
	 * @param sheet rowIndex columnIndex
	 * @return
	 */
	public Object getCellContent(XSSFSheet sheet, int rowIndex, int columnIndex){
		Object value = null;
		if(sheet != null){
			XSSFRow row = sheet.getRow(rowIndex);
			if(row != null){
				XSSFCell cell = row.getCell(columnIndex);
				if(cell != null){
					DecimalFormat df = new DecimalFormat("0");// 格式化 number String字符
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
					DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						if ("@".equals(cell.getCellStyle().getDataFormatString())) {
							value = df.format(cell.getNumericCellValue());
						} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
							value = nf.format(cell.getNumericCellValue());
						} else {
							value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
						}
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
						value = cell.getBooleanCellValue();
						break;
					case XSSFCell.CELL_TYPE_BLANK:
						value = "";
						break;
					default:
						value = cell.toString();
					}
					if(value == null)
						value = "";
				}
			}
		}
		return value;
	}
	
	/**
	 * 读取Office 2007 excel
	 * */
	public List<List<List<Object>>> readExcelAllContent(File file) throws IOException {
		List<List<List<Object>>> sheetList = new LinkedList<List<List<Object>>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		
		// 读取每章表格内容
		for(int k = 0; k < xwb.getNumberOfSheets(); k++){
			// 读取一章表格内容
			XSSFSheet sheet = xwb.getSheetAt(k);
			Object value = null;
			XSSFRow row = null;
			List<List<Object>> rowList = new LinkedList<List<Object>>();
			for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				List<Object> columnLinked = new LinkedList<Object>();
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
					value = getCellContent(sheet, i, j);
					if (value == null || "".equals(value)) {
						continue;
					}
					columnLinked.add(value);
				}
				rowList.add(columnLinked);
			}
			sheetList.add(rowList);
		}
		return sheetList;
	}
	
//	public static void main(String args[]) {
//		File file = new File("D:/workspace/tBugApi/WebContent/data/data.xlsx");
//		List<List<Object>> content = null;
//		try {
//			content = read2007Excel(file);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
