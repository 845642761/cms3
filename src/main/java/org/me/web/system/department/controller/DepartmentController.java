package org.me.web.system.department.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.me.core.common.Result;
import org.me.core.common.ResultUtil;
import org.me.core.exception.ViewExecption;
import org.me.plugin.ztree.dto.ZtreeDTO;
import org.me.web.core.common.base.BaseController;
import org.me.web.system.department.entity.Department;
import org.me.web.system.department.service.IDepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/system/department/")
public class DepartmentController extends BaseController {
	private Logger log = Logger.getLogger(DepartmentController.class);
	@Resource
	private IDepartmentService departmentService;
	
	@RequestMapping("list")
	public ModelAndView list() {
		return new ModelAndView("/system/department/list");
	}
	
	/**
	 * 根据父id获取子节点（加载部门树）
	 * @author: chengbo
	 * @date: 2016年3月17日 11:42:59
	 */
	@RequestMapping("getDepartmentByPid")
	@ResponseBody
	public List<ZtreeDTO> getDepartmentByPid(String id) {
		List<ZtreeDTO> departments = null;
		if(!StringUtils.hasText(id))
			id = "0";
		try {
			Department d = new Department();
			d.setStrPid(id);
			List<Department> depts = departmentService.getList(d);
			departments = toZtree(depts);
		} catch (Exception e) {
			log.error("get department list error : ",e);
			throw new ViewExecption("get department list error");
		}
		return departments;
	}
	
	/**
	 * 查看详情
	 * @author: chengbo
	 * @date: 2016年3月17日 11:43:57
	 */
	@RequestMapping("toDetail")
	public ModelAndView toDetail(String id) {
		ModelAndView mav = new ModelAndView("/system/department/edit");
		try {
			Department dept = departmentService.getById(id);
			mav.addObject("department", dept);
		} catch (Exception e) {
			log.error("get department error : ",e);
			throw new ViewExecption("get department error");
		}
		return mav;
	}
	
	/**
	 * 添加或保存
	 * @author: chengbo
	 * @date: 2016年3月18日 14:25:53
	 */
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public Result saveOrUpdate(Department dept) {
		Result resoult = ResultUtil.buildSuccessResoult();
		String id = dept.getStrId();
		try {
			if(!StringUtils.hasText(id)){
				departmentService.save(dept);
			}else {
				departmentService.update(dept);
			}
		} catch (Exception e) {
			log.error("saveOrUpdate department error : ",e);
			resoult = ResultUtil.buildErrorResoult("修改失败");
		}
		return resoult;
	}
	
	@RequestMapping("delById")
	@ResponseBody
	public Result delById(String id) {
		Result resoult = ResultUtil.buildSuccessResoult();
		if(!StringUtils.hasText(id)){
			return ResultUtil.buildErrorResoult("请选择删除"); 
		}
		try {
			departmentService.delById(id);
		} catch (Exception e) {
			log.error("delete department error : ",e);
			resoult = ResultUtil.buildErrorResoult("删除失败");
		}
		return resoult;
	}
	
	@RequestMapping("add")
	public ModelAndView add(Department dept) {
		ModelAndView mav = new ModelAndView("/system/department/edit");
		mav.addObject("department", dept);
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 查看所有部门
	 * @author: chengbo
	 * @date: 2015年11月23日 15:55:32
	 */
	/*@RequestMapping("listAllDept")
	@ResponseBody
	public List<ZtreeDTO> listAllDept() {
		List<ZtreeDTO> depts = new ArrayList<>();
		try {
			Department dept = new Department();
			dept.setnState(1);
			depts = toZtree((List<Department>) departmentService.getList(dept).getObject()); 
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw new ViewExecption("部门查询失败！");
		}
		return depts;
	}*/
	
	/*@SuppressWarnings("unchecked")
	
	
	@RequestMapping("updateDeptName")
	@ResponseBody
	public Resoult updateDeptName(String id,String name) {
		Resoult resoult = new Resoult();
		if(!StringUtils.hasText(id) || !StringUtils.hasText(name))
			return resoult; 
		try {
			resoult = departmentService.updateDeptName(id, name);
		} catch (Exception e) {
			e.printStackTrace();
			resoult.setCode(-1);
			resoult.setInfo(e.getMessage());
		}
		return resoult; 
	}
	
	
	@RequestMapping("addDept")
	@ResponseBody
	public Resoult addDept(String pid,String name) {
		Resoult resoult = new Resoult();
		try {
			departmentService.addDept(pid, name);
		} catch (Exception e) {
			e.printStackTrace();
			resoult.setCode(-1);
			resoult.setInfo(e.getMessage());
		}
		return resoult; 
	}*/
	
	
	/**
	 * 转为ztree数据
	 * @param: list
	 * @author: chengbo
	 * @date: 2015年11月3日 14:56:04
	 */
	private List<ZtreeDTO> toZtree(List<Department> list) {
		List<ZtreeDTO> ztrees = new ArrayList<>();
		for (Department dept : list) {
			ZtreeDTO ztree = new ZtreeDTO();
			ztree.setId(dept.getStrId());
			ztree.setpId(dept.getStrPid());
			ztree.setName(dept.getStrName());
			int hasChild = dept.getnChild();
			if(hasChild > 0){
				ztree.setIsParent(true);
			}
			ztrees.add(ztree);
		}
		return ztrees;
	}
}
