USE cms;

-- 创建部门表
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`(
	strId							VARCHAR(32)		NOT NULL			COMMENT "主键Id",	
	strPid							VARCHAR(32)		NOT NULL			COMMENT "父级id",	
	strName							VARCHAR(32)		NOT NULL			COMMENT "部门名称",
	strDescription					VARCHAR(200)						COMMENT "部门描述",
	strLevel						VARCHAR(32)		NOT NULL			COMMENT "部门级别",
	nChild							INT				NOT NULL DEFAULT 0	COMMENT "子级数: 0:无 (1:有一个子部门  2：有两个子部门)",
	nState							TINYINT			NOT NULL DEFAULT 0	COMMENT "状态:0-正常,-1-禁用",
	dtCreateTime					DATETIME		NOT NULL			COMMENT "创建时间:yyyy-mm-dd HH:mm:ss",
	PRIMARY KEY(strId),
	UNIQUE(strId)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=utf8;