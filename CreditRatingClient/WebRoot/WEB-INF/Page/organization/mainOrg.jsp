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
		</script>
		<style type="text/css">
			#userInfo>div{
				 float: left;
				 width: 13%;
				 height: 100%;
				 text-align: center;
				 background-color: rgb(0, 160, 233);
				 margin-left: 1%;
				 border-radius: 10px;
				 box-shadow: 10px 5px 20px rgb(212, 212, 212);
			}
			#userInfo>div>p{
				color:#fff;
				
			}
			.font {
				font-style:normal;
				font-family:微软雅黑,黑体;
				font-size: 5em;
				height: 65%;
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
			<div id="right" style="margin-top: 20px;" >
				<!--右侧 内容部分-->
						<!--右侧 内容 上面基础信息部分-->
					<div style="margin-bottom: 5%;">
						<%-- <h4 style="margin-left: 50px">
						<c:if test="${! empty Crc_customer.username}">${Crc_customer.username}</c:if>
						<c:if test="${empty Crc_customer.username}">尊敬的</c:if>
						 &nbsp;用户 , 您好...</h4>--%>
							<br>
							<h4 style="margin-left: 2.5vw">企业评分状态汇总</h4>
						<div id ="userInfo" >
							<%--正在填报信息 <div style="text-shadow:5px 2px 6px #000;">&nbsp;</div>--%>
							<div  style="margin-left: 2%;">
								<p class = "font notApplied">0</p>
								<p>正在填报信息</p>
							</div>
							
							<div >
								<p class = "font confirmation">0</p>
								<p>正在确认填报信息</p>
							</div>
							
							<div >
								<p class = "font editorialSurvey">0</p>
								<p>正在编辑调查</p>
							</div>
							
							<div>
								<p class = "font endInvestigation">0</p>
								<p>已经结束调查</p>
							</div>
							
							<div >
								<p class = "font preliminaryConfirmation">0</p>
								<p>用户确认初评</p>
							</div>
							
							<div>
								<p class = "font score">0</p>
								<p>正在进行评分</p>
							</div>
							
							<div>
								<p class = "font endScore">0</p>
								<p>评分结束</p>
							</div>
						</div>
						<br>
						<div style=" margin: 20px 0px 0px 50px;min-width: 600px; height: 40vh; min-height: 185px; margin-left: 2.5vw;width: 82vw;">
							<h4 >企业得分分布情况</h4>
							<br>
							<div id = "main" style="height: 90%;width: 97%;margin: auto;"></div>
						</div>
					</div >
					
					<!--右侧 内容 上面基础信息部分   end-->
				
				<%--foot start--%>
					<%@ include file="/WEB-INF/Page/Share/foot.jsp"%>
				<%--foot end--%>
			</div>
		</div>
		
		
	</body>
	<script src="<%=path %>/Resources/jquery/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/ucenterPublic/Js/ucenterPublic.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/Highcharts-4.1.9/js/highcharts.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/base.js" type="text/javascript" charset="utf-8"></script>
	<script language="JavaScript">
	 var sysChart = new Highcharts.Chart({
	        chart: {
	        	renderTo : 'main',
	        },
	        title: {
	            text: '企业分数分布'
	        },
	        xAxis : {
				minRange: 60
			},
			yAxis : {
				min : 0,
				title : {
					text : '企业数（个）'
				}
			},
	     	// 右下角设置
			credits : {
				enabled : false
			},
			tooltip : {
				headerFormat : '<span style="font-size:10px">{point.key}</span><table width=150px>',
				pointFormat : '<tr><td style="color:{series.color};padding:0">企业数: </td>'
						+ '<td style="padding:0"><b>{point.y} </b></td></tr>',
				footerFormat : '</table>',
				shared : true,
				useHTML : true
			},
	        series : [ {
				name : '分数',
				data : []
			}]
	    });
	 $.ajax({
			type : "post",
			url: "<%=path%>/initOrganization/getCountScore.do",
			dataType : "json",
			success : function(data) {
				if(data.status == true){
					var sysStatisticsCharts = data.ChartVos;
					xData = []; // x轴名
					value = []; // 
					for ( var i in sysStatisticsCharts) {
						value = [];
						value.push(parseFloat(sysStatisticsCharts[i].descp));
						value.push(parseFloat(sysStatisticsCharts[i].cunt));
						xData.push(value);
					}
					sysChart.series[0].setData(xData);// 设置Y坐标的值
				}else{
					popup("获取数据失败1，请稍后再试  ！   网络错路：204");
				}
				
			 },
			error : function(e) {
				popup(e);
			}
		});
	 
	 $.ajax({
			type : "post",
			url: "<%=path%>/initOrganization/summaryscoring.do",
			dataType : "json",
			success : function(data) {
				if(data.status == true){
					if(data.notApplied != null && "" != data.notApplied){
						$(".notApplied").text(data.notApplied);
					}
					if(data.confirmation != null && "" != data.confirmation){
						$(".confirmation").text(data.confirmation);
					}
					if(data.editorialSurvey != null && "" != data.editorialSurvey){
						$(".editorialSurvey").text(data.editorialSurvey);
					}
					if(data.endInvestigation != null && "" != data.endInvestigation){
						$(".endInvestigation").text(data.endInvestigation);
					}
					if(data.preliminaryConfirmation != null && "" != data.preliminaryConfirmation){
						$(".preliminaryConfirmation").text(data.preliminaryConfirmation);
					}
					if(data.score != null && "" != data.score){
						$(".score").text(data.score);
					}
					if(data.endScore != null && "" != data.endScore){
						$(".endScore").text(data.endScore);
					}
				}else{
					popup("获取数据失败    网络错路：204");
				}
				
			 },
			error : function(e) {
				alert(e);
			}
		});
</script>
</html>