//阅读协议
function readProtocols(state,name){
	if(state==0){
		$("body").append("<div class='modal fade' id='readinfo' style='top: 50px'>"
			    +"<div class='modal-dialog modal-sm' style='width:50%'>"
			        +"<div class='modal-content'>"
			            +"<div class='modal-header'>"
			                +"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>"
			                +"<span aria-hidden='true'>&times;</span></button>"
			                +"<h4 class='modal-title'>阅读协议</h4>"
			            +"</div>"
			            +"<div class='modal-body' id='readinfoModalBody' >"
						+"<div align='center'>"
							+"<b>真实性承诺书</b>"
						+"</div>"
						+"<br>"
						+"<div style='text-align:left;line-height:40px;'>"
							+"<b>甲方： 委托企业</b>"
							+"<br>"
							+"<b>乙方：&nbsp;"+name+"</b>"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、 本协议是甲方与乙方之间的法律协议。"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、 甲方了解并同意：甲方点击“同意”按钮并完成注册，本协议自动生效。乙方视甲方愿意受其约束，并授权乙方根据业务需要而采集甲方信用相关的所有信息。如果发生纠纷，甲方不得以未仔细阅读为由进行抗辩。"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、甲方一旦注册成功，成为本站的合法注册会员，甲方可根据需要修改密码，甲方有义务保证帐号和密码的安全，因保管不当引起的任何损失或损害，由甲方自行承担责任。另外甲方要对以其注册的会员名进行的所有活动和事件负全责。甲方若发现任何非法使用注册会员账户或存在安全漏洞的情况，请立即通知乙方。"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、因不可预见因素导致网络中断，导致乙方不能正常提供服务，乙方不承担任何责任。因为法律认定的不可抗力原因造成的损失，双方均不承担违约责任。 "
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5、乙方提供的信用评价服务是基于甲方提供的原始数据，甲方对因原始数据的真实性、完整性造成的一切后果负责。"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）	甲方保证在系统中提交信息的准确性。"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）甲方在信息确认时选择“同意”并提交后，乙方视甲方同意乙方针对其选取的第三方信用信息，并认可乙方基于此信息完成的健康体检报告。"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6、本次信用评价业务的结果仅针对其原始数据有效；仅供相关当事方作为商业辅助决策的要素之一使用。乙方不承担甲方及相关当事人的任何商业风险，也不承担由于非控因素和数据而引起的相关损失。"
						+"</div>"
						+"<div style='text-align:right;'>特此承诺！</div>"
					 +"<div class='modal-footer'>"
					    +"<button type='button' class='btn btn-primary' data-dismiss='modal' id='agree'>同意</button>"
					 +"</div>"
			        +"</div>"
			    +"</div>"
			+"</div>");
	}else{
		$("body").append("<div class='modal fade' id='readinfo' style='top: 50px'>"
			    +"<div class='modal-dialog modal-sm' style='width:50%'>"
			        +"<div class='modal-content'>"
			            +"<div class='modal-header'>"
			                +"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>"
			                +"<span aria-hidden='true'>&times;</span></button>"
			                +"<h4 class='modal-title'>阅读协议</h4>"
			            +"</div>"
			            +"<div class='modal-body' id='readinfoModalBody' >"
						+"<div align='center'>"
							+"<b>真实性承诺书</b>"
						+"</div>"
						+"<br>"
						+"<div style='text-align:left;line-height:40px;'>"
							+"<b>甲方： 委托企业</b>"
							+"<br>"
							+"<b>乙方：&nbsp;"+name+"</b>"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、 本协议是甲方与乙方之间的法律协议。"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、 甲方了解并同意：甲方点击“同意”按钮并完成注册，本协议自动生效。乙方视甲方愿意受其约束，并授权乙方根据业务需要而采集甲方信用相关的所有信息。如果发生纠纷，甲方不得以未仔细阅读为由进行抗辩。"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、甲方一旦注册成功，成为本站的合法注册会员，甲方可根据需要修改密码，甲方有义务保证帐号和密码的安全，因保管不当引起的任何损失或损害，由甲方自行承担责任。另外甲方要对以其注册的会员名进行的所有活动和事件负全责。甲方若发现任何非法使用注册会员账户或存在安全漏洞的情况，请立即通知乙方。"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、因不可预见因素导致网络中断，导致乙方不能正常提供服务，乙方不承担任何责任。因为法律认定的不可抗力原因造成的损失，双方均不承担违约责任。 "
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5、乙方提供的信用评价服务是基于甲方提供的原始数据，甲方对因原始数据的真实性、完整性造成的一切后果负责。"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）	甲方保证在系统中提交信息的准确性。"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）甲方在信息确认时选择“同意”并提交后，乙方视甲方同意乙方针对其选取的第三方信用信息，并认可乙方基于此信息完成的健康体检报告。"
							+"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6、本次信用评价业务的结果仅针对其原始数据有效；仅供相关当事方作为商业辅助决策的要素之一使用。乙方不承担甲方及相关当事人的任何商业风险，也不承担由于非控因素和数据而引起的相关损失。"
						+"</div>"
						+"<div style='text-align:right;'>特此承诺！</div>"
					 +"<div class='modal-footer'>"
					    +"<button type='button' class='btn btn-primary' data-dismiss='modal'>关闭</button>"
					 +"</div>"
			        +"</div>"
			    +"</div>"
			+"</div>");
	}
	
	$("#readinfo").modal("show");
}
$("body").on("click","#agree",function(){
	$.ajax({
		type : "post",
		url : _ctxPath + "/main/readProtocols.do",
		success : function(msg) {
			popup(msg.msg);
		},
		error : function() {
			popup("网络连接失败，请稍后再试..");
		}
	});
});

