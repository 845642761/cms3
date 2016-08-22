<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${cmsInfo.title}</title>
<meta charset="utf-8" />
<%@ include file="../../init/cssInit.jsp" %>
<link href="<%=basePath %>/plugins/zTreeV3.5.19/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<table class="table">
		<thead>
			<th>登录账号</th>
			<th>姓名</th>
			<th>手机号</th>
			<th>性别</th>
			<th>状态</th>
			<th>注册时间</th>
		</thead>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.strLoginName}</td>
				<td>${user.strName}</td>
				<td>${user.strMobile}</td>
				<td>
					<c:choose>
						<c:when test="${user.nSex == 1 }">男</c:when>
						<c:when test="${user.nSex == 2 }">女</c:when>
						<c:otherwise>未填写</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${user.nState == 1 }">正常</c:when>
						<c:when test="${user.nState == -1 }">禁用</c:when>
						<c:otherwise>待审核</c:otherwise>
					</c:choose>
				</td>
				<td>
					<fmt:formatDate value="${user.dtCreateTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date" />
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery-1.11.3.min.js"></script>
</html>