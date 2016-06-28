package org.me.web.plugin.shiro.entity;

import java.io.Serializable;
import java.util.Date;

import org.me.core.common.base.BaseEntity;

/**
 * 权限
 * @author: cheng_bo
 * @date: 2015年8月6日 10:23:37
 */
public class Permission extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String strId;//主键Id
	private String strPid;//父级主键
	private String strName;//权限名称
	private String strPermission;//权限标识
	private String strDescription;//权限描述
	private Integer nType;//类型:0-菜单,1-按钮
	private Integer nState;//状态:0-正常,-1-禁用
	private Integer nHasChild;//是否有子级: 0:无  1:有
	private Date dtCreateTime;//创建时间
	
	public String getStrId() {
		return strId;
	}
	public void setStrId(String strId) {
		this.strId = strId;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrPermission() {
		return strPermission;
	}
	public void setStrPermission(String strPermission) {
		this.strPermission = strPermission;
	}
	public String getStrDescription() {
		return strDescription;
	}
	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}
	public Integer getnType() {
		return nType;
	}
	public void setnType(Integer nType) {
		this.nType = nType;
	}
	public Integer getnState() {
		return nState;
	}
	public void setnState(Integer nState) {
		this.nState = nState;
	}
	public String getStrPid() {
		return strPid;
	}
	public void setStrPid(String strPid) {
		this.strPid = strPid;
	}
	public Integer getnHasChild() {
		return nHasChild;
	}
	public void setnHasChild(Integer nHasChild) {
		this.nHasChild = nHasChild;
	}
	public Date getDtCreateTime() {
		return dtCreateTime;
	}
	public void setDtCreateTime(Date dtCreateTime) {
		this.dtCreateTime = dtCreateTime;
	}
}
