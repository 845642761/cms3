USE cms;

-- 创建用户-角色对应表
DROP TABLE IF EXISTS `userRole`;
CREATE TABLE `userRole`(
	strId							VARCHAR(32)		NOT NULL			COMMENT "主键Id",	
	strLoginId						VARCHAR(50)		NOT NULL			COMMENT "用户id",	
	strRoleId						VARCHAR(32)		NOT NULL			COMMENT "角色Id",	
	PRIMARY KEY(strId),
	UNIQUE(strId)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=utf8;

-- 创建角色表
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`(
	strId							VARCHAR(32)		NOT NULL			COMMENT "主键Id",	
	strName							VARCHAR(32)		NOT NULL			COMMENT "角色名称",
	strDescription					VARCHAR(100)	NOT NULL			COMMENT "角色描述",
	nState							TINYINT			NOT NULL DEFAULT 0	COMMENT "状态:0-正常,-1-禁用",
	PRIMARY KEY(strId),
	UNIQUE(strId)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=utf8;

-- 创建权限表
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`(
	strId							VARCHAR(32)		NOT NULL			COMMENT "主键Id",	
	strPid							VARCHAR(32)		NOT NULL			COMMENT "父级id",	
	strName							VARCHAR(32)		NOT NULL			COMMENT "权限名称",
	strPermission					VARCHAR(100)						COMMENT "权限标识",
	strDescription					VARCHAR(200)						COMMENT "权限描述",
	nType							TINYINT			NOT NULL DEFAULT 0	COMMENT "类型:0-菜单,1-按钮",
	nState							TINYINT			NOT NULL DEFAULT 0	COMMENT "状态:0-正常,-1-禁用",
	dtCreateTime					DATETIME		NOT NULL			COMMENT "创建时间:yyyy-mm-dd HH:mm:ss",
	PRIMARY KEY(strId),
	UNIQUE(strId)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=utf8;

-- 创建角色-权限对应表
DROP TABLE IF EXISTS `rolePermission`;
CREATE TABLE `rolePermission`(
	strId							VARCHAR(32)		NOT NULL			COMMENT "主键Id",	
	strRoleId						VARCHAR(32)		NOT NULL			COMMENT "角色Id",	
	strPermissionId					VARCHAR(32)		NOT NULL			COMMENT "权限id",	
	PRIMARY KEY(strId),
	UNIQUE(strId)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=utf8;