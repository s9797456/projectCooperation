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
	<style type="text/css">
	@media screen and (max-width: 1480px) {
				.container{
					margin-left: 0vw;
					background-color: white;
					height: auto;
					margin: auto;
					text-align: center;
					padding:0px;
				}
	}
		@media screen and (min-width: 1480px) {
				.container{
					margin-left: 20vw;
					background-color: white;
					height: auto;
					margin: auto;
					text-align: center;
					padding-bottom: 150px;
				}
	}
			
			.preliminary,.credit,.suggestions {
				display: inline-block;
				height: auto;width: 70%;
				text-align: left;
			}
			.base {
				width: 50vw;height: 40px;
				margin-bottom: 10px;
			}
			.preliminary span,.credit span,.suggestions  span{
				margin-left: 15px;
			}
			h4{
				min-width: 150px;
				width: 20%;
				margin: 20px 10px 10px 0px;
			}
	</style>
	<script>
		var _ctxPath="<%=request.getContextPath() %>";
	</script>
	</head>
	<body>
		<!--头-->
		<%--header start--%>
		<%@ include file="/WEB-INF/Page/Share/header.jsp"%>
		<%--header end--%>
		<%--LeftMenu start--%>
		<%@ include file="/WEB-INF/Page/Share/leftMenuPer.jsp"%>
		<%--LeftMenu end--%>
		<div id="right" style="margin-top: -5px;">
			 <div id="rightView" class="container" style="width: auto;">
			 <br>
				<div class = "preliminary ">
					<h4><b>初评信息</b></h4>
					<div class="base">
						<div style="float: left;min-width: 150px;width:20vw;">
							<span> 初评等级 </span>
							<span style="color: #258AC2;font-size: 25px;display: inline-block;"><c:if test="${! empty perResult.preliminarylevel}">${perResult.preliminarylevel}</c:if><c:if test="${ empty perResult.preliminarylevel}">暂未获得</c:if></span>
						</div>
						<div style="float: left;min-width: 150px;width:20vw;">
							<span> 初评分数 </span>
							<span style="color: #258AC2;font-size: 25px;display: inline-block;"><c:if test="${! empty perResult.preliminaryscore}">${perResult.preliminaryscore}</c:if><c:if test="${empty perResult.preliminaryscore}">暂未获得</c:if></span>
						</div>
						
					</div>
						<span style="float: left;width:40vw;"> 等级说明 </span>
						<div style="float: left;width: 75%;margin-left: 5%;font-style:italic;font-family: '微软雅黑';" >
							${explain }
						</div>
				</div>
				<br />
				<c:if test="${perProcess.scorestate == 5 && perProcess.applyreportstate == 1 }">
				<div class = "preliminary ">
					<h4><b>终评信息</b></h4>
					<div class="base">
						<div style="float: left;min-width: 150px;width:20vw;">
							<span> 终评等级 </span>
							<span style="color: #258AC2;font-size: 25px;display: inline-block;"><c:if test="${! empty perResult.finallevel}">${perResult.finallevel}</c:if><c:if test="${ empty perResult.finallevel}">暂未获得</c:if></span>
						</div>
						<div style="float: left;min-width: 150px;width:20vw;">
							<span> 终评分数 </span>
							<span style="color: #258AC2;font-size: 25px;display: inline-block;"><c:if test="${! empty perResult.finalscore}">${perResult.finalscore}</c:if><c:if test="${empty perResult.finalscore}">暂未获得</c:if></span>
						</div>
						
					</div>
				</div>
				</c:if>
				<div class = "credit ">
					<h4><b>信用信息</b></h4>
					<table class="table table-hover" style="width:90%;margin-left: 15px;">
						<c:if test="${empty indexRates }"><td>暂无信用信息</td></c:if>
							<c:if test="${ ! empty indexRates}">
							<!-- <caption>边框表格布局</caption> -->
							<thead>
								<tr style="text-align: left;">
									<td >序号</td>
									<td style="min-width: 50px;">一级指标</td>
									<td >得分比重</td>
									<td >备注</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="entit" items="${indexRates }" varStatus="cou">
									<tr>
										<td>${cou.count }</td>
										<td>
											<c:if test="${! empty entit.indexName }">${entit.indexName }</c:if>
											<c:if test="${empty entit.indexName }"> -- </c:if>
										</td>
										<td>
											<c:if test="${! empty entit.rateValue }">${entit.rateValue }</c:if>
											<c:if test="${empty entit.rateValue }"> -- </c:if>
										</td>
										<td>
											<c:if test="${! empty entit.description }">${entit.description }</c:if>
											<c:if test="${empty entit.description }"> -- </c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							</c:if>
						</table>
				</div>
				<c:if test="${perProcess.scorestate <= 3 && perProcess.applyreportstate == 1 }">
				<div class = "suggestions ">
					<h4><b>意见反馈</b></h4>
					
				<c:if test="${perProcess.scorestate == 3 && perProcess.applyreportstate == 1 }">
				<form action="<%=path %>/initPersonal/feedbackEvaluation.do" method="post">
						
					<div class="base">
						<div style="float: left;">
							<span> 反馈次数</span>
							<span style="color: #258AC2;font-size: 25px;"><c:if test="${! empty perOpinion.feekbacknum}">${perOpinion.feekbacknum}</c:if><c:if test="${empty perOpinion.feekbacknum}">0</c:if></span>
						</div>
						<div style="float: right;margin-right: 21%;">
							<span>  是否同意 </span>
							<span style="color: #258AC2;">
							<input type="radio" name="isconfirm" checked="" value="1"/>同意
							</span>
							<span style="color: #258AC2;">
							<input type="radio" name="isconfirm" value="0"/>不同意
							</span>
						</div>
						
					</div>
					<div >
							<span style="float: left;"> 反馈意见 </span>
							<textarea name = "opinion" style="float: left;width:84%;margin-left: 15px;border: 1px solid #ddd;" ></textarea>
					</div>
				<div class = "preliminary form-actions" style="margin-top: 15px; margin-left:20%;">
					<button class="btn btn-primary" type="submit" style="float: left;margin-left: 88%">提交</button>
				</div>
			</form>
			</c:if>
			<c:if test="${perProcess.scorestate != 3 && perProcess.applyreportstate != 1 }">
				<table class="table table-hover" style="width:90%;margin-left: 15px;">
					<td>功能暂不能使用,请提交申请后等待初评结果。</td>
				</table>
			</c:if>
			</div>
				</c:if>
				
	  </div>
	 </div>
		
	</body>
	<script src="<%=path %>/Resources/jquery/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/ucenterPublic/Js/ucenterPublic.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/base.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/personalBase.js" type="text/javascript" charset="utf-8"></script>
</html>