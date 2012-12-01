package net.taobao.tbug.util;

import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * Creat by 郑彩燕（彩子esevv） 
 */
public class ReadContext {
    

 /**
  * 返回指定文件每个sheet的第1列以外
  * 取得三级类型
 */
  public Map<String, String> getTpye23( String filename ,String type) {

	   Map<String, String> aList = new LinkedHashMap<String, String>();
 		
       File file = new File(filename);
 		
 		ExcelReader readExcel = new ExcelReader(file);
 		try {
 			readExcel.open();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		
 		//取得文件的sheet数；
 		int con =readExcel.getSheetCount();
 		
 		for(int n=0 ;n<con; n++){
 			readExcel.setSheetNum(n); 
 		    // 总行数
 			int count = readExcel.getRowCount();
 			
 			for (int i = 0; i <= count; i++) {
 				
 				String[] rows = readExcel.readExcelLine(i);
 				for (int j = 0; j < rows.length; j++) {
 					if (type == "1") {
 					  if (j == 0 ){
 							if (rows[0] != ""){
 					          String str =String.valueOf(n+1) +String.valueOf(i+1 );
 					          //aList.add(str+rows[0]);
 					          //aList.put(str+"_"+rows[0],String.valueOf(str));
 					          
 					         aList.put(str+"_"+rows[0],str+"_"+rows[0]);
 					       }
 					    }
 					 }else {
 						if (j !=0 ){
 							if (rows[j] != ""){
 		 					    String str =String.valueOf(n+1) +String.valueOf(i+1 )+"_"+String.valueOf(j);
 		 					    //aList.add(str+rows[j]);
 		 					   //aList.put(str+"_"+rows[j],String.valueOf(str));
 		 					   
 		 					   aList.put(str+"_"+rows[j],str+"_"+rows[j]);
 							}
 		 				 } 
 						 
 					 }
 				}
 			}
 		}

 		return aList;	
  }
  
 /**
  * 返回指定文件Sheet名列表 1级类型
  * 取得一级类型
  */
 public Map<String, String> getTpye1(String filename) {
	 
	 //ArrayList<String>  sheetNameList = new  ArrayList<String> ();
	 
	 Map<String, String> sheetNameList = new LinkedHashMap<String, String>();
	 
	 File file = new File(filename);
	 ExcelReader readExcel = new ExcelReader(file);
	 try {
			readExcel.open();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//取得文件的sheet数；
		int con =readExcel.getSheetCount();
		
		//取得文件的sheet名列表；
		for(int i=0 ;i<con; i++){
			int str = i+1;
	        //sheetNameList.add(String.valueOf(str)+"_"+readExcel.getSheetName(i));
			
			//sheetNameList.put(String.valueOf(str)+"_"+readExcel.getSheetName(i),String.valueOf(str));
			
			sheetNameList.put(String.valueOf(str)+"_"+readExcel.getSheetName(i),String.valueOf(str)+"_"+readExcel.getSheetName(i));
		}
	 
	 return sheetNameList;	 
 
 }
 
/* public static void main(String args[]) {
	 
	ArrayList type1List = new ArrayList<String> ();
	ArrayList<String> type2List = new ArrayList<String> ();
	ArrayList<String> type3List = new ArrayList<String> ();
	
	ReadContext red= new ReadContext();
	String filename = "D:\\tsetcase.xls";
	
	type1List = red.getTpye1(filename);
	type2List = red.getTpye23(filename,"1");
	type3List =red.getTpye23(filename,"2");

	System.out.print("ok");
}*/
}
