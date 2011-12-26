$(function() {
	/*
	 * 검색 입력창에서 사용하는 것으로, 값을 입력하고 500ms이상 정지하고 있으면 입렵박스의 href값으로 ajax를 호출하여, 가져온
	 * 값을, js_start_work class(새업무시작하기에서 업무검색하는 입력창)는 id가 upload_work_list인 곳에
	 * 보여주고, 그렇지 않으면 내부모와 같은 수준에 있는 div 영역에 보여준다.
	 */
	$('input.js_auto_complete').live('keyup', function(e) {
		if(e.which>=9 && e.which<=45) return;
		var input = $(e.target);
		var start_work = input.parents('div.js_start_work');
		var chatter_name = input.parents('div.js_chatter_names');
		var target;
		if (!isEmpty(input[0].value))
			input.next('div').removeClass('srch_ico').addClass('btn_im_x');
		if (!isEmpty(start_work))
			target = start_work.find('#upload_work_list');
		else if(!isEmpty(chatter_name))
			target = chatter_name.siblings('div.js_chatter_list');
		else
			target = input.parent().next('div');
		var url = input.attr('href');
		var lastValue = input[0].value;
		setTimeout(function() {
			var currentValue = input[0].value;
			if (lastValue === currentValue) {
				$.ajax({
					url : url,
					data : {
						key : input[0].value
					},
					context : input,
					success : function(data, status, jqXHR) {
						console.log("e.which=" + e.which);
						target.html(data);
						target.show();
					}
				});
			} else {
			}
		}, 300);
	});

	/*
	 * 검색 입력창에서 검색을 하고나서, 포커스가 다른곳으로 이동을 하면, 500ms후에 검색결과 창을 숨긴다.
	 */
	$('input.js_auto_complete').live('focusout', function(e) {
		var input = $(e.target);
		var start_work = input.parents('div.js_start_work');
		var user_name = input.parents('div.js_community_names');
		var chatter_name = input.parents('div.js_chatter_names');
		var target;
		if (!isEmpty(start_work))
			target = start_work.find('#upload_work_list');
		else if (!isEmpty(user_name))
			target = user_name.next('div');
		else if(!isEmpty(chatter_name))
			target = chatter_name.siblings('div.js_chatter_list');
		else
			target = input.parent().siblings('div');
		setTimeout(function() {
			input[0].value = '';
			input.next('div').removeClass('btn_im_x').addClass('srch_ico');
			target.html('').hide();
		}, 500);
	});
	
	$('input.js_auto_complete').live('keydown', function(e) {
		if(e.which == $.ui.keyCode.UP || e.which == $.ui.keyCode.DOWN  ){
			var input = $(e.target);
			var start_work = input.parents('div.js_start_work');
			var chatter_name = input.parents('div.js_chatter_names');

			var target = input.parent().next('div');
			if(!isEmpty(start_work)) target =  start_work.find('#upload_work_list');
			else if(!isEmpty(chatter_name)) target = chatter_name.siblings('div.js_chatter_list');

			var list = target.find('li');
			if(isEmpty(list)) return;
			var sw_hover = target.find('.sw_hover');
			if(e.which == $.ui.keyCode.DOWN){
				if(isEmpty(sw_hover) || isEmpty(sw_hover.next()))
					$(list[0]).addClass('sw_hover').siblings().removeClass('sw_hover');					
				else
					sw_hover.next().first().addClass('sw_hover').siblings().removeClass('sw_hover');
				
			}else if(e.which == $.ui.keyCode.UP){
				if(isEmpty(sw_hover) || isEmpty(sw_hover.prev()))
					$(list[list.length-1]).addClass('sw_hover').siblings().removeClass('sw_hover');					
				else
					sw_hover.prev().first().addClass('sw_hover').siblings().removeClass('sw_hover');					
			}
		}else if(e.which == $.ui.keyCode.ENTER){
			var input = $(e.target);
			var start_work = input.parents('div.js_start_work');
			var chatter_name = input.parents('div.js_chatter_names');

			var target = input.parent().next('div');
			if(!isEmpty(start_work)) target =  start_work.find('#upload_work_list');
			else if(!isEmpty(chatter_name)) target = chatter_name.siblings('div.js_chatter_list');

			target.find('.sw_hover:first a').click();
			input.focusout();
		}
	});
	
	$('.nav_srch_list').find('li').live('hover', function(e){
		$(e.target).parents('li:first').addClass('sw_hover').siblings().removeClass('sw_hover');
	});
	
	$('#upload_work_list').find('li').live('hover', function(e){
		$(e.target).parents('li:first').addClass('sw_hover').siblings().removeClass('sw_hover');
	});
	
	$('div.js_chatter_list').find('li').live('hover', function(e){
		$(e.target).parents('li:first').addClass('sw_hover').siblings().removeClass('sw_hover');
	});

	$('div.js_srch_x').live('click', function(e) {
		var input = $(e.target).prev();
		input.value = "";
		input.next('div').removeClass('btn_im_x').addClass('srch_ico');
		return false;
	});

	$('.js_select_community').live( 'click', function(e) {
		var input = $(e.target);
		
		var comName = input.attr('comName');
		var comId = input.attr('comId');
		var target = input.parents('.js_community_list').prev().find('.js_selected_communities');				
		target.siblings('input.js_auto_complete').value = '';
		var userField = target.parents('td.js_type_userField');
		if(!isEmpty(userField) && userField.attr('multiUsers') !== 'true') {
			var inputTarget = userField.find('input.js_auto_complete');
			inputTarget.hide();
			inputTarget.next('.js_srch_x').hide();
			if(inputTarget.parents('.sw_required').hasClass('sw_error')){
				inputTarget.parents('.sw_required').removeClass('sw_error');
				$('form.js_validation_required').validate({ showErrors: showErrors}).form();
			}
		}
		var oldHTML = target.html();
		if (oldHTML == null)
			oldHTML = "";
		var communityItems = $(target).find('span.js_community_item');
		var isSameId = false;
		for(var i=0; i<communityItems.length; i++){
			var oldComId = $(communityItems[i]).attr('comId');
			if(oldComId !=null && oldComId === comId){
				isSameId = true;
				break;
			}
		}
		if(!isSameId){
			var newHTML = oldHTML
				+ "<span class='js_community_item user_select' comId='" + comId+ "'>"
				+ comName
				+ "<span class='btn_x_gr'><a class='js_remove_community' href=''> x</a></span></span>";
			target.html(newHTML);
		}
		target.next().focus();
		return false;
	});

	$('.js_remove_community').live('click', function(e) {
		var input = $(e.target);
		
		var userField = input.parents('td.js_type_userField');
		if(!isEmpty(userField) && userField.attr('multiUsers') !== 'true') {
			var inputTarget = userField.find('input.js_auto_complete')
			inputTarget.show();
			inputTarget.next('.js_srch_x').show();
		}
		var selected_users = input.parents('.js_selected_communities');
		input.parents('span.js_community_item').remove();
		selected_users.next().focus();
		return false;
	});
});