<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
<%@ include file="/WEB-INF/Page/Share/state.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:ng="http://angularjs.org" lang="zh-cmn-Hans" id="ng-app">

<head>
<%@ include file="/WEB-INF/Page/Share/title.jsp"%>
<link href="<%=path%>/Resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style type="text/css">
body {
	background-color: #f9f9f7;
}
.infodiv {
	height:700px;
	background-image:url("<%=path%>/Images/normal/checksuccess.jpg");
	background-repeat: no-repeat;
	background-position: center;
	background-size: 100% 100%; 
}
hmtl{font-size:62.5%;}
</style>

<script>
	document.oncontextmenu=rightMouse;
	function rightMouse() {
		return false;
	}
</script>
</head>

<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
				<ul class="nav " style="margin-left: 30px;">
					<li><a class="brand" href="http://www.hytcredit.com/portal.php" target="_blank">汇誉通</a></li>
					<li><a href="http://www.hytcredit.com/portal.php" target="_blank"><font color="orange">公告：</font>热烈祝贺汇誉通上线 信用评价互联网第一品牌</a></li>
				</ul>
			<div class="container" style="margin-right: 30px;">
				<ul class="nav  pull-right">
					<li><a href="#" style="color: white;">版本号:3.0</a></li>
					<li><a href="#" style="color: white;">更新日期: 2015-06-02</a></li>
					<li><a href="http://www.hytcredit.com/member.php?mod=register" target="_blank" style="color: white;"><i class="icon-comment" ></i> 官方微博</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container" style="margin-top: 80px;">
		<div class="infodiv" >
			<span style="display:none">${message}</span>
			<div style="padding:33% 0% 0% 6%;font-size:1.33rem;font-weight: 600;">${entName}：</div>
			<div style="padding:3% 50% 0% 6%;margin-right:4%;line-height: 27px;"><font size="3px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;江苏汇誉通数据科技有限公司对贵公司进行信用评价，评定结果为 ${finallevel} 级，特发此证。</b></font></div>	
			<c:if test="${empty encoding}"><div style="padding:9.8% 0% 0% 11.5%;font-size: 0.66rem;font-weight: 600;">-</div></c:if>
			<c:if test="${not empty encoding}"><div style="padding:9.8% 0% 0% 11.5%;font-size: 0.66rem;font-weight: 600;">${encoding}</div></c:if>
			<c:if test="${empty encoding}"><div style="padding-left: 17.5%; height: 11px; line-height: 7px; margin-top: 0px; font-size: 0.66rem; font-weight: 600;">-</div></c:if>
			<c:if test="${not empty encoding}"><div style="padding-left: 17.5%; height: 11px; line-height: 7px; margin-top: 0px; font-size: 0.66rem; font-weight: 600;">${encoding}</div></c:if>
			
			<div style="padding:1.6% 0 0 11.6%; height: 11px; line-height: 7px; margin-top: 0px; font-size: 0.66rem; font-weight: 600;">${reportTime}</div>
			<div style="padding:0.3% 0px 0px 14%; height: 11px; line-height: 7px; margin-top: 0px; font-size: 0.66rem; font-weight: 600;">${reportTimeEn}</div>
			<div style="padding:1.5% 0 0 11.6%; height: 11px; line-height: 7px; margin-top: 0px; font-size: 0.66rem; font-weight: 600;">${usefulTime}</div>
			<div style="padding:0.1% 0px 0px 14%; height: 11px; line-height: 7px; margin-top: 0px; font-size: 0.66rem; font-weight: 600;">${usefulTimeEn}</div>
			<div style="float:left;padding: 0% 8% 0 60%; height: 11px; line-height: 7px; margin:-2% 0 0 2%; font-size: 0.8rem;">${reportTime}</div>
			<div style="float:left;padding: 0% 0% 0 60%; height: 11px; line-height: 7px; margin:-2% -1% 0 24%; font-size: 0.8rem;">${reportTime}</div>
			<div style="weight:15%;height:15%;margin:-12% 0px 0px 37.6%"><img style="weight:100%;height:100%" src="<%=path%>/UploadFiles/CreditRating/Template/report/Twocode.png" /></div>
		</div>
		<div><button class="pull-right"  id="check" name="check">下载电子版报告</button></div>
	</div>

<script src="<%=path%>/Resources/bootstrap/js/jquery-1.7.2.min.js"></script>
<script src="<%=path%>/Resources/bootstrap/js/bootstrap.js"></script>

<script>
	$('#check').click(function () {
		window.open("<%=path%>/user/logon_checksuccess.do?business_no=${business_no}");
     });
</script>
</body>
</html>