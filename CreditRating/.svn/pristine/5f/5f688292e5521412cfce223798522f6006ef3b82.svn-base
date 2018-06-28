<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
<%@ include file="/WEB-INF/Page/Share/state.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <script>
			var _ctxPath="<%=request.getContextPath() %>";
		</script>
<title>${sessionScope.obj_name}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=path%>/Styles/loginStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/Resources/bootstrap/css/bootstrap.css">
<style type="text/css">
	#submit:disabled{ background: #ddd; }
</style>
</head>
<body>
<input type="hidden" value="true" id="CheckCode"/>
<form  id='myForm' >
<dl class="admin_login" style="margin-top: 5%;">
 <dt>
 <a><img src="<%=path%>/Images/replace/login_logo.png"  height="170" width="150"></a>
 <p>
 </p>
  <strong><h1></h1></strong>
 </dt>
 <dd class="user_icon">
  <input type="text" placeholder="账号" id="userName" name="userName"  class="login_txtbx"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="密码" id="password" name="password" class="login_txtbx"/>
 </dd>
         <div id="captcha">
            <p id="wait" class="show">正在加载验证码......</p>
            <p id="notice" class="hide">请先完成验证</p>
        </div>
 <dd>
 <div style="width:100%;float:center;">
  <input type="button" value="登  陆" disabled="disabled" class="submit_login" id="submit"/>
 </div><%--
  <div style="width:49%;float:right;">
  <input type="button" value="注  册" class="submit_reg" id="reg" />
 </div>
 --%></dd>
 <%--<dd style="height:30px">
  <p><a href="<%=path%>/user/jumpToFindpwd.do" style="color: white;">忘记密码？</a></p>
 </dd>
 --%><dd style="height:50px;text-align:center">
  <p><h4><span name="msg" class='state1' ></span></h4></p>
 </dd>
</dl>
</form>
<script src="<%=path%>/Script/jquery.js"></script>
<script src="<%=path%>/Script/Particleground.js" ></script>
<script src="<%=path%>/Resources/bootstrap/js/bootstrap.js" ></script>
<script src="<%=path%>/Resources/geetest/gt.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var CheckCode=$('#CheckCode').val();
if(CheckCode=='true'){
	var handler = function (captchaObj) {
	    $("#submit").click(function (e) {
	            var result = captchaObj.getValidate();
	            if (!result) {
	            	$("#notice").show();
	                setTimeout(function () {
	                	$("#notice").hide();
	                }, 2000);
	                e.preventDefault();
	            }else{
	            	sub(captchaObj);
	            }
	        });
	        // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
	        captchaObj.appendTo("#captcha");
	        captchaObj.onSuccess(function () {
           		$("#submit").attr("disabled",false);
        	});
	        captchaObj.onReady(function () {
	            $("#wait").hide();
	        });
	    // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
	};
	$.ajax({
	    url: '<%=path%>/geetest/register.do?t=' + (new Date()).getTime(), // 加随机数防止缓存
	    type: "get",
	    dataType: "json",
	    success: function (data) {
	        // 调用 initGeetest 初始化参数
	        // 参数1：配置参数
	        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
	        initGeetest({
	            gt: data.gt,
	            challenge: data.challenge,
	            new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
	            offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
	            product: "float", // 产品形式，包括：float，popup
	            width: "100%"
	            // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
	        }, handler);
	    }
	});
}else{
	$("#captcha").hide();
	$("#submit").click(function (e) {
		String.prototype.trim= function () {
		return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
	};
	if($('#userName').val()==null || $('#userName').val().trim() == ""){
		$('span[name="msg"]').text("请输入用户名").removeClass('state1').removeClass('state3').addClass('state2');
		$("#submit").val('登录').prop("disabled",false);
		return false;
	}else if($('#password').val()==null || $('#password').val().trim() == ""){
		$('span[name="msg"]').text("请输入密码").removeClass('state1').removeClass('state3').addClass('state2');
		$("#submit").val('登录').prop("disabled",false);
		return false;
	}
	$.ajax({
				url:"<%=path%>/user/logon.do",
				type:"post",
				dataType:"json",
				traditional:true,
				data:$('#myForm').serialize(),
				success:function(data){
						if(data.status==false){
							$('span[name="msg"]').text(data.msg).removeClass('state1').addClass('state2');
							 $("#submit").val('登录').prop("disabled",false);
						}else{
							$('span[name="msg"]').text("正在加载，请耐心等待！").removeClass('state1').removeClass('state2').addClass('state3');
							location.href='<%=path%>/control/navigation/main.do';
							 $("#submit").val('加载中..').prop("disabled",true);
						}
				},
				error:function(xhr,status,error){
					alert("服务暂停，请联系管理员！");
				}
    	});
});
}

</script>
<script>
$(document).ready(function() {
  //特效背景特效
  $('body').particleground({
    dotColor: '#EFF2F5',
    lineColor: '#EFF2F5'
  });
  $('canvas').css('margin-top','-5%');
});
</script>
<script>
	document.oncontextmenu=rightMouse;
	function rightMouse() {
		return false;
	}
  
   $('input[name="userName"]').focus(function(){$('span[name="msg"]').text("");});
   $('input[name="password"]').focus(function(){$('span[name="msg"]').text("");});

	function sub(captchaObj){
		$('#submit').attr("disabled",true);
		$('span[name="msg"]').text("正在加载，请耐心等待！").removeClass('state1').removeClass('state2').addClass('state3');
		String.prototype.trim= function () {
			return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
		};
		if($('#userName').val()==null || $('#userName').val().trim() == ""){
			$('span[name="msg"]').text("请输入用户名").removeClass('state1').removeClass('state3').addClass('state2');
			$("#submit").val('登录').prop("disabled",false);
			return false;
		}else if($('#password').val()==null || $('#password').val().trim() == ""){
			$('span[name="msg"]').text("请输入密码").removeClass('state1').removeClass('state3').addClass('state2');
			$("#submit").val('登录').prop("disabled",false);
			return false;
		}
		$.ajax({
					url:"<%=path%>/user/logon.do",
					type:"post",
					dataType:"json",
					traditional:true,
					data:$('#myForm').serialize(),
					success:function(data){
							if(data.status==false){
								captchaObj.reset();
								$('span[name="msg"]').text(data.msg).removeClass('state1').removeClass('state3').addClass('state2');
								 $("#submit").val('登录').prop("disabled",false);
							}else{
								 $("#submit").val('加载中..').prop("disabled",true);
								location.href='<%=path%>/control/navigation/main.do';
							}
					},
					error:function(xhr,status,error){
						$('span[name="msg"]').text("服务暂停，请联系管理员！").removeClass('state1').removeClass('state3').addClass('state2');
					}
	    	});
	}

	$("#reg").click(function(){
		location.href='<%=path%>/user/registUI.do';
	});
</script>
</body>
</html>
