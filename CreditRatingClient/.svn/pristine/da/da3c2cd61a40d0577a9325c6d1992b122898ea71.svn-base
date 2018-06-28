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

</head>
<body>
<!-- baidu log stop   -->
    <div class="registerContent">
      <div style="float: left;margin: 20px;"><a href=""><c:if test="${empty Crc_organization}"><img src="<%=path%>/Images/replaceImage/reglogo.png"></c:if></a></div>
        <div class="registerMain">
            <div class="regDiv" style="margin-top:50px">
            <div class="regLogo"><a style="color: #fff;font-size: 25px" ><strong><c:if test="${empty Crc_organization}">企业信用评价平台</c:if><c:if test="${!empty Crc_organization}">${ Crc_organization.name}</c:if></strong></a></div>
                <form action="<%=path%>/customer/editPassword.do" id="resetPwdForm" method="post" class="regForm borderRadius" onsubmit="return false;">
                    <input type="hidden" name="validateCode" value="${validateCode}">
                    <input type="hidden" name="uuid" value="${uuid }">
                    <div class="inputDiv" style="padding-bottom: 5px;">
                        <h4 class="emailTitle">重置密码</h4>
                    </div>
                    <div class="inputDiv">
                        <input type="password" class="regInput" id="password" name="password" placeholder="新密码（不少于6位）" tabindex="1" style="width:480px;">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <input type="password" class="regInput" id="password2" name="password2" placeholder="再次输入密码" tabindex="2" style="width:480px;">
                        <p class="errorTips"><i></i><em></em></p>
                    </div>
                    <div class="inputDiv">
                        <button type="button" class="regBtn">重置密码</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<script src="<%=path%>/Script/login/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Resources/bootstrap/js/bootstrap.min.js"  type="text/javascript" charset="utf-8" ></script>
<script src="<%=path%>/Resources/layer/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Script/login/jquery.form.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/Script/login/regPublic.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
    $(document).ready(function(){
        $('#password').focus(function(){
            $(this).attr('type','password');
        });
        $('#password2').focus(function(){
            $(this).attr('type','password');
        });
        $('.regBtn').click(function(){
            $('.errorTips').css('display','none');
            $(this).html('重置中...').prop('disabled',true);

            var password = $('#password').val().trim();
            var password2 = $('#password2').val().trim();

            if( password.length < 6 ){
                showError( '密码不能少于6位',$('#password') );
            }else if( password != password2 ){
                showError( '两次密码不一样',$('#password2') );
            }else{//基础验证通过
                $("#resetPwdForm").ajaxSubmit(function(data){
                    var code=data.code;
                    var info = data.info;

                    if(code=='-1'){
                        showError( info,$('#password') );
                    }else if(code =='-2'){
                        showError( info,$('#password2') );
                    }else if(code == '1'){
                        layer.msg(info,{icon:1,time:'1500'});
                        setTimeout(function(){window.location.href ='<%=path%>/customer/loginUI.do';},1500);
                    }
                    $(".regBtn").html('重置密码').prop('disabled',false);
                });
            }
            $(this).html('重置密码').prop('disabled',false);
        });
    });

    function showError(msg,selector)
    {
        selector.parent().find('.errorTips').find('em').html(msg);
        selector.parent().find('.errorTips').fadeIn();
        $(".regBtn").html('找回密码').prop('disabled',false);
        selector.focus();
        return false;
    }
</script>


</body></html>