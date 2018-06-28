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
		<link rel="stylesheet" type="text/css" href="<%=path %>/Resources/bootstrap/css/bootstrap.min.css">
		<%-- left 菜单  插件 的css--%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/Resources/ucenterPublic/Css/reset.css">
		<link rel="stylesheet" type="text/css" href="<%=path %>/Resources/ucenterPublic/Css/ucenter.css">
		<%-- 本页面的css --%>
		<link rel="stylesheet" href="<%=path %>/Styles/enterprise/my-resume1025.css">
		<%-- header left 的框架css --%>
		<link rel="stylesheet" type="text/css" href="<%=path %>/Styles/Share/frame.css">
		<%-- 行业选择  插件 --%>
		<link href="<%=path%>/Resources/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
		<%-- 上传组件webuploader的框架css --%>
		<link rel="stylesheet" href="<%=path %>/Styles/upload/webuploader.css">
		<link rel="stylesheet" href="<%=path %>/Styles/upload/style.css">
		<link rel="stylesheet" href="<%=path %>/Styles/upload/upload.css">
	<style type="text/css">
	body{
		font-family: Tahoma,Arial,Helvetica,"microsoft yahei","Hiragino Sans GB",Simsun,宋体,sans-serif;
	}
	.indent {
			left:-15px;
			position:relative;
			animation-name:myfirst;
			animation-duration:1s;
			animation-timing-function:linear;
			animation-delay:0s;
			animation-iteration-count:infinite;
			animation-direction:normal;
			animation-play-state:running;
			/* Safari and Chrome: */
			-webkit-animation-name:myfirst;
			-webkit-animation-duration:1s;
			-webkit-animation-timing-function:linear;
			-webkit-animation-delay:0s;
			-webkit-animation-iteration-count:infinite;
			-webkit-animation-direction:normal;
			-webkit-animation-play-state:running;
	}
	
	@keyframes myfirst
{
	0%   {left:-15px;}
	100% {left:9px;}
}

@-webkit-keyframes myfirst /* Safari and Chrome */
{
	0%   {left:-15px;}
	100% {left:9px;}
}

	.open {
			left:9px;
			position:relative;
			animation-name:myfirst1;
			animation-duration:1s;
			animation-timing-function:linear;
			animation-delay:0s;
			animation-iteration-count:infinite;
			animation-direction:normal;
			animation-play-state:running;
			/* Safari and Chrome: */
			-webkit-animation-name:myfirst1;
			-webkit-animation-duration:1s;
			-webkit-animation-timing-function:linear;
			-webkit-animation-delay:0s;
			-webkit-animation-iteration-count:infinite;
			-webkit-animation-direction:normal;
			-webkit-animation-play-state:running;
	}
	@keyframes myfirst1
{
	0%   {left:9px;}
	100% {left:-15px;}
}

