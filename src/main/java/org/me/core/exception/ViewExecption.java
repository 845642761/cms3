package org.me.core.exception;

/**
 * View自定义异常
 * @author: chengbo
 * @date: 2015年10月21日 14:16:58
 */
public class ViewExecption extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ViewExecption(String msg){
		super(msg);
	}
	
	public ViewExecption(String msg, Throwable t){
		super(msg, t);
	}
}
