<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
<%@ include file="/WEB-INF/Page/Share/state.jsp"%>
<%@ include file="/WEB-INF/Page/Share/title.jsp"%>
<%@ include file="/WEB-INF/Page/Share/meta.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
 
   <body style="margin: 0px;">
  	<div style="width: 100%;height: 100%">
  		<div style="margin: auto;text-align: center;padding-top: 10%;">
  			<img style="width: 80%;height: 80%;" alt="您没有此功能权限..." src="<%=path %>/Images/nopermission.png">
  		</div>
  	</div>
  </body>
<script type="text/javascript" charset="utf-8">
window.setTimeout(function() {
	window.history.go(-1);
}, 5000);//设置显示时间
</script>
  
</html>
