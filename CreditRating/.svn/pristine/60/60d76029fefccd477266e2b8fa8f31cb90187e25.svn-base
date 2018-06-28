<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	<%@ include file="/WEB-INF/Page/Share/taglibs.jsp"%>
	<%@ include file="/WEB-INF/Page/Share/state.jsp"%>
	<%@ include file="/WEB-INF/Page/Share/title.jsp"%>
	<%@ include file="/WEB-INF/Page/Share/meta.jsp" %>
    <title></title>
	<link rel="stylesheet" href="<%=path%>/Resources/Control/Styles/404.css">
	<script>
		var _ctxPath="<%=request.getContextPath() %>";
	</script>
	<script src="<%=path%>/Resources/Plug/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript">
	$(function() {
		var h = $(window).height();
		$('body').height(h);
		$('.mianBox').height(h);
		centerWindow(".tipInfo");
	});

	//2.将盒子方法放入这个方，方便法统一调用
	function centerWindow(a) {
		center(a);
		//自适应窗口
		$(window).bind('scroll resize',
				function() {
					center(a);
				});
	}

	//1.居中方法，传入需要剧中的标签
	function center(a) {
		var wWidth = $(window).width();
		var wHeight = $(window).height();
		var boxWidth = $(a).width();
		var boxHeight = $(a).height();
		var scrollTop = $(window).scrollTop();
		var scrollLeft = $(window).scrollLeft();
		var top = scrollTop + (wHeight - boxHeight) / 2;
		var left = scrollLeft + (wWidth - boxWidth) / 2;
		$(a).css({
			"top": top,
			"left": left
		});
	}
</script>
</head>
<body>
<div class="mianBox">
	<img src="<%=path%>/Resources/Control/Img/yun0.png" alt="" class="yun yun0" />
	<img src="<%=path%>/Resources/Control/Img/yun1.png" alt="" class="yun yun1" />
	<img src="<%=path%>/Resources/Control/Img/yun2.png" alt="" class="yun yun2" />
	<img src="<%=path%>/Resources/Control/Img/bird.png" alt="" class="bird" />
	<img src="<%=path%>/Resources/Control/Img/san.png" alt="" class="san" />
	<div class="tipInfo">
		<div class="in">
			<div class="textThis">
				<h2>
				<c:if test="${not empty code }">
				<strong style="color:red">(${code }：${msg })</strong>
				</c:if>
				系统出现错误，重新登录进操作如无解决请联系管理员(电话17625378024)</h2>
				<p><span>页面自动<a id="href" href="<%=path%>/user/logonUI.do">跳转</a></span><span>等待<b id="wait">10</b>秒</span></p>
				<script type="text/javascript">                            
				(function() {
						var wait = document.getElementById('wait'), href = document.getElementById('href').href;
						var interval = setInterval(function() {
							var time = --wait.innerHTML;
							if (time <= 0) {
								location.href = href;
								clearInterval(interval);
							};
						}, 1000);
					})();
				</script>
			</div>
		</div>
	</div>
</div>

<div style="text-align:center;margin:-150px 0; font:normal 14px/24px 'MicroSoft YaHei';">
<p></p>
</div>
</body>
</html>
