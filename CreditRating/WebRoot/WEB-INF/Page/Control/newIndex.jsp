 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="com.credit.util.WebUtil" %>
	<%@ page import="com.credit.bean.member.User" %>
<%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
<%@ include file="/WEB-INF/Page/Share/state.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/Page/Share/title.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=path%>/Resources/extjs/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/Resources/extjs/resources/css/text.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/Resources/extjs/resources/css/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/Styles/enterpriseScore.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/Styles/index.css" />

<script>
var _ctxPath="<%=request.getContextPath() %>";
var _basePath="<%=request.getScheme() + "://" + request.getHeader("host") +  request.getContextPath()%>";
var userName = "<%=WebUtil.getUser(request).getUserName()%>";
var name = "<%=request.getAttribute("name") %>";
var email = "<%=request.getAttribute("email") %>";
var phone = "<%=request.getAttribute("phone") %>";
var orgUrl = "<%=request.getAttribute("orgUrl") %>";
var logoImage = "<%=request.getAttribute("logoImage") %>";
var proccessImageUrl = "<%=request.getAttribute("proccessImageUrl") %>";
</script>
<script type="text/javascript" src="<%=path%>/Resources/extjs/ext-all.js"></script>
<script type="text/javascript" src="<%=path%>/Resources/extjs/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/app/App.js"></script>
<script type="text/javascript" src="<%=path%>/Resources/bootstrap/js/jquery-1.7.2.min.js" ></script>


<style type="text/css">
   html, body {
        font:normal 12px verdana;
        margin:0;
        padding:0;
        border:0 none;
        overflow:hidden;
        height:100%;
    }
    .x-panel-body p {
        margin:5px;
    }
</style>

</head>
<body>
	<input type=hidden id='username'  value=${userName } />
</body>
</html>
