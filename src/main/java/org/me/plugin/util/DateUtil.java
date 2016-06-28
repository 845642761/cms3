package org.me.plugin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * dateUtils
 * @author cheng_bo
 * @date 2015年6月9日 14:12:23
 */
public class DateUtil {
	private Logger logger = Logger.getLogger(DateUtil.class);
	
	private SimpleDateFormat sdf;
	
	public DateUtil() {
		if(sdf==null){
			sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	}

	/**
	 * 格式化时间
	 * @author cheng_bo
	 * @date 2015年6月9日 14:16:03
	 */
	public Date forMatDate(String strDate) {
		if(StringUtils.isEmpty(strDate)){
			logger.error("strDate is null!");
			return null;
		}
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			logger.error("DateUtils.forMatDate error:"+e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取指定日期的后x天时间
	 * @param date
	 * @param postDay
	 * @return date
	 * @author: chengbo
	 * @date: 2015年9月1日 11:21:03
	 */
	public Date getAfterDayTime(Date date,int postDay) {
		if(date==null)
			date=new Date();
		return new Date(date.getTime()+postDay * 24 * 60 * 60 * 1000L);
	}
	
	/**
	 * 获取指定日期的前x天时间
	 * @param date
	 * @param preDay
	 * @return date
	 * @author: chengbo
	 * @date: 2015年9月1日 11:21:03
	 */
	public Date getBeforeDayTime(Date date,int preDay) {
		if(date==null)
			date=new Date();
		return new Date(date.getTime() - preDay * 24 * 60 * 60 * 1000L);
	}
	
	/**
	 * 获取指定日期的后x小时时间
	 * @param date
	 * @param postHour
	 * @return date
	 * @author: chengbo
	 * @date: 2015年9月1日 11:21:03
	 */
	public Date getAfterHourTime(Date date,int postHour) {
		if(date==null)
			date=new Date();
		return new Date(date.getTime()+postHour * 60 * 60 * 1000L);
	}
	
	/**
	 * 获取指定日期的前x小时时间
	 * @param date
	 * @param preHour
	 * @return date
	 * @author: chengbo
	 * @date: 2015年9月1日 11:21:03
	 */
	public Date getBeforeHourTime(Date date,int preHour) {
		if(date==null)
			date=new Date();
		return new Date(date.getTime()-preHour * 60 * 60 * 1000L);
	}
	
	/**
	 * 获取指定日期的后x分钟时间
	 * @param date
	 * @param postMinute
	 * @return date
	 * @author: chengbo
	 * @date: 2015年9月1日 11:21:03
	 */
	public Date getAfterMinuteTime(Date date,int postMinute) {
		if(date==null)
			date=new Date();
		return new Date(date.getTime()+postMinute * 60 * 1000L);
	}
	
	/**
	 * 获取指定日期的前x分钟时间
	 * @param date
	 * @param preMinute
	 * @return date
	 * @author: chengbo
	 * @date: 2015年9月1日 11:21:03
	 */
	public Date getBeforeMinuteTime(Date date,int preMinute) {
		if(date==null)
			date=new Date();
		return new Date(date.getTime()-preMinute* 60 * 1000L);
	}
	
	/**
	 * 获取指定日期的后x秒时间
	 * @param date
	 * @param postSecond
	 * @return date
	 * @author: chengbo
	 * @date: 2015年9月1日 11:21:03
	 */
	public Date getAfterSecondTime(Date date,int postSecond) {
		if(date==null)
			date=new Date();
		return new Date(date.getTime()+postSecond * 1000L);
	}
	
	/**
	 * 获取指定日期的前x秒时间
	 * @param date
	 * @param preMinute
	 * @return date
	 * @author: chengbo
	 * @date: 2015年9月1日 11:21:03
	 */
	public Date getBeforeSecondTime(Date date,int preSecond) {
		if(date==null)
			date=new Date();
		return new Date(date.getTime()-preSecond * 1000L);
	}
}
