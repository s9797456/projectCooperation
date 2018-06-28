function contactInfo(name,phone,email,address){
	$("body").append("<div class='modal fade' id='contactinfo' style='top: 50px'>"
		    +"<div class='modal-dialog modal-sm' style='width:50%'>"
		        +"<div class='modal-content'>"
		            +"<div class='modal-header'>"
		                +"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>"
		                +"<span aria-hidden='true'>&times;</span></button>"
		                +"<h4 class='modal-title'>联系我们</h4>"
		            +"</div>"
		            +"<div class='modal-body' id='contactinfoModalBody' >"
					+"<div align='center'>"
						+"<b>"+name+"</b>"
					+"</div>"
					+"<br>"
					+"<div style='text-align:left;line-height:40px;'>"
						+"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系电话："+phone+"</b>"
						+"<br>"
						+"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商务合作："+email+"</b>"
						+"<br>"
						+"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工作地址："+address+"</b>"
						+"<br>"
						+"<br>"
					+"</div>"
				 +"<div class='modal-footer'>"
				    +"<button type='button' class='btn btn-primary' data-dismiss='modal'>关闭</button>"
				 +"</div>"
		        +"</div>"
		    +"</div>"
		+"</div>");
	$("#contactinfo").modal("show");
}
//退出登录 
function logout(){
	$("body").append("<div class='modal fade' id='logoutModal' style='top: 300px'><div class='modal-dialog modal-sm'>"
	+"<div class='modal-content'><div class='modal-header'>"
	   +"<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
	   +"<h4 class='modal-title'>提示</h4></div>"
	   +"<div class='modal-body'><p>确认退出吗</p> </div>"
	   +"<div class='modal-footer'> <button type='button' class='btn btn-default'  data-dismiss='modal'>否</button><button type='button' class='btn btn-primary' id='logout'>是</button>"
	   +"</div></div></div></div>");
	$("#logoutModal").modal("show");
	$("body").on("click","#logout",function(){
	    window.location.href= _ctxPath+'/customer/quit.do';
	    //window.location.href="http://www.server.com/logout";
	});
}

