<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
<%@ include file="/WEB-INF/Page/Share/state.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:ng="http://angularjs.org" lang="zh-cmn-Hans" id="ng-app">

<head>
<%@ include file="/WEB-INF/Page/Share/title.jsp"%>
        <script>
			var _ctxPath="<%=request.getContextPath() %>";
		</script>
<link href="<%=path%>/Resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Resources/chosen_v1.4.2/chosen.min.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Resources/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="collapse navbar-collapse" id="example-navbar-collapse">
                <ul class="nav navbar-nav">
						<li><a class="brand" href="javascript:void(0);" style="font-size:20px;">${sessionScope.ObjectName}</a></li>
                </ul>
                <div class="pull-right">
	                <ul class="nav  navbar-nav ">
						<li><a href="#" style="color: white;">版本号：${sessionScope.Version}</a></li>
						<li><a href="#" style="color: white;">更新日期: ${sessionScope.UpdateTime}</a></li>
					</ul>
                </div>
            </div>
        </div>
    </nav>
	
	<div class="container" style="width: 700px;margin: 40px auto;height: 875px;">
	
		<c:if test="${no==2 }">
		<legend><h2>注册新企业用户</h2></legend>
		</c:if>
		<c:if test="${no==1 }">
		<legend><h2>注册新管理员</h2></legend>
		</c:if>
		<form class="form-horizontal"  role="form" id="regForm" name="regForm">
			  <div class="form-group">
				    <label  for="username"  class="col-sm-2 control-label">用&nbsp;&nbsp;户&nbsp;名：</label>
				    <div class="col-sm-9">
				      		<input type="text" id="username" name="username" class="form-control"  >
							<span class='state1'>请输入用户名</span>
				    </div>
			  </div>
			  <div class="form-group">
					    <label for="password" class="col-sm-2 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
					    <div class="col-sm-9">
					      	<input type="password" class="form-control" id="password" name="password">
					      	<span class='state1'>请输入密码</span>
					    </div>
			  </div>
			   <div class="form-group">
					    <label for="password2" class="col-sm-2 control-label">确认密码：</label>
					    <div class="col-sm-9">
					      	<input type="password" class="form-control" id="password2" name="password2">
					      	<span class='state1'>请输入确认密码</span>
					    </div>
			  </div>
			    <div class="form-group">
					    <label for="realName" class="col-sm-2 control-label">联系人：</label>
					    <div class="col-sm-9">
					      	<input type="text" class="form-control" id="realName" name="realname">
					      	<span class='state1'>请输入联系人</span>
					    </div>
			  </div><%--
			  <div class="form-group">
					    <label for="gender" class="col-sm-2 control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					    <div class="col-sm-9">
					      	<label class="radio-inline">
							  	<input type="radio" id="gender" name="gender"  value="MAN" checked> 男
							</label>
							<label class="radio-inline">
							  <input type="radio" id="gender" name="gender" value="WOMEN" > 女
							</label>
					    </div>
			  </div>
			  --%><div class="form-group">
					    <label for="cellphone" class="col-sm-2 control-label">移动电话：</label>
					    <div class="col-sm-9">
					      	<input type="text" class="form-control" id="cellphone" name="cellphone">
					      	<span class='state1'>请输入移动电话</span>
					    </div>
			  </div>
			    <div class="form-group">
					    <label for="email" class="col-sm-2 control-label">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>
					    <div class="col-sm-9">
					      	<input type="text" class="form-control" id="email" name="email">
					      	<span class='state1'>请输入邮箱</span>
					    </div>
			  </div>
			  <c:if test="${no==2 }">
			            
			    <div class="form-group">
					    <label for="entName" class="col-sm-2 control-label">企&nbsp;&nbsp;业&nbsp;名：</label>
					    <div class="col-sm-9">
					      	<input type="text" class="form-control" id="entName" name="name">
					      	<span class='state1'>请输入企业名</span>
					    </div>
			  </div>
			    <div class="form-group">
					    <label for="business_no" class="col-sm-2 control-label">注&nbsp;&nbsp;册&nbsp;号：</label>
					    <div class="col-sm-9">
					      	<input type="text" class="form-control" id="business_no" name="businessNo">
					      	<span class='state1'>请输入企业注册号</span>
					    </div>
			  </div>
			  <div class="form-group">
					    <label for="enterpriseType" class="col-sm-2 control-label">企业类型：</label>
					    <div class="col-sm-9">
							<ul class="list" style="list-style: none">
								<li class="title"><input id="industry" name="industry" type="text" readonly value="" class="form-control"  onclick="showMenu(); return false;" style="margin-left: -40px; width: 490px" />
									<span class="state1" style="margin-left: -35px" >请选择企业类型</span>					
								</li>
							</ul>
						</div>
						<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
							<ul id="treeDemo" class="ztree" style="margin-top:0px;margin-left0px; width:480px;border: 1px solid #617775;background: #f0f6e4;overflow-y:scroll;overflow-x:auto;"></ul>
						</div>
			</div>
			            </c:if>
			  <div class="form-group">
					    <div class="col-sm-offset-9">
					      		<c:if test="${no==2 }"><button id="regist" type="button" class="btn btn-success btn-large" onclick="doregbtn()">注册</button></c:if>
					      		<%--<c:if test="${no==1 }"><button id="regist1" type="button" class="btn btn-success btn-large" >注册</button></c:if>
					      		--%><button type="button" id="goback"class="btn btn-success btn-large">返回</button>
								
					    </div>
			  </div>
	</form>
		
