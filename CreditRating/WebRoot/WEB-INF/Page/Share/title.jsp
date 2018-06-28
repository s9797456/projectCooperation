<%@ page language="java" pageEncoding="UTF-8"%>
<title><c:if test="${empty organization}">${sessionScope.obj_name}</c:if>
          <c:if test="${!empty organization}">${organization.name}${sessionScope.obj_name2}</c:if></title>