//下载报告
/*
 * 公司名称
 * 报告名称
 * 评级日期 - 评级编号
 * 是否可以下载
 * */
function downloadReport(){
	$.ajax({
        cache: true,
        type: "POST",
        url:_ctxPath+'/main/downloadReport.do',
        async: false,
        error: function() {
            popup("网络错误，请重新登录    错误代码：400");
        },
        success: function(data) {
        	if(data.status = 1){
        		if(data.entResult == null){
        			popup("未查询到相关数据，请重新登录后再进行操作！");
        		}
        		var d = "<span class = 'glyphicon glyphicon-ok-sign' style = 'color: rgb(62, 182, 62); float: right; font-size: 20px;'></span>";
        		var c = "<span class = 'glyphicon glyphicon-minus-sign' style = 'color: rgb(231, 43, 29); float: right; font-size: 20px;'></span>";
        		var dow = "<button type='button' class='btn btn-primary' id='download'>下载</button>";
        		var flag = true;
        		var encoding = data.entResult.encoding;
        		if(encoding == null) {
        			encoding = " - </span>"+c;
        			flag = false;
        		}else{
        			encoding += "</span>"+d;
        		}
        		var gradetime = data.entResult.gradetime;
        		if(gradetime == null) {
        			gradetime = " - </span>"+c;
        			flag = false;
        		}else{
        			gradetime = (new Date(gradetime)).Format("yyyy-MM-dd") + "</span>"+d;
        		}
        		if(data.reporturl == 1 && flag == true){
        			var span = "检查完成</span>"+d ;
        		}else{
        			dow = "";
        			var span = "评分操作未完成</span>"+c;
        		}
        		$("body").append("<div class='modal fade' id='downloadReport' style='top: 200px'><div class='modal-dialog'>"
            			+"<div class='modal-content'><div class='modal-header'>"
            			   +"<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
            			   +"<h4 class='modal-title'>下载报告</h4></div>"
            			   +"<div class='modal-body'>" 
            			   +"<div style = 'width: 80%; margin:10px auto;'> <span>公司名称 ： "+data.entName+"</span><span class = 'glyphicon glyphicon-ok-sign' style = 'color: rgb(62, 182, 62); float: right; font-size: 20px;'></span></div>"
            			   +"<div style = 'width: 80%; margin:10px auto;'> <span> 评级编码 ："+encoding+"</div>"
            			   +"<div style = 'width: 80%; margin:10px auto;'> <span> 评级时间 ： "+gradetime+"</div>"
            			   +"<div style = 'width: 80%; margin:10px auto;'> <span>下载状况 ： "+span+"</div>"
            			   +"</div>"
            			   +"<div class='modal-footer'> <button type='button' class='btn btn-default'  data-dismiss='modal'>关闭</button>"+dow
            			   +"</div></div></div></div>");
            	$("#downloadReport").modal("show");
            	$("body").on("click","#download",function(){
            		window.location.href= _ctxPath+'/main/downLoad.do';
            		$("#downloadReport").modal('hide');
            	});
        	}else{
        		popup("网络错误，请重新登录    错误代码：204");
        	}
        }
    });
	
}

