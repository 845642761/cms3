package org.me.core.exception;

/**
 * service自定义异常
 * @author: chengbo
 * @date: 2015年10月12日 10:58:32
 */
public class ServiceExecption extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ServiceExecption(String msg){
		super(msg);
	}
	
	public ServiceExecption(String msg, Throwable t){
		super(msg, t);
	}
}
