package org.me.core.exception;

/**
 * 未登录自定义异常
 * @author: chengbo
 * @date: 2015年10月21日 14:16:58
 */
public class NoLoginExecption extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NoLoginExecption(String msg){
		super(msg);
	}
	
	public NoLoginExecption(String msg, Throwable t){
		super(msg, t);
	}
}
