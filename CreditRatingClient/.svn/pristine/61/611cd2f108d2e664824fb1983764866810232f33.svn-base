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
		<%-- left 菜单  插件 的css--%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/Resources/fullPage/css/jquery.fullPage.css">
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
				 font-size:2rem;
			}
			#userInfo>div>p{
				color:#fff;
				
			}
			.font {
				font-style:normal;
				font-family:微软雅黑,黑体;
				font-size: 5.7rem;
				height: 65%;
			}
			.progress-bar{
				background-color:rgb(0, 160, 233);
				
			}
			.progress{
				margin-top: 10px; margin-bottom: 10px;
			}
			.table > tbody > tr > td{
				line-height: 40px;
			}
			.outer{
			width: 100%;
			height: 7%
			}
			.inner{
			width: 5px;
			height: 10px;
			float: left;
			margin: 3% 6%
			}
			.liOne{
			font-size: 1.8rem;
			padding-left:54%
			}
			.liTwo{
			color:white;
			font-size:1.5rem;
			margin-top:3%
			}
			.liThree{
			color:white;
			font-size:1.5rem;
			margin-top:3vh;
			}
			.region {
				height: 4.5vh;
				line-height: 4.5vh;
			}
			.region >div {
				float: left;
			}
			.region1{
				width: 30%;
				margin-left: 10%;
			}
			.region2{
				width: 20%;
			}
			.region3{
				width: 35%;
			}
			#menu { margin: 0; padding: 0; position: fixed; left: 300px; top: 10px; list-style-type: none; z-index: 70;}
			#menu li { float: left; margin:  0 10px 0 0; font-size: 14px;}
			#menu a { float: left; padding: 10px 20px; background-color: #fff; color: #333; text-decoration: none;}
			#menu .active a { color: #fff; background-color: #b0b0b0;}
		</style>
	</head>
	
	<body>
		<%--header start--%>
		<%@ include file="/WEB-INF/Page/Share/header.jsp"%>
		<%--header end--%>
		<div id="zhong"  >
			<%--LeftMenu start--%>
			<%@ include file="/WEB-INF/Page/Share/leftMenuGov.jsp"%>
			<%--LeftMenu end--%>
			<div id="right"  >
				<!--右侧 内容部分-->
				<%--总体概况    分布统计   红黑榜--%>
				<ul id="menu">
					<li data-menuanchor="page1" class="active"><a href="#page1">总体概况</a></li>
					<li data-menuanchor="page2"><a href="#page2">分布统计</a></li>
					<li data-menuanchor="page3"><a href="#page3">红黑榜</a></li>
				</ul>
						<!--右侧 内容 上面基础信息部分-->
					<div id = "ido" style="margin-bottom: 10%;">
					<div class = "section ">
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
						<div style="margin: 10px 0px 0px 50px;min-width: 600px; height: 61vh; min-height: 300px; margin-left: 2.5vw;width: 82vw;">
							<div style="float: left; height: 100%; width: 30%; margin: auto;overflow-x:auto  " id = "enterprisesNumber">
								<div class = "modal-overlay">
								    <div class="loader-inner line-scale-pulse-out" style="margin-top: 20%;">
								      <div></div>
								      <div></div>
								      <div></div>
								      <div></div>
								      <div></div>
								    </div>
								</div>
							</div>
						<div style="position:relative;float:left;height:100%;width:70%;margin:auto;background-image:url('<%=path %>/Images/grid.png');background-repeat:no-repeat;background-size: 100% 100%">
							<img src="<%=path %>/Images/map2.png" style="margin-left:20%;width:70%;height:100%;background-repeat:no-repeat;"/>
							<div style="min-width:90px;background:white;color:black;width:20%;height:80%;position:absolute;top:10%;bottom:10%;background: rgba(255, 255, 255, 0.36);">
								<ul>
									<li class="liThree"><div class="outer"><div class="inner" style="background-color: red"></div><div style="float: left;">企业数量</div></div></li>
									<li class="liOne" style="color:red;" id = "enterpriseNumber">9649</li>
									<li class="liTwo"><div class="outer"><div class="inner" style="background-color: yellow"></div><div style="float: left;">信用服务机构数量</div></div></li>
									<li class="liOne" style="color:yellow"id = "penaltyNumber1">37</li>
									<li class="liTwo"><div class="outer"><div class="inner" style="background-color: green"></div><div style="float: left;">已服务企业数量</div></div></li>
									<li class="liOne" style="color:green" id = "evaluatedEnterprise">7649</li>
									<li class="liTwo"><div class="outer"><div class="inner" style="background-color: blue"></div><div style="float: left;">3A企业数量</div></div></li>
									<li class="liOne" style="color:blue" id = "enterprises3A">2649</li>
									<li class="liTwo"><div class="outer"><div class="inner" style="background-color: orange"></div><div style="float: left;">行政处罚企业数量</div></div></li>
									<li class="liOne" style="color:orange" id = "penaltyNumber" >0</li>
								</li>
							</div>
						</div>
						</div>
						</div>
						<div class = "section ">
						<div style=" margin-left:50px;min-width: 600px; height: 40vh; min-height: 185px; margin-left: 2.5vw;width: 82vw;">
							<h4 > </h4>
							<br>
							<div id = "main1" style="height: 90%;width: 97%;margin: 20px auto;"></div>
						</div>
						<br>
						<div  style=" margin: 20px 0px 0px 50px;min-width: 600px; height: 40vh; min-height: 285px; margin-left: 2.5vw;width: 82vw;">
							<div style="float: left;height: 90%;width: 47%;margin: auto;">
								<h4 >企业得分分布情况</h4>
								<br>
								<div id = "main2" style="height: 90%;width: 97%;margin: auto;"></div>
							</div>
							<div style="float: left;height: 90%;width: 47%;margin: auto;">
								<h4 >行政处罚分布情况</h4>
								<br>
								<div id = "main3" style="height: 90%;width: 97%;margin: auto;"></div>
							</div>
						</div>
						</div>
						<div class = "section ">
						<div style="margin: 20px 0px 0px 50px;min-width: 600px; height: 20vh; min-height: 285px; margin-left: 2.5vw;width: 82vw;">
							<div style="margin:0 10% 0 10%">
								<div style="float: left;height: 100%;width: 49%;margin:0 2% 0 0;">
									<h4 style="padding-left:7%">企业红榜</h4>
									<br>
									<div id = "red" style="height: 90%;overflow: hidden;border:solid 1px #DBDBDB;border-radius:10px"><ul id="redUl" style="padding-left:7%"></ul></div>
								</div>
								<div style="float: left;height: 100%;width: 49%;margin: auto;">
									<h4 style="padding-left:7%">企业黑榜</h4>
									<br>
									<div id = "black" style="height: 90%;overflow: hidden;border:solid 1px #DBDBDB;border-radius:10px"><ul id="blackUl" style="padding-left:7%"></ul></div>
								</div>
								<div style="float: left;height: 100%;width: 49%;margin: 3% 2% 0 0;">
									<h4  style="padding-left:7%">行政处罚</h4>
									<br>
									<div id = "DoublePublicity_penalty" style="height: 90%;overflow: hidden;border:solid 1px #DBDBDB;border-radius:10px"><ul id="penaltyUl" style="padding-left:7%"></ul></div>
								</div>
								<div style="float: left;height: 100%;width: 49%;margin: 3% 0 0;">
									<h4  style="padding-left:7%">行政许可</h4>
									<br>
									<div id = "DoublePublicity_license" style="height: 90%;overflow: hidden;border:solid 1px #DBDBDB;border-radius:10px"><ul id="licenseUl" style="padding-left:7%"></ul></div>
								</div>
							</div>
						</div>

						<%--<div style=" margin: 20px 0px 0px 50px;min-width: 600px; height: 60vh; min-height: 285px; margin-left: 2.5vw;width: 82vw;">
							<div style="float: left;height: 90%;width: 47%;margin: auto;">
								<h4 >推荐企业</h4>
								<br>
								<div id = "recommendation" style="height: 100%;overflow: auto;"></div>
							</div>
						</div>--%>
						<%--foot start--%>
				<%--foot end--%>
				
						</div >
					</div>
					<!--右侧 内容 上面基础信息部分   end-->
				
				
			</div>
		</div>
		
		
	</body>
	<script src="<%=path %>/Resources/jquery/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/ucenterPublic/Js/ucenterPublic.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/Highcharts-4.1.9/js/highcharts.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/fullPage/js/jquery.fullPage.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/base.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/plug/kxbdSuperMarquee/kxbdSuperMarquee.js" type="text/javascript" charset="utf-8"></script>
	<script language="JavaScript">
	
    $(function() {
        $("#ido").fullpage( {
            continuousVertical: true,  //循环演示
            //绑定菜单
            anchors: ['page1', 'page2', 'page3'],
            menu: '#menu',
            // 导航
            'navigation': true,
        });
    });
	
	 var sysChart = new Highcharts.Chart({
	        chart: {
	        	renderTo : 'main1',
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
				headerFormat : '分值 ：<span style="font-size:10px">{point.key}</span><table width=150px>',
				pointFormat : '<tr><td style="color:{series.color};padding:0">企业数: </td>'
						+ '<td style="padding:0"><b>{point.y} </b></td></tr>',
				footerFormat : '</table>',
				shared : true,
				useHTML : true
			},
	        series : [ {
				name : '分数',
				type:'line',
				data : [] 
			}]
	    });
	 $.ajax({
			type : "post",
			url: "<%=path%>/initGovernment/getCountScore.do",
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
	 
	 var sysChart1 = new Highcharts.Chart({
	        chart: {
	        	renderTo : 'main2',
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	            spacing : [10, 0 ,10, 0]
	        },
	        title: {
	            floating:true,
	            align: 'center',
	            text: '各行业占比',
	            style:{
                    fontSize: '1rem'
                }
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'buttom',
	            verticalAlign:'middle',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        plotOptions: {
	            pie: {
		            showInLegend: true,
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: false,
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                    style: {
	                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                    }
	                },
	                point: {
	                    events: {
	                        mouseOver: function(e) {  // 鼠标滑过时动态更新标题
	                            chart.setTitle({
	                                text:e.target.name,
	                                style:{
	                                    fontSize: '1rem'
	                                }
	                            });
	                        }
	                    }
	                },
	            }
	        },
	        series: [{
	            type: 'pie',
	            innerSize: '80%',
	            name: '企业数量',
	            data: []
	        }]
	    }, function(c) {
	        // 环形图圆心
	        var centerY = c.series[0].center[1],
	            titleHeight = parseInt(c.title.styles.fontSize);
	        c.setTitle({
	            y:centerY + titleHeight/2
	        });
	        chart = c;
		 
	 });
	 $.ajax({
			type : "post",
			url: "<%=path%>/initGovernment/classifyByIndustry.do",
			dataType : "json",
			success : function(data) {
				if(data.status == true){
					var sysStatisticsCharts = data.ChartVos;
					xData = []; // x轴名
					for ( var i in sysStatisticsCharts) {
						value = [];
						value.push(sysStatisticsCharts[i].descp);
						value.push(sysStatisticsCharts[i].cunt*1);
						xData.push(value);
					}
					sysChart1.series[0].setData(xData);// 设置Y坐标的值
				}else{
					popup("获取数据失败1，请稍后再试  ！   网络错路：204");
				}
				
			 },
			error : function(e) {
				popup(e);
			}
		});
	 
	 
	 //调用Dataservice数据 江苏省的饼状图
	var sysChart3 = new Highcharts.Chart({
		    chart: {
		    	renderTo : 'main3',
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: '',
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                showInLegend: true,
	                dataLabels: {
	                    enabled: true,
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                    style: {
	                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                    }
	                },
	            }
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign:'middle',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        tooltip: {
	            headerFormat: '{series.name}<br>',
	            pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        series: [{
	            type: 'pie',
	            name: '企业数量占比',
	            data: []
	        }]
	    });
	 $.ajax({
			type : "post",
			url: "<%=path%>/initGovernment/getEnterprisesPenaltyNumber.do",
			dataType : "json",
			success : function(data) {
				if(data.status == true){
					var val = data.value;
					if(val != null){
						xData = []; // x轴名
						var num = 0;
						for ( var i = 0; i < val.length; i++) {
							var name = val[i].gridValue;
							if(name == null){
								name  = "其他";
							}else{
								name = val[i].gridValue;
							}
							value = [];
							value.push(name);
							value.push(val[i].gridKey*1);
							xData.push(value);
							num += val[i].gridKey*1;
						}
						$("#penaltyNumber").text(num);
						sysChart3.series[0].setData(xData);// 设置Y坐标的值
					}else{
						$("#main3").html("数据获取失败,请联系管理员");
					}
				}else{
					popup("获取数据失败1，请稍后再试  ！   网络错路：204");
				}
			 },
			error : function(e) {
				popup(e);
			}
		});
	 
	 /*企业状态展示*/
	 $.ajax({
			type : "post",
			url: "<%=path%>/initGovernment/summaryscoring.do",
			dataType : "json",
			success : function(data) {
				if(data.status == true){
					if(data.notApplied != null && "" != data.notApplied){
						$(".notApplied").text(data.notApplied);
					}else{
						$(".notApplied").text(0);
					}
					if(data.confirmation != null && "" != data.confirmation){
						$(".confirmation").text(data.confirmation);
					}else{
						$(".confirmation").text(0);
					}
					if(data.editorialSurvey != null && "" != data.editorialSurvey){
						$(".editorialSurvey").text(data.editorialSurvey);
					}else{
						$(".editorialSurvey").text(0);
					}
					if(data.endInvestigation != null && "" != data.endInvestigation){
						$(".endInvestigation").text(data.endInvestigation);
					}else{
						$(".endInvestigation").text(0);
					}
					if(data.preliminaryConfirmation != null && "" != data.preliminaryConfirmation){
						$(".preliminaryConfirmation").text(data.preliminaryConfirmation);
					}else{
						$(".preliminaryConfirmation").text(0);
					}
					if(data.score != null && "" != data.score){
						$(".score").text(data.score);
					}else{
						$(".score").text(0);
					}
					if(data.endScore != null && "" != data.endScore){
						$(".endScore").text(data.endScore);
						$("#evaluatedEnterprise").text(data.endScore);
					}else{
						$(".endScore").text(0);
						$("#evaluatedEnterprise").text(0);
					}
				}else{
					popup("获取数据失败    网络错路：204");
				}
				
			 },
			error : function(e) {
				popup(e);
			}
		});
	 /* 红黑榜*/
	 $.ajax({
			type : "post",
			url: "<%=path%>/initGovernment/getRedBlackList.do",
			dataType : "json",
			success : function(data) {
				if(data.status == true){
					var blackList =	data.blackLists;
					var redList =	data.redLists;
					var str1 = "";
					var str2 = "";
					var jsonObj =  JSON.parse(blackList);//转换为json对象
					for(var i=0;i<jsonObj.length;i++){
						var s = "";
						if(jsonObj[i].name.length > 20){
							s = jsonObj[i].name.slice(0,17)+"...";
						}else{
							s = jsonObj[i].name;
						}
						if(jsonObj[i].legalPerson==null || jsonObj[i].legalPerson==''){
						jsonObj[i].legalPerson=" - ";
						}
						str1 += "<li><div><div style='width:50%;display:inline-block'><a href='http://www.szcredit.gov.cn/InitAction_companyBlack.action?menuId=1'>"
								+s+"</a></div><span style='width:50%;margin-left:10%'>"+jsonObj[i].legalPerson+"<span style='margin-left:4%'>"+jsonObj[i].revocationTime+"</span></span></div></li> ";
					}
					var jsonObj1 =  JSON.parse(redList);//转换为json对象
					for(var i=0;i<jsonObj1.length;i++){
						var s = "";
						if(jsonObj1[i].name.length > 20){
							s = jsonObj1[i].name.slice(0,17)+"...";
						}else{
							s = jsonObj1[i].name;
						}
						if(jsonObj1[i].legalPerson==null || jsonObj1[i].legalPerson==''){
						jsonObj1[i].legalPerson=" - ";
						}
						str2 += "<li><div><div style='width:50%;display:inline-block'><a href='http://www.szcredit.gov.cn/InitAction_companyRed.action?menuId=1 '>"
								+s+"</a></div><span style='width:50%;margin-left:10%'>"+jsonObj1[i].affirmationDocument+"</span></div></li> ";
					}

					$("#blackUl").append(str1);
					$("#redUl").append(str2);
					$('#red').kxbdSuperMarquee({
							isMarquee:true,
							isEqual:false,
							scrollDelay:50,
							controlBtn:{up:'#goUM',down:'#goDM'},
							direction:'up'
					});
					$('#black').kxbdSuperMarquee({
							isMarquee:true,
							isEqual:false,
							scrollDelay:50,
							controlBtn:{up:'#goUM',down:'#goDM'},
							direction:'up'
					});
				}else if(data.status == false){
					popup("获取数据失败   文件未找到");
				}else{
					popup("获取数据失败    网络错路：204");
				}
				
			 },
			error : function(e) {
				popup(e);
			}
		});
	 /*双公示*/
	 $.ajax({
			type : "post",
			url: "<%=path%>/initGovernment/getDoublePublicity.do",
			dataType : "json",
			success : function(data) {
				if(data.status == true){
					var license =	data.license;
					var penalty =	data.penalty;
					var str1 = "";
					var str2 = "";
					for(var i=0;i<license.length;i++){
						var str = JSON.stringify(license[i]);
						var s = "";
						if(license[i].counterpart.length>20){
							s = license[i].counterpart.slice(0,17)+"...";
						}else{
							s = license[i].counterpart;
						}
						var d = "";
						if(license[i].org.length>15){
							d = license[i].org.slice(0,13)+"...";
						}else{
							d = license[i].org;
						}
						str1 += "<li><div><div style='width:50%;display:inline-block'><a id = 'license' title = "+license[i].counterpart+" href='javascript:info(\"license\","+str+")'>"+s
						+"</a></div><span style='width:50%;margin-left:10%'>"+d+"</span></div></li>";
					}
					$("#licenseUl").append(str1);
					$('#DoublePublicity_license').kxbdSuperMarquee({
							isMarquee:true,
							isEqual:false,
							scrollDelay:50,
							controlBtn:{up:'#goUM',down:'#goDM'},
							direction:'up'
					});
					for(var i=0;i<penalty.length;i++){
						var str = JSON.stringify(penalty[i]);
						var s = "";
						if(penalty[i].counterpart.length>20){
							s = penalty[i].counterpart.slice(0,17)+"...";
						}else{
							s = penalty[i].counterpart;
						}
						var d = "";
						if(penalty[i].docno.length>15){
							d = penalty[i].docno.slice(0,13)+"...";
						}else{
							d = penalty[i].docno;
						}
						str2 += "<li><div><div style='width:50%;display:inline-block'><a title = "+penalty[i].counterpart+" href='javascript:info(\"penalty\","+str+")'>"+s
						+"</a></div><span style='width:50%;margin-left:10%'>"+d+"</span></div></li>";
					}
					$("#penaltyUl").append(str2);
					$('#DoublePublicity_penalty').kxbdSuperMarquee({
							isMarquee:true,
							isEqual:false,
							scrollDelay:50,
							controlBtn:{up:'#goUM',down:'#goDM'},
							direction:'up'
					});
				}else if(data.status == false){
					popup("获取数据失败   文件未找到");
					$("#licenseUl").append("<li><div><div style='width:50%;display:inline-block'>获取相关数据失败,请联系管理员</div></div></li>");
					$("#penaltyUl").append("<li><div><div style='width:50%;display:inline-block'>获取相关数据失败,请联系管理员</div></div></li>");
				}else{
					popup("获取数据失败    网络错路：204");
				}
				
			 },
			error : function(e) {
				popup(e);
			}
		});
	 /*区域统计企业数量*/
	$.ajax({
			type : "post",
			url: "<%=path%>/initGovernment/getEnterprisesNumber.do",
			dataType : "json",
			success : function(data) {
				if(data.status == true){
					var commonGridListEntityVos = data.commonGridListEntityVos;
					var num = data.num;
					var enterprises3A = data.enterprises3A;
					var str = "<div class = 'region' style='background:-moz-linear-gradient(left , #00A0E9 , #f9f9f9 75%);border-radius: 3px;color: #fff;'>"
					  +"<div class = 'region1' >区域名称</div>"
					  +"<div class = 'region2' >统计数量</div>"
					  +"<div class = 'region3' > </div></div>";
					for ( var int = 0; int < commonGridListEntityVos.length; int++) {
						var s = "";
						var length = commonGridListEntityVos[int].gridValue.length;
						if( int != 0){
							s = commonGridListEntityVos[int].gridValue.slice(commonGridListEntityVos[int].gridValue.indexOf("省")+1,commonGridListEntityVos[int].gridValue.length);
						}else{
							s = commonGridListEntityVos[int].gridValue;
						}
						if(s.length > 10){
							s = s.slice(s.indexOf("市")+1,s.length);
						}
						str += "<div class = 'region' style='margin-top: 2%;'>"
						  +"<div class = 'region1' ><a title = "+commonGridListEntityVos[int].gridValue+">"+s+"</div>"
						  +"<div class = 'region2' >"+commonGridListEntityVos[int].gridKey+"</div>"
						  +"<div class = 'region3' ><div class='progress' ><div class='progress-bar'  style='width: "+((commonGridListEntityVos[int].gridKey*1)/num*1)*1000+"%;'></div></div></div></div>";
					}
					$("#enterpriseNumber").text(num);
					$("#enterprises3A").text(enterprises3A);
					$(".modal-overlay").remove();
					$("#enterprisesNumber").append(str);
				}else if(data.status == false){
					popup("获取数据失败   未能调用相关数据");
					$(".modal-overlay").remove();
					$("#enterprisesNumber").append("<div class = 'region' style='background:-moz-linear-gradient(left , #00A0E9 , #f9f9f9 75%);border-radius: 3px;color: #fff;'>"
							  +"<div class = 'region1' >区域名称</div>"
							  +"<div class = 'region2' >统计数量</div>"
							  +"<div class = 'region3' > </div></div><div class = 'region' style='margin-top: 2%;'>数据获取失败 ,请联系管理员</div>");
				}else{
					popup("获取数据失败    网络错路：204");
				}
			 },
			error : function(e) {
				popup(e);
			}
		});
	 function info(str,obj){
	 	var check = "";
	 	if(str == "penalty"){//处罚
	 		check = "<tr style='text-align: left;'><td>行政相对人名称 </td><td >"+isNull(obj.counterpart)+"</td><td > 统一编码 </td><td >"+isNull(obj.uscc)+"</td></tr>" 
			       +"<tr style='text-align: left;'><td>处罚决定时间</td><td >"+isNull(obj.penaltydecisiontime)+"</td><td >处罚依据</td><td >"+isNull(obj.basis)+"</td></tr>" 
			       +"<tr style='text-align: left;'><td>省市</td><td >"+isNull(obj.city)+"</td><td >处罚截止时间</td><td >"+isNull(obj.closingdate)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>组织机构代码</td><td >"+isNull(obj.oibc)+"</td><td >数据更新时间戳</td><td >"+isNull(obj.dataupdatetime)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>行政处罚决定文书</td><td >"+isNull(obj.docno)+"</td><td >处罚截止时间</td><td >"+isNull(obj.closingdate)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>处罚生效期</td><td >"+isNull(obj.effectivedate)+"</td><td >居民身份证</td><td >"+isNull(obj.idcardno)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>是否删除</td><td >"+isNull(obj.isdelete)+"</td><td >法定代表人</td><td >"+isNull(obj.legalperson)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>处罚名称</td><td >"+isNull(obj.name)+"</td><td >处罚机关</td><td >"+isNull(obj.org)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>处罚内容</td><td >"+isNull(obj.penaltycontent)+"</td><td >项目名称</td><td >"+isNull(obj.projectname)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>处罚事由</td><td >"+isNull(obj.reason)+"</td><td >工商登记码</td><td >"+isNull(obj.regisno)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>备注</td><td >"+isNull(obj.remarks)+"</td><td >来源单位</td><td >"+isNull(obj.resourceorg)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>处罚结果</td><td >"+isNull(obj.result)+"</td><td >状态</td><td >"+isNull(obj.status)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>税务登记号</td><td >"+isNull(obj.taxno)+"</td><td >处罚类型1</td><td >"+isNull(obj.type1)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>处罚类型2</td><td >"+isNull(obj.type2)+"</td><td >更新时间</td><td >"+isNull(obj.updateTime)+"</td></tr>"; 
	 	}else if(str == "license"){//许可
	 		check = "<tr style='text-align: left;'><td>行政相对人名称 </td><td style='width: 50%;'>"+isNull(obj.counterpart)+"</td><td > 统一编码 </td><td >"+isNull(obj.uscc)+"</td></tr>" 
			       +"<tr style='text-align: left;'><td>许可内容</td><td >"+isNull(obj.approvalcontent)+"</td><td >许可类型</td><td >"+isNull(obj.approvaltype)+"</td></tr>" 
			       +"<tr style='text-align: left;'><td>地区编码</td><td >"+isNull(obj.areacode)+"</td><td >所属地区</td><td >"+isNull(obj.city)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>截止时间</td><td >"+isNull(obj.closingdate)+"</td><td >数据更新时间戳</td><td >"+isNull(obj.dataupdatetime)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>行政许可决定书文号</td><td >"+isNull(obj.docno)+"</td><td >居民身份证号</td><td >"+isNull(obj.idcardno)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>是否删除</td><td >"+isNull(obj.isdelete)+"</td><td >法定代表人</td><td >"+isNull(obj.legalperson)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>许可决定日期</td><td >"+isNull(obj.licensedecisiontime)+"</td><td >许可名称</td><td >"+isNull(obj.name)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>组织机构代码</td><td >"+isNull(obj.oibc)+"</td><td >许可机关</td><td >"+isNull(obj.org)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>项目名称</td><td >"+isNull(obj.projectname)+"</td><td >工商登记码</td><td >"+isNull(obj.regisno)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>来源单位</td><td >"+isNull(obj.resourceorg)+"</td><td >状态</td><td >"+isNull(obj.status)+"</td></tr>"; 
			       +"<tr style='text-align: left;'><td>税务登记号</td><td >"+isNull(obj.taxno)+"</td><td >更新时间</td><td >"+isNull(obj.updateTime)+"</td></tr>"; 
	 	}else if(str == "recommendationEnterprises"){
	 		check = "<tr style='text-align: left;'>" 
			        +"<td > 公司名称 </td><td >"+isNull(obj.name)+"</td><td > 统一编码 </td><td >"+isNull(obj.uscc)+"</td></tr>" 
			        +"<tr style='text-align: left;'>"
			        +"<td>注册资本 </td><td >"+isNull(obj.regiscapital)+"</td><td >币种</td><td >"+isNull(obj.currencytype)+"</td>" 
			        +"</tr><tr>"
			        +"<td>地址 </td><td >"+isNull(obj.address)+"</td><td >公司类型</td><td >"+isNull(obj.enttype)+"</td></tr>";
	 	}else{
	 		popup("选择错误，请重新选择");
	 	}
		 $("body").append("<div class='modal fade' id='recommendationEnterprisesInfo' style='top: 100px'><div class='modal-dialog ' style = 'width: 80vw;'>"
 				+"<div class='modal-content'><div class='modal-header'>"
 				   +"<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
 				   +"<h4 class='modal-title'>查看详情</h4></div>"
 				   +"<div class='modal-body'>" 
 				   +"<table class='table table-hover' style='width:90%;margin-left: 5%;overflow: auto;'>" 
				   +check
			       +"</table>" 
 				   +"</div>"
 				   +"<div class='modal-footer'> <button type='button' class='btn btn-default' id = 'close'  data-dismiss='modal'>确定</button>"
 				   +"</div></div></div></div>");
 		$("#recommendationEnterprisesInfo").modal("show");
 		$("#close").click(function(){
 			$("#recommendationEnterprisesInfo").modal("hide").remove();
 			$(".modal-backdrop").remove();
 		});
	 }
</script>
</html>