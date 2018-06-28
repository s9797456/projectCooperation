<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>报告插图</title>
<script>
	var _ctxPath="<%=request.getContextPath() %>";
	var entID="<%= request.getParameter("entID") %>";
</script>
<script type="text/javascript" src="../Resources/bootstrap/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../Resources/Highcharts-4.1.5/js/highcharts.js"></script>
<script type="text/javascript" src="../Resources/Highcharts-4.1.5/js/modules/exporting.js"></script>
<script type="text/javascript">
Highcharts.theme = {
		colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
		chart: {
			backgroundColor: {
				linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
				stops: [
					[0, 'rgb(255, 255, 255)'],
					[1, 'rgb(240, 240, 255)']
				]
			},
			borderWidth: 2,
			plotBackgroundColor: 'rgba(255, 255, 255, .9)',
			plotShadow: true,
			plotBorderWidth: 1
		},
		title: {
			style: {
				color: '#000',
				font: 'bold 16px "Trebuchet MS", Verdana, sans-serif'
			}
		},
		subtitle: {
			style: {
				color: '#666666',
				font: 'bold 12px "Trebuchet MS", Verdana, sans-serif'
			}
		},
		xAxis: {
			gridLineWidth: 1,
			lineColor: '#000',
			tickColor: '#000',
			labels: {
				style: {
					color: '#000',
					font: '11px Trebuchet MS, Verdana, sans-serif'
				}
			},
			title: {
				style: {
					color: '#333',
					fontWeight: 'bold',
					fontSize: '12px',
					fontFamily: 'Trebuchet MS, Verdana, sans-serif'

				}
			}
		},
		yAxis: {
			minorTickInterval: 'auto',
			lineColor: '#000',
			lineWidth: 1,
			tickWidth: 1,
			tickColor: '#000',
			labels: {
				style: {
					color: '#000',
					font: '11px Trebuchet MS, Verdana, sans-serif'
				}
			},
			title: {
				style: {
					color: '#333',
					fontWeight: 'bold',
					fontSize: '12px',
					fontFamily: 'Trebuchet MS, Verdana, sans-serif'
				}
			}
		},
		legend: {
			itemStyle: {
				font: '9pt Trebuchet MS, Verdana, sans-serif',
				color: 'black'

			},
			itemHoverStyle: {
				color: '#039'
			},
			itemHiddenStyle: {
				color: 'gray'
			}
		},
		labels: {
			style: {
				color: '#99b'
			}
		},
		navigation: {
			buttonOptions: {
				theme: {
					stroke: '#CCCCCC'
				}
			}
		}
	};
	//Apply the theme
	var highchartsOptions = Highcharts.setOptions(Highcharts.theme);
	$(document).ready(function(){
		var chart1 = new Highcharts.Chart({
		    chart: {
		    	renderTo : 'imageChart1',
		    	type: 'column'
		    },
		    title: {
		        text: '	重要财务数据统计图'
		    },
		    xAxis : {
				categories : []
			},
			yAxis : {
				title : {
					text : '金额(万元)'
				},
				labels: {
	                format: '{value}'
	            },
				plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
			},
		 	// 右下角设置
			credits : {
				enabled : false
			},
		    plotOptions: {
		        column: {
		            pointPadding: 0,//控制柱状图轴的大小
		            borderWidth: 0
		        }
		    },
		    series : [ {
				name : '各市企业数',
				data : []
			}, {
				name : '各市企业数',
				data : []
			}, {
				name : '各市企业数',
				data : []
			}]
		});
		
		var chart2 = new Highcharts.Chart({
		    chart: {
		    	renderTo : 'imageChart2',
		    	type: 'column'
		    },
		    title: {
		        text: '重要财务比率统计图'
		    },
		    xAxis : {
				categories : []
			},
			yAxis : {
				title : {
					text : '比率(%)'
				},
				labels: {
	                format: '{value}'
	            },
				plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
			},
		 	// 右下角设置
			credits : {
				enabled : false
			},
		    plotOptions: {
		        column: {
		            pointPadding: 0,//控制柱状图轴的大小
		            borderWidth: 0
		        }
		    },
		    series : [ {
				name : '各市企业数',
				data : []
			}, {
				name : '各市企业数',
				data : []
			}, {
				name : '各市企业数',
				data : []
			}]
		});
		// 异步请求数据
		$.ajax({
			type : "post",
			url: _ctxPath+'/control/reportTemplate/finalImage.do',
			dataType : "json",
			data : {"entID":entID},
			success : function(data, status) {
				var finalcialImageVos = data.finalcialImageVos;
				var finalRateImageVos = data.finalRateImageVos;
				var listNames = data.listNames;
				xData = []; // x轴名
				value1 = []; 
				value2 = []; 
				value3 = [];
				for ( var i in finalcialImageVos) {
					xData.push(finalcialImageVos[i].name);
					value1.push(parseFloat(finalcialImageVos[i].value1));
					value2.push(parseFloat(finalcialImageVos[i].value2));
					value3.push(parseFloat(finalcialImageVos[i].value3));
				}
				chart1.xAxis[0].setCategories(xData);// 设置X轴
				chart1.series[0].setData(value1);// 设置Y坐标的值
				chart1.series[1].setData(value2);// 设置Y坐标的值
				chart1.series[2].setData(value3);// 设置Y坐标的值
				chart1.series[0].update({
					name : listNames[0]
				});
				chart1.series[1].update({
					name : listNames[1]
				});
				chart1.series[2].update({
					name : listNames[2]
				});
				
				xDataw = []; // x轴名
				value1w = []; 
				value2w = []; 
				value3w = [];
				for ( var i in finalRateImageVos) {
					xDataw.push(finalRateImageVos[i].name);
					value1w.push(parseFloat(finalRateImageVos[i].value1));
					value2w.push(parseFloat(finalRateImageVos[i].value2));
					value3w.push(parseFloat(finalRateImageVos[i].value3));
				}
				chart2.xAxis[0].setCategories(xDataw);// 设置X轴
				chart2.series[0].setData(value1w);// 设置Y坐标的值
				chart2.series[1].setData(value2w);// 设置Y坐标的值
				chart2.series[2].setData(value3w);// 设置Y坐标的值
				chart2.series[0].update({
					name : listNames[0]
				});
				chart2.series[1].update({
					name : listNames[1]
				});
				chart2.series[2].update({
					name : listNames[2]
				});
				var svg_line = chart1.getSVG()+"_"+chart2.getSVG();
				$.ajax({
					type : "post",
					url: _ctxPath+'/control/reportTemplate/downFinalImage.do',
					dataType : "json",
					data : {"svgCode":svg_line,"entID":entID},
					success : function(data, status) {
					 },
					error : function(e) {
					}
				});
			 },
			error : function(e) {
			}
		});
	});
</script>
</head>
<body>
	<div id="imageChart1"  ></div>
	<div id="imageChart2"  ></div>
</body>
</html>