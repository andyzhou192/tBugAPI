package net.taobao.tbug.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import net.taobao.tbug.util.readexcel.Read2003Excel;
import net.taobao.tbug.util.readexcel.Read2007Excel;
import net.taobao.tbug.util.readexcel.impl.Read2007ExcelImpl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	Read2003Excel read2003Excel = new Read2003Excel();
	Read2007Excel read2007Excel = new Read2007ExcelImpl();

	/**
	 * 对外提供读取excel 的方法
	 * */
	public List<List<List<Object>>> readExcel(File file) throws IOException {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
				.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel.read2003Excel(file);
		} else if ("xlsx".equals(extension)) {
			XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
			// 读取第一章表格内容
			XSSFSheet sheet = xwb.getSheetAt(0);
			return read2007Excel.readExcelAllContent(file);
		} else {
			throw new IOException("不支持的文件类型");
		}
	}

//	public static void main(String args[]) {
//		File file = new File("D:/workspace/tBugApi/WebContent/data/data.xlsx");
//		List<List<List<Object>>> content = null;
//		try {
//			content = readExcel(file);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
