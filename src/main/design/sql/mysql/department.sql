USE cms;

-- 创建部门表
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`(
	strId							VARCHAR(32)		NOT NULL,											-- 主键Id
	strPid							VARCHAR(32)		NOT NULL,											-- 父级id
	strName							VARCHAR(32)		NOT NULL,											-- 部门名称
	strDescription					VARCHAR(200),														-- 部门描述
	strLevel						VARCHAR(32)		NOT NULL,											-- 部门级别
	nChild							INT				NOT NULL DEFAULT 0,									-- 子级数: 0:无 (1:有一个子部门  2：有两个子部门)
	nState							TINYINT			NOT NULL DEFAULT 0,									-- 状态:0-正常,-1-禁用
	dtCreateTime					DATETIME		NOT NULL,											-- 创建时间:yyyy-mm-dd HH:mm:ss
	PRIMARY KEY(strId),
	UNIQUE(strId)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=utf8;


-- 初始化数据
INSERT INTO `department` (
	`strId`,
	`strPid`,
	`strName`,
	`strDescription`,
	`nState`,
	`dtCreateTime`,
	`nChild`,
	`strLevel`
)VALUES(
		'1',
		'0',
		'组织机构',
		'组织机构',
		'0',
		now(),
		'0',
		'001'
);
