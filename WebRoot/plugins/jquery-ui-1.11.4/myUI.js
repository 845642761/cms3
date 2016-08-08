$.datepicker.setDefaults({
	dateFormat : 'yy-mm-dd',
	monthNamesShort : [ "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一",
			"十二" ],
	monthNames : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月",
			"十一月", "十二月" ],
	dayNamesMin : [ "日", "一", "二", "三", "四", "五", "六" ],
	changeYear : true,
	changeMonth : true,
	showButtonPanel : true,
	showMonthAfterYear : true,
	currentText : '今天',
	closeText : '关闭',
	onSelect : function() {
		var form, inp;
		inp = $(this);
		form = inp.closest('form');
		if (form.length === 1 && form.attr('novalidate') === 'novalidate') {
			$(this).valid();
		}
	}
});

window.ui = {};
(function() {
	ui.createDialog = function(tag, opt) {
		var $dialog, iframeHeight;
		var def = {
			modal : true,
			autoOpen : false
		};
		if (tag == 'frame') {
			$dialog = $('<div><iframe frameborder="0" style="width:100%;height:'
					+ opt.height + 'px;"></iframe></div>');
			if (opt.height == null || opt.height == '') {
				iframeHeight = 'auto';
			} else {
				iframeHeight = opt.height;
			}
			$dialog.setSrc = function(src) {
				$dialog.html('<div><iframe frameborder="0" src="' + src
						+ '" style="width:100%;height:' + iframeHeight
						+ 'px;"></iframe></div>');
			};
		} else {
			$dialog = $(tag);
		}
		return $dialog.dialog($.extend(def, opt));
	};

	ui.createOverlay = function() {
		var $overlay = $(
				"<div class='ajaxOverlay'><i class='fa fa-spinner fa-pulse'></i></div>")
				.css({
					display : 'none',
					position : 'fixed',
					top : 0,
					left : 0,
					bottom : 0,
					right : 0,
					background : 'rgba(0,0,0,.08)',
					textAlign : 'center',
					zIndex : 110
				// jquery.ui.dialog是100
				});
		$overlay.find('.fa').css({
			display : 'inline-block',
			marginTop : 300,
			fontSize : '4em'
		});
		$overlay.appendTo('body');
		return $overlay;
	};

	/* 加载中遮罩标签 */
	ui.tags = {
		AjaxOverlay : function() {
			var $overlay = ui.createOverlay();
			this.$overlay = $overlay;
			jQuery.ajaxSetup({
				global : true
			});
			$(document).ajaxComplete(function() {
				$overlay.hide();
			}).ajaxStart(function() {
				$overlay.show();
			});
		}
	};
})();