@-webkit-keyframes myfirst1 /* Safari and Chrome */
{
	0%   {left:9px;}
	100% {left:-15px;}
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
		<div id="zhong" >
		<%--LeftMenu start--%>
		<%@ include file="/WEB-INF/Page/Share/leftMenuPer.jsp"%>
		<%--LeftMenu end --%>
		<div style="width: 100%; padding-left: 200px; margin: auto;">
				<div class="resume-box" style="box-shadow: 10px 10px 20px #ddd;">
					<%-- 个人基础信息 --%>
					<div class="resume-content" edit-statue="false" data-statue="true" id="PerBaseInfo">
						<div class="browse-head">
							<div class="line-area"></div>
							<div class="headLarea">
								<span class="resumeicon icon1"></span>
								<span class="resumeHeadtitle">基本信息</span>
								<span class="resumeHeadtip"><em>*</em>为必填项</span>
							</div>
							<div class="headRarea">
								<span class="resumeicon icon0"></span>
								<span class="resume-edit">编辑</span>
							</div>
						</div>
						<%-- 个人基础信息 展示 --%>
						<div class="resume-browse-area" style="display: block;">
						</div>
						<%-- 个人基础信息 编辑--%>
						<div class="resume-edit-area " style="display: none;">
						</div>
					</div>
					
						<%-- 企业信用指标信息 --%>
					<div class="resume-content" edit-statue="false" data-statue="false" id="CreditIndex">
						<div class="browse-head">
							<div class="line-area"></div>
							<div class="headLarea">
								<span class="resumeicon icon1"></span>
								<span class="resumeHeadtitle">信用信息</span>
								<span class="resumeHeadtip"><em>*</em>为必填项</span>
							</div>
							<div class="headRarea">
								<span class="resumeicon icon0"></span>
								<span class="resume-edit">编辑</span>
							</div>
						</div>
						<%-- 企业信用指标信息 展示 --%>
						<div class="resume-browse-area" style="display: block;">
						</div>
						<%-- 企业信用指标信息 编辑--%>
						<div class="resume-edit-area " style="display: none;">
						</div>
					</div>
					
					
					<div class="resume-content" edit-statue="false" data-statue="false" id="Education">
						<div class="browse-head">
							<div class="line-area"></div>
							<div class="headLarea">
								<span class="resumeicon icon2"></span>
								<span class="resumeHeadtitle">教育信息</span>
								<span class="resumeHeadtip"><em>*</em>为必填项</span>
							</div>
							<div class="headRarea">
								<span class="resumeicon icon0"></span>
								<span class="resume-edit">添加</span>
							</div>
						</div>
						<div class="resume-browse-area" style="display: none;">
						</div>
						<div class="resume-edit-area" style="display: none;">
						</div>
						<div class="addmorebox" style="display: block">
							<a class="addmore"><span class="addmore1"><em></em>添加教育信息</span><span class="addmore2">完善您的个人信息</span></a>
						</div>
				  </div>
				
				<!-- 工作经历 -->
				 <div class="resume-content" edit-statue="false" data-statue="false" id="Career">
					<div class="browse-head">
						<div class="line-area"></div>
						<div class="headLarea">
							<span class="resumeicon icon2"></span>
							<span class="resumeHeadtitle">工作经历</span>
							<span class="resumeHeadtip"><em>*</em>为必填项</span></div>
						<div class="headRarea">
							<span class="resumeicon icon0"></span>
							<span class="resume-edit">添加</span>
						</div>
					</div>
					<div class="resume-browse-area" style="display: none;">
					</div>
					<div class="resume-edit-area" style="display: none;">
						
					</div>
					<div class="addmorebox" style="display: block">
						<a class="addmore"><span class="addmore1"><em></em>添加工作经历</span><span class="addmore2">完善您的个人信息</span></a>
					</div>
				</div>
				
				<!-- 培训 -->
				<div class="resume-content" edit-statue="false" data-statue="false" id="Train">
					<div class="browse-head">
						<div class="line-area"></div>
						<div class="headLarea">
							<span class="resumeicon icon2"></span>
							<span class="resumeHeadtitle">培训经历</span>
							</div>
						<div class="headRarea">
							<span class="resumeicon icon0"></span>
							<span class="resume-edit">添加</span>
						</div>
					</div>
					<div class="resume-browse-area" style="display: none;">
					</div>
					<div class="resume-edit-area" style="display: none;">
						
					</div>
					<div class="addmorebox" style="display: block">
						<a class="addmore"><span class="addmore1"><em></em>添加培训经历</span><span class="addmore2">完善您的个人信息</span></a>
					</div>
				</div>
				
				<!-- 职业技能 -->
				 <div class="resume-content" edit-statue="false" data-statue="false" id="Skills">
					<div class="browse-head">
						<div class="line-area"></div>
						<div class="headLarea">
							<span class="resumeicon icon2"></span>
							<span class="resumeHeadtitle">职业技能</span>
							<span class="resumeHeadtip"><em>*</em>为必填项</span></div>
						<div class="headRarea">
							<span class="resumeicon icon0"></span>
							<span class="resume-edit">添加</span>
						</div>
					</div>
					<div class="resume-browse-area" style="display: none;">
					</div>
					<div class="resume-edit-area" style="display: none;">
						
					</div>
					<div class="addmorebox" style="display: block">
						<a class="addmore"><span class="addmore1"><em></em>添加职业技能</span><span class="addmore2">完善您的个人信息</span></a>
					</div>
				</div>
				
					<%-- 个人附件信息--%>
					<div class="resume-content" edit-statue="false" data-statue="false" id="UploadFile">
						<div class="browse-head">
							<div class="line-area"></div>
							<div class="headLarea">
								<span class="resumeicon icon1"></span>
								<span class="resumeHeadtitle">附件信息</span>
								<span class="resumeHeadtip"><em>*</em>为必填项</span>
							</div>
							<div class="headRarea">
								<span class="resumeicon icon0"></span>
								<span class="resume-edit" style="display:none">取消</span>
							</div>
						</div>
					
						<div class="form-item">
							<div class="dream-label">附件列表 /
							<a class="checkfile" style="color:#ff6a0e;">检查文件</a>
							<em style="font-size: 12px">（提示：请检查文件，否则无法申请报告）</em>
							<div class="tip"></div>
							<div class="dream-controls"></div>
							</div>
	                    </div>
                    	<div id="uploadFileList"></div>
                    	<div id="uploader" style=" position: absolute; opacity: 0;">
							<div class="queueList">
								<div id="dndArea" class="placeholder">
									<div id = "title" style="text-align: center;margin: -120px  auto 150px;" data-value = ""></div>
									<div id="filePicker"></div>
									<p>或将照片拖到这里(上传文件类型为gif,jpg,jpeg,bmp,png)</p>
								</div>
							</div>
							<div class="statusBar" style="display:none;">
								<div class="progress">
									<span class="text">0%</span> <span class="percentage"></span>
								</div>
								<div class="info"></div>
								<div class="btns">
									<div id="filePicker2"></div>
									<div class="uploadBtn" data-url = "/personalInfo/uploadFile.do" >开始上传</div>
								</div>
							</div>
						</div>
					</div>
					<%--<!--更多模块控制start-->
					<div class="resume-content resume-content2" edit-statue="false" data-statue="false">
						<!--浏览信息start-->
						<div class="resume-browse-area resume-browse-area-more">
							<div class="browse-head">
								<div class="line-area"></div>
								<div class="headLarea">
									<span class="resumeicon icon4"></span>
									<span class="resumeHeadtitle">更多模块</span>
								</div>
							</div>
						</div>
						<!--编辑信息start-->
						<div class="resume-edit-area resume-edit-area-more">
							<a  class="controlbt">完善更多简历信息<em></em></a>
						</div>
						<!--编辑信息end-->
					</div>
					<!--更多模块控制end-->
				--%>
				</div>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<!--right part-->
				
				<div id = "narrow"><a  href = "###" class = "indent">&gt;&gt;</a></div>
				<div class="resume-Rarea" style="background-color: #fff">
					<div class="progress0">
						<div class="progress-text" >信息完整度<span>0</span>%<b></b></div>
						<div class="progress-line">
							<div style="width:0%;height: 5px;background: rgb(0, 170, 238) none repeat scroll 0% 0%;border-radius: 5px;"></div>
						</div>
					</div>
					<div class="info-name-items" >
						<div class="info-itemswrap">
							<a class="info-name-item base-info-nav base-info-navact" data-tap="base-info-navact" href="#PerBaseInfo"><em></em><span class="fl"><b></b>基本信息</span><span class="fr ">未添加</span></a>
							<a class="info-name-item expect-job-nav expect-job-navact" data-tap="expect-job-navact" href="#CreditIndex"><em></em><span class="fl"><b></b>推送信息</span><span class="fr ">未添加</span></a>
							<a class="info-name-item edu-info-nav edu-info-navact" data-tap="edu-info-navact" href="#Education"><em></em><span class="fl"><b></b>教育信息</span><span class="fr ">未添加</span></a>
							<a class="info-name-item base-info-nav base-info-navact" data-tap="base-info-navact" href="#Career"><em></em><span class="fl"><b></b>工作经验</span><span class="fr ">未添加</span></a>
							<a class="info-name-item base-info-nav base-info-navact" data-tap="base-info-navact" href="#Train"><em></em><span class="fl"><b></b>培训经验</span><span class="fr ">未添加</span></a>
							<a class="info-name-item base-info-nav base-info-navact" data-tap="base-info-navact" href="#Skills"><em></em><span class="fl"><b></b>专业技能</span><span class="fr ">未添加</span></a>
							<a class="info-name-item base-info-nav base-info-navact" data-tap="base-info-navact" href="#UploadFile"><em></em><span class="fl"><b></b>附件信息</span><span class="fr ">未添加</span></a>
						</div>
						<%--<div class="info-itemswrap info-itemswrap2" >
							<a class="info-name-item work-exp-nav work-exp-navact" data-tap="work-exp-navact" href="#workExp"><em></em><span class="fl"><b></b>工作经验   </span><span class="fr ">未添加</span></a>
							<a class="info-name-item project-exp-nav project-exp-navact" data-tap="project-exp-navact" href="#projectExp"><em></em><span class="fl"><b></b>项目经验</span><span class="fr ">未添加</span></a>
							<a class="info-name-item skill-info-nav skill-info-navact" data-tap="skill-info-navact" href="#skillInfo"><em></em><span class="fl"><b></b>专业技能</span><span class="fr ">未添加</span></a>
							<a class="info-name-item self-evaluate-nav self-evaluate-navact" data-tap="self-evaluate-navact" href="#selfEvaluate"><em></em><span class="fl"><b></b>自我描述</span><span class="fr ">未添加</span></a>
							<a class="info-name-item extra-info-nav extra-info-navact" data-tap="extra-info-navact" href="#extraInfo"><em></em><span class="fl"><b></b>附加信息</span><span class="fr ">未添加</span></a>
						</div>
					--%>
					</div>

					<div class="nav-link" style="padding-left: 45px; margin-bottom: 20px;">
						<button href="javascript:void(0)" class="preview-resume">申请报告</button>
					</div>
				</div>
				<!--right part end-->
				
		</div>
		<div class = "modal-overlay">
		    <div class="loader-inner line-scale-pulse-out" style="margin-top: 20%;">
		      <div></div>
		      <div></div>
		      <div></div>
		      <div></div>
		      <div></div>
		    </div>
		</div>
	</body>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/Resources/jquery/jquery-1.11.3.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/Resources/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" charset="utf-8" src = "<%=path %>/Resources/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/Script/upload/webuploader.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/Script/upload/upload.js" ></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/Resources/ucenterPublic/Js/ucenterPublic.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/Script/menu/base.js"></script>
 	<script type="text/javascript" charset="utf-8" src="<%=path %>/Script/menu/personalBase.js" ></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/Script/personal/mainProcess.js"></script>
</html>