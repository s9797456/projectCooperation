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
  <c:if test="${error == '404'}">
   <body style="margin: 0px;">
  	<div style="width: 100%;height: 100%">
  		<div style="margin: auto;text-align: center;padding-top: 10%;">
  			<img style="width: 50%;height: 50%;" alt="系统维护中..." src="<%=path %>/Images/404error.png">
  		</div>
  	</div>
  </body>
  </c:if>
  <c:if test="${error == '000'}">
  <body style="margin: 0px;">
  	<div style="width: 100%;height: 100%">
  		<div style="margin: auto;text-align: center;padding-top: 10%;">
  			<img style="width: 20%;height: 50%;" alt="系统维护中..." src="<%=path %>/Images/000error.png">
  		    <h2>${msg }</h2>
  		</div>
  	</div>
  </body>
  </c:if>
  <c:if test="${error == '001'}">
  <body style="margin: 0px;">
  	<div style="width: 100%;height: 100%">
  		<div style="margin: auto;text-align: center;padding-top: 10%;">
  			<img style="width: 20%;height: 50%;" alt="系统维护中..." src="<%=path %>/Images/000error.png">
  			<h2>${msg }</h2>
  		</div>
  	</div>
  </body>
  </c:if>
  <c:if test="${error == '501'}">
  <body style="margin: 0px;">
  	<div style="width: 100%;height: 100%">
  		<div style="margin: auto;text-align: center;padding-top: 10%;">
  			<img style="width: 20%;height: 50%;" alt="系统维护中..." src="<%=path %>/Images/000error.png">
  			<h2>${msg }</h2>
  		</div>
  	</div>
  </body>
  </c:if>
</html>
