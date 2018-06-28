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
			<h2>${entName}--历史存档</h2>
		</div>
		<div class="content">
			<div class="content-left">
				<h3>统一社会信用代码:${uscc!}</h3>
				<h3>公司性质:${entType!}</h3>
				<h3>报告编码:${encoding!}</h3>
				<h3>终评分数:${finalScore!}</h3>
				<h3>评分模型:${model!}</h3>
				<h3>企业成立日期:${setupDate!}</h3>
				<h3>营业开始日期:${startDate!}</h3>
				<h3>电话:${tel!}</h3>
				<h3>人员规模:${scale!}</h3>
			</div>
			<div class="content-right">
				<h3>法定代表人:${legalPerson!}</h3>
				<h3>官网:${website!}</h3>
				<h3>评分时间:${gradeTime!}</h3>
				<h3>终评等级:${finalLevel!}</h3>
				<h3>注册资本:${regiCapital!}</h3>
				<h3>企业发照日期:${issueDate!}</h3>
				<h3>营业结束日期:${endDate!}</h3>
				<h3>邮箱:${email!}</h3>
				
			</div>
			<div class="content-bottom">
			<h3>登记机关:${regisOrg!}</h3>
			<h3>所属行业:${industry!}</h3>
			<h3>企业地址:${address!}</h3>
			<h3>企业简介:${brief!}</h3>
			<h3>经营范围:${businessScope!}</h3>
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