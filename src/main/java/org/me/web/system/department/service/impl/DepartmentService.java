package org.me.web.system.department.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.me.core.exception.ServiceExecption;
import org.me.plugin.util.UuidUtil;
import org.me.web.system.department.dao.IDepartmentDao;
import org.me.web.system.department.entity.Department;
import org.me.web.system.department.service.IDepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class DepartmentService implements IDepartmentService {
	private Logger log = Logger.getLogger(DepartmentService.class);
	
	@Resource
	private IDepartmentDao departmentDao;

	@Transactional
	@Override
	public String save(Department dept) {
		/**
		 * 更新父部门状态
		 */
		if(StringUtils.hasText(dept.getStrPid())){
			Department parent = cumulativeOneParentChild(dept.getStrPid());
			
			String maxLevel = departmentDao.getMaxLevel(parent.getStrLevel());
			if(StringUtils.hasLength(maxLevel) && Integer.parseInt(maxLevel) != 0){
				dept.setStrLevel(String.format("%03d",(Integer.parseInt(maxLevel)+1)));
			}else {
				dept.setStrLevel("001");
			}
		}else {
			dept.setStrLevel("001");
			dept.setStrPid("0");
			
			/**
			 * 更新父部门
			 */
			cumulativeOneParentChild("0");
		}

		String deptId = UuidUtil.getUUID();
		dept.setStrId(deptId);
		dept.setnChild(0);
		dept.setDtCreateTime(new Date());
		try {
			departmentDao.insert(dept);
		} catch (Exception e) {
			log.error("insert department error : ", e);
			throw new ServiceExecption("insert department error");
		}
		return deptId;
	}
	
	/**
	 * 父部门 子级数+1
	 */
	private Department cumulativeOneParentChild(String pId){
		Department parent = departmentDao.getById(pId);
		parent.setnChild(parent.getnChild() + 1);
		departmentDao.update(parent);
		return parent;
	}

	@Override
	public Department getById(String id) {
		if(!StringUtils.hasText(id)){
			log.error("department id is null");
			throw new ServiceExecption("get department error : department id is null");
		}
		Department dept = null;
		
		try {
			dept = departmentDao.getById(id);
		} catch (Exception e) {
			log.error("get department error : ", e);
			throw new ServiceExecption("get department error : id is " + id);
		}
		return dept;
	}

	@Transactional
	@Override
	public void delByIds(List<String> ids) {
		if(ids == null)
			return;
		for (String id : ids) {
			delById(id);
		}
	}

	@Transactional
	@Override
	public void delById(String id) {
		if(!StringUtils.hasText(id)){
			return;
		}
		
		try {
			Department dept = departmentDao.getById(id);
			if(!"0".equals(dept.getStrPid())){
				departmentDao.delByLevel(dept.getStrLevel());
				
				Department parent = departmentDao.getById(dept.getStrPid());
				parent.setnChild(parent.getnChild() - 1);
				departmentDao.update(parent);
			}else {
				departmentDao.delById(id);
			}
		} catch (Exception e) {
			log.error("delete department error : ", e);
			throw new ServiceExecption("delete department error : id is " + id);
		}
	}

	@Override
	public Department update(Department dept) {
		try {
			departmentDao.update(dept);
		} catch (Exception e) {
			log.error("update department error : ", e);
			throw new ServiceExecption("update department error : id is " + dept.getStrId());
		}
		return dept;
	}

	@Override
	public List<Department> getList(Department dept) {
		List<Department> depts = null;
		try {
			depts = departmentDao.getList(dept);
		} catch (Exception e) {
			log.error("get department error : ", e);
			throw new ServiceExecption("get department list error");
		}
		return depts;
	}
}
