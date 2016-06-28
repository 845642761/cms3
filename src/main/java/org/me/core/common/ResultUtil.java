package org.me.core.common;

public class ResultUtil {
	
	public static Result buildSuccessResoult() {
		return new Result();
	}
	
	public static Result buildErrorResoult(String info) {
		Result r = new Result();
		r.setCode(-1);
		r.setInfo(info);
		return r;
	}
}
