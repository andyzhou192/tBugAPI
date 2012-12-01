package net.taobao.tbug.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.taobao.tbug.util.DownloadFile;
import net.taobao.tbug.util.UploadUtil;
import net.taobao.tbug.util.readexcel.Read2007Excel;
import net.taobao.tbug.util.readexcel.impl.Read2007ExcelImpl;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/x-msdownload;charset=utf-8");
//		System.out.println(System.getProperty("user.dir"));  // D:\java_tools\eclipse
		
//		request.setAttribute("edit", (new Boolean(request.getParameter("edit"))).booleanValue());
		request.setAttribute("edit", request.getParameter("edit"));
		
		URL base = this.getClass().getResource("");
		Read2007Excel read2007Excel = new Read2007ExcelImpl();
		File file = new File((new File(base.getFile(),"../../../../../../data/").getCanonicalPath()) + "/data.xlsx");
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		
		List<String> navigationList = read2007Excel.getSheetNames(xwb);
		String page = request.getParameter("page"); 
		XSSFSheet sheet = null;
		if(page == null || "".equals(page) || Integer.parseInt(page) < 1){
			sheet = xwb.getSheetAt(0);
			request.setAttribute("pageName", xwb.getSheetName(0));
		} else {
//			String name = new String(page.getBytes("ISO-8859-1"),"utf-8");
//			sheet = xwb.getSheet(name);
			int pageIndex = Integer.parseInt(page) % xwb.getNumberOfSheets() - 1;
			sheet = xwb.getSheetAt(pageIndex);
			String name = xwb.getSheetName(pageIndex);
			request.setAttribute("pageName", name);
		}
		List<Object> contentTitle = read2007Excel.getColumnContent(sheet, 0);
		List<Object> contentText = read2007Excel.getColumnContent(sheet, 1);
		List<Object> apiDocTitle = read2007Excel.getColumnContent(sheet, 2);
		List<Object> apiDocText = read2007Excel.getColumnContent(sheet, 3);
		List<Object> sdkDownloadTitle = read2007Excel.getColumnContent(sheet, 4);
		List<Object> sdkDownloadText = read2007Excel.getColumnContent(sheet, 5);
		List<Object> discussAreaTitle = read2007Excel.getColumnContent(sheet, 6);
		List<Object> discussAreaText = read2007Excel.getColumnContent(sheet, 7);
		
		request.setAttribute("navigationList", navigationList);
		request.setAttribute("contentTitle", contentTitle);
		request.setAttribute("contentText", contentText);
		request.setAttribute("apiDocTitle", apiDocTitle);
		request.setAttribute("apiDocText", apiDocText);
		request.setAttribute("sdkDownloadTitle", sdkDownloadTitle);
		request.setAttribute("sdkDownloadText", sdkDownloadText);
		request.setAttribute("discussAreaTitle", discussAreaTitle);
		request.setAttribute("discussAreaText", discussAreaText);
		
		/**
		 * 获取文件下载列表
		 */
		String name = request.getParameter("file");
		
		if(name != null){
			if("data".equals(name)){
				response.setHeader("Content-Disposition","attachment; filename=data.xlsx");
				OutputStream dataOutputStream = response.getOutputStream();
				DownloadFile.download("data.xlsx", dataOutputStream);
			}else{
//				System.out.println(java.nio.charset.Charset.defaultCharset()); // 获得平台默认字符编码
				String filename = new String(name.getBytes("ISO-8859-1"),"utf-8");
				
				response.setHeader("Content-Disposition","attachment; filename=" + filename + "");
				OutputStream sdkOutputStream = response.getOutputStream();
				DownloadFile.download(filename, sdkOutputStream);
				
				response.setHeader("Content-Disposition","attachment; filename=" + filename + "");
				OutputStream apiOutputStream = response.getOutputStream();
				DownloadFile.download(filename, apiOutputStream);
				
			}
		}else{
			// 取得服务器中已有SDK文件的下載列表
			List<String> sdkListInServer=new ArrayList<String>(); 
			File sdkDir = new File(UploadUtil.getSDKPath());	    
		    String[] sdkChildren = sdkDir.list();
		    if (sdkChildren != null) {
		        for (int i=0; i<sdkChildren.length; i++) {
		        	sdkListInServer.add(sdkChildren[i]);	            
		        }
		    }
		    
		    request.setAttribute("sdkList", sdkListInServer);
		    
		    // 取得服务器中已有SDK文件的下載列表
		 	List<String> apiListInServer=new ArrayList<String>(); 
		 	File apiDir = new File(UploadUtil.getAPIPath());	    
		 	String[] apiChildren = apiDir.list();
		 	if (apiChildren != null) {
		 		for (int i=0; i<apiChildren.length; i++) {
		 			apiListInServer.add(apiChildren[i]);	            
		 		}
		 	}

		    request.setAttribute("apiList", apiListInServer);
		}
		
		// 跳回原頁面
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}

}
