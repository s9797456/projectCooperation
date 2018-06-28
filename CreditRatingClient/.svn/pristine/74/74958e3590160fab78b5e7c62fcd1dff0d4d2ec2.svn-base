<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
<%@ include file="/WEB-INF/Page/Share/state.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">

<link rel="stylesheet" href="<%=path%>/Styles/login/regstyle.css">
<link rel="stylesheet" href="<%=path%>/Styles/login/reset.css">
<link rel="stylesheet" href="<%=path%>/Resources/layer/skin/layer.css" id="layui_layer_skinlayercss">
<script>
		var _ctxPath="<%=request.getContextPath() %>";
</script>
</head>
<body>
<!-- baidu log stop   -->
    <div class="registerContent">
    <div style="float: left;margin: 20px;"><a href=""><c:if test="${empty Crc_organization}"><img src="<%=path%>/Images/replaceImage/reglogo.png"></c:if></a></div>
        <div class="registerMain">
                <form action="<%=path%>/customer/findPWDByCellPhone.do" id="sendsms" method="post" class="regForm borderRadius" onsubmit="return false;">
                    <div class="title">
                        <h3>重置密码</h3>
                    </div>
                    <div class="inputDiv">
                        <input type="tel" class="regInput" name="cellphone" id="cellphone" placeholder="手机号" tabindex="1" style="width:480px;">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv getCode">
                        <input type="text" class="regInput" name="codeNum" id="codeNum" placeholder="验证码" tabindex="2" style="width:372px;">
                        <button class="getCodea smsbtn" type="button">获取验证码</button>
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <p class="errorTips" id="sysError"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <button type="button" class="regBtn">找回密码</button>
                    	<p class="have"><a style="color: #02c5ff;" href="<%=path%>/customer/logonUI.do">返回登录<i></i></a></p>
                    </div>
                </form>
        </div>
    </div>

<script src="<%=path%>/Script/login/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Resources/bootstrap/js/bootstrap.min.js"  type="text/javascript" charset="utf-8" ></script>
<script src="<%=path%>/Resources/layer/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Script/login/jquery.form.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Script/login/regPublic.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        $(function(){
            //bind keyCode=13 Event
            document.onkeydown = function(e){
                var ev = document.all ? window.event : e;
                if(ev.keyCode==13) {

                    $(".regBtn").click();

                }
            };
            $('.regBtn').click(function(){

                var cellphone = $("#cellphone").val();
                cellphone = $.trim(cellphone);

                $('.errorTips').find('em').html('');
                $('.errorTips').hide();

                if( !isPhone(cellphone) ){
                    showError( '手机号格式不正确',$('#cellphone') );
                }else{//基础验证通过
                    $(".regBtn").html('找回中...').prop('disabled',true);
                    $("#sendsms").ajaxSubmit(function(data){
                        var code=data.code;
                        var info = data.info;
                        if(code == '1'){
                            window.location.href =_ctxPath+data.acturl;
                        }else{
                            if(code=='-1'){
                                showError( info,$('#cellphone') );
                            }else if(code =='-2' || code=='-3'){
                                showError( info,$('#codeNum') );
                            }else if(code=='-100')//手机号错误或被用过
                            {
                                showError(info,$('#sysError'));
                            }
                            $(".regBtn").html('找回密码').prop('disabled',false);
                        }

                    });
                }
            });

            // 点击发送短信按钮时
            $('.smsbtn').click(function() {
                $('.errorTips').find('em').html('');
                $('.errorTips').hide();
                $('.smsbtn').prop('disabled', true);
                var cellphone = $('#cellphone').val();
                if (!isPhone(cellphone)) {
                    showError('请输入正确的手机号', $('#cellphone'), true);
                    $('.smsbtn').prop('disabled', false);
                } else {
                    $('.smsbtn').html('验证码发送...').prop('disabled', true);
                    $.post('<%=path%>/customer/sendSMS.do', {
                        'cellphone': cellphone,
                        'findpwd':true,
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
        });
        // 重置短信发送按钮
        function resetSmsBtn(index){
            var init = setInterval(function(){
                index--;
                if(index<=0){
                    clearInterval(init);
                    $(".smsbtn").html("获取验证码").css({"background":"#2AABE4"}).prop("disabled",false);
                }else{
                    $(".smsbtn").html("重新发送("+index+")").prop("disabled",true);
                }
            },1000);
        }
        function showError(msg, selector, isFocus) {
            selector.parent().find('.errorTips').find('em').html(msg);
            selector.parent().find('.errorTips').fadeIn();
            $(".regBtn").val('找回密码').prop('disabled', false);

            if(isFocus){
                selector.focus();
            }
        }

    </script>


</body></html>