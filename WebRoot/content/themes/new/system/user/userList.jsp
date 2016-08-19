<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../common/init.jsp" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>">
<title>${cmsInfo.title}</title>
<meta charset="utf-8" />
<link href="<%=basePath %>/content/themes/${cmsInfo.theme}/static/common/normalize.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/plugins/zTreeV3.5.19/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div>
		<ul>
			<c:forEach items="${users}" var="user">
				<li>${user.strLoginName}</li>	
			</c:forEach>
		</ul>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery-1.11.3.min.js"></script>
</html>