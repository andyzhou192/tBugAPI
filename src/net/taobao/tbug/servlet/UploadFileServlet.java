package net.taobao.tbug.servlet;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.taobao.tbug.util.UploadUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * �����ļ��ϴ������Servlet
 * @author sitinspring
 *
 * @date 2008-2-12
 */
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 56890894234786L;

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		request.setCharacterEncoding("UTF-8");
		String tabName = request.getParameter("tab");

		// �ļ��ς�����
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (isMultipart == true) {
			try {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				// �õ����еı�������Ŀǰ��������FileItem
				List<FileItem> fileItems = upload.parseRequest(request);
				Iterator<FileItem> iter = fileItems.iterator();
				
				// ���δ���ÿ������
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					
					if(item.isFormField()){
						// ���item�������ı���
						String name = item.getFieldName();
					    String value = item.getString();
					    System.out.print("������Ϊ:"+name+"����ֵΪ:"+value);
					}
					else{
						// ���item���ļ��ϴ�����
						
						// ����ļ�����·��
						String fileName = item.getName();
						if (fileName != null) {
							File fullFile = new File(item.getName());							
														
							// ����ļ��������ϴ�
							if(fullFile.exists()){
								File fileOnServer = null;
								if(tabName.equals("api")){
									fileOnServer = new File(UploadUtil.getAPIPath(), fullFile.getName());
								} else if(tabName.equals("sdk")){
									fileOnServer = new File(UploadUtil.getSDKPath(), fullFile.getName());
								} else if(tabName.equals("data") && fullFile.toString().endsWith(".xlsx")){
									File oldDataFile = new File(UploadUtil.getDataPath()+"/data.xlsx");
									File newDataFile = new File(UploadUtil.getDataPath()+"/data" + String.valueOf(System.currentTimeMillis()) + ".xlsx");
									oldDataFile.renameTo(newDataFile);
//									fileOnServer = new File(UploadUtil.getDataPath(), fullFile.getName());
									fileOnServer = new File(UploadUtil.getDataPath(), "/data.xlsx");
								}
								item.write(fileOnServer);
								
								System.out.println("�ļ�"+fileOnServer.getName()+"�ϴ��ɹ�");
							}
						}
					}
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("the enctype must be multipart/form-data");
		}
		
		// ȡ�÷������������ļ������d�б�
		List<String> fileListInServer=new ArrayList<String>(); 
		
		File dir;
		if(tabName.equals("api")){
			dir = new File(UploadUtil.getAPIPath());
		} else {
			dir = new File(UploadUtil.getSDKPath());
		}
	    String[] children = dir.list();
	    if (children != null) {
	        for (int i=0; i<children.length; i++) {
	            fileListInServer.add(children[i]);	            
	        }
	    }
	    
	    if(tabName.equals("api")){
	    	request.setAttribute("apiList", fileListInServer);
		} else {
			request.setAttribute("sdkList", fileListInServer);
		}

	    // ����ԭ���
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.htm");
		dispatcher.forward(request, response);
		return;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		doPost(request, response);
	}
}
