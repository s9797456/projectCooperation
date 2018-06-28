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
		var _ctxPath = "<%=request.getContextPath() %>";
		
		function topage(pageNum){
			var form = document.forms[0];
			form.pageNum.value=pageNum;
			form.submit();
		}
		</script>
		
	</head>
	
	<body >
		<%--header start--%>
		<%@ include file="/WEB-INF/Page/Share/header.jsp"%>
		<%--header end--%>
		<div id="zhong"  >
			<%--LeftMenu start--%>
			<%@ include file="/WEB-INF/Page/Share/leftMenuGov.jsp"%>
			<%--LeftMenu end--%>
			<div id="right"  >
					<form name="list" action="<%=path%>/initGovernment/scoringEnterpriseList.do" method="post" >
						<input type="hidden" name="pageNum" value="${pageNum}">
					</form>
					<div style="float: right;margin-right:10%; ">
					<form action="<%=path%>/initGovernment/scoringEnterpriseList.do" method="post" >
						<input type="text" name="searchTitle" id = "searchTitle" placeholder="请输入公司名称" style="height: 33px;padding-left: 15px;" value="${searchTitle}"/>
						<button  type="submit" class="btn btn-primary" style="background-color:#00A0E9; margin-top: -3px;">搜索</button>
					</form>
					<input type="hidden" id="status" value="${status}">
					</div>
					<table width="85%"  style="margin: auto;" >
					<tr>
						<td align="center" > 
							<table class="table table-hover"  width="96%" style="margin-top: 15px">
						      <thead >
						        <tr >
						          <th>序号</th>
						          <th>公司名称</th>
						          <th>联系人</th>
						          <th>联系电话</th>
						          <th>终评等级</th>
						          <th>终评得分 </th>
						        </tr>
						      </thead>
						      <tbody>
						       	<c:choose>
									<c:when test="${pageView.getTotalrecord() <= 0 }">
										<td colspan="6"><div style="color: red; font-weight: bold; text-align: center; margin-top: 2%;">没有符合要求的数据存在</div></td>
									</c:when>
									<c:otherwise>
										<c:forEach items="${pageView.records }" var="entry" varStatus="cou">
											<tr style="text-align:left;border-bottom: 1px solid #eff4f8;">
											    <td>${cou.count}</td>
						          				<td >
						          					<c:if test="${!empty entry.name}"><a href = "<%=path%>/initGovernment/scoringEnterpriseListView.do?entid=${entry.entid}">${entry.name}</a></c:if>
						          					<c:if test="${ empty entry.name}"> - </c:if>
						          				</td>
						          				<td>
						          					<c:if test="${!empty entry.realname}">${entry.realname}</c:if>
						          					<c:if test="${ empty entry.realname}"> - </c:if>
						          				</td>
						          				<td>
						          					<c:if test="${!empty entry.cellphone}">${entry.cellphone}</c:if>
						          					<c:if test="${ empty entry.cellphone}"> - </c:if>
						          				</td>
						          				<td>
						          					<c:if test="${!empty entry.finallevel}">${entry.finallevel}</c:if>
						          					<c:if test="${ empty entry.finallevel}"> - </c:if>
						          				</td>
						          				<td>
						          					<c:if test="${!empty entry.finalscore}">${entry.finalscore}</c:if>
						          					<c:if test="${ empty entry.finalscore}"> - </c:if>
						          				</td>
						        			</tr>
						        		</c:forEach> 
									</c:otherwise>
								</c:choose>
						      </tbody>
						  </table>
				  		<div style="text-align: right;margin: 1% 1% 2% 0px "><%@ include file="/WEB-INF/Page/Share/pagination.jsp" %></div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		
	</body>
	<script src="<%=path %>/Resources/jquery/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/ucenterPublic/Js/ucenterPublic.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/Highcharts-4.1.9/js/highcharts.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/base.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var status = $("#status").val();
		if(status == 1){
			popup("系统错误 为查询到该公司");
		}
	</script>
</html>