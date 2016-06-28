package org.me.web.system.department.entity;

import java.io.Serializable;
import java.util.Date;

import org.me.core.common.base.BaseEntity;

/**
 * Department 部门
 * @author cheng_bo
 * @date: 2015年11月3日 10:29:34
 */
public class Department extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String strId;//主键Id
	private String strPid;//父级id
	private String strName;//部门名称
	private String strDescription;//部门描述
	private String strLevel;//部门级别
	private Integer nState;//状态,-1-禁用,0-正常
	private Integer nChild;//子级数
	private Date dtCreateTime;//创建时间
	
	public String getStrId() {
		return strId;
	}
	public void setStrId(String strId) {
		this.strId = strId;
	}
	public String getStrPid() {
		return strPid;
	}
	public void setStrPid(String strPid) {
		this.strPid = strPid;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrDescription() {
		return strDescription;
	}
	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}
	public String getStrLevel() {
		return strLevel;
	}
	public void setStrLevel(String strLevel) {
		this.strLevel = strLevel;
	}
	public Integer getnState() {
		return nState;
	}
	public void setnState(Integer nState) {
		this.nState = nState;
	}
	public Integer getnChild() {
		return nChild;
	}
	public void setnChild(Integer nChild) {
		this.nChild = nChild;
	}
	public Date getDtCreateTime() {
		return dtCreateTime;
	}
	public void setDtCreateTime(Date dtCreateTime) {
		this.dtCreateTime = dtCreateTime;
	}
}
