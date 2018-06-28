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
            <ul class="nav nav-tabs nav-justified" >
			  <li class="active" data-value = "ent"  ><a href="javascript:;" >企业注册</a></li>
			  <li data-value = "per" ><a href="#">个人注册</a></li>
			</ul>
			
            <div class="regDiv ent" >
           		<div class="regLogo"><a style="font-size: 25px" ><strong><c:if test="${empty Crc_organization}">企业信用评价平台</c:if><c:if test="${!empty Crc_organization}">${ Crc_organization.name}</c:if></strong></a></div>
                <form name="registerForm" id="registerEntForm" action="<%=path%>/customer/registEnt.do" method="post" class="regForm" autocomplete="off" onsubmit="return false;">
                    <div class="inputDiv">
                        <input type="tel" class="regInput" name="cellphone" id="cellphoneEnt" placeholder="手机号码" autocomplete="off" tabindex="1">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv getCode">
                        <input type="text" class="regInput" name="codeNum" id="codeNumEnt" maxlength="6" placeholder="验证码" autocomplete="off" tabindex="2">
                        <button class="smsbtnEnt getCodea"type="button">获取验证码</button>
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input type="text" class="regInput" name="password" id="passwordEnt" maxlength="18" placeholder="密码(长度为6～18)" autocomplete="off" tabindex="3">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input type="text" class="regInput" name="realname" id="realnameEnt"  maxlength="22" placeholder="联系人(长度为2~12)" autocomplete="off" tabindex="4">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input type="text" class="regInput" name="name" id="nameEnt" maxlength="20" placeholder="企业名(长度为3~20)" autocomplete="off" tabindex="5">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input type="text" class="regInput" name="uscc" id="uscc" maxlength="18" placeholder="注册号(统一社会信用代码18位，营业执照号15位)" onkeyup="this.value=this.value.toUpperCase()" autocomplete="off" tabindex="6">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <label>
                            <input type="checkbox" name="isRead" value="1" id="isReadEnt">&nbsp;&nbsp;我已阅读并接受<a href="javascript:;" onclick="readEntDeal()" tabindex="7">《企业信用评价服务协议》</a></label>
                        <p class="errorTips" id="checkedTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <p class="errorTips" id="sysErrorEnt"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input style="letter-spacing: 4px;width:100%" type="button" class="regEntBtn regBtn" tabindex="8" value="注册">
                        <p class="have">已有账号，<a href="<%=path%>/customer/logonUI.do">直接登录<i></i></a></p>
                    </div>
                </form>
            </div>
            <div class="regDiv per" style="display: none;" >
           		<div class="regLogo"><a style="font-size: 25px" ><strong>个人信用评价平台</strong></a></div>
                <form name="registerForm" id="registerPersonalForm" action="<%=path%>/customer/registPersonal.do" method="post" class="regForm" autocomplete="off" onsubmit="return false;">
                    <div class="inputDiv">
                        <input type="tel" class="regInput" name="cellphone" id="cellphoneRepsonal" placeholder="手机号码" autocomplete="off" tabindex="1">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv getCode">
                        <input type="text" class="regInput" name="codeNum" id="codeNumRepsonal" placeholder="验证码" autocomplete="off" tabindex="2">
                        <button class="smsbtnRepsonal getCodea"type="button">获取验证码</button>
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input type="text" class="regInput" name="password" id="passwordRepsonal" placeholder="密码(长度为6～18)" autocomplete="off" tabindex="3">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input type="text" class="regInput" name="realname" id="realnameRepsonal" placeholder="姓名(长度为2~12)" autocomplete="off" tabindex="4">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input type="text" class="regInput" name="idcard" id="repsonalID" placeholder="身份证号(18位或15位,字母大写)" autocomplete="off" tabindex="5">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <label>
                            <input type="checkbox" name="isRead" value="1" checked="checked" id="checkedRepsonal" tabindex="7">&nbsp;&nbsp;我已阅读并接受<a href="javascript:;" tabindex="6" onclick="readRepsonalDeal()">《个人信用评价服务协议》</a></label>
                        <p class="errorTips" id="checkedTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <p class="errorTips" id="sysErrorRepsonal"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input style="letter-spacing: 4px;width:100%" type="button" class="regRepBtn regBtn" tabindex="8" value="注册">
                        <p class="have">已有账号，<a href="<%=path%>/customer/logonUI.do">直接登录<i></i></a></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%--阅读协议 --%>
    <div class="modal " id="readEntInfo" tabindex="-1" role="dialog" 
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
		 <%--阅读协议 --%>
    <div class="modal " id="readRepsonalInfo" tabindex="-1" role="dialog" 
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
							<b>个人信用评价服务协议</b>
						</div>
						<div style="text-align:left;line-height:40px;">
							<b>甲方： 委托个人</b>
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

