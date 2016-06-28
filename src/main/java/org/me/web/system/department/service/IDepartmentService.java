package org.me.web.system.department.service;

import java.util.List;
import org.me.web.system.department.entity.Department;

public interface IDepartmentService {
	/**
	 * 保存
	 * @param dept
	 * @return dept id
	 * @date 2016年5月16日 11:05:07
	 */
	public String save(Department dept);
	
	public Department getById(String id);

	public void delByIds(List<String> ids);
	
	public void delById(String id);
	
	public Department update(Department dept);
	
	public List<Department> getList(Department t);
}
