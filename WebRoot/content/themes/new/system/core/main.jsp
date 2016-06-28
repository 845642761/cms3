<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../common/init.jsp" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>">
<title>${cmsInfo.title}</title>
<meta charset="utf-8" />
<link href="<%=basePath %>/content/themes/${cmsInfo.theme}/static/common/normalize.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/content/themes/${cmsInfo.theme}/static/system/core/main.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/plugins/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/plugins/jquery-ui-1.11.4/jquery-ui.theme.min.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/plugins/jquery-ui-1.11.4/jquery-ui-timepicker-addon.min.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/plugins/jquery-ui-1.11.4/myUI.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- 头部 -->
	<div class="top">
		<!-- 左边 -->
		<div class="top_left"></div>
		
		<!-- 右边 -->
		<div class="top_right"></div>
	</div>

	<!-- 标签 -->
	<div class="nav">
		<ul>
			<c:forEach var="items" items="${menuOneLevel}">
				<li onclick="changeMenu(${items.strId})">${items.strName}</li>
			</c:forEach>
		</ul>
	</div>

	<!-- main -->
	<div class="main" id="main">
		<!-- 左边 -->
		<div class="main_left">
			<c:forEach var="items" items="${menuOneLevel}">
				<ul id="${items.strId}" style="display: none">
					<c:forEach var="item" items="${menuTwoLevel[items.strId]}">
						<li><a class="left_a" href="<%=basePath %>${item.strPermission}" target="iframe">${item.strName}</a></li>
					</c:forEach>
				</ul>
			</c:forEach>
		</div>
		<!-- 右边 -->
		<div class="main_right" style="height: 500px;">
			<iframe name="iframe" id="iframe" src="<%=basePath %>/system/index" marginWidth="0" marginHeight="0" frameBorder="0" width="100%" height="100%" scrolling="no"></iframe>
		</div>
	</div>

	<!-- 尾部 -->
	<div class="bottom"></div>
</body>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery-ui-1.11.4/jquery-ui-timepicker-addon.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery-ui-1.11.4/myUI.js"></script>
<script type="text/javascript">
	/**
	 * @description: 菜单切换
	 * @author: cheng_bo
	 * @date: 2015年8月26日 15:50:07
	 */
	function changeMenu(id){
		$(".main_left ul").each(function(){
			$(this).css('display','none');
		});
		$('#'+id).css('display','block');
	};
</script>
</html>