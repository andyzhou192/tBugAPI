<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
    
    <title>tBug API</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css" charset="UTF-8"></link>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/inettuts.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/inettuts.js.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/main.js" charset="UTF-8"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			$('.pson').click( function(){
				if($(this).hasClass('psoff')) {   
					$(this).removeClass("psoff");
					$(this).parent().parent().find('.poltxt').hide();
				} else {
					if(!$(this).hasClass('psoff')) {		
						$(this).addClass("psoff");
						$(this).parent().parent().find('.poltxt').show();
					}				
				}
			});
			
			$(".contentIndex").mouseover( function(){
			  $(this).addClass("bg");
			}).mouseout(function(){$(this).removeClass("bg")});
		});
	</script>
	<script type="text/javascript">
	     function commitModify(linkEl){
	    	var formEl = $(linkEl).parent().parent();
			//debugger;  chrome断点
			//console.dir(window)  输出对象window
			//console.log(4)  输出日志4
			var title = formEl.find('h3:first').text();
			var content = formEl.find('p:first').text();
			var formName = formEl.attr("name");
			//alert(title + "=" + content);
	    	widgetForm.action='/tBugApi/CommitModifyServlet?formName=' + encodeURIComponent(formName) + '&title=' + title + '&content=' + encodeURIComponent(content);
	    	widgetForm.method='POST';
	    	widgetForm.submit();
	    }
	     
	</script>
</head>
<body bgcolor="#e5e5e5">
	<DIV id="navigation" align="left" style="height: 500px; width: 180px; background-color: #999955; margin-left: 20px; margin-top: 15px; position: absolute;">
		<jsp:include page="branch/left_navigation.jsp"></jsp:include>
	</DIV>

	<DIV id="content" style="height: 100%; width:82%; background-color: #e9e9e9; margin-left: 200px; margin-right:20px; margin-top: 15px; position: absolute;">
		<jsp:include page="branch/top_tab.jsp"></jsp:include>
		
		<DIV id="contentText" align="left" style="display: inline; margin-left: 15px; width: 90%;">
			<jsp:include page="branch/content_tab.jsp"></jsp:include>
		</DIV>
		
		<DIV id="apiDoc" align="left" style="display: none; margin-left: 15px;">
			<jsp:include page="branch/apidoc_tab.jsp"></jsp:include>
		</DIV>
		
		<DIV id="sdkDownload" align="left" style="display: none; margin-left: 15px;">
			<jsp:include page="branch/sdkdownload_tab.jsp"></jsp:include>
		</DIV>
		
		<DIV id="discussArea" align="left" style="display: none; margin-left: 15px;">
			<jsp:include page="branch/discussarea_tab.jsp"></jsp:include>
		</DIV>
	
	</DIV>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.2.6.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-personalized-1.6rc2.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/inettuts.js"></script>
</body>
</html>