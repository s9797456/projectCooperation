<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ref = request.getHeader("REFERER");
%>

<html>
	<head>
		<title>数据备份</title>
	</head>
	
	<script type="text/javascript">
	</script>
		
	<body>
		<p>数据备份${resultStr}！</p>
		<p>${tips}</p>
	</body>
</html>