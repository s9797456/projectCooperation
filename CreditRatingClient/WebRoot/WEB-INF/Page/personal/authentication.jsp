	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
    <%@ include file="/WEB-INF/Page/Share/state.jsp"%>
    <%@ include file="/WEB-INF/Page/Share/title.jsp"%>
    <%@ include file="/WEB-INF/Page/Share/meta.jsp" %>
<!DOCTYPE >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
		<%-- bootstrap 框架 --%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/Resources/bootstrap/css/bootstrap.css">
		<%-- left 菜单  插件 的css--%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/Resources/ucenterPublic/Css/reset.css">
		<link rel="stylesheet" type="text/css" href="<%=path %>/Resources/ucenterPublic/Css/ucenter.css">
		<%-- header left 的框架css --%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/Styles/Share/frame.css">
		<style type="text/css">
			img{
				max-width: 680px;
				max-height: 450px;
			}
			.edit_line {
			   display: inline-block;
			   margin:0px auto 15px;
			   width: 70%;
			}
			.hide{
				display: none;
			}
			.bottom{
				margin-bottom: 15px;
			}
			.resume-editLeft {
			    float: left;
			    width: 40%;
			    height: 44px;
			    line-height: 44px;
			    font-size: 14px;
			    color: #666;
			}
			.resume-editRight {
			    float: left;
			    max-width: 60%;
			}
			.forminput {
			    display: inline-block;
			    width:250px;
			    height: 42px;
			    line-height: 42px;
			    border: 1px solid #e3e3e3;
			    padding-left: 12px;
			    border-radius: 5px;
			    font-size: 14px;
			    margin-left: 10px;
			}
		</style>
	<script>
		var _ctxPath="<%=request.getContextPath() %>";
	</script>
	</head>
	<body>
		<!--头-->
		<%--header start--%>
		<%@ include file="/WEB-INF/Page/Share/header.jsp"%>
		<%--header end--%>
		<%--LeftMenu start--%>
		<%@ include file="/WEB-INF/Page/Share/leftMenuPer.jsp"%>
		<%--LeftMenu end--%>
		<div style="padding-left: 200px;">
			<div style="width: 60%;min-width:600px;margin: auto;padding: 20px;box-shadow: 7px 12px 20px #ddd;border-radius: 5px;">
				<h4>个人信息认证</h4>
				<div id = "idcard">
					<form action="" style="margin: auto;width: 70%;">
						<div class='edit_line'>
							<div class='resume-editLeft'>上传身份证正面：</div>
							<div class='resume-editRight'>
								<input name='skillname' placeholder='请上传身份证正面图片' onchange="viewidcard(true,this)" class='form-control forminput' type='file'>
							</div>
						</div>
						<div id = "idcardPositive" class='hide bottom' >
							<img src="">
						</div>
						<div class='edit_line'>
							<div class='resume-editLeft'>上传身份证背面：</div>
							<div class='resume-editRight'>
								<input name='skillname' placeholder='请上传身份证背面图片' onchange="viewidcard(false,this)" class='form-control forminput' type='file'>
							</div>
						</div>
						<div id = "idcardBack" class='hide bottom' >
							<img src="">
						</div>
						<div class='edit_line' >
							<div class='resume-editLeft'>姓名：</div>
							<div class='resume-editRight' style="margin-top: 5px;">
				                <input name="name" type="text" required="required" placeholder="请输入真实姓名" class="form-control forminput">
							</div>
						</div>
						<div class='edit_line' >
							<div class='resume-editLeft'>身份证号：</div>
							<div class='resume-editRight' style="margin-top: 5px;">
				                <input name = "idcard" required="required" type="text" placeholder="请输入第二代身份证号" class="forminput form-control">
							</div>
						</div>
						<div class='edit_line' >
							<div style="text-align: center;"><button class = "btn btn-defule">提交</button></div>
						</div>
					</form>
				</div>
			</div>
	 	</div>
	</body>
	<script src="<%=path %>/Resources/jquery/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/jquery/jquery.form.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/ucenterPublic/Js/ucenterPublic.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/base.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/personalBase.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" charset="utf-8">
	function viewidcard(num,obj){
		//建立一個可存取到該file的url
		 function getObjectURL(file) {
			 var url = null ;
			 if (window.createObjectURL!=undefined) { // basic
			 	url = window.createObjectURL(file) ;
			 } else if (window.URL!=undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file) ;
			 } else if (window.webkitURL!=undefined) { // webkit or chrome
			 	url = window.webkitURL.createObjectURL(file) ;
			 }
			 return url ;
		 }
		
		var objUrl = getObjectURL(obj.files[0]) ;
		 if (objUrl) {
			 if(num){
			 	$("#idcardPositive").removeClass("hide").children("img").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
			 }else{
			 	$("#idcardBack").removeClass("hide").children("img").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
			 }
		 }
	 }
	 function uploadPhoto() {  
		    var imagePath = $("#file").val();  
		    var flag = true;
		    if (imagePath == "") {  
		        showError("请选择文件再进行上传",$("#file"),true);
		        flag = false;  
		    }else{
		    	var strExtension = imagePath.substr(imagePath.lastIndexOf('.') + 1);  
			    if (strExtension != 'jpg' && strExtension != 'jpeg'  
			            && strExtension != 'png' && strExtension != 'bmp') {  
			        showError("请选择图片类型上传，限：jpg、jpeg、bmp、png",$("#file"),true);
			        flag = false;  
			    }
		    }
		      
		    if(flag){
		    	$("#file").siblings('.errorTips').find('em').html('');
		    	$("#file").siblings('.errorTips').hide();
		    	 var options = {  
		                 // 规定把请求发送到那个URL  
		                 url: _ctxPath+"/organization/upload.do",  
		                 // 请求方式  
		                 type: "post",  
		                 // 服务器响应的数据类型  
		                 dataType: "json",  
		                 // 请求成功时执行的回调函数  
		                 success: function(data) {  
		                     alert("上传成功");
		                     $("#file").val("");
		                     $("#imgSrc").attr("src", _ctxPath+"/Images/default.jpg") ; //将图片路径存入src中，显示出图片
		                 }  
		         };  
		         $("#upload").ajaxSubmit(options);  
		    }
		  
		}
	</script>
</html>