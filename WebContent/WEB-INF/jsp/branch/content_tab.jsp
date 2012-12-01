<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="UTF-8"%>
<%
	List<String> contentTitleList = (List<String>)request.getAttribute("contentTitle");
	List<String> contentTextList = (List<String>)request.getAttribute("contentText");
	String pageName = (String)request.getAttribute("pageName");
%>
<fieldset id="contentIndex" class="contentIndex"
	style="background-color: #f66e00; width: 300px; margin-left: 15px;">
	<legend
		style="font-size: 16px; font-style: inherit; font-weight: bolder; margin: 5px 1px 15px 1px; background-color: #FFFFFF;">
		<b class="pson off"></b><%=pageName %>-内容
	</legend>
	<ul class="poltxt" style="margin-top: -10px;">
		<%
					if(contentTitleList!=null){
						for(String str:contentTitleList){	
							if(str != null || "".equals(str)){
								out.print("<li><a href='#content" + contentTitleList.indexOf(str) + "'>"+str+"</a></li>");
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
	<ul id="contentWidget1" class="contentWidget">
		<%
					if(contentTitleList!=null){
						for(int i = 0; i < contentTitleList.size(); i++){	
							String title = contentTitleList.get(i);
							String content = contentTitleList.get(i);
							if(title == null || "".equals(title)){
								continue;
							} else{
								out.print("<li id='content" + i + "' name='content" + i + "' class='widget color-blue'><form name='widgetForm1' method='post' action='' enctype='multipart/form-data'><div id='title' class='widget-head'><h3>"+ title +"</h3></div><div id='content' class='widget-content'><p>" + content + "</p></div></form></li><br/>");
							}
						}
					}
  					
					%>

	</ul>
</div>