package org.me.plugin.ztree.dto;

import java.io.Serializable;

public class ZtreeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;//id
	private String pId;//父级主键
	private String name;//名称
	private String icon;//图标
	private String url;//url
	private String target;//target
	private boolean open;//true 表示节点为 展开 状态;  false 表示节点为 折叠 状态; 默认值：false
	private boolean isParent;//true 表示是父节点; false 表示不是父节点
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
}
