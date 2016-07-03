package org.me.core.exception;

/**
 * 工具包自定义异常
 * @author: chengbo
 * @date: 2016年7月3日16:56:51
 */
public class ToolExecption extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ToolExecption(String msg){
		super(msg);
	}
	
	public ToolExecption(String msg, Throwable t){
		super(msg, t);
	}
}