//查看 驳回项
function rejectedItem(opinion){
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:_ctxPath+'/main/opinion.do',
	        async: false,
	        error: function() {
	            popup("网络错误，请重新登录    错误代码：400");
	        },
	        success: function(data) {
	        	if(data.status == 1){
	        		var opinion = data.opinion;
	        		if(opinion == null||opinion.isconfirm == 1||opinion.isconfirm == null){
	        			popup("暂无驳回内容");
	        			return;
	        		}
	        		
	        		var updateTime = opinion.updatetime;
	        		if (opinion.updatetime != null) {
	        			opinion.updatetime = (new Date(opinion.updatetime)).Format("yyyy-MM-dd");
					}else{
						opinion.updatetime = null;
					}
	        		$("body").append("<div class='modal fade' id='reject' style='top: 300px'><div class='modal-dialog '>"
		    				+"<div class='modal-content'><div class='modal-header'>"
		    				   +"<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
		    				   +"<h4 class='modal-title'>查看驳回内容</h4></div>"
		    				   +"<div class='modal-body'>" 
		    				   +"<table class='table table-hover' style='width:90%;margin-left: 15px;'>" 
					        		+"<tr style='text-align: left;'>" 
					        		+"<td > 驳回项处理人 </td><td >"+isNull(opinion.dealer)+"</td></tr>" 
					        		 +"<tr style='text-align: left;'>"
					        		 +"<td > 驳回时间 </td>"
					        		 +"<td >"+isNull(opinion.updatetime)+"</td>" 
					        		 +"</tr><tr>"+"<td > 驳回内容</td>"
					        		 +"<td >"+isNull(opinion.opinion)+"</td></tr>" 
					        	+"</table>" 
		    				   +"</div>"
		    				   +"<div class='modal-footer'> <button type='button' class='btn btn-default'  data-dismiss='modal'>确定</button>"
		    				   +"</div></div></div></div>");
		    				$("#reject").modal("show");
	        	}else{
	        		popup(data.msg);
	        	}
	        	
	        }
		});
}

/*历史记录*/
function historical(historical){
	if(historical == 1 ){
		window.location.href= _ctxPath+'/main/historical.do';
	}else{
		popup("暂无历史内容");
		return;
	}
}


