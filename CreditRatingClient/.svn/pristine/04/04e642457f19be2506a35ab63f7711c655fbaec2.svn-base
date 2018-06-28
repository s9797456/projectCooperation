<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
<%@ include file="/WEB-INF/Page/Share/state.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<link rel="stylesheet" href="<%=path%>/Resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=path%>/Styles/login/regstyle.css">
<link rel="stylesheet" href="<%=path%>/Styles/login/reset.css"">
<link rel="stylesheet" href="<%=path%>/Resources/layer/skin/layer.css" id="layui_layer_skinlayercss">
<!--[if lt IE 9]>
<script src="/themes/v5/public/js/html5shiv.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/themes/v5/public/js/respond.min.js" type="text/javascript" charset="utf-8"></script>
<![endif]-->
<!--[if IE 6]>
<script src="/themes/v5/public/js/DD_belatedPNG_0.0.8a.js"  type="text/javascript"></script>
<script>DD_belatedPNG.fix('.centerIcon');</script>
<![endif]-->
</head>    
<body>
 
    <div class="registerContent">
   		<div style="float: left;margin: 20px;"><a href=""><c:if test="${empty Crc_organization}"><img src="<%=path%>/Images/replaceImage/reglogo.png"></c:if></a></div>
        <div class="registerMain">
            
            <div class="regDiv">
           		<div class="regLogo"><a style="color: #fff;font-size: 25px" ><strong><c:if test="${empty Crc_organization}">企业信用评价平台</c:if><c:if test="${!empty Crc_organization}">${ Crc_organization.name}</c:if></strong></a></div>
                <form name="registerForm" id="registerForm" action="<%=path%>/customer/regist.do" method="post" class="regForm" autocomplete="off" onsubmit="return false;">
                    <div class="inputDiv">
                        <input type="tel" class="regInput" name="cellphone" value="" id="cellphone" placeholder="手机号码" autocomplete="off" tabindex="1">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv getCode">
                        <input type="text" class="regInput" name="codeNum" value="" id="codeNum" placeholder="验证码" autocomplete="off" tabindex="3">
                        <button class="smsbtn getCodea"type="button">获取验证码</button>
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input type="text" class="regInput" name="password" value="" id="password" placeholder="密码(长度为6～18)" autocomplete="off" tabindex="2">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input type="tel" class="regInput" name="realname" value="" id="realname" placeholder="联系人(长度为2~12)" autocomplete="off" tabindex="1">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input type="tel" class="regInput" name="name" value="" id="name" placeholder="企业名(长度为3~20)" autocomplete="off" tabindex="1">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input type="tel" class="regInput" name="uscc" value="" id="uscc" placeholder="注册号(统一社会信用代码18位，营业执照号15位)" autocomplete="off" tabindex="1">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <label>
                            <input type="checkbox" name="isRead" value="1" checked="checked" id="checkedd">&nbsp;&nbsp;我已阅读并接受<a href="javascript:void(0);" onclick="readDeal()">《企业信用评价服务协议》</a></label>
                        <p class="errorTips" id="checkedTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <p class="errorTips" id="sysError"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input style="letter-spacing: 4px;width:100%" type="button" class="regBtn" value="注册">
                        <p class="have">已有账号，<a href="<%=path%>/customer/logonUI.do">直接登录<i></i></a></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%--阅读协议 --%>
    <div class="modal " id="readinfo" tabindex="-1" role="dialog" 
							aria-labelledby="readinfoModalLabel" aria-hidden="true" data-remote="true" data-backdrop="static">
			<div class="modal-dialog" style="width:900px">
				<div class="modal-content">
					 <div class="modal-header">
					    <h4 class="modal-title" id="readinfoModalLabel" style="color:black;margin-left:-780px;">
					       	阅读协议
					    </h4>
					 </div>
					 <div class="modal-body" id="readinfoModalBody" style="color:black;line-height: 30px;">
						<div align="center">
							<b>企业信用评价服务协议</b>
						</div>
						<div style="text-align:left;line-height:40px;">
							<b>甲方： 委托企业</b>
							<br>
							<b>乙方：&nbsp;<c:if test="${not empty Crc_organization }">${Crc_organization.name }</c:if><c:if test="${empty Crc_organization }">${corp_name }</c:if></b>
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、 本协议是甲方与乙方之间的法律协议。
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、 甲方了解并同意：甲方点击“同意”按钮并完成注册，本协议自动生效。乙方视甲方愿意受其约束，并授权乙方根据业务需要而采集甲方信用相关的所有信息。如果发生纠纷，甲方不得以未仔细阅读为由进行抗辩。
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、甲方一旦注册成功，成为本站的合法注册会员，甲方可根据需要修改密码，甲方有义务保证帐号和密码的安全，因保管不当引起的任何损失或损害，由甲方自行承担责任。另外甲方要对以其注册的会员名进行的所有活动和事件负全责。甲方若发现任何非法使用注册会员账户或存在安全漏洞的情况，请立即通知乙方。
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、因不可预见因素导致网络中断，导致乙方不能正常提供服务，乙方不承担任何责任。因为法律认定的不可抗力原因造成的损失，双方均不承担违约责任。 
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5、乙方提供的信用评价服务是基于甲方提供的原始数据，甲方对因原始数据的真实性、完整性造成的一切后果负责。
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）	甲方保证在系统中提交信息的准确性。
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）甲方在信息确认时选择“同意”并提交后，乙方视甲方同意乙方针对其选取的第三方信用信息，并认可乙方基于此信息完成的信用评价报告。
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6、本次信用评价业务的结果仅针对其原始数据有效；仅供相关当事方作为商业辅助决策的要素之一使用。乙方不承担甲方及相关当事人的任何商业风险，也不承担由于非控因素和数据而引起的相关损失。
						</div>
						<div style="text-align:right;">
							特此承诺！
						</div>
					 <div class="modal-footer">
					    <button type="button" class="btn btn-primary" data-dismiss="modal">
					       	关闭
					    </button>
					 </div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>

