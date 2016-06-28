$.datepicker.setDefaults({
		dateFormat: 'yy-mm-dd',
		monthNamesShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],
		monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		dayNamesMin: ["日","一", "二", "三", "四", "五", "六" ],
		changeYear: true,
		changeMonth: true,
		showButtonPanel: true,
		showMonthAfterYear: true,
		currentText: '今天',
		closeText: '关闭',
		onSelect: function() {
		    var form, inp;
		    inp = $(this);
		    form = inp.closest('form');
		if (form.length === 1 && form.attr('novalidate') === 'novalidate') {
		        $(this).valid();
		    }
		}
});

/**
 * 弹窗
 * @param url
 * @param title
 */
function openDialog(url,title){
	var dialog = $('<div>').dialog({
		width: 600,
		autoOpen: false
	});
	
	$.post(url, function (res) {
		dialog.html(res);
		dialog.dialog('option', 'title', title);
		dialog.dialog('open');
	});
}
