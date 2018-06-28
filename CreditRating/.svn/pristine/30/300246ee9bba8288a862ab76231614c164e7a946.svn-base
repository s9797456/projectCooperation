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
		<form action="<%=path%>/initial/backup/backup.do" method="post">
			单击备份数据库中的数据
			<input type="submit" value="备份数据" >
		</form>
	</body>
</html>