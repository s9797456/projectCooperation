<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
    <%@ include file="/WEB-INF/Page/Share/state.jsp"%>
    <%@ include file="/WEB-INF/Page/Share/title.jsp"%>
    <%@ include file="/WEB-INF/Page/Share/meta.jsp" %>
<!DOCTYPE >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
		<%-- bootstrap 框架 --%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/Resources/bootstrap/css/bootstrap.css">
		<%-- left 菜单  插件 的css--%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/Resources/ucenterPublic/Css/reset.css">
		<link rel="stylesheet" type="text/css" href="<%=path %>/Resources/ucenterPublic/Css/ucenter.css">
		<%-- header left 的框架css --%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/Styles/Share/frame.css">
		<%-- 本页面的css --%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/Styles/enterprise/main.css">
		<script type="text/javascript">
		var _ctxPath = "<%=request.getContextPath()%>";
		
		function topage(pageNum){
			var form = document.forms[0];
			form.pageNum.value=pageNum;
			form.submit();
		}
		</script>
		
		<style type="text/css">
			.space{
				padding-left: 10px;
			}
			.align{
				text-align:center;
			}
		</style>
	</head>
	
	<body >
		<%--header start--%>
		<%@ include file="/WEB-INF/Page/Share/header.jsp"%>
		<%--header end--%>
		<div id="zhong"  >
			<%--LeftMenu start--%>
			<%@ include file="/WEB-INF/Page/Share/leftMenuOrg.jsp"%>
			<%--LeftMenu end--%>
			<div id="right"  >
				<table width="100%"  cellspacing="1" cellpadding="0" bgcolor="#0A7ACD"  bordercolor="#0A7ACD">
				  <tr>
					<td bgcolor="#FFFFFF" valign="top" align="center"> 
					<br>
					  <%-- 企业基础信息 --%>
						<table width="96%"  border="1" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC"  bordercolor="#CCCCCC">
							<tr > 
							  <td height="30" align = "center">企业名称</td>
							  <td width="36%" class = "space"><c:if test="${ ! empty entBaseInfo.name}">${entBaseInfo.name}</c:if><c:if test="${empty entBaseInfo.name}">-</c:if></td>
							  <td width="15%" align = "center">营业执照号</td>
							  <td width="35%" class = "space"><c:if test="${ ! empty entBaseInfo.uscc}">${entBaseInfo.uscc}</c:if><c:if test="${empty entBaseInfo.uscc}">-</c:if></td>
							</tr>
							<tr > 
							  <td height="30" align = "center">公司性质</td>
							  <td width="36%" class = "space"><c:if test="${ ! empty entBaseInfo.enttype}">${entBaseInfo.enttype}</c:if><c:if test="${empty entBaseInfo.enttype}">-</c:if></td>
							  <td width="15%" align = "center">企业地址</td>
							  <td width="35%" class = "space"><c:if test="${ ! empty entBaseInfo.address}">${entBaseInfo.address}</c:if><c:if test="${empty entBaseInfo.address}">-</c:if></td>
							</tr>
							<tr > 
							  <td height="30" align = "center">注册资本</td>
							  <td width="36%" class = "space"><c:if test="${ ! empty entBaseInfo.regicapital}">${entBaseInfo.regicapital}</c:if><c:if test="${empty entBaseInfo.regicapital}">-</c:if></td>
							  <td width="15%" align = "center">币种</td>
							  <td width="35%" class = "space"><c:if test="${ ! empty entBaseInfo.currencytype}">${entBaseInfo.currencytype}</c:if><c:if test="${empty entBaseInfo.currencytype}">-</c:if></td>
							</tr>
							<tr > 
							  <td height="30" align = "center">法人代表</td>
							  <td width="36%" class = "space"><c:if test="${ ! empty entBaseInfo.legalperson}">${entBaseInfo.legalperson}</c:if><c:if test="${empty entBaseInfo.legalperson}">-</c:if></td>
							  <td width="15%" align = "center">登记机关</td>
							  <td width="35%" class = "space"><c:if test="${ ! empty entBaseInfo.regisorg}">${entBaseInfo.regisorg}</c:if><c:if test="${empty entBaseInfo.regisorg}">-</c:if></td>
							</tr>
							<tr > 
							  <td height="30" align = "center">企业成立日期</td>
							  <td width="36%" class = "space"><c:if test="${ ! empty entBaseInfo.setupdate}"><fmt:formatDate value="${entBaseInfo.setupdate}" pattern="yyyy-MM-dd" /></c:if><c:if test="${empty entBaseInfo.setupdate}">-</c:if></td>
							  <td width="15%" align = "center">企业发照日期</td>
							  <td width="35%" class = "space"><c:if test="${ ! empty entBaseInfo.issuedate}"><fmt:formatDate value="${entBaseInfo.issuedate}" pattern="yyyy-MM-dd" /></c:if><c:if test="${empty entBaseInfo.issuedate}">-</c:if></td>
							</tr>
							<tr > 
							  <td height="30" align = "center">营业开始日期</td>
							  <td width="36%" class = "space"><c:if test="${ ! empty entBaseInfo.startdate}"><fmt:formatDate value="${entBaseInfo.enddate}" pattern="yyyy-MM-dd" /></c:if><c:if test="${empty entBaseInfo.startdate}">-</c:if></td>
							  <td width="15%" align = "center">营业结束日期</td>
							  <td width="35%" class = "space"><c:if test="${ ! empty entBaseInfo.enddate}"><fmt:formatDate value="${entBaseInfo.enddate}" pattern="yyyy-MM-dd" /></c:if><c:if test="${empty entBaseInfo.enddate}">-</c:if></td>
							</tr>
							<tr > 
							  <td height="30" align = "center">联系电话</td>
							  <td width="36%" class = "space"><c:if test="${ ! empty entBaseInfo.tel}">${entBaseInfo.tel}</c:if><c:if test="${empty entBaseInfo.tel}">-</c:if></td>
							  <td width="15%" align = "center">电子邮箱</td>
							  <td width="35%" class = "space"><c:if test="${ ! empty entBaseInfo.email}">${entBaseInfo.email}</c:if><c:if test="${empty entBaseInfo.email}">-</c:if></td>
							</tr>
							<tr > 
							  <td height="30" align = "center">企业官网</td>
							  <td width="36%" class = "space"><c:if test="${ ! empty entBaseInfo.website}">${entBaseInfo.website}</c:if><c:if test="${empty entBaseInfo.website}">-</c:if></td>
							  <td width="15%" align = "center">地区邮编</td>
							  <td width="35%" class = "space"><c:if test="${ ! empty entBaseInfo.zipcode}">${entBaseInfo.zipcode}</c:if><c:if test="${empty entBaseInfo.zipcode}">-</c:if></td>
							</tr>
							<tr > 
							  <td height="30" align = "center">所属行业</td>
							  <td width="36%" class = "space"><c:if test="${ ! empty entBaseInfo.industry}">${entBaseInfo.industry}</c:if><c:if test="${empty entBaseInfo.industry}">-</c:if></td>
							  <td width="15%" align = "center">人员规模</td>
							  <td width="35%" class = "space"><c:if test="${ ! empty entBaseInfo.scale}">${entBaseInfo.scale}</c:if><c:if test="${empty entBaseInfo.scale}">-</c:if></td>
							</tr>
							<tr > 
							  <td height="30" align = "center">经营范围</td>
							  <td colspan="3" class = "space"><c:if test="${ ! empty entBaseInfo.businessscope}">${entBaseInfo.businessscope}</c:if><c:if test="${empty entBaseInfo.businessscope}">-</c:if> </td>
							</tr>
							<tr > 
							  <td height="30" align = "center">企业简介</td>
							  <td colspan="3" class = "space"><c:if test="${ ! empty entBaseInfo.brief}">${entBaseInfo.brief}</c:if><c:if test="${empty entBaseInfo.brief}">-</c:if> </td>
							</tr>
					 </table>
					 
				  <table width="96%" border="0" cellspacing="0" cellpadding="0" height="20">
					<tr> 
					  <td></td>
					</tr>
				  </table>
				  
				  <%-- 股东信息 --%>
				
				<c:if test="${! empty shareholders}">
				<table width="96%" border="1" cellspacing="1" cellpadding="0" bordercolor="#CCCCCC">
					<tr bgcolor="#ddd"> 
					  <td height="30" align = "center" width="14%">股东姓名</td>
					  <td height="30" align = "center" width="14%">股东类型</td>
					  <td height="30" align = "center" width="14%">认缴时间</td>
					  <td height="30" align = "center" width="14%">认缴金额</td>
					  <td height="30" align = "center" width="14%">实缴时间</td>
					  <td height="30" align = "center" width="14%">实缴金额</td>
					  <td height="30" align = "center" width="14%">占股比例</td>
					</tr>
					<c:forEach var="entity" items="${shareholders }">
					<tr > 
					   <td height="30" align = "center"><c:if test="${ ! empty entity.name}">${entity.name }</c:if><c:if test="${empty entity.name}">-</c:if></td>
					   <td height="30" align = "center" ><c:if test="${ ! empty entity.type}">${entity.type }</c:if><c:if test="${empty entity.type}">-</c:if></td>
					   <td height="30" align = "center"><c:if test="${ ! empty entity.shouldtime}"><fmt:formatDate value="${entity.shouldtime }" pattern="yyyy-MM-dd" /></c:if><c:if test="${empty entity.shouldtime}">-</c:if></td>
					   <td height="30" align = "center"><c:if test="${ ! empty entity.shouldcapi}">${entity.shouldcapi }</c:if><c:if test="${empty entity.shouldcapi}">-</c:if></td>
					   <td height="30" align = "center"><c:if test="${ ! empty entity.realtime}"><fmt:formatDate value="${entity.realtime }" pattern="yyyy-MM-dd" /></c:if><c:if test="${empty entity.realtime}">-</c:if></td>
					   <td height="30" align = "center"><c:if test="${ ! empty entity.realcapi}">${entity.realcapi }</c:if><c:if test="${empty entity.realcapi}">-</c:if></td>
					   <td height="30" align = "center"><c:if test="${ ! empty entity.stockpercent}">${entity.stockpercent }</c:if><c:if test="${empty entity.stockpercent}">-</c:if></td>
					</tr>
					</c:forEach>
				</table>
				</c:if>
				  
				  <table width="96%" border="0" cellspacing="0" cellpadding="0" height="20">
					<tr> 
					  <td></td>
					</tr>
				  </table>
				  
				    <%-- 高管信息 --%>
				<c:if test="${! empty executives}">
				<table width="96%" border="1" cellspacing="1" cellpadding="0" bordercolor="#CCCCCC">
					<tr bgcolor="#ddd"> 
					  <td height="30" align = "center" width="14%">高管姓名</td>
					  <td height="30" align = "center" width="14%">性别</td>
					  <td height="30" align = "center" width="14%">年龄</td>
					  <td height="30" align = "center" width="14%">职位</td>
					  <td height="30" align = "center" width="14%">部门</td>
					  <td height="30" align = "center" width="14%">学历</td>
					  <td height="30" align = "center" width="14%">证件号</td>
					</tr>
					<c:forEach var="entity" items="${executives }">
					<tr > 
					   <td height="30" align = "center"><c:if test="${ ! empty entity.name}">${entity.name }</c:if><c:if test="${empty entity.name}">-</c:if></td>
					   <td height="30" align = "center"><c:if test="${ ! empty entity.gender}">${entity.gender }</c:if><c:if test="${empty entity.gender}">-</c:if></td>
					   <td height="30" align = "center"><c:if test="${ ! empty entity.age}">${entity.age }</c:if><c:if test="${empty entity.age}">-</c:if></td>
					   <td height="30" align = "center"><c:if test="${ ! empty entity.job}">${entity.job }</c:if><c:if test="${empty entity.job}">-</c:if></td>
					   <td height="30" align = "center"><c:if test="${ ! empty entity.department}">${entity.department }</c:if><c:if test="${empty entity.department}">-</c:if></td>
					   <td height="30" align = "center"><c:if test="${ ! empty entity.education}">${entity.education }</c:if><c:if test="${empty entity.education}">-</c:if></td>
					   <td height="30" align = "center"><c:if test="${ ! empty entity.idcard}">${entity.idcard }</c:if><c:if test="${empty entity.idcard}">-</c:if></td>
					</tr>
					</c:forEach>
				</table>
				</c:if>
				  
				  <table width="96%" border="0" cellspacing="0" cellpadding="0" height="20">
					<tr> 
					  <td></td>
					</tr>
				  </table>
				  
				 <%-- 模型信息 --%>
				<c:if test="${! empty indexs}">
				<table width="96%" border="1" cellspacing="1" cellpadding="0" bordercolor="#CCCCCC">
					<thead>
					<tr bgcolor="#ddd"> 
					  <td height="30" align = "center">指标名称</td>
					  <td height="30" align = "center">指标项</td>
					  <td height="30" align = "center">指标得分</td>
					  <td height="30" align = "center" width="4%" bgcolor="#c3c3c3" ><a id = "displayTable" href = "javascript:displayTable()">收起</a></td>
					</tr>
					</thead>
					<tbody id = "displayTables">
					<c:forEach var="entity" items="${indexs }" >
					<tr > 
					   <td height="30" align = "center"><c:if test="${ ! empty entity.indexName}">${entity.indexName }</c:if><c:if test="${empty entity.indexName}">-</c:if></td>
					   <td height="30" align = "center"><c:if test="${ ! empty entity.optionName}">${entity.optionName}</c:if><c:if test="${empty entity.optionName}">-</c:if></td>
					   <td height="30" align = "center" colspan="2"><c:if test="${ ! empty entity.indexScore}">${entity.indexScore}</c:if><c:if test="${empty entity.optionName}">-</c:if></td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
				</c:if>
				</td>
			  </tr>
			</table>
				<div id="exit"  style="text-align: right; margin:2%;">
					<a  href="javascript:history.go(-1)" class="btn btn-primary" style="background-color: #00A0E9">返回</a>
				</div>
			</div>
		</div>
		
		
	</body>
	<script src="<%=path %>/Resources/jquery/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/ucenterPublic/Js/ucenterPublic.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/Highcharts-4.1.9/js/highcharts.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/base.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		function displayTable(){
			if($("#displayTable").text() == "收起"){
				$("#displayTables").attr("style","display: none;");
				$("#displayTable").text("展开")
			}else{
				$("#displayTables").attr("style","");
				$("#displayTable").text("收起")
			}
			
		}
	</script>
</html>