package net.taobao.tbug.util.readexcel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface Read2007Excel {
	
	// 获取excel中各sheet的名称
	public List<String> getSheetNames(XSSFWorkbook xwb);
	
	// 获取指定单元格内容
	public Object getCellContent(XSSFSheet sheet, int rowIndex, int columnIndex);
	
	/**
	 * 获取指定行内容
	 * @param sheet rowIndex
	 * @return
	 */
	public List<Object> getRowContent(XSSFSheet sheet, int rowIndex);
	
	/**
	 * 获取指定列内容
	 * @param sheet rowIndex 
	 * @return
	 */
	public List<Object> getColumnContent(XSSFSheet sheet, int columnIndex);
	
	/**
	 * 读取Office 2007 excel
	 * 返回结果嵌套关系为：sheet<row<column<value>>>
	 * */
	public List<List<List<Object>>> readExcelAllContent(File file) throws IOException;
}
