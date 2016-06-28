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
<style type="text/css">
	div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 1px;}
	div#rMenu ul li:first-of-type{margin-top: 0px;}
	div#rMenu ul li:last-of-type{margin-bottom: 0px;}
	div#rMenu ul li{
		margin: 1px 0;
		padding: 0 5px;
		cursor: pointer;
		list-style: none outside none;
		background-color: #DFDFDF;
	}
</style>
</head>
<body>
	<div style="width: 100%; height: 100%;">
		<div style="width: 20%; height: 100%; float: left;">
			<ul id="tree" class="ztree"></ul>
		</div>
		<div style="width: 80%; height: 500px; float: left;">
			<iframe name="right" marginWidth="0" marginHeight="0" frameBorder="0" width="100%" height="100%" scrolling="no"></iframe>
		</div>
	</div>

	<div id="rMenu">
		<ul>
			<li id="m_add" onclick="addTreeNode();">增加节点</li>
			<li id="m_del" onclick="removeTreeNode();">删除节点</li>
		</ul>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/plugins/zTreeV3.5.19/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
	var ztree,rMenu;
	var setting = {
		view: {
			selectedMulti: false,
			dblClickExpand: false
		},
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
		},
		callback: {
			onRightClick: OnRightClick
		}
	};
	
	/**
	 * 右键菜单
	 */
	function OnRightClick(event, treeId, treeNode) {
		if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
			ztree.cancelSelectedNode();
			showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) {
			ztree.selectNode(treeNode);
			showRMenu("node", event.clientX, event.clientY);
		}
	}

	function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		$("#m_add").show();
		$("#m_del").show();
		rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
		$("body").bind("mousedown", onBodyMouseDown);
	}
	
	function hideRMenu() {
		if (rMenu) rMenu.css({"visibility": "hidden"});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	
	function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			rMenu.css({"visibility" : "hidden"});
		}
	}
	
	var addCount = 1;
	function addTreeNode() {
		hideRMenu();
		var newNode = { name:"增加" + (addCount++)};
		if (ztree.getSelectedNodes()[0]) {
			newNode.checked = ztree.getSelectedNodes()[0].checked;
			ztree.addNodes(ztree.getSelectedNodes()[0], newNode);
		} else {
			ztree.addNodes(null, newNode);
		}
	}
	function removeTreeNode() {
		hideRMenu();
		var nodes = ztree.getSelectedNodes();
		if (nodes && nodes.length>0) {
			if (nodes[0].children && nodes[0].children.length > 0) {
				var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
				if (confirm(msg)==true){
					ztree.removeNode(nodes[0]);
				}
			} else {
				ztree.removeNode(nodes[0]);
			}
		}
	}
	
	/**
	 * 数据过滤
	 */
	function ajaxDataFilter(treeId, parentNode, responseData) {
	    if (responseData) {
	      for(var i =0; i < responseData.length; i++) {
	    	  responseData[i].url="<%=basePath%>/system/department/toDetail.do?id="+responseData[i].id;
	    	  responseData[i].target="right";
	      }
	    }
	    return responseData;
	};
	
	/**
	 * 刷新
	 */
	function refreshNode(){
		var nodes = ztree.getSelectedNodes();
		if (nodes.length>0) {
			ztree.reAsyncChildNodes(nodes[0].getParentNode(), "refresh");
		}
	};
	
	$(document).ready(function(){
		ztree = $.fn.zTree.init($("#tree"), setting);
		rMenu = $("#rMenu");
	});
</script>
</html>