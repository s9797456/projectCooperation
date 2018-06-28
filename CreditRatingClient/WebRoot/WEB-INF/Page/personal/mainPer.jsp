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
		<%-- 本页面的css --%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/Styles/enterprise/main.css">
		<script type="text/javascript">
		var _ctxPath = "<%=request.getContextPath() %>";
		</script>
		
	</head>
	
	<body >
		<%--header start--%>
		<%@ include file="/WEB-INF/Page/Share/header.jsp"%>
		<%--header end--%>
		<div id="zhong"  >
			<%--LeftMenu start--%>
			<%@ include file="/WEB-INF/Page/Share/leftMenuPer.jsp"%>
			<%--LeftMenu end--%>
			<div id="right" >
				<!--右侧 内容部分-->
				<div id = "rightUp" style="margin-top: 30px;">
						<!--右侧 内容 上面基础信息部分-->
					<div >
						<h5 >
						<c:if test="${! empty Crc_customer.username}">${Crc_customer.username}</c:if>
						<c:if test="${empty Crc_customer.username}">尊敬的</c:if>
						 &nbsp;用户 , 您好</h4>
						<div id ="userInfo" >
							<!--基础信息 部分-->
								<div id = "portrait" >
									<!--男性 头像-->
									<img  src="<%=path %>/Images/default_handsome.jpg"/>
									<!--女性 头像-->
									<img style="display: none;" src="<%=path %>/Images/default_friend.jpg"/>
								</div>
								<div id = "baseInfo" >
									<h3 class = "text-overflow" style="min-width: 190px;width: 23vw;" >
											<c:if test="${! empty Crc_entName}">${Crc_entName}</c:if>
											<c:if test="${ empty Crc_entName}">尊敬的用户</c:if>
									</h3>
									<div>
										<div class = "text-overflow" ><span >用户名称 ：</span> ${Crc_customer.username}</div>
										<div class = "text-overflow" ><span >&nbsp;&nbsp;联系人 ：</span><c:if test="${! empty Crc_customer.realname}">${Crc_customer.realname}</c:if><c:if test="${empty Crc_customer.realname}"> - </c:if></div>
									</div>
									<div>
										<div class = "text-overflow" ><span >联系方式 ： </span>${Crc_customer.cellphone}</div>
										<div class = "text-overflow"><span >电子邮箱： </span><c:if test="${! empty Crc_customer.email}">${Crc_customer.email}</c:if><c:if test="${empty Crc_customer.email}"> - </c:if></div>
									</div>
								</div>
							<div id = 'scoreInfo' >
							<div class = "line" ><img/></div>
							<div class="block1" style="width: 23%; min-width: 140px;">
									<div class = "la1" style="margin-top: 18%;" >
										<c:if test="${! empty processState }">
											<c:choose>
												<c:when test="${processState.applyreportstate == 1 &&  processState.scorestate == 0}">
													已申请
												</c:when>
										 		<c:when test="${processState.applyreportstate == 1 &&( processState.scorestate == 1 || processState.scorestate == 2)}">
													正在调查
												</c:when>
												<c:when test="${processState.applyreportstate == 1 && processState.scorestate == 3}">
													<a style="color:#00C1E1" href="<%=path%>/initPersonal/viewPreliminary.do">请查看初评</a>
												</c:when>
												<c:when test="${processState.applyreportstate == 1 && processState.scorestate == 4}">
													正在评分中
												</c:when>
												<c:when test="${processState.applyreportstate == 1 && processState.scorestate == 5}">
													<a style="color:#00C1E1" href="javascript:downloadReport()">查看评分报告</a>
												</c:when>
												 <c:otherwise>
												 	未申请
												 </c:otherwise> 
											</c:choose>
										</c:if>
										<c:if test="${empty processState }">
											未申请
										</c:if>
									</div>
									<br />
									<div>当&nbsp;前&nbsp;状&nbsp;态</div>
							</div>
							<div  class = "line"><img/></div>
							<div class="block1" >
									<div class = "la1" >
										<c:if test="${! empty processState }">
											<c:choose>
												<c:when test="${reject == 0 && processState.applyreportstate == 0}">
															<a style="color:#00C1E1" onclick="rejectedItem('${reject}')" >申请被驳回</a>
												</c:when>
												 <c:otherwise>
												 	无驳回内容
												 </c:otherwise> 
											</c:choose>
										</c:if>
										<c:if test="${empty processState }">
											无驳回内容
										</c:if>
									</div>
									<br />
									<div>驳&nbsp;回&nbsp;项</div>
							</div>
							<div  class = "line"><img/></div>
							<div class = "block1" >
									<c:if test="${! empty processState }">
											<c:choose>
												<c:when test="${processState.applyreportstate == 1 && processState.scorestate >= 2}">
													<a style="color:#00C1E1" href="<%=path%>/initPersonal/viewPreliminary.do"><div style="margin-bottom: 0px;" class="la1">等级 ：${entResult.preliminarylevel } 级</div>
													<div style="margin-bottom: 20px; color: rgb(6, 139, 159); font-size: 90%;">${entResult.preliminaryscore } 分</div></a>
												</c:when>
												<c:otherwise>
												<div class = "la1" >暂无</div>
												<br />
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${empty processState }">
											<div class = "la1" >未申请</div>
											<br />
										</c:if>
									
									<div>初&nbsp;&nbsp;&nbsp;评</div>
									
							</div>
							<div  class = "line"><img/></div>
							<div class = "block1" >
									<c:if test="${! empty processState }">
											<c:choose>
												<c:when test="${processState.applyreportstate == 1 && processState.scorestate == 5}">
													<div style="margin-bottom: 0px;" class="la1">等级 ：${entResult.finallevel } 级</div>
													<div style="margin-bottom: 20px; color: rgb(6, 139, 159); font-size: 90%;">${entResult.finalscore } 分</div>
												</c:when>
												<c:otherwise>
												<div class = "la1" >暂无</div>
												<br />
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${empty processState }">
											<div class = "la1" >未申请</div>
											<br />
										</c:if>
									<div>终&nbsp;&nbsp;&nbsp;评</div>
							</div>
							</div>
							<div id = 'scoreInfo1' style="float: right;width: 35vw;min-width:300px;height: 100%;" >
								<div style="width: 50%;height: 49%;float: left;">
									<div class = "la1" style="margin:0px;margin:auto;line-height: 400%;text-align: center;" >
										<c:if test="${! empty processState }">
											<c:choose>
												<c:when test="${processState.applyreportstate == 1 &&  processState.scorestate == 0}">
													已申请
												</c:when>
										 		<c:when test="${processState.applyreportstate == 1 &&( processState.scorestate == 1 || processState.scorestate == 2)}">
													正在调查
												</c:when>
												<c:when test="${processState.applyreportstate == 1 && processState.scorestate == 3}">
													<a style="color:#00C1E1" href="<%=path%>/initPersonal/viewPreliminary.do">请查看初评</a>
												</c:when>
												<c:when test="${processState.applyreportstate == 1 && processState.scorestate == 4}">
													正在评分中
												</c:when>
												<c:when test="${processState.applyreportstate == 1 && processState.scorestate == 5}">
													<a style="color:#00C1E1" href="javascript:downloadReport()">查看评分报告</a>
												</c:when>
												 <c:otherwise>
												 	未申请
												 </c:otherwise> 
											</c:choose>
										</c:if>
										<c:if test="${empty processState }">
											未申请
										</c:if>
									</div>
								</div>
								<div style="width: 50%;height: 49%;float: right;">
										<div class = "la1" style="margin:0px;margin:auto;line-height: 400%;text-align: center;" >
											<c:if test="${! empty processState }">
											<c:choose>
												<c:when test="${reject == 0 && ! empty opinion && processState.applyreportstate == 0}">
															<a style="color:#00C1E1" onclick="rejectedItem('${reject}')" >申请被驳回</a>
												</c:when>
												 <c:otherwise>
												 	无驳回项
												 </c:otherwise> 
											</c:choose>
										</c:if>
										<c:if test="${empty processState }">
											无驳回项
										</c:if>
									</div>
								</div>
								<div style="width: 50%;height: 49%;float: left;">
									<c:if test="${! empty processState }">
											<c:choose>
												<c:when test="${processState.applyreportstate == 1 && processState.scorestate >= 2}">
													<a style="color:#00C1E1" href="<%=path%>/initPersonal/viewPreliminary.do"><div class = "la1" style="margin:0px;margin:auto;line-height: 350%;text-align: center;" ><span>初评等级：${entResult.preliminarylevel }</span><p style="color: rgb(6, 139, 159); font-size: 90%;margin-top: -30px">${entResult.preliminaryscore } 分</p></div>
													</a>
												</c:when>
												<c:otherwise>
												<div class = "la1" style="margin:0px;margin:auto;line-height: 400%;text-align: center;" >暂无</div>
												<br />
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${empty processState }">
											<div class = "la1" style="margin:0px;margin:auto;line-height: 400%;text-align: center;" >未申请</div>
											<br />
										</c:if>
								</div>
								<div style="width: 50%;height: 49%;float: right;">
										<c:if test="${! empty processState }">
											<c:choose>
												<c:when test="${processState.applyreportstate == 1 && processState.scorestate == 5}">
													<div class = "la1" style="margin:0px;margin:auto;line-height: 350%;text-align: center;" ><span>终评等级：${entResult.finallevel }</span><p style="color: rgb(6, 139, 159); font-size: 90%;margin-top: -30px">${entResult.finalscore } 分</p></div>
												</c:when>
												<c:otherwise>
												<div class = "la1" style="margin:0px;margin:auto;line-height: 400%;text-align: center;" >暂无</div>
												<br />
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${empty processState }">
											<div class = "la1" style="margin:0px;margin:auto;line-height: 400%;text-align: center;" >未申请</div>
											<br />
										</c:if>
								</div>
							</div>
						</div>
					</div>
					<!--右侧 内容 上面基础信息部分   end-->
					<!--右侧 内容 中面操作流程轮播部分-->
					
	<div >
		<div class="step-block relative">
	    <div class="main-container" style="margin-top:50px;margin-bottom: 25px;text-align: center;">
	        <h1 class="block-title">用户申请评分报告流程</h1>
	
	        <p class="center">请熟悉我们的操作流程</p>
	
	        <div class="step">
	            <div class="singleblock">
	                <div class="step-image standout">
		                <a href="javascript:void(0);" onclick="readProtocols('${processState.readstate}','${corp_name }')" class="hover">
		                <c:if test="${! empty processState  && processState.readstate==0}">
		                   <img src="<%=path%>/Images/readagree.png"  id="readagree">
		                </c:if>
		                <c:if test="${! empty processState  && processState.readstate==1}">
		                   <img src="<%=path%>/Images/readagree2.png" id="readagree2">
		                </c:if>
		                <c:if test="${empty processState}">
		                   <img src="<%=path%>/Images/readagree.png" id="readagree">
		                </c:if>
						</a>
	                </div>
	                <div class="step-title">阅读协议</div>
	                <div class="hidden-xs">
	                    <div class="step-num">01
	                        <div class="left-line">
	                            <hr>
	                        </div>
	                        <div class="right-line">
	                            <hr>
	                        </div>
	                    </div>
	                </div>
	                <div class="step-detail">请仔细阅读协议并遵守</div>
	            </div>
	            <div class="singleblock">
	                <div class="step-image standout">
	                	<a href="javascript:void(0);" onclick="inputView()" class="hover">
	                	<c:if test="${! empty processState  && processState.baseinfostate==0}">
		                   <img src="<%=path%>/Images/fillInfo.png"  id="fillInfo">
		                </c:if>
		                <c:if test="${! empty processState  && processState.baseinfostate==1}">
		                   <img src="<%=path%>/Images/fillInfo2.png" id="fillInfo2">
		                </c:if>
		                <c:if test="${empty processState}">
		                   <img src="<%=path%>/Images/fillInfo.png" id="fillInfo">
		                </c:if>
						</a>
	                </div>
	                <div class="step-title">填写基本信息</div>
	                <div class="hidden-xs">
	                    <div class="step-num">02
	                        <div class="left-line">
	                            <hr>
	                        </div>
	                        <div class="right-line">
	                            <hr>
	                        </div>
	                    </div>
	                </div>
	                <div class="step-detail">根据要求填写完成基本信息</div>
	            </div>
	            <div class="singleblock">
	                <div class="step-image standout">
	                	<a href="javascript:void(0);"  onclick="inputView()" class="hover">
	                	<c:if test="${! empty processState  && processState.pushmodelstate==0}">
		                   <img src="<%=path%>/Images/fillFinaInfo.png"  id="fillFinaInfo">
		                </c:if>
		                <c:if test="${! empty processState  && processState.pushmodelstate==1}">
		                   <img src="<%=path%>/Images/fillFinaInfo2.png" id="fillFinaInfo2" >
		                </c:if>
		                <c:if test="${empty processState}">
		                   <img src="<%=path%>/Images/fillFinaInfo.png"  id="fillFinaInfo">
		                </c:if>
						</a>
	                </div>
	                <div class="step-title">填写信用信息</div>
	                <div class="hidden-xs">
	                    <div class="step-num">03
	                        <div class="left-line">
	                            <hr>
	                        </div>
	                        <div class="right-line">
	                            <hr>
	                        </div>
	                    </div>
	                </div>
	                <div class="step-detail">根据要求填写完成信用信息</div>
	            </div>
	            <div class="singleblock">
	                <div class="step-image standout">
	                	<a href="javascript:void(0);"  onclick="inputView()" class="hover">
	                	<c:if test="${! empty processState  && processState.uploadfilestate==0}">
		                   <img src="<%=path%>/Images/uploadImg.png" id="uploadImg">
		                </c:if>
		                <c:if test="${! empty processState  && processState.uploadfilestate==1}">
		                   <img src="<%=path%>/Images/uploadImg2.png" id="uploadImg2" >
		                </c:if>
		                <c:if test="${empty processState}">
		                   <img src="<%=path%>/Images/uploadImg.png" id="uploadImg">
		                </c:if>
						</a>
	                </div>
	                <div class="step-title">上传扫描件</div>
	                <div class="hidden-xs">
	                    <div class="step-num">04
	                        <div class="left-line">
	                            <hr>
	                        </div>
	                        <div class="right-line">
	                            <hr>
	                        </div>
	                    </div>
	                </div>
	                <div class="step-detail">上传贵公司的企业资质证书，营业证号等扫描件</div>
	            </div>
	            <div class="singleblock">
	                <div class="step-image standout">
	                	<a href="javascript:void(0);" onclick="downloadReport();" class="hover" >
	                	<c:if test="${! empty processState  && processState.applyreportstate==0}">
		                   <img src="<%=path%>/Images/report.png" id="report">
		                </c:if>
		                <c:if test="${! empty processState  && processState.applyreportstate==1}">
		                   <img src="<%=path%>/Images/report2.png" id="report2" >
		                </c:if>
		                <c:if test="${empty processState}">
		                   <img src="<%=path%>/Images/report.png" id="report">
		                </c:if>
						</a>
	                </div>
	                <div class="step-title">申请生成报告</div>
	                <div class="hidden-xs">
	                    <div class="step-num">05
	                        <div class="left-line">
	                            <hr>
	                        </div>
	                        <div class="right-line">
	                            <hr>
	                        </div>
	                    </div>
	                </div>
	                <div class="step-detail">申请评分报告,我们会在两个工作日内通知您审核结果。</div>
	            </div>
	        </div>
	    </div>
	</div>
	
						<div class="carousel slide" id="carousel-302776" >
							<ol class="carousel-indicators">
								<li class="active" data-slide-to="0" data-target="#carousel-302776">
								</li>
								<li data-slide-to="1" data-target="#carousel-302776">
								</li>
								<li data-slide-to="2" data-target="#carousel-302776">
								</li>
								<li data-slide-to="3" data-target="#carousel-302776">
								</li>
								<li data-slide-to="4" data-target="#carousel-302776">
								</li>
							</ol>
							<div class="carousel-inner">
								<div class="item active">
									<img alt="阅读协议" onclick="readProtocols('${processState.readstate}','${corp_name }')" src="<%=path %>/Images/1.jpg" />
								</div>
								<div class="item">
									<img alt="填写基础信息" onclick="inputView()" src="<%=path %>/Images/2.jpg" />
								</div>
								<div class="item">
									<img alt="填写信用信息" onclick="inputView()" src="<%=path %>/Images/3.jpg" />
								</div>
								<div class="item">
									<img alt="上传扫描件" onclick="inputView()" src="<%=path %>/Images/4.jpg" />
								</div>
								<div class="item">
									<img alt="生成报告" onclick="downloadReport()" src="<%=path %>/Images/5.jpg" />
								</div>
						</div> <a class="left " href="#carousel-302776" data-slide="prev" style="color: white;"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right " style="color: white;" href="#carousel-302776" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
						</div>
					</div>
				</div><!--#565a5c-->
				
				<%--foot start--%>
					<%@ include file="/WEB-INF/Page/Share/foot.jsp"%>
				<%--foot end--%>
			</div>
		</div>
		
		
	</body>
	<script src="<%=path %>/Resources/jquery/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/ucenterPublic/Js/ucenterPublic.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/base.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/personalBase.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" charset="utf-8">
	
	//轮播图
		$(function(){
	   		$('#carousel-302776').carousel('cycle');
	    	$('#carousel-302776').carousel({ interval: 100 });
	    });
	</script>
</html>