<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<title></title>
<style type="text/css">
body {
	font-family: pingfang sc light;
}

.page {
	font-weight: bold;
}

.title {
	text-align: center;
	width: 100%;
	height: 100px;
}

.content {
	text-align: left;
	width: 100%;
	height: auto;
}

.content-left {
	width: 50%;
	float: left;
	padding-left: 50px;
}

.content-right {
	width: 85%;
	float: right;
}
.content-bottom{
	width: 100%;
	padding-left: 50px;
	padding-right:50px;
}
.index_table{
	width: 80%;
	padding-left: 30px;
}
table,th, td {
        border: 1px solid black;
        border-spacing: 0px;
        padding: 10px;
        }
</style>
</head>
<body>
	<!--第一页开始-->
	<div class="page">
		<div style="width:100%;height:20px;"></div>
		<div class="title">
			<h2>${name}--历史存档</h2>
		</div>
		<div class="content">
			<div class="content-left">
				<h3>姓名:${name!}</h3>
				<h3>曾用名:${usedName!}</h3>
				<h3>民族:${nation!}</h3>
				<h3>国籍:${nationality!}</h3>
				<h3>婚姻状况:${maritalStatus!}</h3>
				<h3>报告编码:${encoding!}</h3>
				<h3>终评分数:${finalScore!}</h3>
				<h3>评分模型:${model!}</h3>
			</div>
			<div class="content-right">
				<h3>身份证号:${IDCard!}</h3>
				<h3>性别:${gender!}</h3>
				<h3>政治面貌:${politicalOutlook!}</h3>
				<h3>籍贯:${nativePlace!}</h3>
				<h3>生育情况:${fertilityCondition!}</h3>
				<h3>现居住地邮编:${presentZipCode!}</h3>
				<h3>评分时间:${gradeTime!}</h3>
				<h3>终评等级:${finalLevel!}</h3>
			</div>
			<div class="content-bottom">
			<h3>户籍地址:${permanentAddress!}</h3>
			<h3>现居住地址:${presentAddress!}</h3>
			<h3>身份证签发机关:${IDIssuingAgency!}</h3>
			<h3>身份证有效期:${IDTermStart!}至${IDTermEnd!}</h3>
			</div>
		</div>

	</div>
	<!--第一页结束-->
<!---分页标记-->
<span style="page-break-after:always;"></span>
<!--第二页开始-->
	<div class="page">
		<div class="index_table">
		<table>
			<tr>
				<th style="width:42%">指标</th>
				<th style="width:8%">分数</th>
				<th style="width:40%">取档</th>
			</tr>
			<#list indexs as item>
			<tr>
				<td>${item.indexName! }</td>
				<td>${item.indexScore! }</td>
				<td>${item.optionName! }</td>
			</tr>
			</#list>
		</table>
		</div>
	</div>
</body>
</html>