<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>${cmsInfo.title}</title>
<meta charset="utf-8" />
<link href="<%=basePath %>/content/themes/${cmsInfo.theme}/static/common/normalize.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/content/themes/${cmsInfo.theme}/static/system/user/login.css" rel="stylesheet" type="text/css" />
</head>
<body class="login_bg">
	<div id="page-header">
		<div class="container">
			<a href="javascript:;" id="top-logo">
				<span>${cmsInfo.title}</span>
			</a>
		</div>
	</div>
	<div id="page-content">
		<div class="container">
			<div class="b-login">
				<div class="bl-head">
					<h2>
						<span>${cmsInfo.title}</span>
					</h2>
				</div>
				<div class="bl-body">
					<div class="blb-title">
						<span>会员登录</span>
					</div>
					<div class="blb-message"></div>
					<form id="form">
						<div class="form-group">
							<label class="fg-t user" for="ip_account"><span>账号</span></label>
							<div class="fg-c">
								<input id="username" type="text" placeholder="账号">
							</div>
						</div>
						<div class="form-group">
							<label class="fg-t psw" for="ip_password"><span>密码</span></label>
							<div class="fg-c">
								<input id="password" type="password" placeholder="密码">
							</div>
						</div>
						<div class="form-group">
							<label class="fg-t chkcode"><span>验证码</span></label>
							<div class="fg-c">
								<input id="chkcode" type="text" placeholder="验证码"> <img
									id="code-img" src="">
							</div>
						</div>
						<div class="form-btn">
							<button id="login-btn">
								<span>登录</span>
							</button>
						</div>
					</form>
				</div>
				<!-- .bl-body:end -->
			</div>
			<!-- .b-login:end -->

			<div class="b-information">
				<div class="bi-bar">
					<span class="close"></span>
				</div>
				<h4>
					<span>易微行汽车租赁管理系统</span>
				</h4>
				<p>1)、客户可以通过互联网方便的查看车型信息、车辆租赁价格、租赁手续及增值服务内容，还可以直接通过互联网进行车辆的预订；</p>
				<p>2)、汽车租赁工作人员只需要输入最少量的信息即可完成车辆的预订、出车、结算过程；</p>
				<p>3)、系统具备智能化的业务提醒功能，如订单到期提醒、逾期还车提醒、预授权到期提醒、收费差额提醒、车辆保养提醒、客户生日提醒等；</p>
				<p>4)、系统将车辆定位监控与业务集成为一体，除了具备GPS监控的常用功能外，还针对出租行业的特点，能够通过GPS设备直接统计车辆行驶的公里数、行驶时间并直接与还车结算挂接起来。</p>
			</div>

		</div>
		<!-- .container:end -->
	</div>
	<!-- #page-content -->
	<div class="b-bg"></div>
</body>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	if (window != window.top) {
	    window.top.location.href = '/system/user/login';
	}
	
	$(function() {
    	var oName = $('#username');
    	var oPassword = $('#password');
    	var oChkCode = $('#chkcode');
        var oChkCodeImg = $('#code-img');
    	
        var oInfo = $('.b-information');
        
        //动画
        setTimeout(function() {
        	oInfo.addClass('fadeInRight');
        }, 500);
        
        //验证码
        fCodeImgRefresh();
        oChkCodeImg.click(function() {
        	fCodeImgRefresh();
        });
        function fCodeImgRefresh(){
        	oChkCodeImg.attr('src',"<%=basePath%>/common/image/getImageCode?_="+Math.random());
    	}
        
    	//表单提交
        $('#login-btn').click( function(e) {	

        	var bErrorStatus = true;
        	var sErrorInfo = '';
        	var oErrorbox = $('.b-login .blb-message');

            if ($.trim(oName.val()) === '') {
            	sErrorInfo = '请填写账户名';
            	bErrorStatus = false;
            }
            if ($.trim(oPassword.val()) === '') {
            	sErrorInfo = '请填写密码';
                bErrorStatus = false;
            }
            if ($.trim(oChkCode.val()) === '') {
            	sErrorInfo = '验证码不能为空';
                bErrorStatus = false;
            }
            
            if (bErrorStatus) {
            	e.preventDefault();
            	$.post('<%=basePath%>/system/user/ssoLogin', {
            		strUserId : oName.val(),
            		strPassword : oPassword.val(),
                    code: oChkCode.val()
                }, function(data) {
                    if (data.code == 0) {
                        location.href = '<%=basePath%>/system/main';
                    } else {
                    	oErrorbox.html('<div class="alert">'+data.info+'</div>');
                    	oChkCode.val('');
                        fCodeImgRefresh();
                    }
                });
            }else{
            	oErrorbox.html('<div class="alert">'+sErrorInfo+'</div>');
            }
            
            e.preventDefault();
        });
    	
    	function fFormValidate(){
    		var status = true;
            if ($.trim(oName.val()) === '') {
                $oName.closest('.form-group').addClass('has-error');
                status = false;
            }
            if ($.trim($code.val()) === '') {
                $code.closest('.form-group').addClass('has-error');
                status = false;
            }
            if ($.trim($password.val()) === '') {
                $password.closest('.form-group').addClass('has-error');
                status = false;
            }
            return status;
    	}
    });
</script>
</html>