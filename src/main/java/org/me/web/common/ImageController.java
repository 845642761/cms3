package org.me.web.common;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.me.core.exception.ViewExecption;
import org.me.plugin.image.VerificationCodeUtil;
import org.me.web.core.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/common/image/")
public class ImageController extends BaseController {
	private Logger log = Logger.getLogger(ImageController.class);
	
	/**
	 * 生成验证码
	 * @param width：宽
	 * @param height：高
	 * @author chengbo
	 * @date 2016年4月19日 11:56:12
	 */
	@ResponseBody
	@RequestMapping("getImageCode")
	public void getImageCode(Integer width, Integer height) {
		HttpServletResponse response = super.getResponse();
		response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg"); 
        
        if(width == null || width == 0)
        	width = 100;
        if(height == null || height == 0)
        	height = 40;
        
        try {
        	//生成图片  
			new VerificationCodeUtil().outputImage(width, height, response.getOutputStream(), "1234");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
			throw new ViewExecption("生成验证码失败");
		}
	}
}
