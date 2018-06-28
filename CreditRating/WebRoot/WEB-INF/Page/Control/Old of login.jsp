<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
<%@ include file="/WEB-INF/Page/Share/state.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ include file="/WEB-INF/Page/Share/title.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=path%>/Resources/bootstrap/css/bootstrap.css"rel="stylesheet" type="text/css">

<style type="text/css">
#a1:hover,#a2:hover,#a3:hover,#a4:hover,#a5:hover,#a6:hover {
	background-color: orange;
}
li{
	line-height:25px ;
}
.state1{
    color:#aaa;
}
.state2{
    color:red;
}
.state3{
    color:orange;
}
#d1{
background-repeat: no-repeat;
background-position: center;
background-size:100% 100%;
}
</style>
</head>
<body>
	<div id='example' class='modal hide fade in' style='display:none;'>
			<div class='modal-header'>
				<a class='close' data-dismiss='modal'>X</a>
				<h3>帮助</h3>
			</div>
			<div class='modal-body'>
				<p style="text-indent:2em;">企业信用评价包括工业、商业、外贸、交通、建筑、房地产、旅游等公司企业和企业集团的信用评价以及商业银行、保险公司、信托投资公司、 证券公司等各类金融组织的信用评价。
				金融组织与公司企业的信用评价要求不同，一般公司企业生产经营比较正常，
				虽有风险，容易识别， 企业的偿债能力和盈利能力也易测算；而金融组织就不一样，
				容易受经营环境影响，是经营货币借贷和证券买卖的企业，涉及面广，风险大，在资金运用上要求盈利性、 流动性和安全性的协调统一，
				要实行资产负债比例管理，要受政府有关部门监管，特别是保险公司是经营风险业务的单位，风险更大。 
				因此，金融组织信用评价的风险性要比一般公司企业来得大，评估工作也更复杂。</p>
				<p style="text-indent:2em;">信用评价是以一套相关指标体系为考量基础，标示出个人或企业偿付其债务能力和意愿的过程。 
				信用评价即由专业的机构或部门，根据“公正、客观、科学”原则，按照一定的方法和程序，在对企业进行全面了解、考察调研和分析的基础上，
				 作出有关其信用行为的可靠性、安全性程度的估量，并以专用符号或简单的文字形式来表达的一种管理活动。
				 信用评价是专业的机构采用公正、科学、权威的资信考核标准， 对企业及金融机构的基本素质、经营水平、财务状况、盈利能力、管理水平和发展前景等方面进行综合分析和评价，
				 测定企业及金融机构履行各种经济契约的能力和可信任程度， 并以国际通用符号标明信用等级，向社会公告。
				 信用评价是由信用服务机构根据规范的指标体系和科学的评估方法，以客观公正的立场， 
				 对被评估对象履行经济责任所承担的能力及其可信程度进行评价，并以一定的符号表示其信用等级的一种有组织的活动。</p>
			</div>
	</div>
	<%--页面顶部--%>
			<div class="navbar navbar-fixed-top">
				<c:if test="${empty organization}"><%--如果传过来的对象为空，那么为默认的，否则的话为某个机构--%>
					<div class="navbar-inner">
						<ul class="nav" style="margin-left: 30px;">
							<li><a class="brand" href="${sessionScope.corp_url}" target="_blank">${sessionScope.corp_name2}</a></li>
						</ul>
						<div class="container" style="margin-right: 30px;">
							<ul class="nav pull-right">
								<li><a href='#example' data-toggle='modal' style="color: white;">帮助</a></li>
								<li><a href="<%=path%>/user/logon_jumpToFindpwd.do" style="color: white;">找回密码</a></li>
								<li><a href="#" style="color: white;">版本号:${sessionScope.obj_version}</a></li>
								<li><a href="<%=path%>/initSql.jsp" style="color: white;">更新日期: ${sessionScope.obj_updatetime}</a></li>
								<li><a href="${sessionScope.corp_weibo}" target="_blank" style="color: white;"><i class="icon-comment" ></i> 官方微博</a></li>
							</ul>
						</div>
					</div>
				</c:if>
			</div>
			<%--中间登录页面--%>
			<div style="height: 400px;">
				<div id="d1" style="background-image :url('<c:if test="${empty organization}"><%=path%>/Images/replace/login_mainbg.jpg</c:if>')">
					<div class="row container" style="height: 350px;margin-top:30px;" >
						<div class=" span4 offset8" >
							<div class="clearfix">
								<form  id='myForm' class='required-validate' style="margin:25px auto;" />
								<h3>Login</h3>
								<fieldset>
									<div class="control-group">
										<label class="control-label" for="userName">用户名</label>
										<div class="controls">
											<input type="text" id="userName" name="userName"  placeholder="请输入用户名" style="width:300px" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="password">密码</label>
										<div class="controls">
											<input type="password" id="password" name="password" placeholder="请输入密码" style="width:300px"/>
										</div>
									</div>
									<div class="control-group">
										 <div id="captcha">
            								<p id="wait" class="show">正在加载验证码......</p>
            								<p id="notice" class="hide">请先完成验证</p>
        								</div>
									</div>
								</fieldset>
								<div>
									<button type="button" class="btn btn-success btn-large" id="submit" onclick="sub();">登录</button>
									<button type="button" class="btn btn-success btn-large" id="reg" style="margin:0 10px;">注册</button>
								</div>
								<div style="margin-top:10px;"><h4><span name="msg" class='state1' ></span></h4></div>
								</form>
							</div>				
						</div>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="row">
				<center><img src="<%=path%>/Images/replace/nav.png"   style="position: relative;top:-20px;"></center>
				</div>
			</div>
			<%--底部信息--%>
			<hr>
				<c:if test="${empty organization}">
					<div class="container">
						<div class="row">
								<div  class=" span2" >
										<div class="navbar">
										<h4 style="font-weight:normal"><center><ul style="list-style-type: none">
									         <li><img src="<%=path%>/Images/replace/hyt-icon.png" ></li>
									         <li>关注企业信用建设</li>
									         <li><strong>${sessionScope.corp_name2}</strong></li>
									         <li>信用就是财富</li></center>
									    </ul></h4>
									    </div>
								</div>
								<div  class=" span2">
										<div class="navbar">
										<h4 style="font-weight:normal;"><ul style="list-style-type: none;">
											  <li><strong>关于汇誉通</strong></li>
									          <li><a href="http://www.hytcredit.com/misc.php?mod=faq&amp;action=faq&amp;id=1&amp;messageid=3" target="_blank" style="color:#999999;">关于我们</a></li>
									          <li><a href="http://www.hytcredit.com/misc.php?mod=faq&amp;action=faq&amp;id=1&amp;messageid=3" target="_blank" style="color:#999999;">加入我们</a></li>
									          <li><a href="http://www.hytcredit.com/misc.php?mod=faq&amp;action=faq&amp;id=1&amp;messageid=3" target="_blank" style="color:#999999;">广告合作</a></li>
									          <li><a href="http://www.hytcredit.com/misc.php?mod=faq&amp;action=faq&amp;id=1&amp;messageid=3" target="_blank" style="color:#999999;">投稿通道</a></li>
								        </ul></h4>
								        </div>
								</div>
								<div  class=" span2" >
										<div class="navbar">
										<h4 style="font-weight:normal"><ul style="list-style-type: none">
								          	<li><strong>社会化媒体</strong></li>
								          	<li><a target="_blank" href="http://weibo.com/u/5514855333" style="color:#999999;">新浪微博@汇誉通</a></li>
								          	<li><a target="_blank" href="http://t.qq.com/hytdata" style="color:#999999;">腾讯微博@汇誉通</a></li>
								          	<li style="color: #999999;" >微信订阅号hytdata</a></li>
								        </ul></h4>
								        </div>
								</div>
								<div  class=" span2" >
										<div class="navbar">
										<h4 style="font-weight:normal"><ul style="list-style-type: none">
									         <li><strong>汇誉通企业网站</strong></li>
									         <li><a href="http://www.hytcredit.cn" target="_blank" style="color:#999999;">汇誉通企业信用评价平台</a></li>
									         <li><a href="#" style="color:#999999;">汇誉通大数据平台</a></li>
									    </ul></h4>
									    </div>
								</div>
								<div  class=" span2" >
										<div class="navbar">
										<h4 style="font-weight:normal"><ul style="list-style-type: none">
									         <li><strong>友情链接</strong></li>
									         <li><a href="#" style="color:#999999;">待定</a></li>
									    </ul></h4>
									    </div>
								</div>
								<div  class=" span2" >
										<div class="navbar">
										<h4 style="font-weight:normal"><a href="http://www.hytcredit.com/" target="_blank"><img id="sina" src="<%=path%>/Images/replace/sina.png"></a>
										<p>HYT官方微博</p>
										<a href="http://www.hytcredit.com/" target="_blank"><img src="<%=path%>/Images/replace/sina-icon.png"></a>
										</h4></div>								
								</div>
						</div>
					</div>
				</c:if>
			<hr><div class="container">
				<div class="row">
						<center><p style="color:#999999 ">${sessionScope.obj_name}
					    <span>|</span><a href=<c:if test='${empty organization}'>"http://www.hytcredit.com/"</c:if><c:if test='${!empty organization}'>http://${organization.orgUrl }</c:if>target="_blank" style="color: #999999;">About us </a>
					    <span>|</span><c:if test="${empty organization}"><a href="http://www.hytcredit.com/" target="_blank" style="color: #999999;">汇誉通</a></c:if><c:if test='${!empty organization}'><a href="http://${organization.orgUrl }" target="_blank" style="color: #999999;">${organization.name }</a></c:if>
					
				</div>
			</div>
			
