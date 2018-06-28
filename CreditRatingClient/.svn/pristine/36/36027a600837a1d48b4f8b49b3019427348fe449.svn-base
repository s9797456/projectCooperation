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
		<%@ include file="/WEB-INF/Page/Share/leftMenu.jsp"%>
		<%--LeftMenu end--%>
		<div id="right" >
			 <div id="rightView" class="container">


				<table style="width: 96%;background-color: #fff">
						
						<!-- 基础信息  -->
						<tr>
							<td>
								<!-- 标题  -->
								<table class="table table-hover" style="min-width: 150px;">
									<thead>
										<tr>
											<th  >企业基础信息</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>企业名称</td>
										</tr>
										<tr>
											<td>统一社会信用代码</td>
										</tr>
										<tr>
											<td>公司地址</td>
										</tr>
										<tr>
											<td>法人代表</td>
										</tr>
										<tr>
											<td>Email</td>
										</tr>
										<tr>
											<td>Tel</td>
										</tr>
										<tr>
											<td>公司官网</td>
										</tr>
									</tbody>
								</table>
								<!-- 标题  end-->
							</td>
							<c:if test="${! empty historicalVO }" >
							<td>
								<!-- 当前对象  -->
								<table  class="table table-hover">
									<thead>
									<tr>
										<th>&nbsp;</th>
									</tr>
								</thead>
									<tbody>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.name }">
															${historicalVO.name }
												</c:if>
												<c:if test="${ empty historicalVO.name }">
													-
												</c:if>
											</td>
										</tr>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.uscc }">
															${historicalVO.uscc }
												</c:if>
												<c:if test="${ empty historicalVO.uscc }">
													-
												</c:if>
											</td>
										</tr>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.address }">
															${historicalVO.address }
												</c:if>
												<c:if test="${ empty historicalVO.address }">
													-
												</c:if>
											</td>
										</tr>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.legalPerson }">
															${historicalVO.legalPerson }
												</c:if>
												<c:if test="${ empty historicalVO.legalPerson }">
													-
												</c:if>
											</td>
										</tr>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.email }">
															${historicalVO.email }
												</c:if>
												<c:if test="${ empty historicalVO.email }">
													-
												</c:if>
											</td>
										</tr>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.tel }">
															${historicalVO.tel }
												</c:if>
												<c:if test="${ empty historicalVO.tel }">
													-
												</c:if>
											</td>
										</tr>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.website }">
															${historicalVO.website }
												</c:if>
												<c:if test="${ empty historicalVO.website }">
													-
												</c:if>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
							</c:if>
								<!-- 当前对象  end-->
								<!-- 历时对象  -->
								<c:if test="${! empty historicalVOs }"> 
								<c:forEach var="ent" items="${historicalVOs }">
									<td>
										<table  class="table table-hover">
											<thead>
												<tr>
													<th>&nbsp;</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td class = "text-overflow">
														<c:if test="${! empty ent.name }">
															${ent.name }
														</c:if>
														<c:if test="${ empty ent.name }">
															-
														</c:if>
													</td>
												</tr>
												<tr>
													<td class = "text-overflow">
														<c:if test="${! empty ent.uscc }">
															${ent.uscc }
														</c:if>
														<c:if test="${ empty ent.uscc }">
															-
														</c:if>
													</td>
												</tr>
												<tr>
													<td class = "text-overflow">
														<c:if test="${! empty ent.address }">
															${ent.address }
														</c:if>
														<c:if test="${ empty ent.address }">
															-
														</c:if>
													</td>
												</tr>
												<tr>
													<td class = "text-overflow">
														<c:if test="${! empty ent.legalPerson }">
															${ent.legalPerson }
														</c:if>
														<c:if test="${ empty ent.legalPerson }">
															-
														</c:if>
													</td>
												</tr>
												<tr>
													<td class = "text-overflow">
														<c:if test="${! empty ent.email }">
															${ent.email }
														</c:if>
														<c:if test="${ empty ent.email }">
															-
														</c:if>
													</td>
												</tr>
												<tr>
													<td class = "text-overflow">
														<c:if test="${! empty ent.tel }">
															${ent.tel }
														</c:if>
														<c:if test="${ empty ent.tel }">
															-
														</c:if>
													</td>
												</tr>
												<tr>
													<td class = "text-overflow">
														<c:if test="${! empty ent.website }">
															${ent.website }
														</c:if>
														<c:if test="${ empty ent.website }">
															-
														</c:if>
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</c:forEach>
							</c:if>
								<!-- 历时对象  end-->
						</tr>
						<!-- 基础信息  end  -->
						<!-- 评分信息  -->
						<tr>
							<td>
								<!-- 标题  -->
								<table  class="table table-hover" style="min-width: 150px;">
									<thead>
										<tr>
											<th>评分日期</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>评分编码</td>
										</tr>
										<tr>
											<td>评分等级</td>
										</tr>
										<tr>
											<td>评分分数</td>
										</tr>
										<tr>
											<td>评分日期</td>
										</tr>
									</tbody>
								</table>
								<!-- 标题  end-->
							</td>
								<!-- 当前对象  -->
							<c:if test="${! empty historicalVO }" >
							<td>
								<table  class="table table-hover">
									<thead>
										<tr>
											<th>&nbsp;</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.encoding }">
													${historicalVO.encoding }
												</c:if>
												<c:if test="${ empty historicalVO.encoding }">
													-
												</c:if>
											</td>
										</tr>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.finalLevel }">
													${historicalVO.finalLevel }
												</c:if>
												<c:if test="${ empty historicalVO.finalLevel }">
													-
												</c:if>
											</td>
										</tr>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.finalScore }">
													${historicalVO.finalScore }
												</c:if>
												<c:if test="${ empty historicalVO.finalScore }">
													-
												</c:if>
											</td>
										</tr>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.gradeTime }">
													${historicalVO.gradeTime }
												</c:if>
												<c:if test="${ empty historicalVO.gradeTime }">
													-
												</c:if>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
							</c:if>
								<!-- 当前对象  end-->
								<!-- 历时对象  -->
								<c:if test="${! empty historicalVOs }"> 
								<c:forEach var="ent" items="${historicalVOs }">
									<td>
										<table  class="table table-hover">
											<thead>
												<tr>
													<th>&nbsp;</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td class = "text-overflow">
														<c:if test="${! empty ent.encoding }">
															${ent.encoding }
														</c:if>
														<c:if test="${ empty ent.encoding }">
															-
														</c:if>
													</td>
												</tr>
												<tr>
													<td class = "text-overflow">
														<c:if test="${! empty ent.finalLevel }">
															${ent.finalLevel }
														</c:if>
														<c:if test="${ empty ent.finalLevel }">
															-
														</c:if>
													</td>
												</tr>
												<tr>
													<td class = "text-overflow">
														<c:if test="${! empty ent.finalScore }">
															${ent.finalScore }
														</c:if>
														<c:if test="${ empty ent.finalScore }">
															-
														</c:if>
													</td>
												</tr>
												<tr>
													<td class = "text-overflow">
														<c:if test="${! empty ent.gradeTime }">
															${ent.gradeTime }
														</c:if>
														<c:if test="${ empty ent.gradeTime }">
															-
														</c:if>
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</c:forEach>
							</c:if>
								<!-- 历时对象  end-->
						</tr>
						<!-- 评分信息  end  -->
						<!-- 模型信息  -->
						<tr>
							<td>
								<!-- 标题  -->
								<table  class="table table-hover" style="min-width: 150px;">
									<thead>
										<tr>
											<th>模型</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<p>模型名称</p>
											</td>
										</tr>
										<tr>
											<td>模型版本</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<!-- 标题  end-->
							</td>
								<!-- 当前对象  -->
							<c:if test="${! empty historicalVO }" >
							<td>
								<table  class="table table-hover">
									<thead>
										<tr>
											<th>&nbsp;</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.modelName }">
													${historicalVO.modelName }
												</c:if>
												<c:if test="${ empty historicalVO.modelName }">
													-
												</c:if>
											</td>
										</tr>
										<tr>
											<td class = "text-overflow">
												<c:if test="${! empty historicalVO.modelVersion }">
													${historicalVO.modelVersion }
												</c:if>
												<c:if test="${ empty historicalVO.modelVersion }">
													-
												</c:if>
											</td class = "text-overflow">
										</tr>
										<thead>
										<tr>
											<td class = "text-overflow">
												<%--<a href="javascript:details('${historicalVO.entuuid }')">查看详情</a>
												--%><a href="javascript:downloadReport();">下载报告</a> 
											</td>
										</tr>
									</thead>
									</tbody>
								</table>
							</td>
							</c:if>
								<!-- 当前对象  end-->
								<!-- 历时对象  -->
								<c:if test="${! empty historicalVOs }"> 
								<c:forEach var="ent" items="${historicalVOs }">
									<td >
										<table  class="table table-hover">
											<thead>
												<tr>
													<th>&nbsp;</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
														<c:if test="${! empty ent.modelName }">
															${ent.modelName }
														</c:if>
														<c:if test="${ empty ent.modelName }">
															-
														</c:if>
													</td>
												</tr>
												<tr>
													<td>
														<c:if test="${! empty ent.modelVersion }">
															${ent.modelVersion }
														</c:if>
														<c:if test="${ empty ent.modelVersion }">
															-
														</c:if>
													</td>
												</tr>
												<tr>
													<td >
														<a href="javascript:downloadReport_historical('${ent.historicalId }');">下载报告</a> 
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</c:forEach>
							</c:if>
								<!-- 历时对象  end-->
						</tr>
						<!-- 模型信息  end  -->
	  		</div>
	 	</div>
		
	</body>
	<script src="<%=path %>/Resources/jquery/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/ucenterPublic/Js/ucenterPublic.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/base.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/entBase.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		/*获取当前对象的详细信息*/

	</script>
</html>