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
<script>
	var _ctxPath="<%=request.getContextPath() %>";
</script>
<style type="text/css">
	.list-group > li{
		height: 47px;
		margin-top: 0px;
		margin-bottom: 0px;
	}
	.list-group > li:hover{
		margin: 5px;
		box-shadow:-5px -1px 50px #2d2d2d;
		z-index:1;
	}
</style>
</head>
<body>
	<!--头-->
	<%--header start--%>
	<%@ include file="/WEB-INF/Page/Share/header.jsp"%>
	<%--header end--%>
	<%--LeftMenu start--%>
	<%@ include file="/WEB-INF/Page/Share/leftMenuPer.jsp"%>
	<%--LeftMenu end--%>
	<div id = "compare" class="list-group-item" style="position:fixed;top:0px;width: 100%;padding-left: 200px;min-width: 800px;z-index: 2;" >
		<form id = "compareFrom" style="padding-left: 10%;width: 600px;overflow: auto;" >
		</form>
		<div style="position: absolute;right: 12.5%;top: 10px;" >
			<span style="color: #ccc;" >[<span id = "numCompare">0</span>/3]</span>&nbsp;
			<button type="button" class="btn btn-default btn-sm" onclick="compare()" >
	          <span class="glyphicon glyphicon-random"></span>&nbsp;&nbsp;compare
	        </button>
		</div>
	</div>
	<div id="right" style="padding-left: 200px;" >
		<div style="width:70%;min-width:600px;margin:80px auto;">
		<ul class="list-group">
		    <li class="list-group-item" style="height: auto;overflow: hidden;">
			    <table class="table table-striped table-hover">
				  <caption>
					  <a href="javascript:;" style="width: 85%;display:inline-block;" title = "点击展开详情">当前状态</a>
					  <div style = "width: 10%;min-width:90px;float: right;margin-top: -10px;">
					  		<a class="btn btn-default" role="button" href="javascript:downloadReport('${corp_name }');" title= "下载报告" > <span class="glyphicon glyphicon-download-alt"></span></a> 
					  		<a class="btn btn-default contrast" role="button" href="javascript:;" title= "对比其他记录" >
					  			<input type="hidden" value = "${perHistoriesVO.uuid }" />
					  			<span class = "glyphicon glyphicon-plus" ></span>
					  		</a> 
					  </div>
				  </caption>
				  
				    <tr>
				      <td style="width: 10%" >姓名</td>
				      <td style="width: 20%">
				      		<c:if test="${empty perHistoriesVO.name }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.name }">${perHistoriesVO.name }</c:if>
				      </td>
				      <td style="width: 10%">评级编码</td>
				      <td style="width: 20%">
							<c:if test="${empty perHistoriesVO.encoding }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.encoding }">${perHistoriesVO.encoding }</c:if>
					  </td>
				    </tr>
				    <tr>
				      <td>曾用名</td>
				      <td>
							<c:if test="${empty perHistoriesVO.usedname }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.usedname }">${perHistoriesVO.usedname }</c:if>
					  </td>
					  <td>评级时间</td>
				      <td>
							<c:if test="${empty perHistoriesVO.gradetime }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.gradetime }">${perHistoriesVO.gradetime }</c:if>
					  </td>
				    </tr>
				    <tr>
				      <td>政治面貌</td>
				      <td>
							<c:if test="${empty perHistoriesVO.politicaloutlook }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.politicaloutlook }">${perHistoriesVO.politicaloutlook }</c:if>
					  </td>
					  <td>初评等级</td>
				      <td>
							<c:if test="${empty perHistoriesVO.preliminarylevel }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.preliminarylevel }">${perHistoriesVO.preliminarylevel }</c:if>
					  </td>
				    </tr>
				    <tr>
				      <td>婚姻状况</td>
				      <td>
							<c:if test="${empty perHistoriesVO.maritalstatus }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.maritalstatus }">${perHistoriesVO.maritalstatus }</c:if>
					  </td>
					  <td>初评分数</td>
				      <td>
							<c:if test="${empty perHistoriesVO.preliminaryscore }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.preliminaryscore }">${perHistoriesVO.preliminaryscore }</c:if>
					  </td>
				    </tr>
				    <tr>
				      <td>生育情况</td>
				      <td>
							<c:if test="${empty perHistoriesVO.fertilitycondition }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.fertilitycondition }">${perHistoriesVO.fertilitycondition }</c:if>
					  </td>
					   <td>终评等级</td>
				       <td>
							<c:if test="${empty perHistoriesVO.finallevel }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.finallevel }">${perHistoriesVO.finallevel }</c:if>
					   </td>
				    </tr>
				    <tr>
				      <td>居住地址</td>
				      <td>
							<c:if test="${empty perHistoriesVO.presentaddress }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.presentaddress }">${perHistoriesVO.presentaddress }</c:if>
					  </td>
					  <td>终评分数</td>
				      <td>
							<c:if test="${empty perHistoriesVO.finalscore }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.finalscore }">${perHistoriesVO.finalscore }</c:if>
					  </td>
				    </tr>
				    <tr>
				      <td>户籍地址</td>
				      <td>
							<c:if test="${empty perHistoriesVO.permanentaddress }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.permanentaddress }">${perHistoriesVO.permanentaddress }</c:if>
					  </td>
					  <td></td>
					  <td></td>	
				    </tr>
				    <tr>
				      <td>身份证签发机关</td>
				      <td>
							<c:if test="${empty perHistoriesVO.idissuingagency }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.idissuingagency }">${perHistoriesVO.idissuingagency }</c:if>
					  </td>
					  <td >模型名称</td>
				      <td >
							<c:if test="${empty perHistoriesVO.modelName }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.modelName }">${perHistoriesVO.modelName }</c:if>
					  </td>				
					  
				    </tr>
				    <tr>
				      <td>身份证有效期</td>
				      <td>
				      		<c:choose>
				      			<c:when test="${!empty perHistoriesVO.idtermstart && !empty perHistoriesVO.idtermend}">
				      				${perHistoriesVO.idtermstart }&nbsp;&nbsp;至&nbsp;&nbsp; ${perHistoriesVO.idtermend }
				      			</c:when>
				      			<c:when test="${!empty perHistoriesVO.idtermstart && empty perHistoriesVO.idtermend}">
				      				${perHistoriesVO.idtermstart }&nbsp;&nbsp;至&nbsp;&nbsp;无期限
				      			</c:when>
				      			<c:when test="${empty perHistoriesVO.idtermstart && !empty perHistoriesVO.idtermend}">
				      				&nbsp;&nbsp;至&nbsp;&nbsp;${perHistoriesVO.idtermend }
				      			</c:when>
				      			<c:otherwise>
				      				-
				      			</c:otherwise>
				      		</c:choose>
					  </td>
					  <td>模型版本</td>
				      <td>
							<c:if test="${empty perHistoriesVO.modelVersion }">-</c:if>
				      		<c:if test="${!empty perHistoriesVO.modelVersion }">${perHistoriesVO.modelVersion }</c:if>
					  </td>
				    </tr>
				</table>
		    </li>
		    <c:if test="${!empty perHistoriesVOs }">
		    	<c:forEach var="entit" items="${perHistoriesVOs }">
		    	 <li class="list-group-item" style="overflow: hidden;">
		    			<table class="table table-striped table-hover">
							<caption>
							 	<a href="javascript:;" style="width: 85%;display:inline-block;" title = "点击展开详情">
									<c:if test="${empty entit.gradetime }">-</c:if>
						      		<c:if test="${!empty entit.gradetime }">${entit.gradetime }</c:if>
								</a>
					  			<div style = "width: 10%;min-width:90px;float: right;margin-top: -10px;">
							  		<a class="btn btn-default" role="button" href="javascript:downloadReport_historical('${entit.uuid }');" title= "下载报告" > <span class="glyphicon glyphicon-download-alt"></span></a> 
							  		<a class="btn btn-default contrast" role="button" href="javascript:;" title= "对比其他记录" >
							  			<input type="hidden" value = "${entit.uuid }" />
							  			<span class = "glyphicon glyphicon-plus" ></span>
							  		</a> 
							  	</div>
							</caption>
						    <tr>
						      <td style="width: 10%" >姓名</td>
						      <td style="width: 20%">
						      		<c:if test="${empty entit.name }">-</c:if>
						      		<c:if test="${!empty entit.name }">${entit.name }</c:if>
						      </td>
						      <td style="width: 10%">评级编码</td>
						      <td style="width: 20%">
									<c:if test="${empty entit.encoding }">-</c:if>
						      		<c:if test="${!empty entit.encoding }">${entit.encoding }</c:if>
							  </td>
						    </tr>
						    <tr>
						      <td>曾用名</td>
						      <td>
									<c:if test="${empty entit.usedname }">-</c:if>
						      		<c:if test="${!empty entit.usedname }">${perHistoriesVO.usedname }</c:if>
							  </td>
							  <td>评级时间</td>
						      <td>
									<c:if test="${empty entit.gradetime }">-</c:if>
						      		<c:if test="${!empty entit.gradetime }">${entit.gradetime }</c:if>
							  </td>
						    </tr>
						    <tr>
						      <td>政治面貌</td>
						      <td>
									<c:if test="${empty entit.politicaloutlook }">-</c:if>
						      		<c:if test="${!empty entit.politicaloutlook }">${entit.politicaloutlook }</c:if>
							  </td>
							  <td>初评等级</td>
						      <td>
									<c:if test="${empty entit.preliminarylevel }">-</c:if>
						      		<c:if test="${!empty entit.preliminarylevel }">${entit.preliminarylevel }</c:if>
							  </td>
						    </tr>
						    <tr>
						      <td>婚姻状况</td>
						      <td>
									<c:if test="${empty entit.maritalstatus }">-</c:if>
						      		<c:if test="${!empty entit.maritalstatus }">${entit.maritalstatus }</c:if>
							  </td>
							  <td>初评分数</td>
						      <td>
									<c:if test="${empty entit.preliminaryscore }">-</c:if>
						      		<c:if test="${!empty entit.preliminaryscore }">${entit.preliminaryscore }</c:if>
							  </td>
						    </tr>
						    <tr>
						      <td>生育情况</td>
						      <td>
									<c:if test="${empty entit.fertilitycondition }">-</c:if>
						      		<c:if test="${!empty entit.fertilitycondition }">${entit.fertilitycondition }</c:if>
							  </td>
							   <td>终评等级</td>
						       <td>
									<c:if test="${empty entit.finallevel }">-</c:if>
						      		<c:if test="${!empty entit.finallevel }">${entit.finallevel }</c:if>
							   </td>
						    </tr>
						    <tr>
						      <td>居住地址</td>
						      <td>
									<c:if test="${empty entit.presentaddress }">-</c:if>
						      		<c:if test="${!empty entit.presentaddress }">${entit.presentaddress }</c:if>
							  </td>
							  <td>终评分数</td>
						      <td>
									<c:if test="${empty entit.finalscore }">-</c:if>
						      		<c:if test="${!empty entit.finalscore }">${entit.finalscore }</c:if>
							  </td>
						    </tr>
						    <tr>
						      <td>户籍地址</td>
						      <td>
									<c:if test="${empty entit.permanentaddress }">-</c:if>
						      		<c:if test="${!empty entit.permanentaddress }">${entit.permanentaddress }</c:if>
							  </td>
							  <td></td>
							  <td></td>	
						    </tr>
						    <tr>
						      <td>身份证签发机关</td>
						      <td>
									<c:if test="${empty entit.idissuingagency }">-</c:if>
						      		<c:if test="${!empty entit.idissuingagency }">${entit.idissuingagency }</c:if>
							  </td>
							  <td >模型名称</td>
						      <td >
									<c:if test="${empty entit.modelName }">-</c:if>
						      		<c:if test="${!empty entit.modelName }">${entit.modelName }</c:if>
							  </td>				
							  
						    </tr>
						    <tr>
						      <td>身份证有效期</td>
						      <td>
						      		<c:choose>
						      			<c:when test="${!empty entit.idtermstart && !empty entit.idtermend}">
						      				${entit.idtermstart }&nbsp;&nbsp;至&nbsp;&nbsp; ${entit.idtermend }
						      			</c:when>
						      			<c:when test="${!empty entit.idtermstart && empty entit.idtermend}">
						      				${entit.idtermstart }&nbsp;&nbsp;至&nbsp;&nbsp;无期限
						      			</c:when>
						      			<c:when test="${empty entit.idtermstart && !empty entit.idtermend}">
						      				&nbsp;&nbsp;至&nbsp;&nbsp;${entit.idtermend }
						      			</c:when>
						      			<c:otherwise>
						      				-
						      			</c:otherwise>
						      		</c:choose>
							  </td>
							  <td>模型版本</td>
						      <td>
									<c:if test="${empty entit.modelVersion }">-</c:if>
						      		<c:if test="${!empty entit.modelVersion }">${entit.modelVersion }</c:if>
							  </td>
						    </tr>
					</table>
		    	</li>
		    	</c:forEach>
		    </c:if>
		</ul>
		</div>
 	</div>
		
		<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					模态框（Modal）标题
				</h4>
			</div>
			<div class="modal-body">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" data-value = "true"  id = "differentItems" >
					只查看不同项
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
	</body>
	<script src="<%=path %>/Resources/jquery/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Resources/ucenterPublic/Js/ucenterPublic.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/base.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/Script/menu/personalBase.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		$("li").on("click","caption > a",function(){
			$(this).parents("li").css("height","auto").siblings().css("height","50px");
		});
		
		$(".contrast").on("click",function(){
			var value = $(this).children("input").val();
			//设置上限
			if($("#compareFrom").children("a").length >= 3 ){
				popup("已达上限");
				return;
			}
			
			//判断重复项
			var length = $("#compareFrom").find("input[data-value = '"+value+"']").length;
			if(length < 1){
				var val = $(this).children("input").val();
				str = "<input type='hidden' name = 'uuid' value='"+val+"' data-value = '"+val+"' />";
				$("#compare").animate({top:"64px"});
				$("#compareFrom").append(" <a style='margin:5px;'>"+str+"<span>"+$(this).parent("div").siblings("a").text()+"</span><button class='btn btn-link' style='margin-top: -5px;' onclick = 'deleteCompare(this)'>&times;</button></a>");
				$("#numCompare").text($("#numCompare").text()*1+1);
			}else{
				popup("你已添加过此项");
			}
			
		});
		function deleteCompare(obj){
			$(obj).parent("a").remove();
			$(obj).remove();
			$("#numCompare").text($("#numCompare").text()*1-1);
			var length = $("#compareFrom").children("a").length;
			if(length == 0){
				$("#compare").animate({top:"0px"});
			}
		}
		function compare(){
			if($("#compareFrom").find("input").length < 2){
				popup("无法比较单条记录");
				return;
			}
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:_ctxPath+'/initPersonal/compare.do',
		        data:$("#compareFrom").serialize(),
		        async: false,
		        error: function() {
		            popup("网络错误，请重新登录    错误代码：400");
		        },
		        success: function(data) {
		        	if(true/*data.status*/){
		        		var perHistoriesVOs = data.perHistoriesVOs;
		        		var length = perHistoriesVOs.length;
		        		if(perHistoriesVOs != null && length > 0){
		        			var str = "<table class='table table-hover'>";
		        			if(length == 2){
		        				str += "<tr><th>\</th><th>"+perHistoriesVOs[0].gradetime+"["+perHistoriesVOs[0].encoding+"]</th><th>"+perHistoriesVOs[1].gradetime+"["+perHistoriesVOs[1].encoding+"]</th></tr>"
		        				str += isObjectValueEqual(perHistoriesVOs[0],perHistoriesVOs[1],null);
		        			}else{
		        				str += "<tr><th>\</th><th>"+perHistoriesVOs[0].gradetime+"["+perHistoriesVOs[0].encoding+"]</th><th>"+perHistoriesVOs[1].gradetime+"["+perHistoriesVOs[1].encoding+"]</th><th>"+perHistoriesVOs[2].gradetime+"["+perHistoriesVOs[2].encoding+"]</th></tr>"
		        				str += isObjectValueEqual(perHistoriesVOs[0],perHistoriesVOs[1],perHistoriesVOs[2]);
		        			}
		        			str += "</table>"
		        			$(".modal-body").empty().append(str);
		        			$("#myModal").modal("show");
		        		}else{
		        			popup("数据获取失败,请重新尝试");
		        		}
		        	}else if(data.status == -1){
		        		alert(1);
		        		popup(data.msg);
		        	}else{
		        		alert(2);
		        		popup(data.msg);
		        	}
		        }
			});
			 
			$("#differentItems").click(function(){
		       	if($(this).attr("data-value")== "true"){
		       		$(".identical").css("display","none");
			       	$(this).attr("data-value","false").text("查看全部信息");
		       	}else{
		       		$(".identical").css("display","");
			       	$(this).attr("data-value","true").text("只查看不同项");
		       	}
		    });
			
			function isObjectValueEqual(a,b,c) {
				var aProps = Object.getOwnPropertyNames(a);
		        var str = "";
		        var array = [{"name":"name","value":"姓名"},{"name":"usedname","value":"曾用名"},{"name":"politicaloutlook","value":"政治面貌"},{"name":"idissuingagency","value":"身份证签发机关"},
		                     {"name":"idtermend","value":"身份证截至日期"},{"name":"idtermstart","value":"身份证签发日期"},{"name":"fertilitycondition","value":"生育情况"},{"name":"maritalstatus","value":"婚姻状况"},
		                     {"name":"permanentaddress","value":"现居住地址"},{"name":"presentaddress","value":"户籍所在地"},{"name":"encoding","value":"评分编号"},{"name":"finallevel","value":"终评等级"},{"name":"finalscore","value":"终评分数"},
		                     {"name":"gradetime","value":"评分时间"},{"name":"preliminarylevel","value":"初评等级"},{"name":"preliminaryscore","value":"初评分数"},{"name":"modelName","value":"模型名称"},{"name":"modelVersion","value":"模型版本"},];
		        if(c != null){
		        	for (var i = 1; i < aProps.length; i++) {
			            var propName = aProps[i];
			            var d = "";
			            if (a[propName] == b[propName]) {
			            	d = "<tr class = 'identical'>";
			            }else{
			            	d = "<tr class ='danger'>";
			            }
			            if (c[propName] == b[propName]) {
			            	d = "<tr class = 'identical'>";
			            }else{
			            	d = "<tr class ='danger'>";
			            }
			            if (c[propName] == a[propName]) {
			            	d = "<tr class = 'identical'>";
			            }else{
			            	d = "<tr class ='danger'>";
			            }
			            var aname = a[propName];
			            if(aname == null){
			            	aname = " - ";
			            }
			            var bname = b[propName];
			            if(bname == null){
			            	bname = " - ";
			            }
			            var cname = c[propName];
			            if(cname == null){
			            	cname = " - ";
			            }
			            var title = "";
			            for ( var element in array) {
							if(array[element].name == propName){
								title = array[element].value;
							}
						}
			            if(propName != "baseinfoid" ){
							str += d+"<td>"+title+"</td><td>"+aname+"</td><td>"+bname+"</td><td>"+cname+"</td></tr>";
			            }
			        }
		        }else{
		        	 for (var i = 1; i < aProps.length; i++) {
				            var propName = aProps[i];
				            if (a[propName] == b[propName]) {
				            	str += "<tr class = 'identical'>";
				            }else{
				            	str += "<tr class ='danger'>";
				            }
				            var aname = a[propName];
				            if(aname == null){
				            	aname = " - ";
				            }
				            var bname = b[propName];
				            if(bname == null){
				            	bname = " - ";
				            }
				            var title = "";
				            for ( var element in array) {
								if(array[element].name == propName){
									title = array[element].value;
								}
							}
				            if(propName != "baseinfoid" ){
				            	str += "<td>"+title+"</td><td>"+aname+"</td><td>"+bname+"</td></tr>";
				            }
				        }
		        }
		       return str;
			}
		}
		
	</script>
</html>