<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="UTF-8"%>
<%
	List<String> discussAreaTitleList = (List<String>)request.getAttribute("discussAreaTitle");
	List<String> discussAreaTextList = (List<String>)request.getAttribute("discussAreaText");
	String pageName = (String)request.getAttribute("pageName");
%>
<fieldset id="contentIndex" class="contentIndex"
	style="background-color: #f66e00; width: 300px; margin-left: 15px;">
	<legend
		style="font-size: 16px; font-style: inherit; font-weight: bolder; margin: 5px 1px 15px 1px; background-color: #FFFFFF;">
		<b class="pson off"></b><%=pageName %>——讨论区
	</legend>
	<ul class="poltxt" style="margin-top: -10px;">
		<%
					if(discussAreaTitleList!=null){
						for(String str:discussAreaTitleList){	
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
	<ul id="contentWidget1" class="contentWidget">
		<%
					if(discussAreaTitleList!=null){
						for(int i = 0; i < discussAreaTitleList.size(); i++){	
							String title = discussAreaTitleList.get(i);
							String content = discussAreaTextList.get(i);
							if(title == null || "".equals(title)){
								continue;
							} else{
								out.print("<li class='widget color-blue'><form name='widgetForm1' method='post' action='' enctype='multipart/form-data'><div id='title' class='widget-head'><h3>"+ title +"</h3></div><div id='content' class='widget-content'><p>" + content + "</p></div></form></li><br/>");
							}
						}
					} else {
						out.print("<p>暂无内容！</p>");
					}
  					
					%>

	</ul>
</div>
