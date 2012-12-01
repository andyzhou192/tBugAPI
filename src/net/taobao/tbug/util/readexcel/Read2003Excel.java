package net.taobao.tbug.util.readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class Read2003Excel {
	/**
	 * 读取 office 2003 excel
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List<List<List<Object>>> read2003Excel(File file) throws IOException {
		List<List<List<Object>>> sheetList = new LinkedList<List<List<Object>>>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		for(int k = 0; k < hwb.getNumberOfSheets(); k++){
			HSSFSheet sheet = hwb.getSheetAt(k);
			Object value = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<List<Object>> rowList = new LinkedList<List<Object>>();
			for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				List<Object> columnLinked = new LinkedList<Object>();
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					if (cell == null) {
						continue;
					}
					DecimalFormat df = new DecimalFormat("0");// 格式化 number String
					// 字符
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
					DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						System.out.println(i + "行" + j + " 列 is String type");
						value = cell.getStringCellValue();
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						System.out.println(i + "行" + j
								+ " 列 is Number type ; DateFormt:"
								+ cell.getCellStyle().getDataFormatString());
						if ("@".equals(cell.getCellStyle().getDataFormatString())) {
							value = df.format(cell.getNumericCellValue());
						} else if ("General".equals(cell.getCellStyle()
								.getDataFormatString())) {
							value = nf.format(cell.getNumericCellValue());
						} else {
							value = sdf.format(HSSFDateUtil.getJavaDate(cell
									.getNumericCellValue()));
						}
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
						System.out.println(i + "行" + j + " 列 is Boolean type");
						value = cell.getBooleanCellValue();
						break;
					case XSSFCell.CELL_TYPE_BLANK:
						System.out.println(i + "行" + j + " 列 is Blank type");
						value = "";
						break;
					default:
						System.out.println(i + "行" + j + " 列 is default type");
						value = cell.toString();
					}
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
}