/*历史对象  下载报告*/
function downloadReport_historical(hisId){
	if(hisId == null || hisId == ""){
		popup("未获取到数据");
		return;
	}
	$.ajax({
        cache: true,//默认值: true，dataType 为 script 和 jsonp 时默认为 false。设置为 false 将不缓存此页面。
        type: "POST",
        data:{ "hisId":hisId},
        url:_ctxPath+'/main/downloadReportHistoricalUI.do',
        async: true,//默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
        error: function() {
            popup("网络错误，请重新登录    错误代码：400");
        },
        success: function(data) {
        	if(data.status == 1){
        		var d = "<span class = 'glyphicon glyphicon-ok-sign' style = 'color: rgb(62, 182, 62); float: right;font-size: 20px;'></span>";
        		var c = "<span class = 'glyphicon glyphicon-minus-sign' style = 'color: rgb(231, 43, 29); float: right;font-size: 20px;'></span>";
        		var dow = "<button type='button' class='btn btn-primary' id='downloadHis'><input type = 'hidden' value = '"+data.historica.historicalId+"'/>下载</button>";
        		var flag = true;
        		
        		var encoding = data.historica.encoding;
        		if(encoding == null) {
        			encoding = " - </span>"+c;
        			flag = false;
        		}else{
        			encoding += "</span>"+d;
        		}
        		
        		var gradetime = data.historica.gradeTime;
        		if(gradetime == null) {
        			gradetime = " - </span>"+c;
        			flag = false;
        		}else{
        			gradetime = (new Date(gradetime)).Format("yyyy-MM-dd") + "</span>"+d;
        		}
        		
        		if(data.reporturl == 1 && flag == true){
        			var span = "检查完成</span>"+d ;
        		}else{
        			dow = "";
        			var span = "评分操作未完成</span>"+c;
        		}
        		
        		$("body").append("<div class='modal fade' id='downloadHistorical' style='top: 200px'><div class='modal-dialog'>"
            			+"<div class='modal-content'><div class='modal-header'>"
            			   +"<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
            			   +"<h4 class='modal-title'>下载历史报告</h4></div>"
            			   +"<div class='modal-body'>" 
            			   +"<div style = 'width: 80%; margin:10px auto;'> <span>公司名称 ： "+data.historica.name

            			   +"</span><span class = 'glyphicon glyphicon-ok-sign' style = 'color: rgb(62, 182, 62); float: right; font-size: 20px;'></span></div>"
            			   +"<div style = 'width: 80%; margin:10px auto;'> <span> 评级编码 ："+encoding+"</div>"
            			   +"<div style = 'width: 80%; margin:10px auto;'> <span> 评级时间 ： "+gradetime+"</div>"
            			   +"<div style = 'width: 80%; margin:10px auto;'> <span>下载状况 ： "+span+"</div>"
            			   +"</div>"
            			   +"<div class='modal-footer'> <button type='button' class='btn btn-default'  data-dismiss='modal'>关闭</button>"+dow
            			   +"</div></div></div></div>");
            	$("#downloadHistorical").modal("show");
            	$("body").on("click","#downloadHis",function(){
            		window.location.href= _ctxPath+'/main/downLoadHistorical.do?hisId='+$(this).find("input").val();
            		$("#downloadHistorical").modal('hide');
            	});
        	}else{
        		popup("网络错误，请重新登录    错误代码：204");
        	}
        }
    });
}

//跳转 录入界面
function inputView(){
	window.location.href= _ctxPath+'/initEnterprise/jumpIntoEnterprise.do';
}

/*消息提示框*/
function popup(str) {
	$("body").append("<div id = 'popup' style='width: 300px;height: 100px;border: 2px #00a0e9 solid;margin: auto;position: fixed;left: 40%;top: 30%;background:#f9f9f9;border-radius: 15px;box-shadow: 10px 7px 15px #888;'><label style='text-align: center;width: 100%;height: 100%;line-height: 100px;font-family: '微软雅黑';font-size: 15px;'>"+ str + "</label></div>");
	window.setTimeout(function() {
		$("#popup").remove();
	}, 2000);//设置显示时间
}
//
function isNull(param){
	if(param == ""|| param == null ){
		param = " - ";
	}
	return param;
}
/*格式化时间  --- date转str*/
Date.prototype.Format = function(fmt) { //author: meizz
	var o = {
		"M+" : this.getMonth() + 1, //月份
		"d+" : this.getDate(), //日
		"h+" : this.getHours(), //小时
		"m+" : this.getMinutes(), //分
		"s+" : this.getSeconds(), //秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度
		"S" : this.getMilliseconds()
	//毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]): (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};