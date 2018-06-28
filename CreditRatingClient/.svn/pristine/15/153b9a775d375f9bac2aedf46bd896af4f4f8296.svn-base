<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/Page/Share/taglibs.jsp" %>
<font color="#393939">每页:${pageView.maxresult }条  总数:${pageView.totalrecord }条  </font>
    
<c:if test="${pageView.currentpage>5 }"><a href="javascript:topage('1')"><font color="#393939">首 页</font></a></c:if>
<c:if test="${pageView.currentpage>1 }"><a href="javascript:topage('${pageView.currentpage-1}')"><font color="#393939">上一页</font></a></c:if>

<c:forEach begin="${pageView.pageindex.startindex }" end="${pageView.pageindex.endindex }" var="wp">
	<c:if test="${pageView.currentpage==wp }"><b><font color="#00A0E9">[${wp}]</font></b></c:if>
	<c:if test="${pageView.currentpage!=wp }"><a href="javascript:topage('${wp}')"><font color="#393939">『${wp}』</font></a></c:if>
</c:forEach>

<c:if test="${pageView.currentpage<pageView.totalpage }"><a href="javascript:topage('${pageView.currentpage+1}')"><font color="#393939">下一页</font></a></c:if>
<c:if test="${pageView.currentpage<pageView.totalpage-4 }"><a href="javascript:topage('${pageView.totalpage}')"><font color="#393939">尾 页</font></a></c:if>