</div>
	
	<script src="<%=path%>/Resources/jquery/jquery-1.11.3.js"></script>
	<script src="<%=path%>/Resources/bootstrap/js/bootstrap.js" ></script>
	<script src="<%=path%>/Resources/chosen_v1.4.2/chosen.jquery.min.js" ></script>
	<script src="<%=path%>/Resources/zTree/js/jquery.ztree.core-3.5.js"></script>
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
	                pIdKey: "parentid" // 父id编号命名   
				}
			},
			callback: {//设置事件
				beforeClick: beforeClick,
				onClick: onClick
			}
	};
	$.ajax({
		type: "POST",
	    url: "<%=path%>/customer/findRootName.do",
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
	

	var ok1=false;
    var ok2=false;
    var ok3=false;
    var ok4=false;
    var ok5=false;
    var ok6=false;
    var ok7=false;
    var ok8=false;
    var ok9=false;
    var no=${no};
	 // 验证用户名
    $('input[name="username"]').focus(function(){
        $(this).next().text('用户名应该为3-18位之间').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
    	with (regForm) {
    		if (username.value.indexOf("fuck") >= 0
    				|| username.value.indexOf("caonima") >= 0
    				|| username.value.indexOf("fuckoff") >= 0) {
				$('input[name="username"]').next().text('包含敏感字段，请重新输入').removeClass('state1').removeClass('state4').addClass('state3');
    			return false;
    		}
    	}

        var reg = new RegExp("^[a-zA-Z]\w{2,17}$ ");  
        var obj = document.getElementById("username");  
    	$.ajax({
			url:"<%=path%>/customer/check_userNameExist.do?no="+no,
			type:"post",
			async : false,
			dataType:"json",
			data:$('#username').serialize(),
			success:function(data){
				if (!data.status == true) {
					if(data.msg==1){
						$('input[name="username"]').next().text('用户名不能包含中文').removeClass('state1').removeClass('state4').addClass('state3');
					}else if(data.msg==2){
						$('input[name="username"]').next().text('用户名不能包含空格').removeClass('state1').removeClass('state4').addClass('state3');
					}else{
						$('input[name="username"]').next().text('用户名被占用').removeClass('state1').removeClass('state4').addClass('state3');
					}
				}else{
					if($('input[name="username"]').val().length >= 3 && $('input[name="username"]').val().length <=18 && $('input[name="username"]').val()!=''){
				        if(reg.test(obj.value)){  
							$('input[name="username"]').next().text('帐号应该长度在6-20之间，必须以字母开头，可以包含字母、数字和下划线。').removeClass('state1').removeClass('state4').addClass('state3');
				        }else if(!/^[a-zA-Z]\w{2,17}$/.test(obj.value)){  
							$('input[name="username"]').next().text('帐号应该长度在6-20之间，必须以字母开头，可以包含字母、数字和下划线。').removeClass('state1').removeClass('state4').addClass('state3');
				        }else{
							$('input[name="username"]').next().text('输入成功').removeClass('state1').addClass('state4');
							}
				        
			            ok1=true;
			        }else{
			        	$('input[name="username"]').next().text('用户名应该为3-18位之间').removeClass('state1').removeClass('state4').addClass('state3');
			        }
				}
			}
	});

    });
  //验证密码
    $('input[name="password"]').focus(function(){
        $(this).next().text('密码应该为6-20位之间').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
        var reg = new RegExp("^[a-zA-Z]\w{5,19}$ ");  
        var obj = document.getElementById("password");  
    	if($(this).val() == $('input[name="username"]').val()){
        	 $(this).next().text('用户名密码不能相同').removeClass('state1').removeClass('state4').addClass('state3');
    	}else{
		        if($(this).val().length >= 6 && $(this).val().length <=20 && $(this).val()!=''){
		            if(reg.test(obj.value)){  
		                $(this).next().text('密码应该长度在6-20之间，必须以字母开头，可以包含字母、数字和下划线。').removeClass('state1').removeClass('state4').addClass('state3');
		            }else if(!/^[a-zA-Z]\w{5,19}$/.test(obj.value)){  
		                $(this).next().text('密码应该长度在6-20之间，必须以字母开头，可以包含字母、数字和下划线。').removeClass('state1').removeClass('state4').addClass('state3');
		            }else{
			            $(this).next().text('输入成功').removeClass('state1').addClass('state4');
		            }
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
		if($(this).val() == $('input[name="username"]').val()){
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
    // 验证联系人
    $('input[name="realname"]').focus(function(){
        $(this).next().text('联系人应该为2-12位之间').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){

    	with (regForm) {
    		if (realname.value.indexOf("fuck") >= 0
    				|| realname.value.indexOf("caonima") >= 0
    				|| realname.value.indexOf("fuckoff") >= 0 || realname.value.indexOf("操你") >= 0 || realname.value.indexOf("妈的") >= 0
    				|| realname.value.indexOf("傻逼") >= 0 || realname.value.indexOf("煞笔") >= 0 || realname.value.indexOf("畜生") >= 0
    				|| realname.value.indexOf("滚你") >= 0 || realname.value.indexOf("屎") >= 0 || realname.value.indexOf("去死") >= 0 || realname.value.indexOf("大爷") >= 0) {
				$('input[name="realname"]').next().text('包含敏感字段，请重新输入').removeClass('state1').removeClass('state4').addClass('state3');
    			return false;
    		}
    	}
        var reg = new RegExp("^[0-9]+(\\.[0-9]+)?$");  
        var obj = document.getElementById("realName");  
        if($(this).val().length >= 2 && $(this).val().length <=12 && $(this).val()!=''){
            if(reg.test(obj.value)){  
                $(this).next().text('联系人不能是数字').removeClass('state1').removeClass('state4').addClass('state3');
            }else if(/^[0-9]+(\\.[0-9]+)?$/.test(obj.value)){  
                $(this).next().text('联系人不能是数字').removeClass('state1').removeClass('state4').addClass('state3');
            }else{
                $(this).next().text('输入成功').removeClass('state1').addClass('state4');
            }
            ok4=true;
        }else{
            $(this).next().text('联系人应该为2-12位之间').removeClass('state1').removeClass('state4').addClass('state3');
        }
    });
    // 验证移动电话
    $('input[name="cellphone"]').focus(function(){
        $(this).next().text('请输入正确的电话号码').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
    	$.ajax({
			url:"<%=path%>/customer/check_cellphoneExist.do?no="+no,
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
					 if($('input[name="cellphone"]').val().search(/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8}|\d{3})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/)==-1){
						 $('input[name="cellphone"]').next().text('请输入正确的电话号码').removeClass('state1').removeClass('state4').addClass('state3');
				        }else{  
				        	if($('input[name="cellphone"]').val().length !=11){
					        	$('input[name="cellphone"]').next().text('电话号码位数不正确').removeClass('state1').removeClass('state4').addClass('state3');
					        }else{
					        	$('input[name="cellphone"]').next().text('输入成功').removeClass('state1').addClass('state4');
					            ok5=true;
					        }
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
	   			url:"<%=path%>/customer/check_emailExist.do?no="+no,
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
    $('input[name="name"]').focus(function(){
        $(this).next().text('企业名应该为3-20位之间').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
    	with (regForm) {
    		if (name.value.indexOf("fuck") >= 0
    				|| name.value.indexOf("caonima") >= 0
    				|| name.value.indexOf("fuckoff") >= 0 || name.value.indexOf("操你") >= 0 || name.value.indexOf("妈的") >= 0
    				|| name.value.indexOf("傻逼") >= 0 || name.value.indexOf("煞笔") >= 0 || name.value.indexOf("畜生") >= 0
    				|| name.value.indexOf("滚你") >= 0 || name.value.indexOf("屎") >= 0 || name.value.indexOf("去死") >= 0 || name.value.indexOf("大爷") >= 0) {
				$('input[name="name"]').next().text('包含敏感字段，请重新输入').removeClass('state1').removeClass('state4').addClass('state3');
    			return false;
    		}
    	}
        if($(this).val().length >= 3 && $(this).val().length <=20 && $(this).val()!=''){
            $(this).next().text('输入成功').removeClass('state1').addClass('state4');
            ok7=true;
        }else{
            $(this).next().text('企业名应该为3-20位之间').removeClass('state1').removeClass('state4').addClass('state3');
        }
    });
    // 验证企业注册号
    $('input[name="businessNo"]').focus(function(){
        $(this).next().text('企业注册号不能为空').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
        var reg = new RegExp("^[A-Za-z0-9]+$"); 
        var reg1 = new RegExp("^[0-9]*$"); 
        var obj = document.getElementById("business_no");  
        if($(this).val().length==0){
            $(this).next().text('企业注册号不能为空').removeClass('state1').removeClass('state4').addClass('state3');
        }else{
        	if(!reg.test(obj.value)&&!reg1.test(obj.value)){
        		$(this).next().text('企业注册号格式错误').removeClass('state1').removeClass('state4').addClass('state3');
        	}else{
        		 if($(this).val().length==15||$(this).val().length==18){
        			 $(this).next().text('输入成功').removeClass('state1').addClass('state4');
                     ok8=true;
            	 }else{
            		 $(this).next().text('统一社会信用代码为18位，营业执照号为15位，请检查').removeClass('state1').removeClass('state4').addClass('state3');
            	 };
        	};
        };
    });
    // 验证企业类型
    $('input[name="industry"]').focus(function(){
        $(this).next().text('请选择企业类型').removeClass('state1').removeClass('state3').removeClass('state4').addClass('state2');
    }).blur(function(){
        if($(this).val().length==0){
            $(this).next().text('请选择企业类型').removeClass('state1').removeClass('state4').addClass('state3');
        }
    });

    function doregbtn(){
    	$("#regist").attr("disabled", true);
    	if(ok1 && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9) {
			$.ajax({
				url:"<%=path%>/customer/regist.do",
				type:'post',
				dataType:"json",
				traditional:true,
				data:$('#regForm').serialize(),
				success:function(data){
					alert(data.msg);
					location.href="<%=path%>/customer/logonUI.do";
				},
				error:function(){
					alert("失败，请检查网络");
					location.href="<%=path%>/customer/logonUI.do";
				}
			});
		} else {
			alert("请正确完成注册！");
	    	$("#regist").attr("disabled", false);
			return false;
		}
    }
    function doregbtn1(){
    	$("#regist1").attr("disabled", true);
	  with(regForm){
		  if (username.value.indexOf("fuck") >= 0 || username.value.indexOf("caonima") >= 0 || username.value.indexOf("fuckoff") >= 0) {
			  alert("警告信息。");
              username.focus();
              ok2=false;
              return false;
         }
       }
    	if(ok1 && ok2 && ok3 && ok4 && ok5 && ok6) {
			$.ajax({
				url:"<%=path%>/user/reg_start.do",
				type:'post',
				dataType:"json",
				traditional:true,
				data:$('#regForm').serialize(),
				success:function(data){
					alert(data.msg);
					location.href="<%=path%>/customer/logonUI.do";
				},
				error:function(){
					alert("失败，请检查网络");
					location.href="<%=path%>/customer/logonUI.do";
				}
			});
		} else {
			alert("请正确完成注册！");
	    	$("#regist1").attr("disabled", false);
			return false;
		}
    }

		$("#goback").click(function(){
			location.href="<%=path%>/customer/logonUI.do";
		});
	</script>	
</body>
</html>