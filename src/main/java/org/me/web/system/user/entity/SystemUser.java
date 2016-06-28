package org.me.web.system.user.entity;

import java.io.Serializable;
import java.util.Date;

import org.me.core.common.base.BaseEntity;

/**
 * loginUser
 * @author cheng_bo
 * @date:2015年5月24日 21:27:01
 */
public class SystemUser extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String strUserId;//登录id（字母小写）
	private String strPassword;//密码
	private String strName;//姓名
	private Integer nSex;//性别, 0-未填写,1-男,2-女
	private String strMobile;//手机号
	private String strPhone;//电话
	private String strEmail;//邮箱
	private String strQQ;//QQ
	private String strWeChar;//微信
	private Date dtBirthday;//出生日期:yyyy-mm-dd HH:mm:ss
	private String strAddress;//地址
	private String strHeadURL;//头像
	private Integer nState;//状态,0-待审核,1-正常,-1-禁用
	private String strDeptId;//所在部门ID
	private Date dtCreateTime;//注册时间:yyyy-mm-dd HH:mm:ss
	private Date dtUpdateTime;//修改时间:yyyy-mm-dd HH:mm:ss
	
	public String getStrUserId() {
		return strUserId;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}
	public String getStrPassword() {
		return strPassword;
	}
	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public Integer getnSex() {
		return nSex;
	}
	public void setnSex(Integer nSex) {
		this.nSex = nSex;
	}
	public String getStrMobile() {
		return strMobile;
	}
	public void setStrMobile(String strMobile) {
		this.strMobile = strMobile;
	}
	public String getStrPhone() {
		return strPhone;
	}
	public void setStrPhone(String strPhone) {
		this.strPhone = strPhone;
	}
	public String getStrEmail() {
		return strEmail;
	}
	public void setStrEmail(String strEmail) {
		this.strEmail = strEmail;
	}
	public String getStrQQ() {
		return strQQ;
	}
	public void setStrQQ(String strQQ) {
		this.strQQ = strQQ;
	}
	public String getStrWeChar() {
		return strWeChar;
	}
	public void setStrWeChar(String strWeChar) {
		this.strWeChar = strWeChar;
	}
	public Date getDtBirthday() {
		return dtBirthday;
	}
	public void setDtBirthday(Date dtBirthday) {
		this.dtBirthday = dtBirthday;
	}
	public String getStrAddress() {
		return strAddress;
	}
	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}
	public String getStrHeadURL() {
		return strHeadURL;
	}
	public void setStrHeadURL(String strHeadURL) {
		this.strHeadURL = strHeadURL;
	}
	public Integer getnState() {
		return nState;
	}
	public void setnState(Integer nState) {
		this.nState = nState;
	}
	public String getStrDeptId() {
		return strDeptId;
	}
	public void setStrDeptId(String strDeptId) {
		this.strDeptId = strDeptId;
	}
	public Date getDtCreateTime() {
		return dtCreateTime;
	}
	public void setDtCreateTime(Date dtCreateTime) {
		this.dtCreateTime = dtCreateTime;
	}
	public Date getDtUpdateTime() {
		return dtUpdateTime;
	}
	public void setDtUpdateTime(Date dtUpdateTime) {
		this.dtUpdateTime = dtUpdateTime;
	}
}