<script type="text/javascript">
		function readEntDeal(){
			$('#readEntInfo').modal('show');
		}
		function readRepsonalDeal(){
			$('#readRepsonalInfo').modal('show');
		}
		//切换ent/repsonal
		$(".nav").on("click","li",function(){
			$(this).attr("class","active").siblings("li").css("background-color","rgba(228, 228, 228, 0.5)").removeClass();
			if($(this).attr("data-value") == "ent"){
				$(".ent").css("display","block").siblings(".per").css("display","none");
			}else{
				$(".ent").css("display","none").siblings(".per").css("display","block");
			}
		});
		/**
 			ent
 		*/
     	 $('#cellphoneEnt').on('blur', function() {
              $.ajax({
              	url:"<%=path%>/customer/cellphoneExist.do",
          		type:"post",
          		async : false,
          		dataType:"json",
          		data:$('#cellphoneEnt').serialize(),
          		success:function(data){
          			if (data.status == false) {
          				showError(data.msg, $('#cellphoneEnt'), true);
          			}else{
          				$('#cellphoneEnt').siblings('.errorTips').find('em').html('');
   			            $('#cellphoneEnt').siblings('.errorTips').hide();
          			}
          		}
              });  
     	 });
		//密码
     	$('#passwordEnt').focus(function() {
            $(this).attr('type', 'password');
        });
     	$('#passwordEnt').on('blur', function() {
       	 	if($(this).val().length >= 6 && $(this).val().length <=18 && $(this).val()!=''){
	       		var re = new RegExp(/^[a-zA-Z][0-9a-zA-Z_]{5,17}$/);
	       		if (re.test($(this).val())){
	       			$(this).siblings('.errorTips').find('em').html('');
		       		$(this).siblings('.errorTips').hide();
	       	    }else{
	       	    	showError('密码长度6~18位且必须以字母开头',$(this), true);
	       	    }
	        }else{
	        	 showError('密码长度6~18位且必须以字母开头',$(this), true);
	        }
        });
     	//验证
     	$('#codeNumEnt').on('blur', function() {
        	 if($(this).val().length == 6 && $(this).val().trim() != ''){
        		 var re = new RegExp(/^[0-9]{6}$/);
 	       		if (re.test($(this).val())){
 	       			$(this).siblings('.errorTips').find('em').html('');
 		       		$(this).siblings('.errorTips').hide();
 	       	    }else{
 	       	    	showError('验证码输入有误',$(this), true);
 	       	    }
 	        }else{
 	        	 showError('验证码输入有误',$(this), true);
 	        }
         });
     	//联系人
     	$('#realnameEnt').on('blur', function() {
       	 	if($(this).val().length > 0 && $(this).val().trim() != ''){
	       	 	var re = new RegExp(/^[a-zA-Z\u4e00-\u9fa5]+$/);
	       		if (re.test($(this).val())){
	       			$(this).siblings('.errorTips').find('em').html('');
		       		$(this).siblings('.errorTips').hide();
	       	    }else{
	       	    	showError('联系人只能输入中文及字母',$(this), true);
	       	    }
	        }else{
	        	 showError('联系人输入有误',$(this), true);
	        }
        });
     	//企业名
     	$('#nameEnt').on('blur', function() {
     		if($(this).val().length > 0 && $(this).val().trim() != ''){
     			var re = new RegExp(/^[a-zA-Z\u4e00-\u9fa5]+$/);
	       		if (re.test($(this).val())){
	       			$(this).siblings('.errorTips').find('em').html('');
		       		$(this).siblings('.errorTips').hide();
	       	    }else{
	       	    	showError('企业名只能输入中文及字母',$(this), true);
	       	    }
	        }else{
	        	 showError('企业名输入有误',$(this), true);
	        }
        });
     	//uscc
     	$('#uscc').on('blur', function() {
       	 	if($(this).val().length > 14 && $(this).val().length < 19 && $(this).val().trim() != ''){
	       	 	var re = new RegExp(/^[0-9a-zA-Z]{15,18}$/);
	       		if (re.test($(this).val())){
	       			$(this).siblings('.errorTips').find('em').html('');
		       		$(this).siblings('.errorTips').hide();
	       	    }else{
	       	    	showError('注册号长度15~18位',$(this), true);
	       	    }
	        }else{
	        	 showError('注册号输入有误',$(this), true);
	        }
        });
          $('.regEntBtn').click(function() {
              var cellphone = $("#cellphoneEnt").val();
              var password 	= $("#passwordEnt").val();
              var codeNum 	= $("#codeNumEnt").val();
              var realname 	= $("#realnameEnt").val();
              var name  	= $("#nameEnt").val();
              var uscc 		= $("#uscc").val();
              var isReadEnt = $("#isReadEnt").prop("checked");
              var num = 0;
              
              $(".regBtn").val('注册中...').prop('disabled', true);
              $('.errorTips').find('em').html('');
              $('.errorTips').hide();
			  
			  
              if (!isPhone(cellphone)) {
                  showError('手机号码格式不正确', $('#cellphoneEnt'), true);
              } else  if (password.length < 6) {
                  showError('密码不能少于6位', $('#passwordEnt'), true);
              }else  if (isNum(realname)) {
                  showError('联系人不能是数字', $('#realnameEnt'), true);
              }else  if (realname.length < 2) {
                  showError('联系人不能少于2位', $('#realnameEnt'), true);
              }else  if (name.length < 3) {
                  showError('企业名不能少于3位', $('#nameEnt'), true);
              }else  if (!isUsccLength(uscc)) {
                  showError('注册号长度为15或18位', $('#uscc'), true);
              } else if(!isReadEnt){
             	 $('#checkedTips').find('em').html('请阅读并接受企业信用评价服务协议');
                  $('#checkedTips').fadeIn();
                  $(".regBtn").val('注册').removeAttr('disabled');
                  return false;
              }else{ //基础验证通过
                  $("#registerEntForm").ajaxSubmit(function(data) {
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
                          showError(data.msg, $('#sysErrorEnt'), true);
                          $(".regBtn").val('注册').prop('disabled', false);
                 	 }
                  });
              }
          });
          // 点击发送短信按钮时
          $('.smsbtnEnt').click(function() {
              $('.errorTips').find('em').html('');
              $('.errorTips').hide();
              $('.smsbtnEnt').prop('disabled', true);
              var cellphone = $('#cellphoneEnt').val().trim();
              if (!isPhone(cellphone)) {
                  showError('请输入正确的手机号', $('#cellphoneEnt'), true);
                  $('.smsbtnEnt').prop('disabled', false);
              } else {
                  $('.smsbtnEnt').html('验证码发送...').prop('disabled', true);
                  $.post('<%=path%>/customer/sendSMS.do', {
                      'cellphone': cellphone,
                  }, function(data) {
                     if(data.status){
                     	layer.msg(data.msg);
                         resetSmsBtn($('.smsbtnEnt'),60);
                     }else{
                     	 layer.msg(data.msg);
                          showError(data.msg, $('#sysErrorEnt'), true);
                          $('.smsbtnEnt').html('获取验证码').prop("disabled", false);
                     }
                  })
              }
          });
          
          
     	 /**
     		repaonal
     	*/
     	 $('#cellphoneRepsonal').on('blur', function() {
             $.ajax({
             	url:"<%=path%>/customer/cellphoneExist.do",
         		type:"post",
         		async : false,
         		dataType:"json",
         		data:$('#cellphoneRepsonal').serialize(),
         		success:function(data){
         			if (data.status == false) {
         				showError(data.msg, $('#cellphoneRepsonal'), true);
         			}else{
         				$('#cellphoneRepsonal').siblings('.errorTips').find('em').html('');
         				$('#cellphoneRepsonal').siblings('.errorTips').hide();
         			}
         		}
             });  
    	 });
     	 
     	$('#passwordRepsonal').focus(function() {
            $(this).attr('type', 'password');
        });
     	$('#passwordRepsonal').on('blur', function() {
       	 	if($(this).val().length >= 6 && $(this).val().length <=18 && $(this).val()!=''){
	       		var re = new RegExp(/^[a-zA-Z][0-9a-zA-Z_]{5,17}$/);
	       		if (re.test($(this).val())){
	       			$(this).siblings('.errorTips').find('em').html('');
		       		$(this).siblings('.errorTips').hide();
	       	    }else{
	       	    	showError('密码长度6~18位且必须以字母开头',$(this), true);
	       	    }
	        }else{
	        	 showError('密码长度6~18位且必须以字母开头',$(this), true);
	        }
        });
     	//验证
     	$('#codeNumRepsonal').on('blur', function() {
        	 if($(this).val().length == 6 && $(this).val().trim() != ''){
        		 var re = new RegExp(/^[0-9]{6}$/);
 	       		if (re.test($(this).val())){
 	       			$(this).siblings('.errorTips').find('em').html('');
 		       		$(this).siblings('.errorTips').hide();
 	       	    }else{
 	       	    	showError('验证码输入有误',$(this), true);
 	       	    }
 	        }else{
 	        	 showError('验证码输入有误',$(this), true);
 	        }
         });
     	//真实姓名
     	$('#realnameRepsonal').on('blur', function() {
       	 	if($(this).val().length > 0 && $(this).val().trim() != ''){
	       	 	var re = new RegExp(/^[a-zA-Z\u4e00-\u9fa5]+$/);
	       		if (re.test($(this).val())){
	       			$(this).siblings('.errorTips').find('em').html('');
		       		$(this).siblings('.errorTips').hide();
	       	    }else{
	       	    	showError('姓名只能输入中文及字母',$(this), true);
	       	    }
	        }else{
	        	 showError('姓名输入有误',$(this), true);
	        }
        });
     	//ID
     	$('#repsonalID').on('blur', function() {
       	 	if($(this).val().length > 0 && $(this).val().trim() != ''){
       	 	 	var arg1 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
       	 	  	var arg2 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/;
	       		if (arg1.test($(this).val())||arg2.test($(this).val())||$(this).val() == ""){
	       			$(this).siblings('.errorTips').find('em').html('');
		       		$(this).siblings('.errorTips').hide();
	       	    }else{
	       	    	showError('身份证号码输入错误',$(this), true);
	       	    }
	        }
        });
     	
     	 // 点击发送短信按钮时
        $('.smsbtnRepsonal').click(function() {
            $('.errorTips').find('em').html('');
            $('.errorTips').hide();
            $('.smsbtnRepsonal').prop('disabled', true);
            var cellphone = $('#cellphoneRepsonal').val().trim();
            if (!isPhone(cellphone)) {
                showError('请输入正确的手机号', $('#cellphoneRepsonal'), true);
                $('.smsbtnRepsonal').prop('disabled', false);
            } else {
                $('.smsbtnRepsonal').html('验证码发送...').prop('disabled', true);
                $.post('<%=path%>/customer/sendSMS.do', {
                    'cellphone': cellphone,
                }, function(data) {
                   if(data.status){
                   	layer.msg(data.msg);
                       resetSmsBtn($('.smsbtnRepsonal'),60);
                   }else{
                   	 	layer.msg(data.msg);
                        showError(data.msg, $('#sysErrorRepsonal'), true);
                        $('.smsbtnRepsonal').html('获取验证码').prop("disabled", false);
                   }
                })
            }
        });
        $('.regRepBtn').click(function() {
            var cellphone = $("#cellphoneRepsonal").val();
            var password  = $("#passwordRepsonal").val();
            var codeNum   = $("#codeNumRepsonal").val();
            var realname  = $("#realnameRepsonal").val();
            var isReadRep = $("#checkedRepsonal").prop("checked");
            var num = 0;
            
            $(".regBtn").val('注册中...').prop('disabled', true);
            $('.errorTips').find('em').html('');
            $('.errorTips').hide();
			  
			  
            if (!isPhone(cellphone)) {
                showError('手机号码格式不正确', $('#cellphoneRepsonal'), true);
            } else  if (password.length < 6) {
                showError('密码不能少于6位', $('#passwordRepsonal'), true);
            }else  if (isNum(realname)) {
                showError('联系人不能是数字', $('#realnameRepsonal'), true);
            }else  if (realname.length < 2) {
                showError('联系人不能少于2位', $('#realnameRepsonal'), true);
            } else if(!isReadRep){
           	 $('#checkedTips').find('em').html('请阅读并接受企业信用评价服务协议');
                $('#checkedTips').fadeIn();
                $(".regBtn").val('注册').removeAttr('disabled');
                return false;
            }else{ //基础验证通过
                $("#registerPersonalForm").ajaxSubmit(function(data) {
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
                        showError(data.msg, $('#sysErrorRepsonal'), true);
                        $(".regBtn").val('注册').prop('disabled', false);
               	 }
                });
            }
        });
     	/**
     		公用方法
     	*/
     	document.onkeydown = function(e) {
             var ev = document.all ? window.event : e;
             if (ev.keyCode == 13) {
            	 if($("#cellphoneEnt").val().length < 5){
            		 $(".regRepBtn").click();
            	 }else if($("#cellphoneRepsonal").val().length < 5){
            		 $(".regEntBtn").click();
            	 }
             }
         };
          function showError(msg, selector, isFocus) {
              selector.parent().find('.errorTips').find('em').html(msg);
              selector.parent().find('.errorTips').fadeIn();
              $(".regBtn").val('注册').prop('disabled', false);
              if(isFocus){
                  selector.focus();
              }
          }
          // 重置短信发送按钮
          function resetSmsBtn(obj,index) {
              var init = setInterval(function() {
                  index--;
                  if (index <= 0) {
                      clearInterval(init);
                      $(obj).html('获取验证码').prop('disabled', false);
                  } else {
                      $(obj).html('重新发送(' + index + ')').prop('disabled', true);
                  }
              }, 1000);
          }
</script>


</body></html>