<script src="<%=path%>/Script/login/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Resources/bootstrap/js/bootstrap.min.js"  type="text/javascript" charset="utf-8" ></script>
<script src="<%=path%>/Resources/layer/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Script/login/jquery.form.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Script/login/regPublic.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Resources/geetest/gt.js" type="text/javascript" charset="utf-8"></script>

<script>
			function readDeal(){
				$('#readinfo').modal('show');
			}
</script>
<script type="text/javascript">

        $(function() {
            $('#password').focus(function() {
                $(this).attr('type', 'password');
            });
            document.onkeydown = function(e) {
                var ev = document.all ? window.event : e;
                if (ev.keyCode == 13) {
                    $(".regBtn").click();
                }
            };
        	 $('#cellphone').on('blur', function() {
                 $.ajax({
                 	url:"<%=path%>/customer/cellphoneExist.do",
             		type:"post",
             		async : false,
             		dataType:"json",
             		data:$('#cellphone').serialize(),
             		success:function(data){
             			if (data.status == false) {
             				showError(data.msg, $('#cellphone'), true);
             			}else{
             				$('#cellphone').siblings('.errorTips').find('em').html('');
      			            $('#cellphone').siblings('.errorTips').hide();
             			}
             		}
                 });  
             });
             $('.regBtn').click(function() {

                 var cellphone 	= $("#cellphone").val();
                 var password 	= $("#password").val();
                 var codeNum 	= $("#codeNum").val();
                 var realname 	= $("#realname").val();
                 var name  		= $("#name").val();
                 var uscc 			= $("#uscc").val();

                 cellphone 	= $.trim(cellphone);
                 password 	= $.trim(password);
                 codeNum 	= $.trim(codeNum);
                 realname 	= $.trim(realname);
                 name 			= $.trim(name);
                 uscc 			= $.trim(uscc);

                 $(".regBtn").val('注册中...').prop('disabled', true);
                 $('.errorTips').find('em').html('');
                 $('.errorTips').hide();
				var num = 0;

                 if (!isPhone(cellphone)) {
                     showError('手机号码格式不正确', $('#cellphone'), true);
                 } else  if (password.length < 6) {
                     showError('密码不能少于6位', $('#password'), true);
                 }else  if (isNum(realname)) {
                     showError('联系人不能是数字', $('#realname'), true);
                 }else  if (realname.length < 2) {
                     showError('联系人不能少于2位', $('#realname'), true);
                 }else  if (name.length < 3) {
                     showError('企业名不能少于3位', $('#name'), true);
                 }else  if (!isUsccLength(uscc)) {
                     showError('注册号长度为15或18位', $('#uscc'), true);
                 } else if (!$('#checkedd').is(":checked")) {
                     $('#checkedTips').find('em').html('请接受企业信用评价服务协议');
                     $('#checkedTips').fadeIn();
                     $(".regBtn").val('注册').removeAttr('disabled');
                     return false;
                 } else { //基础验证通过
                     $("#registerForm").ajaxSubmit(function(data) {
                    	 if(data.status){
                    		 layer.msg(data.msg, {
                                 icon: 1,
                                 time: 1500
                             }, function() {});
                             setTimeout(function() {
                                 window.location.href = '<%=path%>/customer/loginUI.do';
                             }, "1500");
                    	 }else{
                    		 layer.msg(data.msg);
                             showError(data.msg, $('#sysError'), true);
                             $(".regBtn").val('注册').prop('disabled', false);
                    	 }
                     });
                 }
             });
             // 点击发送短信按钮时
             $('.smsbtn').click(function() {
                 $('.errorTips').find('em').html('');
                 $('.errorTips').hide();
                 $('.smsbtn').prop('disabled', true);
                 var cellphone = $('#cellphone').val().trim();
                 if (!isPhone(cellphone)) {
                     showError('请输入正确的手机号', $('#cellphone'), true);
                     $('.smsbtn').prop('disabled', false);
                 } else {
                     $('.smsbtn').html('验证码发送...').prop('disabled', true);
                     $.post('<%=path%>/customer/sendSMS.do', {
                         'cellphone': cellphone,
                     }, function(data) {
                        if(data.status){
                        	layer.msg(data.msg);
                            resetSmsBtn(60);
                        }else{
                        	 layer.msg(data.msg);
                             showError(data.msg, $('#sysError'), true);
                             $('.smsbtn').html('获取验证码').prop("disabled", false);
                        }
                     })
                 }
             });
             function showError(msg, selector, isFocus) {
                 selector.parent().find('.errorTips').find('em').html(msg);
                 selector.parent().find('.errorTips').fadeIn();
                 $(".regBtn").val('注册').prop('disabled', false);

                 if(isFocus){
                     selector.focus();
                 }
             }
             // 重置短信发送按钮
             function resetSmsBtn(index) {
                 var init = setInterval(function() {
                     index--;
                     if (index <= 0) {
                         clearInterval(init);
                         $('.smsbtn').html('获取验证码').prop('disabled', false);
                     } else {
                         $('.smsbtn').html('重新发送(' + index + ')').prop('disabled', true);
                     }
                 }, 1000);
             }
        });
</script>


</body></html>