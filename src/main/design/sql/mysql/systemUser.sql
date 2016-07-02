USE cms;

-- 创建用户表
DROP TABLE IF EXISTS `systemUser`;
CREATE TABLE `systemUser`(
	strUserId						VARCHAR(50)		NOT NULL,											-- 登录id（字母小写）
	strPassword						VARCHAR(32)		NOT NULL,											-- 密码
	strName							VARCHAR(30)		NOT NULL,											-- 姓名
	nSex							TINYINT			NOT NULL DEFAULT 0,									-- 性别, 0-未填写,1-男,2-女
	strMobile						VARCHAR(20),														-- 手机号
	strPhone						VARCHAR(20),														-- 电话
	strEmail						VARCHAR(60),														-- 邮箱
	strQQ							VARCHAR(20),														-- QQ
	strWeChar						VARCHAR(20),														-- 微信
	dtBirthday						DATETIME,															-- 出生日期:yyyy-mm-dd HH:mm:ss
	strAddress						VARCHAR(100),														-- 地址
	strHeadURL						VARCHAR(300),														-- 头像
	nState							TINYINT			NOT NULL DEFAULT 0,									-- 状态,0-待审核,1-正常,-1-禁用
	strDeptId						VARCHAR(32)		NOT NULL,											-- 所属部门ID
	dtCreateTime					DATETIME		NOT NULL,											-- 注册时间:yyyy-mm-dd HH:mm:ss
	dtUpdateTime					DATETIME		NOT NULL,											-- 修改时间:yyyy-mm-dd HH:mm:ss
	PRIMARY KEY(strUserId),
	UNIQUE(strUserId)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=utf8;