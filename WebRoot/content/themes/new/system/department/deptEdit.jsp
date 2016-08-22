<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../common/init.jsp" %>
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
	<form id="edit" action="<%=basePath%>/system/department/saveOrUpdate" method="post" style="padding: 20px;">
		<input type="hidden" name="strId" value="${department.strId}" />
		<ul style="text-align: left;margin: 0px; padding: 0px;">
			<li style="width: 100%; float: left;">
				<a class="button" onclick="toAddDept('${department.strId}')">
					<b>添加子部门</b>
				</a>
				<a class="button" onclick="saveOrUpdate()">
					<b>保存</b>
				</a>
				<c:if test="${!department.strId eq '0'}">
					<a class="button" onclick="delById('${department.strId}')">
						<b>删除</b>
					</a>
				</c:if>
			</li>
			<li style="width: 100%; float: left;">
				部门名称：
				<input type="text" name="strName" value="${department.strName}" />
			</li>
			<li style=" margin-bottom: 10px; width: 100%; float: left;">
				部门描述：
				<input type="text" name="strDescription" value="${department.strDescription}" />
			</li>
			
			<c:if test="${!empty department.strId}">
				<li style="margin-bottom: 10px;">
					创建时间：
					<input type="text" readonly="readonly" value="<fmt:formatDate value='${department.dtCreateTime}' pattern='yyyy-MM-dd HH:mm:ss' type='date' dateStyle='long'/>" />
				</li>
			</c:if>
		</ul>
	</form>
</body>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

	new parent.parent.ui.tags.AjaxOverlay;
	var dialog = parent.parent.ui.createDialog('<div>',{
		title:'添加子部门'
	});

	function delById(id){
		$.ajax({
			type : 'POST',
			url :'<%=basePath %>/system/department/delById?id='+id,
			success:function(data) { 
				if(data.code != 0){
					alert(data.info);
				}
				parent.delRefreshNode();
				window.location.href = window.location.href;
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {    
				alert("异常");
				parent.refreshNode();
				return;
			}
		});
	}
	
	function saveOrUpdate(){
		var form = $('#edit');
		$.ajax({
			type : 'POST',
			url  : form.attr('action'),
			data : form.serialize(),
			dataType : 'json',
			success:function(data) { 
				if(data.code != 0){
					alert(data.info);
				}
				parent.refreshNode();
				window.location.href = window.location.href;
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {    
				alert("异 常");
				parent.refreshNode();
				return;
			}
		});
	}
	
	/**
	 * 添加子部门
	 */
	function toAddDept(pid){
		$.get('<%=basePath %>/system/department/add?strPid='+pid,function(data){
			dialog.html(data).dialog('open');
		});
	};
	
	dialog.on('click','#add',function(){
		var form = dialog.find('#addChild');
		$.ajax({
			type : 'POST',
			url  : form.attr('action'),
			data : form.serialize(),
			dataType : 'json',
			success:function(data) { 
				if(data.code != 0){
					alert(data.info);
				}
				dialog.dialog('close');
				parent.refreshNode();
				window.location.href = window.location.href;
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {    
				alert("异常");
				parent.refreshNode();
				dialog.dialog('close');
			}
		});
	});
</script>
</html>