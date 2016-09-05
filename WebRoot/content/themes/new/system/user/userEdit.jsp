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
		<input type="hidden" name="strUserId" value="${systemUser.strUserId}" />
		<tr>
			<td class="label">登录账号</td>
			<td><input type="text" name="strLoginName" value="${systemUser.strLoginName}" /></td>
		</tr>
		<tr>
			<td class="label">姓名</td>
			<td><input type="text" name="strName" value="${systemUser.strName}" /></td>
		</tr>
		<tr>
			<td class="label">性别</td>
			<td>
				<select name="nSex">
					<option value="1">男</option>
					<option value="2">女</option>
					<option value="0">未填写</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="label">手机号</td>
			<td><input type="text" name="strMobile" value="${systemUser.strMobile}" /></td>
		</tr>
		<tr>
			<td class="label">电话</td>
			<td><input type="text" name="strPhone" value="${systemUser.strPhone}" /></td>
		</tr>
		<tr>
			<td class="label">邮箱</td>
			<td><input type="text" name="strEmail" value="${systemUser.strEmail}" /></td>
		</tr>
		<tr>
			<td class="label">QQ</td>
			<td><input type="text" name="strQQ" value="${systemUser.strQQ}" /></td>
		</tr>
		<tr>
			<td class="label">微信</td>
			<td><input type="text" name="strWeChar" value="${systemUser.strWeChar}" /></td>
		</tr>
		<tr>
			<td class="label">出生日期</td>
			<td><input type="text" name="dtBirthday" value="<fmt:formatDate value="${systemUser.dtBirthday}" pattern="yyyy-MM-dd HH:mm:ss" type="date" />" /></td>
		</tr>
		<tr>
			<td class="label">地址</td>
			<td><input type="text" name="strAddress" value="${systemUser.strAddress}" /></td>
		</tr>
		<tr>
			<td class="label">状态</td>
			<td>
				<select name="nState">
					<option value="0">待审核</option>
					<option value="1">正常</option>
					<option value="-1">禁用</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="label">注册时间</td>
			<td><input type="text" name="dtCreateTime" value="<fmt:formatDate value="${systemUser.dtCreateTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date" />" /></td>
		</tr>
		<tr>
			<td class="label">修改时间</td>
			<td><input type="text" name="dtUpdateTime" value="<fmt:formatDate value="${systemUser.dtUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date" />" /></td>
		</tr>
	</table>
</body>
</html>