<script src="<%=path%>/Resources/bootstrap/js/jquery-1.7.2.min.js" ></script>
<script src="<%=path%>/Resources/bootstrap/js/bootstrap.js" ></script>
<script src="<%=path%>/Resources/geetest/gt.js"></script>
<script type="text/javascript">
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
</script>
<script>
	document.oncontextmenu=rightMouse;
	function rightMouse() {
		return false;
	}
   $('input[name="userName"]').focus(function(){$('span[name="msg"]').text("");});
   $('input[name="password"]').focus(function(){$('span[name="msg"]').text("");});
	function sub(captchaObj){
		String.prototype.trim= function () {
			return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
		};
		if($('#userName').val()==null || $('#userName').val().trim() == ""){
			alert("请输入用户名");
			return false;
		}else if($('#password').val()==null || $('#password').val().trim() == ""){
			alert("请输入密码");
			return false;
		}
		$('#submit').attr('disabled', 'disabled');
		$.ajax({
					url:"<%=path%>/user/logon.do",
					type:"post",
					dataType:"json",
					traditional:true,
					data:$('#myForm').serialize(),
					success:function(data){
							if(data.status==false){
								$('#submit').attr('disabled', false);
								captchaObj.reset();
								$('span[name="msg"]').text(data.msg).removeClass('state1').addClass('state2');
							}else{
								$('span[name="msg"]').text("正在加载，请耐心等待！").removeClass('state1').removeClass('state2').addClass('state3');
								location.href='<%=path%>/control/navigation/main.do';
							}
					},
					error:function(xhr,status,error){
						$('#submit').attr('disabled', false);
						alert("服务暂停，请联系管理员！");
					}
	    	});
	}
	$("#reg").click(function(){
		location.href='<%=path%>/user/registUI.do';
	});
</script>

</body>
</html>
