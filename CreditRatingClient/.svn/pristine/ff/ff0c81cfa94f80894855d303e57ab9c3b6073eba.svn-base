$(document).ready(function () {
	$('#belongOrgCustTable')
       .dataTable({
		    "lengthChange": false,//是否允许用户改变表格每页显示的记录数(lengthChange)
            "processing": true,
   		    "serverSide": true,
			"searching": false,//是否允许Datatables开启本地搜索
			"deferRender": true,//延迟渲染
			"lengthMenu":"5",
   			"ajax":"/initProccess/belongOrgCust.do",
			"columns": [{
                "data": "name"
            }, {
                "data": "surveystatus"
            }, {
                "data": "finalscore"
            }, {
                "data": "fianllevel"
            }],
			"language": {
                "sLengthMenu": "每页显示 _MENU_ 条记录",
				"sProcessing": "处理中...",
                "sZeroRecords": "抱歉， 没有找到",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "sInfoEmpty": "没有数据",
                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                },
                "sZeroRecords": "没有检索到数据"
            },
      });
	 $.ajax({
		type: "post",
		url: "/initProccess/statisticaltypeOrgCust.do",
		success: function(data, textStatus){
			$(".statisticaltypeincommon").eq(0).html(data.statisticaltypeMap[-1]==null?0:data.statisticaltypeMap[-1]);
			$(".statisticaltypeincommon").eq(1).html(data.statisticaltypeMap[0]==null?0:data.statisticaltypeMap[0]);
			$(".statisticaltypeincommon").eq(2).html(data.statisticaltypeMap[1]==null?0:data.statisticaltypeMap[1]);
			$(".statisticaltypeincommon").eq(3).html(data.statisticaltypeMap[2]==null?0:data.statisticaltypeMap[2]);
			$(".statisticaltypeincommon").eq(4).html(data.statisticaltypeMap[3]==null?0:data.statisticaltypeMap[3]);
			$(".statisticaltypeincommon").eq(5).html(data.statisticaltypeMap[4]==null?0:data.statisticaltypeMap[4]);
			$(".statisticaltypeincommon").eq(6).html(data.statisticaltypeMap[5]==null?0:data.statisticaltypeMap[5]);
		}
	 }); 
	 var sysChart = new Highcharts.Chart({
        chart: {
        	renderTo : 'leftbar',
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
	/**var radioChart = new Highcharts.Chart({
        chart: {
            renderTo : 'rightbar',
			plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '企业等级分布'
        },
        // 右下角设置
		credits : {
			enabled : false
		},
		tooltip: {
    	    pointFormat: '{series.name}: <b>{point.y}</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '企业数',
            data: [
               
            ]
        }]
    });*/
   // 异步请求数据折线图
   $.ajax({
		type : "post",
		url: "/initProccess/getCountScore.do",
		dataType : "json",
		success : function(data, status) {
			var sysStatisticsCharts = data.ChartVos;
			xData = []; // x轴名
			value = []; // 
			for ( var i in sysStatisticsCharts) {
				value = [];
				value.push(parseFloat(sysStatisticsCharts[i].valueX));
				value.push(parseFloat(sysStatisticsCharts[i].valueY));
				xData.push(value);
			}
			sysChart.series[0].setData(xData);// 设置Y坐标的值
		 },
		error : function(e) {
			alert(e);
		}
	});
	
	// 异步请求雷达数据
   /**$.ajax({
		type : "post",
		url: "/initProccess/getCountLevel.do",
		dataType : "json",
		success : function(data, status) {
			var sysStatisticsCharts = data.ChartVos;
			xData = []; // x轴名
			value = []; // 
			for ( var i in sysStatisticsCharts) {
				value = [];
				value.push(sysStatisticsCharts[i].valueX);
				value.push(parseFloat(sysStatisticsCharts[i].valueY));
				xData.push(value);
			}
			radioChart.series[0].setData(xData);// 设置Y坐标的值
		 },
		error : function(e) {
			alert(e);
		}
	});*/
	
	
    $.ajax({
		type: "post",
		url: _ctxPath+"/tbHomePage/findAll.do",
		success: function(data, textStatus){
			var html1 = '';
			var date="";
			$.each(data.news,function(index,obj){
				date = getFormatDate(new Date(obj.operadate),'yyyy-MM-dd');
				html1+="<li><span>";
				html1+=date;
				html1+="</span><a href='#' onclick='preview(\""+obj.uuid+"\");'>";
				if(obj.name.length>=35){
					html1+=obj.name.substr(0,35);
					html1+="...";
				}else{
					html1+=obj.name;
				}
				html1+="</a></li>";
			});
			$("#news").append(html1);
			//Marquee
			$('#marqueeNew').kxbdSuperMarquee({
				isMarquee:true,
				isEqual:false,
				scrollDelay:50,
				controlBtn:{up:'#goUM',down:'#goDM'},
				direction:'up'
			});
		}
	});
	//动态改变样式
	if($("#dynamicfont").text().length>12){
		$("#dynamicfont").css("font-size","1px");
		$("#dynamicfont").css("margin-top","-4px");
	}
	 
	//动态更改font大小  
	var swfontsize = $(".statisticaltypeCommon").width(); //获取外围div的高度并将其赋予变量.
    var swfontsize60 = swfontsize * 0.062; //p标签的字体大小等于div高度的60%.
    var swFontSizeNumber = parseFloat(swfontsize60); 
    $(".statisticaltypeCommon").css({"font-size":swFontSizeNumber});
	
	//
	var swfontsize1 = $(window).width(); 
    var swfontsize601 = (swfontsize1 / 800)*9; 
    var swFontSizeNumber1 = parseFloat(swfontsize601); 
    $("#navbarSecondLi").css({"margin-left":swFontSizeNumber1+"%"});
});

$(window).bind("resize", changeLayout);
function changeLayout( e ) {
   //动态更改font大小  
	var swfontsize = $(".statisticaltypeCommon").width(); //获取外围div的高度并将其赋予变量.
    var swfontsize60 = swfontsize * 0.062; //p标签的字体大小等于div高度的60%.
    var swFontSizeNumber = parseFloat(swfontsize60); 
    $(".statisticaltypeCommon").css({"font-size":swFontSizeNumber});
	
	var swfontsize1 = $(window).width(); 
    var swfontsize601 = (swfontsize1 / 800)*9; 
    var swFontSizeNumber1 = parseFloat(swfontsize601); 
    $("#navbarSecondLi").css({"margin-left":swFontSizeNumber1+"%"});
};
/**
 * 
 */
function getLocalTime(nS) {     
   return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');     
}     

function getFormatDate(day,format) {
	if(!day){
		day= new Date();
	}
	var Year = 0;
	var Month = 0;
	var Day = 0;
	var Hour= 0;
	var Minute= 0;
	var Second= 0;
	var CurrentDate = "";
	Year = day.getFullYear();//ie火狐下都可以 
	Month = day.getMonth() + 1;
	Day = day.getDate();
	Hour = day.getHours(); 
	Minute = day.getMinutes(); 
	Second = day.getSeconds(); 
	CurrentDate += Year + "-";
	if (Month >= 10) {
		CurrentDate += Month + "-";
	} else {
		CurrentDate += "0" + Month + "-";
	}
	if (Day >= 10) {
		CurrentDate += Day + " ";
	} else {
		CurrentDate += "0" + Day + " ";
	}
	if(format=='yyyy-MM-dd'){
		return CurrentDate;
	}
	if(Hour>=10){
		CurrentDate += Hour + ":";
	}else{
		CurrentDate += "0" + Hour + ":";
	}
	if(Minute>=10){
		CurrentDate += Minute + ":";
	}else{
		CurrentDate += "0" + Minute + ":";
	}
	if(Second>=10){
		CurrentDate += Second;
	}else{
		CurrentDate += "0" + Second ;
	}
	return CurrentDate;
	}
	
	
	function preview(uuid){
		$.ajax({
			type: "post",
			url: _ctxPath+"/tbHomePage/findById.do",
			data:{
				uuid : uuid
			},
			success: function(data, textStatus){
				$.dialog({
					title: '新闻预览',
					bgiframe:true,
					closeOnEscape:true,
					height: 600,
					width:800,
					content: data.tbHomePage.content
				});
			}
		});
	}