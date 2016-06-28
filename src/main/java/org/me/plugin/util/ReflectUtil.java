package org.me.plugin.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ReflectUtil {
	/**
	 * 获取类的参数集合
	 * @author: chengbo
	 * @date: 2015年10月21日 11:45:15
	 */
	public ArrayList<String> getDeclaredFields(Object ob) {
		if(ob == null)
			return null;
		ArrayList<String> al=new ArrayList<>();
		Field field[] = ob.getClass().getDeclaredFields();
		for (Field f : field) {
			String name=f.getName();
			if("serialVersionUID".equals(name))
				continue;
			al.add(name);
		}
		return al;
	}
	
	public Object getMethodReturn(Object obj,String methodName) {
		methodName = "get" 
                + methodName.substring(0, 1).toUpperCase()
                + methodName.substring(1);
		Method getMethod;
		try {
			Class<?> cla = obj.getClass();
			getMethod = cla.getMethod(methodName, new Class[] {});
			Object value = getMethod.invoke(obj, new Object[] {});
			return value;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
