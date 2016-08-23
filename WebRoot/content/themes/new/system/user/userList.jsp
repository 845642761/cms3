<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${cmsInfo.title}</title>
<meta charset="utf-8" />
<%@ include file="../../init/cssInit.jsp" %>
</head>
<body>
	<table class="table">
		<thead>
			<th></th>
			<th>登录账号</th>
			<th>姓名</th>
			<th>手机号</th>
			<th>性别</th>
			<th>状态</th>
			<th>注册时间</th>
		</thead>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><input name="strUserId" value="${user.strUserId}" type="checkbox" /></td>
				<td class = "toDetail" value = "${user.strUserId}">${user.strLoginName}</td>
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
<script type="text/javascript">
	new parent.parent.ui.tags.AjaxOverlay;
	var dialog = parent.parent.ui.createDialog('<div>',{
		title:'用户编辑'
	});
	
	/**
	 * 用户编辑
	 */
	$('.toDetail').click(function(){
		$.get('<%=basePath %>/system/user/toDetail?strUserId='+ $(this).attr('value'),function(data){
			dialog.html(data).dialog('open');
		});
	});
</script>
</html>