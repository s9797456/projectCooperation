<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
		<%@ include file="/WEB-INF/Page/Share/state.jsp"%>
		<%@ include file="/WEB-INF/Page/Share/title.jsp"%>
		<%@ include file="/WEB-INF/Page/Share/meta.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<link rel="stylesheet" type="text/css" href="<%=path%>/Styles/login/reset.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/Styles/login/login.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/Resources/layer/skin/layer.css" id="layui_layer_skinlayercss">
<!--[if lt IE 9]>
<script src="/themes/v5/public/js/html5shiv.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/themes/v5/public/js/respond.min.js" type="text/javascript" charset="utf-8"></script>
<![endif]-->
<!--[if IE 6]>
<script src="/themes/v5/public/js/DD_belatedPNG_0.0.8a.js"  type="text/javascript"></script>
<script>DD_belatedPNG.fix('.centerIcon');</script>
<![endif]-->
<script>
		var _ctxPath="<%=request.getContextPath() %>";
</script>
<style>.errorTips{display:none}</style>
</head>
<!--初始化时,隐藏错误信息-->
<body>
<input type="hidden" value="${CheckCode }" id="CheckCode"/>
    <div class="loginWrapper clearfix">
        <div class="banWrapper" style="background: #00a0ea">
        <div class="banContent">
        <a>
	        <c:if test="${empty Crc_organization}"><img src="<%=path%>/Images/replaceImage/homepage.jpg" border="0"></c:if>
	        <c:if test="${! empty Crc_organization}">
			    <c:choose>
        			<c:when test="${! empty Crc_organization.imgurl}">
        				<img src="<%=path%>/'${Crc_organization.imgurl }'" border="0">
        			</c:when>
        			<c:otherwise>
        				<img src="<%=path%>/Images/replaceImage/homepage.jpg" border="0">
        			</c:otherwise>
        		</c:choose>
	        </c:if>
        </a>
        </div>
        </div>
        
        <div class="loginMain">
        <div style="margin-top: 25%;">
        <div class="loginLogoDiv" style="text-align: center;"><a style="color: #03c5ff;font-size: 25px" ><strong><c:if test="${empty Crc_organization}">企业信用评价平台</c:if><c:if test="${!empty Crc_organization}">${ Crc_organization.name}</c:if></strong></a></div>
        <form action="<%=path%>/customer/logon.do" class="formLogin" method="post" id="login_form" onsubmit="return false;">
        <div class="loginList loginListUser">
        <label></label>
        <input type="text" class="loginText" name="cellphone" id="cellphone" value="" placeholder="手机号/用户名">
        <span class="errorTips"><i></i><em></em></span>
        </div>
        <div class="loginList loginListPwd">
        <label></label>
        <input type="password" class="loginText" name="password" id="password" value="" placeholder="密码">
        <span class="errorTips"><i></i><em></em></span>
        </div>
         <div id="captcha">
            <p id="wait" class="show">正在加载验证码......</p>
            <p id="notice" class="hide">请先完成验证</p>
        </div>
		<div class="sysError" style="display:none;">
				<span><i></i><em></em></span>
		</div>
        <input class="loginBtn" type="button" id="loginBtn" disabled value="登录" >
		<p class="loginList" style="margin-top: 10px;" ><a href="<%=path%>/customer/findPWDByCellphoneUI.do" >忘记密码?</a></p>
        <p class="have">还没有账号，<c:if test="${empty Crc_organization}"><a href="<%=path%>/customer/jumpToRegist.do">立即注册<i></i></a></c:if><c:if test="${!empty Crc_organization}"><a href="<%=path%>/customer/jumpToRegist.do">立即注册<i></i></a></c:if></p>
        </form>
        </div>
        </div>
    </div>
    <!-- footer end -->
