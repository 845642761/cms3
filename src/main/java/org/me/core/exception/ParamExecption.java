package org.me.core.exception;

/**
 * 参数异常类
 * @author: chengbo
 * @date: 2016年7月3日17:12:02
 */
public class ParamExecption extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ParamExecption(String msg){
		super(msg);
	}
	
	public ParamExecption(String msg, Throwable t){
		super(msg, t);
	}
}
