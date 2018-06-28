<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
<%@ include file="/WEB-INF/Page/Share/state.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:ng="http://angularjs.org" lang="zh-cmn-Hans" id="ng-app">

<head>
<%@ include file="/WEB-INF/Page/Share/title.jsp"%>
<link href="<%=path%>/Resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/plug/chosen_v1.4.2/chosen.min.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">  
<style type="text/css">
body {
	background-color: #f9f9f7;
	padding-top: 63px; 
}
.state1{
    color:#aaa;
}
.state2{
    color:#000;
}
.state3{
    color:red;
}
.state4{
    color:green;
}
</style>

</head>
<body>
	<div class="navbar navbar-fixed-top">
		<s:if test="organization==null"><%--如果传过来的对象为空，那么为默认的，否则的话为某个机构--%>
			<div class="navbar-inner">
					<ul class="nav " style="margin-left: 30px;">
						<li><a class="brand" href="http://www.hytcredit.com/portal.php" target="_blank">汇誉通</a></li>
						<li><a href="http://www.hytcredit.com/portal.php" target="_blank"><font color="orange">公告：</font>热烈祝贺汇誉通上线 信用评价互联网第一品牌</a></li>
					</ul>
				<div class="container" style="margin-right: 30px;">
					<ul class="nav  pull-right">
						<li><a href="#" style="color: white;">版本号:3.0</a></li>
						<li><a href="#" style="color: white;">更新日期: 2015-06-02</a></li>
						<li><a href="http://www.hytcredit.com/member.php?mod=register" target="_blank" style="color: white;"><i class="icon-comment" ></i> 官方微博</a></li>
					</ul>
				</div>
			</div>
		</s:if>
		<s:else> 
			<s:property escape="false" value="organization.headHtml"/>
		</s:else>
	</div>
	
	<div class="container" style="width: 700px;margin: 80px auto;height: 875px;">
		<legend><h2>注册新用户</h2></legend>
		<form class="form-horizontal  " role="form" id="regForm" method="post" action="<%=path%>/user/reg_start.do">
			<div class="control-group">
				<label for="userName" class="control-label"  style="padding-top:11px;font-size:15px;">用户名：</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" style="width:350px;height: 30px;">
					<span class='state1'>请输入用户名</span>
				</div>
			</div>			
			<div class="control-group">
				<label for="password" class="control-label" style="padding-top:11px;font-size:15px;">密码：</label>
				<div  class="controls">
					<input type="password" id="password" name="password" style="width:350px;height: 30px;">
					<span class='state1'>请输入密码</span>
				</div>
			</div>			
			<div class="control-group">
				<label for="password2" class="control-label" style="padding-top:11px;font-size:15px;">确认密码：</label>
				<div  class="controls">
					<input type="password" id="password2" name="password2" style="width:350px;height: 30px;">
					<span class='state1'>请输入确认密码</span>
				</div>
			</div>			
			<div class="control-group">
				<label for="realName" class="control-label" style="padding-top:11px;font-size:15px;">真实姓名：</label>
				<div  class="controls">
					<input type="text"  id="realName" name="realName" style="width:350px;height: 30px;">
					<span class='state1'>请输入真实姓名</span>
				</div>
			</div>
			<div class="control-group" style="padding-top:3px;height:20px;">
				<label for="gender" class="control-label"
					style="padding-top:3px;font-size:15px;">性别：</label>
				<div class="controls">
					<div class="control-group">
						<div style="padding-bottom: 10px;">
							<input type="radio" id="gender" name="gender"  value="MAN" checked>男 <input type="radio" id="gender" name="gender" value="WOMEN" >女
						</div>
					</div>
				</div>
			</div>
			<div class="control-group" >
				<label for="cellphone" class="control-label" style="padding-top:11px;font-size:15px;">移动电话：</label>
				<div  class="controls">
					<input type="text" id="cellphone" name="cellphone"  style="width:350px;height: 30px;">
					<span class='state1'>请输入移动电话</span>					
				</div>
			</div>			
			<div class="control-group">
				<label for="email" class="control-label" style="padding-top:11px;font-size:15px;">电子邮箱：</label>
				<div  class="controls">
					<input type="text"  id="email" name="email" style="width:350px;height: 30px;">
					<span class='state1'>请输入邮箱</span>
				</div>
			</div>
			<div class="control-group">
				<label for="entName" class="control-label" style="padding-top:11px;font-size:15px;">企业名：</label>
				<div  class="controls">
					<input type="text" id="entName" name="entName" style="width:350px;height: 30px;">
					<span class='state1'>请输入企业名</span>					
				</div>
			</div>
			<div class="control-group">
				<label for="business_no" class="control-label" style="padding-top:11px;font-size:15px;">企业注册号：</label>
				<div  class="controls">
					<input type="text" id="business_no" name="business_no" style="width:350px;height: 30px;">
					<span class='state1'>请输入企业注册号</span>					
				</div>
			</div>
			<div class="control-group">
				<label for="enterpriseType" class="control-label" style="padding-top:5px;font-size:15px;">企业类型：</label>
				<div class="controls">
					<div>
						<ul class="list" style="margin-left: 0px;list-style: none">
							<li class="title"><input id="industry" name="industry" type="text" readonly value="" style="width:350px;height: 30px;" onclick="showMenu(); return false;"/>
								<span class="state1" >请选择企业类型</span>					
							</li>
						</ul>
					</div>
					<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
						<ul id="treeDemo" class="ztree" style="margin-top:0px;margin-left0px; width:350px;border: 1px solid #617775;background: #f0f6e4;overflow-y:scroll;overflow-x:auto;"></ul>
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="span2 offset5">
					<button type="submit" class="btn btn-success btn-large">注册</button>
					<button type="button" id="goback"class="btn btn-success btn-large">返回</button>
				</div>
			</div>
		</form>
	</div>
	
	<script src="<%=path%>/Resources/bootstrap/js/jquery-1.7.2.min.js" ></script>
	<script src="<%=path%>/Resources/bootstrap/js/bootstrap.js" ></script>
	<script src="<%=path%>/plug/chosen_v1.4.2/chosen.jquery.min.js" ></script>
	<script src="<%=path%>/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script>
	var setting = {
			view: {
				dblClickExpand: true,//双击节点时，是否自动展开父节点的标识
				showLine: true,//是否显示节点之间的连线
				selectedMulti: false //设置是否允许同时选中多个节点
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "uuid", // id编号命名
	                pIdKey: "parentId" // 父id编号命名   
				}
			},
			callback: {//设置事件
				beforeClick: beforeClick,
				onClick: onClick
			}
	};
	$.ajax({
		type: "POST",
	    url: "<%=path%>/user/check_findRootName.do",
	    dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
	    success: function (data) {
	        $.fn.zTree.init($("#treeDemo"), setting, data.list);
	    }
	});
	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		if (!check) return false;
	}
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		v = "";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var industry = $("#industry");
		industry.attr("value", v);
		hideMenu();
	  	// 验证企业类型
        if($('input[name="industry"]').val().length==0){
            $(this).next().text('请选择企业类型').removeClass('state1').removeClass('state4').addClass('state3');
        }else{
        	$('input[name="industry"]').next().text('输入成功').removeClass('state1').addClass('state4');
            ok9=true;
        }
	}
	function showMenu() {
		var industry = $("#industry");
		var cityOffset = $("#industry").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + industry.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}
	document.oncontextmenu=rightMouse;
	function rightMouse() {
		return false; 
	}
	var ok1=false;
    var ok2=false;
    var ok3=false;
    var ok4=false;
    var ok5=false;
    var ok6=false;
    var ok7=false;
    var ok8=false;
    var ok9=false;
	 // 验证用户名
    $('input[name="userName"]').focus(function(){
        $(this).next().text('用户名应该为3-20位之间').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
    	$.ajax({
				url:"<%=path%>/user/check_userNameExist.do",
				type:"post",
				async : false,
				dataType:"json",
				data:$('#userName').serialize(),
				success:function(data){
					if (!data.status == true) {
						$('input[name="userName"]').next().text('用户名被占用').removeClass('state1').removeClass('state4').addClass('state3');
					}else{
						if($('input[name="userName"]').val().length >= 3 && $('input[name="userName"]').val().length <=12 && $('input[name="userName"]').val()!=''){
							$('input[name="userName"]').next().text('输入成功').removeClass('state1').addClass('state4');
				            ok1=true;
				        }else{
				        	$('input[name="userName"]').next().text('用户名应该为3-20位之间').removeClass('state1').removeClass('state4').addClass('state3');
				        }
					}
				}
    	});
    });
  //验证密码
    $('input[name="password"]').focus(function(){
        $(this).next().text('密码应该为6-20位之间').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
    	if($(this).val() == $('input[name="userName"]').val()){
        	 $(this).next().text('用户名密码不能相同').removeClass('state1').removeClass('state4').addClass('state3');
    	}else{
		        if($(this).val().length >= 6 && $(this).val().length <=20 && $(this).val()!=''){
		            $(this).next().text('输入成功').removeClass('state1').addClass('state4');
		            ok2=true;
		        }else{
		            $(this).next().text('密码应该为6-20位之间').removeClass('state1').removeClass('state4').addClass('state3');
		        }
    	}
    });
	  //验证确认密码
    $('input[name="password2"]').focus(function(){
    $(this).next().text('确认密码要和密码格式一致').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
	}).blur(function(){
		if($(this).val() == $('input[name="userName"]').val()){
       	 $(this).next().text('用户名密码不能相同').removeClass('state1').removeClass('state4').addClass('state3');
   	}else{
			    if($(this).val().length >= 6 && $(this).val().length <=20 && $(this).val()!='' && $(this).val() == $('input[name="password"]').val()){
			        $(this).next().text('输入成功').removeClass('state1').addClass('state4');
			        ok3=true;
			    }else{
			        $(this).next().text('确认密码要和密码格式一致').removeClass('state1').removeClass('state4').addClass('state3');
			    }
   	}
	});
    // 验证真实姓名
    $('input[name="realName"]').focus(function(){
        $(this).next().text('真实姓名应该为2-20位之间').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
        if($(this).val().length >= 2 && $(this).val().length <=12 && $(this).val()!=''){
            $(this).next().text('输入成功').removeClass('state1').addClass('state4');
            ok4=true;
        }else{
            $(this).next().text('真实姓名应该为2-20位之间').removeClass('state1').removeClass('state4').addClass('state3');
        }
    });
    // 验证移动电话
    $('input[name="cellphone"]').focus(function(){
        $(this).next().text('请输入正确的电话号码').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
    	$.ajax({
			url:"<%=path%>/user/check_cellphoneExist.do",
			type:"post",
			async : false,
			dataType:"json",
			data:$('#cellphone').serialize(),
			success:function(data){
				if (!data.status == true) {
					if(data.msg=="已存在！"){
						$('input[name="cellphone"]').next().text('电话号码被占用').removeClass('state1').removeClass('state4').addClass('state3');
   					}
   					if(data.msg=="格式错误！"){
						 $('input[name="cellphone"]').next().text('请输入正确的电话号码').removeClass('state1').removeClass('state4').addClass('state3');
   					}
				}else{
					 if($('input[name="cellphone"]').val().search(/(^0?[1][358][0-9]{9}$)/)==-1){
						 $('input[name="cellphone"]').next().text('请输入正确的电话号码').removeClass('state1').removeClass('state4').addClass('state3');
				        }else{                 
				        	$('input[name="cellphone"]').next().text('输入成功').removeClass('state1').addClass('state4');
				            ok5=true;
				        }
				}
			}
	});      
    });
    //验证邮箱
       $('input[name="email"]').focus(function(){
           $(this).next().text('请输入正确的EMAIL格式').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
       }).blur(function(){
	    	   $.ajax({
	   			url:"<%=path%>/user/check_emailExist.do",
	   			type:"post",
	   			async : false,
	   			dataType:"json",
	   			data:$('#email').serialize(),
	   			success:function(data){
	   				if (!data.status == true) {
	   					if(data.msg=="已存在！"){
		   					$('input[name="email"]').next().text('EMAIL被占用').removeClass('state1').removeClass('state4').addClass('state3');
	   					}
	   					if(data.msg=="格式错误！"){
	   						 $('input[name="email"]').next().text('请输入正确的EMAIL格式').removeClass('state1').removeClass('state4').addClass('state3');
	   					}
	   				}else{
	   					 if($('input[name="email"]').val().search(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/)==-1){
	   						 $('input[name="email"]').next().text('请输入正确的EMAIL格式').removeClass('state1').removeClass('state4').addClass('state3');
	   				        }else{                 
	   				        	$('input[name="email"]').next().text('输入成功').removeClass('state1').addClass('state4');
	   				            ok6=true;
	   				        }
	   				}
	   			}
	   	});
    });
    // 验证企业名
    $('input[name="entName"]').focus(function(){
        $(this).next().text('企业名应该为3-20位之间').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
        if($(this).val().length >= 3 && $(this).val().length <=20 && $(this).val()!=''){
            $(this).next().text('输入成功').removeClass('state1').addClass('state4');
            ok7=true;
        }else{
            $(this).next().text('企业名应该为3-20位之间').removeClass('state1').removeClass('state4').addClass('state3');
        }
    });
    // 验证企业注册号
    $('input[name="business_no"]').focus(function(){
        $(this).next().text('企业注册号不能为空').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
        if($(this).val().length==0){
            $(this).next().text('企业注册号不能为空').removeClass('state1').removeClass('state4').addClass('state3');
        }else{
            $(this).next().text('输入成功').removeClass('state1').addClass('state4');
            ok8=true;
        }
    });
    // 验证企业类型
    $('input[name="industry"]').focus(function(){
        $(this).next().text('请选择企业类型').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
        if($(this).val().length==0){
            $(this).next().text('请选择企业类型').removeClass('state1').removeClass('state4').addClass('state3');
        }
    });
		$("#regForm").submit(function() {
			if(ok1 && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9) {
				$("#regForm").submit();
			} else {
				alert("请正确完成注册！");
				return false;
			}
		});
		$("#goback").click(function(){
			location.href="<%=path%>/user/logonUI.do";
		});
	</script>	
</body>
</html>