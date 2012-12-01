<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path = request.getContextPath(); %>
<div align="left" style="font-size: 20px; font-style: inherit; background-color: #e77f22;"><b>导航>></b></div>
<ul id="navigationList">
	<%
		List<String> navigationList = (List<String>)request.getAttribute("navigationList");
		if(navigationList!=null){
			for(int i = 0; i < navigationList.size(); i++){
				String nav = navigationList.get(i);
				int index = i + 1;
				out.print("<li class='navigationItem'><a href='/tBugApi/index.htm?page="+index+"'>"+nav+"</a></li>");
			}
		}
	%>
		
</ul>
		
<%
	String edit = (String)request.getAttribute("edit");
	if(edit != null){
		if(edit != "true"){
			out.print("<a href='index.htm?file=data'>下载Excel</a><form method='post' action='" + path + "/UploadFile?tab=data' enctype='multipart/form-data' style=' margin-left: 5px; display: inline;'><input type='file' name='dataFile' style=' width: 170px;' value='浏览文件...' /><br/><input type='submit' style=' margin-left: 115px;' value='上传' /></form>");
		} 
	}
%>