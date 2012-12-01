<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	List<String> sdkDownloadTitleList = (List<String>)request.getAttribute("sdkDownloadTitle");
	List<String> sdkDownloadTextList = (List<String>)request.getAttribute("sdkDownloadText");
	String pageName = (String)request.getAttribute("pageName");
%>
<fieldset id="contentIndex" class="contentIndex"
	style="background-color: #f66e00; width: 300px; margin-left: 15px;">
	<legend
		style="font-size: 16px; font-style: inherit; font-weight: bolder; margin: 5px 1px 15px 1px; background-color: #FFFFFF;">
		<b class="pson off"></b><%=pageName %>——SDK下载
	</legend>
	<ul class="poltxt" style="margin-top: -10px;">
		<%
					if(sdkDownloadTitleList!=null){
						for(String str:sdkDownloadTitleList){	
							if(str != null || "".equals(str)){
								out.print("<li><a href='javascript:void(0)'>"+str+"</a></li>");
							} else{
								continue;
							}
						}
					}
					%>

	</ul>
</fieldset>
<div id="contentWidgets">
	<form name="widgetForm" action=""></form>

	<%
					if(sdkDownloadTitleList!=null){
						for(int i = 0; i < sdkDownloadTitleList.size(); i++){	
							String title = sdkDownloadTitleList.get(i);
							String content = sdkDownloadTextList.get(i);
							if(title == null || "".equals(title)){
								continue;
							} else{
								out.print("<ul id='contentWidget1' class='contentWidget'><li class='widget color-blue'><form name='widgetForm1' method='post' action='' enctype='multipart/form-data'><div id='title' class='widget-head'><h3>"+ title +"</h3></div><div id='content' class='widget-content'><p>" + content + "</p></div></form></li><br/></ul>");
							}
						}
					}
  					
					%>

	<fieldset style="background-color: silver; width: 95%; margin-left: 15px; margin-top: 20px;">
		<legend style="background-color: yellow;">SDK下载列表</legend>
		<ul>
			<%
						List<String> sdkList = (List<String>)request.getAttribute("sdkList");	
						if(sdkList!=null){
							for(String str:sdkList){	
								out.print("<li><a href='index.htm?file="+str+"'>"+str+"</a></li>");
							}
						}
					%>
		</ul>
	</fieldset>
	<!-- enctype属性为表单定义了MIME编码方式，上传文件的表单enctype属性必须如此设置 -->
	<%
		String edit = (String)request.getAttribute("edit");
		if(edit != null){
			if(edit == "true"){
				out.print("<form method='post' action='" + path + "/UploadFile?tab=sdk' enctype='multipart/form-data' style=' margin-left: 15px; display: none;'><input type='file' name='myfile1' value='浏览文件...' /><input type='submit' value='上传' /></form>");
			} else {
				out.print("<form method='post' action='" + path + "/UploadFile?tab=sdk' enctype='multipart/form-data' style=' margin-left: 15px; display: inline;'><input type='file' name='myfile1' value='浏览文件...' /><input type='submit' value='上传' /></form>");
			}
		}
	%>
</div>
