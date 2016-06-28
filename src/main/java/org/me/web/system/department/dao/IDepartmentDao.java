package org.me.web.system.department.dao;

import java.util.List;
import org.me.web.system.department.entity.Department;

public interface IDepartmentDao {
	public int insert(Department dept);
	public int delById(String id);
	public Department getById(String id);
	public int update(Department dept);
	public List<Department> getList(Department dept);
	public int getListSize(Department dept);//查询列表大小
	public int delByLevel(String level);//根据级别删除
	public String getMaxLevel(String level);//根据当前级别中最大数
}