/*修改密码*/
function updatePassword(realname,cellphone){
	$("body").append("<div class='modal fade' id='Password' style='top: 100px'><div class='modal-dialog'>"
			+"<div class='modal-content'><div class='modal-header'>"
			   +"<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"
			   +"<h4 class='modal-title'>修改密码</h4></div>"
			   +"<div class='modal-body'><form  method='post' >"
			   +"<div><div class='password'>用户帐号 </div><div class='updatePassword' ><input type='text' name = 'realname' readonly='readonly' value='"+realname +"'></div></div>"
			   +"<div><div class='password'>联系电话 </div><div class='updatePassword' ><input type='text' name = 'cellphone' readonly='readonly' value='"+cellphone +"'></div></div>"
			   +"<div><div class='password'><span style='color: red;'>*</span>原密码 </div><div class='updatePassword'><input required name = 'oldPassword'  type='password' placeholder='请填写原密码' data-vali = 'false' onblur='isPassword(this)'><span></span></div></div>"
			   +"<div><div class='password'><span style='color: red;'>*</span>设置密码 </div><div class='updatePassword'><input required name = 'newPassword' type='password' placeholder='请填写新密码,需包含字母、数字' data-vali = 'false' onblur='isPassword(this)'><span></span></div></div>"
			   +"<div><div class='password'><span style='color: red;'>*</span>确认密码</div><div class='updatePassword'><input required name = 'newsPassword' type='password' placeholder='请再次输入新密码' data-vali = 'false' onblur='isPassword1(this)'><span></span></div></div></form></div>"
			   +"<div class='modal-footer' style = 'border:0px;'> <button type='button' class='btn btn-default'  data-dismiss='modal'>取消</button><button type='button' class='btn btn-primary' id='save'>修改</button>"
			   +"</div></div></div>");
	$("#Password").modal("show");
	$("body").on("click","#save",function(){
		$("#Password").modal('hide');
		var len = $("[data-vali = 'false']");
		if(len.length > 0){
			popup("带*信息填写不完整，无法进行修改");
			return;
		}
		$.ajax({
            cache: true,
            type: "POST",
            url:_ctxPath+'/main/updatePassword.do',
            data:$('#Password').find("form").serialize(),
            async: false,
            error: function(request) {
                popup("网络错误，请重新登录    错误代码：400");
            },
            success: function(data) {
            	$("#Password").remove();
            	if(data.status == 0){
            		popup(data.msg);
            	}else if(data.status == 1){
            		alert("密码已修改，请重新登录");
            		window.location.href= _ctxPath+'/customer/quit.do';
            	}else{
            		popup(data.msg);
            	}
            }
        });
	});
}
/* 验证 */
function isPassword(obj){
	obj = $(obj);
	var bool = validate_required(obj.val());
	if(bool){
		obj.attr("data-vali","true");
		obj.next().css("color","green").text("✔");
	}else{
		obj.next().css("color","red").html("<p>必须以字母开头，且长度在6~20位之间</p>");
	}
}
function isPassword1(obj){
	obj = $(obj);
	var bool = validate_required(obj.val());
	if(bool){
		if(obj.val() != $("[name = 'newPassword']").val()){
			obj.next().css("color","red").html("<p>密码不一致，请重新输入</p>");
			obj.val("");
		}else{
			obj.attr("data-vali","true");
			obj.next().css("color","green").text("✔");
		}
	}else{
		obj.next().css("color","red").html("<p>必须以字母开头，且长度在6~20位之间</p>");
	}
}
function validate_required(value){
	if (value==null||value==""){
		return false;
	}else {
		//var b = /^[a-zA-Z]{1}[\w]{5,19}$/; 
		var b = /^[\w]{6,20}$/; 
		if(b.test(value) ){
			return true;
		}else{
			return false;
		}
	}
	
}
/* 验证   end */
function globalSearch(){
	$("#right").html("<iframe id = 'iframe' style='margin-top: -80px;' src='http://www.hytdata.cn/ent/listUI.do' width='100%' height='100%' frameborder='0'>您的浏览器不支持iframe，请升级</iframe>");
	$("#fp-nav").remove();
}

function isNull(param){
	if(param == ""|| param == null ){
		param = " - ";
	}
	return param;
}
/*消息提示框*/
function popup(str) {
	$("#popup").remove();
	$("body").append("<div id = 'popup' style='width:auto;padding: 3%;height: 100px;line-height:80%;border: 2px #00a0e9 solid;margin: auto;position:fixed;z-index:9999;left: 40%;top: 30%;background:#f9f9f9;border-radius: 15px;box-shadow: 10px 7px 15px #888;text-align: center;font-family: '微软雅黑';font-size: 15px;'><label>" +str + "</label></div>");
	window.setTimeout(function() {
		$("#popup").remove();
	}, 2000);//设置显示时间
}
function popupUrl(str,url) {
	$("#popup").remove();
	$("body").append("<div id = 'popup' style='width:auto;padding: 3%;height: 100px;line-height:80%;border: 2px #00a0e9 solid;margin: auto;position:fixed;z-index:9999;left: 40%;top: 30%;background:#f9f9f9;border-radius: 15px;box-shadow: 10px 7px 15px #888;text-align: center;font-family: '微软雅黑';font-size: 15px;'><label>" +str + "</label></div>");
	window.setTimeout(function() {
		$("#popup").remove();
		window.location.href = url;
	},4000);//设置显示时间
}
function popupReload(str) {
	$("#popup").remove();
	$("body").append("<div id = 'popup' style='width:auto;padding: 3%;height: 100px;line-height:80%;border: 2px #00a0e9 solid;margin: auto;position:fixed;z-index:9999;left: 40%;top: 30%;background:#f9f9f9;border-radius: 15px;box-shadow: 10px 7px 15px #888;text-align: center;font-family: '微软雅黑';font-size: 15px;'><label>" +str + "</label></div>");
	window.setTimeout(function() {
		$("#popup").remove();
		window.location.reload();
	},3000);//设置显示时间
}
