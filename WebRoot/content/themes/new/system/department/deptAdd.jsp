<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>${cmsInfo.title}</title>
<meta charset="utf-8" />
<link href="<%=basePath %>/content/themes/${cmsInfo.theme}/static/common/normalize.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/content/themes/${cmsInfo.theme}/static/common/common.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	li{margin-bottom: 4px;}
</style>
</head>
<body>
	<form id="addChild" action="<%=basePath%>/system/department/saveOrUpdate" method="post" style="padding: 20px;">
		<input type="hidden" name="strPid" value="${strPid}" />
		<ul style="text-align: left;margin: 0px; padding: 0px;">
			<li style="width: 100%; float: left;">
				部门名称：
				<input type="text" name="strName" value="${department.strName}" />
			</li>
			<li style=" margin-bottom: 10px; width: 100%; float: left;">
				部门描述：
				<input type="text" name="strDescription" value="${department.strDescription}" />
			</li>
			<li>
				<a id = "add" class="button">
					<b>保存</b>
				</a>
			</li>
		</ul>
	</form>
</body>
</html>