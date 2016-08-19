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
	<div style="width: 100%; height: 100%;">
		<div style="width: 20%; height: 100%; float: left;">
			<ul id="tree" class="ztree"></ul>
		</div>
		<div style="width: 80%; height: 500px; float: left;">
			<iframe id = "userList" name="right" marginWidth="0" marginHeight="0" frameBorder="0" width="100%" height="100%" scrolling="no"></iframe>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/plugins/zTreeV3.5.19/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
	var ztree;
	var setting = {
		async: {
			enable: true,
			dataType:"json",
			url: "<%=basePath%>/system/department/getDepartmentByPid.do",
			autoParam: ["id"],
			type: 'POST',
			dataFilter: ajaxDataFilter
		},
		simpleDate: {
			enable:true,
	    	idKey:"id",
	        idPKey:"pId",
	        rootPid:null
		}
	};
	
	/**
	 * 数据过滤
	 */
	function ajaxDataFilter(treeId, parentNode, responseData) {
	    if (responseData) {
	      for(var i =0; i < responseData.length; i++) {
	    	  responseData[i].url="<%=basePath%>/system/user/userList.do?strDeptId="+responseData[i].id;
	    	  responseData[i].target="right";
	      }
	    }
	    return responseData;
	};
	
	$(document).ready(function(){
		ztree = $.fn.zTree.init($("#tree"), setting);
	});
</script>
</html>