<script src="<%=path%>/Script/login/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Resources/layer/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Script/login/jquery.form.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Script/login/regPublic.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Script/login/browser.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Resources/geetest/gt.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
//防止页面后退
history.pushState(null, null, document.URL);
window.addEventListener('popstate', function () {
    history.pushState(null, null, document.URL);
});
var broswer = getBroswer();
if(!(broswer.broswer=="IE"&&broswer.version=="11.0")){
	var info="警告:您当前浏览器为"+broswer.broswer+"-"+broswer.version+"</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;建议使用浏览器内核为IE11的浏览器！";
	$('.sysError').show().find('em').html(info);
}
function isIE() { //ie?
	 if (!!window.ActiveXObject || "ActiveXObject" in window)
	 return true;
	 else
	 return false;
}
var CheckCode=$('#CheckCode').val();
if(CheckCode=='true'){
	var handler = function (captchaObj) {
			 $("#loginBtn").click(function (e) {
					var result = captchaObj.getValidate();
		            if (!result) {
		                $("#notice").show();
		                setTimeout(function () {
		                    $("#notice").hide();
		                }, 2000);
		                e.preventDefault();
		            }else{
		            	login(captchaObj);
		            }
		        });
		        // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
		        captchaObj.appendTo("#captcha");
		        //监听验证成功事件，进行二次验证
		        captchaObj.onSuccess(function () {
		           		$("#loginBtn").attr("disabled",false);
		        });
		        captchaObj.onReady(function () {
		            $("#wait").hide();
		        });
	    // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
	};
	$.ajax({
	    url: _ctxPath+'/geetest/register.do?t=' + (new Date()).getTime(), // 加随机数防止缓存
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
	$("#loginBtn").val('登录').prop("disabled",false);
	$("#loginBtn").click(function (e) {
		 $('.sysError').css('display','none');
         $('.errorTips').css('display','none');
         $("#loginBtn").val('登录中...').prop("disabled",true);
         var cellphone = $("#cellphone").val();
         var password = $("#password").val();

         cellphone = $.trim(cellphone);
         password = $.trim(password);
         if(cellphone == null || cellphone == "" || password == null || password == ""){
 			layer.msg("请先输入帐号和密码");
 			$("#loginBtn").val('登录').prop("disabled",true);
 			return;
 		}
         $("#login_form").ajaxSubmit(function(data){
        	 if(data.status == 0){
        		 layer.msg("登陆成功", {icon: 1, time: 1500}, function() {});
	             setTimeout(function() {
	            	 window.location.href = "<%=path%>"+data.type;
	             }, "1500");
		    	 
		    }else{
		    		  if (data.status==1){
			    		  var info="手机号或密码错误";
		                  layer.msg(info);
	                      showError(info, $('#cellphone'), true);
			    	  }else if(data.status==2){
			    		  var info="验证码输入错误";
			    		  layer.msg(info);
	                      showError(info, $('#captcha'), true);
			    	  }else if(data.status==3){
			    		  var info="请先联系管理员激活后再登录";
			    		  layer.msg(info);
	                  	$('.sysError').show().find('em').html(info);
			    	  }else if(data.status==4){
			    		  var info="用户已经被注销";
			    		  layer.msg(info);
	                  	$('.sysError').show().find('em').html(info);
					  } else if(data.status==-100){
			    		  layer.msg(data.msg);
	                  	$('.sysError').show().find('em').html(data.msg);
					  } 
	                    $("#loginBtn").val('登录').prop("disabled",true);
		    }
     }).error(function(xhr, status, info){
		})
 });
}


</script>

<script type="text/javascript">
	document.oncontextmenu=rightMouse;
	function rightMouse() {
		return false;
	}
    
        function login(captchaObj){
        	$('.sysError').css('display','none');
            $('.errorTips').css('display','none');
            $("#loginBtn").val('登录中...').prop("disabled",true);
            var cellphone = $("#cellphone").val();
            var password = $("#password").val();
  
            cellphone = $.trim(cellphone);
            password = $.trim(password);
            if(cellphone == null || cellphone == "" || password == null || password == ""){
    			layer.msg("请先输入帐号和密码");
    			$("#loginBtn").val('登录').prop("disabled",false);
    			return;
    		}
            $("#login_form").ajaxSubmit(function(data){
            	if(data.status==0){
            		console.log(data.type);
            		 layer.msg("登陆成功", {icon: 1, time: 1500}, function() {});
    	             setTimeout(function() {
    	            	 window.location.href = "<%=path%>"+data.type;
    	             }, "1500");
		    	  }else{
		    		  if (data.status==1){
			    		  var info="用户名、手机号或密码错误";
		                  layer.msg(info);
	                      showError(info, $('#cellphone'), true);
			    	  }else if(data.status==2){
			    		  var info="验证码输入错误";
			    		  layer.msg(info);
	                      showError(info, $('#captcha'), true);
			    	  }else if(data.status==3){
			    		  var info="请先联系管理员激活后再登录";
			    		  layer.msg(info);
	                  	$('.sysError').show().find('em').html(info);
			    	  }else if(data.status==4){
			    		  var info="用户已经被注销";
			    		  layer.msg(info);
	                  	$('.sysError').show().find('em').html(info);
					  }else if(data.status==-100){
			    		  layer.msg(data.msg);
		                  	$('.sysError').show().find('em').html(data.msg);
						  }
	                    captchaObj.reset();
	                    $("#loginBtn").val('登录').prop("disabled",true);
		    	  } 
                
                });
        }
        $(document).ready(function(){
        	//bind keyCode=13 Event
            document.onkeydown = function(e){
                var ev = document.all ? window.event : e;
                if(ev.keyCode==13) {
                    $("#loginBtn").click();

                }
            };
        });
        function showError(msg, selector, isFocus) {
            selector.parent().find('.errorTips').find('em').html(msg);
            selector.parent().find('.errorTips').fadeIn();
            $(".loginBtn").val('登录').prop('disabled', false);

            if(isFocus){
                selector.focus();
            }
        }

    </script>

</body></html>