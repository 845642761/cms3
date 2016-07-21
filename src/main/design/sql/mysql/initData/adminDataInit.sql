USE cms;

-- 初始化菜单数据
INSERT INTO `permission` (`strId`, `strPid`, `strName`, `strPermission`, `strDescription`, `nType`, `nState`, `dtCreateTime`) 
VALUES ('001', '0', '系统管理', NULL, NULL, '0', '0', now()),
('001-002', '001', '用户管理', '/system/user/list.do', '用户管理', '0', '0', now()),
('001-001', '001', '部门管理', '/system/department/list.do', '部门管理', '0', '0', now());

-- 初始化角色数据
INSERT INTO `role` (`strId`, `strName`, `strDescription`, `nState`) 
VALUES ('1', 'admin', '管理员', '0');

-- 初始化角色权限数据
INSERT INTO `rolepermission` (`strId`, `strRoleId`, `strPermissionId`) 
VALUES ('1', '1', '001'),
('2', '1', '001-001'),
('3', '1', '001-002');

-- 初始化部门数据
INSERT INTO `department` (`strId`,`strPid`,`strName`,`strDescription`,`nState`,`dtCreateTime`,`nChild`,`strLevel`)
VALUES('1' ,'0','组织机构' ,'组织机构' ,'0',now() ,'0' ,'001');

-- 初始化用户数据
INSERT INTO `systemuser` (`strUserId`,`strLoginName`, `strPassword`, `strName`, `nSex`, `strMobile`, `strPhone`, `strEmail`, `strQQ`, `strWeChar`, `dtBirthday`, `strAddress`, `strHeadURL`, `nState`, `strDeptId`, `dtCreateTime`, `dtUpdateTime`) 
VALUES ('1', 'admin', '00000000', 'admin', '0', NULL , NULL, NULL , NULL, NULL , now() , NULL , NULL, '1', '1', now() , now());

-- 初始化用户角色数据
INSERT INTO `userrole` (`strId`, `strLoginId`, `strRoleId`) 
VALUES ('1', '1', '1');





